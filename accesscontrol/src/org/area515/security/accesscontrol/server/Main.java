package org.area515.security.accesscontrol.server;

import java.util.Collections;

import org.eclipse.jetty.security.ConstraintMapping;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.security.authentication.BasicAuthenticator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.security.Constraint;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;


/*
 * References:
 * http://news-anand.blogspot.com/2012/05/today-i-am-going-tell-you-how-to-create.html
 * http://alvinalexander.com/java/jwarehouse/jetty-6.1.9/etc/realm.properties.shtml
 * http://www.eclipse.org/jetty/documentation/9.1.4.v20140401/embedded-examples.html#embedded-secured-hello-handler
 */

public class Main {

	public static void main(String[] args) throws Exception {
		
		 Server server = new Server(9091);
		  ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		  context.setContextPath("/");
		  ServletHolder h = new ServletHolder(new HttpServletDispatcher());
		  h.setInitParameter("resteasy.role.based.security", String.valueOf(true));
//		  h.setInitParameter("resteasy.servlet.mapping.prefix", "/*");
//		  h.setInitParameter("resteasy.use.builtin.providers", String.valueOf(true));
//		  h.setInitParameter("resteasy.media.type.mappings","json : application/json, xml : application/xml");
		  h.setInitParameter("javax.ws.rs.Application", "org.area515.security.accesscontrol.server.ApplicationConfig");
		  context.addServlet(h, "/*");
		//  server.setHandler(context);
		  
		//Since this example is for our test webapp, we need to setup a LoginService so this shows how to create a
		  // very simple hashmap based one.  The name of the LoginService needs to correspond to what is configured a
		  // webapp's web.xml and since it has a lifecycle of its own we register it as a bean with the Jetty server
		  // object so it can be started and stopped according to the lifecycle of the server itself. In this example
		  // the name can be whatever you like since we are not dealing with webapp realms.
		  LoginService loginService = new HashLoginService("MyRealm", "C:\\nonsync\\realm\\realm.properties"); //"src/test/resources/realm.properties");
		  server.addBean(loginService); 

		  // A security handler is a jetty handler that secures content behind a particular portion of a url space. The
		  // ConstraintSecurityHandler is a more specialized handler that allows matching of urls to different
		  // constraints. The server sets this as the first handler in the chain,
		  // effectively applying these constraints to all subsequent handlers in the chain.
		  ConstraintSecurityHandler security = new ConstraintSecurityHandler();
		  server.setHandler(security);

		  // This constraint requires authentication and in addition that an authenticated user be a member of a given
		  // set of roles for authorization purposes.
		  Constraint constraint = new Constraint();
		  constraint.setName("auth");
		  constraint.setAuthenticate( true );
		  constraint.setRoles(new String[]{"rfid","write","read","delete"});

		  // Binds a url pattern with the previously created constraint. The roles for this constraing mapping are
		  // mined from the Constraint itself although methods exist to declare and bind roles separately as well.
		  ConstraintMapping mapping = new ConstraintMapping();
		  mapping.setPathSpec( "/*" );
		  mapping.setConstraint( constraint );

		  // First you see the constraint mapping being applied to the handler as a singleton list,
		  // however you can passing in as many security constraint mappings as you like so long as they follow the
		  // mapping requirements of the servlet api. Next we set a BasicAuthenticator instance which is the object
		  // that actually checks the credentials followed by the LoginService which is the store of known users, etc.
		  security.setConstraintMappings(Collections.singletonList(mapping));
		  security.setAuthenticator(new BasicAuthenticator());
		  security.setLoginService(loginService);

		  // The Hello Handler is the handler we are securing so we create one, and then set it as the handler on the
		  // security handler to complain the simple handler chain.
		//  HelloHandler hh = new HelloHandler();

		  // chain the hello handler into the security handler
		  security.setHandler(context);
		  HostProperties.init();
		  try {
		   server.start();
		   server.join();
		  } catch (Exception e) {
		   e.printStackTrace();
		  }


		
	}
}