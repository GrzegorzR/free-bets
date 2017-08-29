import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/index';
import { LoginComponent } from './login/index';
import { RegisterComponent } from './register/index';
import { AuthGuard } from './_guards/index';
import {AddBetDefComponent} from "./add-bet-def/add-bet-def.component";
import {ActiveBetDefComponent} from "./active-bet-def/active-bet-def.component";
import {MyBetDefComponent} from "./my-bet-def/my-bet-def.component";
import {MyBetsComponent} from "./my-bets/my-bets.component";
import {HistoryBetDefComponent} from "./history-bet-def/history-bet-def.component";

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'add-bet-def', component: AddBetDefComponent },
    { path: 'active', component: ActiveBetDefComponent },
    {path: 'my-bet-def', component: MyBetDefComponent},
    {path: 'my-bets', component: MyBetsComponent},
    {path: 'historical', component: HistoryBetDefComponent},


  // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);
