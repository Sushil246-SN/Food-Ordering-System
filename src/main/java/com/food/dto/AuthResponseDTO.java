package com.food.dto;

public class AuthResponseDTO {

	private String token;
	private String tokenType;
	private long expiresIn;
	
	public AuthResponseDTO(String token, String tokenType, Long expiresIn){
		 
		this.token=token;
		this.tokenType=tokenType;
		this.expiresIn=expiresIn;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public long getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
	
	
}
