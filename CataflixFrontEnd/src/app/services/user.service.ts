import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

const API_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getPublicContent(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'movies', { responseType: 'text' });
  }

  getUserByEmail(email: String) {
    return this.http.get<User>(API_URL + 'users/email/' + email);
  }

  updateUser(user: User) {
    return this.http.put(API_URL+'users/' + user.id, user);
  }

  upgradeUser(user: User) {
    return this.http.put(API_URL+'users/upgrade/' + user.id, user);
  }

}