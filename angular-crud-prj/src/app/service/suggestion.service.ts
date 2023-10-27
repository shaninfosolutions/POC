import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';
import { map } from 'rxjs/operators';
import { TrackChangeSuggestion } from '../model/suggestion';
import { BehaviorSubject } from 'rxjs';


interface TrackChangSuggestion{
   id:string;
   type:string;
   authorId:string;
   createAt:Date;
   hasComments:boolean;
   data:any
}


@Injectable({
  providedIn: 'root'
})
export class SuggestionService {

  private dataSubject = new BehaviorSubject<any[]>([]);
  data$ = this.dataSubject.asObservable();

  
  private baseUrl = "http://localhost:8080/api/v1/trackchange";
  constructor(private httpClient:HttpClient) {
    //this.fetchData()
   }

 

  getTrackChangeSuggestions():Observable<TrackChangeSuggestion[]>
  {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.get<TrackChangeSuggestion[]>(this.baseUrl,{headers});
  }

  getTesting(id: number){
    console.log("It is here"+id);
  }

  getTrackChangeSuggestion(id: number)
  {
    let username='javainuse'
    let password='password'
    console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.get<TrackChangeSuggestion>(this.baseUrl+ "/"+ id,{headers});
  }

  
  getTrackChangeSugByRecordingId(id: number):Observable<TrackChangeSuggestion[]>
  {
    let username='javainuse'
    let password='password'
   // console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.get<TrackChangeSuggestion[]>(this.baseUrl+ "/recordingid/"+ id,{headers});
  }

  getTrackChangeListByStatementId(id: number):Observable<any>
  {
    let username='javainuse'
    let password='password'
    console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.get<any>(this.baseUrl+ "/trackchangelist/"+ id,{headers});
  }

  getTrackChangeListByThreadId(threadId: string):Observable<any>
  {
    let username='javainuse'
    let password='password'
    console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.get<any>(this.baseUrl+ "/threadid/"+ threadId,{headers}).pipe(
      map(response => {

        let createDate:any;
        if(response.createdAt===null || response.createdAt===undefined){
          console.log("The creation Date is null");
          createDate=null;
        }else{
          createDate=new Date(response.createdAt);
        }

        return {
          id: response.id,
          type: response.type,
          authorId: response.authorId,
          hasComments: response.hasComments,
          createdAt: createDate,
          data: response.data
        }
      })
    );
  }

  

  getTrackChangeMap(id: number):Observable<any>
  {
    let username='javainuse'
    let password='password'
    console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
   // return this.httpClient.get<any>(this.baseUrl+ "/trackchangelist/"+ id,{headers});
    return this.httpClient.get(this.baseUrl+ "/trackchangelist/"+ id,{headers}).pipe(
      map((data) => data )
    )
  }

  /*
  fetchData(id?:number) {
    let username='javainuse'
    let password='password'
    console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
    console.log("the fetch data")
    this.httpClient.get<any[]>(this.baseUrl+ "/trackchangelist/"+ id,{headers}).subscribe(data => {
      this.dataSubject.next(data);
    });

  }*/
  // map((data) => response as resp[] || [])
  



  getTrackChangeByRecordingId(id: number):Promise<TrackChangeSuggestion[]>
  {
    let username='javainuse'
    let password='password'
    console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.get<TrackChangeSuggestion[]>(this.baseUrl+ "/recordingid/"+ id,{headers}).toPromise();
  }

  public createTrackChangeSuggestion(trackChangeSuggestion:TrackChangeSuggestion) :Observable<TrackChangeSuggestion> {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    console.log("Line 36 "+ trackChangeSuggestion);
    return this.httpClient.post<TrackChangeSuggestion>(this.baseUrl, trackChangeSuggestion,{headers});
  }

  public updateTrackChangeSuggestion(id: any,trackChangeSuggestion:TrackChangeSuggestion) :Observable<TrackChangeSuggestion> {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
   // console.log("Line 36 "+ trackChangeSuggestion);
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.put<TrackChangeSuggestion>(this.baseUrl+ "/"+id, trackChangeSuggestion,{headers});
  }


  public updateTrackChangeSugByThreadId(threadid: string,trackChangeSuggestion:TrackChangeSuggestion) :Observable<TrackChangeSuggestion> {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    //console.log("Line 36 "+ trackChangeSuggestion);
    headers.set('Content-Type','application/json;charset=utf-8');
    return this.httpClient.put<TrackChangeSuggestion>(this.baseUrl+ "/threadid/"+threadid, trackChangeSuggestion,{headers});
  }


  public updateByRecordingAndThreadId(id:string,threadid: string,trackChangeSuggestion:TrackChangeSuggestion) :Observable<TrackChangeSuggestion> {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    //console.log("Line 36 "+ trackChangeSuggestion);
    headers.set('Content-Type','application/json;charset=utf-8');
   return  this.httpClient.put<TrackChangeSuggestion>(this.baseUrl+ "/updated/"+id+"/"+threadid, trackChangeSuggestion,{headers});
  }

  public deleteStatement(trackChangeSuggestion:TrackChangeSuggestion) {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.delete<TrackChangeSuggestion>(this.baseUrl + "/"+ trackChangeSuggestion.id,{headers});
  }


}
