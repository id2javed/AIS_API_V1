package com.app.main.business;

import java.util.List;

public class MapAlldataResponse {


	private List<LiveVehicleResponse> dataarr;
	private String jsnstr,jsnstrRD1,jsnstrRD2;
	public List<LiveVehicleResponse> getDataarr() {
		return dataarr;
	}


	public void setDataarr(List<LiveVehicleResponse> dataarr) {
		this.dataarr = dataarr;
	}


	public String getJsnstr() {
		return jsnstr;
	}


	public void setJsnstr(String jsonstr) {
		this.jsnstr = jsonstr;
	}


	public String getJsnstrRD1() {
		return jsnstrRD1;
	}


	public void setJsnstrRD1(String jsnstrRD1) {
		this.jsnstrRD1 = jsnstrRD1;
	}


	public String getJsnstrRD2() {
		return jsnstrRD2;
	}


	public void setJsnstrRD2(String jsnstrRD2) {
		this.jsnstrRD2 = jsnstrRD2;
	}


//	public List<AlertResolvedResponse> getDataarralert() {
//		return dataarralert;
//	}
//
//
//	public void setDataarralert(List<AlertResolvedResponse> dataarralert) {
//		this.dataarralert = dataarralert;
//	}


	//private List<AlertResolvedResponse> dataarralert;
	/*private List<alertsimeicounts> dataarralertcount;
	private List<alertscmpdata> list;
	private List<AlertResolvedata> eventalertdata;*/
	
	
//	@Override
//	public String toString() {
//		return "MapAlldataResponse [dataarr=" + dataarr + ", jsnstr=" + jsnstr + ", dataarralert=" + dataarralert + "]";
//	}
	
	
	
}
