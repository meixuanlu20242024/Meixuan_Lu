import {Component, OnInit, ViewChild} from '@angular/core';
import { LayoutService } from 'src/app/layout/service/app.layout.service';
import {AuthService} from "../../shared/api/auth.service";
import {ActivationStart, Route, Router, RouterOutlet} from "@angular/router";
import {MessageService} from "primeng/api";
import {LoginRequest} from "../../shared/api/shared.models";
import {environment} from "../../../../../environments/environment";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styles: [`
        :host ::ng-deep .pi-eye,
        :host ::ng-deep .pi-eye-slash {
            transform:scale(1.6);
            margin-right: 1rem;
            color: var(--primary-color) !important;
        }
    `],
})
export class LoginComponent implements OnInit{


    @ViewChild(RouterOutlet) outlet: RouterOutlet;
    valCheck: string[] = ['remember'];
    loading: boolean = false;
    COMPANY_NAME = environment.COMPANY_NAME;

    constructor(public layoutService: LayoutService ,
                private authservice : AuthService ,
                private router :Router ,
                private messageService: MessageService
    ) { }
    loginRequest: LoginRequest = {
       username : '', password : ''
    };
    submitForm() {
        this.loading = true;
        this.loading = false;
        //navigate to dashboard
        this.router.navigate(['/motorpolicy/create-policy']).then(() => {
            console.log("Navigation complete");
        });
        // this.authservice.login({ username: this.loginRequest.username, password: this.loginRequest.password })
        //     .subscribe({
        //         next: (res) => {
        //             localStorage.setItem('token', res.token);
        //             this.loading = false;
        //             this.router.navigateByUrl('/motorpolicy/create-policy');
        //         },
        //         error: (error) => {
        //             this.loading = false;
        //         }
        //     });



    }

    ngOnInit(): void {

        this.router.events.subscribe(e => { if (e instanceof ActivationStart && e.snapshot.outlet === "auth")
        {

            console.log(e.snapshot.outlet+" "+e.snapshot.url[0].path)
            this.outlet.deactivate();
        }
        });

    }



}
