export class TrackChangeComment {
    constructor(
        public id: string,
        public recordingId:string,
        public commentThreadId:string,
        public commentId:string,
        public commentThread:string,
        public resolvedBy:string,
        public resolvedAt:Date,
        public createdDt:Date,
        public lastUpdatedDt:Date,
    ){}
}