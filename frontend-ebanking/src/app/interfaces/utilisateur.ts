export class User {
    id: number;
    nom: string;
    prenom: string;
    cin: string;
    adresse: string;
    telephone: string;
    email: string;
    username: string;
    password: string;
    role: string;
    constructor(username:string,email:string,nom:string,prenom:string,adresse:string,password:string,cin:string,telephone:string){
            this.username = username;
            this.email = email;
            this.nom = nom;
            this.prenom = prenom;
            this.adresse = adresse;
            this.password = password;
            this.cin = cin;
            this.telephone = telephone; 
    }
}
