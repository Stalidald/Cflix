import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Movie } from '../models/movie';

const API_URL = 'http://localhost:8080/movies';

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  constructor(private http: HttpClient) { }

  getMovies() {
    return this.http.get<Movie[]>(API_URL);
  }

  getMovieById(id: Number) {
    return this.http.get<Movie>(API_URL + '/' + id);
  }
}