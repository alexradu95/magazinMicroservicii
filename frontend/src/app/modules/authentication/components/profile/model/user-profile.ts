import { Address } from "./address";
export class UserProfile { 

    customerId: number ;
    firstname: string ;
    lastname: string;
    email: string;
    phone: string;
    address: Address;

    constructor(customerId: number, firstname: string, lastname: string, 
        email: string,  phone: string, address: Address) {
        this.customerId = customerId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }



}