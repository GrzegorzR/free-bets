





import {ActivatedRoute, Router} from "@angular/router";
import {AuthenticationService} from "./_services/authentication.service";
export class AbstractComponent {


  constructor(    public route: ActivatedRoute,
                  public router: Router,
                  public authService : AuthenticationService) {
  }

  public checkSession(){
    this.authService.checkSession().subscribe(
      data => {

      },
      error => {
        localStorage.removeItem('currentUser');
        this.router.navigate(['/login']);
      });


  }


  public logout(){
    this.authService.logout()
      .subscribe(
        data => {
          this.router.navigate(["/login"]);
          localStorage.removeItem('currentUser');
        },
        error => {

        });
    console.log("logout");

  }
}
