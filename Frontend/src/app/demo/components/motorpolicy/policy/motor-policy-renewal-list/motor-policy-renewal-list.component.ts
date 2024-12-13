import { Component } from '@angular/core';
import {MotorPolicy} from "../../api/motor-policy.models";
import {LazyLoadEvent, MenuItem, MessageService} from "primeng/api";
import {MotorPolicyService} from "../../api/motor-policy.service";
import {SharedService} from "../../../shared/api/shared.service";
import {Pageable} from "../../../shared/api/shared.models";
import {ContextMenu} from "primeng/contextmenu";
import {CIBModuleType} from "../../../shared/api/shared.models";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-motor-policy-renewal-list',
  templateUrl: './motor-policy-renewal-list.component.html',
  styleUrls: ['./motor-policy-renewal-list.component.scss'],
})
export class MotorPolicyRenewalListComponent {

    policies!: MotorPolicy[];
    cols: any[] = [];
    loading: boolean = false;
    menuItems: MenuItem[] | undefined;
    selectedPolicy: MotorPolicy ;
    protected readonly CIBModuleType = CIBModuleType;
    title: string = 'Motor Policy Renewal List';
    private policyId: number;

    constructor(private motorPoliceService : MotorPolicyService  ,private sharedService : SharedService ,
                private route: ActivatedRoute,
                private router : Router,
                private messageService: MessageService
                ) {}

    ngOnInit() {

        this.route.paramMap.subscribe(params => {
            const id = params.get('id');
            if (id) {
                this.policyId = parseInt(id);
            } else {
                this.messageService.add({severity:'error', summary: 'Error', detail: 'No Insured Selected'});
            }

        });

        this.loading = true;
        this.cols = [
            { field: 'id', header: 'ID' },

            { field: 'policyStatus', header: 'Policy Status' },
            { field: 'insured', header: 'Insured' },
            { field: 'currency', header: 'Currency' },
            { field: 'fleetIndividual', header: 'Fleet Individual' },
            { field: 'periodFrom', header: 'Period From' },
            { field: 'periodTo', header: 'Period To' },
            { field: 'policyClaim', header: 'Policy Claim' },
            { field: 'policyEndorsements', header: 'Policy Endorsements' },
            { field: 'motorPolicyDocuments', header: 'Motor Policy Documents' },
            { field: 'policyNo', header: 'Policy No' },
            { field: 'coverType', header: 'Cover Type' },
            { field: 'sumInsured', header: 'Sum Insured' },
            { field: 'premium', header: 'Premium' },
            { field: 'rate', header: 'Rate' },
            { field: 'stampDuty', header: 'Stamp Duty' },
            { field: 'createdAt', header: 'Created At' },
            { field: 'createdBy', header: 'Created By' },

        ];
        this.motorPoliceService.get('motor-policy/renewals/list/'+this.policyId).subscribe({
                // next: (data:  MotorPolicy[]) => {
                //     if (data) {

                //         console.log(data);
                //         this.policies = data;
                //     } else {
                //         // Handle the case where the response structure is unexpected
                //         console.error('Invalid response structure:', data);
                //     }
                //     this.loading = false;
                // }
                next: (data: any) => {
                    if (data && data.content) {
                        console.log(data.content);  // Check if content is available
                        this.policies = data.content;  // Extract the content array
                    } else {
                        console.error('No content found in the response:', data);
                    }
                    this.loading = false;
                }
            }
        );

    }




    exportExcel() {
        this.sharedService.exportExcel(this.policies);
    }

    exportPdf() {
        // this.sharedService.exportPdf();
    }


}
