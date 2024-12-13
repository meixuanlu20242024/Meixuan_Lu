import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MotorPolicyRoutingModule } from './motor-policy-routing.module';
import { DocumentsDialogComponent } from '../shared/components/documents-dialog/documents-dialog.component';
import {DialogModule} from "primeng/dialog";
import {ButtonModule} from "primeng/button";
import { MotorPolicyCreateComponent } from './policy/motor-policy-create/motor-policy-create.component';
import {DropdownModule} from "primeng/dropdown";
import {InputTextModule} from "primeng/inputtext";
import {InputTextareaModule} from "primeng/inputtextarea";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AutoCompleteModule} from "primeng/autocomplete";
import {CalendarModule} from "primeng/calendar";
import {ChipsModule} from "primeng/chips";
import {InputMaskModule} from "primeng/inputmask";
import {InputNumberModule} from "primeng/inputnumber";
import {CascadeSelectModule} from "primeng/cascadeselect";
import {MultiSelectModule} from "primeng/multiselect";
import {KeyFilterModule} from "primeng/keyfilter";
import {MessageModule} from "primeng/message";
import {FileUploadModule} from "primeng/fileupload";
import {TableModule} from "primeng/table";
import { MotorPolicyClaimListComponent } from './claims/motor-policy-claim-list/motor-policy-claim-list.component';
import {ContextMenuModule} from "primeng/contextmenu";
import {ToastModule} from "primeng/toast";
import { MotorPolicyCreateClaimComponent } from './claims/motor-policy-create-claim/motor-policy-create-claim.component';
import {CibInputDirective} from "../shared/directives/cib-input.directive";
import { MotorPolicyRenewalListComponent } from './policy/motor-policy-renewal-list/motor-policy-renewal-list.component';
import { MotorClaimsPerPolicyComponent } from './claims/motor-claims-per-policy/motor-claims-per-policy.component';
import {NgxDocViewerModule} from "ngx-doc-viewer";
import { DrNoteComponent } from './accounting/dr-note/dr-note.component';
import {SplitterModule} from "primeng/splitter";
import {ImageModule} from "primeng/image";
import { MotorPolicyEditComponent } from './policy/motor-policy-edit/motor-policy-edit.component';
import { MotorPolicyRenewComponent } from './policy/motor-policy-renew/motor-policy-renew.component';
import { MotorPolicyAddDocumentsComponent } from './policy/motor-policy-add-documents/motor-policy-add-documents.component';
import { MotorPolicyRenewalDueListComponent } from './policy/motor-policy-renewal-due-list/motor-policy-renewal-due-list.component';
import {MotorPolicyListComponent} from "./policy/motor-policy-list/motor-policy-list.component";




@NgModule({
    declarations: [
        MotorPolicyCreateComponent,
        MotorPolicyClaimListComponent,
        MotorPolicyCreateClaimComponent,
        MotorPolicyRenewalListComponent ,
        DocumentsDialogComponent,
        MotorClaimsPerPolicyComponent,
        DrNoteComponent,
        MotorPolicyEditComponent,
        MotorPolicyListComponent,
        MotorPolicyRenewComponent,
        MotorPolicyAddDocumentsComponent,
        MotorPolicyRenewalDueListComponent ,
    ],
    exports: [
        DocumentsDialogComponent,
        MotorPolicyClaimListComponent,
        MotorClaimsPerPolicyComponent,
        MotorPolicyAddDocumentsComponent
    ],
    imports: [
        CommonModule,
        MotorPolicyRoutingModule,
        DialogModule,
        ButtonModule,
        DropdownModule,
        InputTextModule,
        InputTextareaModule,
        AutoCompleteModule,
        CalendarModule,
        InputMaskModule,
        InputNumberModule,
        CascadeSelectModule,
        MultiSelectModule,
        KeyFilterModule,
        ReactiveFormsModule,
        FormsModule,
        MessageModule,
        FileUploadModule,
        TableModule,
        ContextMenuModule,
        ToastModule,
        NgxDocViewerModule,
        SplitterModule,
        ImageModule,
    ]
})
export class MotorPolicyModule { }
