package com.app.main.controller;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.VehicleInstallationManager;
import com.app.main.dao.VehicleInstallationData;
import com.app.main.encryption.EncryptionData;
import com.sun.jersey.api.client.ClientResponse.Status;

/*
* Developer    : Pallavi Dhage
* Created Date : 2019-07-27
* Updated Date : 
* Description  : API Controller For Vehicle Installation
*  */

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class VehicleInstallationMaster {
	static VehicleInstallationManager vehicleInstallationManager=new VehicleInstallationManager();
	
public static String[] spltstr = null;
	
	@PostMapping(value = ApiRestURIConstants.INSERT_VEHICLE_INSTALL)
	public Response testapipost(@RequestBody VehicleInstallationData deviceData,@RequestHeader("headersparam") String headersparam) {
		String deviceResponse=null;
		spltstr=EncryptionData.getparamencryption(headersparam);
		deviceResponse=vehicleInstallationManager.insertvehicleInstallatin(deviceData,spltstr);
		if(deviceResponse==null || deviceResponse.equals("") || deviceResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("INTERNAL_SERVER_ERROR")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(deviceResponse.equals("Successfully Saved.")) {
			return Response.status(200)
					.entity(deviceResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(deviceResponse).build();
		}
		
	}
}
