/**
 * @license Copyright (c) 2003-2023, CKSource Holding sp. z o.o. All rights reserved.
 * For licensing, see LICENSE.md.
 */

import type { Editor } from '@ckeditor/ckeditor5-core';

import type { Users } from '@ckeditor/ckeditor5-collaboration-core';
import type { CommentsRepository } from '@ckeditor/ckeditor5-comments';
import type { TrackChanges } from '@ckeditor/ckeditor5-track-changes';
import type { Autosave } from '@ckeditor/ckeditor5-autosave';


import { TrackChangeSuggestion } from '../model/suggestion';
import { SuggestionService } from '../service/suggestion.service';
import { CommentService } from '../service/comment.service';
import { TrackChangeComment } from '../model/comment';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, map } from 'rxjs';



export function getTrackChangesAdapter( appData ,trackChangeSerice,commentService,recordingId) {
	return class TrackChangesAdapter {
		public editor: Editor;
		public suggestionService: SuggestionService;
		public commentService:CommentService;
		public constructor( editor: Editor ,private route: ActivatedRoute, private router: Router) {
			this.editor = editor;
			this.suggestionService=trackChangeSerice;	
			this.commentService=commentService;
		}

		public trackChangeSuggestion=new TrackChangeSuggestion("","","","",new Date(),new Date());
		public trackChangeComment=new TrackChangeComment("","","","","","",null,new Date(),new Date());
		temp$: Observable<any[]>;
		

		public async init() {
			const usersPlugin = this.editor.plugins.get( 'Users' ) as Users;
			const trackChangesPlugin = this.editor.plugins.get( 'TrackChanges' ) as TrackChanges;
			const commentsRepositoryPlugin = this.editor.plugins.get( 'CommentsRepository' ) as CommentsRepository;
			const autosavePlugin=this.editor.plugins.get('Autosave') as Autosave;

			let curUser={"id":sessionStorage.getItem('userid'),
			"name":sessionStorage.getItem('username'),
			"avatar":sessionStorage.getItem('useravator')};
			usersPlugin.addUser( curUser );
			usersPlugin.defineMe( sessionStorage.getItem('userid') );

			//Auto Save Plugin
			autosavePlugin.adapter={
				save:editor=>{

					return Promise.resolve(editor.data);

				}
			}

			// Set the adapter to the `TrackChanges#adapter` property.
			trackChangesPlugin.adapter = {

				getSuggestion: suggestionId => {
					// This function should query the database for data for a suggestion with a `suggestionId`.
					console.log( 'Get suggestion in line 179', suggestionId );
					 return new Promise( resolve => {
						this.suggestionService.getTrackChangeListByThreadId(suggestionId).subscribe({
							next: (suggestion) =>{
								console.log("line 170" + JSON.stringify(suggestion));
								//resolve();
							}
						})

						resolve;

					} );

					/*return new Promise( resolve => {
						resolve( 
							this.suggestionService.getTrackChangeListByThreadId(suggestionId).toPromise()
							
							);
					} );
					*/
					/*return new Promise( resolve => {
						resolve( appData.suggestions.find( suggestion => suggestionId === suggestion.id ) );
					} );
				*/

				}
				
				,
				addSuggestion: suggestionData => {
					// This function should save `suggestionData` in the database.
					console.log( 'Suggestion added', JSON.stringify(suggestionData) );
					
					this.trackChangeSuggestion.suggestionThread=JSON.stringify(suggestionData);
					this.trackChangeSuggestion.recordingId=recordingId;
					this.trackChangeSuggestion.suggestionThreadId=suggestionData.id

					const suggestionCommentData = trackChangesPlugin.getSuggestions();

					/*for(const comment of suggestionCommentData){
						//CommentThread commentThread=comment.commentThread
						console.log("line 164 "+ suggestionData.id+" "+
						JSON.stringify("It is comment thread :"+comment.commentThread.id)+" "+
						JSON.stringify("It is suggested comment "+JSON.stringify(comment.commentThread)))
						// Add and update the comment there .. 
						//to update the 
						this.trackChangeComment.commentThread=JSON.stringify(comment.commentThread);
						this.trackChangeComment.commentThreadId=comment.commentThread.id;

						if(comment.commentThread.id===suggestionData.id){
							console.log("Line 147 : to update the comment suggest" )
							//Add the comment 
							//TrackChangeComment trackchangeComment=new TrackChangeComment();
							this.trackChangeComment.commentThreadId=comment.commentThread.id;
							this.trackChangeComment.commentThread=JSON.stringify(comment.commentThread);
							this.trackChangeComment.recordingId=recordingId;

							this.commentService.createTrackChangeComment(this.trackChangeComment).subscribe(data =>{
								console.log("Data save successfully "+ JSON.stringify(data));
							})

						}
						
						//if found update if not create to avoid the duplicate


					}*/

					// This function should save `suggestionData` in the database.
					console.log( 'Suggestion added', suggestionData );
					//data.find( suggestion => suggestionId === suggestion.id ) ;
					this.trackChangeSuggestion.suggestionThread=JSON.stringify(suggestionData);
					this.trackChangeSuggestion.recordingId=recordingId
					
					  const promise=Promise.resolve(this.trackChangeSuggestion) as Promise<any>;
					  promise.then((value)=>{
						this.suggestionService.createTrackChangeSuggestion(value).subscribe({
							next:(data) =>{
								console.log("It is in line 128 :"+data.suggestionThread);
								
							},
							error: (e)=>{
								console.log(e)
							}
						})
					  })  
			
					return promise;
						
					//this.trackChangeComment=JSON.stringify(suggestionData);
					//call the method to create the 

					/*return Promise.resolve( {
						createdAt: new Date()		// Should be set server-side.
					} ) as Promise<any>;
					*/

				},
				updateSuggestion: ( id, suggestionData ) => {
					// This function should update `suggestionData` in the database.
					console.log( 'Suggestion updated', id + " "+
					JSON.stringify( suggestionData ));

					return Promise.resolve();
				}
			};

			// Track changes uses comments to allow discussing about the suggestions.
			// The comments adapter has to be defined as well.
		
			commentsRepositoryPlugin.adapter = {
				getCommentThread: ( { threadId }: any ) => {
					// This function should query the database for data for the comment thread with a `commentThreadId`.
					console.log( 'Line 195 Get comment thread', threadId );
					
					/*return new Promise( resolve => {
						resolve( appData.comments.find( comment => threadId === comment.threadId ) );
					} ) as Promise<any>;*/
					

					return new Promise( resolve => {
						resolve( 
							//appData.comments.find( comment => threadId === comment.threadId ) 
							this.commentService.getChangeCommentListByThreadId(threadId).subscribe({
								next: (comment) =>{
									console.log("line 221" + JSON.stringify(comment));
								}
								
							})
							);
					
					} ) as Promise<any>;

				},
				addCommentThread: ( data: any ) => {
					// Write a request to your database here. The returned `Promise`
					// should be resolved when the request has finished.
						// Write a request to your database here. The returned `Promise`
					// should be resolved when the request has finished.
					console.log("Add comment Thread" );
					const promise= Promise.resolve(data);
					promise.then((value)=>{
						console.log("Data in 192" + JSON.stringify(data));
						this.trackChangeComment.commentThread=JSON.stringify({threadId: value.threadId,
															comments: value.comments.map(comment => ({
															commentId:comment.commentId,content:comment.content,
															authorId:comment.authorId,createdAt:new Date(),
															resolvedAt:null,
															resolvedBy:null,
															attributes:comment.attributes
														}))});
						//this.trackChangeComment.commentThread=JSON.stringify(data);				
						this.trackChangeComment.recordingId=recordingId
						this.trackChangeComment.commentThreadId=value.threadId;
						this.commentService.createTrackChangeComment(this.trackChangeComment).subscribe({
							next:(data) =>{
								console.log("Create Track Change comment successfully:"+data);
							},
							error: (e)=>{
								console.log(e)
							}
						})
					  })  

					  return promise;
				},
				updateCommentThread: ( data: any ) => {
					// This function should save `data` in the database.
					console.log( 'Comment thread updated', data );
					return Promise.resolve();
				},
				resolveCommentThread: ( data: any ) => {
					// This function should save `data` in the database.
					console.log( 'Comment thread resolved', data );
					return Promise.resolve( {
						resolvedAt: new Date(), // Should be set on the server side.
						resolvedBy: usersPlugin.me.id // Should be set on the server side.
					} ) as Promise<any>;
				},
				reopenCommentThread: ( data: any ) => {
					// This function should save `data` in the database.
					console.log( 'Comment thread reopened', data );
					return Promise.resolve() as any;
				},
				removeCommentThread: ( data: any ) => {
					// This function should remove the comment of a given `data` from the database.
					console.log( 'Comment thread removed', data );
					return Promise.resolve();
				},

				addComment: ( data: any ) => {
					// This function should save `data` in the database.
					console.log( 'Comment added', data );
					return Promise.resolve( {
						commentId: data.commentId,
						createdAt: new Date()		// Should be set server-side.
					} );
				},
				updateComment: ( data: any ) => {
					// This function should save `data` in the database.
					console.log( 'Comment updated', data );
					return Promise.resolve();
				},
				removeComment: ( data: any ) => {
					// This function should remove the comment of a given `data` from the database.
					console.log( 'Comment removed', data );
					return Promise.resolve();
				}
			};

			
			
			
		}
	};
}