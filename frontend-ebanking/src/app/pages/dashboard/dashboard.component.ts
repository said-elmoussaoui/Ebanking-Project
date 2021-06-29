import { Component, OnInit } from '@angular/core';
import { Compte } from '../../interfaces/compte';
import { Virement } from '../../interfaces/virement';
import { Operation } from '../../interfaces/operation';
import { DashboardService } from '../../services/dashboard.service';
import {User} from '../../interfaces/utilisateur';
import {Client} from '../../interfaces/client'
import { Agence } from 'app/interfaces/agence';
import { VirementService } from 'app/services/virement.service';
import {DebiteurVir} from '../../interfaces/DebiteurVir';
import  Swal from 'sweetalert2';
import { AgentServiceService } from 'app/services/agent-service.service';
import { sha256 } from 'js-sha256';
import { UserService } from 'app/services/user.service';


@Component({
    selector: 'dashboard-cmp',
    moduleId: module.id,
    templateUrl: 'dashboard.component.html'
})

export class DashboardComponent implements OnInit{
  currentUser: User;
  idClient:number;
  clientNonValide:Array<Client>=[];
  clientUpdate:Client;
  comptes:Array<Compte>=[];
  clients:Array<Client>=[];
  listDebiteurs:Array<DebiteurVir>=[];
  agencee:Agence = {} as Agence;
  newVirement:Virement;
  numDebiteur:string;
  sommeEnv:string;
  tempClient:Client;
  comptesOfClient:Array<Compte>=[];
  constructor(
         private dashboardService:DashboardService,
         private virementService:VirementService,
         private agentService:AgentServiceService,
         private userService:UserService
         ){}
    ngOnInit(){
      var getUser = sessionStorage.getItem("user");
      this.currentUser = JSON.parse(getUser);
      this.idClient = this.currentUser.id;
      if(this.currentUser.role == 'Client'){
        this.dashboardService.getComptes(this.idClient).subscribe((res:Array<Compte>)=>{
        this.comptes=res;
        for(let compte of this.comptes){
        
          this.dashboardService.getAllVirEnv(compte).subscribe((res1:Array<Virement>)=>{
            compte.virementsEnvoyes = res1;
            console.log("virement envoyes"+compte.virementsEnvoyes);
            compte.debitMois=0;
            for (let vire of res1) {
              compte.debitMois = compte.debitMois + vire.sommeEnv;
            }
            
          },err=>{
            console.log(err);
          });
          this.dashboardService.getAllVirRec(compte).subscribe((res2:Array<Virement>)=>{
            compte.virementsRecus = res2;
            compte.creditMois=0;
            for (let vire of res2) {
              compte.creditMois = compte.creditMois + vire.sommeRecu;
            }
          },err=>{
            console.log(err);
          });
          this.dashboardService.getAllOper(compte).subscribe((res3:Array<Operation>)=>{
            compte.operations = res3;
          },err=>{
            console.log(err);
          });
        }
      },err=>{
        console.log(err);
      });
    }
    if(this.currentUser.role == 'Agent'){
     this.dashboardService.getClientsByAgent(this.idClient).subscribe((res:Array<Client>)=>{
       this.clients = res;
     },err=>{
      document.location.reload();
   })
     this.dashboardService.getAgenceOfAgent(this.idClient).subscribe((res:Agence)=>{
       this.agencee = res;
     },err=>{
        document.location.reload();
     })
      
    }
    if(this.currentUser.role == 'Admin'){
      this.userService.getAllClients().subscribe((res:Array<Client>)=>{
        console.log(res);
        let clients = res;
        for(let client of clients){
          console.log(client.status);
          if(client.status == 'En Attente de Validation') this.clientNonValide.push(client);
        }
        console.log(this.clientNonValide);
      },err=>{
        console.log(err);
      })
    }
  }
  public onOpenModal(): void {
    console.log('clicked');
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');
    button.setAttribute('data-target', '#addCustomer');
    container.appendChild(button);
    button.click();
}
public addVirement(){
    Swal.fire({
      title: 'Confirmation',
      text: "Voulez-vous effectuer ce virement ?",
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#50C7C7',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui'
    }).then((result) => {
      if (result.isConfirmed) {
        if(this.listDebiteurs.length != 0){
        this.virementService.addVirementMultiple(this.listDebiteurs).subscribe((response : any)=>{
           if(response.length == 0){
            Swal.fire(
              'Erreur !',
               "Le solde est insuffisant .",
              'error'
            )}else if(response.length == this.listDebiteurs.length){
              Swal.fire(
                'Modifié.',
                "Votre virement a été effecuté!",
                'success'
              )
           }else if(response.length <= this.listDebiteurs.length){
            Swal.fire(
              'Attention !',
              "Certains virement ne sont pas effectués",
              'warning'
            )
           }
          this.ngOnInit();
          this.listDebiteurs=[];
         },err=>{
          if(err.error.status == 404){
              Swal.fire(
                'Erreur!',
                 "L'un des comptes n'existe pas , veuillez vérifer.",
                'error'
              )
            }else if(err.error.status == 406){
              Swal.fire(
                'Erreur!',
                'Le solde insuffisant.',
                'error'
              )

            }
           console.log(err);
           this.ngOnInit();
         })
        }else{
          Swal.fire(
            'Erreur!',
             "La liste des virements est vide.",
            'error'
          )
        }
      }
    })
    
}
addCompteVirm(numCreancier:string,numDebiteur:string,amount:string){
  let vir = new DebiteurVir(numCreancier,numDebiteur,+amount);
  console.log(vir);
  if(numCreancier == undefined || numCreancier == "" || numDebiteur == undefined || numDebiteur == "" || amount == undefined || +amount == NaN ){
    Swal.fire(
      'Erreur!',
      "le champe est vide ou ce bénéficiaire exist déja .",
      'error'
    )
  }else{
    this.listDebiteurs.push(vir);
    this.numDebiteur="";
    this.sommeEnv="";
  }
  }
  EmptyList(){
    this.listDebiteurs = [];
  }
  deleteDebiteurFromList(index:number){
       this.listDebiteurs.splice(index,1);
  }
  deleteClient(id:number,nom:string){
    Swal.fire({
      title: 'Confirmation',
      text: "Voulez-vous supprimer le client"+nom,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#50C7C7',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Oui'
    }).then((result) => {
      if (result.isConfirmed) {
         this.agentService.deleteClient(id).subscribe((res:User)=>{
           console.log(res);
           Swal.fire(
            'Modifié!',
            "Le client  a été supprimé .",
            'success'
          )
          this.ngOnInit();
         },err=>{
          Swal.fire(
            'Erreur!',
            "Vous ne pouvez pas supprimer ce client",
            'error'
          )
           console.log(err);
         })
      }
    })
  }
  addCompte(client:number){
    console.log("clientttttttttttttttttt",client);
    Swal.fire({
      title: 'Ajouter compte',
      html: `
      <select id="type" class="swal2-select" style="width:300px;border-size:1px;" placeholder="type">
          <option value="VISA">VISA</option>
          <option value="MasterCard">MasterCard</option>
      </select>
      <input type="text" id="solde" class="swal2-select"  style="width:300px;border-size:1px;" placeholder="solde">
      `,
      confirmButtonText: 'Enregistrer',
      cancelButtonText: 'Annuler',
      cancelButtonColor : 'red',
      focusConfirm: false,
      preConfirm: () => {
        const type = (document.getElementById('type') as HTMLInputElement).value
        const solde = (document.getElementById('solde') as HTMLInputElement).value
        if (!type || !solde ) {
          Swal.showValidationMessage(`Toutes les champs sont oblégatoire.`)
        }
        return { type: type, solde:solde}
      }
    }).then((result) => {
      let compte = new Compte(result.value.type,+result.value.solde,"DH");
      this.agentService.addCompte(compte,client).subscribe((res)=>{
        console.log(res);
        Swal.fire(
          'Créé!',
          "Compte créé avec succes .",
          'success'
        )
      },err=>{
        Swal.fire(
          'Erreur!',
          "Une erreur s'est produite .",
          'error'
        )
      })
    })
  
  }
onOpenModal2(client: Client): void {
    console.log(client);
    console.log('clicked');
    const container = document.getElementById('main-container');
    const button = document.createElement('button');
    button.type = 'button';
    button.style.display = 'none';
    button.setAttribute('data-toggle', 'modal');

    this.tempClient = client;
    button.setAttribute('data-target', '#updateClientModal');
   
    container.appendChild(button);
    button.click();
 }
 updateClient(idClient:number,editForm){
   this.clientUpdate = editForm.value;
   this.clientUpdate.password = sha256(this.clientUpdate.password);
   this.agentService.updateClient(idClient,this.clientUpdate).subscribe((res:Client)=>{
     console.log(res);
     Swal.fire(
      'Modifié!',
      "Client modifié avec succes .",
      'success'
    )
    this.ngOnInit();
  },err=>{
     console.log(err);
    Swal.fire(
      'Erreur!',
      "Un probléme s'est produit .",
      'error'
    )
   })
 }
 showComptes(idClient:number){
     this.dashboardService.getComptes(idClient).subscribe((res:Array<Compte>)=>{
       this.comptesOfClient = res;
       console.log(res);
     })
 }
 deleteCompte(compte:number,idProp:number){
  Swal.fire({
    title: 'Confirmation',
    text: "Voulez-vous effectuer ce virement ?",
    icon: 'warning',
    showCancelButton: true,
    confirmButtonColor: '#50C7C7',
    cancelButtonColor: '#d33',
    confirmButtonText: 'Oui'
  }).then((result) => {
    if (result.isConfirmed) {

   this.agentService.deleteCompte(compte).subscribe((res)=>{
    Swal.fire(
      'Supprimé!',
      "Compte supprimé avec succes .",
      'success'
    )
    this.showComptes(idProp);
   },err=>{
    Swal.fire(
      'Erreur!',
      "Un probléme s'est produit .",
      'error'
    )
   })

  }
})
 }

 addOperation(compte:Compte,type:string){
  console.log(type);
  Swal.fire({
    title: 'Opération',
    html: `
    <input type="text" id="solde" class="swal2-select"  style="width:300px;border-size:1px;" placeholder="solde">
    `,
    confirmButtonText: 'Enregistrer',
    cancelButtonText: 'Annuler',
    cancelButtonColor : 'red',
    focusConfirm: false,
    preConfirm: () => {
      const solde = (document.getElementById('solde') as HTMLInputElement).value
      if (!solde ) {
        Swal.showValidationMessage(`Toutes les champs sont oblégatoire.`)
      }
      return {solde:solde}
    }
  }).then((result) => {
    let operation = new Operation(compte,+result.value.solde,type);
    this.agentService.addOperation(operation).subscribe((res)=>{
      this.showComptes(compte.proprietaire.id);
      Swal.fire(
        'Opération.',
        " Opération effectuée .",
        'success'
      )
    },err=>{
      Swal.fire(
        'Erreur!',
        "Une erreur s'est produite .",
        'error'
      )
    })
  })

 }

 valideClient(clientId:number){
        this.userService.validateClient(clientId).subscribe((res)=>{
          Swal.fire(
            'succés.',
            " Opération effectuée .",
            'success'
          )
          window.location.reload();
        },err=>{
          Swal.fire(
            'Erreur!',
            "Une erreur s'est produite .",
            'error'
          )
        })
 }
 public rejeterClient(id: number){
  console.log(id);
  this.userService.rejeterClient(id).subscribe((res)=>{
    Swal.fire(
      'succés.',
      " Opération effectuée .",
      'success'
    )
    window.location.reload();
  },err=>{
    Swal.fire(
      'Erreur!',
      "Une erreur s'est produite .",
      'error'
    )
  })
}
}