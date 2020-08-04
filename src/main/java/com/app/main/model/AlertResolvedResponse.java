package com.app.main.model;

import java.util.List;

public class AlertResolvedResponse {

	/**
	 * 
	 */
	private String id, actiondetails, mobileno, remark, status, resolvedtime, imeino, latitude, longitude, flag, alertvalue,
	alertinserstiontime, datetimestamp, alertgenerationtime,speed,imeiid,devicesrno,customername,ig,gpsstatus,vehstatus,pollingtime,
	igcolor,gpscolor,poiname,entrytime,exittime,iccidno;
	public String getIccidno() {
		return iccidno;
	}

	public void setIccidno(String iccidno) {
		this.iccidno = iccidno;
	}

	public String getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(String entrytime) {
		this.entrytime = entrytime;
	}

	public String getExittime() {
		return exittime;
	}

	public void setExittime(String exittime) {
		this.exittime = exittime;
	}


	Integer dirdeg;
	
	private String typename,typecount;

	public String getTypename() {
			return typename;
		}

		public void setTypename(String typename) {
			this.typename = typename;
		}

		public String getTypecount() {
			return typecount;
		}

		public void setTypecount(String typecount) {
			this.typecount = typecount;
		}


	private String companyname;
	private String rtoname;
	private String alerttypeid;
	private String count;
	private String permitholdername;
	private String permitholderno;
	private String vehicleid;
	private String country;
	private String companyid;
	private String events,vehiclevahno;
	
	
	
	private String alerttype;
	private String imgpath;
	private String vehicleno;
	int total;
	List<AlertResolvedResponse> data;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	private List<AlertResolvedResponse> dataarr;
	public Integer getDirdeg() {
		return dirdeg;
	}

	

	public void setDirdeg(Integer dirdeg) {
		this.dirdeg = dirdeg;
	}

	public String getPoiname() {
		return poiname;
	}

	public void setPoiname(String poiname) {
		this.poiname = poiname;
	}

	public String getGpscolor() {
		return gpscolor;
	}

	public void setGpscolor(String gpscolor) {
		this.gpscolor = gpscolor;
	}

	public String getIgcolor() {
		return igcolor;
	}

	public void setIgcolor(String igcolor) {
		this.igcolor = igcolor;
	}


	int srno;
	public int getSrno() {
		return srno;
	}

	public void setSrno(int srno) {
		this.srno = srno;
	}

	public String getPollingtime() {
		return pollingtime;
	}

	public void setPollingtime(String pollingtime) {
		this.pollingtime = pollingtime;
	}


	public List<AlertResolvedResponse> getData() {
		return data;
	}

	public void setData(List<AlertResolvedResponse> data) {
		this.data = data;
	}

	public String getImeiid() {
		return imeiid;
	}

	public void setImeiid(String imeiid) {
		this.imeiid = imeiid;
	}

	public String getDevicesrno() {
		return devicesrno;
	}

	public void setDevicesrno(String devicesrno) {
		this.devicesrno = devicesrno;
	}

	public String getIg() {
		return ig;
	}

	public void setIg(String ig) {
		this.ig = ig;
	}

	public String getGpsstatus() {
		return gpsstatus;
	}

	public void setGpsstatus(String gpsstatus) {
		this.gpsstatus = gpsstatus;
	}

	public String getVehstatus() {
		return vehstatus;
	}

	public void setVehstatus(String vehstatus) {
		this.vehstatus = vehstatus;
	}

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getVehiclevahno() {
		return vehiclevahno;
	}

	public void setVehiclevahno(String vehiclevahno) {
		this.vehiclevahno = vehiclevahno;
	}

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public String getCompanyid() {
		return companyid;
	}

	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}

	
	private Integer litres;
	
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public Integer getLitres() {
		return litres;
	}

	public void setLitres(Integer litres) {
		this.litres = litres;
	}

	public String getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(String vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getPermitholdername() {
		return permitholdername;
	}

	public void setPermitholdername(String permitholdername) {
		this.permitholdername = permitholdername;
	}

	public String getPermitholderno() {
		return permitholderno;
	}

	public void setPermitholderno(String permitholderno) {
		this.permitholderno = permitholderno;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getAlerttypeid() {
		return alerttypeid;
	}

	public void setAlerttypeid(String alerttypeid) {
		this.alerttypeid = alerttypeid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getRtoname() {
		return rtoname;
	}

	public void setRtoname(String rtoname) {
		this.rtoname = rtoname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActiondetails() {
		return actiondetails;
	}

	public void setActiondetails(String actiondetails) {
		this.actiondetails = actiondetails;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getResolvedtime() {
		return resolvedtime;
	}

	public void setResolvedtime(String resolvedtime) {
		this.resolvedtime = resolvedtime;
	}

	public String getImeino() {
		return imeino;
	}

	public void setImeino(String imeino) {
		this.imeino = imeino;
	}

	

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAlertvalue() {
		return alertvalue;
	}

	public void setAlertvalue(String alertvalue) {
		this.alertvalue = alertvalue;
	}

	public String getAlertinserstiontime() {
		return alertinserstiontime;
	}

	public void setAlertinserstiontime(String alertinserstiontime) {
		this.alertinserstiontime = alertinserstiontime;
	}

	public String getDatetimestamp() {
		return datetimestamp;
	}

	public void setDatetimestamp(String datetimestamp) {
		this.datetimestamp = datetimestamp;
	}

	public String getAlertgenerationtime() {
		return alertgenerationtime;
	}

	public void setAlertgenerationtime(String alertgenerationtime) {
		this.alertgenerationtime = alertgenerationtime;
	}

	public String getAlerttype() {
		return alerttype;
	}

	public void setAlerttype(String alerttype) {
		this.alerttype = alerttype;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public String getVehicleno() {
		return vehicleno;
	}

	public void setVehicleno(String vehicleno) {
		this.vehicleno = vehicleno;
	}

	

	

}
