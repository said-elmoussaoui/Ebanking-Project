import { Compte } from "./compte";

export class Operation {
    id: number;
    compte: Compte;
    date: Date;
    sommeEspece: number;
    sommeCompte: number;
    type: string;
    devise: string;
    constructor(compte:Compte,sommeEspece:number,type:string){
        this.compte = compte;
        this.sommeEspece = sommeEspece;
        this.type = type;
    }
}