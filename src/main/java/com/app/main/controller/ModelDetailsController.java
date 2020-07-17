package com.app.main.controller;


import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.ModelDetailsManager;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.ModelDetailsResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class ModelDetailsController {

	public static String[] spltstr = null;
	static ModelDetailsManager  modeldetailsManager=new ModelDetailsManager();
	
	@PostMapping(value = ApiRestURIConstants.GET_MODEL_DETAILS)
	public Response testapipost(@RequestHeader("headersparam") String headersparam) {
		
		List<ModelDetailsResponse> modeldetailsResponse=null;
		spltstr=EncryptionData.getparamencryption(headersparam);
		modeldetailsResponse=modeldetailsManager.modeldetails(spltstr);
		
		if(modeldetailsResponse.size()==0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("INTERNAL_SERVER_ERROR")
					.header("Access-Control-Allow-Origin", "*").build();
		}else {
			return Response.status(200)
					.entity(modeldetailsResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}
	}
}
