import { NgModule } from '@angular/core';
import {
    CommonModule,
    CurrencyPipe, DatePipe,
    HashLocationStrategy,
    LocationStrategy,
    NgClass,
    NgForOf,
    NgIf,
    NgStyle
} from '@angular/common';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AppLayoutModule } from './layout/app.layout.module';
import { NotfoundComponent } from './demo/components/shared/components/notfound/notfound.component';
import {DropdownModule} from "primeng/dropdown";
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {ChartModule} from "primeng/chart";
import {MenuModule} from "primeng/menu";
import {ButtonModule} from "primeng/button";
import {TableModule} from "primeng/table";
import {ToastModule} from "primeng/toast";
import {ContextMenuModule} from "primeng/contextmenu";
import {DialogModule} from "primeng/dialog";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {AuthInterceptor} from "./demo/components/shared/interceptors/auth.interceptor";
import {MessageService} from "primeng/api";
import {NgxDocViewerModule} from "ngx-doc-viewer";
import {MessageModule} from "primeng/message";
import {ModuleUsersModule} from "./demo/components/module-users/module-users.module";
import { ForbiddenComponent } from './demo/components/shared/forbidden/forbidden.component';
import {NgxCaptureModule} from "ngx-capture";
import {InputTextModule} from "primeng/inputtext";
import { SharedModule } from './demo/components/shared/shared.module';
import {CardModule} from "primeng/card";
import {DividerModule} from "primeng/divider";

import {DialogServiceCib} from "./demo/components/shared/api/dialog-service-cib.service";
import {ClientModule} from "./demo/components/client/client.module";
import {MotorPolicyModule} from "./demo/components/motorpolicy/motor-policy.module";

@NgModule({
    declarations: [
        AppComponent, NotfoundComponent,  ForbiddenComponent
    ],
    imports: [

        AppRoutingModule,
        AppLayoutModule,
        NgxDocViewerModule,
        CommonModule, ReactiveFormsModule, DropdownModule, FormsModule, ChartModule, MenuModule, NgStyle, NgIf, ButtonModule, CurrencyPipe, TableModule, NgClass, NgForOf, ToastModule, ContextMenuModule,
        MotorPolicyModule,ModuleUsersModule ,
        DialogModule, MessageModule, NgxCaptureModule, InputTextModule, SharedModule, CardModule, DividerModule, ClientModule
    ],
    providers: [
        MessageService,
        DatePipe,
        DialogServiceCib ,
        { provide: LocationStrategy, useClass: HashLocationStrategy },
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
    ],


    bootstrap: [AppComponent]
})
export class AppModule { }
