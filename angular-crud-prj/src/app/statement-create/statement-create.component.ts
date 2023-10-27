import { AfterViewInit,OnInit, OnDestroy, Component, ElementRef, EventEmitter, Output, ViewChild } from '@angular/core';
import type { Editor } from '@ckeditor/ckeditor5-core';
import type { AnnotationsUIs } from '@ckeditor/ckeditor5-comments';
import * as ClassicEditorBuild from '../../../vendor/ckeditor5/build/classic-editor-with-track-changes.js';
import { getTrackChangesAdapter } from './track-changes-adapter';
import { ExportPdf } from '@ckeditor/ckeditor5-export-pdf';

import { Router } from '@angular/router';
import { C2isStatement } from '../model/statement';
import { StatementService } from '../service/statement.service';
import { CloudServicesConfig } from '../common/common-interface';
import { SuggestionService } from '../service/suggestion.service';
import  { TrackChangeSuggestion } from '../model/suggestion';



const LOCAL_STORAGE_KEY = 'CKEDITOR_CS_CONFIG';

@Component({
  selector: 'app-statement-create',
  templateUrl: './statement-create.component.html',
  styleUrls: ['./statement-create.component.css']
})
export class StatementCreateComponent implements OnInit,AfterViewInit, OnDestroy  {

  c2isStatement:C2isStatement=new C2isStatement("","","","","","","",new Date,new Date());

  trackChangeSuggestion:TrackChangeSuggestion=new TrackChangeSuggestion("1","1002","statemen-1","Testing Testing",new Date(),new Date());
		


  constructor(private statementService: StatementService, 
	private suggestionService:SuggestionService,
	private router: Router,suggestionService1: SuggestionService) {
	 }

  @Output() public ready = new EventEmitter<Editor>();
	@ViewChild( 'sidebar', { static: true } ) private sidebarContainer?: ElementRef<HTMLDivElement>;

	public Editor = ClassicEditorBuild.ClassicEditorWithTrackChanges;
	public exportPdf?:ExportPdf
	public editor?: Editor;

	public data =this.getInitialData();

	public get editorConfig() {
		return {
			extraPlugins: [
				getTrackChangesAdapter( this.appData ,this.suggestionService),
			],
			
			sidebar: {
				container: this.sidebar
			},
			licenseKey: this.licenseKey
			
		};
	}

	/*public config={
		exportPdf:{
			dataCallback:(data)=>{
				data.content=data.content.toUpperCase();
				console.log("this ...." +data);
				return data;
			}
		}
	}*/


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
				avatar: 'https://randomuser.me/api/portraits/thumb/men/25.jpg'
			},
			{
				id: 'user-2',
				name: 'John Smith',
				// Note that the avatar is optional.
				avatar: 'https://randomuser.me/api/portraits/thumb/men/26.jpg'
			},
			
		],
		// Suggestion threads data.
		suggestions: [
			{
				id: 'suggestion-1',
				type: 'insertion',
				authorId: 'user-2',
				createdAt: new Date( 2019, 1, 13, 11, 20, 48 ),
				hasComments: true
			},
			{
				id: 'suggestion-2',
				type: 'deletion',
				authorId: 'user-1',
				createdAt: new Date( 2019, 1, 14, 12, 7, 20 ),
				hasComments: false
			},
			{
				id: 'suggestion-3',
				type: 'insertion',
				authorId: 'user-1',
				createdAt: new Date( 2019, 1, 14, 12, 7, 20 ),
				hasComments: false
			},
			{
				id: 'suggestion-4',
				type: 'deletion',
				authorId: 'user-1',
				createdAt: new Date( 2019, 1, 15, 8, 44, 1 ),
				hasComments: true
			},
			{
				id: 'suggestion-5',
				type: 'formatInline:886cqig6g8rf',
				authorId: 'user-2',
				hasComments: false,
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
				hasComments: false,
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
		comments: [
			{
				threadId: 'suggestion-1',
				comments: [
					{
						commentId: 'comment-1',
						content: 'Sounds good.',
						authorId: 'user-1',
						createdAt: new Date( 2019, 1, 13, 11, 32, 57 )
					}
				]
			},
			{
				threadId: 'suggestion-4',
				comments: [
					{
						commentId: 'comment-2',
						content: 'I think it\'s not relevant.',
						authorId: 'user-2',
						createdAt: new Date( 2019, 1, 15, 9, 3, 1 )
					},
					{
						commentId: 'comment-3',
						content: 'You are right. Thanks.',
						authorId: 'user-1',
						createdAt: new Date( 2019, 1, 15, 9, 28, 1 )
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



	public ngOnInit(): void {
		console.log("Line 190 "+ this.suggestionService);

	( window as any ).CKBox = ClassicEditorBuild.CKBox;
		
		// Save the provided license key in the local storage.
		this.licenseKey = window.localStorage.getItem( this.STORAGE_KEY ) || window.prompt( 'Your license key' );
		window.localStorage.setItem( this.STORAGE_KEY, this.licenseKey );
		
  }

  public ngAfterViewInit(): void {
	if ( !this.sidebarContainer ) {
		throw new Error( 'Div container for sidebar was not found' );
	}

	this.sidebarContainer.nativeElement.appendChild( this.sidebar );
	
}

public ngOnDestroy(): void {
	window.removeEventListener( 'resize', this.boundRefreshDisplayMode );
	window.removeEventListener( 'beforeunload', this.boundCheckPendingActions );
}

public onReady( editor: Editor ): void {
	this.editor = editor;
	this.ready.emit( editor );

	// Make the track changes mode the "default" state by turning it on right after the editor initializes.
	this.editor.execute( 'trackChanges' );
	// Prevent closing the tab when any action is pending.
	window.addEventListener( 'beforeunload', this.boundCheckPendingActions );
	// Switch between inline and sidebar annotations according to the window size.
	window.addEventListener( 'resize', this.boundRefreshDisplayMode );
	this.refreshDisplayMode();
	//console.log("line 215");
	getTrackChangesAdapter(this.data,this.suggestionService);
	
	/*console.log("line 215");
	const TrackChangesAdapterClass = getTrackChangesAdapter(this.appData,this.suggestionService);
	const adapterInstance = new TrackChangesAdapterClass(this.editor, this.suggestionService);
	adapterInstance.init();
	
	console.log("line 217");*/

	
	
}
/*
public printPdf():void{
	//const config1=this.editor.config.get(config);
	console.log("it is in Print PDF");
	this.editor.execute('exportPdf',this.config.exportPdf);
	
}*/

public resetLicenseKey(): void {
	window.localStorage.removeItem( this.STORAGE_KEY );
	window.location.reload();
}

private checkPendingActions( domEvt ): void {
	console.log("Line 262 :: this.checkPendingActions " );
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
		<h2>
			Bilingual Personality Disorder
		</h2>
		<figure class="image image-style-side">
			<img src="https://c.cksource.com/a/1/img/docs/sample-image-bilingual-personality-disorder.jpg" srcset="https://c.cksource.com/a/1/img/docs/sample-image-bilingual-personality-disorder.jpg, https://c.cksource.com/a/1/img/docs/sample-image-bilingual-personality-disorder_2x.jpg 2x">
			<figcaption>
				One language, one person.
			</figcaption>
		</figure>
		<p>
			This may be the first time you hear about this made-up disorder but it
			<suggestion-start name="insertion:suggestion-1:user-2"></suggestion-start>actually<suggestion-end name="insertion:suggestion-1:user-2"></suggestion-end>
			isn’t so far from the truth. Even the studies that were conducted almost half a century show
			that <strong>the language you speak has more effects on you than you realize</strong>.
		</p>
		<p>
			One of the very first experiments conducted on this topic dates back to 1964.
			<a href="https://www.researchgate.net/publication/9440038_Language_and_TAT_content_in_bilinguals">In the experiment</a>
			designed by linguist Ervin-Tripp who is an
			<suggestion-start name="deletion:suggestion-2:user-1"></suggestion-start>
			authority<suggestion-end name="deletion:suggestion-2:user-1">
			</suggestion-end>
			<suggestion-start name="insertion:suggestion-3:user-1"></suggestion-start>
			expert<suggestion-end name="insertion:suggestion-3:user-1"></suggestion-end>
			in psycholinguistic and sociolinguistic studies, adults who are bilingual
			in English in French were showed series of pictures and were asked to create 3-minute stories.
			In the end participants emphasized
			drastically different dynamics for stories in English and French.
		</p>
		<p>
			Another ground-breaking experiment which included bilingual Japanese women married to American men
			<suggestion-start name="deletion:suggestion-4:user-1"></suggestion-start>in San
			Francisco <suggestion-end name="deletion:suggestion-4:user-1">
			</suggestion-end>were
			asked to complete sentences. The goal of the experiment was to investigate whether or not human
			feelings and thoughts
			are expressed differently in <strong>different language mindsets</strong>.
		</p>
		<p data-suggestion-start-before="formatBlock:698dn3otqzd6:suggestion-6:user-2">
			Here is a sample from the the experiment:
			<suggestion-end name="formatBlock:698dn3otqzd6:suggestion-6:user-2"></suggestion-end>
		</p>
		<table>
			<thead>
				<tr>
					<th></th>
					<th>English</th>
					<th>Japanese</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Real friends should</td>
					<td>Be very frank</td>
					<td>Help each other</td>
				</tr>
				<tr>
					<td>I will <suggestion-start name="formatInline:886cqig6g8rf:suggestion-5:user-2"></suggestion-start>probably<suggestion-end name="formatInline:886cqig6g8rf:suggestion-5:user-2" suggestion-type="formatInline:886cqig6g8rf"></suggestion-end> become</td>
					<td>A teacher</td>
				<td>A housewife</td>
				</tr>
				<tr>
					<td>When there is a conflict with family</td>
					<td>I do what I want</td>
					<td>It's a time of great unhappiness</td>
				</tr>
			</tbody>
		</table>
		<p>
			More recent <a href="https://books.google.pl/books?id=1LMhWGHGkRUC">studies</a> show, the
			language a person speaks affects
			their cognition, behavior, emotions and hence <strong>their personality</strong>.
			This shouldn’t come as a surprise
			<a href="https://en.wikipedia.org/wiki/Lateralization_of_brain_function">since we already know</a>
			that different regions of the brain become more active depending on the person’s activity at hand.
			The structure, information and especially <strong>the culture</strong> of languages varies
			substantially and the language a person speaks is an essential element of daily life.
		</p>
	`;
}


  public saveStatement() {
    this.c2isStatement.stage='Draft';
    this.statementService.createStatement(this.c2isStatement).subscribe({
      next: (data) => {
        console.log(data);
        this.redirectToStatementList();
      },
      error: (e) => {
        console.log(e);
      }
    });
  }

  redirectToStatementList() {
    this.router.navigate(['/viewstatement']);
  }

  startRecording(){
	//to save the data


  }

  onSubmit() {
    console.log(this.c2isStatement);
    this.saveStatement();
	console.log("Line 367 "+this.c2isStatement.content);
	getTrackChangesAdapter(this.c2isStatement.content,this.suggestionService);
	console.log("Line 369 "+this.c2isStatement.content);
  }


  /*public selectUser( user: User ): void {
		//this.selectedUser = user.id;
		//this.isWarning = false;

		const keys = Object.keys( user ) as ( keyof User )[];

    console.log("line 116 "+user.name+" "+user.avatar+" "+user.role );

		this.config.tokenUrl = `${ getRawTokenUrl( this.config.tokenUrl ) }?` + keys
			.filter( key => user[ key ] )
			.map( key => {
				if ( key === 'role' ) {
					return `${ key }=${ user[ key ] }`;
				}

				return `user.${ key }=${ user[ key ] }`;
			} )
			.join( '&' );
	}*/
}


interface User {
	id: string;
	name?: string;
	avatar?: string;
	role?: string;
}

function getUser(){
	let ckUser = JSON.parse( window.sessionStorage.getItem('') || '{}' );
	
	
}

function handleChannelIdInUrl(): string {
	let id = getChannelIdFromUrl();

	if ( !id ) {
		id = randomString();
		updateChannelIdInUrl( id );
	}
	
	return id;
}

function getChannelIdFromUrl(): string|null {
	const channelIdMatch = location.search.match( /channelId=(.+)$/ );

	return channelIdMatch ? decodeURIComponent( channelIdMatch[ 1 ] ) : null;
}

function randomString(): string {
	return Math.floor( Math.random() * Math.pow( 2, 52 ) ).toString( 32 );
}

function storeConfig( csConfig: CloudServicesConfig ): void {
	localStorage.setItem( LOCAL_STORAGE_KEY, JSON.stringify( csConfig ) );
}

function updateChannelIdInUrl( id: string ): void {
	window.history.replaceState( {}, document.title, generateUrlWithChannelId( id ) );
}

function generateUrlWithChannelId( id: string ): string {
	return `${ window.location.href.split( '?' )[ 0 ] }?channelId=${ id }`;
}

function getRawTokenUrl( url: string ): string {
	if ( isCloudServicesTokenEndpoint( url ) ) {
		return url.split( '?' )[ 0 ];
	}

	return url;
}

function isCloudServicesTokenEndpoint( tokenUrl: string ): boolean {
	console.log("Line 182 : "+tokenUrl);
	return /cke-cs[\w-]*\.com\/token\/dev/.test( tokenUrl );
}

function getStoredConfig(): CloudServicesConfig {
	const config = JSON.parse( localStorage.getItem( LOCAL_STORAGE_KEY ) || '{}' );
	config.tokenUrl='https://99330.cke-cs.com/token/dev/qETiDA3zwjh2YJ06PO4JmzwCVZY4m610uiDg?limit=10';
	config.webSocketUrl='wss://99330.cke-cs.com/ws';

	/*return {
		//tokenUrl: config.tokenUrl || 'https://99330.cke-cs.com/token/dev/qETiDA3zwjh2YJ06PO4JmzwCVZY4m610uiDg',
		//ckboxTokenUrl: config.ckboxTokenUrl || '',
		//webSocketUrl: config.webSocketUrl || 'wss://99330.cke-cs.com/ws'

		tokenUrl: 'https://99330.cke-cs.com/token/dev/qETiDA3zwjh2YJ06PO4JmzwCVZY4m610uiDg',
		ckboxTokenUrl:  '',
		webSocketUrl:  'wss://99330.cke-cs.com/ws'
	};*/

	return config;
}