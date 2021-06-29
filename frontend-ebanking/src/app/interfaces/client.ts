import { Agence } from "./agence";
import { Agent } from "./agent";
import { User } from "./utilisateur";

export interface Client extends User{
    estOperateur: string;
    creationAgent: Agent;
    agence: Agence;
    status:string;

}