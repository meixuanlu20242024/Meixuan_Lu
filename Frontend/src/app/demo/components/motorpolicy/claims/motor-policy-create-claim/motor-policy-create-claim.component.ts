import {Component, OnInit, ViewChild} from '@angular/core';
import {SharedService} from "../../../shared/api/shared.service";
import {MotorPolicyService} from "../../api/motor-policy.service";
import {FormBuilder, FormGroup, NgForm} from "@angular/forms";
import {MessageService} from "primeng/api";
import {MotorPolicy, MotorPolicyClaim} from "../../api/motor-policy.models";
import {environment} from "../../../../../../environments/environment";
import {ActivatedRoute, Router} from "@angular/router";
import {FileUploadDetails, FileUploadDTO} from "../../../shared/api/shared.models";

@Component({
  selector: 'app-motor-policy-create-claim',
  templateUrl: './motor-policy-create-claim.component.html',
  styleUrls: ['./motor-policy-create-claim.component.scss'],
})
export class MotorPolicyCreateClaimComponent implements  OnInit{

    constructor(private sharedService : SharedService ,
                private motorPolicyService : MotorPolicyService ,
                private messageService: MessageService,
                private route: ActivatedRoute,
                private router : Router
                ) {
    }
    claim: MotorPolicyClaim = {
        motorPolicyClaimDocuments: [],
        dateOfLoss: '',
        notificationDate: '',
        insured: null,
        insuredId: 0,
        timeOfLoss: '',
        lossAmount: null,
        claimNumber: '',
        lossDetails: '',
        createdAt: '',
        createdBy: '',
        claimStatus: null,
        id: 0
    };
    @ViewChild('form', { static: false }) ngForm: NgForm;
    claimStatuses: any[];
    title: string = ''
    selectedClaimNumber: string;
    isNoGenerated: boolean = false;
    generatedNo: string = null;
    uploadedFiles: any[] = [];
    uploadUrl: String = environment.API_ENDPOINT + 'files/upload';
    filesUploaded: boolean = false;


    ngOnInit(): void {

        this.route.paramMap.subscribe(params => {
            const id = params.get('id');
            if (id) {
                this.claim.insuredId = parseInt(id);
            } else {
                this.messageService.add({ key:'notification' ,severity:'error', summary: 'Error', detail: 'No Insured Selected'});
            }

        });

        this.motorPolicyService.getClaimStatus().subscribe((data: any[]) => {
                this.claimStatuses = data;
            }
        );

        this.motorPolicyService.getPolicyById(this.claim.insuredId).subscribe((data: MotorPolicy) => {
                // this.claim.insured = data

            this.title = 'Create Claim for ' + data.insured.name + ' : policy ' + data.policyNo;
        }
        );

    }
    submitForm() {
        this.motorPolicyService.createMotorPolicyClaim(this.claim).subscribe((data: any) => {
                //     is successful, redirect to list page
                if (data && data.message) {



                    this.uploadedFiles = [];
                    this.filesUploaded = false;
                    this.claim = {
                        motorPolicyClaimDocuments: [],
                        dateOfLoss: '',
                        insuredId: 0,
                        insured: null,
                        notificationDate: '',
                        timeOfLoss: '',
                        lossAmount: null,
                        claimNumber: '',
                        lossDetails: '',
                        createdAt: '',
                        createdBy: '',
                        claimStatus: null,
                        id: 0

                    }
                    this.ngForm.resetForm();
                    this.messageService.add({severity:'success', summary: 'Success', detail: 'Motor Policy Claim Created Successfully'});

                }
                else {
                    this.messageService.add({severity:'error', summary: 'Error', detail: 'Motor Policy Claim Creation Failed'});
                }

            }
        );

    }

    generateClaimNumber() {
        this.sharedService.generateNumberSeries({entityType: 'CLAIM'}).subscribe((data: any) => {
                if (data && data.message) {
                    this.selectedClaimNumber = data.message;
                    console.log(data.message);


                    this.claim.claimNumber = data.message;
                    this.generatedNo = data.message;
                    this.isNoGenerated = true;

                } else {
                    // Handle the case where the response structure is unexpected
                    console.error('Invalid response structure:', data);
                }


            }
        );
    }



    onUpload(event:any) {
        for(let file of event.files) {
            this.uploadedFiles.push(file);
        }
        const serverResponse = event.originalEvent.body as FileUploadDTO;
        serverResponse.details.forEach((fileInfo: FileUploadDetails) => {
            this.claim.motorPolicyClaimDocuments.push(
                {
                    fileName: fileInfo.fileName,
                    originalName: fileInfo.originalName,
                    id: null
                }
            );
        });
        this.filesUploaded = true;
        this.messageService.add({severity: 'info', summary: 'File Uploaded', detail: ''});

    }
    onBeforeUpload(event: any): void {
        event.formData.append('directory', 'motorpolicy');
    }





}
