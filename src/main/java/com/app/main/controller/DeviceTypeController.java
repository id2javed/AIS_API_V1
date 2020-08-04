/* Developer : pritam laxane
 * Created Date :  2020-07-4
 * Updated Date : 
 * Description  : API Controller For Device Type Master Details 
 *  */

package com.app.main.controller;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.DeviceTypeMasterManager;
import com.app.main.dao.DeviceDataType;
import com.app.main.dao.DeviceType;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.DeviceTypeResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
public class DeviceTypeController {
	public static String[] spltstr = null;
	DeviceTypeMasterManager manager = null;
	
	@PostMapping(value = ApiRestURIConstants.SET_DEVICE_TYPE)
	public Response testapipost(@RequestBody DeviceDataType mDataType, @RequestHeader("headersparam") String headersparam) {
		String[] deviceResponse=null;
		manager=new DeviceTypeMasterManager();
		spltstr=EncryptionData.getparamencryption(headersparam);
		
		deviceResponse = manager.insertDeviceType(mDataType, spltstr);
		if(deviceResponse[1]==null || deviceResponse[1].equals("") || deviceResponse[1].equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Failed to save details")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(deviceResponse[1].equals("Successfully Saved.")) {
			return Response.status(Integer.parseInt(deviceResponse[0]))
					.entity(deviceResponse[1])
					.header("Access-Control-Allow-Origin", "*").build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(deviceResponse).build();
		}		
	}
	
	
	@PostMapping(value = ApiRestURIConstants.GET_DEVICE_TYPE_DETAILS)
	public Response devicetypedetails(@RequestBody DeviceType devdata,@RequestHeader("headersparam") String headersparam) {
		DeviceTypeResponse devicetype=new DeviceTypeResponse();
		manager=new DeviceTypeMasterManager();
		spltstr=EncryptionData.getparamencryption(headersparam);
		devicetype=manager.devicetypedetails(spltstr,devdata);
		 if(devicetype.getResponse().equals("Successfully Saved!")) {
			return Response.status(200)
					.entity(devicetype)
					.header("Access-Control-Allow-Origin", "*").build();
		}else{	
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(devicetype).build();
		}	
	}
	
	
	
	
	
	@PostMapping(value = ApiRestURIConstants.DELETE_DEVICE_TYPE)
	public Response deleteDeviceType(@RequestBody DeviceDataType mDataType, @RequestHeader("headersparam") String headersparam) {
//		System.out.println("In controller...");
		String[] deviceResponse=null;
		manager=new DeviceTypeMasterManager();
		spltstr=EncryptionData.getparamencryption(headersparam);
		
		deviceResponse = manager.deleteDeviceType(mDataType, spltstr);
		if(deviceResponse[1]==null || deviceResponse[1].equals("") || deviceResponse[1].equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Failed to save details")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(deviceResponse[1].equals("Successfully Deleted.")) {
			return Response.status(Integer.parseInt(deviceResponse[0]))
					.entity(deviceResponse[1])
					.header("Access-Control-Allow-Origin", "*").build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(deviceResponse).build();
		}		
	}
	
}
