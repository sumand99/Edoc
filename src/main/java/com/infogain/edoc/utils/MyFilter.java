package com.infogain.edoc.utils;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.infogain.edoc.dao.EmployeeDao;
import com.infogain.edoc.dao.PreEmployeeDao;
import com.infogain.edoc.dao.impl.UserDaoImpl;
import com.infogain.edoc.model.Employee;
import com.infogain.edoc.model.PreEmployee;
import com.infogain.edoc.model.User;


public class MyFilter implements AuthenticationSuccessHandler 
{
	@Autowired
	UserDaoImpl userDao;
	
	@Autowired
	private PreEmployeeDao preEmpDao;
	
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	ServletContext servletContext;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException 
	{	
		
		String selectedRole = request.getParameter("selectedRole");
		HttpSession session = request.getSession();
		
		User user = userDao.searchUser(authentication.getName());
		
		List<String> roles = userDao.getRoles(user.getUsername());
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
		if(!roles.contains(selectedRole))
		{
			System.out.println("Invalid Role");
			response.sendRedirect(servletContext.getContextPath()+"/login?msg=invalidRole");
		}
		else
		{
			roles.clear();
			roles.add(selectedRole);
			
			user.setRoles(roles);
			
			for(String r : roles)
			{
				if(r.equalsIgnoreCase("ROLE_ADMIN"))
				{
					Employee emp = empDao.getEmployee(user.getUsername());
					session.setAttribute("emp", emp);
					session.setAttribute("user", user);
					session.setAttribute("userName", emp.getFirstName());
					
					response.sendRedirect(servletContext.getContextPath()+"/dashboard");
				}
				if(r.equalsIgnoreCase("ROLE_RMG"))
				{
					Employee emp = empDao.getEmployee(user.getUsername());
					session.setAttribute("emp", emp);
					session.setAttribute("user", user);
					session.setAttribute("userName", emp.getFirstName());
					
					response.sendRedirect(servletContext.getContextPath()+"/rmgdashboard");
				}
				if(r.equalsIgnoreCase("ROLE_RMG_ADMIN"))
				{
					Employee emp = empDao.getEmployee(user.getUsername());
					session.setAttribute("emp", emp);
					session.setAttribute("user", user);
					session.setAttribute("userName", emp.getFirstName());
					
					response.sendRedirect(servletContext.getContextPath()+"/rmgadmindashboard");
				}
				if(r.equalsIgnoreCase("ROLE_USER"))
				{
					PreEmployee preEmp =  preEmpDao.getPreEmployee(user.getUsername());
					
					if(preEmp == null)
					{
						Employee emp = empDao.getEmployee(user.getUsername());
						
						session.setAttribute("preEmp", emp);
						session.setAttribute("selectedApplicationId", emp.getApplicationId());
						session.setAttribute("userName", emp.getFirstName());
						session.setAttribute("user", user);
						
						if(preEmpDao.isEmployeeEnabled(emp.getApplicationId()))
						{
							response.sendRedirect(servletContext.getContextPath()+"/landingpage");
						}
						else
						{
							response.sendRedirect(servletContext.getContextPath()+"/accessdeniedForLogin");
							//session.invalidate();
						}
						
						
					}
					else
					{
						session.setAttribute("preEmp", preEmp);
						session.setAttribute("selectedApplicationId", preEmp.getApplicationId());
						session.setAttribute("userName", preEmp.getFirstName());
						session.setAttribute("user", user);
						
						if(preEmpDao.isEmployeeEnabled(preEmp.getApplicationId()))
						{
							response.sendRedirect(servletContext.getContextPath()+"/landingpage");
						}
						else
						{
							response.sendRedirect(servletContext.getContextPath()+"/accessdeniedForLogin");
							//session.invalidate();
						}
					
					}
		
					System.out.println(user.toString());
					/*response.sendRedirect(servletContext.getContextPath()+"/landingpage");*/
				}
			}
		}
		/*PreEmployee preEmp =  preEmpDao.getPreEmployee(user.getUsername());
		
		session.setAttribute("user", user);
		session.setAttribute("preEmp", preEmp);
		session.setAttribute("selectedApplicationId", preEmp.getApplicationId());
		
		System.out.println(user.toString());
		//response.sendRedirect(servletContext.getContextPath()+"/landingpage");
*/		
		/*response.sendRedirect(servletContext.getContextPath());*/
		
	}

	
	

}
