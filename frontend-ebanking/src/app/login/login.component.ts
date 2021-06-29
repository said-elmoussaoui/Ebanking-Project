import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, Validators, FormControl } from '@angular/forms';
import { AuthenticationService } from '../services/auth.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  err:string;
  loginInvalid = false;

  constructor(
    private router: Router,
    private loginservice: AuthenticationService
  ) {}

  ngOnInit() {
    this.form = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
    this.initializeError()
  }
  get username() {
    return this.form.get('username');
  }
  get password() {
    return this.form.get('password');
  }

  checkLogin() {
    console.log(this.username.value,this.password.value);
    this.loginservice
      .authentificate(this.username.value, this.password.value)
      .subscribe(
        (data) => {
         if(data != null){
          this.loginInvalid = false;
          this.router.navigate(['/dashboard']);
          }
        },(error) => {
          Swal.fire(
            'Erreur!',
            "Les informations invalide .",
            'error'
          )
          this.loginInvalid = true;
        }
      );
  }
  initializeError(){
    this.err='';
  }

}