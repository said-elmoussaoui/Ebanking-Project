import { HttpClient, HttpHeaders, JsonpClientBackend } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import {User} from '../interfaces/utilisateur'
import { Observable } from 'rxjs';
import { Client } from 'app/interfaces/client';
import { Rendezvous } from 'app/interfaces/Rendezvous';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  currentUser:User
  BASE_URL = 'http://localhost:8080';
  headers = new HttpHeaders({
    Authorization: 'Basic ' + btoa(JSON.parse(sessionStorage.getItem("user")).username + ':' + JSON.parse(sessionStorage.getItem("user")).password),
  });
  constructor(private http:HttpClient) { }
  updateProfile(user: User,id:number) { 
      return this.http
        .post(`${this.BASE_URL}/utilisateur/update/${id}`,user,{headers : this.headers}
        )
        .pipe(
          map((userData) => {
            var userInf = JSON.stringify(userData);
            sessionStorage.setItem("user", userInf);
            return userData;
          })
        );
  }

  getAllClients(){
    return this.http.get(`${this.BASE_URL}/client/getall`,{headers : this.headers});
  }
  validateClient(idClient:number){
    return this.http.get(`${this.BASE_URL}/client/validate/${idClient}`,{headers : this.headers});
  }
  public rejeterClient(id){
    return this.http.get<Client[]>(`http://localhost:8080/client/rejeter/${id}`,{headers:this.headers})
  }

  public addRDV(rdv: Rendezvous){
    var getUser = sessionStorage.getItem("user");
        this.currentUser = JSON.parse(getUser);
    return this.http.post<Rendezvous>(`http://localhost:8080/rendezVous/add/${this.currentUser.id}`, rdv,{headers:this.headers});

  }

  public getRDV(){
    var getUser = sessionStorage.getItem("user");
    this.currentUser = JSON.parse(getUser);
    return this.http.get<Rendezvous[]>(`http://localhost:8080/rendezVous/getRDV/${this.currentUser.id}`,{headers:this.headers}); 
  }
}
