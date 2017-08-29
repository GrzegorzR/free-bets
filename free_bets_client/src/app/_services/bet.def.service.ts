import { Injectable } from '@angular/core';
import {Headers, Http, RequestOptions, Response} from '@angular/http';
import { URLSearchParams } from '@angular/http';
import {AppSettings} from "../app.consts";

@Injectable()
export class BetDefinitionService {
  constructor(private http: Http) { }



  create(betDef: any) {
    let body = new URLSearchParams();
    body.append('description', betDef.description);
    body.append('optionOne', betDef.optionOne);
    body.append('optionTwo', betDef.optionTwo);
    console.log(body);
    return this.http.post(AppSettings.API_ENDPOINT+'/bet-definition/add', body, this.jwt() )
      .map((response: Response) => response.json());
    //return this.http.get('http://localhost:9080/free_bets-1/api/hello')
  }

  getActiveBetDef(){
    let body = new URLSearchParams();
    return this.http.get(AppSettings.API_ENDPOINT+'/bet-definition/get-active',  this.jwt() )
      .map((response: Response) => response.json());
  }

  getInactiveBetDef(){
    return this.http.get(AppSettings.API_ENDPOINT+'/bet-definition/get-inactive',  this.jwt() )
      .map((response: Response) => response.json());
  }


  resolve(betDef: any){
    let body = new URLSearchParams();
    body.append('betDefinitionId', betDef.betDefId);
    body.append('option', betDef.option);
    return this.http.post(AppSettings.API_ENDPOINT+'/bet-definition/resolve', body, this.jwt() );
  }

  // private helper methods

  private jwt() {

    const headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded'});
    return new RequestOptions({ headers: headers, withCredentials: true });

  }

  getMyBetDefs() {
    return this.http.get(AppSettings.API_ENDPOINT+'/bet-definition/my',  this.jwt() )
      .map((response: Response) => response.json());
  }
}
