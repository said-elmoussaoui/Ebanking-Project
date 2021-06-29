
import { Agent } from "./agent";
import { Client } from "./client";
import { Virement } from "./virement";
import { Operation } from "./operation";

export class Compte {
    id: number;
    numero: string;
    type: string;
    solde: number;
    devise: string;
    creationDate: Date;
    proprietaire: Client;
    creationAgent: Agent;
    virementsEnvoyes:Virement[];
    virementsRecus:Virement[];
    operations:Operation[];
    debitMois:number;
    creditMois:number;
    constructor(type:string,solde:number,devise:string){
        this.type = type;
        this.solde = solde;
        this.devise = devise;
    }
}