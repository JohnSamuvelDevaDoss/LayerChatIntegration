package com.admin.app.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.admin.app.dao.GenerateTokenDao;
import com.admin.app.dao.UploadDao;
import com.admin.app.model.Parts;

@RestController
public class UploadController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private UploadDao uploadDao;
	
	@Autowired
	private GenerateTokenDao generateTokenDao;

	@CrossOrigin
	@RequestMapping(value = "/requestUrl", method = RequestMethod.POST)
	public String requestUrl(@RequestParam("image") MultipartFile file) {
		String flag="success";
		/*String flag = uploadDao.requestUrl(file);
		logger.info("returning response "+flag);*/

		return flag;
	}

	@CrossOrigin
	@RequestMapping(value = "/messages", method = RequestMethod.GET)
	public List<Parts> requestmessages() {
		logger.info("inside get message");
		httpSession.setAttribute("name", "sucess");
		List<Parts> parts = uploadDao.requestmessages();
		return parts;
	}

	@CrossOrigin
	@RequestMapping(value = "/gettoken", method = RequestMethod.POST)
	public String tokenGenerator(@RequestBody Map<String, String> nonce) {
		String token = "";
		/*if(nonce!=null && nonce.get("nonce")!="") {
		token = generateTokenDao.generateToken(nonce.get("nonce"));
		}*/
		logger.info(token);
		return (String) httpSession.getAttribute("name");
	}

}
