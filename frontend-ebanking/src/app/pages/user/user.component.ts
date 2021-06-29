import { Component, OnInit } from '@angular/core';
import { sha256 } from 'js-sha256';
import Swal from 'sweetalert2';
import {User} from '../../interfaces/utilisateur'
import {UserService} from '../../services/user.service'
@Component({
    selector: 'user-cmp',
    moduleId: module.id,
    templateUrl: 'user.component.html'
})

export class UserComponent implements OnInit{
    currentUser: User;
    updateUser :User;
    constructor(private userService:UserService){}
    ngOnInit(){
        var getUser = sessionStorage.getItem("user");
        this.currentUser = JSON.parse(getUser);
    }
    updateProfile(username:string,email:string,nom:string,prenom:string,adresse:string,password:string,cin:string,telephone:string){
      this.updateUser = new User(username,email,nom,prenom,adresse,password,cin,telephone);
      if(this.updateUser.password != null && this.updateUser.password != ""){
        this.updateUser.password = sha256(this.updateUser.password);
      }
    
      Swal.fire({
        title: 'Confirmation',
        text: "Voulez-vous modifier votre profil !!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#50C7C7',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Oui'
      }).then((result) => {
        if (result.isConfirmed) {
           this.userService.updateProfile(this.updateUser,this.currentUser.id).subscribe((res:User)=>{
             console.log(res);
             Swal.fire(
              'Modifié!',
              "Votre profil   a été modifié!",
              'success'
            )
            
           // window.location.reload();
           },err=>{
            Swal.fire(
              'Erreur!',
              "Vous ne pouvez pas modifier ce  profil !",
              'error'
            )
             console.log(err);
           })
        }
      })
    }
}
