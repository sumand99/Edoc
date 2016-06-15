package com.infogain.edoc.controller;


import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.infogain.edoc.dao.EmployeeDao;
import com.infogain.edoc.dao.FileCategoryDao;
import com.infogain.edoc.dao.FileDataDao;
import com.infogain.edoc.dao.PreEmployeeDao;
import com.infogain.edoc.dao.UserDao;
import com.infogain.edoc.exception.RegistrationException;
import com.infogain.edoc.model.AddRole;
import com.infogain.edoc.model.FileCategory;
import com.infogain.edoc.model.FileData;
import com.infogain.edoc.model.PreEmployee;
import com.infogain.edoc.model.Registration;
import com.infogain.edoc.utils.JsonConverter;

/**
 * 
 */
@Controller
public class HomeController 
{
		
	@Autowired
	Environment env;
	
	@Value("${category}")
	private String category;
	
	@Value("${fileLocation}")
	private String fLocation;
	
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private PreEmployeeDao preEmpDao;
	
	@Autowired
	private FileCategoryDao fileCategoryDao;
	
	@Autowired
	private FileDataDao fileDataDao;
	
	@Autowired
	private UserDao userDao;
	
	/**
	 * This method handles the request for home page.
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "login";
	}

	
	/**
	 * This method handles the request for error page.
	 * @return
	 */
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error() {
		return "error";
	}
	
	/**
	 * This method handles the request for access denied page.
	 * @return
	 */
	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String accessDenied()
	{		
		return "accessdenied";
	}
	
	/**
	 * This method handles the request for access denied page.
	 * @return
	 */
	@RequestMapping(value = "/accessdeniedForLogin", method = RequestMethod.GET)
	public String accessDeniedForLogin()
	{		
		return "accessdeniedForLogin";
	}
	
	/**
	 * This method handles the request for register page.
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register()
	{		
		return "register";
	}
	
	/**
	 * This method handles the request for user registration page.
	 * @param register
	 * @param fileLocation
	 * @return
	 * @throws RegistrationException
	 */
	@RequestMapping(value = "/userRegistration", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> userRegistration(@RequestBody Registration register,@Value("${fileLocation}") String fileLocation) throws RegistrationException
	{		
		HashMap<String, Object> status = new HashMap<String, Object>();
		status.put("status", preEmpDao.registration(register, fileLocation));
		return status;
	}
	
	/**
	 * This method handles the request for login page.
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() 
	{
		return "login";
	}
	
	/**
	 * This method handles the request for logout page.
	 * @return
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout() {
		return "logout";
	}

	/**
	 * This method handles the request for pre-employee or employee landing page.
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/landingpage", method = RequestMethod.GET)
	public ModelAndView landingpage(HttpSession session) 
	{	
		if(session.getAttribute("user") == null){
			ModelAndView modelAndView = new ModelAndView("login");
			return modelAndView;
		}
		ModelAndView modelAndView = new ModelAndView("landingpage");
		modelAndView.addObject("landingpageDataResponse", JsonConverter.getJson(landingpagepanel(session)));
		modelAndView.addObject("bgcData", JsonConverter.getJson(empDao.backgroundCheckPersonalDetail((Integer)session.getAttribute("selectedApplicationId"))));
		return modelAndView;
	}
	
	/**
	 *  This method handles the request for pre-employee or employee landing page using specified application id.
	 * @param applicationId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/landingpage/{applicationId}", method = RequestMethod.GET)
	public ModelAndView hrLandingpage(@PathVariable("applicationId") int applicationId, HttpSession session) 
	{
		if(session.getAttribute("user") == null){
			ModelAndView modelAndView = new ModelAndView("login");
			return modelAndView;
		}
		PreEmployee preEmp =  preEmpDao.getPreEmployee(applicationId);
		session.setAttribute("preEmp", preEmp);
		session.setAttribute("selectedApplicationId", preEmp.getApplicationId());
		ModelAndView modelAndView = new ModelAndView("landingpage");
		modelAndView.addObject("landingpageDataResponse", JsonConverter.getJson(landingpagepanel(session)));
		modelAndView.addObject("bgcData", JsonConverter.getJson(empDao.backgroundCheckPersonalDetail((Integer)session.getAttribute("selectedApplicationId"))));
		return modelAndView;
	}
	
	/**
	 * This method returns landing page data to the client.
	 * @param session
	 * 
	 * @return
	 */
	
	@RequestMapping(value = "/landingpagepanel", method = RequestMethod.GET)
	public HashMap<String, Object> landingpagepanel(HttpSession session)
	{	
		int applicationId = (Integer) session.getAttribute("selectedApplicationId");
		List<FileData> fileList = fileDataDao.listRootFiles(applicationId);
		List<FileCategory> folderList = fileCategoryDao.rootFolderList(applicationId, session);
		int parentCategoryId = fileCategoryDao.getRootId(applicationId);
		int uploadedDocumentCount = fileDataDao.getNoOfUploadedFiles(applicationId);
		PreEmployee preEmp = preEmpDao.getPreEmployee(applicationId);
		HashMap<String, Object> landingPage = new HashMap<String, Object>();
		landingPage.put("fileList", fileList);
		landingPage.put("folderList", folderList);
		landingPage.put("parentCategoryId", parentCategoryId);
		landingPage.put("uploadedDocumentCount", uploadedDocumentCount);
		landingPage.put("preEmp", preEmp);
		return landingPage;
	}
	
	/**
	 * This method handles request for opening a folder
	 * @param parentCategoryId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = {"/insideFolder/{parentCategoryId}"}, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> insideFolder(@PathVariable("parentCategoryId") int parentCategoryId, HttpSession session)
	{	
		int applicationId = (Integer) session.getAttribute("selectedApplicationId");
		List<FileData> fileList = fileDataDao.listFolderFiles(applicationId, parentCategoryId);
		List<FileCategory> folderList = fileCategoryDao.folderList(applicationId, parentCategoryId, session);
		PreEmployee preEmp = preEmpDao.getPreEmployee(applicationId);
		HashMap<String, Object> folderPage = new HashMap<String, Object>();
		folderPage.put("fileList", fileList);
		folderPage.put("folderList", folderList);
		folderPage.put("parentCategoryId", parentCategoryId);
		folderPage.put("preEmp", preEmp);
		return folderPage;
	}
	
	/**
	 * This method handles request for opening a folder
	 * @param parentCategoryId
	 * @param session
	 * @return
	 */
	@RequestMapping(value = {"/landingpage/insideFolder/{parentCategoryId}"}, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> insideFolderForAdmin(@PathVariable("parentCategoryId") int parentCategoryId, HttpSession session)
	{	
		int applicationId = (Integer) session.getAttribute("selectedApplicationId");
		List<FileData> fileList = fileDataDao.listFolderFiles(applicationId, parentCategoryId);
		List<FileCategory> folderList = fileCategoryDao.folderList(applicationId, parentCategoryId, session);
		PreEmployee preEmp = preEmpDao.getPreEmployee(applicationId);
		HashMap<String, Object> folderPage = new HashMap<String, Object>();
		folderPage.put("fileList", fileList);
		folderPage.put("folderList", folderList);
		folderPage.put("parentCategoryId", parentCategoryId);
		folderPage.put("preEmp", preEmp);
		return folderPage;
	}
	
	@RequestMapping(value = "/addrole", method = RequestMethod.GET)
	public ModelAndView addrole() {
		ModelAndView mv = new ModelAndView("addrole");
		mv.addObject("userList", JsonConverter.getJson(userDao.getUserList()));
		mv.addObject("rmgList", JsonConverter.getJson(userDao.getRMGList()));
		mv.addObject("rmgAdminList", JsonConverter.getJson(userDao.getRMGAdminList()));
		mv.addObject("hrList", JsonConverter.getJson(userDao.getAdminList()));
	
		return mv;
	}
	
	@RequestMapping(value = "/addNewRole", method = RequestMethod.POST)
	public @ResponseBody String addNewRole(@RequestBody AddRole addRole) {
		empDao.addRole(addRole);

		return "success";
	}
	
	@RequestMapping(value = "/removeUserRole", method = RequestMethod.POST)
	public @ResponseBody String removeUserRole(@RequestBody AddRole addRole) {
		empDao.removeRole(addRole);

		return "success";
	}
}
