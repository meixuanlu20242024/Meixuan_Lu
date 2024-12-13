import {Component, Input} from '@angular/core';
import {FilterType} from "../../shared/api/shared.models";

@Component({
  selector: 'app-user-dialog',
  templateUrl: './user-dialog.component.html',
  styleUrls: ['./user-dialog.component.scss']
})
export class UserDialogComponent {
    @Input({required : true}) visible: boolean ;

    @Input({required : true}) userId: number;

    showDialog() {
        this.visible = true;
    }
}
