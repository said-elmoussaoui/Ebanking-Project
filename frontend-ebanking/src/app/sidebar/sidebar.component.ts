import { Component, OnInit } from '@angular/core';
import { User } from 'app/interfaces/utilisateur';


export interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard',     title: 'Dashboard',         icon:'nc-bank',       class: '' },
    { path: '/user',          title: 'Mon Profile',      icon:'nc-single-02',  class: '' },
    { path: '/table',         title: 'Les agents',        icon:'nc-tile-56',    class: '' },
    { path: '/typography',    title: 'Ajouter client',        icon:'fas fa-user-plus', class: '' },
    { path: '/MesRDV', title: 'Mes Rendez-Vous', icon:'nc-bell-55', class: '' },
    { path: '/rendezvous', title: 'Prendre un RDV', icon:'nc-caps-small', class: '' },
    { path: '/notifications', title: 'Mes Rendez-Vous',     icon:'nc-bell-55',    class: '' }

];
const ROUTES1: RouteInfo[] = [
    { path: '/dashboard',     title: 'Dashboard',         icon:'nc-bank',       class: '' },
    { path: '/user',          title: 'Mon Profile',      icon:'nc-single-02',  class: '' },
    { path: '/table',         title: 'Les agents',        icon:'nc-tile-56',    class: '' }
    

];
const ROUTES2: RouteInfo[] = [
    { path: '/dashboard',     title: 'Dashboard',         icon:'nc-bank',       class: '' },
    { path: '/user',          title: 'Mon Profile',      icon:'nc-single-02',  class: '' },
    { path: '/typography',    title: 'Ajouter client',        icon:'fas fa-user-plus', class: '' },
    { path: '/MesRDV', title: 'Mes Rendez-Vous', icon:'nc-bell-55', class: '' }

];
const ROUTES3: RouteInfo[] = [
    { path: '/dashboard',     title: 'Dashboard',         icon:'nc-bank',       class: '' },
    { path: '/user',          title: 'Mon Profile',      icon:'nc-single-02',  class: '' },
    { path: '/rendezvous', title: 'Prendre un RDV', icon:'nc-caps-small', class: '' },
    { path: '/notifications', title: 'Mes Rendez-Vous',     icon:'nc-bell-55',    class: '' }

];
@Component({
    moduleId: module.id,
    selector: 'sidebar-cmp',
    templateUrl: 'sidebar.component.html',
})

export class SidebarComponent implements OnInit {
    public menuItems: any[];
    currentUser:User;
    ngOnInit() {
        var getUser = sessionStorage.getItem("user");
        this.currentUser = JSON.parse(getUser);
        if(this.currentUser.role == "Admin"){
           
            //ROUTES1.splice(3);
            this.menuItems = ROUTES1.filter(menuItem => menuItem);

        }
        if(this.currentUser.role == "Agent"){
            
            //ROUTES1.splice(2,1);
            //ROUTES1.splice(4,2);
            this.menuItems = ROUTES2.filter(menuItem => menuItem);
          
        }
        if(this.currentUser.role == "Client"){
           
            //ROUTES1.splice(2,3);
            this.menuItems = ROUTES3.filter(menuItem => menuItem);
        }
        
    }
}
