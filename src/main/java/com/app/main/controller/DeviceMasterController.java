package com.app.main.controller;


import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.DeviceMasterManager;
import com.app.main.dao.DeviceData;
import com.app.main.encryption.EncryptionData;
import com.sun.jersey.api.client.ClientResponse.Status;

/* Developer : Pallavi Dhage
 * Created Date : 2019-07-26 - 2019-07-27
 * Updated Date : 
 * Description  : API Controller For Device Master Details 
 *  */

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class DeviceMasterController {
	static DeviceMasterManager deviceMasterManager=new DeviceMasterManager();
	public static String[] spltstr = null;
	
	
	/* Developer : Pallavi Dhage
	 * Created Date : 2019-07-26 - 2019-07-27
	 * Updated Date : 
	 * Description  : API Controller For Device Master Entry 
	 *  */
	
	@PostMapping(value = ApiRestURIConstants.INSERT_DEVICE)
	public Response testapipost(@RequestBody DeviceData deviceData,@RequestHeader("headersparam") String headersparam) {
		String deviceResponse=null;
		spltstr=EncryptionData.getparamencryption(headersparam);
		deviceResponse=deviceMasterManager.insertDevice(deviceData,spltstr);
		if(deviceResponse==null || deviceResponse.equals("") || deviceResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Failed to save details")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(deviceResponse.equals("Successfully Saved.")) {
			return Response.status(200)
					.entity(deviceResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(deviceResponse).build();
		}		
	}
}
