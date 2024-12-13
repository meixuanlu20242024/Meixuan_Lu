import {Component, OnInit} from '@angular/core';
import {UserService} from "../../module-users/api/user.service";
import {SharedService} from "../../shared/api/shared.service";
import {LazyLoadEvent, MenuItem} from "primeng/api";
import {User} from "../../module-users/api/user.models";
import {ClientType, Pageable} from "../../shared/api/shared.models";
import {ClientService} from "../api/client.service";
import {RetailClient} from "../api/client.models";
import {Router} from "@angular/router";
import {DialogServiceCib} from "../../shared/api/dialog-service-cib.service";

@Component({
  selector: 'app-client-list',
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.scss']
})
export class ClientListComponent implements OnInit {

    constructor(private clientService: ClientService ,  private sharedService : SharedService ,  private router: Router , private dialogService: DialogServiceCib ) {
    }

    cols: any[] = [];
    loading: boolean = false;
    totalRecords!: number;

    t
    menuItems: MenuItem[] | undefined;
    selectedClient: RetailClient;
    clients!: RetailClient[];
    title: string = 'Clients List';




    ngOnInit(): void {


        this.menuItems = [

            {
                label: 'Edit Client',
                icon: 'pi pi-fw pi-pencil',
                command: (event) => this.onEditClient(this.selectedClient),


            },
            {
                label: 'Send Text Message',
                icon: 'pi pi-fw pi-pencil',
                command: (event) => this.sendTextMessage(this.selectedClient),


            },


            {
                separator: true
            },




        ];


        this.cols = [
            {field: 'id', header: 'id'},
            {field: 'name', header: 'Name'},
            {field: 'permanentAddress', header: 'Permanent Address'},
            {field: 'mailingAddress', header: 'Mailing Address'},
            {field: 'telephoneNumber', header: 'Telephone Number'},
            {field: 'faxNumber', header: 'Fax Number'},
            {field: 'nationality' , header: 'Nationality'   },
            {field: 'identityNumber' , header: 'Identity Number'   },
            {field: 'occupationLineOfBusiness' , header: 'Occupation Line Of Business'   },
            {field: 'sourceOfFunds' , header: 'Source Of Funds'   },
            {field: 'contactPerson' , header: 'Contact Person'   },
            {field: 'mobileNumber' , header: 'Mobile Number'   },
            {field: 'email' , header: 'Email'   },
            {field: 'clientType' , header: 'Client Type'   },
            {field: 'incorporationDate' , header: 'Incorporation Date'   },


        ];
        this.clients = [];
        this.totalRecords = 0;


    }

    loadData(event: LazyLoadEvent) {
        console.log(event);
        this.loading = true;
        const pageableData: Pageable = {
            page: event.first = event.first / event.rows,
            size: event.rows ,
            globalFilter: event.globalFilter,
        };

        setTimeout(() => {
            this.clientService.getClients(pageableData).subscribe((data) => {
                if (data && data.content && Array.isArray(data.content)) {
                    this.clients = data.content;
                    this.totalRecords = data.totalElements;
                } else {
                    console.error('Invalid response structure:', data);
                }
                this.loading = false;
            });
        }, 1000);
    }

    private onEditClient(selectedClient: RetailClient) {
        this.router.navigate(['/clients/edit-client', selectedClient.id]).then();
    }

    exportExcel() {
        this.sharedService.exportExcel(this.clients);
    }

    exportPdf() {
         //this.sharedService.exportPdf();
    }

    private sendTextMessage(selectedClient: RetailClient) {
        this.dialogService.showDialog();this.dialogService.showDialog();
    }
}
