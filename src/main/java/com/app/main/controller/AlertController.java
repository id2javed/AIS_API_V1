package com.app.main.controller;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import com.sun.jersey.api.client.ClientResponse.Status;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.connection.APIResponseModel;
import com.app.main.business.AlertManager;
import com.app.main.dao.LiveEmergrncy;
import com.app.main.dao.RequestInsertDataDao;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.LiveEmergrncyResponse;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class AlertController {
public static String[] spltstr = null;
	
	static AlertManager alertManager=new AlertManager();
	APIResponseModel apiResponseModel = null;
	
	@PostMapping(value = ApiRestURIConstants.GET_LIVE_EMERGENCY_ALERT_DETAILS)
	public Response liveemergencyalert(@RequestBody LiveEmergrncy devdata,@RequestHeader("headersparam") String headersparam) {
		LiveEmergrncyResponse liveEmergrncyResponse=null;
		spltstr=EncryptionData.getparamencryption(headersparam);
		liveEmergrncyResponse= alertManager.liveemergencyalert(spltstr, devdata);
		
		 if(liveEmergrncyResponse.getResponse().equals("Successfully Saved!")) {
			return Response.status(200)
					.entity(liveEmergrncyResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}else{	
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(liveEmergrncyResponse).build();
		}	
	}
	
	@PostMapping(value = ApiRestURIConstants.INSERT_ALERT_CONFI)

	public APIResponseModel insertAlertConfig(@RequestBody RequestInsertDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		alertManager = new AlertManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel = alertManager.insertalertconfig(spltstr, devdata);
		} catch (Exception e) {
			System.out.println("controller " + e.toString());
			apiResponseModel.setEntity("INTERNAL SEVER ERROR ");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}

		
	/**
	Developer : Prem Gaigole
	Date      : 2020-07-09
	Description : Select Framework
	Update Date :
	Updation  
	**/
	
	@PostMapping(value = ApiRestURIConstants.GET_ALERT_CONFIGURE_DETAILS)
	public APIResponseModel getAlertConfigureDetails(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= alertManager.alertconfiguredetails(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR" +e.toString());
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
    	}

	/**
	Developer : Prem Gaigole
	Date      : 2020-07-24
	Description : Select Framework
	Update Date :
	Updation  
	**/
	
	@PostMapping(value = ApiRestURIConstants.GET_ALTERNATE_TYPE_DETAILS)
	public APIResponseModel getAlertTypeDetails(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= alertManager.alerttypedetails(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR" +e.toString());
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
    	}

}
