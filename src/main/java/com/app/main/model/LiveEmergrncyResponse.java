package com.app.main.model;



import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LiveEmergrncyResponse {
	private String imeino, alerttime, alertreceivedtime, latitude, longitude, speed,distance, message_type, packet_type, 
	gps_status, deviceiccidno,  vehicleregno, vendorname, customername, customermobileno;

	
	private Integer count;
	List<LiveEmergrncyResponse> data ;
	
	Integer total;
		
	String response;
	
	

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<LiveEmergrncyResponse> getData() {
		return data;
	}

	public void setData(List<LiveEmergrncyResponse> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getImeino() {
		return imeino;
	}

	public void setImeino(String imeino) {
		this.imeino = imeino;
	}

	public String getAlerttime() {
		return alerttime;
	}

	public void setAlerttime(String alerttime) {
		this.alerttime = alerttime;
	}

	public String getAlertreceivedtime() {
		return alertreceivedtime;
	}

	public void setAlertreceivedtime(String alertreceivedtime) {
		this.alertreceivedtime = alertreceivedtime;
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

	public String getSpeed() {
		return speed;
	}

	public void setSpeed(String speed) {
		this.speed = speed;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getPacket_type() {
		return packet_type;
	}

	public void setPacket_type(String packet_type) {
		this.packet_type = packet_type;
	}

	public String getGps_status() {
		return gps_status;
	}

	public void setGps_status(String gps_status) {
		this.gps_status = gps_status;
	}

	public String getDeviceiccidno() {
		return deviceiccidno;
	}

	public void setDeviceiccidno(String deviceiccidno) {
		this.deviceiccidno = deviceiccidno;
	}

	public String getVehicleregno() {
		return vehicleregno;
	}

	public void setVehicleregno(String vehicleregno) {
		this.vehicleregno = vehicleregno;
	}

	public String getVendorname() {
		return vendorname;
	}

	public void setVendorname(String vendorname) {
		this.vendorname = vendorname;
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

	@Override
	public String toString() {
		return "LiveEmergrncyResponse [imeino=" + imeino + ", alerttime=" + alerttime + ", alertreceivedtime="
				+ alertreceivedtime + ", latitude=" + latitude + ", longitude=" + longitude + ", speed=" + speed
				+ ", distance=" + distance + ", message_type=" + message_type + ", packet_type=" + packet_type
				+ ", gps_status=" + gps_status + ", deviceiccidno=" + deviceiccidno + ", vehicleregno=" + vehicleregno
				+ ", vendorname=" + vendorname + ", customername=" + customername + ", customermobileno="
				+ customermobileno + ", count=" + count + ", data=" + data + ", total=" + total + ", response="
				+ response + "]";
	}
	
	
}
