import { Admin } from "./admin";
import { Agence } from "./agence";
import { User } from "./utilisateur";

export interface Agent extends User{

    creationAdmin: Admin;
    agence: Agence;

    
}