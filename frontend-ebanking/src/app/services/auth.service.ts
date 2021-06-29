import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import {User} from '../interfaces/utilisateur';
import { sha256 } from 'js-sha256';

@Injectable({
  providedIn: 'root'
})

export class AuthenticationService {
  
  constructor(private http:HttpClient) { }
  authentificate(username,password) {
    const headers = new HttpHeaders({
        Authorization: 'Basic ' + btoa(username + ':' + sha256(password)),
    });
    return this.http
      .get<User>('http://localhost:8080/utilisateur/login/'+ username+'/'+sha256(password), {
        headers,
      })
      .pipe(
        map((userData) => {
          var userInf = JSON.stringify(userData);
          sessionStorage.setItem("user", userInf);
          let authString = 'Basic ' + btoa(username + ':' + sha256(password));
          sessionStorage.setItem('basicauth', authString);
          return userData;
        })
      );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem('user');
    console.log(!(user === null));
    return !(user === null);
  }

  logOut() {
    sessionStorage.removeItem('user');
  }
}
