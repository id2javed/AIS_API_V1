package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.dao.DealerData;
import com.app.main.model.DealerDetailsResponse;
import com.app.main.model.DealerListResponse;
import com.app.main.model.DealerResponse;
import com.app.queryconstants.QueryConstants;

public class DealerManager {

	public String insertdealer(DealerData dealerData, String[] spltstr) {
		Connection con=DBConnection.getMainConnection();
		String dealerResponse=null;
		PreparedStatement insertDealer;
		try {
			insertDealer = con.prepareStatement(QueryConstants.inserdealer);
			System.out.println("dealerData.getdealercityname()  "+dealerData.getDealercityname());
			insertDealer.setString(1, "insertdealer");
			insertDealer.setString(2, "");
			insertDealer.setString(3, dealerData.getDealername());
			insertDealer.setString(4, dealerData.getDealergst());
			insertDealer.setString(5, dealerData.getDealerregaddr1());
			insertDealer.setString(6, dealerData.getDealerregaddr2());
			insertDealer.setString(7, dealerData.getDealercityname());
			insertDealer.setString(8, dealerData.getDealerstatename());
			insertDealer.setString(9, dealerData.getDealercontactpers());
			insertDealer.setString(10, dealerData.getDealercontactemailid());
			insertDealer.setString(11, dealerData.getDealercontactmobile1());
			insertDealer.setString(12, dealerData.getDealercontactmobile2());
			insertDealer.setString(13, "");
			insertDealer.setString(14, dealerData.getDistributorid());
			insertDealer.setString(15, dealerData.getLoginname());
			insertDealer.setString(16, dealerData.getLoginpasswd());
			insertDealer.setString(17, "13");
			insertDealer.setString(18, spltstr[0]);
			//insertDealer.setString(18, dealerData.getDistributorid());
			insertDealer.setString(19, dealerData.getDealerbankaccountno1());
			insertDealer.setString(20, dealerData.getDealerbankname1());
			insertDealer.setString(21, dealerData.getDealerbankifsc1());
			insertDealer.setString(22, dealerData.getDealerbankaddress1());
			insertDealer.setString(23, dealerData.getDealerbankcityname1());
			insertDealer.setString(24, dealerData.getDealerbankaccountno2());
			insertDealer.setString(25, dealerData.getDealerbankname2());
			insertDealer.setString(26, dealerData.getDealerbankifsc2());
			insertDealer.setString(27, dealerData.getDealerbankaddress2());
			insertDealer.setString(28, dealerData.getDealerbankcityname2());
			insertDealer.setString(29, dealerData.getDistributorid());
			System.out.println(insertDealer);
			ResultSet dealerRS = insertDealer.executeQuery();
			  while(dealerRS.next()) {
				  DealerResponse obj=new DealerResponse();
				  obj.setResponse(dealerRS.getString(1));
				  dealerResponse=dealerRS.getString(1);		
				  System.out.println("Dealer insert Response frm DB : " + dealerResponse);
				}
			  insertDealer.close(); 
			  dealerRS.close();
			  
		} catch (SQLException e) {
			 dealerResponse="Failed to Save Details";
			e.printStackTrace();
		}finally {
			DBConnection.getCloseConnection(con);
		}
		
	return dealerResponse;
}

	public List<DealerDetailsResponse> getDealerDetails(String[] spltstr) {
List<DealerDetailsResponse> dealerList  = new ArrayList<DealerDetailsResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getDealerList;
		try {
			getDealerList = con.prepareStatement(QueryConstants.selectdealerdetails);
			
			getDealerList.setString(1, "dealerdetails");
			getDealerList.setString(2, spltstr[4]);	
			getDealerList.setString(3, spltstr[3]);
			getDealerList.setString(4, spltstr[4]);
			getDealerList.setString(5, spltstr[0]);
			ResultSet compRS = getDealerList.executeQuery();
			  while(compRS.next()) {
				  try{
					  DealerDetailsResponse obj=new DealerDetailsResponse();
					  obj.setDealerId(compRS.getString(1));
					  obj.setDealerName(compRS.getString(2));
					  obj.setDealerGst(compRS.getString(3));
					  obj.setDealerregAddr1(compRS.getString(4));
					  obj.setDealerregAddr2(compRS.getString(5));
					  obj.setDealerCityname(compRS.getString(6));
					  obj.setDealerStatename(compRS.getString(7));
					  obj.setDealerContactpers(compRS.getString(8));
					  obj.setDealercontactemailid(compRS.getString(9));
					  obj.setDealercontactmobile1(compRS.getString(10));
					  obj.setDealercontactmobile2(compRS.getString(11));
					  obj.setDealervendorid(compRS.getString(12));
					  obj.setDealerdistributorid(compRS.getString(13));
					  obj.setDealercreatedat(compRS.getString(14));
					  obj.setDealerupdatedat(compRS.getString(15));
					  obj.setDealerflag(compRS.getString(16));
					  dealerList.add(obj);  
				  }catch (Exception e) {
					  System.err.println("iterate error cdealer "+e);
				}
				 
					
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

}
