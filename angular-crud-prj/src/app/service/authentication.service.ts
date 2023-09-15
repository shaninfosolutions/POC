import { Injectable } from '@angular/core';
import { HttpclientService } from './httpclient.service';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';

export class User{
  constructor(
    public status:string,
    public userId:string,
    public name:string,
    public avator:string,
    public role:string,
    public channel:string,
     ) {}
  
}

export class JwtResponse{
  constructor(
    public jwttoken:string,
     ) {}
  
}

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor( private httpClient:HttpClient) { }

  authenticate(username:any, password:any) {
    /*if (username === "javainuse" && password === "password") {
      sessionStorage.setItem('username', username)
      return true;
    } else {
      return false;
    }*/
    console.log(username);
    console.log(password);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    
    //http://localhost:8080/employees/validateLogin
    //http://localhost:8080/employees/api/v1/users/validateLogin
    return this.httpClient.get<User>('http://localhost:8080/api/v1/users/validateLogin',{headers}).pipe(
     map(
       userData => {
        sessionStorage.setItem('username',userData.name);
        sessionStorage.setItem('userid',userData.userId);
        sessionStorage.setItem('useravator',userData.avator);
        sessionStorage.setItem('userrole',userData.role);
        sessionStorage.setItem('channel',userData.channel);
        
        //let tokenStr= 'Bearer '+userData.token;
        let authString = 'Basic ' + btoa(username + ':' + password);
        sessionStorage.setItem('basicauth', authString);
        return userData;
       }
     )

    );


  }

  /*authenticate(username, password) {
    console.log(username);
    console.log(password);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.get<User>('http://localhost:8080/employees/validateLogin', { headers }).pipe(
      map(
        userData => {
          sessionStorage.setItem('username', username);
          let authString = 'Basic ' + btoa(username + ':' + password);
          sessionStorage.setItem('basicauth', authString);
          return userData;
        }
      )

    );
  }*/

  isUserLoggedIn() {
    let user = sessionStorage.getItem('username')
    console.log(!(user === null))

    if(!(user==null)){
     
    }
    return !(user === null)
  }

  logOut() {
    sessionStorage.removeItem('username')
  }
}
