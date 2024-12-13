import {Component, Input, ViewChild} from '@angular/core';
import {BordreauxQueryDTO} from "../api/client.models";
import {DialogServiceCib} from "../../shared/api/dialog-service-cib.service";
import {MessageService} from "primeng/api";
import {ClientService} from "../api/client.service";
import {Currency} from "../../shared/api/shared.models";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-sms-dialog',
  templateUrl: './sms-dialog.component.html',
  styleUrls: ['./sms-dialog.component.scss']
})
export class SmsDialogComponent {

    visible: boolean = true;

    @Input({required : true}) bordeaux :  BordreauxQueryDTO ;
    @Input({required : true}) insurerName :  String ;
    showDialog: boolean = false;
    loading: boolean;
    currencies: string[];
    bordTypes: string[];
    bordreauxForm: BordreauxQueryDTO = {
        insurerId: 0,
        periodFrom: '',
        periodTo: '',
        currency: '',
        bordType: ''  ,
    };


    constructor(private dialogService: DialogServiceCib ,
                private messageService: MessageService,

                private clientService : ClientService ,
    ) {

    }

    ngOnInit(): void {
        this.dialogService.showDialog$.subscribe(show => {
            this.visible = show;
        });

        console.log("is visble = "+this.visible)
        this.currencies = Object.values(Currency) ;
        this.bordTypes = ["Outline" ,  "Detailed" ,  "Invoice"]
    }



    @ViewChild('form', { static: false }) ngForm: NgForm;




    submitForm() {

        // this.loading = true;
        //
        //
        // const periodFrom = this.ngForm.controls['periodFrom'].value;
        // const periodTo = this.ngForm.controls['periodTo'].value;
        // const currency = this.ngForm.controls['currency'].value;
        // const bordType = this.ngForm.controls['bordType'].value;
        // this.bordreauxForm  = {
        //     insurerId: this.bordeaux.insurerId,
        //     periodFrom: periodFrom ,
        //     periodTo: periodTo ,
        //     currency: currency,
        //     bordType: bordType  ,
        // };
        // if (this.bordreauxForm.bordType != "Outline"){
        //
        //     this.messageService.add({
        //         key: 'notification',
        //         severity: 'success',
        //         summary: 'Data syncing . please wait... ',
        //         detail: 'Data syncing . please wait.... '
        //     });
        //
        //     return ;
        // }
        // this.clientService.runBordeauxWithRange(this.bordreauxForm).subscribe({
        //
        //         next: (data: any) => {
        //
        //             if (data){
        //                 this.loading = false;
        //                 const blob = new Blob([data], {type: 'application/pdf'});
        //                 const url = window.URL.createObjectURL(blob);
        //                 this.dialogService.hideDialog();
        //                 window.open(url);
        //
        //
        //
        //
        //             }
        //
        //         }
        //     }
        // );
        //




    }

    closeDialog() {
        this.dialogService.hideDialog();
    }

    showDialogB() {
        this.visible = true;
    }
}
