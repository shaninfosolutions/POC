export class StatementPdf {
    constructor(
        public id: string,
        public recordingId:string,
        public fileName:string,
        public filePath:string,
        public fileVerNo:string,
      ) {}
}

export class DigitalStatementPdf {
    constructor(
        public id: string,
        public recordingId:string,
        public fileName:string,
        public filePath:string,
        public fileVerNo:string,
      ) {}
}