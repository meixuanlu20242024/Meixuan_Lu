import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ModuleUsersRoutingModule } from './module-users-routing.module';
import { UsersListComponent } from './users-list/users-list.component';
import {ButtonModule} from "primeng/button";
import {ContextMenuModule} from "primeng/contextmenu";

import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";
import {TooltipModule} from "primeng/tooltip";
import { CreateUserComponent } from './create-user/create-user.component';
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {FileUploadModule} from "primeng/fileupload";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputNumberModule} from "primeng/inputnumber";
import {InputTextModule} from "primeng/inputtext";
import {MessagesModule} from "primeng/messages";
import {PasswordModule} from "primeng/password";
import {MessageModule} from "primeng/message";
import { DisableUserComponent } from './disable-user/disable-user.component';
import { UserDialogComponent } from './user-dialog/user-dialog.component';
import {DialogModule} from "primeng/dialog";
import { ChangePasswordComponent } from './change-password/change-password.component';


@NgModule({
  declarations: [
    UsersListComponent,
    CreateUserComponent,
    DisableUserComponent,
    UserDialogComponent,
    ChangePasswordComponent
  ],
    imports: [
        CommonModule,
        ModuleUsersRoutingModule,
        ButtonModule,
        ContextMenuModule,
        TableModule,
        ToastModule,
        TooltipModule,
        CalendarModule,
        DropdownModule,
        FileUploadModule,
        FormsModule,
        InputNumberModule,
        InputTextModule,
        MessagesModule,
        ReactiveFormsModule,
        PasswordModule,
        MessageModule,
        DialogModule
    ],
  exports: [
    UsersListComponent
  ]
})
export class ModuleUsersModule { }
