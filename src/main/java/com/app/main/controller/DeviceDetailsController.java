package com.app.main.controller;


import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.connection.APIResponseModel;
import com.app.main.business.DeviceDetailsManager;
import com.app.main.dao.DeviceData;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.DeviceDetailsResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class DeviceDetailsController {
	

	public static String[] spltstr = null;
	APIResponseModel apiResponseModel =null;
	static DeviceDetailsManager  deviceddetailsManager=new DeviceDetailsManager();
	
	@PostMapping(value = ApiRestURIConstants.GET_DEVICE_DETAILS)
	public Response testapipost(@RequestBody DeviceData devdata,@RequestHeader("headersparam") String headersparam) {
		
		DeviceDetailsResponse devicedetailsResponse=null;
		
		spltstr=EncryptionData.getparamencryption(headersparam);
		devicedetailsResponse=deviceddetailsManager.devicedetails(spltstr,devdata);
		
		if(devicedetailsResponse.equals("Error") || devicedetailsResponse==null || devicedetailsResponse.equals("") || devicedetailsResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Details Failed to Save")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(devicedetailsResponse.getResponse().equals("Successfully Saved!")) {
			return Response.status(200)
					.entity(devicedetailsResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}else{	
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(devicedetailsResponse).build();
		}	
	}
	}
