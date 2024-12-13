import { Injectable } from '@angular/core';
import {
    HttpRequest,
    HttpHandler,
    HttpEvent,
    HttpInterceptor, HttpErrorResponse
} from '@angular/common/http';
import {catchError, Observable, throwError} from 'rxjs';
import {Router} from "@angular/router";
import {MessageService} from "primeng/api";

@Injectable(


)
export class AuthInterceptor implements HttpInterceptor {

    token!: string;

    constructor(
        private router: Router ,
        private messageService: MessageService
    ) {}

    intercept(requestToHandle: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {

        // get token from local storage
        this.token = localStorage.getItem('token') === null ? '' : localStorage.getItem('token') as string;

        // Clone our request with the new headers because HttpRequests are immutable
        const authReq = requestToHandle.clone({

            headers: requestToHandle.headers.set('Authorization', 'Bearer ' + this.token)
        });

        return next.handle(authReq).pipe(


            catchError((error: HttpErrorResponse) => {

                    // if (error.status === 401) {
                    //     // navigate to login page
                    //     this.router.navigate(['/auth']);
                    //
                    // }
                    // if (error.status === 403) {
                    //     // navigate to login page
                    //     this.router.navigate(['/forbidden']);
                    // }
                    //

                   if (error.status === 404) {
                        // navigate to 404 page
                        this.router.navigate(['/404']);
                    }

                    if (error.status === 400) {


                        this.messageService.add({
                            key: 'notification',
                            severity: 'error',
                            summary: 'Woops! There was an error!',
                            detail: error.error.message
                        });

                        try {
                            if (error.error.errors.length > 0) {
                                for (let i = 0; i < error.error.errors.length; i++) {
                                    this.messageService.add({
                                        key: 'notification',
                                        severity: 'error',
                                        summary: 'Error',
                                        detail: error.error.errors[i]
                                    });
                                }

                            }

                        } catch (e) {

                        }


                    }

                    else if (error.status === 500) {

                        this.messageService.add({key:'notification' ,  severity:'error', summary: 'Error', detail: "Internal Server Error"});
                    }



                  return throwError(() => error);
            }));
    }
  }


