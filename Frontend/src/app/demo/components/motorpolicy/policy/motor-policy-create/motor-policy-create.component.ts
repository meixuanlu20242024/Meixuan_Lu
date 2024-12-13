import {Component, OnInit} from '@angular/core';
import {MotorPolicy, MotorPolicyRequest} from "../../api/motor-policy.models";
import {SharedService} from "../../../shared/api/shared.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {dateRangeValidator} from "../../../../utils/custom-validators";
import {MotorPolicyService} from "../../api/motor-policy.service";
import {MessageService} from "primeng/api";
import {Currency} from "../../../shared/api/shared.models";
import {Insurer, RetailClient} from "../../../client/api/client.models";

@Component({
  selector: 'app-motor-policy-create',
  templateUrl: './motor-policy-create.component.html',
  styleUrls: ['./motor-policy-create.component.scss'] ,
})
export class MotorPolicyCreateComponent implements OnInit{
    selectedState: any = null;
    form: FormGroup;
    title: string = 'Create Motor Policy';
    motorPolicyRequest: MotorPolicyRequest;
    retailClients: RetailClient[];
    insurers: Insurer[];
    policyStatuses: any[];
    currencies: string[];
    coverTypes: any[];
    fleetIndividuals: any[];
    selectedFiles: File[] | null = null;
    selectPolicyFile: File[] | null = null;
    selectedRetailClient: RetailClient | null = null;
    isNoGenerated: boolean = false;
    generatedNo: string = null;

    vehicleTemplateUrl :string  = 'c29ad5f3-99d2-4cb5-a65c-9074a93ce675.xlsx'

    constructor(private sharedService : SharedService ,
                private motorPolicyService : MotorPolicyService ,
                private fb: FormBuilder ,
                private messageService: MessageService
                ) {

    }
    ngOnInit(): void {
        this.form = this.fb.group({
            id: [null],
            policyStatus: [null, Validators.required],
            insured: [null, Validators.required],
            insurer: [null, Validators.required],
            currency: [null, Validators.required],
            fleetIndividual: [null, Validators.required],
            periodFrom: [null, Validators.required],
            periodTo: [null, [Validators.required , dateRangeValidator()]],
            motorPolicyDocuments: [[]],
            policyExcel: [[]],
            policyNo: [null, Validators.required],
            coverType: [null, Validators.required],
            sumInsured: [null, Validators.required],
            premium: [null, Validators.required],
            rate: [null, [Validators.required, Validators.min(0), Validators.max(100)]] ,
            stampDuty: [null, Validators.required],
            governmentLevy: [null, Validators.required],
            motorPolicyRenewals: [[]]
        });


        this.sharedService.getAllClients().subscribe((data: RetailClient[]) => {
                this.retailClients = data;
            }
        );
        this.sharedService.getAllInsurers().subscribe((data: Insurer[]) => {
                this.insurers = data;
            }
        );

        this.sharedService.getPolicyStatuses().subscribe((data: any[]) => {
                this.policyStatuses = data;
            }
        );
        this.motorPolicyService.getCovertypes().subscribe((data: any[]) => {
                this.coverTypes = data;
            }
        );
        this.currencies = Object.values(Currency) ;

        this.form.valueChanges.subscribe((formData: any) => {
            this.motorPolicyRequest = formData;
        });
        this.motorPolicyService.getFleetIndividual().subscribe((data: any[]) => {
                this.fleetIndividuals = data;

        }
        );
    }


    onSubmit() {
        this.motorPolicyRequest = this.form.value;

        const formData = new FormData();

        if(this.selectedFiles.length > 0){
            console.log("in selected file");

            for (let i = 0; i < this.selectedFiles.length; i++) {
                formData.append('motorPolicyDocuments[]', this.selectedFiles[i]);
            }
            //formData.append('policyExcel[]', this.selectPolicyFile[0]);
            this.selectedRetailClient = this.form.value.insured;
            console.log(this.form.value.insured.id+" selectedRetailClient");
            formData.append('policyStatus' , this.form.value.policyStatus);
            formData.append('insured' , this.form.value.insured.id);
            formData.append('insurer' , this.form.value.insurer.id);
            formData.append('currency' , this.form.value.currency);
            formData.append('fleetIndividual' , this.form.value.fleetIndividual);
            formData.append('periodFrom' , this.form.value.periodFrom);
            formData.append('periodTo' , this.form.value.periodTo);
            formData.append('policyNo' , this.generatedNo == null ? this.form.value.policyNo : this.generatedNo);
            formData.append('coverType' , this.form.value.coverType);
            formData.append('sumInsured' , this.form.value.sumInsured);
            formData.append('premium' , this.form.value.premium);
            formData.append('rate' , this.form.value.rate);
            formData.append('stampDuty' , this.form.value.stampDuty);
            formData.append('governmentLevy' , this.form.value.governmentLevy);
        }

        // check if motorPolicyDocuments is empty


        this.motorPolicyService.createMotorPolicy(formData).subscribe((data: any) => {
        //     is successful, redirect to list page
            if (data) {
                this.messageService.add({severity:'success', summary: 'Success', detail: 'Motor Policy Created Successfully'});
                this.form.reset();
            }
            else {
                this.messageService.add({severity:'error', summary: 'Error', detail: 'Motor Policy Creation Failed'});
            }

        }
        );

    }

    onFileSelected(event: any) {
        this.selectedFiles = event.target.files ;

    }

    onPolicyFileSelected(event: any) {
        this.selectPolicyFile  = event.target.files ;


    }
    generatePolicyNumber() {
        this.sharedService.generateNumberSeries({entityType: 'POLICY'}).subscribe((data: any) => {
                if (data && data.message) {

                    this.form.patchValue({
                        policyNo: data.message
                    });
                    this.generatedNo = data.message;
                    this.form.get('policyNo')?.disable()
                    this.isNoGenerated = true;

                } else {
                    // Handle the case where the response structure is unexpected
                    console.error('Invalid response structure:', data);
                }


            }
        );
    }

    downloadTemplate() {
        this.sharedService.downloadFile(this.vehicleTemplateUrl).subscribe((data: Blob) => {
            const blob = new Blob([data], { type: 'application/octet-stream' });
            const url = window.URL.createObjectURL(blob);

            // Create a link element and trigger the download
            const a = document.createElement('a');
            a.href = url;
            a.download = this.vehicleTemplateUrl;
            a.style.display = 'none';
            document.body.appendChild(a);
            a.click();

            // Clean up
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
        });
    }
}
