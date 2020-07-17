package com.app.main.controller;


import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.DashboardManager;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.DashboardResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class DashboardController {
	
	

	public static String[] spltstr = null;

	static DashboardManager DashboardCountManager = new DashboardManager();

	@PostMapping(value = ApiRestURIConstants.GET_DASHBOARD_COUNT)
	public Response testapipost(@RequestHeader("headersparam") String headersparam) {

	DashboardResponse dashboardResponse = null;

		spltstr = EncryptionData.getparamencryption(headersparam);
		dashboardResponse = DashboardCountManager.dashboard( spltstr);

		if(dashboardResponse.getResponse().equals("Error!") || dashboardResponse.getResponse()==null || dashboardResponse.getResponse().equals("") || dashboardResponse.getResponse().equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(dashboardResponse.getResponse())
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(dashboardResponse.getResponse().equals("Success!")) {
			return Response.status(200)
					.entity(dashboardResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(dashboardResponse.getResponse()).build();
		}

	}

}
