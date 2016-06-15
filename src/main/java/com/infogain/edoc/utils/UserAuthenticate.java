package com.infogain.edoc.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.infogain.edoc.dao.EmployeeDao;
import com.infogain.edoc.dao.PreEmployeeDao;
import com.infogain.edoc.dao.UserDao;
import com.infogain.edoc.exception.RegistrationException;
import com.infogain.edoc.model.Employee;
import com.infogain.edoc.model.Registration;
import com.infogain.edoc.model.User;



@Component
public class UserAuthenticate implements AuthenticationProvider {
 
	@Autowired
	UserDao userDao;
	
	@Value("${fileLocation}")
	private String fLocation;
	
	@Value("${keystore}")
	private String keystore;
	
	@Value("${java.naming.factory.initial}")
	private String ldapInitial;
	
	@Value("${java.naming.security.authentication}")
	private String ldapAuthentication;
	
	@Value("${java.naming.provider.url}")
	private String ldapUrl;
	
	@Autowired
	private PreEmployeeDao preEmpDao;
	
	@Autowired
	private EmployeeDao empDao;
	
    @Override
    public Authentication authenticate(Authentication authentication) 
      throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
       System.out.println(authentication.toString());
        // use the credentials to try to authenticate against the third party system
        if (verifyDomainUser("igglobal", name, password)) {
        	 List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        	    User user = userDao.searchUser(name);
        	    
        	    List<String> roleList = userDao.getRoles(name);
        	    if(!roleList.isEmpty()){
        	    user.setRoles(roleList);
        	    
        	    for(String role : roleList)
        	    {
        	    	System.out.println(role);
        	    	authorities.add(new SimpleGrantedAuthority(role));  
        	    }
        	    }
        	    else {
        	        Employee emp = empDao.getEmployeeFromAspire(name);
        	        emp.setEmpUsername(name);
        	        Registration register = new Registration();
        	        List<String> email = new ArrayList<String>();
        	        register.setFirstName(emp.getFirstName());
        	        register.setLastName(emp.getLastName());
        	        /*email.add(emp.getEmail());*/
        	        email.add(name + "@infogain.com");
        	        register.setEmail(email);
        	        try {
        	   		preEmpDao.existingEmployeeRegistration(emp, register, fLocation);
        	   		List<String> userRoleList = userDao.getRoles(name);
        	   		for(String role : userRoleList)
        	   	    {
        	   	    	System.out.println(role);
        	   	    	authorities.add(new SimpleGrantedAuthority(role));  
        	   	    }
        	   	} catch (RegistrationException e) {
        	   		
        	   		e.printStackTrace();
        	   	}
        	       }
            return new UsernamePasswordAuthenticationToken(name, password, authorities);
        } else {
        	try {
				throw new javax.naming.AuthenticationException("unable to authenticate");
			} catch (javax.naming.AuthenticationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            /*throw new AuthenticationException("Unable to auth against third party systems");*/
        	
        }
		return null;
    }
 
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    private boolean verifyDomainUser(String domain, String userName, String password)
    {
      boolean status = false;
      System.out.println("User Name to Login: " + userName);
      System.out.println("domain::" + domain);
      if ((userName != null) && (password != null))
      {
        Hashtable env = new Hashtable();

        if (password.equals("")) {
          password = "blank";
        }
        if (userName.equals("")) {
          userName = "blank";
        }
        //String keystore = "/usr/java/jdk1.5.0_01/jre/lib/security/cacerts";
        System.setProperty("javax.net.ssl.trustStore", keystore);

        //env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
        env.put("java.naming.factory.initial", ldapInitial);

        //env.put("java.naming.security.authentication", "simple");
        env.put("java.naming.security.authentication", ldapAuthentication);

        env.put("java.naming.security.credentials", password);

        //env.put("java.naming.provider.url", "ldap://172.18.65.35:3268");
        env.put("java.naming.provider.url", ldapUrl);

        LdapContext ctx = null;
        try
        {
          env.put("java.naming.security.principal", domain + "\\" + userName);

          ctx = new InitialLdapContext(env, null);

          System.out.println("URL:::" + ctx.getEnvironment().get("java.naming.provider.url"));

          if (ctx != null) {
            status = true;
          }

        }
        catch (AuthenticationException e)
        {
          System.err.println("Authentication Error");
          System.err.println(e.getMessage());
          try
          {
            if (ctx != null)
              ctx.close();
          }
          catch (Exception en) {
            System.err.println("Final Error");
            System.err.println(en.getMessage());
          }
        }
        catch (NamingException e)
        {
          System.err.println("Connection Error");
          System.err.println(e.getMessage());
          try
          {
            if (ctx != null)
              ctx.close();
          }
          catch (Exception ex) {
            System.err.println("Final Error");
            System.err.println(ex.getMessage());
          }
        }
        catch (Exception e)
        {
          System.out.println("Exception E");
          e.printStackTrace();
          try
          {
            if (ctx != null)
              ctx.close();
          }
          catch (Exception et) {
            System.err.println("Final Error");
            System.err.println(et.getMessage());
          }
        }
        finally
        {
          try
          {
            if (ctx != null)
              ctx.close();
          }
          catch (Exception e) {
            System.err.println("Final Error");
            System.err.println(e.getMessage());
          }
        }
      }

      System.out.println("statuss:::::::::::;" + status);
      return status;
    }
}