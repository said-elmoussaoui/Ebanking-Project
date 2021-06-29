import { Component } from '@angular/core';
import { Rendezvous } from 'app/interfaces/Rendezvous';
import { UserService } from 'app/services/user.service';

import { ToastrService } from "ngx-toastr";


@Component({
    selector: 'notifications-cmp',
    moduleId: module.id,
    templateUrl: 'notifications.component.html'
})

export class NotificationsComponent{
  rendezVous : Rendezvous[];
  constructor(private userService:UserService) {}
  ngOnInit(): void {
    this.getMesRDV();
  }
  public getMesRDV(): void {
    this.userService.getRDV().subscribe((response:Rendezvous[])=>{this.rendezVous=response; console.log(this.rendezVous)})
    
  }
}
