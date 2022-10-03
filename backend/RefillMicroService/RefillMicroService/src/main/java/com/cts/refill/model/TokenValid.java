package com.cts.refill.model;




public class TokenValid {
	
	private String uid;
	private String name;
	private boolean isValid;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isValid() {
		return isValid;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public TokenValid(String uid, String name, boolean isValid) {
		super();
		this.uid = uid;
		this.name = name;
		this.isValid = isValid;
	}
	
	public TokenValid()
	{
		
	}
}
