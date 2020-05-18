import { BaseClass } from './base-class';
import { Movie } from './movie';

export class MovieMember extends BaseClass{
    name: String;
    role: String;
    relatedMovies: Movie[]
    
    constructor(){
        super();
    }
}
