import {ClientType} from "../../shared/api/shared.models";

export interface RetailClient {
    id: number;
    name: string;
    lastName?: string;
    permanentAddress: string;
    mailingAddress: string;
    telephoneNumber: string;
    faxNumber: string;
    nationality: string;
    identityNumber: string;
    occupationLineOfBusiness: string;
    sourceOfFunds: string;
    contactPerson: string;
    mobileNumber: string;
    email: string;
    clientType: ClientType;
    renewal: string;
    incorporationDate: string;

}

export interface ClientSanctionCheckRequest{
    firstName: string;
    lastName: string;
    referenceNumber: string;
    nationality: string;

}


export interface ClientSanctionCheckResponse{

    dataId: string;
    versionNum: string;
    firstName: string;
    lastName: string;
    unListType: string;
    referenceNumber: string;
    listedOn: string;
    comments: string;
    designations: any[];
    nationality : string;
    listType : string;
    lastDayUpdated : string;
    aliases : any[];
    addressCountry : string;
    dateOfBirthType : string;
    dateOfBirthYear : string;
    placeOfBirthCity : string;
    placeOfBirthCountry : string;
    documentNumbers : any[];

}
export interface Insurer {
    id: number;
    name: string;
    faxNumber: string;
    address: string;
    telephoneNumber: string;
    mobileNumber: string;
    email: string;
    incorporationDate: string;
    registrationNumber: string;

}

export interface BordreauxQueryDTO {
    periodFrom: string;
    periodTo: string;
    insurerId : number;
    currency: string;
    bordType: string ;
}
