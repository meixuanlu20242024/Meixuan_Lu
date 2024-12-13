import { Injectable } from '@angular/core';
import {HttpBaseEntityService} from "./http-base-entity.service";
import {LoginRequest} from "./shared.models";

@Injectable({
  providedIn: 'root'
})
export class AuthService  extends HttpBaseEntityService<any>{

    login(loginRequest  : LoginRequest) {
        this.url_sub_path = 'auth/login';
        return this.post(loginRequest);
    }

    passwordPolicy() {
        this.url_sub_path = 'admin/password-policy';
        return this.get();
    }
}
