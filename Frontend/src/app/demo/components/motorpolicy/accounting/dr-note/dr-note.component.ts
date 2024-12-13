import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import jsPDF from 'jspdf';
import pdfMake from 'pdfmake/build/pdfmake';
import pdfFonts from 'pdfmake/build/vfs_fonts';
pdfMake.vfs = pdfFonts.pdfMake.vfs
import htmlToPdfmake from 'html-to-pdfmake';
import html2canvas from "html2canvas";
import jspdf from "jspdf";
import {NgxCaptureService} from "ngx-capture";
import {MotorPolicyService} from "../../api/motor-policy.service";
import {ActivatedRoute} from "@angular/router";
import {MessageService} from "primeng/api";

@Component({
  selector: 'app-dr-note',
  templateUrl: './dr-note.component.html',
  styleUrls: ['./dr-note.component.css']
})
export class DrNoteComponent  implements OnInit{
  title = 'Generate DR Note and CR Note';
  policyId: number;
  crLoading: boolean = false;
    drLoading: boolean = false;
  crDrDisabled: boolean = false;

  constructor(private motorPoliceService:MotorPolicyService ,
  private route: ActivatedRoute,
              private messageService: MessageService,
  ) {
  }
  public generateDrNote() {
    this.drLoading = true;
    this.crDrDisabled = true;
    this.motorPoliceService.generateDrNote(this.policyId).subscribe({
          next: (data: any) => {
            this.drLoading = false;
            this.crDrDisabled = false;
            const blob = new Blob([data], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);
            window.open(url);

          },
          error: (error: any) => {
            this.drLoading = false;
            this.crDrDisabled = false;
            this.messageService.add({ key:'notification' ,severity:'error', summary: 'Error', detail: 'Error generating DR Note'});
          }

        }
    )

  } public generateCrNote() {
    this.crLoading = true;
    this.crDrDisabled = true;
    this.motorPoliceService.generateCrNote(this.policyId).subscribe({
          next: (data: any) => {
            this.crLoading = false;
            this.crDrDisabled = false;
            const blob = new Blob([data], { type: 'application/pdf' });
            const url = window.URL.createObjectURL(blob);
            window.open(url);

          },
      error: (error: any) => {

            this.crLoading = false;
            this.crDrDisabled = false;
        this.messageService.add({ key:'notification' ,severity:'error', summary: 'Error', detail: 'Error generating CR Note'});
      }
        }
    );

  }

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.policyId = parseInt(id);
      } else {
        this.messageService.add({ key:'notification' ,severity:'error', summary: 'Error', detail: 'Id not found'});
      }

    });

    this.motorPoliceService.getPolicyById(this.policyId).subscribe((data: any) => {
            this.title = 'Generate DR Note and CR Note for ' + data.insured.name + ' : policy no :' + data.policyNo;
            }
    );
    }



}
