import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import PSPDFKit from "pspdfkit";
import { StatementService } from '../service/statement.service';

@Component({
  selector: 'app-pspdfkit-viewer',
  templateUrl: './pspdfkit-viewer.component.html',
  styleUrls: ['./pspdfkit-viewer.component.css']
})
export class PspdfkitViewerComponent {

  title = "PSPDFKit for Web Angular Example";
  id!: string;
  test1!:any;


  constructor( private statementService: StatementService,
    private route: ActivatedRoute, private router: Router,){}
  ngOnInit(): void {
  }
  ngAfterViewInit(): void {
    
    const test="/assets/example.pdf";
   
    this.id = this.route.snapshot.params['id'];
    console.log("The is is :" + this.id)
    this.statementService.downloadStatementPdfById(this.id).subscribe((response:any) => {
      
    })

    PSPDFKit.load({
		// Use the assets directory URL as a base URL. PSPDFKit will download its library assets from here.
      baseUrl: location.protocol + "//" + location.host + "/assets/",
      document:test,
      container: ".pspdfkit-container",
      licenseKey: "YOUR_LICENSE_KEY_GOES_HERE", // optional license key
    }).then((instance) => {
      // For the sake of this demo, store the PSPDFKit for Web instance
      // on the global object so that you can open the dev tools and
      // play with the PSPDFKit API.

      (<any>window).instance = instance;
    });
  }

}
