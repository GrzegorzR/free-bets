import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AlertService, AuthenticationService } from '../_services/index';
import {BetDefinitionService} from "../_services/bet.def.service";
import {UserService} from "../_services/user.service";
import {AbstractComponent} from "../AbstractComponent";
import {BetService} from "../_services/bet.service";

@Component({
  moduleId: module.id.toString(),
  templateUrl: 'history-bet-def.component.html'
})

export class HistoryBetDefComponent extends AbstractComponent implements OnInit{
  arr: any = [];
  selected = false;
  selectedDef: any;
  user :any;
  constructor(
    public route: ActivatedRoute,
    public router: Router,
    public authService: AuthenticationService,
    private betDefService: BetDefinitionService,
    private alertService: AlertService,

  ) {
    super(route,router,authService);
  }
  model: any = {};




  loading = false;

  ngOnInit(): void {
    this.checkSession();
    this.betDefService.getInactiveBetDef().subscribe(
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

}
