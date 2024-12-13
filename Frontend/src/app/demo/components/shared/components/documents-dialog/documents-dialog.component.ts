import {Component, Input, OnInit} from '@angular/core';
import {MotorPolicyDocument} from "../../../motorpolicy/api/motor-policy.models";
import {MotorPolicyService} from "../../../motorpolicy/api/motor-policy.service";
import {SharedService} from "../../api/shared.service";
import {MessageService} from "primeng/api";
import {CIBModuleType, POLICY_TYPE} from "../../api/shared.models";



@Component({
  selector: 'app-documents-dialog',
  templateUrl: './documents-dialog.component.html',
  styleUrls: ['./documents-dialog.component.scss']
})
export class DocumentsDialogComponent{

    constructor(
        private  motorPoliceService : MotorPolicyService ,

        private  directorsPersonalAccidentService : MotorPolicyService ,
        private  bondsAndGuaranteesService : MotorPolicyService ,


                private sharedService : SharedService , private messageService : MessageService) {
    }
    visible: boolean = false;
    @Input() policyId!: number;
    @Input({required :true})  moduleType  :  CIBModuleType
    @Input({required :true})  policyType  :  POLICY_TYPE
    @Input({required :true})  title  :  string
    documents!: MotorPolicyDocument[];

    showDialog() {

        const moduleTypeFunctionMap = {
            [CIBModuleType.POLICY]: () => this.getPolicyDocumentsByType(this.policyType),
            [CIBModuleType.CLAIM]: () => this.getPolicyClaimDocumentsByType(this.policyType),
            // [CIBModuleType.CLAIM]: () => this.motorPoliceService.getClaimDocuments(this.policyId),
            // [CIBModuleType.ENDORSEMENT]: () => this.motorPoliceService.getEndorsementDocuments(this.policyId),
            // [CIBModuleType.RENEWAL]: () => this.motorPoliceService.getRenewalDocuments(this.policyId),
            // [CIBModuleType.ACCOUNTING]: () => this.motorPoliceService.getAccountingDocuments(this.policyId)
        };

        const func = moduleTypeFunctionMap[this.moduleType];
        if (func) {
            const observable = func();
            if (observable && typeof observable.subscribe === 'function') {
                observable.subscribe((data: any[]) => {
                    this.documents = data;
                }, error => {
                    console.error('Error subscribing to observable', error);
                });
            } else {
                //console.error('Returned value is not an observable');
            }
        } else {
            console.error('No matching function for moduleType', this.moduleType);
        }

        this.visible = true;
    }


    downloadFile(documentName: string) {
        this.sharedService.downloadFile(documentName).subscribe((data: Blob) => {
            const blob = new Blob([data], { type: 'application/octet-stream' });
            const url = window.URL.createObjectURL(blob);

            // Create a link element and trigger the download
            const a = document.createElement('a');
            a.href = url;
            a.download = documentName;
            a.style.display = 'none';
            document.body.appendChild(a);
            a.click();

            // Clean up
            window.URL.revokeObjectURL(url);
            document.body.removeChild(a);
        });
    }


    getPolicyDocumentsByType(policyType) {
        const policyTypeFunctionMap = {
            [POLICY_TYPE.MOTORPOLICY]: () => this.motorPoliceService.getDocuments(this.policyId) ,

           [POLICY_TYPE.DIRECTORSPERSONALACCIDENT]: () => this.directorsPersonalAccidentService.getDocuments(this.policyId),


            // Add other policy types here
        };

        const func = policyTypeFunctionMap[policyType];
        if (func) {
            func().subscribe((data: any[]) => {
                this.documents = data;
            });
        }
    }
    getPolicyClaimDocumentsByType(policyType) {
        const policyTypeFunctionMap = {
            [POLICY_TYPE.MOTORPOLICY]: () => this.motorPoliceService.getClaimDocuments(this.policyId) ,

            [POLICY_TYPE.GROUPPERSONALACCIDENT]: () => this.motorPoliceService.getClaimDocuments(this.policyId) ,
            [POLICY_TYPE.EMPLOYERSLIABILITY]: () => this.motorPoliceService.getClaimDocuments(this.policyId) ,


            [POLICY_TYPE.DIRECTORSPERSONALACCIDENT]: () => this.directorsPersonalAccidentService.getClaimDocuments(this.policyId),




        };

        const func = policyTypeFunctionMap[policyType];
        if (func) {
            func().subscribe((data: MotorPolicyDocument[]) => {
                this.documents = data;
            });
        }
    }
}
