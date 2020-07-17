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
import com.app.main.dao.DeviceData;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.DeviceAssignDealerResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class DeviceAssignDealerController {
	
	

	
	 public static String[] spltstr = null;
	
	static DeviceAssignDealerManager  diviceassigndealerManager=new DeviceAssignDealerManager();
	
	@PostMapping(value = ApiRestURIConstants.SET_DEVICE_ASSIGN_DEALER)
	public Response testapipost(@RequestParam("dealerid") String dealerid,@RequestBody List<DeviceData> data,@RequestHeader("headersparam") String headersparam) {
		
		DeviceAssignDealerResponse divassdeaResponse=null;
		System.out.println("sysout 1");
		spltstr=EncryptionData.getparamencryption(headersparam);
		divassdeaResponse=diviceassigndealerManager.diviceassigndealer(dealerid, spltstr,data);
		if(divassdeaResponse.getResponse().equals("Error") || divassdeaResponse.getResponse()==null || divassdeaResponse.getResponse().equals("") || divassdeaResponse.getResponse().equals("null")) {
			System.out.println("sysout 2");
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("INTERNAL_SERVER_ERROR")
					.header("Access-Control-Allow-Origin", "*").build();
			
		}else if(divassdeaResponse.getResponse().equals("Successfully Saved.")) {
			System.out.println("sysout 3");
			return Response.status(200)
					.entity(divassdeaResponse.getResponse())
					.header("Access-Control-Allow-Origin", "*").build();
			
		}else{
			System.out.println("sysout 4");
			
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(divassdeaResponse.getResponse()).build();
			
		}
		
	}



}
