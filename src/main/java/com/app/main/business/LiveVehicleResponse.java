package com.app.main.business;

import java.util.ArrayList;
import java.util.List;

public class LiveVehicleResponse {
	
	
	List<MapAlldataResponse> datamap = new ArrayList<MapAlldataResponse>();
	List<String> datalist = new ArrayList<String>();
	private String unitid;
	private String datereceived;
	private String timereceived;
	private String latitude;
	private String longitude;
	private double speed;
	private String headings;
	private String vehical_no;
	private String routetype;
	private String tripid;
	private String companyid;
	private String mode;
	private String routename;
	private int Seno;
	private List<MapAlldataResponse> dataarr;

	public List<MapAlldataResponse> getDatamap() {
		return datamap;
	}
	public void setDatamap(List<MapAlldataResponse> datamap) {
		this.datamap = datamap;
	}
	public List<String> getDatalist() {
		return datalist;
	}
	public void setDatalist(List<String> datalist) {
		this.datalist = datalist;
	}
	
	public void setDataarr(List<MapAlldataResponse> dataarr) {
		this.dataarr = dataarr;
	}
	
	
	public int getSeno() {
		return Seno;
	}
	public void setSeno(int seno) {
		Seno = seno;
	}
	private String depotname;
	private String location;
	private String trackdate;
	private String gpsstatus;
	private String ignnumber;
	private String vtype;
	private String district;
	private boolean status;
	private String historyicon;
	private String etpno; 
	private String minename; 
	private String quantity; 
	private String source; 
	private String destination; 
	private String starttime; 
	private String endtime; 
	private String transportername;
	private String checkdate;
	private String company;
	private String imeino;
	private String panicstatus;
	private String destLat;
	private String destLong;
	private Integer paniccount;
	private String directionicon;
	private Integer dirdeg;
	private String circle;
	private String errorresponsemsg;
	private String srno;
	private String color;
	private String gpscolor;
	private String imgsrc;
	private String alertt;
	private String alerttype,statename,alertname;
	
	public String getUnitid() {
		return unitid;
	}
	public void setUnitid(String unitid) {
		this.unitid = unitid;
	}
	public String getDatereceived() {
		return datereceived;
	}
	public void setDatereceived(String datereceived) {
		this.datereceived = datereceived;
	}
	public String getTimereceived() {
		return timereceived;
	}
	public void setTimereceived(String timereceived) {
		this.timereceived = timereceived;
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
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public String getHeadings() {
		return headings;
	}
	public void setHeadings(String headings) {
		this.headings = headings;
	}
	public String getVehical_no() {
		return vehical_no;
	}
	public void setVehical_no(String vehical_no) {
		this.vehical_no = vehical_no;
	}
	public String getRoutetype() {
		return routetype;
	}
	public void setRoutetype(String routetype) {
		this.routetype = routetype;
	}
	public String getTripid() {
		return tripid;
	}
	public void setTripid(String tripid) {
		this.tripid = tripid;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getRoutename() {
		return routename;
	}
	public void setRoutename(String routename) {
		this.routename = routename;
	}
	public String getDepotname() {
		return depotname;
	}
	public void setDepotname(String depotname) {
		this.depotname = depotname;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTrackdate() {
		return trackdate;
	}
	public void setTrackdate(String trackdate) {
		this.trackdate = trackdate;
	}
	public String getGpsstatus() {
		return gpsstatus;
	}
	public void setGpsstatus(String gpsstatus) {
		this.gpsstatus = gpsstatus;
	}
	public String getIgnnumber() {
		return ignnumber;
	}
	public void setIgnnumber(String ignnumber) {
		this.ignnumber = ignnumber;
	}
	public String getVtype() {
		return vtype;
	}
	public void setVtype(String vtype) {
		this.vtype = vtype;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getHistoryicon() {
		return historyicon;
	}
	public void setHistoryicon(String historyicon) {
		this.historyicon = historyicon;
	}
	public String getEtpno() {
		return etpno;
	}
	public void setEtpno(String etpno) {
		this.etpno = etpno;
	}
	public String getMinename() {
		return minename;
	}
	public void setMinename(String minename) {
		this.minename = minename;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public String getTransportername() {
		return transportername;
	}
	public void setTransportername(String transportername) {
		this.transportername = transportername;
	}
	public String getCheckdate() {
		return checkdate;
	}
	public void setCheckdate(String checkdate) {
		this.checkdate = checkdate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getImeino() {
		return imeino;
	}
	public void setImeino(String imeino) {
		this.imeino = imeino;
	}
	public String getPanicstatus() {
		return panicstatus;
	}
	public void setPanicstatus(String panicstatus) {
		this.panicstatus = panicstatus;
	}
	public String getDestLat() {
		return destLat;
	}
	public void setDestLat(String destLat) {
		this.destLat = destLat;
	}
	public String getDestLong() {
		return destLong;
	}
	public void setDestLong(String destLong) {
		this.destLong = destLong;
	}
	public Integer getPaniccount() {
		return paniccount;
	}
	public void setPaniccount(Integer paniccount) {
		this.paniccount = paniccount;
	}
	public String getDirectionicon() {
		return directionicon;
	}
	public void setDirectionicon(String directionicon) {
		this.directionicon = directionicon;
	}
	public Integer getDirdeg() {
		return dirdeg;
	}
	public void setDirdeg(Integer dirdeg) {
		this.dirdeg = dirdeg;
	}
	public String getCircle() {
		return circle;
	}
	public void setCircle(String circle) {
		this.circle = circle;
	}
	public String getErrorresponsemsg() {
		return errorresponsemsg;
	}
	public void setErrorresponsemsg(String errorresponsemsg) {
		this.errorresponsemsg = errorresponsemsg;
	}
	public String getSrno() {
		return srno;
	}
	public void setSrno(String srno) {
		this.srno = srno;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getGpscolor() {
		return gpscolor;
	}
	public void setGpscolor(String gpscolor) {
		this.gpscolor = gpscolor;
	}
	public String getImgsrc() {
		return imgsrc;
	}
	public void setImgsrc(String imgsrc) {
		this.imgsrc = imgsrc;
	}
	public String getAlertt() {
		return alertt;
	}
	public void setAlertt(String alertt) {
		this.alertt = alertt;
	}
	public String getAlerttype() {
		return alerttype;
	}
	public void setAlerttype(String alerttype) {
		this.alerttype = alerttype;
	}
	public String getStatename() {
		return statename;
	}
	public void setStatename(String statename) {
		this.statename = statename;
	}
	public String getAlertname() {
		return alertname;
	}
	public void setAlertname(String alertname) {
		this.alertname = alertname;
	}
}
