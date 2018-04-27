package com.admin.app.dao;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.admin.app.model.Parts;

public interface UploadDao {

	public String requestUrl(MultipartFile file);

	public List<Parts> requestmessages();
}
