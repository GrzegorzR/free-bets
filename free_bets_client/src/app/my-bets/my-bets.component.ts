import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AlertService, AuthenticationService } from '../_services/index';
import {BetDefinitionService} from "../_services/bet.def.service";
import {UserService} from "../_services/user.service";
import {AbstractComponent} from "../AbstractComponent";
import {BetService} from "../_services/bet.service";

@Component({
  moduleId: module.id.toString(),
  templateUrl: 'my-bets.component.html'
})

export class MyBetsComponent extends AbstractComponent implements OnInit{
  selected = false;
  selectedDef: any;
  user :any;
  arr: any = [];


  constructor(
    public route: ActivatedRoute,
    public router: Router,
    private alertService: AlertService,
    private betService: BetService,
    public authService: AuthenticationService) {
    super(route, router, authService);
  }

  ngOnInit(): void {
    this.authService.getCurrentUser().subscribe(
      data => {
        this.user = data;
      },
      error => {
        this.alertService.error(error);
      });

    this.betService.getCurrentUsersBets().subscribe(
      data => {
        console.log(data);
        this.arr = data;
      },
      error =>{
        this.alertService.error(error);
      });
  }


  setClickedRow(i: any){
    this.selected = true;
    this.selectedDef = i;
  }

}
