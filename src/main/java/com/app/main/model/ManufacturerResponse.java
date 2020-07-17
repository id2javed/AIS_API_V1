package com.app.main.model;

import java.util.List;

public class ManufacturerResponse {
	
	private String compname,mancode,cin,gst,regadd,offno,email,state,city,loginName,conPass,response;
	
	private Integer srno;
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	
	private Integer total;
	
	
	

	public Integer getSrno() {
		return srno;
	}

	public void setSrno(Integer srno) {
		this.srno = srno;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	private List<ManufacturerResponse> data;

	public List<ManufacturerResponse> getData() {
		return data;
	}

	public void setData(List<ManufacturerResponse> data) {
		this.data = data;
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

	
	
	private int companyId;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	private String logoFile;
	
	public String getLogoFile() {
		return logoFile;
	}

	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}

	private String themeColour;
	
	public String getThemeColour() {
		return this.themeColour;
	}

	public void setThemeColour(String themeColour) {
		this.themeColour = themeColour;
	}
	
}
