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
import com.app.main.business.AlertManager;
import com.app.main.dao.LiveEmergrncy;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.LiveEmergrncyResponse;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class AlertController {
public static String[] spltstr = null;
	
	static AlertManager alertManager=new AlertManager();
	
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
}
