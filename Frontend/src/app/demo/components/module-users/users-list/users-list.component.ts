import {ChangeDetectionStrategy, Component, Input} from '@angular/core';
import {SharedService} from "../../shared/api/shared.service";
import {LazyLoadEvent, MenuItem, MessageService} from "primeng/api";
import {Pageable} from "../../shared/api/shared.models";
import {User} from "../api/user.models";
import {UserService} from "../api/user.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss'],
    changeDetection: ChangeDetectionStrategy.Default

})
export class UsersListComponent {

    constructor(
        private userService: UserService,private messageService: MessageService,
        private sharedService: SharedService ,
        private router: Router

    ) {
    }

    cols: any[] = [];
    loading: boolean = false;
    totalRecords!: number;
    menuItems: MenuItem[] | undefined;
    selectedUser: User;
    users!: User[];
    title: string = 'Users List';

    ngOnInit(): void {

        this.menuItems = [


            {
                separator: true
            },

            {
                label: 'Disable User',
                icon: 'pi pi-fw pi-ban' ,
                command: (event) => this.disableUser(this.selectedUser),
            },
            {
                label: 'Activate User',
                icon: 'pi pi-fw pi-check' ,
                command: (event) => this.activateUser(this.selectedUser),
            },

            {
                label: 'Change Password',
                icon: 'pi pi-fw pi-check' ,
                command: (event) => this.changePassword(this.selectedUser),

                //routerLink: '/users/change-password/'+this.selectedUser.id
            },



            {
                separator: true
            },




        ];


        this.cols = [
            {field: 'id', header: 'id'},
            {field: 'Enabled', header: 'Enabled'},
            {field: 'firstName', header: 'First Name'},
            {field: 'lastName', header: 'Last Name'},
            {field: 'email', header: 'Email'},
            {field: 'mobile', header: 'Mobile'},
            {field: 'nationalId', header: 'National Id'},
            {field: 'passwordChangedTime', header: 'Password Changed Time'},

        ];
        this.users = [];
        this.totalRecords = 0;


    }

    loadData(event: LazyLoadEvent) {
        this.loading = true;
        const pageableData: Pageable = {
            page: event.first = event.first / event.rows,
            size: event.rows
        };

        console.log('event', event);
        // stringify the event
        console.log(JSON.stringify(event));

        setTimeout(() => {
            this.userService.getUsers(pageableData).subscribe((data) => {
                if (data && data.content && Array.isArray(data.content)) {
                    this.users = data.content;
                    this.totalRecords = data.totalElements;
                } else {
                    // Handle the case where the response structure is unexpected
                    console.error('Invalid response structure:', data);
                }
                this.loading = false;
            });
        }, 1000);

    }

    private disableUser(selectedUser: User) {

        this.userService.disableUser(selectedUser.id).subscribe((data: any) => {
            if (data && data.message) {
                this.messageService.add({severity:'success', summary: 'Success', detail: 'User Disabled Successfully'});
                this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {

                    this.router.navigate(['/users/list-users']);
                });
            }
        });

    }

    private activateUser(selectedUser: User) {


        this.userService.activateUser(selectedUser.id).subscribe((data: any) => {
            if (data && data.message) {
                this.messageService.add({severity:'success', summary: 'Success', detail: 'User activated'});

                this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {

                    this.router.navigate(['/users/list-users']);
                });
            }
        });

    }

    private changePassword(selectedUser: User) {
        this.router.navigate(['/users/change-password', selectedUser.id]).then();
    }
}
