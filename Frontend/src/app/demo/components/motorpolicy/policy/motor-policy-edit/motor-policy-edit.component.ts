import {Component, ViewChild} from '@angular/core';
import {SharedService} from "../../../shared/api/shared.service";
import {MotorPolicyService} from "../../api/motor-policy.service";
import {MessageService} from "primeng/api";
import {ActivatedRoute, Router} from "@angular/router";
import {MotorPolicy, MotorPolicyRequest} from "../../api/motor-policy.models";
import {NgForm, Validators} from "@angular/forms";
import {environment} from "../../../../../../environments/environment";
import {Currency, FileUploadDetails, FileUploadDTO} from "../../../shared/api/shared.models";
import {dateRangeValidator} from "../../../../utils/custom-validators";
import {Insurer, RetailClient} from "../../../client/api/client.models";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-motor-policy-edit',
  templateUrl: './motor-policy-edit.component.html',
  styleUrls: ['./motor-policy-edit.component.scss']
})
export class MotorPolicyEditComponent {


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
    title: string = ''
    selectedClaimNumber: string;
    isNoGenerated: boolean = false;
    generatedNo: string = null;
    currencies: string[];
    fleetIndividuals: any[];
    retailClients: RetailClient[];
    insurers: Insurer[] ;
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
        this.sharedService.getAllInsurers().subscribe((data: any[]) => {
                this.insurers = data;
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
                this.title = 'Editing Motor Policy  for ' + data.insured.name + ' : policy ' + data.policyNo;
                this.policy = {
                    id: data.id,
                    policyStatus: data.policyStatus,
                    insured: data.insured,
                    insurer: data.insurer,
                    currency: data.currency,
                    fleetIndividual: data.fleetIndividual,
                    periodFrom: data.periodFrom,
                    periodTo: data.periodTo,
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

        this.policy.periodFrom = this.datePipe.transform(this.policy.periodFrom, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'" )
        this.policy.periodTo = this.datePipe.transform(this.policy.periodTo, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        this.motorPolicyService.editPolicy(this.policy).subscribe((data: any) => {
                //     is successful, redirect to list page
                if (data && data.message) {

                    this.messageService.add({severity:'success', summary: 'Success', detail: 'Motor Policy Edited Successfully'});
                    this.router.navigate(['/motorpolicy/list-policies'])

                }
                else {
                    this.messageService.add({severity:'error', summary: 'Error', detail: 'Failed to Edit Motor Policy'});
                }

            }
        );

    }
    generatePolicyNumber() {
        this.sharedService.generateNumberSeries({ entityType: 'POLICY' }).subscribe((data: any) => {
          if (data && data.message) {
            this.policy.policyNo = data.message;  // Set the generated policy number to the property
            this.generatedNo = data.message;
            this.isNoGenerated = true;  // Disable the input and button after generating the number
          } else {
            console.error('Invalid response structure:', data);
          }
        });
    }






}
