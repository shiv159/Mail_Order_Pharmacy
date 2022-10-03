package com.mailorderpharmacy.authservice.entity;

public class Jwtreturn {
	private String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public Jwtreturn(String jwt) {
		super();
		this.jwt = jwt;
	}
	public Jwtreturn()
	{
		
	}
	
}
