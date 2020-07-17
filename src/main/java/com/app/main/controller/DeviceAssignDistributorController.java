package com.app.main.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.DeviceAssignDealerManager;
import com.app.main.business.DeviceAssignDistributorManager;
import com.app.main.dao.DeviceAssignDealerData;
import com.app.main.dao.DeviceAssignDistributorData;
import com.app.main.dao.DeviceData;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.DeviceAssignDealerResponse;
import com.app.main.model.DeviceAssignDistributorResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class DeviceAssignDistributorController {
	


	
	 public static String[] spltstr = null;
	
	static DeviceAssignDistributorManager  diviceassignDistributorManager=new DeviceAssignDistributorManager();
	
	@PostMapping(value = ApiRestURIConstants.SET_DEVICE_ASSIGN_DISTRIBUTOR)
	public Response testapipost(@RequestParam("distributorid") String distributorid,@RequestBody List<DeviceData> data,@RequestHeader("headersparam") String headersparam) {
		
		DeviceAssignDistributorResponse divassdistriResponse=null;
		System.out.println(distributorid+" size  "+data.size()  );
		
		spltstr=EncryptionData.getparamencryption(headersparam);
		divassdistriResponse=diviceassignDistributorManager.diviceassigndistri(distributorid, spltstr,data);
		if(divassdistriResponse.getResponse().equals("Error") || divassdistriResponse.getResponse()==null || divassdistriResponse.getResponse().equals("") || divassdistriResponse.getResponse().equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("INTERNAL_SERVER_ERROR")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(divassdistriResponse.getResponse().equals("Successfully Saved.")) {
			return Response.status(200)
					.entity(divassdistriResponse.getResponse())
					.header("Access-Control-Allow-Origin", "*").build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(divassdistriResponse.getResponse()).build();
	}
		
	}


}
