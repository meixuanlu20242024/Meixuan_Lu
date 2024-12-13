import { Injectable } from '@angular/core';
import {HttpBaseEntityService} from "../../shared/api/http-base-entity.service";
import {Pageable} from "../../shared/api/shared.models";
import {UserCreateContext} from "../../module-users/api/user.models";
import {BordreauxQueryDTO, ClientSanctionCheckRequest, Insurer, RetailClient} from "./client.models";

@Injectable({
  providedIn: 'root'
})
export class ClientService extends HttpBaseEntityService<any>{

    override url_sub_path = '/users';

    getClients(pageable: Pageable) {
        this.url_sub_path = 'clients/list';
        return this.post(pageable);

    }

    createClient(client: RetailClient) {
        this.url_sub_path = 'clients/create';
        return this.post(client);
    }
    checkSanctionedStatus(sanction: ClientSanctionCheckRequest) {
        this.url_sub_path = 'clients/aml-check';
        return this.post(sanction);
    }
    createInsurer(insurer: Insurer) {
        this.url_sub_path = 'clients/create-insurer';
        return this.post(insurer);

    }

    getInsurers(pageable: Pageable) {
        this.url_sub_path = 'clients/list-insurers';
        return this.post(pageable);

    }

    runBordeaux(selectedInsurer: Insurer) {
                        this.url_sub_path = 'clients/run-bordeaux';
                        return this.postByHeaders(selectedInsurer , null ,
                                {
                                    responseType: 'blob'
                                },
                            );
    }

    getClientById(id: number) {
        this.url_sub_path = 'clients/'+id;
        return this.get();
    }

    editClient(client: RetailClient) {
        this.url_sub_path = 'clients/edit/'+client.id;
        return this.post(client);
    }

    runBordeauxWithRange(bordreauxForm: BordreauxQueryDTO) {

        this.url_sub_path = 'clients/run-bordeaux';
        return this.postByHeaders(bordreauxForm , null ,
            {
                responseType: 'blob'
            },
        );

    }
}
