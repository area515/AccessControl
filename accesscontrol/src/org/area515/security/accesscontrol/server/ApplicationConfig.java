package org.area515.security.accesscontrol.server;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.area515.security.accesscontrol.services.RfidService;

public class ApplicationConfig extends Application{

//	private static Set services = new HashSet(); 
//	 public  ApplicationConfig() {     
//	   // initialize restful services   
//	   services.add(new RfidService());  
//	 }
//	 @Override
//	 public  Set getSingletons() {
//	  return services;
//	 }  
//	 public  static Set getServices() {  
//	  return services;
//	 } 
	
	private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public ApplicationConfig() {
        singletons.add(new RfidService());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
	
}
