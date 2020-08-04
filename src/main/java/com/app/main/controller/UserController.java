package com.app.main.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.connection.APIResponseModel;
import com.app.main.business.UserManager;
import com.app.main.dao.RequestInsertDataDao;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.encryption.EncryptionData;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class UserController {
	String[] spltstr = null;

	static UserManager userManager = new UserManager();
	APIResponseModel apiResponseModel = null;

	@PostMapping(value = ApiRestURIConstants.INSERT_USER)
	public APIResponseModel insertUser(@RequestBody RequestInsertDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		
		spltstr = EncryptionData.getparamencryption(headersparam);
		userManager = new UserManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel = userManager.insertuser(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}

	/**
	 * Developer : Prem Gaigole Date : 2020-07-27 Description : update Framework
	 * Update Date : Updation
	 **/

	@PostMapping(value = ApiRestURIConstants.UPDATE_USER)
	public APIResponseModel updateUser(@RequestBody RequestInsertDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		userManager = new UserManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel = userManager.updateuser(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SERVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}

	/**
	 * Developer : Prem Gaigole Date : 2020-07-27 Description : Delete Framework
	 * Update Date : Updation
	 **/

	@PostMapping(ApiRestURIConstants.DELETE_USER)
	public APIResponseModel deleteUser(@RequestBody RequestInsertDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		System.out.println("in controller delete....");
		spltstr = EncryptionData.getparamencryption(headersparam);
		userManager = new UserManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel = userManager.deleteuser(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SERVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}

	/**
	 * Developer : Prem Gaigole Date : 2020-07-09 Description : Select Framework
	 * Update Date : Updation
	 **/

	@PostMapping(value = ApiRestURIConstants.USER_DETAILS)
	public APIResponseModel userDetails(@RequestBody RequestSelectDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr = EncryptionData.getparamencryption(headersparam);

		try {
			apiResponseModel = userManager.userdetails(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR" + e.toString());
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}

}
