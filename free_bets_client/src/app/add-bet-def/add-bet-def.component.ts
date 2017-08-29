import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AlertService, AuthenticationService } from '../_services/index';
import {BetDefinitionService} from "../_services/bet.def.service";
import {AbstractComponent} from "../AbstractComponent";

@Component({
    moduleId: module.id.toString(),
    templateUrl: 'add-bet-def.component.html'
})

export class AddBetDefComponent extends AbstractComponent implements OnInit{

  user: any;

  ngOnInit(): void {
    this.checkSession();
    this.authService.getCurrentUser().subscribe(
      data => {
        this.user = data;
      },
      error => {
        this.alertService.error(error);
      });
  }


  model: any = {};
    loading = false;

    constructor(
        public route: ActivatedRoute,
        public router: Router,
        private betDefService: BetDefinitionService,
        private alertService: AlertService,
        public authService: AuthenticationService) {
      super(route, router, authService);
    }



    addBetDef(){
      this.loading = true;



      this.betDefService.
      create(this.model).subscribe(
        data => {
          this.router.navigate(["my-bet-def"]);
          this.alertService.success("Bet definition added.")
        },
        error => {

          this.alertService.error(error);
        });

    }

}
