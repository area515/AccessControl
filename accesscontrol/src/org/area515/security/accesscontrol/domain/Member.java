package org.area515.security.accesscontrol.domain;


public class Member {

	String name;
	public String getName(){return name;}
	public void setName(String name){this.name = name;}
	
	String email;
	public String getEmail(){return email;}
	public void setEmail(String email){this.email = email;}
	
	Rfid rfid;
	public Rfid getRfid(){return rfid;}
	public void setRfid(Rfid rfid){this.rfid = rfid;}
	
	public Member(){
		this.name = "";
		this.email = "";
		this.rfid = new Rfid("","");
	}
	
	public Member(String name, String email, Rfid rfid){
		this.name = name;
		this.email = email;
		this.rfid = rfid;
	}
	
	public Member(String[] member){
		this.name = member[1];
		this.email = member[2];
		this.rfid = new Rfid(member[3], member[0]);
	}
	
}
