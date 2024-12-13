import { Injectable } from '@angular/core';
import {HttpBaseEntityService} from "../../shared/api/http-base-entity.service";
import {CibDocument, Pageable, SelectedFilters} from "../../shared/api/shared.models";
import {Observable} from "rxjs";
import {HttpHeaders} from "@angular/common/http";
import {MotorPolicy, MotorPolicyRequest} from "./motor-policy.models";

@Injectable({
  providedIn: 'root'
})
export class MotorPolicyService extends HttpBaseEntityService<any>{

    override url_sub_path = '/motor-policy';

    getChartData(selectedFilters: SelectedFilters) {
        this.url_sub_path = 'motorpolicy/accounting/chart';
        return this.post(selectedFilters);

    }


    getPolicies(param: Pageable) {
        this.url_sub_path = 'motor-policy/list';
        return this.post(param);

    }

    getDocuments(policyId: number) {
        this.url_sub_path = 'motor-policy-document/view/' + policyId;
        return this.get();


    }



    getCovertypes() {
        this.url_sub_path = 'motor-policy/cover-types';
        return this.get();
    }

    getFleetIndividual() {
        this.url_sub_path = 'motor-policy/fleet-individual';
        return this.get();
    }

    createMotorPolicy(policyRequest: any) {
        this.url_sub_path = 'motor-policy/create';
        return this.post(policyRequest);
    }

    getClaimsByPolicyId(policyId: number) {
            this.url_sub_path =  'motor-policy-claim/list/' + policyId;
            return this.get();
    }

    getPoliciesClaims(param: Pageable) {
        this.url_sub_path = 'motor-policy-claim/all-claims';
        return this.post(param);

    }

    getClaimDocuments(claimId: number) {
        this.url_sub_path = 'motor-policy-claim-document/view/' + claimId;
        return this.get();

    }

    getClaimStatus() {
        return this.get('motor-policy-claim/statuses');
    }

    createMotorPolicyClaim(claimRequest: any) {
        this.url_sub_path = 'motor-policy-claim/create';
        return this.post(claimRequest);

    }

    getPoliciesRenewals(param: Pageable, policyId: number) {

        this.url_sub_path = 'motor-policy/renewals/list/' + policyId;
        return this.post(param);


    }
    getPoliciesDuePeriod(param: Pageable, period: number) {

            this.url_sub_path = 'motor-policy/renewal_period_list/' + period;
            return this.post(param);

    }


    getPolicyById(policyId: number) {
        this.url_sub_path = 'motor-policy/' + policyId;
        return this.get();


    }

    generateDrNote(id: number) {
        this.url_sub_path = 'motor-policy/dr-note/' + id;
        return this.get(
            null,
            {
                responseType: 'blob'
            },
        );
    }

    generateCrNote(policyId: number) {
        this.url_sub_path = 'motor-policy/cr-note/' + policyId;
        return this.get(
            null,
            {
                responseType: 'blob'
            },
        );
    }

    editPolicy(policy: MotorPolicyRequest) {
        this.url_sub_path = 'motor-policy/edit';
        return this.post(policy);

    }
    renewPolicy(policy: MotorPolicyRequest) {
        this.url_sub_path = 'motor-policy/renew/'+ policy.id;
        return this.post(policy);

    }

    addDocuments(cibDocuments: CibDocument[] ,  policyId: number) {
        this.url_sub_path = 'motor-policy/documents/add/' + policyId;
        return this.post(cibDocuments);


    }
}
