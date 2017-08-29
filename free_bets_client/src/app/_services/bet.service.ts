

import { Injectable } from '@angular/core';
import {Headers, Http, RequestOptions, Response} from '@angular/http';
import { URLSearchParams } from '@angular/http';
import {AppSettings} from "../app.consts";

@Injectable()
export class BetService {
  constructor(private http: Http) { }



  create(betDef: any) {
    let body = new URLSearchParams();
    body.append('option', betDef.option);
    body.append('amount', betDef.amount);
    body.append('betDefinitionId', betDef.betDefId);
    return this.http.post(AppSettings.API_ENDPOINT+'/bet/add', body, this.jwt() ).map(res => res.text());
  }

  getCurrentUsersBets(){
    return this.http.get(AppSettings.API_ENDPOINT + '/bet/my', this.jwt()).map((response: Response) => response.json());
  }


  private jwt() {

    const headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded'});
    return new RequestOptions({ headers: headers, withCredentials: true });

  }
}
/**
 * Created by gr on 28.08.17.
 */
