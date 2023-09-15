export class AnnexFile {
    constructor(
        public id: string,
        public recordingId:string,
        public annex:string,
        public annexNo:string,
        public fileName:string,
        public fileSize:number,
        public description:string,
        public officierSignature:string,
        public witnessSignature:string,
        public interpreterSignature:string,
      ) {}
}