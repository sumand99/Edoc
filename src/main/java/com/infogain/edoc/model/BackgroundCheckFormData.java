package com.infogain.edoc.model;

import java.util.List;

public class BackgroundCheckFormData {
private List<BackgroundCheckEducationalDetail> backgroundCheckEducationalDetailList;
private List<BackgroundCheckEmploymentDetail> backgroundCheckEmploymentDetailList;
private BackgroundCheckPersonalDetail backgroundCheckPersonalDetail;
public List<BackgroundCheckEducationalDetail> getBackgroundCheckEducationalDetailList() {
	return backgroundCheckEducationalDetailList;
}
public void setBackgroundCheckEducationalDetailList(
		List<BackgroundCheckEducationalDetail> backgroundCheckEducationalDetailList) {
	this.backgroundCheckEducationalDetailList = backgroundCheckEducationalDetailList;
}
public List<BackgroundCheckEmploymentDetail> getBackgroundCheckEmploymentDetailList() {
	return backgroundCheckEmploymentDetailList;
}
public void setBackgroundCheckEmploymentDetailList(
		List<BackgroundCheckEmploymentDetail> backgroundCheckEmploymentDetailList) {
	this.backgroundCheckEmploymentDetailList = backgroundCheckEmploymentDetailList;
}
public BackgroundCheckPersonalDetail getBackgroundCheckPersonalDetail() {
	return backgroundCheckPersonalDetail;
}
public void setBackgroundCheckPersonalDetail(
		BackgroundCheckPersonalDetail backgroundCheckPersonalDetail) {
	this.backgroundCheckPersonalDetail = backgroundCheckPersonalDetail;
}


}
