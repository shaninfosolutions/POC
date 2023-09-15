import { Component,OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StatementService } from '../service/statement.service';
import { StatementPdf,DigitalStatementPdf } from '../model/statementpdf';
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


signStatement(id:string){

}
createDigitalStatement(id:string){

}

downloadStatementPdf(id:string){

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

downloadDigitalPdf(id:string){

}

}
