package com.app.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.VendorManager;
//import com.app.main.dao.DealerData;
import com.app.main.encryption.EncryptionData;

import com.app.main.model.VendorDetailsResponse;
import com.sun.jersey.api.client.ClientResponse.Status;


	@RestController
	@EnableAutoConfiguration
	@CrossOrigin(origins = "${angularOrigin}")
	public class VendorController {
		static VendorManager vendorManager=new VendorManager();
		 public static String[] spltstr = null;
		
		 
		 /* Developer : Pallavi Dhage
		  * Created Date : 2019-07-26
		  * Updated Date : 
		  * Description  : API Controller For Dealer Master  Entry 
		  *  */
		 
		 @GetMapping(value = ApiRestURIConstants.GET_VENDOR_DETAILS)
			public Response getVendor(@RequestHeader("headersparam") String headersparam) {
				List<VendorDetailsResponse> vendorDetails = new ArrayList<VendorDetailsResponse>();
				spltstr = EncryptionData.getparamencryption(headersparam);
				try {

					vendorDetails = vendorManager.getVendorDetails(spltstr);
							
				} catch (Exception e) {
					System.err.println(e);
				}
				if (vendorDetails == null || vendorDetails.size() == 0) {
					return Response.status(Status.INTERNAL_SERVER_ERROR).entity(vendorDetails)
							.header("Access-Control-Allow-Origin", "*").build();
				} else {
					return Response.status(200).entity(vendorDetails).build();
				}
			
}
}

