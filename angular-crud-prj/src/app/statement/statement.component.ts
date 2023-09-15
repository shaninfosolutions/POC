import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { C2isStatement } from '../model/statement';
import { StatementService } from '../service/statement.service';
import { Observable } from 'rxjs/internal/Observable';

@Component({
  selector: 'app-statement',
  templateUrl: './statement.component.html',
  styleUrls: ['./statement.component.css']
})
export class StatementComponent {

  c2isStatements!: C2isStatement[]

  c2isStatementsPromise: C2isStatement[];

  test=this.httpClientService.getAll();



  constructor(
    private httpClientService:StatementService,
    private router: Router
  ) { }

   ngOnInit() {
    this.httpClientService.getStatements().subscribe(
     response =>this.handleSuccessfulResponse(response),
    );

   // this.c2isStatementsPromise =  await this.httpClientService.getAll();  
   // console.log("testing Line 35 "+ this.c2isStatementsPromise.length);
    console.log("Testing testing 34 : test" + this.test.then((v)=>
    console.log(v[0].caseFileNo)));

    // to initiali
  }

  handleSuccessfulResponse(response:any)
{
    this.c2isStatements=response;
}

deleteStatement(c2isStatement: C2isStatement): void {
  this.httpClientService.deleteStatement(c2isStatement)
    .subscribe( data => {
      this.c2isStatements = this.c2isStatements.filter(u => u !== c2isStatement);
      alert("Statement Deleted Successfully:["+c2isStatement.statementNo+"]")
    })
};

updateStatement(id: string) {
  this.router.navigate(['update-statement', id]);
}

createStatement(){
  this.router.navigate(['create-statement']);
}

}
