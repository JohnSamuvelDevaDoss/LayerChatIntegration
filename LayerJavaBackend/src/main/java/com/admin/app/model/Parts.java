package com.admin.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Parts {

	public String id;
	public String url;
	public String message_url;
	public String mime_type;
	public String body;
	public String encoding;
	public Content content;
	@JsonIgnore
	public String updated_at;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMessage_url() {
		return message_url;
	}

	public void setMessage_url(String message_url) {
		this.message_url = message_url;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}

	public String getMime_type() {
		return mime_type;
	}

	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}

	public Content getContent() {
		return content;
	}

	public void setContent(Content content) {
		this.content = content;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Parts [id=" + id + ", url=" + url + ", message_url=" + message_url + ", mime_type=" + mime_type
				+ ", body=" + body + ", encoding=" + encoding + ", content=" + content + ", updated_at=" + updated_at
				+ "]";
	}

}
