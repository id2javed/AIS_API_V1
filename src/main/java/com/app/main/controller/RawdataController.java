package com.app.main.controller;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.RawdataManager;
import com.app.main.dao.RawData;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.RawDataResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class RawdataController {
	
	public static String[] spltstr = null;
	static RawdataManager rawdata=new RawdataManager();
	
	@PostMapping(value = ApiRestURIConstants.GET_RAW_DATA)
	public Response rawdataapipost(@RequestBody RawData rawD,
			@RequestHeader("headersparam") String headersparam) {
		
		System.out.println("Controller");

		List<RawDataResponse> rawResponse = null;
		spltstr = EncryptionData.getparamencryption(headersparam);
		rawResponse = rawdata.rawdatamethod(rawD, spltstr); 
		
		
		
		if(rawResponse.size()==0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("INTERNAL_SERVER_ERROR")
					.header("Access-Control-Allow-Origin", "*").build();
		}else {
			return Response.status(200)
					.entity(rawResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}


	}
	

}
