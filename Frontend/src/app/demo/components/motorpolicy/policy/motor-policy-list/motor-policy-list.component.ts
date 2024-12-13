import {Component, OnInit, ViewChild} from '@angular/core';
import {MotorPolicy} from "../../api/motor-policy.models";
import {MotorPolicyService} from "../../api/motor-policy.service";
import {
    CIBModuleType,
    FilterOptions,
    FilterType,
    Pageable, POLICY_TYPE,
    PolicyAccountingData,
    SelectedFilters
} from "../../../shared/api/shared.models";
import {LazyLoadEvent, MenuItem} from "primeng/api";
import {SharedService} from "../../../shared/api/shared.service";
import {Router} from "@angular/router";
import {DrNoteComponent} from "../../accounting/dr-note/dr-note.component";
import {DialogServiceCib} from "../../../shared/api/dialog-service-cib.service";

@Component({
  selector: 'app-motor-policy-list',
  templateUrl: './motor-policy-list.component.html',
  styleUrls: ['./motor-policy-list.component.scss'],
})
export class MotorPolicyListComponent implements OnInit{
    policies!: MotorPolicy[];
    cols: any[] = [];
    loading: boolean = false;
    totalRecords!: number;
    menuItems: MenuItem[] | undefined;
    selectedPolicy: MotorPolicy ;
    protected readonly CIBModuleType = CIBModuleType;
    protected readonly POLICY_TYPE = POLICY_TYPE;

    documentsTitle: string;
    filterOptions: FilterOptions;
    selectedFilters: SelectedFilters = {};
    @ViewChild(DrNoteComponent) drNoteComponent: DrNoteComponent


    constructor(private motorPoliceService : MotorPolicyService  ,
                private dialogService: DialogServiceCib ,
                private sharedService : SharedService ,  private router: Router) {}

    ngOnInit() {
        this.loading = true;
        this.menuItems = [


            {
                separator: true
            },


            {
                label: 'Edit Policy',
                icon: 'pi pi-fw pi-pencil',
                command: (event) => this.onEditClaimClick(this.selectedPolicy),


            },
            {
                label: 'Renew Policy',
                icon: 'pi pi-fw pi-refresh' ,
                command: (event) => this.onRenewPolicyClick(this.selectedPolicy),
            },
            {
                label: 'Add Documents',
                icon: 'pi pi-fw pi-book',
                command: (event) => this.onAddDocumentsClick(this.selectedPolicy),


            },
            {
                label: 'Renewal List',
                icon: 'pi pi-fw pi-list',
                command : (event ) => this.onRenewalsClick(this.selectedPolicy),


            },
            {
                label: 'Create Claim',
                icon: 'pi pi-fw pi-plus' ,
                command: (event) => this.onCreateClaimClick(this.selectedPolicy),

            },










            {
                separator: true
            },




        ];

        this.cols = [
            { field: 'id', header: 'ID' },

            { field: 'policyStatus', header: 'Policy Status' },

            { field: 'insured', header: 'Insured' },
            { field: 'insurer', header: 'Insurer' },
            { field: 'currency', header: 'Currency' },
            { field: 'fleetIndividual', header: 'Fleet Individual' },
            { field: 'periodFrom', header: 'Period From' },
            { field: 'periodTo', header: 'Period To' },
            { field: '', header: 'Policy Status' },
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
        // this.motorPoliceService.get('motor-policy/list').subscribe({
        //     next: (data: any) => {
        //         if (data && data.content && Array.isArray(data.content)) {
        //             this.policies = data.content;
        //             this.totalRecords = data.totalElements;
        //         } else {
        //             // Handle the case where the response structure is unexpected
        //             console.error('Invalid response structure:', data);
        //         }
        //         this.loading = false;
        //     }
        // }
        // );

        this.documentsTitle = 'Motor Policy Documents for Policy No: ' + this.selectedPolicy?.policyNo;

    }

    loadData(event: LazyLoadEvent) {
        console.log(event);
        this.loading = true;
        const pageableData: Pageable = {
            page: event.first = event.first / event.rows,
            size: event.rows ,
            globalFilter: event.globalFilter,
        };


        setTimeout(() => {
            this.motorPoliceService.getPolicies(pageableData).subscribe((data) => {
                if (data && data.content && Array.isArray(data.content)) {
                    this.policies = data.content;
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
            this.sharedService.exportExcel(this.policies);
    }

    exportPdf() {
        // this.sharedService.exportPdf();
    }



    private onCreateClaimClick(selectedPolicy: MotorPolicy) {
        this.selectedPolicy = selectedPolicy;

        if (selectedPolicy && selectedPolicy.id) {
            this.router.navigate(['/motorpolicy/create-claim', selectedPolicy.id]).then();
        }
    }
    private onRenewalsClick(selectedPolicy: MotorPolicy) {
        this.selectedPolicy = selectedPolicy;

        if (selectedPolicy && selectedPolicy.id) {
            this.router.navigate(['/motorpolicy/renewals', selectedPolicy.id]).then();
        }
    }


    isExpired(periodTo: string): boolean {
        const today = new Date();
        const toDate = new Date(periodTo);
        return toDate < today;
    }

    onFilterChanged(filters: SelectedFilters): void {
        this.selectedFilters = filters;
    }


    onPolicyDataChanged(policyData: PolicyAccountingData) {


    }

    protected readonly FilterType = FilterType;
    filteredResultUrl: any;

    private generateDrNote(selectedPolicy: MotorPolicy) {



         this.router.navigate(['/motorpolicy/dr-note/', selectedPolicy.id]).then();


    }

    private onEditClaimClick(selectedPolicy: MotorPolicy) {
        this.router.navigate(['/motorpolicy/edit', selectedPolicy.id]).then();

    }

    private onRenewPolicyClick(selectedPolicy: MotorPolicy) {
        this.router.navigate(['/motorpolicy/renew', selectedPolicy.id]).then();

    }

    onRowSelect(event) {
        this.selectedPolicy = event.data;
    }
    private onAddDocumentsClick(selectedPolicy: MotorPolicy) {

        this.router.navigate(['/motorpolicy/add-documents', selectedPolicy.id]).then();


    }

    private createPayment(selectedPolicy: MotorPolicy) {
        this.router.navigate(['/shared/create-payment', selectedPolicy.id] , {state : selectedPolicy}).then();
    }

    private onListPolicyInvoices(selectedPolicy: any) {


        this.router.navigate(['/shared/invoices-list', selectedPolicy.id] , {
            state : {
                policyType: POLICY_TYPE.MOTORPOLICY.toString() ,
                title : "Listing invoices for "+selectedPolicy?.insured?.name+" "+selectedPolicy?.insured?.lastName+" "+" police no : "+selectedPolicy?.policyNo
            }}).then();
    }
}
