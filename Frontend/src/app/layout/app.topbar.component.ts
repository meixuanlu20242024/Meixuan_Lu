import { Component, ElementRef, ViewChild } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { LayoutService } from "./service/app.layout.service";
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";

@Component({
    selector: 'app-topbar',
    templateUrl: './app.topbar.component.html'
})
export class AppTopBarComponent {

    items!: MenuItem[];

    @ViewChild('menubutton') menuButton!: ElementRef;

    @ViewChild('topbarmenubutton') topbarMenuButton!: ElementRef;

    @ViewChild('topbarmenu') menu!: ElementRef;

    COMPANY_TOP_BAR_NAME = environment.COMPANY_TOP_BAR_NAME;
    constructor(public layoutService: LayoutService , private router : Router) { }

    ngOnInit() {
        this.items = [
            {

                items: [
                    {
                        label: 'Logout',
                        icon: 'pi pi-arrow-right',
                        command: () => {
                            this.logout();
                        }
                    },

                ]
            },

        ];
    }

    logout() {
        localStorage.removeItem('token');
        this.router.navigate(['/auth']);

    }


}
