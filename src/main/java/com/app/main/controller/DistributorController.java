package com.app.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.DistributorMnager;
import com.app.main.dao.DistributorData;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.DealerDetailsResponse;
import com.app.main.model.DistributorResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

/* Developer : Ishant Baghele
 * Created Date :  2019-07-27
 * Updated Date : 
 * Description  : API Controller For Distributor Master Details 
 *  */

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class DistributorController {

	public static String[] spltstr = null;

	static DistributorMnager distributorManager = new DistributorMnager();

	@PostMapping(value = ApiRestURIConstants.SET_DISTRIBUTOR)
	public Response testapipost(@RequestBody DistributorData distriData,
			@RequestHeader("headersparam") String headersparam) {

		String distriResponse = null;

		spltstr = EncryptionData.getparamencryption(headersparam);
		distriResponse = distributorManager.distributor(distriData, spltstr);
		if (distriResponse.equals("Error") || distriResponse == null || distriResponse.equals("")
				|| distriResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Details Failed to Save")
					.header("Access-Control-Allow-Origin", "*").build();
		} else if (distriResponse.equals("Successfully Saved.")) {
			return Response.status(200).entity(distriResponse).header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(distriResponse).build();
		}

	}

	/*
	 * Developer : Pallavi Dhage Created Date : 2019-07-26 Updated Date :
	 * Description : API Controller For Dealer Master Details
	 */

	@PostMapping(value = ApiRestURIConstants.GET_DISTRIBUTOR_DETAILS)
	public Response getdistribuotdetails(@RequestBody DistributorData distriData,
			@RequestHeader("headersparam") String headersparam) {
		// System.out.println("Call me distribuor details ");
		List<DistributorData> distributorDetails = new ArrayList<DistributorData>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {
			distributorDetails = distributorManager.getdistribuotdetails(spltstr, distriData);
			// System.out.println("details: "+distributorDetails.get(0).getDistributorname());
		} catch (Exception e) {
			System.err.println(e);
		}
		if (distributorDetails == null || distributorDetails.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(distributorDetails)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(distributorDetails).build();
		}
	}

}
