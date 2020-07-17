package com.app.main.model;

import java.util.List;

public class CustomerDetailsResponse {
private String	customerid, customername, customertype, customermobileno, customeremailid, customeralternateno, customeraddress, customercity, customerpincode,customerstate,distributor,dealer,response;
private Integer count;
List<CustomerDetailsResponse> data ;

public List<CustomerDetailsResponse> getData() {
	return data;
}

public void setData(List<CustomerDetailsResponse> data) {
	this.data = data;
}

Integer total;


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

public Integer getTotal() {
	return total;
}

public void setTotal(Integer total) {
	this.total = total;
}

public String getCustomerid() {
	return customerid;
}

public void setCustomerid(String customerid) {
	this.customerid = customerid;
}

public String getCustomername() {
	return customername;
}

public void setCustomername(String customername) {
	this.customername = customername;
}

public String getCustomertype() {
	return customertype;
}

public void setCustomertype(String customertype) {
	this.customertype = customertype;
}

public String getCustomermobileno() {
	return customermobileno;
}

public void setCustomermobileno(String customermobileno) {
	this.customermobileno = customermobileno;
}

public String getCustomeremailid() {
	return customeremailid;
}

public void setCustomeremailid(String customeremailid) {
	this.customeremailid = customeremailid;
}

public String getCustomeralternateno() {
	return customeralternateno;
}

public void setCustomeralternateno(String customeralternateno) {
	this.customeralternateno = customeralternateno;
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

public String getCustomerpincode() {
	return customerpincode;
}

public void setCustomerpincode(String customerpincode) {
	this.customerpincode = customerpincode;
}

public String getCustomerstate() {
	return customerstate;
}

public void setCustomerstate(String customerstate) {
	this.customerstate = customerstate;
}

public String getDistributor() {
	return distributor;
}

public void setDistributor(String distributor) {
	this.distributor = distributor;
}

public String getDealer() {
	return dealer;
}

public void setDealer(String dealer) {
	this.dealer = dealer;
}
	
	

}
