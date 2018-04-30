import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { CreateUserComponent } from './create-user/create-user.component';
import { commonservice } from './services/commonservice';
import { Routes, Router, RouterModule } from '@angular/router';
import { HttpModule } from '@angular/http';
import {HttpClientModule} from '@angular/common/http';
import { OtheruserComponent } from './otheruser/otheruser.component';
import { ChatComponent } from './chat/chat.component';
import { Authendication } from './services/Authendiation';

const routes: Routes = [
  {path:'', component: CreateUserComponent},
  {path:'user1',component:CreateUserComponent}
]

@NgModule({
  declarations: [
    AppComponent,
    CreateUserComponent,
    OtheruserComponent,
    ChatComponent
  ],
  imports: [
    BrowserModule,RouterModule.forRoot(routes),HttpModule,HttpClientModule
  ],
  providers: [commonservice,Authendication],
  bootstrap: [AppComponent]
})
export class AppModule { }
