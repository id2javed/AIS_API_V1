package com.app.main.dao;

import java.io.FileInputStream;

public class ManufacturerData {

	private int companyId;
	
	private String compname,mancode,cin,gst,regadd,offno,email,state,city,loginName,conPass;
	
	private String searchby,pageno,itemsPerPage;
	
	private String companyLogo, logoColor, remarks;
	
	private FileInputStream imageInputStream;

	public String getSearchby() {
		return searchby;
	}

	public void setSearchby(String searchby) {
		this.searchby = searchby;
	}

	public String getPageno() {
		return pageno;
	}

	public void setPageno(String pageno) {
		this.pageno = pageno;
	}

	public String getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(String itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public String getCompname() {
		return compname;
	}

	public void setCompname(String compname) {
		this.compname = compname;
	}

	public String getMancode() {
		return mancode;
	}

	public void setMancode(String mancode) {
		this.mancode = mancode;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getGst() {
		return gst;
	}

	public void setGst(String gst) {
		this.gst = gst;
	}

	public String getRegadd() {
		return regadd;
	}

	public void setRegadd(String regadd) {
		this.regadd = regadd;
	}

	public String getOffno() {
		return offno;
	}

	public void setOffno(String offno) {
		this.offno = offno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getConPass() {
		return conPass;
	}

	public void setConPass(String conPass) {
		this.conPass = conPass;
	}

	public String getCompanyLogo() {
		return companyLogo;
	}

	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}

	public String getLogoColor() {
		return logoColor;
	}

	public void setLogoColor(String logoColor) {
		this.logoColor = logoColor;
	}

	public FileInputStream getImageInputStream() {
		return imageInputStream;
	}

	public void setImageInputStream(FileInputStream imageInputStream) {
		this.imageInputStream = imageInputStream;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
