import { Component ,OnInit} from '@angular/core';
import { C2isuserService } from '../service/c2isuser.service';
import { C2isUser } from '../model/c2isuser';

@Component({
  selector: 'app-c2isuser',
  templateUrl: './c2isuser.component.html',
  styleUrls: ['./c2isuser.component.css']
})
export class C2isuserComponent implements OnInit{

  c2isusers!: C2isUser[]

  constructor(
    private httpClientService:C2isuserService
  ) { }

  ngOnInit() {
    this.httpClientService.getUsers().subscribe(
     response =>this.handleSuccessfulResponse(response),
    );

   // console.log("Line 23 C2is user:"+sessionStorage.getItem('name'));
   
  // var obj=(JSON.parse(window.sessionStorage.getItem('name')));
   console.log("C2is user ID:"+sessionStorage.getItem('userid'));
   console.log("C2is user name:"+sessionStorage.getItem('username'));
   console.log("C2is user role:"+sessionStorage.getItem('userrole'));
   console.log("C2is user avator:"+sessionStorage.getItem('useravator'));

    // to initiali
  }

  handleSuccessfulResponse(response:any)
{
    this.c2isusers=response;
}

deleteUser(c2isuser: C2isUser): void {
  this.httpClientService.deleteUser(c2isuser)
    .subscribe( data => {
      this.c2isusers = this.c2isusers.filter(u => u !== c2isuser);
    })
};

}
