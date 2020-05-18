import { Movie } from './movie';
import { Achivement } from './achivement';
import { BaseClass } from './base-class';

export class User extends BaseClass{
    name: String;
    email: String;
    userName: String;
    password: String;
    type: String;
    ageLimit: number;
    creditCardNumber: String;
    cvc: number;
    expireDate: String;
    balance: number;
    ownedMovies: Movie[];
    rentedMovies: Movie[];
    unlockedAchivements: Achivement[];
    wishList: Movie[]
    relatives: User[]
    roles: String[];
    
    constructor(){
        super();
    }
}
