package com.app.main.controller;

import java.util.List;
import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.app.RestURIConstants.ApiRestURIConstants;

import com.app.main.business.LoginIndManager;
import com.app.main.dao.LoginIndData;
import com.app.main.model.LoginIndResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class LoginIndController {

		static LoginIndManager  loginIndManager=new LoginIndManager();
		
		@PostMapping(value =  ApiRestURIConstants.GET_LOGIN_IND)
		public Response testapipost(@RequestBody LoginIndData loginIndData) {
			List<LoginIndResponse> loginIndResponse=null;
			loginIndResponse=loginIndManager.loginind(loginIndData);
			if(loginIndResponse==null || loginIndResponse.size()==0) {
				return Response.status(Status.INTERNAL_SERVER_ERROR)
						.entity("User Name Or Password Wrong ")
						.header("Access-Control-Allow-Origin", "*").build();
			}else {
				return Response.status(200).entity(loginIndResponse).build();
			}
		}
	}


