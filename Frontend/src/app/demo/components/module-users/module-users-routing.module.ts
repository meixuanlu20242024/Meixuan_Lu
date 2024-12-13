import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UsersListComponent} from "./users-list/users-list.component";
import {CreateUserComponent} from "./create-user/create-user.component";
import {ChangePasswordComponent} from "./change-password/change-password.component";

const routes: Routes = [
    { path: 'list-users', component: UsersListComponent },
    { path: 'create-user', component: CreateUserComponent },
    {path :  'user-dialog' , component: UsersListComponent} ,
    {path :  'change-password/:id' , component: ChangePasswordComponent} ,
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ModuleUsersRoutingModule {

}
