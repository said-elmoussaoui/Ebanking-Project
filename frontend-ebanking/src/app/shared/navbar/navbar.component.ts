import { Component, OnInit, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import {AuthenticationService} from '../../services/auth.service';
import {User} from '../../interfaces/utilisateur';
import { ROUTES } from '../../sidebar/sidebar.component';

@Component({
    moduleId: module.id,
    selector: 'navbar-cmp',
    templateUrl: 'navbar.component.html'
})

export class NavbarComponent implements OnInit{
   private listTitles: any[];
   private nativeElement: Node;
   currentUser: User;
   private toggleButton;
   public sidebarVisible: boolean;
    constructor(private router: Router,private loginService:AuthenticationService,private element : ElementRef) {
      this.nativeElement = element.nativeElement;
      this.sidebarVisible = false;
    }

    ngOnInit(){
        var getUser = sessionStorage.getItem("user");
        this.currentUser = JSON.parse(getUser);
        this.listTitles = ROUTES.filter(listTitle => listTitle);
        var navbar : HTMLElement = this.element.nativeElement;
        this.toggleButton = navbar.getElementsByClassName('navbar-toggle')[0];
        this.router.events.subscribe((event) => {
          this.sidebarClose();
       });
    }
    sidebarOpen() {
      const toggleButton = this.toggleButton;
      const html = document.getElementsByTagName('html')[0];
      const mainPanel =  <HTMLElement>document.getElementsByClassName('main-panel')[0];
      setTimeout(function(){
          toggleButton.classList.add('toggled');
      }, 500);

      html.classList.add('nav-open');
      if (window.innerWidth < 991) {
        mainPanel.style.position = 'fixed';
      }
      this.sidebarVisible = true;
  };
  sidebarClose() {
    const html = document.getElementsByTagName('html')[0];
    const mainPanel =  <HTMLElement>document.getElementsByClassName('main-panel')[0];
    if (window.innerWidth < 991) {
      setTimeout(function(){
        if(mainPanel != undefined) mainPanel.style.position = '';
      }, 500);
    }
    this.toggleButton.classList.remove('toggled');
    this.sidebarVisible = false;
    html.classList.remove('nav-open');
};
    sidebarToggle() {
      if (this.sidebarVisible === false) {
          this.sidebarOpen();
      } else {
          this.sidebarClose();
      }
    }
    logout(){
        this.loginService.logOut();
        this.router.navigate(['/']);
    }

}