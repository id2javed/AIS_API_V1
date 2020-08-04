package com.app.main.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.connection.APIResponseModel;
import com.app.main.business.HistoryDataManager;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.encryption.EncryptionData;


@RestController
@EnableAutoConfiguration
//@CrossOrigin(origins = "${angularOrigin}")
public class HistoryDataController {

public static String[] spltstr = null;
	
	static HistoryDataManager historyDataManager=new HistoryDataManager();
	APIResponseModel apiResponseModel = null;
	
	/**
	Developer : Prem Gaigole
	Date      : 2020-07-10
	Description : Select Framework
	Update Date :
	Updation  
	**/
	
	@PostMapping(value = ApiRestURIConstants.GET_HISTORY_DATA_DETAILS)
	public APIResponseModel historydata(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
		try {
			apiResponseModel= historyDataManager.historydata(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
    	}

	
}
