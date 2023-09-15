export class TrackChangeSuggestion {
    constructor(
        public id: string,
        public recordingId:string,
        public suggestionThreadId:string,
        public suggestionThread:string,
        public createdDt:Date,
        public lastUpdatedDt:Date,
    ){}
}