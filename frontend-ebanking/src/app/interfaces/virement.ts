
import { Compte } from "./compte";

export interface Virement {
    id: number;
    creancier: Compte;
    debiteur: Compte;
    date: Date;
    sommeEnv: number;
    sommeRecu: number;
}