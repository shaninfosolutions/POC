import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { EmployeeComponent } from './employee/employee.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule,HTTP_INTERCEPTORS} from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { C2isuserComponent } from './c2isuser/c2isuser.component';
import { AddC2isuserComponent } from './add-c2isuser/add-c2isuser.component';
import { EditorComponent } from './editor/editor.component';
import { StatementComponent } from './statement/statement.component';
import { StatementUpdateComponent } from './statement-update/statement-update.component';
import { StatementCreateComponent } from './statement-create/statement-create.component';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { EditorTrackChangeComponent } from './editor-track-change/editor-track-change.component';
import { BasicAuthInterceptorService } from './service/basic-auth-interceptor.service';
import { RevisionHisotryAdapterComponent } from './revision-hisotry-adapter/revision-hisotry-adapter.component';
import { RevisionHistoryComponent } from './revision-history/revision-history.component';
import { AnnexComponent } from './annex/annex.component';
import { AnnexSignaturePanelComponent } from './annex-signature-panel/annex-signature-panel.component';
import { DigitalStatementComponent } from './digital-statement/digital-statement.component';



@NgModule({
  declarations: [
    AppComponent,
    AddEmployeeComponent,
    EmployeeComponent,
    FooterComponent,
    HeaderComponent,
    LoginComponent,
    LogoutComponent,
    C2isuserComponent,
    AddC2isuserComponent,
    EditorComponent,
    StatementComponent,
    StatementUpdateComponent,
    StatementCreateComponent,
    EditorTrackChangeComponent,
    RevisionHisotryAdapterComponent,
    RevisionHistoryComponent,
    AnnexComponent,
    AnnexSignaturePanelComponent,
    DigitalStatementComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    CKEditorModule
   
  ],
  providers: [{  
    provide:HTTP_INTERCEPTORS, useClass:BasicAuthInterceptorService, multi:true 
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
