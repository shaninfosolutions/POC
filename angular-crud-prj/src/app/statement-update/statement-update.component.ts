import { AfterViewInit,OnInit, OnDestroy, Component, ElementRef, EventEmitter, Output, ViewChild } from '@angular/core';
import type { Editor } from '@ckeditor/ckeditor5-core';
import type { AnnotationsUIs } from '@ckeditor/ckeditor5-comments';
import * as ClassicEditorBuild from '../../../vendor/ckeditor5/build/classic-editor-with-track-changes.js';


import { ActivatedRoute, Router } from '@angular/router';
import { C2isStatement } from '../model/statement';
import { StatementService } from '../service/statement.service';

import type { CommentsRepository } from '@ckeditor/ckeditor5-comments';
import type { TrackChanges } from '@ckeditor/ckeditor5-track-changes';
import { getTrackChangesAdapter} from './load-save-integration';

import { SuggestionService } from '../service/suggestion.service';
import { CommentService } from '../service/comment.service';
import { TrackChangeSuggestion } from '../model/suggestion';
import {  map } from 'rxjs';
import { TrackChangeComment } from '../model/comment';
import { AnnexFile } from '../model/annex.js';
import { saveAs } from 'file-saver';



@Component({
  selector: 'app-statement-update',
  templateUrl: './statement-update.component.html',
  styleUrls: ['./statement-update.component.css']
})
export class StatementUpdateComponent implements OnInit,AfterViewInit, OnDestroy{
  id!: number;
  c2isStatement: C2isStatement = new C2isStatement("","","","","","","",new Date,new Date());
 // annexFile:AnnexFiles=new AnnexFiles("","","","","","","","");
  //suggestionService:SuggestionService;
  trackChangeSuggestion=new TrackChangeSuggestion("","","","",new Date(),new Date());
  trackChangeComment=new TrackChangeComment("","","","","","",null,new Date(),new Date());
		
  //trackChangeSuggestions?:TrackChangeSuggestion[]
  trackChangeSuggestions?:TrackChangeSuggestion[];

  annexFiles?:AnnexFile[];

  trackChangeList:String[];
  //public appData?:any
  constructor(
  private statementService: StatementService,
  private route: ActivatedRoute, private router: Router, 
  private suggestionService:SuggestionService,
  private commentService:CommentService
  ){ }



  @Output() public ready = new EventEmitter<Editor>();
  @ViewChild( 'sidebar', { static: true } ) private sidebarContainer?: ElementRef<HTMLDivElement>;

	public Editor = ClassicEditorBuild.ClassicEditorWithTrackChanges;
	public editor?: Editor;

	public data = this.getInitialData();

  public get editorConfig() {
		return {
			extraPlugins: [
				
				getTrackChangesAdapter(this.appData,this.suggestionService,this.commentService, this.route.snapshot.params['id'])
			],
			sidebar: {
				container: this.sidebar
			},
			revisionHistory: {
				editorContainer: document.querySelector( '#editor-container' ),
				viewerContainer: document.querySelector( '#revision-viewer-container' ),
				viewerEditorElement: document.querySelector( '#revision-viewer-editor' ),
				viewerSidebarContainer: document.querySelector( '#revision-viewer-sidebar' )
			},   
			pagination: {
				// Page width and height correspond to A4 format
				pageWidth: '21cm',
				pageHeight: '29.7cm',
	
				pageMargins: {
					top: '20mm',
					bottom: '20mm',
					right: '12mm',
					left: '12mm'
				}
			},
			exportPdf: {
				fileName:document.querySelector('#caseFileNo'),
				dataCallback: ( editor ) => {
					this.editor.data.get({showSuggestionHighlights:true});
					return `
						${ editor.getData() }
						<div class="watermark">Draft</div>
					`;

					
				},
			},
			licenseKey: this.licenseKey
		};
	}

	private readonly STORAGE_KEY = 'ckeditor-license-key';
	private licenseKey = 'Y29iKzVnd2phUy9DOFVsTDBMQ3VrRWU0Zkk5VWdwRDI0TnRyZlY1Wm1aRjRwUE1wb1EyZG82UlBEOUM0LU1qQXlNekV4TWpZPQ==';

	private appData = {
		// The ID of the current user.
		userId: 'user-1',
		// Users data.
		users: [
			{
				id: 'user-1',
				name: 'Joe Doe',
				// Note that the avatar is optional.
				avatar: 'https://randomuser.me/api/portraits/thumb/men/26.jpg'
			},
			{
				id: 'user-2',
				name: 'Ella Harper',
				avatar: 'https://randomuser.me/api/portraits/thumb/women/65.jpg'
			},
			{id:sessionStorage.getItem('userid'),
			name:sessionStorage.getItem('username'),
			avatar:sessionStorage.getItem('useravator')
		}
		],
		// Suggestion threads data.
		suggestions: [
			{
				id: 'suggestion-1',
				type: 'insertion',
				authorId: 'user-2',
				createdAt: new Date( 2019, 1, 13, 11, 20, 48 )
			},
			{
				id: 'suggestion-2',
				type: 'deletion',
				authorId: 'user-1',
				createdAt: new Date( 2019, 1, 14, 12, 7, 20 )
			},
			{
				id: 'suggestion-3',
				type: 'insertion',
				authorId: 'user-1',
				createdAt: new Date( 2019, 1, 14, 12, 7, 20 )
			},
			{
				id: 'suggestion-4',
				type: 'deletion',
				authorId: 'user-1',
				createdAt: new Date( 2019, 1, 15, 8, 44, 1 )
			},
			{
				id: 'suggestion-5',
				type: 'formatInline:886cqig6g8rf',
				authorId: 'user-2',
				createdAt: new Date( 2019, 2, 8, 10, 2, 7 ),
				data: {
					commandName: 'bold',
					commandParams: [ { forceValue: true } ]
				}
			},
			{
				id: 'suggestion-6',
				type: 'formatBlock:698dn3otqzd6',
				authorId: 'user-2',
				createdAt: new Date( 2019, 2, 8, 10, 2, 10 ),
				data: {
					commandName: 'heading',
					commandParams: [ { value: 'heading2' } ],
					formatGroupId: 'blockName',
					multipleBlocks: false
				}
			}
		],
		// Comment threads data.
		commentThreads: [
			{
				threadId: 'suggestion-1',
				comments: [
					{
						commentId: 'comment-1',
						authorId: 'user-1',
						content: '<p>Are you sure it will fit here?</p>',
						createdAt: new Date( '09/20/2018 14:21:53' )
					},
					{
						commentId: 'comment-2',
						authorId: 'user-2',
						content: '<p>I think so...</p>',
						createdAt: new Date( '09/21/2018 08:17:01' )
					}
				]
			}
		]
	};

  // Note that Angular refs can be used once the view is initialized so we need to create
	// this container and use in the above editor configuration to work around this problem.
	private sidebar = document.createElement( 'div' );

	private boundRefreshDisplayMode = this.refreshDisplayMode.bind( this );
	private boundCheckPendingActions = this.checkPendingActions.bind( this );

	title = 'Signature Pad';
  signPad: any;
  //@ViewChild('signPadCanvas', {static: false}) signaturePadElement:any;
  @ViewChild('signPadCanvas', {static: false}) signaturePadElement:any;
  
  signImage:any;

  //@ViewChild( 'sidebar', { static: true } ) private sidebarContainer?: ElementRef<HTMLDivElement>;

	public async  ngOnInit() {	

		( window as any ).CKBox = ClassicEditorBuild.CKBox;
		// Save the provided license key in the local storage.
		this.licenseKey = window.localStorage.getItem( this.STORAGE_KEY ) || window.prompt( 'Your license key' );
		window.localStorage.setItem( this.STORAGE_KEY, this.licenseKey );
		this.getStatementById();

		this.getAnnexByRecordingId();
		//console.log("Line 777 before calling " + this.c2isStatement )

		
	}
	

	public ngAfterViewInit(): void {
		if ( !this.sidebarContainer ) {
			throw new Error( 'Div container for sidebar was not found' );
		}

		this.sidebarContainer.nativeElement.appendChild( this.sidebar );
		//this.signPad = new SignaturePad(this.signaturePadElement.nativeElement);
  

		//console.log("Line 162 :: ngAfterViewInit")

	}

	/*It's work in devices*/
	startSignPadDrawing(event: Event) {
		console.log(event);
	  }
	  /*It's work in devices*/
	  movedFinger(event: Event) {
	  }
	  /*Undo last step from the signature*/
	  undoSign() {
		const data = this.signPad.toData();
		if (data) {
		  data.pop(); // remove the last step
		  this.signPad.fromData(data);
		}
	  }
	  /*Clean whole the signature*/
	  clearSignPad() {
		this.signPad.clear();
	  }
	  /*Here you can save the signature as a Image*/
	  saveSignPad() {
		const base64ImageData = this.signPad.toDataURL();
		this.signImage = base64ImageData;
		//Here you can save your signature image using your API call.
	  }

	public ngOnDestroy(): void {
		window.removeEventListener( 'resize', this.boundRefreshDisplayMode );
		window.removeEventListener( 'beforeunload', this.boundCheckPendingActions );
	}

	

	public onReady( editor: Editor ): void {
		this.editor = editor;
		this.ready.emit( editor );

		// Make the track changes mode the "default" state by turning it on right after the editor initializes.
		 //this.editor.execute( 'trackChanges' );
		//this.editor.execute( 'comment' );

		// Prevent closing the tab when any action is pending.
		window.addEventListener( 'beforeunload', this.boundCheckPendingActions );

		// Switch between inline and sidebar annotations according to the window size.
		window.addEventListener( 'resize', this.boundRefreshDisplayMode );
		this.refreshDisplayMode();
	}

	public resetLicenseKey(): void {
		window.localStorage.removeItem( this.STORAGE_KEY );
		window.location.reload();
	}

	private checkPendingActions( domEvt ): void {
		if ( this.editor.plugins.get( 'PendingActions' ).hasAny ) {
			domEvt.preventDefault();
			domEvt.returnValue = true;
		}
	}
	private refreshDisplayMode(): void {
		const annotationsUIs = this.editor.plugins.get( 'AnnotationsUIs' ) as AnnotationsUIs;
		const sidebarElement = this.sidebarContainer.nativeElement;

		if ( window.innerWidth < 1070 ) {
			sidebarElement.classList.remove( 'narrow' );
			sidebarElement.classList.add( 'hidden' );
			annotationsUIs.switchTo( 'inline' );
		}
		else if ( window.innerWidth < 1300 ) {
			sidebarElement.classList.remove( 'hidden' );
			sidebarElement.classList.add( 'narrow' );
			annotationsUIs.switchTo( 'narrowSidebar' );
		}
		else {
			sidebarElement.classList.remove( 'hidden', 'narrow' );
			annotationsUIs.switchTo( 'wideSidebar' );
		}
	}
  private getInitialData(): string {
		return `
		`;
	}



private getStatementById() {
      this.id = this.route.snapshot.params['id'];
      this.statementService.getStatement(this.id).subscribe({
        next: (data) => {
          this.c2isStatement = data;
        },
        error: (e) => {
          console.log(e);
        }
      });
 }

 private getAnnexByRecordingId(){
	this.id = this.route.snapshot.params['id'];
	this.statementService.getAnnexsByRecordingId(this.id).subscribe({
        next: (data) => {
          this.annexFiles = data;
		  this.ngOnInit;
        },
        error: (e) => {
          console.log(e);
        }
      });
 }



  getTrackChangeSugByRecodId(){
	this.id = this.route.snapshot.params['id'];
	return this.suggestionService.getTrackChangeSugByRecordingId(this.id).pipe(map(
		(data=>{
		this.trackChangeSuggestions=data;
		console.log("Line 1111 117 :  #### " + this.trackChangeSuggestions.length);
		}
		))
		);
}

private async  getTrackChangeList() {
	this.id = this.route.snapshot.params['id'];
	return new Promise<void>((resolve, reject) => {
	  this.suggestionService.getTrackChangeListByStatementId(this.id).subscribe({
		next: (data) => {
		  this.trackChangeList = data;
		  resolve();
		},
		error: (e) => {
		  console.log(e);
		  reject(e);
		}
	  });
	});
  }

private async  getData() {
	this.id = this.route.snapshot.params['id'];
	return new Promise<void>((resolve, reject) => {
	  this.suggestionService.getTrackChangeSugByRecordingId(this.id).subscribe({
		next: (data) => {
		  this.trackChangeSuggestions = data;
		  resolve();
		},
		error: (e) => {
		  console.log(e);
		  reject(e);
		}
	  });
	});
  }

  private performBusinessLogic() {
	console.log("Line 206 data is avaialbel here "+this.trackChangeSuggestions[0].suggestionThread); // Data is available here
	for (const suggestion of this.trackChangeSuggestions) {
		console.log(suggestion); // Do something with each suggestion
	  }  
	  

	  console.log(this.appData); 
}


    updateStatement() {
      this.statementService.updateStatement(this.id,this.c2isStatement,).subscribe({
        next: (data) => {
          console.log("the id of the statement "+this.id);
          alert("Statement update successfully:["+this.c2isStatement.statementNo+"]")
          this.redirectToUserList();
        },
        error: (e) => {
          console.log(e);
        }
      });
    }
  
    redirectToUserList() {
      this.router.navigate(['/viewstatement']);
    }


  //ngOnInit(): void {
   // this.getStatementById();
  //}

  onSubmit() {
    console.log("To update "+this.c2isStatement);
    this.updateStatement();
  }


  updateAllTrackChange( evt):void{

	//capture all the Track change and Commend Change and loop and update 
	const editorData = this.editor.data.get();
	const trackChanges = this.editor.plugins.get( 'TrackChanges' ) as TrackChanges;
	const comments = this.editor.plugins.get( 'CommentsRepository' ) as CommentsRepository;
	
	const suggestionsData = trackChanges.getSuggestions();
	const commentThreadsData = comments.getCommentThreads( {
		skipNotAttached: true,
		skipEmpty: true
	} );


	

	//console.log( 'Editor data:' );
	//console.log( editorData );
	//console.log( 'Suggestions data:' );
	// console.log( suggestionsData );
	//console.log( 'Comment threads data:' );
	// console.log( commentThreadsData );
	//alert("it is here " + suggestionsData)

	for(let d of suggestionsData){
		console.log("suggestionsData: Suggestion in the looop:"+JSON.stringify(d.id) +":"+JSON.stringify(d) );
		 this.id = this.route.snapshot.params['id'];
		 this.trackChangeSuggestion.recordingId=this.route.snapshot.params['id'];
		 this.trackChangeSuggestion.suggestionThread=JSON.stringify(d);
		 this.trackChangeSuggestion.suggestionThreadId=d.id;
		//TrackChangeSuggestion trackChangeSuggestion=new TrackChangeSuggestion();
		//Update the Track Change
		 new Promise((_resolve) => {
			this.suggestionService.updateByRecordingAndThreadId(this.trackChangeSuggestion.recordingId,d.id,this.trackChangeSuggestion).subscribe({
			  next: (data) => {
				console.log("Update and Create Successfully:"+d.id+"["+data+"]");
			  },
			  error: (e) => {
				console.log(e);
			  }
			});
		  });

		//update or insert into Suggestion and the Comment 
		//console.log("Suggestion thread id"+ d.id)
		//console.log("suggestionsData: Suggestion comment in the looop:"+JSON.stringify(d.commentThread.id) +":"+
		//JSON.stringify(d.commentThread) );


		this.trackChangeComment.recordingId=this.route.snapshot.params['id'];
		this.trackChangeComment.commentThreadId=d.id;
		this.trackChangeComment.commentThread=JSON.stringify(d.commentThread);
		new Promise((_resolve) => {
			this.commentService.updateByCommentThreadId(this.trackChangeComment.recordingId,d.id,this.trackChangeComment).subscribe({
			  next: (data) => {
				console.log("Suggestion Thread Comment Thread::Track Change Comment: Update and Create Successfully:"+d.id+"["+data+"]");
			  },
			  error: (e) => {
				console.log(e);
			  }
			});
		  });


		
		/*for(const c of d.commentThread.comments){
			console.log("Comment threadid" +c.threadId);
			console.log("Comment id" +c.id);
			this.trackChangeComment.recordingId=this.route.snapshot.params['id'];
			this.trackChangeComment.commentId=c.id;
			this.trackChangeComment.commentThreadId=c.threadId;
			this.trackChangeComment.commentThread=JSON.stringify(c);

			new Promise((_resolve) => {
				
				this.commentService.updateByCommentThreadId(this.trackChangeComment.recordingId,c.threadId,this.trackChangeComment).subscribe({
				  next: (data) => {
					console.log(" d.commentThread.comments::::Update and Create Successfully:"+d.id+"["+data+"]");
				  },
				  error: (e) => {
					console.log(e);
				  }
				});
			  });
		}*/
		


	}

	for(let c of commentThreadsData){
		console.log("commentThreadsData: Suggestion in the looop:"+JSON.stringify(c.id) +":"+JSON.stringify(c) );
		//console.log("Suggestion comment in the looop:"+JSON.stringify(d.) +":"+
		// JSON.stringify(d.commentThread) );
		this.trackChangeComment.recordingId=this.route.snapshot.params['id'];
			this.trackChangeComment.commentId=c.id;
			//this.trackChangeComment.commentThreadId=c;
			this.trackChangeComment.commentThread=JSON.stringify(c);
		new Promise((_resolve) => {
				
			this.commentService.updateByCommentThreadId(this.trackChangeComment.recordingId,this.trackChangeComment.commentThreadId,this.trackChangeComment).subscribe({
			  next: (data) => {
				console.log(" d.commentThread.comments::::Update and Create Successfully:"+c.id+"["+data+"]");
			  },
			  error: (e) => {
				console.log(e);
			  }
			});
		  });

	}

	evt.preventDefault();

	


  }

  //fileName = '';

  public uploadSatementPdfFile(evt):void{
	const file:File = evt.target.files[0];

	if (file) {
		const formData = new FormData();
		formData.append("file", file);
		console.log("recording id "+this.c2isStatement.id);
		
		this.statementService.createStatementPdf(formData,"Digital-Signature-Input",this.route.snapshot.params['id']).subscribe({
			next: (data) => {
				console.log("Created Successful"+JSON.stringify(data));
				this.ngOnInit;
				window.location.reload();
			},
			error:(e)=>{
				console.log(e);
			}
		})
	}

  }

  public onFileSelected(evt):void{
	const file:File = evt.target.files[0];

        if (file) {
            const formData = new FormData();
			formData.append("file", file);
			console.log("recording id "+this.c2isStatement.id);
			
			this.statementService.createAnnex(formData,"NA","0",this.route.snapshot.params['id'],"").subscribe({
				next: (data) => {
					console.log("Created Successful"+JSON.stringify(data));
					this.ngOnInit;
					window.location.reload();
				},
				error:(e)=>{
					console.log(e);
				}
			})
		}

  }

 /* public deleteAnnexFile(id:string){
	console.log("Delete Annex file : " + id);
	this.statementService.deleteAnnex(id);
	alert("Deleted Successfully ");
	this.ngOnInit;
	window.location.reload();
  }
  */

  public deleteAnnexFile(id:string): void {
	console.log("Delete Annex file : " + id);
	this.statementService.deleteAnnex(id)
	  .subscribe( data => {
		//this.c2isStatements = this.c2isStatements.filter(u => u !== c2isStatement);
		alert("Statement Deleted Successfully:")
		this.ngOnInit;
		window.location.reload();
	  },
	  )
  };

  public updateAnnexFile(annexFile:AnnexFile){
	console.log("Update Annex file : " + JSON.stringify(annexFile));
	this.statementService.updateAnnex(annexFile.id,annexFile.annex,annexFile.annexNo,annexFile.description).subscribe({
		next: (data) => {
			console.log("Updated Successful"+JSON.stringify(data));
			//this.ngOnInit;
			alert("Update Successfully ");
			//window.location.reload();
		},
		error:(e)=>{
			console.log(e);
		}
	})
  }

  public addsignaturepanel(id:string){
	console.log("Update Digital Statement to redirect : " + id);
	this.router.navigate(['annexfileaddsignature', id]);
  }


  public digitalStatementPdf(id:string){
	console.log("Update Digital Statement PDF to redirect : " + id);
	this.router.navigate(['statementpdf', id]);
  }


  public generateSingPdf(id:string){
	console.log("Generate Sign PDF : " + id);

	this.statementService.generateSignAnnexPdf(id).subscribe({
		next: (data) => {
			console.log("Updated Successful"+JSON.stringify(data));
			//this.ngOnInit;
			alert("Generate PDF Successfully, It is ready to download :"+data.fileName);
			//window.location.reload();
		},
		error:(e)=>{
			console.log(e);
		}
	})
	
	
  }


  public downloadSignPdf(id:string,fileName:string){
	console.log("Download Annex Signed PDF file : " + id);
   // this.statementService.downloadStatementPdfById(id).subscribe(data=>{
	 
	  this.statementService.downloadAnnexSignPdfById(id).subscribe((response:any) => {
		let blob:any = new Blob([response], { type: 'application/pdf; charset=utf-8' });
	   const url = window.URL.createObjectURL(blob);
	   alert("The file name "+ fileName);
		saveAs(blob, fileName);
  
	  }
	),(error: any) => console.log('Error downloading the file'),
	() => console.info('File downloaded successfully');
  
  }
  


  public showEditorDataInConsole( evt ): void {
	const editorData = this.editor.data.get();

	const trackChanges = this.editor.plugins.get( 'TrackChanges' ) as TrackChanges;
	const comments = this.editor.plugins.get( 'CommentsRepository' ) as CommentsRepository;
	
	const suggestionsData = trackChanges.getSuggestions();
	const commentThreadsData = comments.getCommentThreads( {
		skipNotAttached: true,
		skipEmpty: true
	} );

	console.log( 'Editor data:' );
	console.log( editorData );
	console.log( 'Suggestions data:' );
	console.log( suggestionsData );
	console.log( 'Suggestions data:Comment Thread' );
	console.log( suggestionsData[0].commentThread);
	console.log( 'Comment threads data:' );
	console.log( commentThreadsData );
	

	evt.preventDefault();
}

  


}
