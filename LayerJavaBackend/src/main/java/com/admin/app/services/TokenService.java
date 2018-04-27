package com.admin.app.services;

import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.pkcs.RSAPrivateKey;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.admin.app.dao.GenerateTokenDao;
import com.admin.app.model.Message;
import com.admin.app.utils.TokenConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;

@Service
public class TokenService implements GenerateTokenDao {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private RestTemplate restTemplate;
	
	@SuppressWarnings("resource")
	@Override
	public String generateToken(String nonce) {

		String token1 = "";
		try {
			/*StringReader reader = new StringReader("-----BEGIN RSA PRIVATE KEY-----\r\n"
					+ "MIIEowIBAAKCAQEAtODjXkRbKR9GPW96yj2r3znpGckBTE6c9wkTKJAh0BEMilr6\r\n"
					+ "Z4UJ5ynnfRiun13GL/tJBSTKfVJkkTwO3dfiq0h5RLYkZEKRgVSUuFu/o8lNc8ss\r\n"
					+ "FkkzXtcher74Rwu8SPZAud6h6yJGj/CbjUMAwoU41Ux79Yrbyjd/cC1ouo5XWcoO\r\n"
					+ "U7JdJzRCzqxSnMGQAOVYcEuAfC3l5JmiTq+bnGTWLRFzsllrKy3AUakpZXvPZJqR\r\n"
					+ "FLpRX/BZA8OY3Jv/SXtabrrrv0WBWevlBBxsqLnmD9oHEsLRSwjSOwTScKRIIg3C\r\n"
					+ "P302tZuen7wiKkcfnjOu3dDZ9R+YPyjLn0KCEQIDAQABAoIBABj+p6S35PIAipwE\r\n"
					+ "Ou9WCNlavwmrY1TIWzvu4tkc27NNG9LFoisqcPUi7wR0I/NNSc0JGCzQCfQ57cA9\r\n"
					+ "EJ/azI2XQPy7/7jkEwfQaJG2Sfvn3h5VcdTtjh35s/xqhdQJfN4HO2gD6hOcQ6xx\r\n"
					+ "1wj0/JakwrcE7+89HkSqGR/nAn0e4UP4fb1FS62aUU1Gu8UPLkbqsRP4RMCeqYiU\r\n"
					+ "bZAqIhvsfvsZTIDbe4AsLYREzOVf74/xLKTpgoZncYaBO4r/txwbi9QpfZGb4ZRD\r\n"
					+ "fdtypOsfhXHlQsPNgBJraMDs1HAKDrLvIEz+eQ5n6PtshFcj5SJzJ1qz5Qlg/47Z\r\n"
					+ "qARjLH0CgYEA5B+rxLpDBn6i7wuqU2m2asjA/wLVxvGAEuN4AdMZMShggCxBKnhm\r\n"
					+ "3dJdKYUtUWcq9hUNknf0rcgFl00f+BINEwx79a2YOnGocDzaJtpugDoc+SioSDyJ\r\n"
					+ "0iDIHDawPd1677MI4gxPSJaqtPLITI/a5cTjtiE5g40zdc8xGj5+0a0CgYEAyvtB\r\n"
					+ "9mn+WLthSCB9AoUHTjurVcw0IVR6W8HOOdGUYw8/Fg0+V/3qCI4ArJveTYMAnqFb\r\n"
					+ "qjWeWw2+u0xRGVb0/qGnneuMojgx7wormOv2t4OqyCELqgtH4+ybvhIjB+Wclh8K\r\n"
					+ "UyeL60iLjDhQB4Fpy1OG6mNFqHlDgUJCc2SBJnUCgYA3n7FWasRlFQPNnd6OMDyW\r\n"
					+ "lRTaGWre84vzDxv39e0ym1HMbv4EyQ7sD5s/wIVoT+udzUxd5nn21crnNZsR9fTS\r\n"
					+ "LD0djpKLzaLwmyXmS2SeR41BA7TkyD4iynOAzaTzsMmFIPaNh8rdPD+rl3dXk8wg\r\n"
					+ "Mn4Drt5DQW7Gzxp997cckQKBgDTKtnQ1Sw6e5YVR1dGS8bx6LmypKKHGi/BTbqSB\r\n"
					+ "Crvc2kaM+Tr/ch/5dtltzJqSSNX5F5MkUORTpdim16zkfeG//41GFxSV0v7dwUsL\r\n"
					+ "i+Zc200jsxntxyvTfQopQ20pXdl5h0DrVyvBeXHlr4LUOMTGG5/YY+SWbWUv64KB\r\n"
					+ "kEmFAoGBAMr977c8JaraMfPIPlC6EtecsykDLcFuLGAo+Ui0R/wIyheJDBwJgJJK\r\n"
					+ "eCo/FUrIEkjY04QRPgNQd3J5pNb/EKS4iDjXlxq7eK9zeDx40N15dyIHWuX2Vhhh\r\n"
					+ "kNtVLgt304hvwp910SNuI6+RW30iUhF7FlnrWXfrmh6oqUulRKRr\r\n" + "-----END RSA PRIVATE KEY-----\r\n"
					+ "");*/
			String content = new String(Files.readAllBytes(Paths.get("src/main/resources/keycore.txt")));
			StringReader reader = new StringReader(content);
			PemReader pemReader = new PemReader(reader);
			PemObject pem = pemReader.readPemObject();
			byte[] der = pem.getContent();
			ASN1InputStream in = new ASN1InputStream(der);
			ASN1Primitive primitive = in.readObject();
			RSAPrivateKey key = RSAPrivateKey.getInstance(primitive);
			RSAPrivateKeySpec spec = new RSAPrivateKeySpec(key.getModulus(), key.getPrivateExponent());
			KeyFactory factory = KeyFactory.getInstance("RSA");

			factory.generatePrivate(spec);

			Map<String, String> map = new HashMap<String, String>();
			map.put("iss", TokenConstants.PROVIDER_ID);
			map.put("prn", TokenConstants.USER_ID);
			map.put("nce", nonce);//"LUWMQ1tYzRa5hDcFySFC6QpS7_ZbeUMTUVR3W9AQ6DZtTxDBri_I6pKa_DQzwkebVMLzsMIezy-TNyBSLjcDpw");
			map.put("first_name", "john");
			map.put("last_name", "samuvel");
			map.put("avatar_url", "http://sillylordoftheringspictures.com/frodo-riding-a-dodo.png");
			map.put("display_name", "Frodo the Dodo");
			Claims claims = new DefaultClaims();
			claims.putAll(map);

			Map<String, Object> headermap = new HashMap<String, Object>();
			headermap.put("kid", TokenConstants.KEY_ID);
			headermap.put("cty", TokenConstants.CONTENT_TYPE);
			headermap.put("alg", "RS256");
			headermap.put("typ", "JWT");

			token1 = Jwts.builder().setClaims(claims).setSubject("john")
					.setIssuedAt(new Date(System.currentTimeMillis())).setHeaderParams(headermap)
					.setExpiration(new Date(System.currentTimeMillis() + TokenConstants.EXPIRATION_TIME))
					.signWith(SignatureAlgorithm.RS256,
							(java.security.interfaces.RSAPrivateKey) factory.generatePrivate(spec))
					.compact();

			logger.info("generated Token :: " + token1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return token1;
	}

	@Override
	public String getNounce() {
		
		String nonceUrl = "https://api.layer.com/nonces";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/vnd.layer+json; version=3.0");
		headers.add("Content-Type", "application/json");
		
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		logger.info("" + requestEntity);
		ResponseEntity<String> getNonce = restTemplate.exchange(nonceUrl, HttpMethod.POST, requestEntity,
				String.class);
		logger.info("responseEntity getting nonce :: " + getNonce);
		return null;
	}

	@Override
	public String getSessionToken(String identityToken) {
		
		String sessionTokenUrl = "https://api.layer.com/sessions";
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", "application/vnd.layer+json; version=3.0");
		headers.add("Content-Type", "application/json");
		
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		logger.info("" + requestEntity);
		ResponseEntity<String> getSession = restTemplate.exchange(sessionTokenUrl, HttpMethod.POST, requestEntity,
				String.class);
		logger.info("responseEntity getting nonce :: " + getSession);
		return null;
	}

}
