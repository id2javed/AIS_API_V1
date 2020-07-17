package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.app.connection.DBConnection;
import com.app.main.dao.CustomerData;
import com.app.main.model.CustomerDetailsResponse;
import com.app.main.model.CustomerResponse;
import com.app.main.model.DeviceDetailsResponse;
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
	
}
