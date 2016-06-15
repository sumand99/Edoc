package com.infogain.edoc.model;

import java.util.List;

public class ProcessNotificationMail {
private List<Integer> applicationIdList;
private int status;
private String reason;

public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public List<Integer> getApplicationIdList() {
	return applicationIdList;
}
public void setApplicationIdList(List<Integer> applicationIdList) {
	this.applicationIdList = applicationIdList;
}
public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = reason;
}



}
