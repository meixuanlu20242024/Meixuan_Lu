import {Component, OnInit, ViewChild} from '@angular/core';
import {MessageService} from "primeng/api";
import {UserService} from "../api/user.service";
import {AuthService} from "../../shared/api/auth.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NgForm} from "@angular/forms";
import {ChangePasswordRequest, UserCreateContext} from "../api/user.models";
import {PassWordPolicy} from "../../shared/api/shared.models";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit{
    constructor(
        private messageService: MessageService,
        private userService : UserService,
        private authService: AuthService,
        private route: ActivatedRoute,

        private router : Router) {

    }
    @ViewChild('form', { static: false }) ngForm: NgForm;
    title: string = 'Change User Password '
    userId: number  ;
    user: ChangePasswordRequest = {
        oldPassword: '',
        password: '',
        confirmPassword: '',
        userId: 0
    };
    passwordPolicy: PassWordPolicy = {

        minimum_length: 0,
        ambiguous_chars: false,
        lower_chars: false,
        upper_chars: false,
        include_numbers: false,
        include_symbols: false,
        expiration_days: 0



    }
    passwordsMatch: boolean = true;

    submitForm() {
        this.user.userId = this.userId;
        this.userService.changeUserPassword(this.user).subscribe((data: any) => {
                //     is successful, redirect to list page
                if (data && data.message) {

                    this.user = {
                        oldPassword: '',
                        password: '',
                        confirmPassword: '',
                        userId: 0

                    }
                    this.ngForm.resetForm();
                    this.messageService.add({severity:'success', summary: 'Success', detail: 'User password changed Successfully'});

                }
                else {
                    this.messageService.add({severity:'error', summary: 'Error', detail: 'Failed to change user password'});
                }

            }
        );

    }

    checkPasswordsMatch() {

        if (this.user.password === this.user.confirmPassword) {
            this.passwordsMatch = true;
            this.ngForm.form.controls['confirmPassword'].setErrors(null);
        }else {
            this.ngForm.form.controls['confirmPassword'].setErrors({'incorrect': true});
        }
    }

    ngOnInit(): void {

        this.route.paramMap.subscribe(params => {
            const id = params.get('id');
            if (id) {
                this.userId = parseInt(id);
            } else {
                this.messageService.add({severity:'error', summary: 'Error', detail: 'No User is  Selected'});
            }

        });


        this.authService.passwordPolicy().subscribe((data: PassWordPolicy) => {

                if (data) {
                    this.passwordPolicy = data;
                }
            }
        );
    }
}
