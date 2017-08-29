import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { URLSearchParams } from '@angular/http';
import { User } from '../_models/index';
import {AppSettings} from "../app.consts";

@Injectable()
export class UserService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get('/api/users', this.jwt()).map((response: Response) => response.json());
    }

    getById(id: number) {
        return this.http.get('/api/users/' + id, this.jwt()).map((response: Response) => response.json());
    }

    create(user: User) {
        let body = new URLSearchParams();
        body.append('login', user.login);
        body.append('password', user.password);
        return this.http.post(AppSettings.API_ENDPOINT+'/registration', body, this.jwt()).map((response: Response) => response.json());
    }

    update(user: User) {
        return this.http.put('/api/users/' + user.id, user, this.jwt()).map((response: Response) => response.json());
    }

    delete(id: number) {
        return this.http.delete('/api/users/' + id, this.jwt()).map((response: Response) => response.json());
    }

    // private helper methods

    private jwt() {
        // create authorization header with jwt token

      const headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded'});
      return new RequestOptions({ headers: headers, withCredentials: true });


    }


}
