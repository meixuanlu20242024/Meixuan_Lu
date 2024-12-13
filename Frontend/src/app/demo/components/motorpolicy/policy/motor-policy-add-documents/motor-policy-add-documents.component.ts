import {Component, ViewChild} from '@angular/core';
import {SharedService} from "../../../shared/api/shared.service";
import {MotorPolicyService} from "../../api/motor-policy.service";
import {FormBuilder, FormGroup, NgForm} from "@angular/forms";
import {MessageService} from "primeng/api";
import {MotorPolicy, MotorPolicyClaim} from "../../api/motor-policy.models";
import {environment} from "../../../../../../environments/environment";
import {ActivatedRoute, Router} from "@angular/router";
import {CibDocument, FileUploadDetails, FileUploadDTO} from "../../../shared/api/shared.models";


@Component({
  selector: 'app-motor-policy-add-documents',
  templateUrl: './motor-policy-add-documents.component.html',
  styleUrls: ['./motor-policy-add-documents.component.scss']
})
export class MotorPolicyAddDocumentsComponent {

    constructor(private sharedService : SharedService ,
                private motorPolicyService : MotorPolicyService ,
                private messageService: MessageService,
                private route: ActivatedRoute,
                private router : Router
    ) {
    }

    @ViewChild('form', { static: false }) ngForm: NgForm;
    title: string = ''
    uploadedFiles: any[] = [];
    uploadUrl: String = environment.API_ENDPOINT + 'files/upload';
    filesUploaded: boolean = false;
    policyId: number;
    cibDocuments : CibDocument[] = [] ;


    ngOnInit(): void {

        this.route.paramMap.subscribe(params => {
            const id = params.get('id');
            if (id) {
                this.policyId = parseInt(id);
            } else {
                this.messageService.add({ key:'notification' ,severity:'error', summary: 'Error', detail: 'No Insured Selected'});
            }

        });
        this.motorPolicyService.getPolicyById(this.policyId).subscribe((data: MotorPolicy) => {
                // this.claim.insured = data

                this.title = 'Documents for  ' + data.insured.name + ' : policy ' + data.policyNo;
            }
        );

    }


    onUpload(event:any) {
        for(let file of event.files) {
            this.uploadedFiles.push(file);
        }
        const serverResponse = event.originalEvent.body as FileUploadDTO;

        console.log(serverResponse);
        serverResponse.details.forEach((fileInfo: FileUploadDetails) => {
            this.cibDocuments.push(
                {
                    fileName: fileInfo.fileName,
                    originalName: fileInfo.originalName,
                    id: null
                }
            );
        });
        this.filesUploaded = true;
        this.messageService.add({key:'notification' ,severity: 'info', summary: 'File Uploaded , proceed to submit', detail: ''});

    }
    onBeforeUpload(event: any): void {
        event.formData.append('directory', 'motorpolicy');
    }


    submitForm() {

        this.motorPolicyService.addDocuments(this.cibDocuments, this.policyId).subscribe((data: any) => {
                //     is successful, redirect to list page
                if (data && data.message) {



                    this.uploadedFiles = [];
                    this.filesUploaded = false;

                    this.ngForm.resetForm();
                    this.messageService.add({key:'notification' , severity:'info', summary: 'Error', detail: 'Documents added successfully'});

                }
                else {
                    this.messageService.add({key:'notification' , severity:'error', summary: 'Error', detail: 'Failed to add document '});
                }

            }
        );

    }
}
