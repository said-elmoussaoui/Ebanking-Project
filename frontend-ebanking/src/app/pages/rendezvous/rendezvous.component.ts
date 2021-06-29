import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Rendezvous } from 'app/interfaces/Rendezvous';
import { UserService } from 'app/services/user.service';

@Component({
  selector: 'app-rendezvous',
  templateUrl: './rendezvous.component.html',
  styleUrls: ['./rendezvous.component.css']
})
export class RendezvousComponent implements OnInit {
time:any;
forDate:string;
description:string;
rdv:Rendezvous;
  constructor(private userService: UserService,private router: Router) { }

  ngOnInit(): void {
  }
  dateChanged($event){
    console.log(this.time=$event.target.value)
    var test = this.time.split("T")
    console.log(test[0])
    this.forDate = test[0]+" "+ test[1];
  }
  descriptionn($event){
    console.log("description fct")
    this.description=$event.target.value;
    console.log($event.target.value);
  }

  rendezvous(form){
    console.log(form.value);
    console.log(this.description);
    this.rdv=form.value;
    this.rdv.description=this.description;
    this.rdv.forDate=this.forDate;
    console.log(this.rdv);
    this.userService.addRDV(this.rdv).subscribe((response)=>{console.log(response)});
    this.router.navigate(['/notifications'])

  }

}
