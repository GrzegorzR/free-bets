import {MyBetDefComponent} from "./my-bet-def/my-bet-def.component";
import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }    from '@angular/forms';
import { HttpModule } from '@angular/http';

// used to create fake backend
import { MockBackend, MockConnection } from '@angular/http/testing';
import { BaseRequestOptions } from '@angular/http';

import { AppComponent }  from './app.component';
import { routing }        from './app.routing';

import { AlertComponent } from './_directives/index';
import { AuthGuard } from './_guards/index';
import { AlertService, AuthenticationService, UserService } from './_services/index';
import { HomeComponent } from './home/index';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import {AddBetDefComponent} from "./add-bet-def/add-bet-def.component";
import {BetDefinitionService} from "./_services/bet.def.service";
import {ActiveBetDefComponent} from "./active-bet-def/active-bet-def.component";
import {MyBetsComponent} from "./my-bets/my-bets.component";
import {HistoryBetDefComponent} from "./history-bet-def/history-bet-def.component";
import {BetService} from "./_services/bet.service";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        routing
    ],
    declarations: [
        AppComponent,
        AlertComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        AddBetDefComponent,
        ActiveBetDefComponent,
      MyBetDefComponent,
      MyBetsComponent,
      HistoryBetDefComponent
    ],
    providers: [
        AuthGuard,
        AlertService,
        AuthenticationService,
        UserService,
        BetDefinitionService,
      BetService,

        BaseRequestOptions
    ],
    bootstrap: [AppComponent]
})

export class AppModule { }
