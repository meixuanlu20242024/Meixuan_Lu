import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedRoutingModule } from './shared-routing.module';
import {ButtonModule} from "primeng/button";
import {CalendarModule} from "primeng/calendar";
import {DropdownModule} from "primeng/dropdown";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {InputNumberModule} from "primeng/inputnumber";
import {InputTextModule} from "primeng/inputtext";
import {MessagesModule} from "primeng/messages";
import {ProgressSpinnerModule} from "primeng/progressspinner";
import {ProgressBarModule} from "primeng/progressbar";
import {TableModule} from "primeng/table";
import {SkeletonModule} from "primeng/skeleton";
import { InvoiceCreateComponent } from './components/invoice-create/invoice-create.component';
import {DialogModule} from "primeng/dialog";
import {ContextMenuModule} from "primeng/contextmenu";
import {ToastModule} from "primeng/toast";
import {CamelToSeparateWordsPipePipe} from "./directives/camel-to-separate-words-pipe.pipe";


@NgModule({
    declarations: [

        InvoiceCreateComponent,

        CamelToSeparateWordsPipePipe,

    ],
    exports: [
        InvoiceCreateComponent,
    ],
    imports: [
        CommonModule,
        SharedRoutingModule,
        ButtonModule,
        CalendarModule,
        DropdownModule,
        FormsModule,
        InputNumberModule,
        InputTextModule,
        MessagesModule,
        ReactiveFormsModule,
        ProgressSpinnerModule,
        ProgressBarModule,
        TableModule,
        SkeletonModule,
        DialogModule,
        ContextMenuModule,
        ToastModule
    ]
})
export class SharedModule { }
