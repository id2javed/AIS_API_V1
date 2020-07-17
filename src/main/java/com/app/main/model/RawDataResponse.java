package com.app.main.model;

public class RawDataResponse {
	
	String datatimestamp;
	String datamessage;
	String clientfqdn;
	String clientport;
	Integer rowno;
	String response;
	String recordid,imeino,date,companyid,flag,pageno,itemsPerPage;
	public String getRecordid() {
		return recordid;
	}
	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	public String getImeino() {
		return imeino;
	}
	public void setImeino(String imeino) {
		this.imeino = imeino;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCompanyid() {
		return companyid;
	}
	public void setCompanyid(String companyid) {
		this.companyid = companyid;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
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
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getDatatimestamp() {
		return datatimestamp;
	}
	public void setDatatimestamp(String datatimestamp) {
		this.datatimestamp = datatimestamp;
	}
	public String getDatamessage() {
		return datamessage;
	}
	public void setDatamessage(String datamessage) {
		this.datamessage = datamessage;
	}
	public String getClientfqdn() {
		return clientfqdn;
	}
	public void setClientfqdn(String clientfqdn) {
		this.clientfqdn = clientfqdn;
	}
	public String getClientport() {
		return clientport;
	}
	public void setClientport(String clientport) {
		this.clientport = clientport;
	}
	public Integer getRowno() {
		return rowno;
	}
	public void setRowno(Integer rowno) {
		this.rowno = rowno;
	}


}
