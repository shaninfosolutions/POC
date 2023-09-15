export class AnnexFiles {
    constructor(
        public id: string,
        public annex: string,
        public annexNo: string,
        public description: string,
        public recordingId:string,
        public fileName:string,
        public downloadUri:string,
        public size:string,
        
      ) {}
}