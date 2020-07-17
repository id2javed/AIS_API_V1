package com.app.main.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceTypeResponse {
	private String devicetypeid,devicetypename,remarks,loginid,createdat,flag;
	
	private Integer count;
	List<DeviceTypeResponse> data ;
	
	Integer total;
		
	String response;
	

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<DeviceTypeResponse> getData() {
		return data;
	}

	public void setData(List<DeviceTypeResponse> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getDevicetypeid() {
		return devicetypeid;
	}

	public void setDevicetypeid(String devicetypeid) {
		this.devicetypeid = devicetypeid;
	}

	public String getDevicetypename() {
		return devicetypename;
	}

	public void setDevicetypename(String devicetypename) {
		this.devicetypename = devicetypename;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getLoginid() {
		return loginid;
	}

	public void setLoginid(String loginid) {
		this.loginid = loginid;
	}

	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}}
