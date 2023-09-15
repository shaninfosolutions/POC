import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = 'javainuse'
  password = ''
  invalidLogin = false

  constructor(private router: Router,
    private loginservice: AuthenticationService) { }

  ngOnInit() {
  }

  checkLogin() {
    /*
    if (this.loginservice.authenticate(this.username, this.password)
    ) {
      this.router.navigate([''])
      this.invalidLogin = false
    } else
      this.invalidLogin = true
  */

      (this.loginservice.authenticate(this.username, this.password).subscribe(
        data => {
          console.log("Authenticate True");
          this.router.navigate([''])
          this.invalidLogin = false
          sessionStorage.setItem('loginsuccess','success');
          console.log("Login success "+sessionStorage.getItem('username'));
        },
        error => {
          console.log("Authenticate [false]");
          this.invalidLogin = true
          
        }
      )
      );
    }

   
}
