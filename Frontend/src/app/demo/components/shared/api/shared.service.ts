import { Injectable } from '@angular/core';
import {HttpBaseEntityService} from "./http-base-entity.service";
import * as FileSaver from 'file-saver';
import {Observable} from "rxjs";
import {CibPayment, Invoice, Pageable, PolicyName} from "./shared.models";
@Injectable({
  providedIn: 'root'
})
export class SharedService extends HttpBaseEntityService<any>{

    override url_sub_path = '/shared';

    // getChartData(selectedFilters: SelectedFilters) {
    //     this.url_sub_path = 'shared/all=policy-names';
    //     return this.get();
    //
    // }

    getAllClients() {
        this.url_sub_path = 'clients/all';
        return this.get();

    }
    downloadFile(documentName: string): Observable<Blob> {


        return this.get( this.url_sub_path = 'files/' + documentName ,{

            responseType: 'blob'
        });
    }

    // exportPdf() {
    //     import("jspdf").then(jsPDF => {
    //         import("jspdf-autotable").then(x => {
    //             const doc = new jsPDF.default(0,0);
    //             doc.autoTable(this.exportColumns, this.products);
    //             doc.save('products.pdf');
    //         })
    //     })
    // }

    exportExcel(data :any) {
        import("xlsx").then(xlsx => {
            const worksheet = xlsx.utils.json_to_sheet(data);
            const workbook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
            const excelBuffer: any = xlsx.write(workbook, { bookType: 'xlsx', type: 'array' });
            this.saveAsExcelFile(excelBuffer, "data");
        });
    }

    saveAsExcelFile(buffer: any, fileName: string): void {
        let EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
        let EXCEL_EXTENSION = '.xlsx';
        const data: Blob = new Blob([buffer], {
            type: EXCEL_TYPE
        });
        FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
    }
    getPolicyStatuses() {
        this.url_sub_path = 'shared/policy-statuses';
        return this.get();
    }
    getRenewalPeriods() {
        this.url_sub_path = 'shared/renewal-periods';
        return this.get();

    }


    generateNumberSeries(type  : any) {
        this.url_sub_path = 'shared/number-series/generate-number';
        return this.post(type);
    }

    getClientById(insuredId: number) {
        this.url_sub_path = 'clients/' + insuredId;
        return this.get();

    }

    createPayment(payment: CibPayment) {
        this.url_sub_path = 'shared/payment/create';
        return this.post(payment);


    }

    getAllInsurers() {
        this.url_sub_path = 'clients/all-insurers';
        return this.get();

    }

    generateInvoice(invoice: Invoice) {

        this.url_sub_path = 'shared/invoices/create';
        return this.post(invoice);

    }

    getPolicyInvoices(pageableData: Pageable , policyId :  any , policyType : string) {
        console.log("policy Name "+ policyType+" policyId "+policyId)
        this.url_sub_path = 'shared/invoices/list/'+policyId+'/'+policyType;
        return this.post(pageableData);

    }

    getPolicyCommissions(pageableData: Pageable) {
        this.url_sub_path = 'shared/policy-commissions';
        return this.post(pageableData);

    }

    adjustCommissioRate(policyName: PolicyName) {
        this.url_sub_path = 'shared/adjust-commissions-rate';
        return this.post(policyName);



    }

    getInsurerInvoices(pageableData: Pageable, insurerId: number) {

        console.log(" policyId "+insurerId)
        this.url_sub_path = 'shared/invoices/list-by-insurer/'+insurerId
        return this.post(pageableData);


    }
}





