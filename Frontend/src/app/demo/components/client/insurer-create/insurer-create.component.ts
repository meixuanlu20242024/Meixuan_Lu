import {Component, ViewChild} from '@angular/core';
import {MessageService} from "primeng/api";
import {ClientService} from "../api/client.service";
import {AuthService} from "../../shared/api/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {ClientSanctionCheckComponent} from "../client-create/client-sanction-check/client-sanction-check.component";
import {ClientSanctionCheckRequest, Insurer, RetailClient} from "../api/client.models";
import {ClientType} from "../../shared/api/shared.models";
import {Country} from "../../shared/api/countries.models";

@Component({
  selector: 'app-insurer-create',
  templateUrl: './insurer-create.component.html',
  styleUrls: ['./insurer-create.component.scss']
})
export class InsurerCreateComponent {

    constructor(
        private messageService: MessageService,
        private clientService : ClientService,
        private authService: AuthService,
        private route: ActivatedRoute,

        private router : Router) {

    }
    @ViewChild('form', { static: false }) ngForm: NgForm;
    @ViewChild('sanctionDialog', { static: false }) sanctionDialog: ClientSanctionCheckComponent;
    title: string = 'Create Insurer'
    clientTypes: any[];
    countries: any[];
    sanctionCheckRequest: ClientSanctionCheckRequest;
    isAmlLoading: boolean = false;

    insurer: Insurer = {

        id: 0,
        name: '',
        telephoneNumber: '',
        faxNumber: '',
        mobileNumber: '',
        email: '',
        incorporationDate: '',
        address: '',
        registrationNumber: '',


    };

    passwordsMatch: boolean = true;
    amlStatus: string =  'Not Checked';
    statusSeverity: string = 'warning'
    isSanctioned: boolean  = true ;
    sanctionCheckCompleted: boolean = false;

    submitForm() {

        this.clientService.createInsurer(this.insurer).subscribe((data: any) => {
                if (data && data.message) {

                    this.insurer = {
                        id: 0,
                        name: '',
                        telephoneNumber: '',
                        faxNumber: '',
                        mobileNumber: '',
                        email: '',
                        incorporationDate: '',
                        address: '',
                        registrationNumber: '',

                    }
                    this.ngForm.resetForm();
                        this.messageService.add({key:'notification' ,severity:'success', summary: 'Success', detail: 'Insurer Created Successfully'});
                }
                else {
                    this.messageService.add({key:'notification',severity:'error', summary: 'Error', detail: 'Insurer to create Client'});
                }

            }
        );

    }



    ngOnInit(): void {
        this.clientTypes = Object.values(ClientType) ;
        this.countries = Object.keys(Country) ;


    }


}
