package org.area515.security.accesscontrol.domain;

public class Rfid {

	String key;
	public String getKey(){return key;}
	public void setKey(String key){this.key = key;}
	
	String id;
	public String getId(){return id;}
	public void setId(String id){this.id = id;}
	
	public Rfid(){
		this.key = "";
		this.id = "";
	}
	
	public Rfid(String key, String id){
		this.key = key;
		this.id = id;
	}
	
	public Rfid(String[] rfid){
		this.key = rfid[0];
		this.id = rfid[1];
	}
	
	@Override
	public String toString(){
		return this.id + "|" + this.key;
	}
}
