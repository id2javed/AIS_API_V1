package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.app.connection.APIResponseModel;
import com.app.connection.DBConnection;
import com.app.main.dao.CustomerData;
import com.app.main.dao.ListDataResponse;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.model.CustomerDetailsResponse;
import com.app.main.model.CustomerResponse;
import com.app.main.model.DeviceDetailsResponse;
import com.app.queryconstants.PaginationControl;
import com.app.queryconstants.QueryConstants;

public class CustomerManager {

	public List<CustomerResponse> insertcustomer(CustomerData customerData, String[] spltstr){
		CustomerDetailsResponse custDetResp=new CustomerDetailsResponse();
		List<CustomerResponse> customerResponse=new ArrayList<CustomerResponse>();
	Connection con=DBConnection.getMainConnection();
	PreparedStatement insertCustomer;
	try {
		insertCustomer = con.prepareStatement(QueryConstants.insertcustomer);
		//	public static String insertcustomer="select * from masters.insertprocedure('insertcustomer',  'customerid',  'customername', 'customertype', 'customermobileno', 'customeremailid',  'customeralternateno',  'customeraddress', 'customercity', 'customerpincode', 'creatorloginid','state', 'distributorid', 'dealerid',  '', '','', '', '', '',  '', '', '', '','', '', '', '','', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','');";

		insertCustomer.setString(1, "insertcustomer");
		insertCustomer.setString(2, "");
		insertCustomer.setString(3,customerData.getCustomername());
		insertCustomer.setString(4, "");
		insertCustomer.setString(5,customerData.getCustomermobileno());
		insertCustomer.setString(6,customerData.getCustomeremailid());
		insertCustomer.setString(7, customerData.getCustomeralternateno());
		insertCustomer.setString(8, customerData.getCustomeraddress());
		insertCustomer.setString(9, customerData.getCustomercity());
		insertCustomer.setString(10, customerData.getCustomerpincode());
		insertCustomer.setString(11, spltstr[0]);
		insertCustomer.setString(12, customerData.getState());
		insertCustomer.setString(13, customerData.getDistributorid());
		insertCustomer.setString(14, customerData.getDealerid());
		insertCustomer.setString(15, customerData.getLoginame());
		insertCustomer.setString(16, customerData.getPassword());
		

		
		
		System.out.println("Customer Query==>> "+insertCustomer.toString());
		
	
	
	
		ResultSet dealerRS = insertCustomer.executeQuery();
		  while(dealerRS.next()) {
			  CustomerResponse obj=new CustomerResponse();
			  obj.setResponse(dealerRS.getString(1));
			  customerResponse.add(obj);
				
			}
		  insertCustomer.close(); 
		  dealerRS.close();
		  
	} catch (SQLException e) {
		System.out.println("Hii Exception ==>> "+e);
	}finally {
		DBConnection.getCloseConnection(con);
	}
	
return customerResponse;

}
	
	
	

	public CustomerDetailsResponse getcustomerdetails(String[] spltstr, CustomerData customerdata) {
		List<CustomerDetailsResponse> customersDetails  = new ArrayList<CustomerDetailsResponse>(); 
		CustomerDetailsResponse custDetResp=new CustomerDetailsResponse();
		
		long start = 0, end = 0;
		String seraccbyoffset="";
		try {
			if(customerdata.getPageno().equals("NA")) {
				seraccbyoffset="";
			}else if(customerdata.getItemsPerPage().equals("NA")) {
				seraccbyoffset="";
			}else 
			
			if (Integer.parseInt(customerdata.getPageno()) != 1) {
				start = Integer.parseInt(customerdata.getItemsPerPage()) * (Integer.parseInt(customerdata.getPageno()) - 1);
				end = start + Integer.parseInt(customerdata.getItemsPerPage());
				seraccbyoffset=" offset "+start+" limit "+customerdata.getItemsPerPage()+"";
				start++;
			} else {
				start = 0;
				end = start + Integer.parseInt(customerdata.getItemsPerPage());
				seraccbyoffset=" offset "+start+" limit "+customerdata.getItemsPerPage()+"  ";
				start++;
			}
			
		}catch (Exception e) {					
			System.err.println("e ----  "+e);
		}
		
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getCustomers;
		try {
			getCustomers = con.prepareStatement(QueryConstants.getcustomerdetails);
			
			getCustomers.setString(1, "customerdetails");
			getCustomers.setString(2, spltstr[4]);	
			getCustomers.setString(3, spltstr[3]);
			getCustomers.setString(4, spltstr[4]);
			getCustomers.setString(5, spltstr[0]);
			getCustomers.setString(6, customerdata.getSearchby());
			getCustomers.setString(7,seraccbyoffset);
			getCustomers.setString(8,"");

			ResultSet compRS = getCustomers.executeQuery();
			System.out.println("hiii customer Details    " +getCustomers.toString());

			 
				  
				//  customerid, customername, customertype, customermobileno, customeremailid, customeralternateno, customeraddress, customercity, customerpincode
				  try{
					  
					  while(compRS.next()) {
					  CustomerDetailsResponse obj=new CustomerDetailsResponse();
					  obj.setCustomerid(compRS.getString(1));
					  obj.setCustomername(compRS.getString(2));
					  obj.setCustomertype(compRS.getString(3));
					  obj.setCustomermobileno(compRS.getString(4));
					  obj.setCustomeremailid(compRS.getString(5));
					  obj.setCustomeralternateno(compRS.getString(6));
					  obj.setCustomeraddress(compRS.getString(7));
					  obj.setCustomercity(compRS.getString(8));
					  obj.setCustomerpincode(compRS.getString(9));
					  obj.setCustomerstate(compRS.getString(10));
					  obj.setDistributor(compRS.getString(11));
					  obj.setDealer(compRS.getString(12));
					  customersDetails.add(obj);  
					  }
					  
					  int count=0;
						 ResultSet rs4=null;
						try
						{
							PreparedStatement getcustcount;
							getcustcount = con.prepareStatement(QueryConstants.getcustomerdetails);
							getcustcount.setString(1, "devicedetails");
							getcustcount.setString(2, spltstr[4]);//company id send owners id
							getcustcount.setString(3, spltstr[3]);//roleid
							getcustcount.setString(4, spltstr[4]);//ownersid
							getcustcount.setString(5,spltstr[0]);//loginid
							getcustcount.setString(6,customerdata.getSearchby());//search by
							getcustcount.setString(7,"");
							getcustcount.setString(8,"Count");
							
						//System.out.println("Device Details Query Count ==>> "+getcustcount.toString());
							
							  rs4 = getcustcount.executeQuery();
							  
							  
							  while (rs4.next()) {
									
								  count=rs4.getInt(1); 
								}
							  
							  
						}catch(Exception e)
						{
							
						}
						
						custDetResp = new CustomerDetailsResponse();
						custDetResp.setData(customersDetails);
						custDetResp.setTotal(count);
						
						custDetResp.setResponse("Successfully Saved!");
					  
				  }catch (Exception e) {
					  System.err.println("iterate error cdealer "+e);
				}
				
			  getCustomers.close();
			  compRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return custDetResp;
	}
	
	/**
	   Developer : Prem Gaigole
	   Date      : 2020-07-09
	   Description : Select framework Manager
	   Update Date :
	   Updation  
	 * 
	 **/
	
	public APIResponseModel customerdashboard(String[] spltstr, RequestSelectDataDao devdata) {
		RequestSelectDataDao obj = null;
		List<RequestSelectDataDao> detailsdata = new ArrayList<RequestSelectDataDao>();
		ListDataResponse listData = new ListDataResponse();
		APIResponseModel apiResponseModel = new APIResponseModel();
		ResultSet rscount = null;
		

		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getdev = null;
		String searchByOffset=PaginationControl.getPaginationControl(devdata.getParam2(),devdata.getParam3()); // param2 = pageno & param 3 = ItemPerPage
		String [] searchByOffsetSplit=searchByOffset.split("&&");
		
		try {
//			select * from masters.selectdashboard('customerdashboard', 'companyid', 'roleid', 'ownersid',
//					'loginid', '', '', '', '', '',  '');
			getdev = con.prepareStatement(QueryConstants.customerdashboardcount);
			getdev.setString(1, "customerdashboard");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			
			rscount = getdev.executeQuery();
			System.out.println("Query == > " + getdev.toString());
	
			int rownumber=0;
			if(rscount.next())
			{
				
				listData.setParam1(rscount.getString(1));
				rownumber=Integer.parseInt(searchByOffsetSplit[1]);
				
				getdev = con.prepareStatement(QueryConstants.customerdashboardcount);
				getdev.setString(1, "customerdashboard");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				
				System.out.println("getdev "+getdev);
          ResultSet rs=getdev.executeQuery();
          System.out.println("while status == " +rs.next());
			while (rs.next()) {
				
//				totalvehicle, running, stop, idle, nonpolling
				
				obj = new RequestSelectDataDao();
				obj.setParam1(Integer.toString(rownumber++)); // Srno
				obj.setParam2(rs.getString(1)); // totalvehicle
				obj.setParam3(rs.getString(2)); // running
				obj.setParam4(rs.getString(3)); // alertreceivedtime
				obj.setParam5(rs.getString(4)); // stop
				obj.setParam6(rs.getString(5)); // idle
				obj.setParam7(rs.getString(6)); // nonpolling
				obj.setParam8(rs.getString(7)); 
				obj.setParam9(rs.getString(8)); 
				obj.setParam10(rs.getString(9)); 
				obj.setParam11(rs.getString(10)); 
				obj.setParam12(rs.getString(11)); 
				obj.setParam13(rs.getString(12)); 
				obj.setParam14(rs.getString(13)); 
				obj.setParam15(rs.getString(14)); 
				obj.setParam16(rs.getString(15)); 
				obj.setParam17(rs.getString(16)); 
				obj.setParam18(rs.getString(17));
				obj.setParam19(rs.getString(18));
				obj.setParam20(rs.getString(19));
				obj.setParam21(rs.getString(20));
				obj.setParam22(rs.getString(21));
				obj.setParam23(rs.getString(22));
				obj.setParam24(rs.getString(23));
				obj.setParam25(rs.getString(24));
				obj.setParam26(rs.getString(25));
				obj.setParam27(rs.getString(26));
				obj.setParam28(rs.getString(27));
				obj.setParam29(rs.getString(28));
				obj.setParam30(rs.getString(29));
				obj.setParam31(rs.getString(30));
				obj.setParam32(rs.getString(31));
				obj.setParam33(rs.getString(32));
				obj.setParam34(rs.getString(33));
				obj.setParam35(rs.getString(34));
				obj.setParam36(rs.getString(35));
				obj.setParam37(rs.getString(36));
				obj.setParam38(rs.getString(37));
				obj.setParam39(rs.getString(38));
				obj.setParam40(rs.getString(39));
				obj.setParam41(rs.getString(40));
				obj.setParam42(rs.getString(41));
				obj.setParam43(rs.getString(42));
				obj.setParam44(rs.getString(43));
				obj.setParam45(rs.getString(44));
				obj.setParam46(rs.getString(45));
				obj.setParam47(rs.getString(46));
				obj.setParam48(rs.getString(47));
				obj.setParam49(rs.getString(48));
				obj.setParam50(rs.getString(49));
				obj.setParam51(rs.getString(50));
				detailsdata.add(obj);
				
			}
	
			listData.setListData(detailsdata);			
			apiResponseModel.setEntity(listData);
			System.out.println("list data is ==> " +listData);
			apiResponseModel.setStatus(true);
			apiResponseModel.setStatuscode(200);
			}else {
				apiResponseModel.setEntity("NO DATA FOUND");
				apiResponseModel.setStatus(false);
				apiResponseModel.setStatuscode(201);
			}	
			
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(500);
			apiResponseModel.setException("exception " + e.toString());
			System.err.println(e);
		} finally {
//			for(int i=0;i<spltstr.length;i++)
//			{
//				System.out.println("value of i "+spltstr[i]);
//			}
//			
//			RequestInsertDataDao setException =new RequestInsertDataDao();

//			setException.setParam1("live emergency alert");
//			setException.setParam2("SELECT");
//			setException.setParam3(getdev.toString());
//			setException.setParam4(apiResponseModel.getEntity().toString());
//			setException.setParam5(apiResponseModel.getException());
//			setException.setParam6(spltstr[7]); // ipaddress  
//			setException.setParam7(spltstr[0]); // loginid
////			setException.setParam8(spltstr[4]+"-"+spltstr[5]); // browserdetails
//			setException.setParam8(spltstr[8]); // browserdetails
//			setException.setParam9(spltstr[9]+"-"+spltstr[10]); // osdetails
//			setException.setParam10(spltstr[11]); // Application Type (Web/Mobile)
//			ExceptionMasterUtil.insetException(setException);
			/* Close Connection */
			if(con!=null)
				DBConnection.getCloseConnection(con);

		}
		return apiResponseModel;

	}

	
}
