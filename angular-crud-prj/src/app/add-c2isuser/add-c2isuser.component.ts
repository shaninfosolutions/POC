import { Component,OnInit } from '@angular/core';
import { C2isuserService } from '../service/c2isuser.service';
import { Router } from '@angular/router';
import { C2isUser } from '../model/c2isuser';

@Component({
  selector: 'app-add-c2isuser',
  templateUrl: './add-c2isuser.component.html',
  styleUrls: ['./add-c2isuser.component.css']
})
export class AddC2isuserComponent implements OnInit {

  user: C2isUser = new C2isUser("","","","","","");

  constructor(
    private httpClientService: C2isuserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  createUser(): void {
    this.httpClientService.createC2isUser(this.user)
        .subscribe( data => {
          alert("User created successfully.");
          this.redirectToUserList() ;
        });

  };

  redirectToUserList() {
    this.router.navigate(['/']);
  }

}
