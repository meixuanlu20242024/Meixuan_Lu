import {Component, OnInit, ViewChild} from '@angular/core';
import {MessageService} from "primeng/api";
import {UserService} from "../../module-users/api/user.service";
import {AuthService} from "../../shared/api/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {UserCreateContext} from "../../module-users/api/user.models";
import {ClientType, Currency, PassWordPolicy} from "../../shared/api/shared.models";
import {ClientService} from "../api/client.service";
import {Country} from "../../shared/api/countries.models";
import {ClientSanctionCheckRequest, ClientSanctionCheckResponse, RetailClient} from "../api/client.models";
import {ClientSanctionCheckComponent} from "./client-sanction-check/client-sanction-check.component";

@Component({
  selector: 'app-client-create',
  templateUrl: './client-create.component.html',
  styleUrls: ['./client-create.component.scss']
})
export class ClientCreateComponent implements OnInit{

    constructor(
        private messageService: MessageService,
        private clientService : ClientService,
        private authService: AuthService,
        private route: ActivatedRoute,

        private router : Router) {

    }
    @ViewChild('form', { static: false }) ngForm: NgForm;
    @ViewChild('sanctionDialog', { static: false }) sanctionDialog: ClientSanctionCheckComponent;
    title: string = 'Create Client'
    clientTypes: any[];
    countries: any[];
    sanctionCheckRequest: ClientSanctionCheckRequest;
    isAmlLoading: boolean = false;

    client: RetailClient = {

        id: 0,
        name: '',
        lastName: '',
        permanentAddress: '',
        mailingAddress: '',
        telephoneNumber: '',
        faxNumber: '',
        nationality: '',
        identityNumber: '',
        occupationLineOfBusiness: '',
        sourceOfFunds: '',
        contactPerson: '',
        mobileNumber: '',
        email: '',
        clientType: ClientType.INDIVIDUAL,
        renewal: '',
        incorporationDate: '',


    };

    passwordsMatch: boolean = true;
    amlStatus: string =  'Not Checked';
    statusSeverity: string = 'warning'
    isSanctioned: boolean  = true ;
    sanctionCheckCompleted: boolean = false;

    submitForm() {
        // add last name to name
        this.client.name =  this.client.name+' '+this.client.lastName;
        this.clientService.createClient(this.client).subscribe((data: any) => {
                if (data && data.message) {

                    this.client = {
                        id: 0,
                        name: '',
                        permanentAddress: '',
                        mailingAddress: '',
                        telephoneNumber: '',
                        faxNumber: '',
                        nationality: '',
                        identityNumber: '',
                        occupationLineOfBusiness: '',
                        sourceOfFunds: '',
                        contactPerson: '',
                        mobileNumber: '',
                        email: '',
                        clientType: ClientType.INDIVIDUAL,
                        renewal: '',
                        incorporationDate: '',

                    }
                    this.ngForm.resetForm();
                    this.messageService.add({key:'notification' ,severity:'success', summary: 'Success', detail: 'Client Created Successfully'});
                }
                else {
                    this.messageService.add({key:'notification',severity:'error', summary: 'Error', detail: 'Failed to create Client'});
                }

            }
        );

    }



    ngOnInit(): void {
        this.clientTypes = Object.values(ClientType) ;
        this.countries = Object.keys(Country) ;


    }

    checkSanctionedStatus() {
        this.sanctionCheckRequest = {
            firstName: this.client.name,
            lastName: this.client.lastName,
            referenceNumber: this.client.identityNumber,
            nationality: this.client.nationality
        }
        this.isAmlLoading = true;


        this.clientService.checkSanctionedStatus(this.sanctionCheckRequest).subscribe(


            {
                next: (data: any) => {

                    if(data && data.message) {
                       this.amlStatus = 'Client is not sanctioned';
                       this.statusSeverity = 'success';
                       this.messageService.add({key:'notification' ,severity:'success', summary: 'Success', detail: 'Client is not Sanctioned'});
                       this.isSanctioned = false;
                       this.sanctionCheckCompleted = true;
                    }
                    else{
                        this.amlStatus = 'Client is sanctioned';
                        this.statusSeverity = 'danger';
                        this.sanctionDialog.showSanctionsData(data);
                        this.messageService.add({key:'notification' ,severity:'error', summary: 'Error', detail: 'Client is Sanctioned'});
                        this.sanctionCheckCompleted = true;


                    }
                    this.isAmlLoading = false;
                },
                error: (err: any) => {
                    this.amlStatus = 'Not Completed';
                    this.statusSeverity = 'warning';
                    this.isAmlLoading = false;
                    this.sanctionCheckCompleted = true;
                    this.messageService.add({key:'notification' ,severity:'error', summary: 'Error', detail: 'Failed to check Sanctioned Status'});

                }
            }



        );

    }
}
