import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DialogServiceCib {

    private showDialogSource = new BehaviorSubject<boolean>(false);
    showDialog$ = this.showDialogSource.asObservable();

    constructor() { }

    showDialog() {

        this.showDialogSource.next(true);
    }

    hideDialog() {
        console.log("in hide  dialog")
        this.showDialogSource.next(false);
    }


}
