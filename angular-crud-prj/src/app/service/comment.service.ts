import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import { TrackChangeComment } from '../model/comment';
import { BehaviorSubject } from 'rxjs';
import { AnyCatcher } from 'rxjs/internal/AnyCatcher';
import { DatePipe } from '@angular/common'


interface ChangeComment{
    commentId:string,
    content:string,
    authorId:string,
    createdAt:Date
}

@Injectable({
  providedIn: 'root'
})



export class CommentService {


  private baseUrl = "http://localhost:8080/api/v1/comment";
  constructor(private httpClient:HttpClient,public datepipe: DatePipe) { }


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

  getChangeCommentListByThreadId(id: string):Observable<any>
  {
    let username='javainuse'
    let password='password'
    console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.get<any>(this.baseUrl+ "/threadid/"+ id,{headers}).pipe(
      map(response => {
        console.log("line 76 in service : " +JSON.stringify(response));
        let list: any[] = [];
        for (const c of response.comments){
          console.log("line 79 : testing comment "+ c.commentId)
          console.log("line 79 : testing comment "+ c.content)
          let obj={commentId:String,
                    content:String,
                    authorId:String,
                    createdAt:new Date};
          obj.commentId=c.commentId;
          obj.content=c.content;
          obj.authorId=c.authorId;
          if(c.createdAt===null || c.createdAt===undefined ){
            console.log("The CratedAt Date is null : line 93 ");
          }else{
          obj.createdAt=new Date(c.createdAt );
          //console.log("Line 97 " + obj.createdAt.toDateString());

         // let latest_date =this.datepipe.transform(obj.createdAt, 'yyyy-MM-dd HH:MM:');
          //console.log("Line 97 " + latest_date);
          }
          list.push(obj);          
        }
       // console.log("Comments : "+JSON.stringify(comments));
        return {
          threadId:response.threadId,
          comments:list
        }
      })
    );
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