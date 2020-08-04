package com.app.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;

import com.app.main.business.MapVehicleManager;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.LiveVehicleResponse;
import com.app.main.model.MapAlldataResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
public class MapVehicleController {
	public static String[] spltstr = null;
	static MapVehicleManager  mapVehicleManager=new MapVehicleManager();
	//static MapVehicleManager  mapvahilceResponse=new MapVehicleManager();
	
	
	@GetMapping(value = ApiRestURIConstants.LIVE_VEHICLE)
		public Response getLiveVehiles(@QueryParam("vehicleno") String vehicleno,@RequestHeader("headersparam") String headersparam) {
		 System.out.println("heeeerer jf");
		 spltstr = EncryptionData.getparamencryption(headersparam);
		 
			MapAlldataResponse vehicledata =null;
			//spltstr = EncryptionData.getparamencryption(headersparam);
		
			try {

				vehicledata = mapVehicleManager.livevehicle(vehicleno,spltstr);
				
			} catch (Exception e) {
				System.err.println(e);
			}
//			if (vehicledata == null || vehicledata.size() == 0) {
//				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(vehicledata)
//						.header("Access-Control-Allow-Origin", "*").build();
//			} else {
				return Response.status(200).entity(vehicledata).build();
			//}
		}
	
	
	

		

		
		@GetMapping(value = ApiRestURIConstants.HISTORY_VEHICLE)
			public Response getHistoryVehicle(@QueryParam("vehicleno") String vehicleno, @QueryParam("date") String date,
					@QueryParam("time1") String time1, @QueryParam("time2") String time2) {
			
			//List<MapAlldataResponse> vehicledata =null;
			List<MapVehicleManager> vehicledata =null;
				//spltstr = EncryptionData.getparamencryption(headersparam);
				try {

					vehicledata = mapVehicleManager.historyvehicle(vehicleno,date,time1,time2);

					if (vehicledata == null || vehicledata.size() == 0) {
						return Response.status(201).entity("No Data Found").build();
					} else {
						return Response.status(200).entity(vehicledata).build();
					}
					
				} catch (Exception e) {
					System.err.println(e);
					return Response.status(Status.INTERNAL_SERVER_ERROR).entity(vehicledata)
							.header("Access-Control-Allow-Origin", "*").build();
				}
			}
	
}
