import { Component, OnInit, AfterViewInit } from '@angular/core';

import { User } from '../_models/index';
import { UserService } from '../_services/index';
import {AuthenticationService} from "../_services/authentication.service";
import {ActivatedRoute, Router} from "@angular/router";
import {AlertService} from "../_services/alert.service";
import {AbstractComponent} from "../AbstractComponent";

@Component({
    moduleId: module.id.toString(),
    templateUrl: 'home.component.html'
})

export class HomeComponent extends AbstractComponent implements OnInit {

    user: any;

  constructor(
    public route: ActivatedRoute,
    public router: Router,
    private alertService: AlertService,
    public authService: AuthenticationService) {
    super(route, router, authService);
  }

    ngOnInit() {
    this.checkSession();
      this.authService.getCurrentUser().subscribe(
        data => {
          this.user = data;
          console.log(this.user);
        },
        error => {
          this.alertService.error(error);
        });

    }




}
