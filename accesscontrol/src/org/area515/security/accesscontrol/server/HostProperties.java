package org.area515.security.accesscontrol.server;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;


public class HostProperties {

	/*
	 * TODO: Need to clean up. 
	 * 
	 */
	
	private static HostProperties m_instance = null;
	public static HostProperties Instance() throws IOException {
		if (m_instance == null) {
			m_instance = new HostProperties();
		}
		return m_instance;
	}
	
	public HostProperties() throws IOException{HostProperties.init();}
	
//	static String membersFile = "";
//	public String getMembersFile(){
//		return membersFile;
//	}
	
	static String aclFile = "";
	public String getAclFile(){
		return aclFile;
	}
	
	
	/*
	 * init()
	 * Check if "/home/user/rfid/rfidreader/acl" exists
	 * Create the file and directories if it doesn't exist
	 * (user is whoever is running this)
	 */
	
	public static void init() throws IOException{
		//win
//		aclFile = new File("C:\\dev\\acl").getAbsolutePath();
		
		//nix
		aclFile = new File(System.getProperty("user.home"),"rfid/rfidreader/acl").getAbsolutePath();		
		System.out.println(System.getProperty("user.home"));
		
		if(!new File(aclFile).exists()){
//			throw new IOException("ACL file doesn't exist at: " + new File(aclFile).getAbsolutePath());
		FileUtils.forceMkdir(new File(aclFile));
		}
//		System.out.println("ACL file is located at: " + aclFile);
		
	}
	
//	public static Properties getHostProperties() throws IOException{
//		Properties properties = new Properties();
//		properties.load(HostProperties.class.getClassLoader().getResourceAsStream("config.properties"));
//		return properties;
//	}
	
}
