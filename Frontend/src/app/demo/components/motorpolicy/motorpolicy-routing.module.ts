import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CreateMotorPolicyComponent} from "./create-motor-policy/create-motor-policy.component";
import {ListPolicesComponent} from "./list-polices/list-polices.component";

const routes: Routes = [

    { path: 'create-policy', component: CreateMotorPolicyComponent }  ,
    { path: 'list-polices', component: ListPolicesComponent }  ,

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MotorpolicyRoutingModule { }
