import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable} from 'rxjs';
import { map } from 'rxjs/operators';
import { TrackChangeSuggestion } from '../model/suggestion';
import { BehaviorSubject } from 'rxjs';

import { AnnexFile } from '../model/annexs';

@Injectable({
  providedIn: 'root'
})
export class AnnexService {

  private baseUrl = "http://localhost:8080/api/v1/annex";

  constructor(private httpClient:HttpClient) { }

  public createAnnex(annex:string,annexNo:string,recordingId:string,
    description:string) {
      let username='javainuse'
      let password='password'
     // console.log("test call");
      const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
     
      const formData = new FormData();
      formData.append('annex',annex );
      formData.append('annexNo',annexNo);
      formData.append('recordingId',recordingId);
      formData.append('description',description);
    return this.httpClient.post<AnnexFile>(this.baseUrl+"/uploadFile", formData,{headers});
  }
}
