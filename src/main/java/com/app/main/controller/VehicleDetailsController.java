package com.app.main.controller;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.connection.APIResponseModel;
import com.app.main.business.VehicleDetailsManager;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.dao.VehicleData;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.VehicleDetailsResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class VehicleDetailsController {

	public static String[] spltstr = null;
	APIResponseModel apiResponseModel = null;
	static VehicleDetailsManager vehicledetailsManager = new VehicleDetailsManager();

	@PostMapping(value = ApiRestURIConstants.GET_VEHICLE_DETAILS)
	public Response testapipost(@RequestBody VehicleData vehdata, @RequestHeader("headersparam") String headersparam) {

		VehicleDetailsResponse vehicleResponse = null;

		spltstr = EncryptionData.getparamencryption(headersparam);
		vehicleResponse = vehicledetailsManager.vehicle(spltstr, vehdata);

		System.out.println("Record : " + vehicleResponse.getData().size());

		if (vehicleResponse.equals("Error") || vehicleResponse == null || vehicleResponse.equals("")
				|| vehicleResponse.equals("null")) {

			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("INTERNAL_SERVER_ERROR")
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(vehicleResponse).header("Access-Control-Allow-Origin", "*").build();
		}

	}
	@PostMapping(value = ApiRestURIConstants.GET_LIVE_VEHICLE_DETAILS)
	public APIResponseModel emergencyrawdata(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
		try {
			apiResponseModel= vehicledetailsManager.Livevehiclestatus(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
    	}

	@PostMapping(value = ApiRestURIConstants.GET_SINGLE_VEHICLE_LIVE_DATA_DETAILS)
	public APIResponseModel singlevehiclelivedata(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		apiResponseModel = new APIResponseModel();
		spltstr=EncryptionData.getparamencryption(headersparam);
		try {
			apiResponseModel= vehicledetailsManager.singlevehiclelivedata(spltstr, devdata);
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(412);
		}
		return apiResponseModel;
    	}

}