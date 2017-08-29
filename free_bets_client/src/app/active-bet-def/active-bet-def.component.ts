import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AlertService, AuthenticationService } from '../_services/index';
import {BetDefinitionService} from "../_services/bet.def.service";
import {UserService} from "../_services/user.service";
import {AbstractComponent} from "../AbstractComponent";
import {BetService} from "../_services/bet.service";

@Component({
  moduleId: module.id.toString(),
  templateUrl: 'active-bet-def.component.html'
})

export class ActiveBetDefComponent extends AbstractComponent implements OnInit {
  arr: any = [];
  selected = false;
  selectedDef: any;
  user :any;
  constructor(
    public route: ActivatedRoute,
    public router: Router,
    public authService: AuthenticationService,
    private betService: BetService,
    private betDefService: BetDefinitionService,
    private alertService: AlertService,

  ) {
    super(route,router,authService);
  }
  model: any = {};




  loading = false;

  ngOnInit(): void {
    this.checkSession();
     this.betDefService.getActiveBetDef().subscribe(
       data => {
         this.arr = data;
       },
       error => {
         this.alertService.error(error);
         this.loading = false;
       });

     this.authService.getCurrentUser().subscribe(
       data => {
         this.user = data;
       },
       error => {
         this.alertService.error(error);
       });
  }


  setClickedRow(i: any){
    this.selected = true;
    this.selectedDef = i;

  }

  makeBet(){

    this.model.betDefId = this.selectedDef.id;

    this.betService.create(this.model).subscribe(
      data => {
        this.router.navigate(["my-bets"]);
        this.alertService.success("Bet added.")
      },
      error => {
        this.alertService.error(error._body);
        this.loading = false;
      });
  }

}

