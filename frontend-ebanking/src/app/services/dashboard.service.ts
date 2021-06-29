import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Client } from 'app/interfaces/client';
import { Compte } from 'app/interfaces/compte';
import { Virement} from 'app/interfaces/virement';
import { Operation} from 'app/interfaces/operation';
import {Agence} from 'app/interfaces/agence'
import { Agent } from 'app/interfaces/agent';
@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private urlVirement: string;
  private urlCompte: string;
  private urlOperation: string;
  private urlAgent:string;
  private urlAdmin:string;
  headers = new HttpHeaders({
    Authorization: 'Basic ' + btoa(JSON.parse(sessionStorage.getItem("user")).username + ':' + JSON.parse(sessionStorage.getItem("user")).password),
  });
  constructor(private http: HttpClient) { 
    this.urlVirement='http://localhost:8080/virement'; 
    this.urlCompte='http://localhost:8080/compte';
    this.urlOperation='http://localhost:8080/operation'; 
    this.urlAgent='http://localhost:8080/agent';
    this.urlAdmin='http://localhost:8080/admin';
  }

  getComptes(clientId:number){
    return this.http.get<Compte[]>(`${this.urlCompte}/getComptes/${clientId}`,{headers : this.headers});
  }
  getAllVirEnv(compte:Compte){
    return this.http.get<Virement[]>(`${this.urlVirement}/getAllVirEnv/${compte.id}`,{headers : this.headers});
  }
  getAllVirRec(compte:Compte){
    return this.http.get<Virement[]>(`${this.urlVirement}/getAllVirRec/${compte.id}`,{headers : this.headers});
  }
  getAllOper(compte:Compte){
    return this.http.get<Operation[]>(`${this.urlOperation}/getAllOper/${compte.id}`,{headers : this.headers});
  }
  getClientsByAgent(agent:number){
    return this.http.get<Client[]>(`${this.urlAgent}/clients/${agent}`,{headers : this.headers});
  }
  getAgenceOfAgent(agent:number){
    return this.http.get<Agence>(`${this.urlAgent}/agence/${agent}`,{headers : this.headers});
  }
  addAgent(agent:Agent,nomAgence:string){
     return this.http.post(`${this.urlAdmin}/addagent/${nomAgence}`,agent,{headers:this.headers});
  }
  addAgence(agence:Agence){
    return this.http.post(`${this.urlAdmin}/addagence`,agence,{headers:this.headers});
  }
}