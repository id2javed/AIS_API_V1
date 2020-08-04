package com.app.main.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.connection.APIResponseModel;
import com.app.main.business.MenuManagmentManager;
import com.app.main.dao.RequestInsertDataDao;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.encryption.EncryptionData;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class MenuManagmentController {

	 String[] spltstr = null;

	static MenuManagmentManager menuManagementManager = new MenuManagmentManager();
	APIResponseModel apiResponseModel = new APIResponseModel();

	@PostMapping(value = ApiRestURIConstants.INSERT_MAIN_MENU)

	public APIResponseModel insertMainMenu(@RequestBody RequestInsertDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		menuManagementManager = new MenuManagmentManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel = menuManagementManager.insertmainmenu(spltstr, devdata);
		} catch (Exception e) {
			System.out.println("controller " + e.toString());
			apiResponseModel.setEntity("INTERNAL SEVER ERROR ");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}
	
	@PostMapping(value = ApiRestURIConstants.INSERT_SUB_MENU)

	public APIResponseModel insertSubMenu(@RequestBody RequestInsertDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		menuManagementManager = new MenuManagmentManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel = menuManagementManager.insertsubmenu(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR "+e.toString());
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}

	/**
	Developer : Prem Gaigole
	Date      : 2020-07-14
	Description : update Framework
	Update Date :
	Updation  
	**/


	@PostMapping(value = ApiRestURIConstants.UPDATE_MENU)
	public APIResponseModel updateMenu(@RequestBody RequestInsertDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		menuManagementManager =new MenuManagmentManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel= menuManagementManager.updatemenu(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SERVER ERROR "+e.toString());
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}
	
	/**
	Developer : Prem Gaigole
	Date      : 2020-07-14
	Description : Delete Framework
	Update Date :
	Updation  
	**/

	@PostMapping(ApiRestURIConstants.DELETE_MENU)
	public APIResponseModel deleteMenu(@RequestBody RequestInsertDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		menuManagementManager = new MenuManagmentManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel= menuManagementManager.deletemenu(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SERVER ERROR "+e.toString());
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}

	@PostMapping(value = ApiRestURIConstants.ASSIGN_MENU_ROLE_WISE)

	public APIResponseModel assignMenuRoleWise(@RequestBody RequestInsertDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		menuManagementManager = new MenuManagmentManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel = menuManagementManager.assignmenurolewise(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR "+e.toString());
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
	}
	
	@PostMapping(value = ApiRestURIConstants.ASSIGN_MENU_USER_WISE)

	public APIResponseModel assignMenuUserWise(@RequestBody RequestInsertDataDao devdata,
			@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		menuManagementManager = new MenuManagmentManager();
		apiResponseModel = new APIResponseModel();
		try {
			apiResponseModel = menuManagementManager.assignmenuuserwise(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR "+e.toString());
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
	
	@PostMapping(value = ApiRestURIConstants.GET_MAIN_MENU)
	public APIResponseModel getMainMenu(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= menuManagementManager.getmainmenu(spltstr, devdata);
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
	
	@PostMapping(value = ApiRestURIConstants.GET_MAIN_MENU_ASSIGN)
	public APIResponseModel getMainMenuAssign(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= menuManagementManager.getmainmenuassign(spltstr, devdata);
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
	
	@PostMapping(value = ApiRestURIConstants.GET_FIANL_MAIN_MENU)
	public APIResponseModel finalmainmenu(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= menuManagementManager.finalmainmenu(spltstr, devdata);
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
	
	@PostMapping(value = ApiRestURIConstants.GET_FIANL_ALL_ASSIGN_MENU)
	public APIResponseModel finalAllAssignMenu(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= menuManagementManager.finalallassignmenu(spltstr, devdata);
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
	
	@PostMapping(value = ApiRestURIConstants.GET_SUB_MENU)
	public APIResponseModel getSubMenu(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= menuManagementManager.getsubmenu(spltstr, devdata);
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
	
	@PostMapping(value = ApiRestURIConstants.GET_MENU_DETAILS)
	public APIResponseModel getMenuDetails(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= menuManagementManager.getmenudetails(spltstr, devdata);
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
	
	@PostMapping(value = ApiRestURIConstants.GET_MENU_ROLE_WISE)
	public APIResponseModel getMenuRoleWise(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= menuManagementManager.getmenurolewise(spltstr, devdata);
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
	
	@PostMapping(value = ApiRestURIConstants.GET_ALL_ASSIGN_MENU_USER)
	public APIResponseModel getAllAssignMenuUser(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
	
		
		try {
			apiResponseModel= menuManagementManager.getallassignmenuuser(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR" +e.toString());
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
    	}


}
