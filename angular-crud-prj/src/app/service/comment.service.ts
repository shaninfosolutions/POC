import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import { TrackChangeComment } from '../model/comment';
import { BehaviorSubject } from 'rxjs';


interface ChangeComment{
  
}

@Injectable({
  providedIn: 'root'
})



export class CommentService {


  private baseUrl = "http://localhost:8080/api/v1/comment";
  constructor(private httpClient:HttpClient) { }


  getTrackChangeComments():Observable<TrackChangeComment[]>
  {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.get<TrackChangeComment[]>(this.baseUrl,{headers});
  }

  getTrackChangeComment(id: number)
  {
    let username='javainuse'
    let password='password'
    console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.get<TrackChangeComment>(this.baseUrl+ "/"+ id,{headers});
  }

  getTrackChangeComByRecordingId(id: number):Observable<TrackChangeComment[]>
  {
    let username='javainuse'
    let password='password'
    console.log("getTrackChangeComByRecordingId Call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.get<TrackChangeComment[]>(this.baseUrl+ "/recordingid/"+ id,{headers});
  }

  getTrackChangeComListByStatementId(id: number):Observable<any>
  {
    let username='javainuse'
    let password='password'
    console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.get<any>(this.baseUrl+ "/commentchangelist/"+ id,{headers}).pipe(map(data=>data));

    //  return this.http.get(this.url2).pipe(map(countries => countries.map(country => country.name);

  }

  public createTrackChangeComment(trackChangeComment:TrackChangeComment) :Observable<TrackChangeComment> {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.post<TrackChangeComment>(this.baseUrl, trackChangeComment,{headers});
  }

  public updateTrackChangeComment(id: string,trackChangeComment:TrackChangeComment) :Observable<TrackChangeComment> {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.put<TrackChangeComment>(this.baseUrl+ "/"+id, trackChangeComment,{headers});
  }

  public deleteStatement(trackChangeComment:TrackChangeComment) {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.delete<TrackChangeComment>(this.baseUrl + "/"+ trackChangeComment.id,{headers});
  }

  public updateByCommentThreadId(id:string,threadid: string,trackChangeSuggestion:TrackChangeComment) :Observable<TrackChangeComment> {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    //console.log("Line 36 "+ trackChangeSuggestion);
    headers.set('Content-Type','application/json;charset=utf-8');
   return  this.httpClient.put<TrackChangeComment>(this.baseUrl+ "/updated/"+id+"/"+threadid, trackChangeSuggestion,{headers});
  }




}