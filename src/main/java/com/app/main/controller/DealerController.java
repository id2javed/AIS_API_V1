package com.app.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.DealerManager;
import com.app.main.dao.DealerData;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.DealerDetailsResponse;
import com.app.main.model.DealerResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

/* Developer : Pallavi Dhage
 * Created Date : 2019-07-26
 * Updated Date : 
 * Description  : API Controller For Dealer Master  
 *  */

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class DealerController {
	static DealerManager  dealerManager=new DealerManager();
	 public static String[] spltstr = null;
	
	 
	 /* Developer : Pallavi Dhage
	  * Created Date : 2019-07-26
	  * Updated Date : 
	  * Description  : API Controller For Dealer Master  Entry 
	  *  */
	 
	 @PostMapping(value = ApiRestURIConstants.INSERT_DEALER)
	 @Consumes("application/json")
		@Produces(MediaType.APPLICATION_JSON)
	public Response testapipost(@RequestBody DealerData dealerData,@RequestHeader("headersparam") String headersparam) {
		String dealerResponse=null;
		spltstr=EncryptionData.getparamencryption(headersparam);
		dealerResponse=dealerManager.insertdealer(dealerData,spltstr);
		

		if(dealerResponse.equals("Error") || dealerResponse==null || dealerResponse.equals("") || dealerResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Details Failed to Save")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(dealerResponse.equals("Successfully Saved.")) {
			return Response.status(200)
					.entity(dealerResponse).header("Access-Control-Allow-Origin", "*").build();
		}else{	
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(dealerResponse).build();
		}
		/*if(dealerResponse==null || dealerResponse.equals("Failed to Save Details")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(dealerResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}else {
			return Response.status(200).entity(dealerResponse).build();
		}
		*/
	}
	 
	 /* Developer : Pallavi Dhage
	  * Created Date : 2019-07-26
	  * Updated Date : 
	  * Description  : API Controller For Dealer Master Details 
	  *  */
	 
	 	@PostMapping(value = ApiRestURIConstants.GET_DEALER_DETAILS)
		public Response getDealer(@RequestHeader("headersparam") String headersparam) {
			List<DealerDetailsResponse> dealerDetails = new ArrayList<DealerDetailsResponse>();
			spltstr = EncryptionData.getparamencryption(headersparam);
			try {
				dealerDetails = dealerManager.getDealerDetails(spltstr);
			} catch (Exception e) {
				System.err.println(e);
			}
						
			if (dealerDetails == null || dealerDetails.size() == 0) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(dealerDetails)
						.header("Access-Control-Allow-Origin", "*").build();
			} else {
				return Response.status(200).entity(dealerDetails).build();
			}
			
		}
}
