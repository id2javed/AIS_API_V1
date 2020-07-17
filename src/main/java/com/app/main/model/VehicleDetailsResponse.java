package com.app.main.model;

import java.util.List;

public class VehicleDetailsResponse {
	
//	mv.vehicleid, vehiclemakeid, vehiclemodelid, vehicletypeid, vehicleregno, vehiclechasisno, vehicleengineno, vehicleregdate, vehiclereceiptno, 
//	vehiclemfgyear, mv.customerid, vehiclestatecode, vehiclertoid, vehicletype,
//	(select makename from masters.tblvehiclemake where makeid = vehiclemakeid) as vehiclemakename, 
//	(select modelname from masters.tblvehiclemodel where modelid = vehiclemodelid) as vehiclemodelname,
//	(select vehicleclass from masters.tblvehicleclass where id = vehicletypeid) as vehicleclass,
//	md.deviceimeino,md.deviceuniqueno,md.deviceiccidno,
//	mdr.distributorname, mdr2.dealername,
//	(SELECT devmodelname FROM masters.tbldevicemodelmaster where devmodelid = md.modelid) as devmodelname,
//	mc.customername, mc.customermobileno, mc.customeraddress, mc.customercity, mc.customerstate, mdr2.dealercontactmobile1
	
	
	private String vehicleid, vehiclemakeid, vehiclemodelid, vehicletypeid, vehicleregno, vehiclechasisno, vehicleengineno, vehicleregdate, vehiclereceiptno, 
	vehiclemfgyear, customerid, vehiclestatecode, vehiclertoid, vehicletype,
	 vehiclemakename, 
	vehiclemodelname,
	vehicleclass,
	deviceimeino,deviceuniqueno,deviceiccidno,
	distributorname,
	dealername,
	devmodelname,customername, customermobileno, customeraddress, customercity, customerstate, dealercontactmobile1,devmodelid, devmodelcode, response;
	
	
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

	public List<VehicleDetailsResponse> getData() {
		return data;
	}

	public void setData(List<VehicleDetailsResponse> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	private Integer count;
	List<VehicleDetailsResponse> data ;
	Integer total;
	

	



	public String getDevmodelid() {
		return devmodelid;
	}

	public void setDevmodelid(String devmodelid) {
		this.devmodelid = devmodelid;
	}

	public String getDevmodelcode() {
		return devmodelcode;
	}

	public void setDevmodelcode(String devmodelcode) {
		this.devmodelcode = devmodelcode;
	}

	public String getDevmodelname() {
		return devmodelname;
	}

	public void setDevmodelname(String devmodelname) {
		this.devmodelname = devmodelname;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomermobileno() {
		return customermobileno;
	}

	public void setCustomermobileno(String customermobileno) {
		this.customermobileno = customermobileno;
	}

	public String getCustomeraddress() {
		return customeraddress;
	}

	public void setCustomeraddress(String customeraddress) {
		this.customeraddress = customeraddress;
	}

	public String getCustomercity() {
		return customercity;
	}

	public void setCustomercity(String customercity) {
		this.customercity = customercity;
	}

	public String getCustomerstate() {
		return customerstate;
	}

	public void setCustomerstate(String customerstate) {
		this.customerstate = customerstate;
	}

	public String getDealercontactmobile1() {
		return dealercontactmobile1;
	}

	public void setDealercontactmobile1(String dealercontactmobile1) {
		this.dealercontactmobile1 = dealercontactmobile1;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getVehiclemakeid() {
		return vehiclemakeid;
	}

	public void setVehiclemakeid(String vehiclemakeid) {
		this.vehiclemakeid = vehiclemakeid;
	}

	public String getVehiclemodelid() {
		return vehiclemodelid;
	}

	public void setVehiclemodelid(String vehiclemodelid) {
		this.vehiclemodelid = vehiclemodelid;
	}

	public String getVehicletypeid() {
		return vehicletypeid;
	}

	public void setVehicletypeid(String vehicletypeid) {
		this.vehicletypeid = vehicletypeid;
	}

	public String getVehicleregno() {
		return vehicleregno;
	}

	public void setVehicleregno(String vehicleregno) {
		this.vehicleregno = vehicleregno;
	}

	public String getVehiclechasisno() {
		return vehiclechasisno;
	}

	public void setVehiclechasisno(String vehiclechasisno) {
		this.vehiclechasisno = vehiclechasisno;
	}

	public String getVehicleengineno() {
		return vehicleengineno;
	}

	public void setVehicleengineno(String vehicleengineno) {
		this.vehicleengineno = vehicleengineno;
	}

	public String getVehicleregdate() {
		return vehicleregdate;
	}

	public void setVehicleregdate(String vehicleregdate) {
		this.vehicleregdate = vehicleregdate;
	}

	public String getVehiclereceiptno() {
		return vehiclereceiptno;
	}

	public void setVehiclereceiptno(String vehiclereceiptno) {
		this.vehiclereceiptno = vehiclereceiptno;
	}

	public String getVehiclemfgyear() {
		return vehiclemfgyear;
	}

	public void setVehiclemfgyear(String vehiclemfgyear) {
		this.vehiclemfgyear = vehiclemfgyear;
	}

	public String getCustomerid() {
		return customerid;
	}

	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}

	public String getVehiclestatecode() {
		return vehiclestatecode;
	}

	public void setVehiclestatecode(String vehiclestatecode) {
		this.vehiclestatecode = vehiclestatecode;
	}

	public String getVehiclertoid() {
		return vehiclertoid;
	}

	public void setVehiclertoid(String vehiclertoid) {
		this.vehiclertoid = vehiclertoid;
	}

	public String getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	public String getVehiclemakename() {
		return vehiclemakename;
	}

	public void setVehiclemakename(String vehiclemakename) {
		this.vehiclemakename = vehiclemakename;
	}

	public String getVehiclemodelname() {
		return vehiclemodelname;
	}

	public void setVehiclemodelname(String vehiclemodelname) {
		this.vehiclemodelname = vehiclemodelname;
	}

	public String getVehicleclass() {
		return vehicleclass;
	}

	public void setVehicleclass(String vehicleclass) {
		this.vehicleclass = vehicleclass;
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

}
