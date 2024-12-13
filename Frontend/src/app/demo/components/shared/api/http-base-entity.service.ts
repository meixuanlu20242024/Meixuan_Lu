import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../../../environments/environment";



@Injectable({
    providedIn: 'root'
})
export class HttpBaseEntityService<T> {
    url_sub_path = '';
    private baseUrl = environment.API_ENDPOINT;


    constructor(protected http: HttpClient) {}
    get(url?: string ,  headers? : {  }): Observable<T> {
        const apiUrl  = url ? this.baseUrl+url : this.baseUrl + this.url_sub_path

        return this.http.get<any>(apiUrl  , headers ? headers : {});
    }


    post(entity: T ,  url?: string): Observable<any> {
        const apiUrl  = url ? this.baseUrl+url : this.baseUrl + this.url_sub_path
        return this.http.post<any>(apiUrl  , entity);
    }
    postByHeaders(entity: T ,  url?: string ,   headers? : {  }): Observable<any> {
        const apiUrl  = url ? this.baseUrl+url : this.baseUrl + this.url_sub_path
        return this.http.post<any>(apiUrl  , entity , headers ? headers : {});
    }
    patch(entity: T ,url?: string ): Observable<any> {

        const apiUrl  = url ? this.baseUrl+url : this.baseUrl + this.url_sub_path
        return this.http.patch<any>(apiUrl  , entity);
    }
    put(entity: T , url?: string): Observable<any> {
        const apiUrl  = url ? this.baseUrl+url : this.baseUrl + this.url_sub_path
        return this.http.put<any>(apiUrl  , entity);
    }
    delete(entity: T , url?: string): Observable<any> {
        const apiUrl  = url ? this.baseUrl+url : this.baseUrl + this.url_sub_path
        return this.http.delete<any>(apiUrl  , entity);
    }

}
