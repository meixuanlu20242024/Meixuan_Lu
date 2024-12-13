import {Component, ViewChild} from '@angular/core';
import {MessageService} from "primeng/api";
import {ClientService} from "../api/client.service";
import {AuthService} from "../../shared/api/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {ClientSanctionCheckComponent} from "../client-create/client-sanction-check/client-sanction-check.component";
import {ClientSanctionCheckRequest, RetailClient} from "../api/client.models";
import {ClientType} from "../../shared/api/shared.models";
import {Country} from "../../shared/api/countries.models";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-client-edit',
  templateUrl: './client-edit.component.html',
  styleUrls: ['./client-edit.component.scss']
})
export class ClientEditComponent {

    constructor(
        private messageService: MessageService,
        private clientService : ClientService,
        private authService: AuthService,
        private route: ActivatedRoute,
        private datePipe: DatePipe,

        private router : Router) {

    }
    @ViewChild('form', { static: false }) ngForm: NgForm;
    @ViewChild('sanctionDialog', { static: false }) sanctionDialog: ClientSanctionCheckComponent;
    title: string = 'Edit Client'
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
        this.client.name =  this.client.name ;
        this.client.incorporationDate = this.datePipe.transform(this.client.occupationLineOfBusiness, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" );
        this.clientService.editClient(this.client).subscribe((data: any) => {
                if (data && data.message) {

                    this.messageService.add({key:'notification' ,severity:'success', summary: 'Success', detail: 'Client info modified '});
                }
                else {
                    this.messageService.add({key:'notification',severity:'error', summary: 'Error', detail: 'Failed to modify Client'});
                }

            }
        );

    }



    ngOnInit(): void {

        this.route.paramMap.subscribe(params => {
            const id = params.get('id');
            if (id) {
                this.client.id = parseInt(id);
            } else {
                this.messageService.add({ key:'notification' ,severity:'error', summary: 'Error', detail: 'No Client Selected'});
            }

        });
        this.clientTypes = Object.values(ClientType) ;
        this.countries = Object.keys(Country) ;


        this.clientService.getClientById(this.client.id).subscribe((data: RetailClient) => {
                // this.claim.insured = data
                this.title = 'Editing Retail Client  for ' + data.name + ' ' + data.lastName;
                this.client = {


                    id: data.id,
                    name: data.name,
                    lastName: data.lastName,
                    permanentAddress: data.permanentAddress,
                    mailingAddress: data.mailingAddress,
                    telephoneNumber: data.telephoneNumber,
                    faxNumber: data.faxNumber,
                    nationality: data.nationality ,
                    identityNumber: data.identityNumber,
                    occupationLineOfBusiness: data.occupationLineOfBusiness,
                    sourceOfFunds: data.sourceOfFunds,
                    contactPerson: data.contactPerson,
                    mobileNumber: data.mobileNumber,
                    email: data.email,
                    clientType: data.clientType,
                    renewal: data.renewal,
                    incorporationDate: data.incorporationDate,


                }
            }
        );




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
