import { Injectable } from '@angular/core';
import {HttpBaseEntityService} from "../../shared/api/http-base-entity.service";
import {Pageable} from "../../shared/api/shared.models";
import {ChangePasswordRequest, UserCreateContext} from "./user.models";

@Injectable({
  providedIn: 'root'
})
export class UserService extends HttpBaseEntityService<any>{

    override url_sub_path = '/users';

    getUsers(pageable: Pageable) {
        this.url_sub_path = 'admin/users/list';
        return this.post(pageable);

    }

    createUser(user: UserCreateContext) {
        this.url_sub_path = 'admin/users/create';
        return this.post(user);
    }

    disableUser(id: number) {
        this.url_sub_path = 'admin/users/disable/'+id;
        return this.post(id);
    }

    activateUser(id: number) {
        this.url_sub_path = 'admin/users/activate/'+id;
        return this.post(id);
    }

    changeUserPassword(user: ChangePasswordRequest) {
        this.url_sub_path = 'admin/users/change-password/'+user.userId;
        return this.post(user);

    }
}
