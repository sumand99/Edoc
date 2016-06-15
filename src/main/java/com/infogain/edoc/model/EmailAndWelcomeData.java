package com.infogain.edoc.model;

import java.util.ArrayList;
import java.util.List;

public class EmailAndWelcomeData 
{
	List<String> mailToList = new ArrayList<String>();
	List<WelcomeTemplateData> welcomeDataList = new ArrayList<WelcomeTemplateData>();
	
	public List<WelcomeTemplateData> getWelcomeDataList() {
		return welcomeDataList;
	}
	public void setWelcomeDataList(List<WelcomeTemplateData> welcomeDataList) {
		this.welcomeDataList = welcomeDataList;
	}
	@Override
	public String toString() {
		return "EmailAndWelcomeData [emailToList=" + mailToList
				+ ", welcomeDataList=" + welcomeDataList + "]";
	}
	public List<String> getMailToList() {
		return mailToList;
	}
	public void setMailToList(List<String> mailToList) {
		this.mailToList = mailToList;
	}
	
	
}
