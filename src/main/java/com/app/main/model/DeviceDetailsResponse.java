package com.app.main.model;

import java.util.List;

public class DeviceDetailsResponse {
	
	private String deviceid,makeid,modelid, deviceimeino, deviceuniqueno, deviceiccidno, mobilenumber1, mobilenumber2, 
	networkid1, networkid2, m2mproviderid, m2mprovidername, m2mprovidercode,distributorname,dealername,devmodelname,response,
	deviceType;
	
	
	
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	private Integer count;
	List<DeviceDetailsResponse> data ;
	
	Integer total;
	
	
	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<DeviceDetailsResponse> getData() {
		return data;
	}

	public void setData(List<DeviceDetailsResponse> data) {
		this.data = data;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getDistributorname() {
		return distributorname;
	}

	public void setDistributorname(String distributorname) {
		this.distributorname = distributorname;
	}

	public String getDealername() {
		return dealername;
	}

	public void setDealername(String dealername) {
		this.dealername = dealername;
	}

	public String getDevmodelname() {
		return devmodelname;
	}

	public void setDevmodelname(String devmodelname) {
		this.devmodelname = devmodelname;
	}

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getMakeid() {
		return makeid;
	}

	public void setMakeid(String makeid) {
		this.makeid = makeid;
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getDeviceimeino() {
		return deviceimeino;
	}

	public void setDeviceimeino(String deviceimeino) {
		this.deviceimeino = deviceimeino;
	}

	public String getDeviceuniqueno() {
		return deviceuniqueno;
	}

	public void setDeviceuniqueno(String deviceuniqueno) {
		this.deviceuniqueno = deviceuniqueno;
	}

	public String getDeviceiccidno() {
		return deviceiccidno;
	}

	public void setDeviceiccidno(String deviceiccidno) {
		this.deviceiccidno = deviceiccidno;
	}

	public String getMobilenumber1() {
		return mobilenumber1;
	}

	public void setMobilenumber1(String mobilenumber1) {
		this.mobilenumber1 = mobilenumber1;
	}

	public String getMobilenumber2() {
		return mobilenumber2;
	}

	public void setMobilenumber2(String mobilenumber2) {
		this.mobilenumber2 = mobilenumber2;
	}

	public String getNetworkid1() {
		return networkid1;
	}

	public void setNetworkid1(String networkid1) {
		this.networkid1 = networkid1;
	}

	public String getNetworkid2() {
		return networkid2;
	}

	public void setNetworkid2(String networkid2) {
		this.networkid2 = networkid2;
	}

	public String getM2mproviderid() {
		return m2mproviderid;
	}

	public void setM2mproviderid(String m2mproviderid) {
		this.m2mproviderid = m2mproviderid;
	}

	public String getM2mprovidername() {
		return m2mprovidername;
	}

	public void setM2mprovidername(String m2mprovidername) {
		this.m2mprovidername = m2mprovidername;
	}

	public String getM2mprovidercode() {
		return m2mprovidercode;
	}

	public void setM2mprovidercode(String m2mprovidercode) {
		this.m2mprovidercode = m2mprovidercode;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

}
