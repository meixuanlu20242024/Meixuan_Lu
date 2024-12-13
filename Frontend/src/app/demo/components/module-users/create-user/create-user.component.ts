import {Component, OnInit, ViewChild} from '@angular/core';
import {User, UserCreateContext} from "../api/user.models";
import {MessageService} from "primeng/api";
import {ActivatedRoute, Router} from "@angular/router";
import {UserService} from "../api/user.service";
import {NgForm} from "@angular/forms";
import {AuthService} from "../../shared/api/auth.service";
import {PassWordPolicy} from "../../shared/api/shared.models";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.scss']
})
export class CreateUserComponent  implements OnInit{

    constructor(
                private messageService: MessageService,
                private userService : UserService,
                private authService: AuthService,
                private route: ActivatedRoute,

                private router : Router) {

    }
    @ViewChild('form', { static: false }) ngForm: NgForm;
    title: string = 'Create User'
    user: UserCreateContext = {
        firstName: '',
        lastName: '',
        email: '',
        mobile: '',
        nationalId: '',
        password: '',
        confirmPassword: '',
        id: 0
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


        this.userService.createUser(this.user).subscribe((data: any) => {
                //     is successful, redirect to list page
                if (data && data.message) {

                    this.user = {
                        firstName: '',
                        lastName: '',
                        email: '',
                        mobile: '',
                        nationalId: '',
                        password: '',
                        confirmPassword : '',
                        id: 0

                    }
                    this.ngForm.resetForm();
                    this.messageService.add({severity:'success', summary: 'Success', detail: 'User Created Successfully'});

                }
                else {
                    this.messageService.add({severity:'error', summary: 'Error', detail: 'Failed to create User'});
                }

            }
        );

    }

    checkPasswordsMatch() {
        // this.messageService.add({severity:'error', summary: 'Error', detail: 'Passwords do not match'});

        if (this.user.password === this.user.confirmPassword) {
            this.passwordsMatch = true;
            this.ngForm.form.controls['confirmPassword'].setErrors(null);
        }else {
            this.ngForm.form.controls['confirmPassword'].setErrors({'incorrect': true});
        }
    }

    ngOnInit(): void {

        this.authService.passwordPolicy().subscribe((data: PassWordPolicy) => {

            if (data) {
                this.passwordPolicy = data;
            }
        }
        );
    }
}
