package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.app.connection.DBConnection;
import com.app.main.dao.DistributorData;
import com.app.main.model.DealerDetailsResponse;
import com.app.main.model.DeviceDetailsResponse;
import com.app.main.model.DistributorListResponse;
import com.app.main.model.DistributorResponse;
import com.app.queryconstants.QueryConstants;

public class DistributorMnager {

	public String distributor(@RequestBody DistributorData distriData, String[] spltstr) {

		DistributorResponse data = new DistributorResponse();
		String response = null;

		/*
		 * List<DistributorResponse> data1 = new ArrayList<DistributorResponse>(); long
		 * start = 0, end = 0; String seraccbyoffset=""; try {
		 * if(distriData.getPageno().equals("NA")) { seraccbyoffset=""; }else
		 * if(distriData.getItemsPerPage().equals("NA")) { seraccbyoffset=""; }else
		 * 
		 * if (Integer.parseInt(distriData.getPageno()) != 1) { start =
		 * Integer.parseInt(distriData.getItemsPerPage()) *
		 * (Integer.parseInt(distriData.getPageno()) - 1); end = start +
		 * Integer.parseInt(distriData.getItemsPerPage());
		 * seraccbyoffset=" offset "+start+" limit "+distriData.getItemsPerPage()+"";
		 * start++; } else { start = 0; end = start +
		 * Integer.parseInt(distriData.getItemsPerPage());
		 * seraccbyoffset=" offset "+start+" limit "+distriData.getItemsPerPage()+"  ";
		 * start++; }
		 * 
		 * }catch (Exception e) { System.err.println("e ----  "+e); }
		 */
		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdistri;
		try {
			getdistri = con.prepareStatement(QueryConstants.distributorinsertquery);
			System.out.println("OWNERS ID==>> " + spltstr[0]);
			System.out.println("OWNERS ID==>> " + spltstr[1]);
			System.out.println("OWNERS ID==>> " + spltstr[2]);
			System.out.println("OWNERS ID==>> " + spltstr[3]);
			System.out.println("OWNERS ID==>> " + spltstr[4]);
			getdistri.setString(1, "insertdistributor");
			getdistri.setString(2, "");
			getdistri.setString(3, distriData.getDistributorname());
			getdistri.setString(4, distriData.getDistributorgst());
			getdistri.setString(5, distriData.getDistributorregaddr1());
			getdistri.setString(6, distriData.getDistributorregaddr2());
			getdistri.setString(7, distriData.getDistributorcityname());
			getdistri.setString(8, distriData.getDistributorstatename());
			getdistri.setString(9, distriData.getDistributorcontactpers());
			getdistri.setString(10, distriData.getDistributorcontactemailid());
			getdistri.setString(11, distriData.getDistributorcontactmobile1());
			getdistri.setString(12, distriData.getDistributorcontactmobile2());
			getdistri.setString(13, ""); // superdistributorid
			getdistri.setString(14, spltstr[4]);
			getdistri.setString(15, distriData.getLoginname());
			getdistri.setString(16, distriData.getLoginpasswd());
			getdistri.setString(17, "12");
			getdistri.setString(18, spltstr[0]);
			getdistri.setString(19, distriData.getDistributorbankaccountno1());
			getdistri.setString(20, distriData.getDistributorbankname1());
			getdistri.setString(21, distriData.getDistributorbankifsc1());
			getdistri.setString(22, distriData.getDistributorbankaddress1());
			getdistri.setString(23, distriData.getDistributorbankcityname1());
			getdistri.setString(24, distriData.getDistributorbankaccountno2());
			getdistri.setString(25, distriData.getDistributorbankname2());
			getdistri.setString(26, distriData.getDistributorbankifsc2());
			getdistri.setString(27, distriData.getDistributorbankaddress2());
			getdistri.setString(28, distriData.getDistributorbankcityname2());
			getdistri.setString(29, "");
			System.out.println("distributor " + getdistri.toString());
			ResultSet rs = getdistri.executeQuery();

			while (rs.next()) {
				System.err.println(rs.getString(1));
				response = rs.getString(1);
				data.setResponse(rs.getString(1));
			}
		} catch (Exception e) {
			System.err.println(e);
			response = "Error";
			data.setResponse("Error");
		} finally {
			DBConnection.getCloseConnection(con);
		}

		return response;
	}

	public List<DistributorData> getdistribuotdetails(String[] spltstr, DistributorData distriData) {
		/*
		 * Developer : Pallavi Dhage Created Date : 2019-07-30 Updated Date :
		 * Description : Method Implementation For Distributor details
		 */
		// System.out.println("In manager...");
		List<DistributorData> distributorList = new ArrayList<DistributorData>();
		Connection con = DBConnection.getMainConnection();
		PreparedStatement getDistList;
		try {
			getDistList = con.prepareStatement(QueryConstants.selectdistributordetails);

			getDistList.setString(1, "distributordetails");
			getDistList.setString(2, spltstr[4]);
			getDistList.setString(3, spltstr[3]);
			getDistList.setString(4, spltstr[4]);
			getDistList.setString(5, spltstr[0]);
			System.out.println("Query:- " + getDistList.toString());
			ResultSet distRS = getDistList.executeQuery();
			while (distRS.next()) {
				try {
					DistributorData obj = new DistributorData();
					obj.setDistributorid(distRS.getString(1));
					obj.setDistributorname(distRS.getString(2));
					obj.setDistributorgst(distRS.getString(3));
					obj.setDistributorregaddr1(distRS.getString(4));
					obj.setDistributorregaddr2(distRS.getString(5));
					obj.setDistributorcityname(distRS.getString(6));
					obj.setDistributorstatename(distRS.getString(7));
					obj.setDistributorcontactpers(distRS.getString(8));
					obj.setDistributorcontactemailid(distRS.getString(9));
					obj.setDistributorcontactmobile1(distRS.getString(10));
					obj.setDistributorcontactmobile2(distRS.getString(11));

					distributorList.add(obj);
				} catch (Exception e) {
					System.err.println("iterate error distributordetails " + e);
				}

			}
			getDistList.close();
			distRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			DBConnection.getCloseConnection(con);
		}
		return distributorList;
	}

}
