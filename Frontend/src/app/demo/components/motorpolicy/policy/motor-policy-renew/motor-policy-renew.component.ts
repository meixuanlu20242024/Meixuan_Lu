import {Component, ViewChild} from '@angular/core';
import {SharedService} from "../../../shared/api/shared.service";
import {MotorPolicyService} from "../../api/motor-policy.service";
import {MessageService} from "primeng/api";
import {ActivatedRoute, Router} from "@angular/router";
import {DatePipe} from "@angular/common";
import {MotorPolicy, MotorPolicyRequest} from "../../api/motor-policy.models";
import {NgForm} from "@angular/forms";
import {RetailClient} from "../../../client/api/client.models";
import {environment} from "../../../../../../environments/environment";
import {Currency} from "../../../shared/api/shared.models";

@Component({
  selector: 'app-motor-policy-renew',
  templateUrl: './motor-policy-renew.component.html',
  styleUrls: ['./motor-policy-renew.component.scss']
})
export class MotorPolicyRenewComponent {


    constructor(private sharedService : SharedService ,
                private motorPolicyService : MotorPolicyService ,
                private messageService: MessageService,
                private route: ActivatedRoute,
                private datePipe: DatePipe,
                private router : Router
    ) {
    }
    policy: MotorPolicyRequest = {
        id: 0,
        policyStatus: null,
        insured: null,
        insurer: null,
        currency: null,
        fleetIndividual: null,
        periodFrom: '',
        periodTo: '',
        motorPolicyDocuments: [],
        policyNo: '',
        coverType: null,
        sumInsured: null,
        premium: null,
        rate: null,
        stampDuty: null,
        governmentLevy: null,
        motorPolicyRenewals: [],
    };
    @ViewChild('form', { static: false }) ngForm: NgForm;
    policyStatuses: any[];
    title: string = '';
    selectedClaimNumber: string;
    isNoGenerated: boolean = false;
    generatedNo: string = null;
    currencies: string[];
    fleetIndividuals: any[];
    retailClients: RetailClient[];
    uploadedFiles: any[] = [];
    uploadUrl: String = environment.API_ENDPOINT + 'files/upload';
    filesUploaded: boolean = false;
    coverTypes: any[];


    ngOnInit(): void {

        this.route.paramMap.subscribe(params => {
            const id = params.get('id');
            if (id) {
                this.policy.id = parseInt(id);
            } else {
                this.messageService.add({ key:'notification' ,severity:'error', summary: 'Error', detail: 'No Policy Selected'});
            }

        });


        this.sharedService.getPolicyStatuses().subscribe((data: any[]) => {
                this.policyStatuses = data;
            }
        );
        this.sharedService.getAllClients().subscribe((data: RetailClient[]) => {
                this.retailClients = data;
            }
        );
        this.motorPolicyService.getFleetIndividual().subscribe((data: any[]) => {
                this.fleetIndividuals = data;

            }
        );
        this.currencies = Object.values(Currency) ;
        this.motorPolicyService.getCovertypes().subscribe((data: any[]) => {
                this.coverTypes = data;
            }
        );

        this.motorPolicyService.getPolicyById(this.policy.id).subscribe((data: MotorPolicy) => {
                // this.claim.insured = data
                this.title = 'Renewing  Motor Policy  for ' + data.insured.name + ' : policy ' + data.policyNo;
                this.policy = {
                    id: data.id,
                    policyStatus: data.policyStatus,
                    insured: data.insured,
                    insurer: data.insurer,
                    currency: data.currency,
                    fleetIndividual: data.fleetIndividual,
                    periodFrom: this.datePipe.transform(data.periodFrom, 'yyyy-MM-dd'),
                    periodTo: this.datePipe.transform(data.periodTo, 'yyyy-MM-dd'),
                    policyNo: data.policyNo,
                    coverType: data.coverType,
                    sumInsured: data.sumInsured,
                    premium: data.premium,
                    rate: data.rate,
                    stampDuty: data.stampDuty,
                    governmentLevy: data.governmentLevy,
                    motorPolicyRenewals: data.motorPolicyRenewals,
                    motorPolicyDocuments: [],

                }
            }
        );

    }
    submitForm() {
        this.motorPolicyService.renewPolicy(this.policy).subscribe((data: any) => {
                //     is successful, redirect to list page
                if (data && data.message) {

                    this.messageService.add({severity:'success', summary: 'Success', detail: 'Motor Policy Renewed Successfully'});

                }
                else {
                    this.messageService.add({severity:'error', summary: 'Error', detail: 'Failed to Renew Motor Policy'});
                }

            }
        );

    }





}
