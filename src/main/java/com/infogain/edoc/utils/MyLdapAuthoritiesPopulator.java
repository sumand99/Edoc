package com.infogain.edoc.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapAuthoritiesPopulator;
import org.springframework.stereotype.Component;

import com.infogain.edoc.dao.EmployeeDao;
import com.infogain.edoc.dao.PreEmployeeDao;
import com.infogain.edoc.dao.UserDao;
import com.infogain.edoc.exception.RegistrationException;
import com.infogain.edoc.model.Employee;
import com.infogain.edoc.model.Registration;
import com.infogain.edoc.model.User;

@Component("myLDAPAuthPopulator")
public class MyLdapAuthoritiesPopulator implements LdapAuthoritiesPopulator {

	@Value("${fileLocation}")
	private String fLocation;

	@Autowired
	private UserDao userDao;

	@Autowired
	private PreEmployeeDao preEmpDao;

	@Autowired
	private EmployeeDao empDao;

	@Override
	public Collection<? extends GrantedAuthority> getGrantedAuthorities(
			DirContextOperations userData, String username) {
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		User user = userDao.searchUser(username);
		
		List<String> roleList = userDao.getRoles(username);
		if (!roleList.isEmpty()) {
			user.setRoles(roleList);

			for (String role : roleList) {
				System.out.println(role);
				authorities.add(new SimpleGrantedAuthority(role));
			}
		} else {
			Employee emp = empDao.getEmployeeFromAspire(username);
			if(emp != null){
			Registration register = new Registration();
			List<String> email = new ArrayList<String>();
			register.setFirstName(emp.getFirstName());
			register.setLastName(emp.getLastName());
			/* email.add(emp.getEmail()); */
			email.add(username + "@infogain.com");
			register.setEmail(email);
			try {
				preEmpDao
						.existingEmployeeRegistration(emp, register, fLocation);
				List<String> userRoleList = userDao.getRoles(username);
				for (String role : userRoleList) {
					System.out.println(role);
					authorities.add(new SimpleGrantedAuthority(role));
				}
			} catch (RegistrationException e) {

				e.printStackTrace();
			}
		}
		}
		// authorities.add(new SimpleGrantedAuthority(user.getRoles()));

		return authorities;
	}

}