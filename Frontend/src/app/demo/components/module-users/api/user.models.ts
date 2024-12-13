export interface User {

    id: number;
    enabled: boolean;
    firstName: string;
    lastName: string;
    email: string;
    mobile: string;
    nationalId: string;
    passwordChangedTime: string;
}

export interface UserCreateContext {

    id: number;
    firstName: string;
    lastName: string;
    email: string;
    mobile: string;
    nationalId: string;
    password: string;
    confirmPassword: string;
}

export  interface  ChangePasswordRequest {
    oldPassword: string;
    password: string;
    confirmPassword: string;
    userId: number;
}
