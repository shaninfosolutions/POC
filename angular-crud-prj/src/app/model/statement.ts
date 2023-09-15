export class C2isStatement {
    constructor(
        public id: string,
        public statementNo: string,
        public caseFileNo: string,
        public personInCharge: string,
        public offender:string,
        public content:string,
        public stage:string,
        public createdDt:Date,
        public lastUpdatedDt:Date,
      ) {}
}