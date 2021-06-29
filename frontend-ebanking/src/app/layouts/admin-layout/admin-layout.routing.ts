import { Routes } from '@angular/router';

import { DashboardComponent } from '../../pages/dashboard/dashboard.component';
import { UserComponent } from '../../pages/user/user.component';
import { TableComponent } from '../../pages/table/table.component';
import { NotificationsComponent }   from '../../pages/notifications/notifications.component';
import { TypographyComponent } from '../../pages/typography/typography.component';
import { RendezvousComponent } from 'app/pages/rendezvous/rendezvous.component';
import { MesRDVComponent } from '../../pages/mes-rdv/mes-rdv.component';
export const AdminLayoutRoutes: Routes = [
    { path: 'dashboard',      component: DashboardComponent },
    { path: 'user',           component: UserComponent },
    { path: 'table',          component: TableComponent },
    { path: 'typography',     component: TypographyComponent },
    {path: 'rendezvous',     component: RendezvousComponent},
    { path: 'notifications',  component: NotificationsComponent },
    {path: 'MesRDV', component:MesRDVComponent}
];
