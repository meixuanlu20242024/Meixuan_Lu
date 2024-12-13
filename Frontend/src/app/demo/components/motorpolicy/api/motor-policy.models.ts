
import {Validators} from "@angular/forms";
import {dateRangeValidator} from "../../../utils/custom-validators";
import {Insurer, RetailClient} from "../../client/api/client.models";
import {CibDocument} from "../../shared/api/shared.models";

export interface MotorPolicy {

    id: number;
    createdAt: string;
    createdBy: string;
    policyStatus: string;
    insured: RetailClient;
    insurer: Insurer;
    currency: string;
    fleetIndividual: string;
    periodFrom: string;
    periodTo: string;
    policyClaim: any[];
    policyEndorsements: any[];
    motorPolicyDocuments: MotorPolicyDocument[];
    policyNo: string;
    tmpVehicles: string;
    coverType: string;
    sumInsured: number;
    premium: number;
    rate: number;
    stampDuty: number;
    governmentLevy: number;
    motorPolicyRenewals: any[];
}
export interface MotorPolicyRequest {

    id: number;
    policyStatus: string;
    insured: RetailClient;
    insurer: Insurer;
    currency: string;
    fleetIndividual: string;
    periodFrom: string;
    periodTo: string;
    motorPolicyDocuments: File[];
    policyNo: string;
    coverType: string;
    sumInsured: number;
    premium: number;
    rate: number;
    stampDuty: number;
    governmentLevy: number;
    motorPolicyRenewals: any[];
}

export interface MotorPolicyDocument {

    id: number;
    fileName: string;
    originalName: string;
    motorPolicyEnumDocumentType: string;
}

export interface MotorPolicyClaim {
    motorPolicyClaimDocuments: CibDocument[];
    dateOfLoss: string;
    insured: RetailClient;
    insuredId: number;
    notificationDate: string;
    timeOfLoss: string;
    lossAmount: number;
    claimNumber: string;
    lossDetails: string;
    createdAt: string;
    createdBy: string;
    claimStatus: string;
    id: number;
}

