export class DebiteurVir{
    creancier:string;
    debiteur:string;
    montant:number;
    constructor(numCreancier:string,numDeiteur:string,amount:number){
        this.creancier = numCreancier;
        this.debiteur = numDeiteur;
        this.montant = amount;
    }
}