import {RetailClient} from "../../client/api/client.models";

export interface Pageable {
    page?: number;
    size?: number;
    globalFilter?: string;
}
export interface QueryParams {
    $filter: string;
    $orderby: string;
    $skip: string;
    $top: number;
}

export interface LoginRequest {
    username: string;
    password: string;
}

export interface SelectedFilters {
    period?: ValueLabel;
    currency?: string;
    month?: ValueLabel;
    year?: string;
    insured?: RetailClient;
    policyType?: PolicyName;
}
export interface FilterOptions {
    periods: ValueLabel[];
    currencies: string[];
    months: ValueLabel[];
    years: string[];
    insureds: RetailClient[];
    policyTypes: PolicyName[];
}

export interface PolicyAccountingData {
    policyCount: number;
    policyClaimCount: number;
    policyEndorsementsCount: number;
    totalSumInsured: number;
    totalLoss: number;
    totalEndorsements: number;
    renewalDates: RenewalDates;
    totalPremium: number;
    totalPremiumDue: number;
    totalPremiumPaid: number;
    totalPremiumOutstanding: number;
    totalClaims: number;
    totalClaimsPaid: number;
    totalClaimsOutstanding: number;

}
export interface RenewalDates {
    expired: number;
    due: number;
    dueIn7days: number;
    dueIn30Days: number;
    dueIn60Days: number;
    dueIn90Days: number;
    dueIn90PlusDays: number;

}

export interface PolicyName {
    id: number;
    policyName: string;
    policyDescription: string;
    policyCode: string;
    avatar: string;
    commissionRate: number ;
}

export enum Currency {
    USD = 'USD',
    EUR = 'EUR',
}

export enum CIBModuleType {
    CLAIM , POLICY ,
    ENDORSEMENT ,
    ACCOUNTING,
    RENEWAL,
}

export enum FilterType {
    REPORT ,
    LIST ,
}

export enum ClientType {
    INDIVIDUAL = 'INDIVIDUAL',
    CORPORATE = 'CORPORATE',

}

export interface ValueLabel  {
    value?: any;
    label?: string;
}



export interface NumberSeriesRequest {
    entityType : string;
}
export interface FileUploadDTO {
    details: FileUploadDetails[];
}
export interface FileUploadDetails {

    fileName: string;
    originalName: string;
    directory: string;



}

export  interface PassWordPolicy
{
    minimum_length: number;
    ambiguous_chars: boolean;
    lower_chars: boolean;
    upper_chars: boolean;
    include_numbers: boolean;
    include_symbols: boolean;
    expiration_days: number;
}

export interface CibDocument {
    fileName: string;
    originalName: string;
    id: number;
}

export interface CibPayment {
    id: number;
    createdAt: string;
    createdBy: string;
    periodFrom: string;
    periodTo: string;
    amountDue: number;
    paymentAmount: number;
    paymentMethod: string;
    paymentReference: string;
    paymentStatus: string;
    policy: PolicyName;
    policyId: number;
}

export interface Invoice {
    id: number;
    createdAt: string;
    createdBy: string;
    paymentDate: string;
    paymentAmount: number;
    paymentMethod: string;
    paymentReference: string;
    policyType: POLICY_TYPE;
    policyId: number;
    insurerId : number ;
}


export interface RenewalPeriods {
    due: number;
    dueIn7Days: number;
    dueIn30Days: number;
    dueIn60Days: number;
    dueIn90Days: number;
    dueIn90PlusDays: number;
    expired: number;
}


export enum POLICY_TYPE {
    MOTORPOLICY = 'MOTORPOLICY',
    ASSETALLRISK = 'ASSETALLRISK',
    HOMEPOLICY = 'HOMEPOLICY',
    ELECTRONICEQUIPMENT = 'ELECTRONICEQUIPMENT',
    CARRIERSLIABILITY = 'CARRIERSLIABILITY',
    UMBRELLALIABILITYPOLICY = 'UMBRELLALIABILITYPOLICY',
    PUBLICLIABILITY = 'PUBLICLIABILITY' ,
    GROUPPERSONALACCIDENT = 'GROUPPERSONALACCIDENT',
    EMPLOYERSLIABILITY = 'EMPLOYERSLIABILITY',
    DIRECTORSANDOFFICERSLIABILITY = 'DIRECTORSANDOFFICERSLIABILITY',
    WAREHOUSEMANLIABILITY = 'WAREHOUSEMANLIABILITY',
    TOBACCOTHROUGHPUT = 'TOBACCOTHROUGHPUT',
    LOANPROTECTION = 'LOANPROTECTION',
    FIDELITYGUARANTEE = 'FIDELITYGUARANTEE',
    MACHINERYBREAKDOWN = 'MACHINERYBREAKDOWN',
    MACHINERYBREAKDOWNLOSSOFPROFITS = 'MACHINERYBREAKDOWNLOSSOFPROFITS',
    MONEY = 'MONEY',
    DIRECTORSPERSONALACCIDENT = 'DIRECTORSPERSONALACCIDENT',
    PROFESSIONALINDEMNITY = 'PROFESSIONALINDEMNITY',
    TRAVEL = 'TRAVEL',
    POLITICALVIOLENCE = 'POLITICALVIOLENCE',
    MOTOREXCESSBUYBACK = 'MOTOREXCESSBUYBACK',
    BONDSANDGUARANTEES = 'BONDSANDGUARANTEES',
    MOTORPOLICYMOZAMBIQUE = 'MOTORPOLICYMOZAMBIQUE',
    PRODUCTLIABILITY = 'PRODUCTLIABILITY' ,




}



