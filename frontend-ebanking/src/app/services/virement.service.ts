import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { DebiteurVir } from 'app/interfaces/DebiteurVir';
import { Virement} from 'app/interfaces/virement';
@Injectable({
  providedIn: 'root'
})
export class VirementService {
  private url: string;
  headers = new HttpHeaders({
    Authorization: 'Basic ' + btoa(JSON.parse(sessionStorage.getItem("user")).username + ':' + JSON.parse(sessionStorage.getItem("user")).password),
  });
  constructor(private http: HttpClient) { 
    this.url='http://localhost:8080/virement';
  }
  /*
  public addVirement(virement:Virement, numero1:string , numero2:string ){
    return this.http.post<Virement>(`${this.url}/add/${numero1}/${numero2}`,virement,{headers : this.headers});
  }
  */
  public addVirementMultiple(virements:Array<DebiteurVir>){
        return this.http
        .post(`${this.url}/addmult`,virements,{headers : this.headers});
  }

}
