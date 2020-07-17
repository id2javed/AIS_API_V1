package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.model.VendorDetailsResponse;
import com.app.queryconstants.QueryConstants;

public class VendorManager {
	public List<VendorDetailsResponse> getVendorDetails(String[] spltstr) {
		List<VendorDetailsResponse> vendorList  = new ArrayList<VendorDetailsResponse>(); 
				
				Connection con=DBConnection.getMainConnection();
				PreparedStatement getVendorList;
				try {
					getVendorList = con.prepareStatement(QueryConstants.selectvendorlist);
					getVendorList.setString(1, "vendordetails");
					getVendorList.setString(2, spltstr[4]);	
					getVendorList.setString(3, spltstr[3]);
					getVendorList.setString(4, spltstr[4]);
					getVendorList.setString(5, spltstr[0]);
					getVendorList.setString(5, "");
					ResultSet compRS = getVendorList.executeQuery();
					  while(compRS.next()) {
						  try{
							  VendorDetailsResponse obj=new VendorDetailsResponse();
							  
							  obj.setVendorId(compRS.getString(1));
							  obj.setMfgCode(compRS.getString(2));
							  obj.setVendorName(compRS.getString(3));
							  obj.setVendorShortCode(compRS.getString(4));
							  obj.setVendorRegAddress1(compRS.getString(5));
							  obj.setVendorRegAddress2(compRS.getString(6));
							  obj.setVendorCity(compRS.getString(7));
							  obj.setVendorState(compRS.getString(8));
							  obj.setVendorCin(compRS.getString(9));
							  obj.setVendorGst(compRS.getString(10));
							  obj.setVendorPan(compRS.getString(11));
							  obj.setVendorCityPincode(compRS.getString(12));
							  obj.setVendorOfficialEmailId(compRS.getString(13));
							  obj.setVendorlAndLineNo(compRS.getString(14));
							  obj.setVendorContactPerson(compRS.getString(15));
							  obj.setVendorContpersEmailId(compRS.getString(16));
							  obj.setVendorContactMobile(compRS.getString(17));
							  obj.setVendorStatusFlag(compRS.getString(18));
							  obj.setVendorCreateDat(compRS.getString(19));
							  obj.setVendorUpdateDat(compRS.getString(20));
							  obj.setVendorFlag(compRS.getString(21));
							  
							  
							  
							  
							  
							  
							  vendorList.add(obj);  
						  }catch (Exception e) {
							  System.err.println("iterate error cdealer "+e);
						}
						 
							
						}
					  getVendorList.close();
					  compRS.close();
				} catch (SQLException e) {
					System.err.println(e);
				}finally {
					DBConnection.getCloseConnection(con);
				}
				return  vendorList;
			}

		}


