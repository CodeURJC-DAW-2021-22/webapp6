export interface Tournament{
    id?: number;
    owner:string;
    tournamentName:string;
    numParticipants:number;
    numSignedUp:number;
    rounds:number;
    about:string;
    ruleset:string;
    location:string;
    inscriptionDate:string;
    startDate:string;
    started:boolean;
    imageFile:Blob;
    image:boolean;
    

}