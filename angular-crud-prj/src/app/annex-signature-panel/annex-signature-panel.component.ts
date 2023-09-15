import { Component ,ElementRef, OnInit, ViewChild} from '@angular/core';
import SignaturePad from 'signature_pad';
import { StatementService } from '../service/statement.service';
import { AnnexFile } from '../model/annex';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-annex-signature-panel',
  templateUrl: './annex-signature-panel.component.html',
  styleUrls: ['./annex-signature-panel.component.css']
})
export class AnnexSignaturePanelComponent  implements OnInit{
  signatureNeeded!: boolean;

  signatureOfficalNeeded!: boolean;
  signatureWitnessNeeded!: boolean;
  signatureInterpreterNeeded!: boolean;
  
  signaturePad!: SignaturePad;
  signaturePadOfficial!:SignaturePad;
  signaturePadWitness!:SignaturePad;
  signaturePadInterpreter!:SignaturePad;
  

  @ViewChild('canvas') canvasEl!: ElementRef;
  @ViewChild('canvasOfficial') canvasOfficial!: ElementRef;
  @ViewChild('canvasWitness') canvasWitness!: ElementRef;
  @ViewChild('canvasInterpreter') canvasInterpreter!: ElementRef;
  

  signatureImg!: string;
  signatureImgOfficial!:string;
  signatureImgWitness!:string;
  signatureImgInterpreter!:string;


  id!: number;
  annexFile=new AnnexFile("","","","","",0,"","","","");

  constructor( private statementService: StatementService,
    private route: ActivatedRoute, private router: Router, ) { }

  ngOnInit(): void {
  this.getAnnexById();
  
  }
  ngAfterViewInit() {
   // this.signaturePad = new SignaturePad(this.canvasEl.nativeElement);
    this.signaturePadOfficial= new SignaturePad(this.canvasOfficial.nativeElement);
    this.signaturePadWitness=new SignaturePad(this.canvasWitness.nativeElement);
    this.signaturePadInterpreter=new SignaturePad(this.canvasInterpreter.nativeElement);
  }

  startDrawing(event: Event) {
    // works in device not in browser
  }

  moved(event: Event) {
    // works in device not in browser
  }

  clearPad() {
    this.signaturePad.clear();
  }

  clearPadOfficial() {
    this.signaturePadOfficial.clear();
  }

  clearPadWitness() {
    this.signaturePadWitness.clear();
  }

  clearPadInterpreter() {
    this.signaturePadInterpreter.clear();
  }

  savePad() {
    //const base64Data = this.signaturePad.toDataURL();
    //this.signatureImg = base64Data; //save it into DB
    console.log("It is here .. to save Pad")
    const base64DataOfficial=this.signaturePadOfficial.toDataURL();
    this.signatureImgOfficial=base64DataOfficial;

    console.log("based 64 "+this.signatureImgOfficial)

    const base64DataWitness=this.signaturePadWitness.toDataURL();
    this.signatureImgWitness=base64DataWitness;

    const base64DataInterpreter=this.signaturePadInterpreter.toDataURL();
    this.signatureImgInterpreter=base64DataInterpreter;


    this.signatureOfficalNeeded = this.signaturePadOfficial.isEmpty();
    if (!this.signatureOfficalNeeded) {
      this.signatureOfficalNeeded = false;
    }

    this.signatureWitnessNeeded = this.signaturePadWitness.isEmpty();
    if (!this.signatureWitnessNeeded) {
      this.signatureWitnessNeeded = false;
    }

    this.signatureInterpreterNeeded = this.signaturePadInterpreter.isEmpty();
    if (!this.signatureInterpreterNeeded) {
      this.signatureInterpreterNeeded = false;
    }

  }

  savePadOfficial() {
    const base64DataOfficial=this.signaturePadOfficial.toDataURL();
    this.signatureImgOfficial=base64DataOfficial;
    this.signatureNeeded = this.signaturePad.isEmpty();
    if (!this.signatureNeeded) {
      this.signatureNeeded = false;
    }


  }

  onSubmit() {
    console.log("To update "+this.annexFile);
    console.log("It is here .. to save Pad");
    const base64DataOfficial=this.signaturePadOfficial.toDataURL();
    this.signatureImgOfficial=base64DataOfficial;

    const base64DataWitness=this.signaturePadWitness.toDataURL();
    this.signatureImgWitness=base64DataWitness;

    const base64DataInterpreter=this.signaturePadInterpreter.toDataURL();
    this.signatureImgInterpreter=base64DataInterpreter;


    this.signatureOfficalNeeded = this.signaturePadOfficial.isEmpty();
    if (!this.signatureOfficalNeeded) {
      this.signatureOfficalNeeded = false;
    }

    this.signatureWitnessNeeded = this.signaturePadWitness.isEmpty();
    if (!this.signatureWitnessNeeded) {
      this.signatureWitnessNeeded = false;
    }

    this.signatureInterpreterNeeded = this.signaturePadInterpreter.isEmpty();
    if (!this.signatureInterpreterNeeded) {
      this.signatureInterpreterNeeded = false;
    }

    //if(this.signatureOfficalNeeded && this.signatureWitnessNeeded && this.signatureInterpreterNeeded){
      
    this.id = this.route.snapshot.params['id'];

    console.log("It is here .. to Update Pad");
    this.statementService.updateAnnexSignature(this.route.snapshot.params['id'],this.signatureImgOfficial,
        this.signatureImgWitness,this.signatureImgInterpreter).subscribe({
      next: (data) => {
        this.annexFile = data;
        alert("Update Successfully:" +this.annexFile.recordingId);
        this.routeannexsignature(this.annexFile.recordingId)
      },
      error: (e) => {
        console.log(e);
      }
    });
   // }

  }

  cancelSignature(){
    console.log("to cancel");
    this.routeannexsignature(this.annexFile.recordingId);
  }


  //this.redirectToUserList();
  routeannexsignature(id: string) {
    this.router.navigate(['update-statement', id]);
  }
  /*redirectToUserList(id:string) {
       this.router.navigate(['annexfileaddsignature', id]);
     }
*/
  private getAnnexById() {
    this.id = this.route.snapshot.params['id'];
    this.statementService.getAnnexById(this.id).subscribe({
      next: (data) => {
        this.annexFile = data;
      },
      error: (e) => {
        console.log(e);
      }
    });
}

}
