package com.admin.app.dao;

public interface GenerateTokenDao {
	
	public String getNounce();
	
	public String generateToken(String nonce);
	
	public String getSessionToken(String identityToken);
}
