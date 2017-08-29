import { Injectable } from '@angular/core';
import {Http, Headers, Response, RequestOptions} from '@angular/http';
import { URLSearchParams } from '@angular/http';
import 'rxjs/add/operator/map'
import {AppSettings} from "../app.consts";
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuthenticationService {
    constructor(private http: Http) { }

    login(username: string, password: string) {
        let body = new URLSearchParams();
        body.append('login', username);
        body.append('password', password);
        console.log(body);
        return this.http.post(AppSettings.API_ENDPOINT+'/login', body, this.jwt())
            .map((response: Response) => {
                localStorage.setItem('currentUser', JSON.stringify(username));

            });
    }

    getCurrentUser(){
      return this.http.get(AppSettings.API_ENDPOINT+'/user/current',this.jwt())
        .map((response: Response) => response.json());

    }
    checkSession() {
           return this.http.get(AppSettings.API_ENDPOINT + '/is-logged', this.jwt());
    }

    logout() {
       return this.http.get(AppSettings.API_ENDPOINT+'/logout').map((response: Response) => {
        });
    }

    private jwt() {
        // create authorization header with jwt token

      const headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded'});
      return new RequestOptions({ headers: headers, withCredentials: true });


    }


}
