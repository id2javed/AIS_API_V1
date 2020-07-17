package com.app.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.StateCityManager;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.CityResponse;
import com.app.main.model.StateResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

/* Developer    : Pallavi Dhage
 * Created Date : 2019-07-27
 * Updated Date : 
 * Description  : API Controller For State & City Master List (Drop-Down Master)
 *  */

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class StateCityController {
	public static String[] spltstr = null;
	static StateCityManager  stateCityManager=new StateCityManager();
	
	
	@GetMapping(value = ApiRestURIConstants.GET_STATE)
	public Response getState(@RequestHeader("headersparam") String headersparam) {
		List<StateResponse> stateList  = new ArrayList<StateResponse>(); 
		spltstr=EncryptionData.getparamencryption(headersparam);
		try{
			
			stateList=stateCityManager.getStateList(spltstr);
		}catch(Exception e) {
			System.err.println(e);
		}
		if(stateList==null || stateList.size()==0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(stateList)
					.header("Access-Control-Allow-Origin", "*").build();
		}else {
			return Response.status(200).entity(stateList).build();
		}
	}
	
	@GetMapping(value = ApiRestURIConstants.GET_CITY)
	public Response getCity(@RequestHeader("headersparam") String headersparam,@RequestParam("stateid") String stateid) {
		List<CityResponse> cityList  = new ArrayList<CityResponse>(); 
		spltstr=EncryptionData.getparamencryption(headersparam);
		try{
			
			cityList=stateCityManager.getCityList(spltstr,stateid);
		}catch(Exception e) {
			System.err.println(e);
		}
		if(cityList==null || cityList.size()==0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(cityList)
					.header("Access-Control-Allow-Origin", "*").build();
		}else {
			return Response.status(200).entity(cityList).build();
		}
	}
	
	
}
