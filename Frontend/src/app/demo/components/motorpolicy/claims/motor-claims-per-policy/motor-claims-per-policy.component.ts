import {Component, Input} from '@angular/core';
import {MotorPolicyService} from "../../api/motor-policy.service";
import {SharedService} from "../../../shared/api/shared.service";
import {LazyLoadEvent, MenuItem, MessageService} from "primeng/api";
import {MotorPolicyClaim} from "../../api/motor-policy.models";
import {CIBModuleType, POLICY_TYPE} from "../../../shared/api/shared.models";

@Component({
  selector: 'app-motor-claims-per-policy',
  templateUrl: './motor-claims-per-policy.component.html',
  styleUrls: ['./motor-claims-per-policy.component.scss'],
})
export class MotorClaimsPerPolicyComponent {

    constructor(private  motorPoliceService : MotorPolicyService , private sharedService : SharedService) {
    }

    cols: any[] = [];
    loading: boolean = false;
    menuItems: MenuItem[] | undefined;
    selectedClaim :  MotorPolicyClaim ;
    visible: boolean = false;
    policyClaims : MotorPolicyClaim[] = [];
    @Input( {required :true})  title  :  string
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

        this.loadClaims()





    }



    exportExcel() {
        this.sharedService.exportExcel(this.claims);
    }

    loadClaims(){
        this.motorPoliceService.get('motor-policy-claim/list/'+this.policyId).subscribe({
                next: (data: any) => {
                    if (data && data && Array.isArray(data)) {
                        this.claims = data;
                    } else {
                        console.error('Invalid response structure:', data);
                    }
                    this.loading = false;
                }
            }
        );

    }

    showDialog() {
        this.motorPoliceService.getClaimsByPolicyId(this.policyId).subscribe((data: MotorPolicyClaim[]) => {
            this.policyClaims = data;
        });

        this.visible = true;
    }



}
