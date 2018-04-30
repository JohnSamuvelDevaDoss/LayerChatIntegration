import { content } from "./Content";

export interface text{
 id?:string,
	 url?:string,
	 message_url?:string,
	 mime_type?:string,
	 body?:string,
	 encoding?:string,
	 content?:content,
     updated_at?:string
}