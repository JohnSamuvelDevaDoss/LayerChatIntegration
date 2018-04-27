package com.admin.app.services;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.admin.app.dao.UploadDao;
import com.admin.app.model.Content;
import com.admin.app.model.Message;
import com.admin.app.model.Messages;
import com.admin.app.model.Parts;
import com.admin.app.model.UrlResponse;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class UploadService implements UploadDao {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RestTemplate restTemplate;

	public boolean uploadGoogle(MultipartFile file, String url) {

		try {
			// List<> map = new LinkedMultiValueMap<>();
			// map.add("file",file);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG);
			headers.setContentLength(file.getSize());

			HttpEntity<byte[]> requestEntity = new HttpEntity<byte[]>(file.getBytes(), headers);
			ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
			logger.info("responseEntity upload google :: " + result.getStatusCodeValue());
			if (result != null) {
				logger.debug("Status Code:" + result.getStatusCode());
				logger.debug("Response Body:" + result.getBody());
				logger.debug("Response Headers:" + result.getHeaders());
			}
			if (result.getStatusCodeValue() == 200) {
				return true;
			}
		} catch (Exception e) {
			logger.info("exception in uploadGoogle ");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String requestUrl(MultipartFile file) {

		boolean sent = false;
		boolean flag = false;
		try {
			logger.info("inside service " + file.getContentType());

			String AppId = "d5467960-163c-11e8-94ab-5f1bfcfa24f9";
			String ConversationId = "7def0629-ce28-4a52-96ed-62ebb4f56f5b";
			String AuthoraizationId = "luYH2Whrz0G0t0lnexzGZWdj80lz2I8eZuuRIo9493SbIOoc";
			String url = "https://api.layer.com/apps/" + AppId + "/conversations/" + ConversationId + "/content";

			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", "application/vnd.layer+json; version=3.0");
			headers.add("Authorization", "BEARER " + AuthoraizationId);
			headers.add("Content-Type", "application/json");
			headers.add("Upload-Content-Type", file.getContentType());
			headers.add("Upload-Content-Length", "" + file.getSize() + "");

			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<UrlResponse> result = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					UrlResponse.class);
			logger.info("responseEntity request upload :: " + result);
			if (result.getStatusCodeValue() == 200 || result.getStatusCodeValue() == 201) {
				String uploadurl = result.getBody().upload_url;
				flag = uploadGoogle(file, uploadurl);
				if (flag == true) {
					logger.info("file uploaded sending meseeage");
					sent = sendMessage(result, file);
				}
			}
			if (sent) {
				logger.info("message sent success");
				return "Success";
			} else {
				return "fail";
			}

		} catch (Exception e) {
			logger.info("exception in requestUrl");
			e.printStackTrace();
			logger.info("here message is " + e.getLocalizedMessage() + " " + e.toString());
		}
		return null;
	}

	public boolean sendMessage(ResponseEntity<UrlResponse> result, MultipartFile file) {
		boolean sent = false;

		try {

			String AppId = "d5467960-163c-11e8-94ab-5f1bfcfa24f9";
			String ConversationId = "7def0629-ce28-4a52-96ed-62ebb4f56f5b";
			String AuthoraizationId = "luYH2Whrz0G0t0lnexzGZWdj80lz2I8eZuuRIo9493SbIOoc";
			String url = "https://api.layer.com/apps/" + AppId + "/conversations/" + ConversationId + "/messages";

			HttpHeaders headers = new HttpHeaders();
			headers.add("Accept", "application/vnd.layer+json; version=3.0");
			headers.add("Authorization", "BEARER " + AuthoraizationId);
			headers.add("Content-Type", "application/json");

			Content content = new Content();
			content.setId(result.getBody().id);
			content.setSize(file.getSize());

			Parts part1 = new Parts();
			part1.setMime_type("text/plain");
			part1.setBody("this is the message");

			Parts part = new Parts();
			part.setMime_type(file.getContentType());
			part.setContent(content);

			List<Parts> parts = new ArrayList<>();
			parts.add(part1);
			parts.add(part);

			Messages message = new Messages();
			message.setSender_id("layer:///identities/0001");
			message.setParts(parts);
			ObjectMapper mapper = new ObjectMapper();
			String serialized = mapper.setDefaultPropertyInclusion(Include.NON_NULL).writeValueAsString(message);
			logger.info(serialized);

			HttpEntity<Messages> requestEntity = new HttpEntity<Messages>(message, headers);
			logger.info("" + requestEntity);
			ResponseEntity<String> sentMessage = restTemplate.exchange(url, HttpMethod.POST, requestEntity,
					String.class);
			logger.info("responseEntity sending message :: " + sentMessage);
			if (sentMessage.getStatusCodeValue() == 200 || sentMessage.getStatusCodeValue() == 201) {
				sent = true;
				return sent;
			}
		} catch (Exception e) {
			logger.info("exception in sendMessage");
			e.printStackTrace();
		}
		return sent;
	}

	@Override
	public List<Parts> requestmessages() {

		String AppId = "d5467960-163c-11e8-94ab-5f1bfcfa24f9";
		String ConversationId = "7def0629-ce28-4a52-96ed-62ebb4f56f5b";
		String AuthoraizationId = "luYH2Whrz0G0t0lnexzGZWdj80lz2I8eZuuRIo9493SbIOoc";
		String url = "https://api.layer.com/apps/" + AppId + "/conversations/" + ConversationId + "/messages";

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/vnd.layer+json; version=3.0");
		headers.add("Authorization", "BEARER " + AuthoraizationId);
		headers.add("Content-Type", "application/json");

		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		logger.info("" + requestEntity);
		ResponseEntity<Message[]> sentMessage = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
				Message[].class);
		logger.info("responseEntity getting messages :: " + sentMessage.getBody());
		List<Parts> list = new ArrayList<>();
		
		List<Parts> latestList = new ArrayList<>();
		Message[] singleText = sentMessage.getBody();
		latestList.add(singleText[singleText.length-1].getParts().get(singleText[singleText.length-1].getParts().size()));
		logger.info("latestList :: "+latestList);
		
		for (Message text : sentMessage.getBody()) {
			//list.clear();
			list.addAll(text.getParts());
			
		}

		return list;
	}
}
