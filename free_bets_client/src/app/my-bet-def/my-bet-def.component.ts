import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AlertService, AuthenticationService } from '../_services/index';
import {BetDefinitionService} from "../_services/bet.def.service";
import {UserService} from "../_services/user.service";
import {AbstractComponent} from "../AbstractComponent";

@Component({
  moduleId: module.id.toString(),
  templateUrl: 'my-bet-def.component.html'
})

export class MyBetDefComponent extends AbstractComponent implements OnInit{
  selected = false;
  selectedDef: any;
  user :any;
  arr: any = [];


  loading = false;
  constructor(
    public route: ActivatedRoute,
    public router: Router,
    private alertService: AlertService,
    private betDefService: BetDefinitionService,
    public authService: AuthenticationService) {
    super(route, router, authService);
  }
  model: any = {};





  ngOnInit(): void {
    this.checkSession();
    this.betDefService.getMyBetDefs().subscribe(
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

  resolveBet(){
    this.model.betDefId = this.selectedDef.id;

    this.betDefService.resolve(this.model).subscribe(
      data => {
        console.log(data);
        this.router.navigate(["historical"]);
        this.alertService.success("Bet resolved.")
      },
      error => {
        this.alertService.error(error);
        this.loading = false;
      });

  }


}
