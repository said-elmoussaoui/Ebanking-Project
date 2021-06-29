import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Agent } from 'app/interfaces/agent';
import { Client } from 'app/interfaces/client';
import {Compte} from 'app/interfaces/compte'
import { Operation } from 'app/interfaces/operation';
import { Rendezvous } from 'app/interfaces/Rendezvous';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class AgentServiceService {
  private url: string;
  private BASE_URL:string;
  headers = new HttpHeaders({
    Authorization: 'Basic ' + btoa(JSON.parse(sessionStorage.getItem("user")).username + ':' + JSON.parse(sessionStorage.getItem("user")).password),
  });
  constructor(private http: HttpClient) { 
    this.url='http://localhost:8080/agent';
    this.BASE_URL='http://localhost:8080';
  }
  updateAgenceOfAgent(agentId : number, agence: string){
    console.log(this.headers);
    return this.http.put(`${this.url}/updateAgenceOfAgent/${agentId}/${agence}`,{},{headers:this.headers});
  }
  getAll():Observable<any>{
    return this.http.get(`${this.url}/getall`,{headers : this.headers});
  }
  deleteAgent(agentId:number){
    console.log(agentId);
     return this.http.delete(`${this.url}/deleteAgent/${agentId}`,{headers : this.headers})
  }
  getAllAgences(){
      return this.http.get(`${this.BASE_URL}/agence/getall`,{headers : this.headers});
  }
  deleteClient(client:number){
     return this.http.delete(`${this.url}/deleteClient/${client}`,{headers:this.headers})
  }
  deleteCompte(compte:number){
    return this.http.delete(`${this.BASE_URL}/compte/deleteCompte/${compte}`,{headers:this.headers})
  }
  addCompte(compte:Compte,client:number){
    return this.http.post(`${this.BASE_URL}/compte/add/${client}`,compte,{headers : this.headers});
  }
  addClient(client:Client){
    return this.http.post(`${this.BASE_URL}/client/add`,client,{headers:this.headers});
  }
  updateClient(id:number,client:Client){
    return this.http.put(`${this.BASE_URL}/client/updateClient/${id}`,client,{headers:this.headers});
  }
  addOperation(operation:Operation){
    return this.http.post(`${this.BASE_URL}/operation/add`,operation,{headers:this.headers});
  }
  public getMesRDV(){
    return this.http.get<Rendezvous[]>(`http://localhost:8080/rendezVous/getRdvAgent`,{headers:this.headers});
  }

  public validateRDV(id:number){
    return this.http.put<Rendezvous>(`http://localhost:8080/rendezVous/validateRDV/${id}`,"",{headers:this.headers});
  }
  public rejeterRDV(id:number){
    return this.http.put<Rendezvous>(`http://localhost:8080/rendezVous/rejeterRDV/${id}`,"",{headers:this.headers});
  }
}
