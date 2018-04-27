package com.admin.app.model;

public class UrlResponse {

	public String size;
	public String refresh_url;
	public String download_url;
	public String id;
	public String upload_url;
	public String expiration;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getRefresh_url() {
		return refresh_url;
	}

	public void setRefresh_url(String refresh_url) {
		this.refresh_url = refresh_url;
	}

	public String getDownload_url() {
		return download_url;
	}

	public void setDownload_url(String download_url) {
		this.download_url = download_url;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUpload_url() {
		return upload_url;
	}

	public void setUpload_url(String upload_url) {
		this.upload_url = upload_url;
	}

	public String getExpiration() {
		return expiration;
	}

	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}

	@Override
	public String toString() {
		return "UrlResponse [size=" + size + ", refresh_url=" + refresh_url + ", download_url=" + download_url + ", id="
				+ id + ", upload_url=" + upload_url + ", expiration=" + expiration + "]";
	}

}
