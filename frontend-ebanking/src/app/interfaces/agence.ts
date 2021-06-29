import { Admin } from "./admin";
import { Agent } from "./agent";
import { Client } from "./client";

export interface Agence {
    id: number;
    nom: string;
    adresse: string;
    telephone: string;
    email: string;
    creationAdmin: Admin;
    agents: Agent[];
    clients: Client[];


}