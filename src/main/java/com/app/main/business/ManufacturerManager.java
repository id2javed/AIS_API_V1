package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.dao.ManufacturerData;
import com.app.main.model.ManufacturerResponse;
import com.app.queryconstants.QueryConstants;

public class ManufacturerManager {

	public String setManufac(ManufacturerData manuData, String[] spltstr, String procType) {
		String data = null;

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getmanu;
		try {
			getmanu = con.prepareStatement(QueryConstants.manufacturerinsertquery);

			getmanu.setString(1, procType);
			getmanu.setString(2, spltstr[4]);
			getmanu.setString(3, manuData.getMancode());
			getmanu.setString(4, manuData.getCompname());
			getmanu.setString(5, manuData.getMancode());
			getmanu.setString(6, manuData.getRegadd());
			getmanu.setString(7, "0");
			getmanu.setString(8, manuData.getCity());
			getmanu.setString(9, manuData.getState());
			getmanu.setString(10, manuData.getCin());
			getmanu.setString(11, manuData.getGst());
			getmanu.setString(12, "0");
			getmanu.setString(13, "0");
			getmanu.setString(14, manuData.getEmail());
			getmanu.setString(15, "0");
			getmanu.setString(16, "0");
			getmanu.setString(17, "0");
			getmanu.setString(18, manuData.getOffno());
			getmanu.setString(19, "0");
			getmanu.setString(20, manuData.getLoginName());
			getmanu.setString(21, manuData.getConPass());
			getmanu.setString(22, "11");
			getmanu.setString(23, spltstr[0]);
			getmanu.setString(24, "0");
			getmanu.setString(25, "0");
			getmanu.setString(26, "0");
			getmanu.setString(27, "0");
			getmanu.setString(28, "0");
			getmanu.setString(29, "0");
			getmanu.setString(30, "0");
			getmanu.setString(31, "0");
			getmanu.setString(32, "0");
			getmanu.setString(33, "0");
			getmanu.setString(34, manuData.getCompanyLogo());
			getmanu.setString(35, manuData.getLogoColor());
			getmanu.setString(36, "");

			System.out.println("Manufacture Query:- " + getmanu.toString());
			ResultSet rs = getmanu.executeQuery();

			while (rs.next()) {
				System.err.println(rs.getString(1));
				data = rs.getString(1);
			}
		} catch (Exception e) {
			System.err.println("Manufacturer Exception ==>> " + e);
			data = "Error";

		} finally {
			DBConnection.getCloseConnection(con);
		}
		return data;
	}

	// This method is created by : Javed Shaikh
	public String updateManufacturer() {
		String mData = null;
		Connection mConnection = null;
		PreparedStatement mPreparedStatement = null;
		try {
			mConnection = DBConnection.getMainConnection();
			mPreparedStatement = mConnection.prepareStatement(QueryConstants.manufacturerDetailUpdate);
			System.out.println("Manufacture Query:- " + mPreparedStatement.toString());
			mPreparedStatement.close();
			mData = "Successfully Saved.";
		} catch (Exception e) {
			System.err.println("Manufacturer Exception ==>> " + e);
		} finally {
			DBConnection.getCloseConnection(mConnection);
		}
		return mData;
	}

	// This method is created by : Javed Shaikh
	public String updateManufacturer(ManufacturerData paramsManufacturerData, String[] spltstr, String paramsProcedureType) {
		String mData = null;
		Connection mConnection = null;
		PreparedStatement mPreparedStatement = null;
		try {
			String str = paramsManufacturerData.getCompanyLogo();
			String imageName = str.substring(str.lastIndexOf("\\") + 1);
			
			mConnection = DBConnection.getMainConnection();
			mPreparedStatement = mConnection.prepareStatement(QueryConstants.manufacturerDetailUpdate);

			mPreparedStatement.setString(1, paramsProcedureType);					// 'updatecompany'
			mPreparedStatement.setString(2, ""+paramsManufacturerData.getCompanyId()); // 'vendorid' == OwnersId
			mPreparedStatement.setString(3, paramsManufacturerData.getMancode());	// 'mfgcode' 
			mPreparedStatement.setString(4, paramsManufacturerData.getCompname());	// 'vendorname'
			mPreparedStatement.setString(5, paramsManufacturerData.getMancode());	// 'vendorshortcode' == mfgcode
			mPreparedStatement.setString(6, paramsManufacturerData.getRegadd());	// 'vendorregaddress1' 
			mPreparedStatement.setString(7, "0");									// 'vendorregaddress2'  
			mPreparedStatement.setString(8, paramsManufacturerData.getCity());		// 'vendorcity'
			mPreparedStatement.setString(9, paramsManufacturerData.getState());		// 'vendorstate'
			mPreparedStatement.setString(10, paramsManufacturerData.getCin());		// 'vendorcin'
			mPreparedStatement.setString(11, paramsManufacturerData.getGst());		// 'vendorgst'
			mPreparedStatement.setString(12, "0");									// 'vendorpan' 
			mPreparedStatement.setString(13, "0");									// 'vendorcitypincode' 
			mPreparedStatement.setString(14, paramsManufacturerData.getEmail());	// 'vendorofficialemailid'
			mPreparedStatement.setString(15, "0");									// 'vendorlandlineno'
			mPreparedStatement.setString(16, "0");									// 'vendorcontactperson'
			mPreparedStatement.setString(17, "0");									// 'vendorcontpersemailid'
			mPreparedStatement.setString(18, paramsManufacturerData.getOffno());	// 'vendorcontactmobile'
			mPreparedStatement.setString(19, "0");									// 'vendorstatusflag'
			mPreparedStatement.setString(20, "");									// ''
			mPreparedStatement.setString(21, "");									// ''
			mPreparedStatement.setString(22, "");									// ''
			mPreparedStatement.setString(23, spltstr[0]);							// 'creatorloginid'
			mPreparedStatement.setString(24, "0");									// 'vendorbankaccountno1'
			mPreparedStatement.setString(25, "0");									// 'vendorbankname1'
			mPreparedStatement.setString(26, "0");									// 'vendorbankifsc1'
			mPreparedStatement.setString(27, "0");									// 'vendorbankaddress1'
			mPreparedStatement.setString(28, "0");									// 'vendorbankcityname1'
			mPreparedStatement.setString(29, "0");									// 'vendorbankaccountno2'
			mPreparedStatement.setString(30, "0");									// 'vendorbankname2'
			mPreparedStatement.setString(31, "0");									// 'vendorbankifsc2'
			mPreparedStatement.setString(32, "0");									// 'vendorbankaddress2'
			mPreparedStatement.setString(33, "0");									// 'vendorbankcityname2' 
			mPreparedStatement.setString(34, imageName);							// 'logofilepath'
			mPreparedStatement.setString(35, paramsManufacturerData.getLogoColor());// 'themecolour'
			mPreparedStatement.setString(36, "");									// 'logofile'

			ResultSet mResultSet = mPreparedStatement.executeQuery();
			System.out.println("Manufacture Query:- " + mPreparedStatement.toString());

			while (mResultSet.next()) {
				// System.err.println(mResultSet.getString(1));
				mData = mResultSet.getString(1);
			}
		} catch (Exception e) {
			System.err.println("Manufacturer Exception ==>> " + e);
			mData = "Error";
		} finally {
			DBConnection.getCloseConnection(mConnection);
		}
		return mData;
	}
	
	// This method is created by : Javed Shaikh
	public String deleteManufacturer(ManufacturerData paramsManufacturerData, String[] spltstr, String paramsProcedureType) {
		String mData = null;
		Connection mConnection = null;
		PreparedStatement mPreparedStatement = null;
		try {
			mConnection = DBConnection.getMainConnection();
			mPreparedStatement = mConnection.prepareStatement(QueryConstants.manufacturerDetailDelete);

			mPreparedStatement.setString(1, paramsProcedureType); 	// "deletecompany"
			mPreparedStatement.setString(2, ""+ paramsManufacturerData.getCompanyId()); // companid
			mPreparedStatement.setString(3, spltstr[0]);	// loginid 
			mPreparedStatement.setString(4, paramsManufacturerData.getRemarks()); 	// remarks

			System.out.println("Manufacture Query:- " + mPreparedStatement.toString());
			ResultSet mResultSet = mPreparedStatement.executeQuery();

			while (mResultSet.next()) {
				System.out.println(mResultSet.getString(1));
				mData = mResultSet.getString(1);
			}			
		} catch (Exception e) {
			System.err.println("Manufacturer Exception ==>> " + e);
		} finally {
			DBConnection.getCloseConnection(mConnection);
		}
		return mData;
	}
	

	public ManufacturerResponse getManufac(ManufacturerData manuData, String[] spltstr) {
		List<ManufacturerResponse> data = new ArrayList<ManufacturerResponse>();
		ManufacturerResponse det = null;

		long start = 0, end = 0;
		String seraccbyoffset = "";
		try {
			if (manuData.getPageno().equals("NA")) {
				seraccbyoffset = "";
			} else if (manuData.getItemsPerPage().equals("NA")) {
				seraccbyoffset = "";
			} else

			if (Integer.parseInt(manuData.getPageno()) != 1) {
				start = Integer.parseInt(manuData.getItemsPerPage()) * (Integer.parseInt(manuData.getPageno()) - 1);
				end = start + Integer.parseInt(manuData.getItemsPerPage());
				seraccbyoffset = " offset " + start + " limit " + manuData.getItemsPerPage() + "";
				start++;
			} else {
				start = 0;
				end = start + Integer.parseInt(manuData.getItemsPerPage());
				seraccbyoffset = " offset " + start + " limit " + manuData.getItemsPerPage() + "  ";
				start++;
			}

		} catch (Exception e) {
			System.err.println("e ----  " + e);
		}

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getmanu;
		try {
			getmanu = con.prepareStatement(QueryConstants.manufacturerdetailsquery);
			getmanu.setString(1, "vendordetails");
			getmanu.setString(2, spltstr[4]);
			getmanu.setString(3, spltstr[3]);
			getmanu.setString(4, spltstr[4]);
			getmanu.setString(5, spltstr[0]);
			getmanu.setString(6, manuData.getSearchby());
			getmanu.setString(7, seraccbyoffset);
			getmanu.setString(8, "");

			System.out.println("Manufacturing Query ==>>" + getmanu.toString());

			ResultSet rs = getmanu.executeQuery();

			Integer i = null;
			if (manuData.getPageno().equals("NA")) {
				i = 1;
			} else {
				i = (int) (long) start;
				System.out.println(start);
				manuData.setItemsPerPage("NA");
			}

			while (rs.next()) {
				det = new ManufacturerResponse();
				det.setSrno(i);
				i++;

				det.setCompanyId(rs.getInt(1)); // added by Javed 29-06-2020

				det.setMancode(rs.getString(2));
				det.setCompname(rs.getString(3));
				det.setRegadd(rs.getString(5));

				det.setCity(rs.getString(7));
				det.setState(rs.getString(8));
				det.setCin(rs.getString(9));
				det.setGst(rs.getString(10));

				det.setEmail(rs.getString(13));
				det.setOffno(rs.getString(17));

				det.setLogoFile(rs.getString(22));
				det.setThemeColour(rs.getString(23));
				det.setLoginName(rs.getString(24));
				det.setConPass(rs.getString(25));

				data.add(det);

			}

			int count = 0;
			ResultSet rs4 = null;
			try {
				PreparedStatement getmenucount;
				getmenucount = con.prepareStatement(QueryConstants.manufacturerdetailsquery);
				getmenucount.setString(1, "vendordetails");
				getmenucount.setString(2, spltstr[4]);
				getmenucount.setString(3, spltstr[3]);
				getmenucount.setString(4, spltstr[4]);
				getmenucount.setString(5, spltstr[0]);
				getmenucount.setString(6, manuData.getSearchby());
				getmenucount.setString(7, seraccbyoffset);
				getmenucount.setString(8, "Count");

				System.out.println("Manufacturer count Query ==>> " + getmenucount.toString());
				rs4 = getmenucount.executeQuery();

				while (rs4.next()) {

					count = rs4.getInt(1);
				}

			} catch (Exception e) {
				System.out.println("Exception ==>> " + e);
			}
			det = new ManufacturerResponse();
			det.setData(data);
			det.setTotal(count);

			det.setResponse("Successfully Saved!");

		} catch (Exception e) {
			System.err.println("Manufacturer Exception ==>> " + e);

			det.setResponse("Error");

		} finally {
			DBConnection.getCloseConnection(con);
		}

		return det;
	}

}
