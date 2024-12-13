import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ClientListComponent} from "./client-list/client-list.component";
import {ClientCreateComponent} from "./client-create/client-create.component";
import {InsurerCreateComponent} from "./insurer-create/insurer-create.component";
import {InsurerListComponent} from "./insurer-list/insurer-list.component";
import {ClientEditComponent} from "./client-edit/client-edit.component";
import {DialogComponent} from "./dialog/dialog.component";

const routes: Routes = [
    { path: 'list', component: ClientListComponent },
    { path: 'list-insurers', component: InsurerListComponent },
    { path: 'create-client', component: ClientCreateComponent },
    { path: 'create-insurer', component: InsurerCreateComponent } ,
    {path :  'edit-client/:id', component: ClientEditComponent},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
