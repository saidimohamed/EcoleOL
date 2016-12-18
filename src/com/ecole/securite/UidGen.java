package com.ecole.securite;

import java.util.UUID;

public class UidGen {

	private UUID uid;

	public String getUid() {
		
		uid=UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");
		uid = UUID.randomUUID();
		String suid =uid.toString().replace("-", "");
		return suid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	 /*  public static void main(String[] args) {
	   // creating UUID      
	   UUID uid = UUID.fromString("38400000-8cf0-11bd-b23e-10b96e4ef00d");     
	        
	   // checking the value of random UUID
	   System.out.println("Random UUID value: "+uid.randomUUID());    
	   }    */
	}