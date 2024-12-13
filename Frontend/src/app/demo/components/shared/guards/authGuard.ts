import { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';
import {inject} from "@angular/core";

export const authGuard: CanActivateFn = (route, state) => {
    const router = inject(Router);
    const token =  localStorage.getItem('token') ;

    if (token) {
        const expiry = (JSON.parse(atob(token.split('.')[1]))).exp;
        if((Math.floor((new Date).getTime() / 1000)) >= expiry){
            localStorage.removeItem('token');

                router.navigate(['/auth']);

            return false;
        }
        return true;
    } else {
            router.navigate(['/auth']);

        return false;
    }



};





