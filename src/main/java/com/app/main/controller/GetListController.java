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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.connection.APIResponseModel;
import com.app.main.business.ListManager;
import com.app.main.dao.DealerData;
import com.app.main.dao.DeviceAssignDealerData;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.CertAuthListResponse;
import com.app.main.model.CompanyListResponse;
import com.app.main.model.CustomerListResponse;
import com.app.main.model.DealerListResponse;
import com.app.main.model.DeviceListResponse;
import com.app.main.model.DistributorListResponse;
import com.app.main.model.M2MResponse;
import com.app.main.model.ModelListResponse;
import com.app.main.model.NetworkListResponse;
import com.app.main.model.RTOListResponse;
import com.app.main.model.VehicleMakeResponse;
import com.app.main.model.VehicleModelResponse;
import com.app.main.model.VehicleTypeNameResponse;
import com.sun.jersey.api.client.ClientResponse.Status;


/* Developer    : Pallavi Dhage
 * Created Date : 2019-07-26 
 * Updated Date : 2019-07-27 & 2019-07-28
 * Update By    : Pallavi Dhage
 * Updation     : Added New List API
 * Description  : API Controller For All Master List (Dropdown Master)
 *  */

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class GetListController {
	public static String[] spltstr = null;
	static  ListManager ListManager=new ListManager();
	
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-26 
	 * Description  : API Controller For Distributor List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_DISTRIBUTOR)
	public Response getDistributor(@RequestHeader("headersparam") String headersparam) {
		List<DistributorListResponse> distributorList = new ArrayList<DistributorListResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			distributorList = ListManager.getDistributorList(spltstr);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (distributorList == null || distributorList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(distributorList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(distributorList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-26 
	 * Description  : API Controller For All Device Model List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_MODEL)
	public Response getModel(@RequestHeader("headersparam") String headersparam) {
		List<ModelListResponse> modelList = new ArrayList<ModelListResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {
			modelList = ListManager.getModelList(spltstr);
			System.out.println("modelList size : " + modelList.size());
		} catch (Exception e) {
			System.err.println(e);
		}
		if (modelList == null || modelList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(modelList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(modelList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-26 
	 * Description  : API Controller For Network List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_NETWORK)
	public Response getNetworkList(@RequestHeader("headersparam") String headersparam) {
		List<NetworkListResponse> networkList = new ArrayList<NetworkListResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			networkList = ListManager.getnetworkList(spltstr);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (networkList == null || networkList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(networkList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(networkList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-27
	 * Description  : API Controller For Company List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_COMPANY)
	public Response getVendorlist(@RequestHeader("headersparam") String headersparam) {
		List<CompanyListResponse> vendorList = new ArrayList<CompanyListResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			vendorList = ListManager.getComapanyList(spltstr);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (vendorList == null || vendorList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(vendorList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(vendorList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-27
	 * Description  : API Controller For Dealer List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_DEALER)
	public Response getDealerlist(@RequestHeader("headersparam") String headersparam , @RequestParam("ownersidnew") String ownersidnew) {
		List<DealerListResponse> dealerList = new ArrayList<DealerListResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {
			dealerList = ListManager.getDealerList(spltstr,ownersidnew);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (dealerList == null || dealerList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(dealerList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(dealerList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-27
	 * Description  : API Controller For Certificate Authority List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_CERT_AUTH)
	public Response getCertAuthlist(@RequestHeader("headersparam") String headersparam) {
		List<CertAuthListResponse> certAuthList = new ArrayList<CertAuthListResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			certAuthList = ListManager.getCertAuthListList(spltstr);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (certAuthList == null || certAuthList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(certAuthList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(certAuthList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-27
	 * Description  : API Controller For M2M Provider List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_M2M)
	public Response getM2Mlist(@RequestHeader("headersparam") String headersparam) {
		List<M2MResponse> m2mList = new ArrayList<M2MResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			m2mList = ListManager.getM2MList(spltstr);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (m2mList == null || m2mList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(m2mList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(m2mList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-27
	 * Description  : API Controller For RTO List (Dropdown Master)
	 *  */
	
	
	@GetMapping(value = ApiRestURIConstants.GET_RTO)
	public Response getRTOlist(@RequestHeader("headersparam") String headersparam,@RequestParam("stateid") String stateid) {
		List<RTOListResponse> rtoList = new ArrayList<RTOListResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			rtoList = ListManager.getRTOList(spltstr,stateid);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (rtoList == null || rtoList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(rtoList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(rtoList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-27
	 * Description  : API Controller For Device List (Dropdown Master)
	 *  */
	
	
	@GetMapping(value = ApiRestURIConstants.GET_DEVICE)
	public Response getDevicelist(@RequestHeader("headersparam") String headersparam,
			@RequestParam("type") String type,
			@RequestParam("ownersidnew") String ownersidnew) {
		List<DeviceListResponse> rtoList = new ArrayList<DeviceListResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			rtoList = ListManager.getDeviceList(spltstr,type,ownersidnew);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (rtoList == null || rtoList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(rtoList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(rtoList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-27
	 * Description  : API Controller For Vehicle Make List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_VEHICLE_MAKE)
	public Response getvehiclemake(@RequestHeader("headersparam") String headersparam) {
		List<VehicleMakeResponse> vehiclemakeList = new ArrayList<VehicleMakeResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			vehiclemakeList = ListManager.getgetvehiclemakeList(spltstr);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (vehiclemakeList == null || vehiclemakeList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(vehiclemakeList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(vehiclemakeList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-27
	 * Description  : API Controller For Model List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_VEHICLE_MODEL)
	public Response getvehicleModel(@RequestHeader("headersparam") String headersparam,@RequestParam("makeid") String makeid) {
		List<VehicleModelResponse> vehiclemodelList = new ArrayList<VehicleModelResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			vehiclemodelList = ListManager.getgetvehiclemodelList(spltstr,makeid);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (vehiclemodelList == null || vehiclemodelList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(vehiclemodelList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(vehiclemodelList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-27
	 * Description  : API Controller For Customer List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_CUSTOMER_LIST)
	public Response getCustomerList(@RequestHeader("headersparam") String headersparam) {
		List<CustomerListResponse> CustomerList = new ArrayList<CustomerListResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			CustomerList = ListManager.getCustomerList(spltstr);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (CustomerList == null || CustomerList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(CustomerList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(CustomerList).build();
		}
	}
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-27
	 * Description  : API Controller For Vehicle Class List (Dropdown Master)
	 *  */
	
	
	@GetMapping(value = ApiRestURIConstants.GET_VEHICLE_TYPE_NAME)
	public Response getVehicleTypeList(@RequestHeader("headersparam") String headersparam) {
		List<VehicleTypeNameResponse> vehtypeList = new ArrayList<VehicleTypeNameResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			vehtypeList = ListManager.getVehicleTypeList(spltstr);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (vehtypeList == null || vehtypeList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(vehtypeList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(vehtypeList).build();
		}
	}
	
	
	/* Developer    : Pallavi Dhage
	 * Created Date : 2019-07-28
	 * Description  : API Controller For Document List (Dropdown Master)
	 *  */
	
	@GetMapping(value = ApiRestURIConstants.GET_DOCUMENT_LIST)
	public Response getDocumentList(@RequestHeader("headersparam") String headersparam) {
		List<VehicleTypeNameResponse> docList = new ArrayList<VehicleTypeNameResponse>();
		spltstr = EncryptionData.getparamencryption(headersparam);
		try {

			docList = ListManager.getDocList(spltstr);
		} catch (Exception e) {
			System.err.println(e);
		}
		if (docList == null || docList.size() == 0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(docList)
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(docList).build();
		}
	}
	
	/**
	Developer : Prem Gaigole
	Date      : 2020-07-24
	Description : Select List Framework
	Update Date :
	Updation  
	**/
	
	@PostMapping(value = ApiRestURIConstants.GET_VEHICLE_LIST)
	public APIResponseModel getVehicleList(@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		APIResponseModel responseModel = new APIResponseModel();
		ListManager = new ListManager();
		try {
			responseModel = ListManager.vehiclelist(spltstr);
		} catch (Exception e) {
			responseModel.setEntity("INTERNAL SERVER ERROR" +e.toString());
			responseModel.setStatus(false);
			responseModel.setStatuscode(412);
		}
		return responseModel;
	}

	/**
	Developer : Prem Gaigole
	Date      : 2020-07-24
	Description : Select List Framework
	Update Date :
	Updation  
	**/
	
	@PostMapping(value = ApiRestURIConstants.GET_ROLL_LIST)
	public APIResponseModel getRoleList(@RequestBody RequestSelectDataDao devdata,@RequestHeader("headersparam") String headersparam) {
		spltstr = EncryptionData.getparamencryption(headersparam);
		APIResponseModel responseModel = new APIResponseModel();
		ListManager = new ListManager();
		try {
			responseModel = ListManager.rolelist(spltstr,devdata);
		} catch (Exception e) {
			responseModel.setEntity("INTERNAL SERVER ERROR" +e.toString());
			responseModel.setStatus(false);
			responseModel.setStatuscode(412);
		}
		return responseModel;
	}

}
