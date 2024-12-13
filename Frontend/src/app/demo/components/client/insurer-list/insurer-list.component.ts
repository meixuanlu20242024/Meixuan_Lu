import { Component } from '@angular/core';
import {ClientService} from "../api/client.service";
import {LazyLoadEvent, MenuItem, MessageService} from "primeng/api";
import {BordreauxQueryDTO, Insurer, RetailClient} from "../api/client.models";
import {Pageable} from "../../shared/api/shared.models";
import {DialogServiceCib} from "../../shared/api/dialog-service-cib.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-insurer-list',
  templateUrl: './insurer-list.component.html',
  styleUrls: ['./insurer-list.component.scss']
})
export class InsurerListComponent {


    constructor(
        private messageService: MessageService,
        private router: Router ,
    private dialogService: DialogServiceCib ,
        private clientService: ClientService) {
    }

    cols: any[] = [];
    loading: boolean = false;
    totalRecords!: number;
    menuItems: MenuItem[] | undefined;
    selectedInsurer: Insurer;
    insurers!: Insurer[];

    bordreauxQueryDTO: BordreauxQueryDTO;
    title: string = 'Insurers  List';




    ngOnInit(): void {

        this.menuItems = [


            {
                separator: true
            },

            {
                label: 'Run Bordeaux Report',
                icon: 'pi pi-fw pi-bookmark' ,
                command: (event) => this.runBordeaux(),
            },

            {
                label: 'View Insurer Invoices',
                icon: 'pi pi-fw pi-bookmark' ,
                command: (event) => this.viewInsurerInvoices(this.selectedInsurer),
            },





            {
                separator: true
            },




        ];

        this.cols = [
            {field: 'id', header: 'id'},
            {field: 'name', header: 'Name'},
            {field: 'telephoneNumber', header: 'Telephone Number'},
            {field: 'faxNumber', header: 'Fax Number'},
            {field: 'mobileNumber' , header: 'Mobile Number'   },
            {field: 'email' , header: 'Email'   },
            {field: 'incorporationDate' , header: 'Incorporation Date'   },
            {field: 'address', header: 'Address'},
            {field: 'registrationNumber', header: 'Registration Number'},
        ];
        this.insurers = [];
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
            this.clientService.getInsurers(pageableData).subscribe((data) => {
                if (data && data.content && Array.isArray(data.content)) {
                    this.insurers = data.content;
                    this.totalRecords = data.totalElements;
                } else {
                    // Handle the case where the response structure is unexpected
                    console.error('Invalid response structure:', data);
                }
                this.loading = false;
            });
        }, 1000);
    }


    runBordeaux() {

        this.bordreauxQueryDTO = {
            insurerId: this.selectedInsurer.id,
            periodFrom: '2021-01-01',
            periodTo: '2024-03-30',
            currency: 'USD' ,
            bordType: ''
        }

        console.log('Run Bordeaux Report for Insurer: ', this.selectedInsurer.name);
        this.dialogService.showDialog();

    }

    private viewInsurerInvoices(selectedInsurer: Insurer) {




            this.router.navigate(['/shared/invoices-list-by-insurer', selectedInsurer.id] , {
                state : {
                    title : "Listing invoices for "+selectedInsurer?.name+" Reg  "+selectedInsurer?.registrationNumber
                }}).then();
        }


}
