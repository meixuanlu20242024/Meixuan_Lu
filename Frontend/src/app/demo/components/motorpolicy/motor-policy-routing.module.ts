import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MotorPolicyListComponent} from "./policy/motor-policy-list/motor-policy-list.component";
import {MotorPolicyCreateComponent} from "./policy/motor-policy-create/motor-policy-create.component";
import {MotorPolicyClaimListComponent} from "./claims/motor-policy-claim-list/motor-policy-claim-list.component";
import {MotorPolicyCreateClaimComponent} from "./claims/motor-policy-create-claim/motor-policy-create-claim.component";
import {MotorPolicyRenewalListComponent} from "./policy/motor-policy-renewal-list/motor-policy-renewal-list.component";
import {DrNoteComponent} from "./accounting/dr-note/dr-note.component";
import {MotorPolicyEditComponent} from "./policy/motor-policy-edit/motor-policy-edit.component";
import {MotorPolicyRenewComponent} from "./policy/motor-policy-renew/motor-policy-renew.component";
import {
    MotorPolicyAddDocumentsComponent
} from "./policy/motor-policy-add-documents/motor-policy-add-documents.component";
import {
    MotorPolicyRenewalDueListComponent
} from "./policy/motor-policy-renewal-due-list/motor-policy-renewal-due-list.component";

const routes: Routes = [
    { path: 'list-policies', component: MotorPolicyListComponent },
    { path: 'create-policy', component: MotorPolicyCreateComponent } ,
    { path: 'renewals/:id', component: MotorPolicyRenewalListComponent } ,
    { path: 'edit/:id', component: MotorPolicyEditComponent } ,
    { path: 'add-documents/:id', component: MotorPolicyAddDocumentsComponent } ,
    { path: 'renew/:id', component: MotorPolicyRenewComponent } ,
    { path: 'renewal-due-list', component: MotorPolicyRenewalDueListComponent } ,

    { path: 'list-claims', component: MotorPolicyClaimListComponent }  ,
    { path: 'create-claim/:id', component: MotorPolicyCreateClaimComponent }  ,
    { path: 'dr-note/:id', component: DrNoteComponent }  ,

];

@NgModule({
  imports: [RouterModule.forChild(routes)

  ],
  exports: [RouterModule]
})
export class MotorPolicyRoutingModule { }
