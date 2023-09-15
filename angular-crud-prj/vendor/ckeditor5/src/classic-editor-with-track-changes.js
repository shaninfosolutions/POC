/**
 * @license Copyright (c) 2003-2023, CKSource Holding sp. z o.o. All rights reserved.
 * For licensing, see LICENSE.md.
 */

import BaseEditor from '@ckeditor/ckeditor5-editor-classic/src/classiceditor';
import RevisionHistory from '@ckeditor/ckeditor5-revision-history/src/revisionhistory';
import TrackChanges from '@ckeditor/ckeditor5-track-changes/src/trackchanges';
import Comments from '@ckeditor/ckeditor5-comments/src/comments';
import Autosave from '@ckeditor/ckeditor5-autosave/src/autosave';
import Alignment from '@ckeditor/ckeditor5-alignment/src/alignment';
import Autoformat from '@ckeditor/ckeditor5-autoformat/src/autoformat';
import BlockQuote from '@ckeditor/ckeditor5-block-quote/src/blockquote';
import Bold from '@ckeditor/ckeditor5-basic-styles/src/bold';
import CKBoxPlugin from '@ckeditor/ckeditor5-ckbox/src/ckbox';
import PictureEditing from '@ckeditor/ckeditor5-image/src/pictureediting';
import CloudServices from '@ckeditor/ckeditor5-cloud-services/src/cloudservices';
import Essentials from '@ckeditor/ckeditor5-essentials/src/essentials';
import FontFamily from '@ckeditor/ckeditor5-font/src/fontfamily';
import FontSize from '@ckeditor/ckeditor5-font/src/fontsize';
import Heading from '@ckeditor/ckeditor5-heading/src/heading';
import Highlight from '@ckeditor/ckeditor5-highlight/src/highlight';
import Image from '@ckeditor/ckeditor5-image/src/image';
import ImageCaption from '@ckeditor/ckeditor5-image/src/imagecaption';
import ImageResize from '@ckeditor/ckeditor5-image/src/imageresize';
import ImageStyle from '@ckeditor/ckeditor5-image/src/imagestyle';
import ImageToolbar from '@ckeditor/ckeditor5-image/src/imagetoolbar';
import ImageUpload from '@ckeditor/ckeditor5-image/src/imageupload';
import Italic from '@ckeditor/ckeditor5-basic-styles/src/italic';
import Link from '@ckeditor/ckeditor5-link/src/link';
import List from '@ckeditor/ckeditor5-list/src/list';
import MediaEmbed from '@ckeditor/ckeditor5-media-embed/src/mediaembed';
import Paragraph from '@ckeditor/ckeditor5-paragraph/src/paragraph';
import PasteFromOffice from '@ckeditor/ckeditor5-paste-from-office/src/pastefromoffice';
import RemoveFormat from '@ckeditor/ckeditor5-remove-format/src/removeformat';
import Strikethrough from '@ckeditor/ckeditor5-basic-styles/src/strikethrough';
import Table from '@ckeditor/ckeditor5-table/src/table';
import TableToolbar from '@ckeditor/ckeditor5-table/src/tabletoolbar';
import Underline from '@ckeditor/ckeditor5-basic-styles/src/underline';

import { ImportWord } from '@ckeditor/ckeditor5-import-word';
import { ExportPdf } from '@ckeditor/ckeditor5-export-pdf';
import {ExportWord} from '@ckeditor/ckeditor5-export-word';

import * as CKBox from 'ckbox';
import 'ckbox/dist/styles/ckbox.css';

class ClassicEditorWithTrackChanges extends BaseEditor {}

ClassicEditorWithTrackChanges.builtinPlugins = [
	Alignment,
	Autoformat,
	BlockQuote,
	Bold,
	CKBoxPlugin,
	PictureEditing,
	CloudServices,
	Essentials,
	FontFamily,
	FontSize,
	Heading,
	Highlight,
	Image,
	ImageCaption,
	ImageResize,
	ImageStyle,
	ImageToolbar,
	ImageUpload,
	Italic,
	Link,
	List,
	MediaEmbed,
	Paragraph,
	PasteFromOffice,
	RemoveFormat,
	Strikethrough,
	Table,
	TableToolbar,
	Underline,
	RevisionHistory,
	Comments,
	TrackChanges,
	ImportWord,
	ExportPdf,
	ExportWord,
	Autosave
];

ClassicEditorWithTrackChanges.defaultConfig = {
	// CKBox configuration is added only for the CKBox integration. This configuration should not be used in
	// a production environment. It is not needed for the track changes feature. See https://ckeditor.com/ckbox/
	ckbox: {
		tokenUrl: 'https://99330.cke-cs.com/token/dev/zwtfuY4mSYmklmXHFHgggY5VlLSuzAdxN9C0?limit=10'
	},
	toolbar: [
		'revisionHistory',
		'|',
		'importWord',
		'exportPdf',
		'exportWord',
		'heading',
		'|',
		'fontsize',
		'fontfamily',
		'|',
		'bold',
		'italic',
		'underline',
		'strikethrough',
		'removeFormat',
		'highlight',
		'|',
		'numberedList',
		'bulletedList',
		'|',
		'ckbox',
		'imageUpload',
		'link',
		'blockquote',
		'insertTable',
		'mediaEmbed',
		'|',
		'trackChanges',
		'comment',
		'commentsArchive',
		'|',
		'undo',
		'redo'
	],
	image: {
		toolbar: [
			'imageStyle:inline',
			'imageStyle:block',
			'imageStyle:side',
			'|',
			'toggleImageCaption',
			'imageTextAlternative',
			'|',
			'comment'
		]
	},
	table: {
		contentToolbar: [
			'tableColumn',
			'tableRow',
			'mergeTableCells'
		],
		tableToolbar: [ 'comment' ]
	},
	mediaEmbed: {
		toolbar: [ 'comment' ]
	},
	comments: {
		editorConfig: {
			extraPlugins: [ Bold, Italic, Underline, List, Autoformat ]
		}
	},
	exportPdf:{
		fileName: 'my-file.pdf',
		stylesheets: [
			'EDITOR_STYLES',
			'./styles.css'
		],
		converterOptions: {
			format: 'A4',
			margin_top: '20mm',
			margin_bottom: '20mm',
			margin_right: '12mm',
			margin_left: '12mm',
			page_orientation: 'portrait'
		}
	},
	exportWord: {
		stylesheets: [
			'EDITOR_STYLES',
			'./styles.css'
		],
		fileName: 'my-sample-file.docx',
		converterOptions: {
			format: 'A4',
			margin_top: '20mm',
			margin_bottom: '20mm',
			margin_right: '12mm',
			margin_left: '12mm'
		}
	},
	autosave:{
		
	}
};

export default {
	ClassicEditorWithTrackChanges,
	CKBox
};
