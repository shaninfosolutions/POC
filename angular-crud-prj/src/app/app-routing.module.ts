import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { EmployeeComponent } from './employee/employee.component';
import { AddEmployeeComponent } from './add-employee/add-employee.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { AuthGaurdService } from './service/auth-gaurd.service';
import { C2isuserComponent } from './c2isuser/c2isuser.component';
import { AddC2isuserComponent } from './add-c2isuser/add-c2isuser.component';
import { StatementComponent } from './statement/statement.component';
import { StatementUpdateComponent } from './statement-update/statement-update.component';
import { StatementCreateComponent } from './statement-create/statement-create.component';
import { EditorTrackChangeComponent } from './editor-track-change/editor-track-change.component';
import { RevisionHisotryAdapterComponent } from './revision-hisotry-adapter/revision-hisotry-adapter.component';
import { RevisionHistoryComponent } from './revision-history/revision-history.component';
import { AnnexComponent } from './annex/annex.component';
import { AnnexSignaturePanelComponent } from './annex-signature-panel/annex-signature-panel.component';
import { DigitalStatementComponent } from './digital-statement/digital-statement.component';

const routes: Routes = [
  { path: '', component: C2isuserComponent,canActivate:[AuthGaurdService] },
  { path: 'viewuser', redirectTo: 'viewuser', pathMatch: 'full' },
  { path: 'viewemployee', component: EmployeeComponent,canActivate:[AuthGaurdService] },
  { path: 'addemployee', component: AddEmployeeComponent,canActivate:[AuthGaurdService]},
  { path: 'adduser', component: AddC2isuserComponent,canActivate:[AuthGaurdService]},
  { path: 'viewstatement', component: StatementComponent,canActivate:[AuthGaurdService]},
  { path: 'update-statement/:id', component: StatementUpdateComponent,canActivate:[AuthGaurdService]},
  { path: 'create-statement', component: StatementCreateComponent,canActivate:[AuthGaurdService]}, 
  { path: 'login', component: LoginComponent },
  { path: 'logout', component: LogoutComponent,canActivate:[AuthGaurdService] },
  { path: 'editor-track-change', component: EditorTrackChangeComponent,canActivate:[AuthGaurdService]}, 
  { path: 'revision-history', component: RevisionHisotryAdapterComponent,canActivate:[AuthGaurdService]}, 
  { path: 'viewrevision', component: RevisionHistoryComponent,canActivate:[AuthGaurdService]},
  { path: 'update-revision/:id', component: RevisionHisotryAdapterComponent,canActivate:[AuthGaurdService]},
  { path: 'annexfileaddsignature/:id', component: AnnexSignaturePanelComponent,canActivate:[AuthGaurdService]},
  { path: 'statementpdf/:id', component: DigitalStatementComponent,canActivate:[AuthGaurdService]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
