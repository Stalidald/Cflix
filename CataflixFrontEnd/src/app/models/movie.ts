import { BaseClass } from './base-class';
import { MovieMember } from './movie-member';

export class Movie extends BaseClass{
    title: String;
    category: String;
    price: number;
    imageURL: String;
    videoURL: String;
    ageLimit: number;
    releaseYear: number;
    description: String;
    rating: number;
    relatedMovieMembers: MovieMember[]

    constructor(){
        super();
    }
}
