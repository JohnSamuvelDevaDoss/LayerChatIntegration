import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { commonservice } from '../services/commonservice';
import { content } from '../models/Content';
import { pop } from '../models/pop';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  public texts: pop[] = [];
  public dynamichtml: boolean = false;
  @ViewChild('fileInput') fileInput : ElementRef;
  constructor(public com: commonservice) { }

  validImage(text) {
    if (text.image != undefined) {
      return true;
    } else {
      return false;
    }
  }
  validText(text) {
    if (text.text != undefined) {
      return true;
    } else {
      return false;
    }
  }
  call() {
    this.com.getConversations().subscribe(res => {
      this.texts = [];
      console.log(res);
      let data: pop = {};
      for (let t of res) {
        if (t.mime_type == "text/plain") {
          data.text = t.body;

        } else {
          data.image = t.content.download_url;
        }
        this.texts.push(data);
        //console.log(this.texts);
        
        data = {};
      }
      this.authendication();
    });
  }

  authendication(){
    this.com.getAuthendication().subscribe(res=>{
      console.log(res);
    });
  }
  upload() {

    let fileBrowser = this.fileInput.nativeElement;
    let formData:FormData = new FormData();
    if (fileBrowser.files && fileBrowser.files[0]) {
      console.log(fileBrowser.files[0]);
      formData.append("image", fileBrowser.files[0]);
      console.log(formData.getAll("image"));
      this.com.requestUpload(formData).subscribe(res => {
        console.log(res)
        if (res['_body'] == "Success") {
          this.call();
        }
        this.fileInput.nativeElement.value = "";
      });
    }
  }

  ngOnInit() {
    this.call();
    
    /* this.com.messages().subscribe(res =>{
      console.log(res);
    }) */
  }

}

