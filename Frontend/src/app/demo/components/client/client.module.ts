import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ClientRoutingModule } from './client-routing.module';
import { ClientListComponent } from './client-list/client-list.component';
import {ButtonModule} from "primeng/button";
import {ContextMenuModule} from "primeng/contextmenu";
import {SharedModule} from "primeng/api";
import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";
import { ClientCreateComponent } from './client-create/client-create.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputTextModule} from "primeng/inputtext";
import {MessageModule} from "primeng/message";
import {MessagesModule} from "primeng/messages";
import {PasswordModule} from "primeng/password";
import {DropdownModule} from "primeng/dropdown";
import {InputTextareaModule} from "primeng/inputtextarea";
import {CalendarModule} from "primeng/calendar";
import { ClientSanctionCheckComponent } from './client-create/client-sanction-check/client-sanction-check.component';
import {DialogModule} from "primeng/dialog";
import {PanelModule} from "primeng/panel";
import {TagModule} from "primeng/tag";
import { InsurerCreateComponent } from './insurer-create/insurer-create.component';
import { InsurerListComponent } from './insurer-list/insurer-list.component';
import { ClientEditComponent } from './client-edit/client-edit.component';
import {DialogComponent} from "./dialog/dialog.component";
import {DialogServiceCib} from "../shared/api/dialog-service-cib.service";
import { SmsDialogComponent } from './sms-dialog/sms-dialog.component';


@NgModule({
  declarations: [
    ClientListComponent,
    ClientCreateComponent,
    ClientSanctionCheckComponent,
    InsurerCreateComponent,
    InsurerListComponent,
    ClientEditComponent,
      DialogComponent,
      SmsDialogComponent
  ],
    imports: [
        CommonModule,
        ClientRoutingModule,
        ButtonModule,
        ContextMenuModule,
        SharedModule,
        TableModule,
        ToastModule,
        FormsModule,
        InputTextModule,
        MessageModule,
        MessagesModule,
        PasswordModule,
        ReactiveFormsModule,
        DropdownModule,
        InputTextareaModule,
        CalendarModule,
        DialogModule,
        PanelModule,
        TagModule ,
    ],
    exports: [
      ClientSanctionCheckComponent,
        DialogComponent
    ],
    providers : [DialogServiceCib]
})
export class ClientModule { }
