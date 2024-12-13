import { Component } from '@angular/core';
import {ClientSanctionCheckResponse} from "../../api/client.models";

@Component({
  selector: 'app-client-sanction-check',
  templateUrl: './client-sanction-check.component.html',
  styleUrls: ['./client-sanction-check.component.scss']
})
export class ClientSanctionCheckComponent {
    title: string = 'Sanction Check Result';
    visible : boolean = false;

    clientSanctionCheckResponse : ClientSanctionCheckResponse[] ;

    showSanctionsData(clientSanctionCheckResponse : ClientSanctionCheckResponse[]) {
        this.visible = true;
        this.clientSanctionCheckResponse = clientSanctionCheckResponse;
    }
}
