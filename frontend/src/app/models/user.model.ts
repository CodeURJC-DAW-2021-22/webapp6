export interface User{
    id?:number;
    name: string;
    email: string;
    realName:string;
    location:string;
    country:string;
    phone:string;
    numWins:number;
    numLoses:number;
    numMatchesPlayed:number;
    historicalKarma: number[];
    status:boolean;
    imageFile: Blob;
    image:boolean;
    encodedPassword:string;
    roles: string[];

}
