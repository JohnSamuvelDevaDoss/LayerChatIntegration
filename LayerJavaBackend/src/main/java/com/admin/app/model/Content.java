package com.admin.app.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Content {

	public String id;
	public long size;
	public String download_url;
	public String refresh_url;
	public String expiration;
	public String upload_url;

	public String getDownload_url() {
		return download_url;
	}

	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	public String getRefresh_url() {
		return refresh_url;
	}

	public void setRefresh_url(String refresh_url) {
		this.refresh_url = refresh_url;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	public String getUpload_url() {
		return upload_url;
	}

	public void setUpload_url(String upload_url) {
		this.upload_url = upload_url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Content [id=" + id + ", size=" + size + ", download_url=" + download_url + ", refresh_url="
				+ refresh_url + ", expiration=" + expiration + ", upload_url=" + upload_url + "]";
	}

}
