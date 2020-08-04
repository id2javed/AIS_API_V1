package com.app.main.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.connection.APIResponseModel;
import com.app.main.business.PortManager;
import com.app.main.dao.RequestInsertDataDao;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.encryption.EncryptionData;

@RestController
@EnableAutoConfiguration
public class PortController {
	public static String[] spltstr = null;
	static PortManager portManager = new PortManager();
	APIResponseModel apiResponseModel = null;

	@PostMapping(value = ApiRestURIConstants.INSERT_PORT)
	public APIResponseModel insertport(@RequestBody RequestInsertDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		System.out.println("Insert Controller...");
		spltstr = EncryptionData.getparamencryption(headersparam);
		portManager = new PortManager();
		apiResponseModel = new APIResponseModel();
		try {
			 apiResponseModel= portManager.insertport(spltstr, devdata);

		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}
	
	
	@PostMapping(value = ApiRestURIConstants.GET_PORT_DETAILS)
	public APIResponseModel portdetails(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
		try {
			apiResponseModel= portManager.portdetails(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
    	}

	
	
	
	
	
	
	

	@PostMapping(value = ApiRestURIConstants.ASSIGN_PORT_VENDOR)

	public APIResponseModel assignporttovendor(@RequestBody RequestInsertDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		System.out.println("In assigned...");
		spltstr = EncryptionData.getparamencryption(headersparam);
		portManager = new PortManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel = portManager.assignporttovendor(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}
	
	

	/**
	Developer : Prem Gaigole
	Date      : 2020-07-11
	Description : Select Framework
	Update Date :
	Updation  
	**/
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@PostMapping(value = ApiRestURIConstants.GET_PORT_ASSIGN_DETAILS)
	public APIResponseModel portassigndetails(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
		try {
			apiResponseModel= portManager.portassigndetails(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
    	}
	
	/**
	Developer : Prem Gaigole
	Date      : 2020-07-18
	Description : Delete Framework
	Update Date :
	Updation  
	**/

	@PostMapping(ApiRestURIConstants.DELETE_PORT)
	public APIResponseModel deletePort(@RequestBody RequestInsertDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		portManager = new PortManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel= portManager.deleteport(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SERVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}

	/**
	Developer : Prem Gaigole
	Date      : 2020-07-18
	Description : Delete Framework
	Update Date :
	Updation  
	**/

	@PostMapping(ApiRestURIConstants.UNASSING_PORT_TO_VENDER_API)
	public APIResponseModel unassignPortToVendor(@RequestBody RequestInsertDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		System.out.println("API Call Success");
		spltstr = EncryptionData.getparamencryption(headersparam);
		portManager = new PortManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel= portManager.unassignporttovendor(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SERVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}

}
