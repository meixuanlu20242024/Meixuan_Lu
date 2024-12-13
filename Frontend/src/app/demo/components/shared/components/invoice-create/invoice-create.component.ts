import {Component, Input, OnInit, Output, ViewChild} from '@angular/core';
import {DialogServiceCib} from "../../api/dialog-service-cib.service";
import {Currency, Invoice, POLICY_TYPE} from "../../api/shared.models";
import {NgForm} from "@angular/forms";
import {SharedService} from "../../api/shared.service";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-invoice-create',
  templateUrl: './invoice-create.component.html',
  styleUrls: ['./invoice-create.component.scss']
})
export class InvoiceCreateComponent implements OnInit{


    loading: boolean;
    visible: boolean = true;
    invoice :  Invoice  = {
        id: 0 ,
        createdAt: '' ,
        createdBy: '' ,
        paymentDate: '',
        paymentAmount:null,
        paymentMethod: '',
        paymentReference: '',
        policyType: null,
        policyId: 0,
        insurerId : 0
    }

    constructor(private dialogService: DialogServiceCib ,
                private sharedService :  SharedService ,
                private messageService: MessageService,
                ) {
    }

    @Input({required : true}) policyType : POLICY_TYPE  ;
    @Input({required : true}) policy: any ;

    ngOnInit(): void {

        this.dialogService.showDialog$.subscribe(show => {
            this.visible = show;
        });

    }
    @ViewChild('form', { static: false }) ngForm: NgForm;

    closeDialog() {
        this.dialogService.hideDialog();
    }


    submitForm() {

        this.invoice  = {
            id: 0 ,
            createdAt: '' ,
            createdBy: '' ,
            paymentDate: this.ngForm.controls['paymentDate'].value ,
            paymentAmount: this.ngForm.controls['paymentAmount'].value ,
            paymentMethod: this.ngForm.controls['paymentMethod'].value ,
            paymentReference: this.ngForm.controls['paymentReference'].value ,
            policyType: this.policyType ,
            policyId: this.policy.id ,
            insurerId : this.policy.insurer.id
        }

        console.log(this.invoice.policyId+" policy id and this.pilicyId = "+this.policy.id )
        this.sharedService.generateInvoice(this.invoice).subscribe({

                next: (data: any) => {
                    if (data && data.message){
                        this.ngForm.resetForm();
                        this.messageService.add({key:'notification' ,severity:'success', summary: 'Success', detail: 'Invoice Created Successfully'});

                    }

                },
                error: (error: any) => {


                    this.loading = false;
                    this.messageService.add({
                        key: 'notification',
                        severity: 'error',
                        summary: 'Error',
                        detail: 'Error generating Invoice  '+error.toString()
                    });
                }
            }
        );

    }

    protected readonly POLICY_TYPE = POLICY_TYPE;
}
