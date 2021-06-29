import { Agent } from "./agent";
import { Client } from "./client";

export interface Rendezvous{
    id:number,
    client:Client,
    agent:Agent,
    forDate:string,
    status:string,
    title:string,
    description:string
}