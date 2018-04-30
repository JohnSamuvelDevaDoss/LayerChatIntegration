import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Subject, BehaviorSubject } from "rxjs";
import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Observable';
import { Authendication } from './Authendiation';
import { requesturl } from '../models/requestUrl';
import { text } from '../models/message';
import { headersToString } from 'selenium-webdriver/http';

@Injectable()
export class commonservice {

    res: Response;
    constructor(public http: Http, public utils: Authendication) { }
    public url: string = "http://localhost:8080/api/requestUrl";
    public requestconversation: string = 'http://localhost:8080/api/messages';
    public nonceUrl:string = "";
    public tokenSeriveUrl:string = 'http://localhost:8080/api/gettoken';
    public urlRecieved: string = '';
    public texts: text[] = [];
    
    public accept = 'application/vnd.layer+json; version=3.0';
    requestUpload(formData:FormData) {
        console.log(formData.get("image"));
        return this.http.post(this.url,formData);
    }
    getConversations() {
        return this.http.get(this.requestconversation).map(res=><text[]>res.json());
    }

    getAuthendication(){
        let headers = new Headers();
        headers.append('Content-Type',' application/json');
        return this.http.post(this.tokenSeriveUrl,
            {nonce:"LUWMQ1tYzRa5hDcFySFC6QpS7_ZbeUMTUVR3W9AQ6DZtTxDBri_I6pKa_DQzwkebVMLzsMIezy-TNyBSLjcDpw"},
            {headers});
    }
   
}
// public requestUploadUrl: string = 'https://api.layer.com/apps/' + this.utils.AppId + '/conversations/'
//+ this.utils.ConversationId + '/content';
 /* messages() {
        let headers = new Headers();
        headers.append('Accept-Charset',this.accept);
        headers.set('Accept',this.accept);
        headers.set('Authorization','Bearer luYH2Whrz0G0t0lnexzGZWdj80lz2I8eZuuRIo9493SbIOoc');
        headers.set('Content-Type','application/json');
        const headers = new HttpHeaders({
            'Accept': 'application/vnd.layer+json; version=3.0; charset=utf-8',
            'Authorization': 'Bearer luYH2Whrz0G0t0lnexzGZWdj80lz2I8eZuuRIo9493SbIOoc; charset=utf-8',
            'Content-Type': 'application/json; charset=utf-8'
        });
        return this.http.get(this.Murl, { headers: headers });
    } */
//public mess: Subject<text[]> = new BehaviorSubject<text[]>(this.texts);
    //public Murl: string = 'https://api.layer.com/apps/d5467960-163c-11e8-94ab-5f1bfcfa24f9/conversations/' + this.utils.ConversationId + '/messages';