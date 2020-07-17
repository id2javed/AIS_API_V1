package com.app.main.controller;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.ModelManager;
import com.app.main.dao.ModelData;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.ModelResponse;


import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class ModelController {

	public static String[] spltstr = null;

	static ModelManager modelManager = new ModelManager();

	@PostMapping(value = ApiRestURIConstants.SET_MODEL)
	public Response testapipost(@RequestBody ModelData modelData,
			@RequestHeader("headersparam") String headersparam) {
		
		ModelResponse modelResponse = null;

		spltstr = EncryptionData.getparamencryption(headersparam);
		modelResponse = modelManager.model(modelData, spltstr); 

		if(modelResponse.getResponse().equals("Error") || modelResponse.getResponse()==null || modelResponse.getResponse().equals("") || modelResponse.getResponse().equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("INTERNAL_SERVER_ERROR")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(modelResponse.getResponse().equals("Successfully Saved.")) {
			return Response.status(200)
					.entity(modelResponse.getResponse())
					.header("Access-Control-Allow-Origin", "*").build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(modelResponse.getResponse()).build();
		}

	}

}
