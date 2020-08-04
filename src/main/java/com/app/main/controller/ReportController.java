package com.app.main.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.connection.APIResponseModel;
import com.app.main.business.ReportManager;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.encryption.EncryptionData;

@RestController
@EnableAutoConfiguration
//@CrossOrigin(origins = "${angularOrigin}")
public class ReportController {
	APIResponseModel apiResponseModel = null;
	ReportManager reportManager = new ReportManager();
	String[] sptstr = null; 
	/**
	Developer : Prem Gaigole
	Date      : 2020-07-26
	Description : Select Framework
	Update Date :
	Updation  
	**/
	
	@PostMapping(value = ApiRestURIConstants.GET_DISTANCE_REPORT)
	public APIResponseModel getDistanceReport(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		sptstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= reportManager.distancereport(sptstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR" +e.toString());
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
    	}

}
