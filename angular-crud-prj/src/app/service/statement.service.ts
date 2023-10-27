import { Injectable } from '@angular/core';
import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { C2isStatement } from '../model/statement';
import { AnnexFile } from '../model/annex';
import { StatementPdf,DigitalStatementPdf } from '../model/statementpdf';


@Injectable({
  providedIn: 'root'
})
export class StatementService {

  private baseUrl = "http://localhost:8080/api/v1/statement";

  private baseUrl_1="http://localhost:8080/api/v1/annex"

  constructor(private httpClient:HttpClient) { }

  getStatements():Observable<C2isStatement[]>
  {
    
    return this.httpClient.get<C2isStatement[]>(this.baseUrl);
  }

  getAll():Promise<C2isStatement[]>
  {
    
    return this.httpClient.get<C2isStatement[]>(this.baseUrl,).toPromise();
  }

  getStatement(id: number)
  {
    
    return this.httpClient.get<C2isStatement>(this.baseUrl+ "/"+ id,);
  }

  public createStatement(c2statement:C2isStatement) {
   
    return this.httpClient.post<C2isStatement>(this.baseUrl, c2statement,);
  }

  public deleteStatement(c2statement:C2isStatement) {
    return this.httpClient.delete<C2isStatement>(this.baseUrl + "/"+ c2statement.id);
  }

  public updateStatement(id: number,c2statement:C2isStatement){
   // return this.httpClient.put<C2isStatement>(this.baseUrl + "/"+ id,c2statement);
    return this.httpClient.put<C2isStatement>(`${this.baseUrl}/${id}`, c2statement);
  }

  public getAnnexsByRecordingId(recordingid:number):Observable<AnnexFile[]>{
    let username='javainuse'
      let password='password'
      console.log("Line 55 to call the get Annex:" +recordingid);
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
     
    return this.httpClient.get<AnnexFile[]>("http://localhost:8080/api/v1/annex/recordingid/"+recordingid);
  
  }

  

  public createAnnex(formData: FormData,annex:string,annexNo:string,recordingId:string,
    description:string) {
      let username='javainuse'
      let password='password'
     // console.log("test call");
      const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
     
      //const formData = new FormData();
      formData.append('annex',annex );
      formData.append('annexNo',annexNo);
      formData.append('recordingId',recordingId);
      formData.append('description',description);
    return this.httpClient.post<AnnexFile>(this.baseUrl_1+"/uploadFile", formData,{headers});
  }

  public createStatementPdf(formData: FormData,filepath:string,recordingId:string) {
      let username='javainuse'
      let password='password'
     // console.log("test call");
      const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
     
      //const formData = new FormData();
      formData.append('filepath',filepath );
      formData.append('recordingId',recordingId);
    return this.httpClient.post<AnnexFile>("http://localhost:8080/api/v1/statementpdf/uploadFile", formData,{headers});
  }

  public updateAnnex(id:string,annex:string,annexNo:string,description:string) {
      let username='javainuse'
      let password='password'
     // console.log("test call");
      const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
     
      const formData = new FormData();
      formData.append('id',id );
      formData.append('annex',annex );
      formData.append('annexNo',annexNo);
      formData.append('description',description);
    return this.httpClient.put<AnnexFile>(this.baseUrl_1, formData,{headers});
  }

  public generateSignAnnexPdf(id:string){
    let username='javainuse'
    let password='password'
   // console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    const formData = new FormData();
      formData.append('id',id );
      return this.httpClient.post<any>("http://localhost:8080/api/v1/annex/addsignature", formData,{headers});
  }

  public deleteAnnex(id:string){
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) }); 
    return this.httpClient.delete("http://localhost:8080/api/v1/annex/"+id,{headers});
  
  }
  
  public getAnnexById(id: number)
  {
    let username='javainuse'
    let password='password'
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    return this.httpClient.get<AnnexFile>(this.baseUrl_1+ "/finbyid/"+ id,{headers});
  }


  public updateAnnexSignature(id:string,officierSiganature:string,
                              witnessSignature:string,interpreterSignature:string) {
    let username='javainuse'
    let password='password'
   // console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
   
    const formData = new FormData();
    formData.append('id',id );
    formData.append('officialSignature',officierSiganature );
    formData.append('witnessSignature',witnessSignature);
    formData.append('interpreterSignature',interpreterSignature);
  return this.httpClient.put<AnnexFile>("http://localhost:8080/api/v1/annex/signature", formData,{headers});
}

public createDigitalStatement(statementId:string,filePath:string,recordingId:string){
  let username='javainuse'
    let password='password'
   // console.log("test call");
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
    const formData = new FormData();
    formData.append('statementId',statementId);
    formData.append('filepath',filePath);
    formData.append('recordingId',recordingId);
    return this.httpClient.post<any>("http://localhost:8080/api/v1/digitalstatement/sign", formData,{headers})

}

  
public getSatementRecordingId(recordingid:number):Observable<StatementPdf[]>{
  let username='javainuse'
    let password='password'
    console.log("Line 55 to call the get Annex:" +recordingid);
  const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
   
  return this.httpClient.get<StatementPdf[]>("http://localhost:8080/api/v1/statementpdf/recordingid/"+recordingid);

}

public getDigitalSatementRecordingId(recordingid:number):Observable<StatementPdf[]>{
  let username='javainuse'
    let password='password'
    console.log("Line 55 to call the get Annex:" +recordingid);
  const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) });
   
  return this.httpClient.get<DigitalStatementPdf[]>("http://localhost:8080/api/v1/digitalstmtpdf/recordingid/"+recordingid);

}

public deleteStatementPdfByid(id:string){
  let username='javainuse'
  let password='password'
  const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) }); 
  return this.httpClient.delete("http://localhost:8080/api/v1/statementpdf/"+id,{headers});

}

public deleteDigitalStatementPdfByid(id:string){
  let username='javainuse'
  let password='password'
  const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) }); 
  return this.httpClient.delete("http://localhost:8080/api/v1/digitalstmtpdf/"+id,{headers});

}



/*public downloadStatementPdfById(id:string):Observable<Blob>{
  let username='javainuse'
  let password='password'
  const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) }); 
  return this.httpClient.get<Blob>("http://localhost:8080/api/v1/downloadFile/statement/"+id,{headers});

}*/


downloadStatementPdfById(id:string): Observable<any>{
  let username='javainuse'
  let password='password'
  const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) }); 
  return this.httpClient.get('http://localhost:8080/api/v1/downloadFile/statement/'+id, {headers,responseType: 'blob'});
}

getStatementPdfFileById(id:string): Observable<any>{
  let username='javainuse'
  let password='password'
  const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) }); 
  return this.httpClient.get('http://localhost:8080/api/v1/downloadFile/statement/'+id, {headers,responseType: 'arraybuffer'});
}



downloadDigitalStatementPdfById(id:string): Observable<any>{
  let username='javainuse'
  let password='password'
  const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) }); 
  return this.httpClient.get('http://localhost:8080/api/v1/downloadFile/digitalstmt/'+id, {headers,responseType: 'blob'});
}


downloadAnnexSignPdfById(id:string): Observable<any>{
  let username='javainuse'
  let password='password'
  const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(username + ':' + password) }); 
  return this.httpClient.get('http://localhost:8080/api/v1/annex/download/'+id, {headers,responseType: 'blob'});
}

 
}
