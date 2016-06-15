package com.infogain.edoc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.infogain.edoc.dao.EmployeeDao;
import com.infogain.edoc.dao.PreEmployeeDao;
import com.infogain.edoc.exception.MakeEmployeeException;
import com.infogain.edoc.model.BackgroundCheckFormData;
import com.infogain.edoc.model.DashboardData;
import com.infogain.edoc.model.EmployeeEducationalBackground;
import com.infogain.edoc.model.EmployeeEmploymentDetail;
import com.infogain.edoc.model.EmployeeFamilyDetail;
import com.infogain.edoc.model.EmployeeOtherDetail;
import com.infogain.edoc.model.EmployeePersonalDetail;
import com.infogain.edoc.model.EmployeeQualification;
import com.infogain.edoc.model.EmployeeReference;
import com.infogain.edoc.model.EmployeeSalaryDetail;
import com.infogain.edoc.model.EmployeeTrainingAttended;
import com.infogain.edoc.model.EmploymentDetail;
import com.infogain.edoc.model.HrBgcApprove;
import com.infogain.edoc.model.MakeEmployee;
import com.infogain.edoc.model.OtherDetail;
import com.infogain.edoc.model.SkillDetail;
import com.infogain.edoc.model.Skills;
import com.infogain.edoc.model.SubmitData;
import com.infogain.edoc.model.User;
import com.infogain.edoc.model.WelcomeTemplateData;
import com.infogain.edoc.utils.JsonConverter;

/**
 * This class handles url requests related to an Employee.
 * 
 * @author amant.kumar
 *
 */
@Controller
public class EmployeeController {
	@Autowired
	private PreEmployeeDao preEmpDao;
	@Autowired
	private EmployeeDao empDao;
	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	/**
	 * This method handles request for the admin dashboard page. The value in
	 * request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * 
	 * @param session
	 * @return ModelAndView
	 * @see ModelAndView
	 */
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpSession session) {
		if (session.getAttribute("user") == null) {
			ModelAndView modelAndView = new ModelAndView("login");
			return modelAndView;
		}
		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		if (roles.isEmpty()) {
			System.out.println("Role is empty");
		} else {
			role = (String) roles.get(0);
		}
		ModelAndView modelAndView = new ModelAndView("dashboard");
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(role);
		int totalCount = preEmpDao.getCount("", role);
		modelAndView.addObject("dashboardResponse",
				JsonConverter.getJson(empList));
		modelAndView.addObject("totalCount", totalCount);
		return modelAndView;
	}

	/**
	 * This method handles request for the admin dashboard page when a
	 * particular employee is searched. The value in request mapping annotation
	 * gives the requested urls and method defines the supported methods.
	 * 
	 * @param session
	 *            The Session object
	 * @param offset
	 *            Number of rows to be skipped.
	 * @param searchTerm
	 *            String to be searched.
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 */
	@RequestMapping(value = "/dashboard/{offset}/{searchTerm}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> dashboard(
			@PathVariable("offset") int offset,
			@PathVariable(value = "searchTerm") String searchTerm,
			HttpSession session) {
		HashMap<String, Object> dashboardData = new HashMap<String, Object>();

		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		role = (String) (roles.isEmpty() ? null : roles.get(0));
		if (role == null) {
			logger.info("Role is empty");
		} 
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(offset,
				searchTerm, role);
		int totalCount = preEmpDao.getCount(searchTerm, role);

		dashboardData.put("empList", empList);
		dashboardData.put("totalCount", totalCount);
		return dashboardData;
	}

	/**
	 * This method handles request for the admin dashboard page when a
	 * particular page is selected. The value in request mapping annotation
	 * gives the requested urls and method defines the supported methods.
	 * 
	 * @param session
	 * @param offset
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @deprecated
	 */
	@Deprecated
	@RequestMapping(value = "/dashboard/{offset}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> dashboard(
			@PathVariable(value = "offset") int offset, HttpSession session) {
		HashMap<String, Object> dashboardData = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		role = (String) (roles.isEmpty() ? null : roles.get(0));
		if (role == null) {
			logger.info("Role is empty");
		} 
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(offset, "",
				role);
		int totalCount = preEmpDao.getCount("", role);
		dashboardData.put("empList", empList);
		dashboardData.put("totalCount", totalCount);
		return dashboardData;
	}

	/**
	 * This method handles request for the rmg dashboard page. The value in
	 * request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * 
	 * @param session
	 * @return ModelAndView
	 * @see ModelAndView
	 * 
	 */
	@RequestMapping(value = "/rmgdashboard", method = RequestMethod.GET)
	public ModelAndView rmgDashboard(HttpSession session) {
		if (session.getAttribute("user") == null) {
			ModelAndView modelAndView = new ModelAndView("login");
			return modelAndView;
		}
		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		role = (String) (roles.isEmpty() ? null : roles.get(0));
		if (role == null) {
			logger.info("Role is empty");
		} 
		ModelAndView modelAndView = new ModelAndView("rmgdashboard");
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(role);
		int totalCount = preEmpDao.getCount("", role);
		modelAndView.addObject("rmgDashboardResponse",
				JsonConverter.getJson(empList));
		modelAndView.addObject("totalCount", totalCount);
		return modelAndView;
	}

	/**
	 * This method handles request for the rmg dashboard page when a particular
	 * employee is searched. The value in request mapping annotation gives the
	 * requested urls and method defines the supported methods.
	 * 
	 * @param session
	 *            The Session object
	 * @param offset
	 *            Number of rows to be skipped.
	 * @param searchTerm
	 *            String to be searched.
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 */
	@RequestMapping(value = "/rmgdashboard/{offset}/{searchTerm}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> rmgDashboard(
			@PathVariable("offset") int offset,
			@PathVariable(value = "searchTerm") String searchTerm,
			HttpSession session) {
		HashMap<String, Object> dashboardData = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		role = (String) (roles.isEmpty() ? null : roles.get(0));
		if (role == null) {
			logger.info("Role is empty");
		} 
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(offset,
				searchTerm, role);
		int totalCount = preEmpDao.getCount(searchTerm, role);
		dashboardData.put("empList", empList);
		dashboardData.put("totalCount", totalCount);
		return dashboardData;
	}

	/**
	 * This method handles request for the rmg dashboard page when a particular
	 * page is selected. The value in request mapping annotation gives the
	 * requested urls and method defines the supported methods.
	 * @param session
	 * @param offset
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @deprecated
	 */
	@Deprecated
	@RequestMapping(value = "/rmgdashboard/{offset}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> rmgDashboard(
			@PathVariable(value = "offset") int offset, HttpSession session) {
		HashMap<String, Object> dashboardData = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		role = (String) (roles.isEmpty() ? null : roles.get(0));
		if (role == null) {
			logger.info("Role is empty");
		} 
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(offset, "",
				role);
		int totalCount = preEmpDao.getCount("", role);
		dashboardData.put("empList", empList);
		dashboardData.put("totalCount", totalCount);
		return dashboardData;
	}

	/**
	 * This method handles request for the rmg admin dashboard page. The value
	 * in request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * 
	 * @param session
	 * @return ModelAndView
	 * @see ModelAndView
	 * 
	 */
	@RequestMapping(value = "/rmgadmindashboard", method = RequestMethod.GET)
	public ModelAndView rmgadminDashboard(HttpSession session) {
		if (session.getAttribute("user") == null) {
			ModelAndView modelAndView = new ModelAndView("login");
			return modelAndView;
		}
		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		role = (String) (roles.isEmpty() ? null : roles.get(0));
		if (role == null) {
			logger.info("Role is empty");
		} 
		ModelAndView modelAndView = new ModelAndView("rmgadmindashboard");
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(role);
		int totalCount = preEmpDao.getCount("", role);
		modelAndView.addObject("rmgAdminDashboardResponse",
				JsonConverter.getJson(empList));
		modelAndView.addObject("totalCount", totalCount);
		return modelAndView;
	}

	/**
	 * This method handles request for the rmg admin dashboard page when a
	 * particular employee is searched. The value in request mapping annotation
	 * gives the requested urls and method defines the supported methods.
	 * 
	 * @param session
	 *            The Session object
	 * @param offset
	 *            Number of rows to be skipped.
	 * @param searchTerm
	 *            String to be searched.
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 */
	@RequestMapping(value = "/rmgadmindashboard/{offset}/{searchTerm}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> rmgadminDashboard(
			@PathVariable("offset") int offset,
			@PathVariable(value = "searchTerm") String searchTerm,
			HttpSession session) {
		HashMap<String, Object> dashboardData = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		role = (String) (roles.isEmpty() ? null : roles.get(0));
		if (role == null) {
			logger.info("Role is empty");
		} 
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(offset,
				searchTerm, role);
		int totalCount = preEmpDao.getCount(searchTerm, role);
		dashboardData.put("empList", empList);
		dashboardData.put("totalCount", totalCount);
		return dashboardData;
	}

	/**
	 * This method handles request for the rmg admin dashboard page when a
	 * particular page is selected. The value in request mapping annotation
	 * gives the requested urls and method defines the supported methods.
	 * 
	 * @param session
	 * @param offset
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @deprecated
	 */
	@Deprecated
	@RequestMapping(value = "/rmgadmindashboard/{offset}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> rmgadminDashboard(
			@PathVariable(value = "offset") int offset, HttpSession session) {
		HashMap<String, Object> dashboardData = new HashMap<String, Object>();
		User user = (User) session.getAttribute("user");
		List roles = user.getRoles();
		String role = "";
		role = (String) (roles.isEmpty() ? null : roles.get(0));
		if (role == null) {
			logger.info("Role is empty");
		} 
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(offset, "",
				role);
		int totalCount = preEmpDao.getCount("", role);
		dashboardData.put("empList", empList);
		dashboardData.put("totalCount", totalCount);
		return dashboardData;
	}
	
	@RequestMapping(value = "/workflowstats", method = RequestMethod.GET)
	public ModelAndView workflowStats(HttpSession session) {
		if (session.getAttribute("user") == null) {
			ModelAndView modelAndView = new ModelAndView("login");
			return modelAndView;
		}
		
		ModelAndView modelAndView = new ModelAndView("workflowStats");
		List<DashboardData> empList = preEmpDao.getPreEmployeeList("");
		int totalCount = preEmpDao.getCount("", "");
		modelAndView.addObject("workflowStatsResponse",
				JsonConverter.getJson(empList));
		modelAndView.addObject("totalCount", totalCount);
		return modelAndView;
	}

	/**
	 * This method handles request for the rmg dashboard page when a particular
	 * employee is searched. The value in request mapping annotation gives the
	 * requested urls and method defines the supported methods.
	 * 
	 * @param session
	 *            The Session object
	 * @param offset
	 *            Number of rows to be skipped.
	 * @param searchTerm
	 *            String to be searched.
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 */
	@RequestMapping(value = "/workflowstats/{offset}/{searchTerm}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> workflowStats(
			@PathVariable("offset") int offset,
			@PathVariable(value = "searchTerm") String searchTerm,
			HttpSession session) {
		HashMap<String, Object> workflowData = new HashMap<String, Object>();
		
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(offset,
				searchTerm, "");
		int totalCount = preEmpDao.getCount(searchTerm, "");
		workflowData.put("empList", empList);
		workflowData.put("totalCount", totalCount);
		return workflowData;
	}

	/**
	 * This method handles request for the rmg dashboard page when a particular
	 * page is selected. The value in request mapping annotation gives the
	 * requested urls and method defines the supported methods.
	 * @param session
	 * @param offset
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @deprecated
	 */
	@Deprecated
	@RequestMapping(value = "/workflowstats/{offset}", method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> workflowStats(
			@PathVariable(value = "offset") int offset, HttpSession session) {
		HashMap<String, Object> workflowData = new HashMap<String, Object>();
		
		List<DashboardData> empList = preEmpDao.getPreEmployeeList(offset, "",
				"");
		int totalCount = preEmpDao.getCount("", "");
		workflowData.put("empList", empList);
		workflowData.put("totalCount", totalCount);
		return workflowData;
	}

	/**
	 * This method handles request when a pre-employee is confirmed as an
	 * employee. The value in request mapping annotation gives the requested
	 * urls and method defines the supported methods.
	 * @param session
	 * @param makeEmp
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see MakeEmployee
	 * 
	 */
	@RequestMapping(value = "/landingpage/makeEmployee", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> makeEmployee(
			@RequestBody MakeEmployee makeEmp, HttpSession session)
			throws MakeEmployeeException {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> empCrearted = new HashMap<String, Object>();
		String status = empDao.makeEmployee(makeEmp, applicationId);
		System.out.println(status);
		empCrearted.put("empCrearted", status);
		return empCrearted;
	}

	/**
	 * This method handles request when an employee or pre-employee submits his
	 * documents. The value in request mapping annotation gives the requested
	 * urls and method defines the supported methods.
	 * @param session
	 * @param submit
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see SubmitData
	 *
	 */
	@RequestMapping(value = "/submitData", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> submitData(
			@RequestBody SubmitData submit, HttpSession session) {
		HashMap<String, Object> submitData = new HashMap<String, Object>();
		submitData.put("submitData",
				preEmpDao.submitData(submit.getApplicationId()));
		return submitData;
	}

	/**
	 * This method handles request when an employee background check is
	 * approved. The value in request mapping annotation gives the requested
	 * urls and method defines the supported methods.
	 * @param hrBgcApproveList
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see HrBgcApprove
	 *
	 */
	@RequestMapping(value = "/approveBgc", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> approveBGC(
			@RequestBody List<HrBgcApprove> hrBgcApproveList) {
		HashMap<String, Object> bgcMap = new HashMap<String, Object>();
		bgcMap.put("bgcApprovedList",
				preEmpDao.approveBgc(hrBgcApproveList, true));
		return bgcMap;
	}

	/**
	 * This method handles request when an employee is rejected. The value in
	 * request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * @param session
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @deprecated
	 */
	@Deprecated
	@RequestMapping(value = "/landingpage/rejectEmployee", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> rejectEmployee(
			HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> empCrearted = new HashMap<String, Object>();
		String status = empDao.rejectEmployee(applicationId);
		System.out.println(status);
		empCrearted.put("empCrearted", status);
		return empCrearted;
	}

	/**
	 * This method handles request when an employee background check is
	 * initiated. The value in request mapping annotation gives the requested
	 * urls and method defines the supported methods.
	 * @param hrBgcInitiateList
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see HrBgcApprove
	 *
	 */
	@RequestMapping(value = "/initiateBgc", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> initiateBGC(
			@RequestBody List<HrBgcApprove> hrBgcInitiateList) {
		HashMap<String, Object> bgcMap = new HashMap<String, Object>();
		bgcMap.put("bgcInitiatedList",
				preEmpDao.approveBgc(hrBgcInitiateList, false));
		return bgcMap;
	}

	/**
	 * This method handles request when an employee is approved by rmg. The
	 * value in request mapping annotation gives the requested urls and method
	 * defines the supported methods.
	 * @param rmgApproveList
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see SubmitData
	 *
	 */
	@RequestMapping(value = "/approveRmg", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> approveRmg(
			@RequestBody List<SubmitData> rmgApproveList) {
		HashMap<String, Object> rmgMap = new HashMap<String, Object>();
		rmgMap.put("rmgApprovedList", preEmpDao.approveRmg(rmgApproveList));
		return rmgMap;
	}

	/**
	 * This method handles request when an employee is approved by rmg admin.
	 * The value in request mapping annotation gives the requested urls and
	 * method defines the supported methods.
	 * @param rmgAdminApproveList
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see SubmitData
	 *
	 */
	@RequestMapping(value = "/approveRmgAdmin", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> approveRmgAdmin(
			@RequestBody List<SubmitData> rmgAdminApproveList) {
		HashMap<String, Object> rmgAdminMap = new HashMap<String, Object>();
		rmgAdminMap.put("rmgAdminApprovedList",
				preEmpDao.approveRmgAdmin(rmgAdminApproveList));
		return rmgAdminMap;
	}

	/**
	 * This method handles request when an employee is rejected by rmg admin.
	 * The value in request mapping annotation gives the requested urls and
	 * method defines the supported methods.
	 * @param rmgRejectList
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see SubmitData
	 *
	 */
	@RequestMapping(value = "/rejectRmg", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> rejectRmg(
			@RequestBody List<SubmitData> rmgRejectList) {
		HashMap<String, Object> rmgMap = new HashMap<String, Object>();
		rmgMap.put("rmgRejectedList", preEmpDao.rejectRmg(rmgRejectList));
		return rmgMap;
	}

	/**
	 * This method handles request when an employee is rejected by hr. The value
	 * in request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * @param rmgAdminRejectList
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see SubmitData
	 *
	 */
	@RequestMapping(value = "/rejectRmgAdmin", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> rejectRmgAdmin(
			@RequestBody List<SubmitData> rmgAdminRejectList) {
		HashMap<String, Object> rmgAdminMap = new HashMap<String, Object>();
		rmgAdminMap.put("rmgAdminRejectedList",
				preEmpDao.rejectRmgAdmin(rmgAdminRejectList));
		return rmgAdminMap;
	}

	/**
	 * This method handles request when an employee is enabled by hr. The value
	 * in request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/landingpage/enableUser", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> enableUser(HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> status = new HashMap<String, Object>();
		status.put("status",
				preEmpDao.enableDisableEmployee(applicationId, true));
		return status;
	}

	/**
	 * This method handles request when an employee is disabled by hr. The value
	 * in request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/landingpage/disableUser", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> disableUser(HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> status = new HashMap<String, Object>();
		status.put("status",
				preEmpDao.enableDisableEmployee(applicationId, false));
		return status;
	}

	/**
	 * This method handles request for employee application. It sends data to
	 * populate on client machine if the form was previously filled by the
	 * employee. The value in request mapping annotation gives the requested
	 * urls and method defines the supported methods.
	 * @param session
	 * @return ModelAndView
	 * @see ModelAndView
	 */
	@RequestMapping(value = { "/employeeapplicationform",
			"/landingpage/employeeapplicationform" }, method = RequestMethod.GET)
	public ModelAndView employeeApplicationForm(HttpSession session) {
		if (session.getAttribute("user") == null) {
			ModelAndView modelAndView = new ModelAndView("login");
			return modelAndView;
		}
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		int editFlag = preEmpDao.getPreEmployee(applicationId).getEditFlag();
		int totalEditFlag = preEmpDao.getPreEmployee(applicationId).getTotalEditFlag();
		ModelAndView modelAndView = new ModelAndView("employeeapplicationform");
		HashMap<String, Object> empApplicationFormData = new HashMap<String, Object>();
		empApplicationFormData.put("editFlag", editFlag);
		empApplicationFormData.put("totalEditFlag", totalEditFlag);
		empApplicationFormData.put("empPersonalDetail",
				empDao.getEmployeePersonalDetail(applicationId));
		empApplicationFormData.put("empEducationalBackgroundList",
				empDao.getEmployeeEducationalBackgroundList(applicationId));
		empApplicationFormData.put("empTrainingAttendedList",
				empDao.getEmployeeTrainingAttendedList(applicationId));
		empApplicationFormData.put("empEmploymentDetailList",
				empDao.getEmployeeEmploymentDetailList(applicationId));
		empApplicationFormData.put("empCurrentEmploymentDetail",
				empDao.getCurrentEmploymentDetail(applicationId));
		empApplicationFormData.put("empSalaryDetail",
				empDao.getEmployeeSalaryDetail(applicationId));
		empApplicationFormData.put("empFamilyDetailList",
				empDao.getEmployeeFamilyDetailList(applicationId));
		empApplicationFormData.put("empReferenceList",
				empDao.getEmployeeReferenceList(applicationId));
		empApplicationFormData.put("empOtherDetail",
				empDao.getEmployeeOtherDetail(applicationId));
		empApplicationFormData.put("empSkillList",
				empDao.getEmployeeSkillsList(applicationId));
		empApplicationFormData.put("educationFormData",
				empDao.getEducationFormData());
		empApplicationFormData.put("backgroundCheckEmploymentDetail",
				empDao.backgroundCheckEmploymentDetail(applicationId));
		empApplicationFormData.put("backgroundCheckEducationalDetail",
				empDao.backgroundCheckEducationalDetail(applicationId));
		empApplicationFormData.put("backgroundCheckPersonalDetail",
				empDao.backgroundCheckPersonalDetail(applicationId));
		modelAndView.addObject("empApplicationFormData",
				JsonConverter.getJson(empApplicationFormData));
		return modelAndView;
	}

	/**
	 * This method handles request to save Employee Personal Details. The value
	 * in request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * @param session
	 * @param employeepersonalDetail
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see EmployeePersonalDetail
	 */
	@RequestMapping(value = "/saveEmployeePersonalDetail", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> saveEmployeePersonalDetail(
			@RequestBody EmployeePersonalDetail employeepersonalDetail,
			HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> employeeRegistration = new HashMap<String, Object>();
		employeeRegistration.put("employeeData", empDao
				.saveEmployeePersonalDetail(employeepersonalDetail,
						applicationId));
		return employeeRegistration;
	}

	/**
	 * This method handles request to save Employee qualifications. The value in
	 * request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * @param session
	 * @param employeeQualification
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see EmployeeQualification
	 */
	@RequestMapping(value = "/saveEmployeeQualification", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> saveEmployeeQualification(
			@RequestBody EmployeeQualification employeeQualification,
			HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> employeeRegistration = new HashMap<String, Object>();
		empDao.deleteTable(applicationId, "emp_education_background");
		empDao.deleteTable(applicationId, "emp_training_attended");
		for (EmployeeEducationalBackground employeeEducationalBackground : employeeQualification
				.getEmployeeEducationalBackgroundList()) {
			empDao.saveEmployeeEducationalBackground(
					employeeEducationalBackground, applicationId);
		}
		for (EmployeeTrainingAttended employeeTrainingDetail : employeeQualification
				.getEmployeeTrainingAttendedList()) {
			empDao.saveEmployeeTrainingDetail(employeeTrainingDetail,
					applicationId);
		}
		return employeeRegistration;
	}

	/**
	 * This method handles request to save Employee previous employment details.
	 * The value in request mapping annotation gives the requested urls and
	 * method defines the supported methods.
	 * @param session
	 * @param employmentDetail
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see EmploymentDetail
	 */
	@RequestMapping(value = "/saveEmploymentDetail", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> saveEmploymentDetail(
			@RequestBody EmploymentDetail employmentDetail, HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> employeeRegistration = new HashMap<String, Object>();
		empDao.deleteTable(applicationId, "emp_employment_detail");
		empDao.deleteTable(applicationId, "emp_current_employment_detail");
		for (EmployeeEmploymentDetail employeeEmploymentDetail : employmentDetail
				.getEmployeeEmploymentDetailList()) {
			empDao.saveEmployeeEmploymentDetail(employeeEmploymentDetail,
					applicationId);
		}
		empDao.saveCurrentEmploymentDetail(
				employmentDetail.getCurrentEmploymentDetail(), applicationId);
		return employeeRegistration;
	}

	/**
	 * This method handles request to save Employee previous salary details. The
	 * value in request mapping annotation gives the requested urls and method
	 * defines the supported methods.
	 * 
	 * @param session
	 * @param employeeSalaryDetail
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see EmployeeSalaryDetail
	 */
	@RequestMapping(value = "/saveEmployeeSalaryDetail", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> saveEmployeeSalaryDetail(
			@RequestBody EmployeeSalaryDetail employeeSalaryDetail,
			HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> employeeRegistration = new HashMap<String, Object>();
		employeeRegistration.put("employeeSalaryData", empDao
				.saveEmployeeSalaryDetail(employeeSalaryDetail, applicationId));
		return employeeRegistration;
	}

	/**
	 * This method handles request to save Employee Miscellaneous details. The
	 * value in request mapping annotation gives the requested urls and method
	 * defines the supported methods.
	 * 
	 * @param session
	 * @param otherDetail
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see OtherDetail
	 */
	@RequestMapping(value = "/saveOtherDetail", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> saveOtherDetail(
			@RequestBody OtherDetail otherDetail, HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> employeeRegistration = new HashMap<String, Object>();
		empDao.deleteTable(applicationId, "emp_family_detail");
		empDao.deleteTable(applicationId, "emp_reference");
		empDao.deleteTable(applicationId, "emp_other_detail");
		for (EmployeeFamilyDetail employeeFamilyDetail : otherDetail
				.getEmployeeFamilyDetailList()) {
			empDao.saveEmployeeFamilyDetail(employeeFamilyDetail, applicationId);
		}
		for (EmployeeReference employeeReference : otherDetail
				.getEmployeeReferenceList()) {
			empDao.saveEmployeeReference(employeeReference, applicationId);
		}
		for (EmployeeOtherDetail employeeOtherDetail : otherDetail
				.getEmployeeOtherDetail())
			empDao.saveEmployeeOtherDetail(employeeOtherDetail, applicationId);
		return employeeRegistration;
	}

	/**
	 * This method handles request to save Employee skill details. The value in
	 * request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * @param session
	 * @param skillDetail
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see SkillDetail
	 */
	@RequestMapping(value = "/saveBackgroundCheckDetail", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> saveBackgroundCheckForm(
			@RequestBody BackgroundCheckFormData backgroundCheckFormData, HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		empDao.deleteTable(applicationId, "background_check_employment_detail");
		empDao.deleteTable(applicationId, "background_check_educational_detail");
		empDao.deleteTable(applicationId, "background_check_personal_detail");
		HashMap<String, Object> backgroundCheckData = new HashMap<String, Object>();
		empDao.saveBackgroundCheckDetail(backgroundCheckFormData, applicationId);
		return backgroundCheckData;
	}

	/**
	 * This method handles request to save Employee skill details. The value in
	 * request mapping annotation gives the requested urls and method defines
	 * the supported methods.
	 * @param session
	 * @param skillDetail
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 * @see SkillDetail
	 */
	@RequestMapping(value = "/saveSkillDetail", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> saveSkillDetail(
			@RequestBody SkillDetail skillDetail, HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> employeeRegistration = new HashMap<String, Object>();
		empDao.saveEmployeeSkills(skillDetail, applicationId);
		return employeeRegistration;
	}
	
	
	/**
	 * This method handles request to get pre-defined skills from the database.
	 * The value in request mapping annotation gives the requested urls and
	 * method defines the supported methods.
	 * @return HashMap<String, Object>
	 * @see HashMap<String, Object>
	 */
	@RequestMapping(value = { "/getSkills", "/landingpage/getSkills" }, method = RequestMethod.GET)
	public @ResponseBody HashMap<String, Object> getSkills() {
		HashMap<String, Object> employeeRegistration = new HashMap<String, Object>();
		List<Skills> skills = empDao.getSkills();
		employeeRegistration.put("skills", skills);
		return employeeRegistration;
	}
	
	
	@RequestMapping(value = {"/makeMultipleEmployees","/landingpage/makeMultipleEmployees"}, method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> makeMultipleEmployees(
			@RequestBody List<MakeEmployee> makeEmpList, HttpSession session)
			throws MakeEmployeeException {
		HashMap<String, Object> empCrearted = new HashMap<String, Object>();
		
		List<String> mailToList = new ArrayList<String>();
		List<WelcomeTemplateData> welcomeDataList = new ArrayList<WelcomeTemplateData>();
		for(MakeEmployee makeEmp: makeEmpList){
			mailToList.add(makeEmp.getEmail());
		}
		
		for(MakeEmployee makeEmp: makeEmpList){
			Integer applicationId = empDao.makeMultipleEmployee(makeEmp);
			welcomeDataList.add(empDao.getWelcomeTemplateData(applicationId));
		}
		
		empCrearted.put("welcomeDataList", welcomeDataList);
		empCrearted.put("mailToList", mailToList);
		
		return empCrearted;
	}
	
	@RequestMapping(value = "/landingpage/enableEdit", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> enableEdit(HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> status = new HashMap<String, Object>();
		status.put("status",
				preEmpDao.enableDisableEdit(applicationId, true));
		return status;
	}
	
	@RequestMapping(value = "/landingpage/disableEdit", method = RequestMethod.POST)
	public @ResponseBody HashMap<String, Object> disableEdit(HttpSession session) {
		int applicationId = (Integer) session
				.getAttribute("selectedApplicationId");
		HashMap<String, Object> status = new HashMap<String, Object>();
		status.put("status",
				preEmpDao.enableDisableEdit(applicationId, false));
		return status;
	}
}
