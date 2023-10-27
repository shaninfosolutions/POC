import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StatementService } from '../service/statement.service';
import { StatementPdf,DigitalStatementPdf } from '../model/statementpdf';
import { saveAs } from 'file-saver';
@Component({
  selector: 'app-digital-statement',
  templateUrl: './digital-statement.component.html',
  styleUrls: ['./digital-statement.component.css']
})
export class DigitalStatementComponent implements OnInit{
  id!: number;
  statementPdfs?:StatementPdf[];
  digitalStatementPdfs?:DigitalStatementPdf[];
  constructor( private statementService: StatementService,
    private route: ActivatedRoute, private router: Router, ) { }

    ngOnInit(): void {
     this.getStatementPdfById();
     this.getDigitalStatementPdfById();
      
      }
  private getStatementPdfById() {
    this.id = this.route.snapshot.params['id'];
    this.statementService.getSatementRecordingId(this.id).subscribe({
      next: (data) => {
        this.statementPdfs = data;
      },
      error: (e) => {
        console.log(e);
      }
    });
}

private getDigitalStatementPdfById() {
  this.id = this.route.snapshot.params['id'];
  this.statementService.getDigitalSatementRecordingId(this.id).subscribe({
    next: (data) => {
      this.digitalStatementPdfs = data;
    },
    error: (e) => {
      console.log(e);
    }
  });
}

public deleteStatementPdf(id:string): void {
	console.log("Delete Statement PDF file : " + id);
	this.statementService.deleteStatementPdfByid(id)
	  .subscribe( data => {
		//this.c2isStatements = this.c2isStatements.filter(u => u !== c2isStatement);
		alert("Statement PDF Deleted Successfully:")
		this.ngOnInit;
		window.location.reload();
	  },
	  )
  };


public signStatement(id:string){
  console.log("Redirect to PDF Viewer file : " + id);
	this.router.navigate(['pdf-pspdf-viewer', id]);
}

createDigitalStatement(id:string,filePath:string,recordingId:string){
  console.log("Sign the PDF File: "+id);
  this.statementService.createDigitalStatement(id,filePath,recordingId).subscribe(
    data=>{
      alert("Sign PDF Statement Successfully:" + JSON.stringify(data));
      this.ngOnInit;
		  window.location.reload();
  })

}
downloadStatementPdf(id:string,filename:string){

  console.log("Download Statement PDF file : " + id);
 // this.statementService.downloadStatementPdfById(id).subscribe(data=>{
   
    this.statementService.downloadStatementPdfById(id).subscribe((response:any) => {
      let blob:any = new Blob([response], { type: 'application/pdf; charset=utf-8' });
			const url = window.URL.createObjectURL(blob);
      saveAs(blob, filename);

    }
  ),(error: any) => console.log('Error downloading the file'),
  () => console.info('File downloaded successfully');

}

downloadDigitalPdf(id:string,filename:string){
  console.log("Download Digital Statement PDF file : " + id);
 // this.statementService.downloadStatementPdfById(id).subscribe(data=>{
   
    this.statementService.downloadDigitalStatementPdfById(id).subscribe((response:any) => {
      let blob:any = new Blob([response], { type: 'application/pdf; charset=utf-8' });
			const url = window.URL.createObjectURL(blob);
      saveAs(blob, filename);

    }
  ),(error: any) => console.log('Error downloading the file'),
  () => console.info('File downloaded successfully');

}

deleteDigitalStatement(id:string){
  console.log("Delete Digital Statement PDF file : " + id);
	this.statementService.deleteDigitalStatementPdfByid(id)
	  .subscribe( data => {
		//this.c2isStatements = this.c2isStatements.filter(u => u !== c2isStatement);
		alert("Digital Statement PDF Deleted Successfully:")
		this.ngOnInit;
		window.location.reload();
	  },
	  )

}



}
