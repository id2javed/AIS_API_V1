package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.dao.DealerData;
import com.app.main.dao.DeviceAssignDealerData;
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
import com.app.queryconstants.QueryConstants;

/* Developer    : Pallavi Dhage
 * Created Date : 2019-07-26 
 * Updated Date : 2019-07-27 & 2019-07-28
 * Update By    : Pallavi Dhage
 * Updation     : Added Method Implementation Logic for New List 
 * Description  : Method Implementation Logic For All Master List (Dropdown Master)
 *  */

public class ListManager {

	public List<DistributorListResponse> getDistributorList(String[] spltstr) {
		List<DistributorListResponse> distributorList  = new ArrayList<DistributorListResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getdistributorList;
		try {
			getdistributorList = con.prepareStatement(QueryConstants.selectdistributorlist);
			
			getdistributorList.setString(1, "distributorlist");
			getdistributorList.setString(2, spltstr[4]);	
			getdistributorList.setString(3, spltstr[3]);
			getdistributorList.setString(4, spltstr[4]);
			getdistributorList.setString(5, spltstr[0]);
			ResultSet distRS = getdistributorList.executeQuery();
			  while(distRS.next()) {
				  DistributorListResponse obj=new DistributorListResponse();
				  obj.setDistributorId(distRS.getString(1));
				  obj.setDistributorName(distRS.getString(2));
				  distributorList.add(obj);
					
				}
			  getdistributorList.close();
			  distRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			
			DBConnection.getCloseConnection(con);
		}
		return distributorList;
	}

	public List<ModelListResponse> getModelList(String[] spltstr) {
List<ModelListResponse> modelList  = new ArrayList<ModelListResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getModelList;
		try {
			getModelList = con.prepareStatement(QueryConstants.selectmodellist);
			
			getModelList.setString(1, "modellist");
			getModelList.setString(2, spltstr[4]);	
			getModelList.setString(3, spltstr[3]);
			getModelList.setString(4, spltstr[4]);
			getModelList.setString(5, spltstr[0]);
			System.out.println("Model Query:- " + getModelList.toString());
			ResultSet modelRS = getModelList.executeQuery();
			  while(modelRS.next()) {
				  ModelListResponse obj=new ModelListResponse();
				  obj.setModelId(modelRS.getString(1));
				  obj.setModelName(modelRS.getString(2));
				  obj.setModelCode(modelRS.getString(3));
				  modelList.add(obj);
					
				}
			  getModelList.close();
			  modelRS.close();  
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return modelList;
	}

	public List<NetworkListResponse> getnetworkList(String[] spltstr) {
List<NetworkListResponse> networkList  = new ArrayList<NetworkListResponse>(); 
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getNetworkList;
		try {
			getNetworkList = con.prepareStatement(QueryConstants.selectnetworklist);
			
			getNetworkList.setString(1, "networklist");
			getNetworkList.setString(2, spltstr[4]);	
			getNetworkList.setString(3, spltstr[3]);
			getNetworkList.setString(4, spltstr[4]);
			getNetworkList.setString(5, spltstr[0]);
			ResultSet modelRS = getNetworkList.executeQuery();
			  while(modelRS.next()) {
				  NetworkListResponse obj=new NetworkListResponse();
				  obj.setNetworkId(modelRS.getString(1));
				  obj.setNetworkName(modelRS.getString(2));
				  networkList.add(obj);
					
				}
			  getNetworkList.close();
			  modelRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return networkList;
	}

	public List<CompanyListResponse> getComapanyList(String[] spltstr) {
List<CompanyListResponse> companyList  = new ArrayList<CompanyListResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getCompanyList;
		try {
			getCompanyList = con.prepareStatement(QueryConstants.selectvendorlist);
			
			getCompanyList.setString(1, "vendorlist");
			getCompanyList.setString(2, spltstr[4]);	
			getCompanyList.setString(3, spltstr[3]);
			getCompanyList.setString(4, spltstr[4]);
			getCompanyList.setString(5, spltstr[0]);
			ResultSet compRS = getCompanyList.executeQuery();
			  while(compRS.next()) {
				  CompanyListResponse obj=new CompanyListResponse();
				  obj.setVendorId(compRS.getString(1));
				  obj.setVendorName(compRS.getString(2));
				  obj.setMfgCode(compRS.getString(3));
				  companyList.add(obj);
					
				}
			  getCompanyList.close();
			  compRS.close(); 
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return companyList;
	}

	public List<DealerListResponse> getDealerList(String[] spltstr,String ownersidnew) {
List<DealerListResponse> dealerList  = new ArrayList<DealerListResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getDealerList;
		try {
			getDealerList = con.prepareStatement(QueryConstants.selectdealerlist);
			
			getDealerList.setString(1, "dealerlist");
			getDealerList.setString(2, spltstr[4]);	
			if(ownersidnew.equals("") || ownersidnew==null)
			{
				
				
				getDealerList.setString(4, spltstr[4]);
				getDealerList.setString(3, spltstr[3]);
			}else
			{
				getDealerList.setString(4, ownersidnew);
				getDealerList.setString(3, "12");
			}
			getDealerList.setString(5, spltstr[0]);
			
			
			System.out.println("getDealerList Query==>> "+getDealerList.toString());
			ResultSet compRS = getDealerList.executeQuery();
			  while(compRS.next()) {
				  DealerListResponse obj=new DealerListResponse();
				  obj.setDealerId(compRS.getString(1));
				  obj.setDealerName(compRS.getString(2));
				  dealerList.add(obj);
					
				}
			  getDealerList.close();
			  compRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return dealerList;
	}

	public List<CertAuthListResponse> getCertAuthListList(String[] spltstr) {
List<CertAuthListResponse> certAuthList  = new ArrayList<CertAuthListResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getCertAuthList;
		try {
			getCertAuthList = con.prepareStatement(QueryConstants.selectcertauthlist);
			
			getCertAuthList.setString(1, "cetificateauthlist");
			getCertAuthList.setString(2, spltstr[4]);	
			getCertAuthList.setString(3, spltstr[3]);
			getCertAuthList.setString(4, spltstr[4]);
			getCertAuthList.setString(5, spltstr[0]);
			ResultSet certAuthRS = getCertAuthList.executeQuery();
			  while(certAuthRS.next()) {
				  CertAuthListResponse obj=new CertAuthListResponse();
				  obj.setCertauthId(certAuthRS.getString(1));
				  obj.setCertauthAgency(certAuthRS.getString(2));
				  obj.setCertauthCode(certAuthRS.getString(3));
				  certAuthList.add(obj);
				}
			  getCertAuthList.close();
			  certAuthRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return certAuthList;
	}

	public List<M2MResponse> getM2MList(String[] spltstr) {
List<M2MResponse> M2MList  = new ArrayList<M2MResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getM2MList;
		try {
			getM2MList = con.prepareStatement(QueryConstants.m2mproviderlist);
			
			getM2MList.setString(1, "m2mproviderlist");
			getM2MList.setString(2, spltstr[4]);	
			getM2MList.setString(3, spltstr[3]);
			getM2MList.setString(4, spltstr[4]);
			getM2MList.setString(5, spltstr[0]);
			ResultSet M2MRS = getM2MList.executeQuery();
			  while(M2MRS.next()) {
				  M2MResponse obj=new M2MResponse();
				  obj.setM2mproviderId(M2MRS.getString(1));
				  obj.setM2mproviderName(M2MRS.getString(2));
				  obj.setM2mproviderCode(M2MRS.getString(3));
				  M2MList.add(obj);
				}
			  getM2MList.close();
			  M2MRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return M2MList;
	}

	public List<RTOListResponse> getRTOList(String[] spltstr, String stateid) {
List<RTOListResponse> RTOList  = new ArrayList<RTOListResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getRTOList;
		try {
			getRTOList = con.prepareStatement(QueryConstants.rtolist);
			
			getRTOList.setString(1, "subrtolist");
			getRTOList.setString(2, spltstr[4]);	
			getRTOList.setString(3, spltstr[3]);
			getRTOList.setString(4, spltstr[4]);
			getRTOList.setString(5, spltstr[0]);
			getRTOList.setString(6, stateid);
			ResultSet rtoRS = getRTOList.executeQuery();
			  while(rtoRS.next()) {
				  RTOListResponse obj=new RTOListResponse();
				  obj.setRtoId(rtoRS.getString(1));
				  obj.setRtoName(rtoRS.getString(2));
				  RTOList.add(obj);
				}
			  getRTOList.close();
			  rtoRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return RTOList;
	}

	public List<DeviceListResponse> getDeviceList(String[] spltstr, String type,String ownersidnew) {
List<DeviceListResponse> deviceList  = new ArrayList<DeviceListResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getDeviceList;
		try {
			getDeviceList = con.prepareStatement(QueryConstants.devicelist_Assign_vehicle);
			
			getDeviceList.setString(1, type);
			getDeviceList.setString(2, spltstr[4]);	

			if(type.equals("devicelist_Assign_fordealer"))
			{
				getDeviceList.setString(4, ownersidnew);
				getDeviceList.setString(3, "12");
			}else
			{
				getDeviceList.setString(4, spltstr[4]);
				getDeviceList.setString(3, spltstr[3]);
			}

			getDeviceList.setString(5, spltstr[0]);
			
			System.out.println("getDeviceList Query==>> "+getDeviceList.toString());
			
			ResultSet deviceRS = getDeviceList.executeQuery();
			  while(deviceRS.next()) {
				  DeviceListResponse obj=new DeviceListResponse();
				  obj.setDeviceid(deviceRS.getString(1));
				  obj.setDeviceimeino(deviceRS.getString(2));
				  deviceList.add(obj);
				}
			  getDeviceList.close();
			  deviceRS.close();
		} catch (SQLException e) {
			System.err.println("device list "+e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return deviceList;
	}

	public List<VehicleMakeResponse> getgetvehiclemakeList(String[] spltstr) {
List<VehicleMakeResponse> vehiclemakeList  = new ArrayList<VehicleMakeResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getvehiclemakeList;
		try {
			getvehiclemakeList = con.prepareStatement(QueryConstants.vehiclemakelist);
			
			getvehiclemakeList.setString(1, "vehiclemakelist");
			getvehiclemakeList.setString(2, spltstr[4]);	
			getvehiclemakeList.setString(3, spltstr[3]);
			getvehiclemakeList.setString(4, spltstr[4]);
			getvehiclemakeList.setString(5, spltstr[0]);
			ResultSet deviceRS = getvehiclemakeList.executeQuery();
			  while(deviceRS.next()) {
				  VehicleMakeResponse obj=new VehicleMakeResponse();
				  obj.setMakeId(deviceRS.getString(1));
				  obj.setMakeName(deviceRS.getString(2));
				  vehiclemakeList.add(obj);
				}
			  getvehiclemakeList.close();
			  deviceRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return vehiclemakeList;
	}

	public List<VehicleModelResponse> getgetvehiclemodelList(String[] spltstr,String makeid) {
List<VehicleModelResponse> vehiclemodelList  = new ArrayList<VehicleModelResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getvehiclemodelList;
		try {
			getvehiclemodelList = con.prepareStatement(QueryConstants.vehiclemodellist);
			
			getvehiclemodelList.setString(1, "vehiclemodellist");
			getvehiclemodelList.setString(2, spltstr[4]);	
			getvehiclemodelList.setString(3, spltstr[3]);
			getvehiclemodelList.setString(4, spltstr[4]);
			getvehiclemodelList.setString(5, spltstr[0]);
			getvehiclemodelList.setString(6, makeid);
			ResultSet deviceRS = getvehiclemodelList.executeQuery();
			  while(deviceRS.next()) {
				  VehicleModelResponse obj=new VehicleModelResponse();
				  obj.setModelId(deviceRS.getString(1));
				  obj.setModelName(deviceRS.getString(2));
				  vehiclemodelList.add(obj);
				}
			  getvehiclemodelList.close();
			  deviceRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return vehiclemodelList;
	}

	public List<CustomerListResponse> getCustomerList(String[] spltstr) {
List<CustomerListResponse> customerList  = new ArrayList<CustomerListResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getcustomerList;
		try {
			getcustomerList = con.prepareStatement(QueryConstants.customerlist);
			
			getcustomerList.setString(1, "customerlist");
			getcustomerList.setString(2, spltstr[4]);	
			getcustomerList.setString(3, spltstr[3]);
			getcustomerList.setString(4, spltstr[4]);
			getcustomerList.setString(5, spltstr[0]);
			ResultSet deviceRS = getcustomerList.executeQuery();
			  while(deviceRS.next()) {
				  CustomerListResponse obj=new CustomerListResponse();
				  obj.setCustomerId(deviceRS.getString(1));
				  obj.setCustomerName(deviceRS.getString(2));
				  customerList.add(obj);
				}
			  getcustomerList.close();
			  deviceRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return customerList;
	}

	public List<VehicleTypeNameResponse> getVehicleTypeList(String[] spltstr) {
List<VehicleTypeNameResponse> vehtypeList  = new ArrayList<VehicleTypeNameResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getVehTypeList;
		try {
			getVehTypeList = con.prepareStatement(QueryConstants.vehicleclasslist);
			
			getVehTypeList.setString(1, "vehicleclasslist");
			getVehTypeList.setString(2, spltstr[4]);	
			getVehTypeList.setString(3, spltstr[3]);
			getVehTypeList.setString(4, spltstr[4]);
			getVehTypeList.setString(5, spltstr[0]);
			ResultSet deviceRS = getVehTypeList.executeQuery();
			  while(deviceRS.next()) {
				  VehicleTypeNameResponse obj=new VehicleTypeNameResponse();
				  obj.setId(deviceRS.getString(1));
				  obj.setName(deviceRS.getString(2));
				  vehtypeList.add(obj);
				}
			  getVehTypeList.close();
			  deviceRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return vehtypeList;
	}

	public List<VehicleTypeNameResponse> getDocList(String[] spltstr) {
List<VehicleTypeNameResponse> docList  = new ArrayList<VehicleTypeNameResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getDocList;
		try {
			getDocList = con.prepareStatement(QueryConstants.documentlist);
			
			getDocList.setString(1, "documentlist");
			getDocList.setString(2, spltstr[4]);	
			getDocList.setString(3, spltstr[3]);
			getDocList.setString(4, spltstr[4]);
			getDocList.setString(5, spltstr[0]);
			ResultSet docRS = getDocList.executeQuery();
			  while(docRS.next()) {
				  VehicleTypeNameResponse obj=new VehicleTypeNameResponse();
				  obj.setId(docRS.getString(1));
				  obj.setName(docRS.getString(2));
				  docList.add(obj);
				}
			  getDocList.close();
			  docRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return docList;
	}

}
