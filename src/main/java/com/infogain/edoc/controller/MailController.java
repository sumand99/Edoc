package com.infogain.edoc.controller;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.infogain.edoc.dao.EmployeeDao;
import com.infogain.edoc.dao.FileDataDao;
import com.infogain.edoc.dao.PreEmployeeDao;
import com.infogain.edoc.model.EmailAndWelcomeData;
import com.infogain.edoc.model.Employee;
import com.infogain.edoc.model.Mail;
import com.infogain.edoc.model.MakeEmployee;
import com.infogain.edoc.model.PreEmployee;
import com.infogain.edoc.model.ProcessNotificationMail;
import com.infogain.edoc.model.WelcomeTemplateData;
import com.infogain.edoc.utils.AppZip;

@Controller
public class MailController {
	public static final int RMG_APPROVE = 0;
	public static final int RMG_ADMIN_REJECT = 1;
	public static final int RMG_ADMIN_APPROVE = 2;
	public static final int HR_REJECT = 3;
	
	@Value("${mail.sendBgcMailFrom}")
	private String sendBgcMailFrom;
	
	@Value("${mail.sendRegisterMailFrom}")
	private String sendRegisterMailFrom;
	
	@Value("${mail.sendWelcomeMailFrom}")
	private String sendWelcomeMailFrom;
	
	@Value("${mail.notificationMailToRmgAdmin}")
	private String notificationMailToRmgAdmin;
	
	@Value("${mail.notificationMailToRmg}")
	private String notificationMailToRmg;
	
	@Value("${mail.notificationMailToHr}")
	private String notificationMailToHr;
	
	@Value("${mail.notificationMailFrom}")
	private String notificationMailFrom;
	
	@Autowired
	FileDataDao fileDataDao;
	
	@Autowired
	private JavaMailSender mailSender; 
	
	@Autowired
	private VelocityEngine velocityEngine;
	 
	@Autowired
	private PreEmployeeDao preEmpDao;
	
	@Autowired
	private EmployeeDao empDao;
	
	@Autowired
	private ServletContext servletContext;
	
	
	 /**
	  * This method handles for mail sending.
	  * @param mail
	  * @return
	  * @throws MessagingException
	  */
	@RequestMapping(value="sendmail")
	public @ResponseBody HashMap<String,Object> mailer(@RequestBody Mail mail) throws MessagingException{
		
		
		
		MimeMessage message = mailSender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		 mail.setTemplateName("emailtemplate.vm");
		mail.setMailFrom(sendBgcMailFrom);
		helper.setFrom(mail.getMailFrom());  
		
		helper.setSubject(mail.getMailSubject());
		PreEmployee preEmp =  preEmpDao.getPreEmployee(mail.getApplicationId());
		String folderLocation = servletContext.getRealPath("/") + preEmp.getFileLocation() + "/" + mail.getApplicationId();
       
		       
       //convert files to zip
       AppZip.setSourceFolder(folderLocation);
       AppZip.generateFileList(new File(AppZip.getSourceFolder()));
       AppZip.setOutputZipFile(preEmp.getFirstName() + preEmp.getLastName()+".zip");
       AppZip.zipIt(AppZip.getOutputZipFile());
       AppZip.fileList.clear();
		
		FileSystemResource file = new FileSystemResource(AppZip.getOutputZipFile());
		helper.addAttachment(file.getFilename(), file);
		  Template template = velocityEngine.getTemplate("./templates/" +mail.getTemplateName());
		  VelocityContext velocityContext = new VelocityContext();
		  StringWriter stringWriter = new StringWriter();
		  template.merge(velocityContext, stringWriter);
		  helper.setText(stringWriter.toString());
		  for(String s:mail.getMailTo()){
				helper.setTo(s);
				 mailSender.send(message);
				}
		 
		  HashMap status = new HashMap<String, Object>();
		  status.put("message", "mail sent");
		  return status;
	}
	
	@RequestMapping(value="sendRegisterMail")
	public @ResponseBody HashMap<String,Object> mailerRegistration(@RequestBody Mail mail) throws MessagingException{
		mail.setTemplateName("emailtemplate.vm");
		mail.setMailFrom(sendRegisterMailFrom);
		MimeMessage message = mailSender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(mail.getMailFrom());  
		helper.setSubject(mail.getMailSubject());
		helper.setText(mail.getMailContent());
		
		helper.setTo(mail.getMailTo().toArray(new String[mail.getMailTo().toArray().length]));
		
		 mailSender.send(message);
		  HashMap status = new HashMap<String, Object>();
		  status.put("message", "mail sent");
		  return status;
		
		
	}
	
	@RequestMapping(value="/landingpage/sendWelcomeMail")
	public @ResponseBody HashMap<String,Object> mailerWelcome(HttpSession session) throws MessagingException, IOException{
		Mail mail = new Mail();
		mail.setTemplateName("welcomehtmltemplate.html");
		mail.setMailFrom(sendWelcomeMailFrom);
		mail.setMailSubject("Welcome to Infogain.");
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(mail.getMailFrom());
		helper.setSubject(mail.getMailSubject());
		Employee emp = empDao.getEmployeeByApplicationId(Integer
				.parseInt(session.getAttribute("selectedApplicationId")
						.toString()));
		Template template = velocityEngine.getTemplate("./templates/"
				+ mail.getTemplateName());
		VelocityContext velocityContext = new VelocityContext();
		File file = new File(servletContext.getRealPath("/")
				+ fileDataDao.getPhoto(Integer.parseInt(session.getAttribute(
						"selectedApplicationId").toString())));
		velocityContext.put("employeeName", emp.getFirstName());
		velocityContext.put("email", emp.getEmail());
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		helper.setText(stringWriter.toString(), true);
		helper.addInline("displayImage", file);
		String email = emp.getEmail();
		List<String> emails = new ArrayList<String>();
		emails.add(email);
		mail.setMailTo(emails);
		helper.setTo(mail.getMailTo().toArray(
				new String[mail.getMailTo().toArray().length]));
		mailSender.send(message);
		HashMap status = new HashMap<String, Object>();
		status.put("message", "welcome mail sent");
		return status;
	}
	

	
	@RequestMapping(value={"/sendMultipleWelcomeEmail", "/landingpage/sendMultipleWelcomeEmail"}, method = RequestMethod.POST)
	public @ResponseBody HashMap<String,Object> sendMultipleWelcomeEmail(@RequestBody List<MakeEmployee> makeEmpList ,HttpSession session) throws MessagingException{
		List<String> mailToListTemp = new ArrayList<String>();
		List<WelcomeTemplateData> welcomeDataListTemp = new ArrayList<WelcomeTemplateData>();
		EmailAndWelcomeData emailAndWelcomeData = new EmailAndWelcomeData();
		for(MakeEmployee makeEmp:makeEmpList){
			mailToListTemp.add(makeEmp.getEmail());
		}
		for(MakeEmployee makeEmp:makeEmpList){
			welcomeDataListTemp.add(empDao.getWelcomeTemplateData(makeEmp.getApplicationId()));
		}
		emailAndWelcomeData.setMailToList(mailToListTemp);
		emailAndWelcomeData.setWelcomeDataList(welcomeDataListTemp);
		
		List<String> mailTo = emailAndWelcomeData.getMailToList();
		List<WelcomeTemplateData> welcomeDataList = emailAndWelcomeData.getWelcomeDataList();
		List<File> profileFiles = new ArrayList<File>(); 
		File banner;
		Mail mail = new Mail();
		mail.setTemplateName("welcometemplate.vm");
		mail.setMailFrom(sendWelcomeMailFrom);
		mail.setMailSubject("Welcome to Infogain.");
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(mail.getMailFrom());
		helper.setSubject(mail.getMailSubject());
		Template template = velocityEngine.getTemplate("./templates/"
				+ mail.getTemplateName());
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("welcomeDataList", welcomeDataList);
		
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		helper.setText(stringWriter.toString(),true);
		mail.setMailTo(mailTo);
		
		for(WelcomeTemplateData welcomeData : welcomeDataList)
		{
			File f = new File(servletContext.getRealPath("/") + fileDataDao.getPhoto(welcomeData.getApplicationId()));
			helper.addInline(welcomeData.getApplicationId()+"", f);
			profileFiles.add(f);
		}
		
		if(welcomeDataList.size() > 1)
			banner = new File(servletContext.getRealPath("/")+"resources/images/banner2.jpg");
		else
			banner = new File(servletContext.getRealPath("/")+"resources/images/banner.jpg");
		
		helper.addInline("banner", banner);
		helper.setTo(mail.getMailTo().toArray(
				new String[mail.getMailTo().toArray().length]));
		/*helper.setTo("amant.kumar@infogain.com");
		*/
		mailSender.send(message);
		HashMap status = new HashMap<String, Object>();
		status.put("message", "welcome mail sent");
		return status;
		
		
	}
	
	@RequestMapping(value="/sendNotificationMail")
	public @ResponseBody HashMap<String,Object> sendNotification(@RequestBody ProcessNotificationMail processNotificationMail) throws MessagingException{
		Mail mail = new Mail();
		List<String> employeeList = new ArrayList<String>();
		List mailTo = new ArrayList<String>();
		
		if(processNotificationMail.getStatus() == RMG_ADMIN_REJECT ){
			mail.setTemplateName("rmgadminreject.vm");
			mail.setMailSubject("Employee Rejected by RMG ADMIN");
			mailTo.add(notificationMailToRmg);
		}
		
		else if(processNotificationMail.getStatus() == RMG_ADMIN_APPROVE ){
			mail.setTemplateName("rmgadminapprove.vm");
			mail.setMailSubject("Employee Approved by RMG ADMIN");
			mailTo.add(notificationMailToHr);
		}
			
		else if(processNotificationMail.getStatus() == HR_REJECT ){
			mail.setTemplateName("hrreject.vm");
			mail.setMailSubject("Employee Rejected by HR");
			mailTo.add(notificationMailToRmgAdmin);
		}
			
		else if(processNotificationMail.getStatus() == RMG_APPROVE){
			mail.setTemplateName("rmgapprove.vm");
			mail.setMailSubject("Employee Approved by RMG");
			mailTo.add(notificationMailToRmgAdmin);
		}
		
		
		mail.setMailTo(mailTo);
		mail.setMailFrom(notificationMailFrom);
		MimeMessage message = mailSender.createMimeMessage();
		 MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(mail.getMailFrom());  
		helper.setSubject(mail.getMailSubject());
		Template template = velocityEngine.getTemplate("./templates/"
				+ mail.getTemplateName());
		VelocityContext velocityContext = new VelocityContext();
		for(Integer applicationId:processNotificationMail.getApplicationIdList()){
			PreEmployee preEmp = preEmpDao.getPreEmployee(applicationId);
			employeeList.add(preEmp.getFirstName() + " " + preEmp.getLastName());
		}
		velocityContext.put("employeeList", employeeList);
		velocityContext.put("reason", processNotificationMail.getReason());
		
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		helper.setText(stringWriter.toString(),true);
		
		helper.setTo(mail.getMailTo().toArray(new String[mail.getMailTo().toArray().length]));
		
		 mailSender.send(message);
		  HashMap status = new HashMap<String, Object>();
		  status.put("message", "mail sent");
		  return status;
		
		
	}
}
