import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    constructor(public layoutService: LayoutService) { }

    ngOnInit() {
        this.model = [
            {
                label: 'Home',
                items: [
                    { label: 'Retail Clients', icon: 'pi pi-fw pi-users', routerLink: ['/clients/list'] },
                    { label: 'Insurers', icon: 'pi pi-fw pi-users', routerLink: ['/clients/list-insurers'] }
                ]
            },
            {


                label: 'Policies',
                icon: 'pi pi-fw pi-briefcase',
                items: [

                    {
                        label: 'Motor Policy',
                        icon: 'pi pi-fw pi-car',
                        items: [
                            {
                                label: 'Policies',
                                icon: 'pi pi-fw pi-id-card',
                                routerLink: ['/motorpolicy/list-policies']
                            },
                            {
                                label: 'All Claims',
                                icon: 'pi pi-fw pi-times-circle',
                                routerLink: ['/motorpolicy/list-claims']
                            },



                        ]
                    },








                ]
            },




            {
                label: 'Admin',
                icon: 'pi pi-fw pi-cog',
                items: [
                    {
                        label: 'Users',
                        //routerLink: ['/users/list-users']
                    },



                ]
            },







        ];
    }
}
