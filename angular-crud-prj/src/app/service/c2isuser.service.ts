import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { C2isUser } from '../model/c2isuser';


@Injectable({
  providedIn: 'root'
})
export class C2isuserService {
  
  private baseUrl = "http://localhost:8080/api/v1/users";


  constructor(private httpClient:HttpClient) { }

     getUsers()
  {
    //let username='javainuse'
   // let password='password'
    console.log("test call");
    //const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.get<C2isUser[]>(this.baseUrl);
  }

  public deleteUser(c2isuser:C2isUser) {
   // const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.delete<C2isUser>("http://localhost:8080/api/v1/users" + "/"+ c2isuser.id);
  }

  public createUser(c2isuser:C2isUser) {
    //const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.post<C2isUser>("http://localhost:8080/api/v1/users", C2isUser);
  }

  public createC2isUser(c2isuser: C2isUser): Observable<Object> {
   // const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.post(`${this.baseUrl}`, c2isuser);
  }
/*
  getUsers(): Observable<C2isUser[]>{
    return this.httpClient.get<C2isUser[]>(`${this.baseUrl}`);
  }

  getUserList(): Observable<C2isUser[]> {
    return this.httpClient.get<C2isUser[]>(`${this.baseUrl}`);
  }

  createUser(c2isuser: C2isUser): Observable<Object> {
    return this.httpClient.post(`${this.baseUrl}`, c2isuser);
  }

  getUserById(id: number): Observable<C2isUser>{
    return this.httpClient.get<C2isUser>(`${this.baseUrl}/${id}`);
  }

  updateUser(id:number, c2isuser:C2isUser): Observable<Object>{
    return this.httpClient.put(`${this.baseUrl}/${id}`, c2isuser);
  }

  deleteUser(id:number): Observable<Object>{
    return this.httpClient.delete(`${this.baseUrl}/${id}`);
  }
*/
  
}
