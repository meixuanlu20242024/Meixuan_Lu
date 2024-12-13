import { Component } from '@angular/core';
import { LayoutService } from "./service/app.layout.service";
import {environment} from "../../environments/environment";

@Component({
    selector: 'app-footer',
    templateUrl: './app.footer.component.html'
})
export class AppFooterComponent {

      PROVIDER_NAME = environment.PROVIDER_NAME;
      PROVIDER_URL = environment.PROVIDER_URL;
    constructor(public layoutService: LayoutService) { }
}
