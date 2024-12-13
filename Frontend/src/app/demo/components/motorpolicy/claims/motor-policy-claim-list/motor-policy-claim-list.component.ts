import {ChangeDetectionStrategy, Component, Input, OnInit} from '@angular/core';
import {MotorPolicyService} from "../../api/motor-policy.service";
import {MotorPolicy, MotorPolicyClaim, MotorPolicyDocument} from "../../api/motor-policy.models";
import {LazyLoadEvent, MenuItem, MessageService} from "primeng/api";
import {POLICY_TYPE, Pageable} from "../../../shared/api/shared.models";
import {ContextMenu} from "primeng/contextmenu";
import {SharedService} from "../../../shared/api/shared.service";
import {CIBModuleType} from "../../../shared/api/shared.models";

@Component({
  selector: 'app-motor-policy-claim-list',
  templateUrl: './motor-policy-claim-list.component.html',

})
export class MotorPolicyClaimListComponent implements OnInit{
    constructor(private  motorPoliceService : MotorPolicyService , private sharedService : SharedService) {
    }

    cols: any[] = [];
    loading: boolean = false;
    totalRecords!: number;
    menuItems: MenuItem[] | undefined;
    selectedClaim :  MotorPolicyClaim ;
    protected readonly CIBModuleType = CIBModuleType;
    protected readonly POLICY_TYPE = POLICY_TYPE;
    @Input() policyId!: number;

    claims!: MotorPolicyClaim[];



    ngOnInit(): void {
        this.cols = [
            { field: 'id', header: 'id' },
            { field: 'claimStatus', header: 'Claim Status' },
            { field: 'claimNumber', header: 'Claim Number' },
            { field: 'dateOfLoss', header: 'Date of Loss' },
            { field: 'notificationDate', header: 'Notification Date' },
            { field: 'timeOfLoss', header: 'Time of Loss' },
            { field: 'lossAmount', header: 'Loss Amount' },
            { field: 'lossDetails', header: 'Loss Details' },
            { field: 'createdAt', header: 'Created At' },
            { field: 'createdBy', header: 'Created By' }
        ];





    }
    loadData(event: LazyLoadEvent) {
        this.loading = true;
        const pageableData: Pageable = {
            page: event.first = event.first / event.rows,
            size: event.rows
        };


        setTimeout(() => {
            this.motorPoliceService.getPoliciesClaims(pageableData).subscribe((data) => {
                if (data && data.content && Array.isArray(data.content)) {
                    this.claims = data.content;
                    this.totalRecords = data.totalElements;
                } else {
                    // Handle the case where the response structure is unexpected
                    console.error('Invalid response structure:', data);
                }
                this.loading = false;
            });
        }, 1000);
    }




    exportExcel() {
        this.sharedService.exportExcel(this.claims);
    }





}
