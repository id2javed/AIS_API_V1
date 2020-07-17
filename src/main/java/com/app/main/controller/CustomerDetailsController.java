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
import com.app.main.business.CustomerManager;
import com.app.main.dao.CustomerData;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.CustomerDetailsResponse;
import com.app.main.model.CustomerResponse;
import com.sun.jersey.api.client.ClientResponse.Status;



@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class CustomerDetailsController {

	static CustomerManager  customerManager=new CustomerManager();
	 public static String[] spltstr = null;
	

	 /* Developer : Tafseer
	  * Created Date : 2019-08-01
	  * Updated Date : 
	  * Description  : API Controller For Customer Entry 
	  *  */
	 
	 @PostMapping(value = ApiRestURIConstants.INSERT_CUSTOMER)
	public Response testapipost(@RequestBody CustomerData customerData,@RequestHeader("headersparam") String headersparam) {
		 
		List<CustomerResponse> customerResponse=null;
		spltstr=EncryptionData.getparamencryption(headersparam);
		customerResponse=customerManager.insertcustomer(customerData,spltstr);
		if(customerResponse==null || customerResponse.size()==0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(customerResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}else {
			return Response.status(200).entity(customerResponse).build();
		}
		
	}
	 
	 /* Developer : Tafseer khan
	  * Created Date : 2019-08-01
	  * Updated Date : 27-08-2019
	  * Description  : API Controller For Customer Details   
	  *  */
	 
	 @PostMapping(value = ApiRestURIConstants.GET_CUSTOMER)
		public Response getDealer(@RequestBody CustomerData customerdata ,@RequestHeader("headersparam") String headersparam)
	 {
		 System.out.println("Get customer637487534854");
			//List<CustomerDetailsResponse> customerDetails = new ArrayList<CustomerDetailsResponse>();
			CustomerDetailsResponse customerDetails=null;
		 //List<CustomerDetailsResponse> customerDetails = null;
			spltstr = EncryptionData.getparamencryption(headersparam);
			try {

				customerDetails = customerManager.getcustomerdetails(spltstr,customerdata);
			} catch (Exception e) {
				System.err.println(e);
			}

			if (customerDetails.equals("Error") ||customerDetails == null ||customerDetails.equals("") || customerDetails.equals("null")) {
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(customerDetails)
						.header("Access-Control-Allow-Origin", "*").build();
			} else {
				return Response.status(200).entity(customerDetails).build();
			}
		}
	 
	 
	
	 

}
