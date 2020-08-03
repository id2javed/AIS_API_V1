package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.APIResponseModel;
import com.app.connection.DBConnection;
import com.app.main.dao.ListDataResponse;
import com.app.main.dao.LiveEmergrncy;
import com.app.main.dao.RequestInsertDataDao;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.model.LiveEmergrncyResponse;
import com.app.queryconstants.PaginationControl;
import com.app.queryconstants.QueryConstants;

public class AlertManager {
	public LiveEmergrncyResponse liveemergencyalert(String[] spltstr, LiveEmergrncy devdata) {
		LiveEmergrncyResponse det = new LiveEmergrncyResponse();
		List<LiveEmergrncyResponse> data = new ArrayList<LiveEmergrncyResponse>();
		long start = 0, end = 0;
		String seraccbyoffset = null;
		ResultSet rs = null;

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdev;

		try {

			if (devdata.getPageno().equals("NA")) {

				seraccbyoffset = "";
			} else if (devdata.getItemsPerPage().equals("NA")) {

				seraccbyoffset = "";
			} else if (Integer.parseInt(devdata.getPageno()) != 1) {

				start = Integer.parseInt(devdata.getItemsPerPage()) * (Integer.parseInt(devdata.getPageno()) - 1);
				end = start + Integer.parseInt(devdata.getItemsPerPage());
				seraccbyoffset = " offset " + start + " limit " + devdata.getItemsPerPage() + "";
				start++;
			} else {

				start = 0;
				end = start + Integer.parseInt(devdata.getItemsPerPage());
				seraccbyoffset = " offset " + start + " limit " + devdata.getItemsPerPage() + "  ";
				start++;
			}

		} catch (Exception e) {
			System.err.println("e1 ----  " + e);
		}
		try {
			getdev = con.prepareStatement(QueryConstants.alertprocedure);

			getdev.setString(1, "liveemergencyalert");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// ownersid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getSearchby());// search by
			getdev.setString(7, seraccbyoffset);
			getdev.setString(8, "");
			getdev.setString(9, "");
			getdev.setString(10, "");
			getdev.setString(11, devdata.getVehicleid());
			System.out.println("query   " + getdev.toString());

			rs = getdev.executeQuery();

//		 emr.imeino, alerttime, alertreceivedtime, emr.latitude, emr.longitude, emr.speed, emr.distance,
//		 emr.message_type, emr.packet_type, 
//		 emr.gps_status, md.deviceiccidno,  vehicleregno, vdr.vendorname, customername, customermobileno

			while (rs.next()) {

				LiveEmergrncyResponse obj = new LiveEmergrncyResponse();
				obj.setImeino(rs.getString(1));
				obj.setAlerttime(rs.getString(2));
				obj.setAlertreceivedtime(rs.getString(3));
				obj.setLatitude(rs.getString(4));
				obj.setLongitude(rs.getString(5));
				obj.setSpeed(rs.getString(6));
				obj.setDistance(rs.getString(7));
				obj.setMessage_type(rs.getString(8));
				obj.setPacket_type(rs.getString(9));
				obj.setGps_status(rs.getString(10));
				obj.setDeviceiccidno(rs.getString(11));
				obj.setVehicleregno(rs.getString(12));
				obj.setVendorname(rs.getString(13));
				obj.setCustomername(rs.getString(14));
				obj.setCustomermobileno(rs.getString(15));
				System.out.println("obj : " + obj.toString());
				data.add(obj);
			}

			int count = 0;

			try {

//			select * from masters.alertproceduer('liveemergencyalert', 'companyid', 'roleid', 'ownersid', 'loginid', 'searchby', 
//					'offset', 'Count', '', '', 'vehicleid/All', '', '', '', '', '', '', '', '', '', '');

				System.out.println("check 3  ");
				PreparedStatement getdevcount = null;
				getdevcount = con.prepareStatement(QueryConstants.alertprocedure);
				getdevcount.setString(1, "liveemergencyalert");
				getdevcount.setString(2, spltstr[4]);// company id send owners id
				getdevcount.setString(3, spltstr[3]);// roleid
				getdevcount.setString(4, spltstr[4]);// ownersid
				getdevcount.setString(5, spltstr[0]);// loginid
				getdevcount.setString(6, devdata.getSearchby());// search by
				getdevcount.setString(7, seraccbyoffset);
				getdevcount.setString(8, "Count");
				getdevcount.setString(9, "");
				getdevcount.setString(10, "");
				getdevcount.setString(11, devdata.getVehicleid());

				System.out.println("Live Emergency Query Count ==>> " + getdevcount.toString());

				rs = getdevcount.executeQuery();
				if (rs.next()) {

					count = Integer.parseInt(rs.getString(1));

				}

			} catch (Exception e) {
				System.out.println("Excewption ==>> " + e.toString());
			}
			det = new LiveEmergrncyResponse();
			det.setResponse("Successfully Saved!");
			det.setData(data);
			det.setTotal(count);

		} catch (Exception e) {
			det.setResponse("Exception");

			System.out.println("Emergency Query Count ==>> " + e.toString());
		} finally {
			DBConnection.getCloseConnection(con);
		}
		return det;
	}
	
	/**
	 * Developer : Prem Gaigole Date : 2020-07-09 Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/
	APIResponseModel apiResponseModel = new APIResponseModel();
	
	public APIResponseModel insertalertconfig(String[] splitstr, RequestInsertDataDao requestInsertDataDao ) {
		 apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;

		
		try {
			con = DBConnection.getMainConnection();
			try {
//				select * from masters.inserteventprocedure('insertalertconfig', 'idx',  'loginid', 'mobileno', 'name', 'emailid', 
//						'roleid',  'ownersid', 'vehicleid', 'alertid', 'alert value', 'scheduled','', '', '',  '', '','', '', '', '');
			
				preparedStatement = con.prepareStatement(QueryConstants.insert_alert_confi);
				
				preparedStatement.setString(1, "insertalertconfig");
				preparedStatement.setString(2, requestInsertDataDao.getParam1());//idx			
				preparedStatement.setString(3, splitstr[0]);//loginid			
				preparedStatement.setString(4, requestInsertDataDao.getParam3()); //mobileno				
				preparedStatement.setString(5, requestInsertDataDao.getParam4());//name		
				preparedStatement.setString(6, requestInsertDataDao.getParam5());//emailid	
				preparedStatement.setString(7, splitstr[3]);//roleid				
				preparedStatement.setString(8, splitstr[4]);//ownersid
				preparedStatement.setString(9, requestInsertDataDao.getParam8());//vehicleid
				preparedStatement.setString(10, requestInsertDataDao.getParam9());//alertid
				preparedStatement.setString(11, requestInsertDataDao.getParam10());//alert value
				preparedStatement.setString(12, requestInsertDataDao.getParam11());//scheduled

				
			} catch (Exception e) {
				System.out.println("exception in this try == "+e.toString());
			}

	
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("query==> " +preparedStatement.toString());

			if (resultSet.next()) {
				try {
					splitstr=resultSet.getString(1).split("#");
					apiResponseModel.setEntity(splitstr[1]);
					apiResponseModel.setStatus(true);
					apiResponseModel.setStatuscode(Integer.parseInt(splitstr[0]));
				} catch (Exception e) {
					System.out.println("try " +e.toString());
					apiResponseModel.setEntity("INTERNAL SERVER ERROR");
					apiResponseModel.setStatus(false);
					apiResponseModel.setStatuscode(412);
				}
				
						
			} else {
				System.out.println("if "+resultSet.toString());
				apiResponseModel.setEntity("INTERNAL SERVER ERROR");
				apiResponseModel.setStatus(false);
				apiResponseModel.setStatuscode(412);
			}
		} catch (Exception e) {
			System.out.println(e);
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(500);
			apiResponseModel.setException("exception " +e.toString());
		}
		finally {
			
//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			
//			RequestInsertDataDao setException = new RequestInsertDataDao();
//			
////				[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("insert alert config");
//			
//			setException.setParam2("INSERT");
//			setException.setParam3(preparedStatement.toString());
//			setException.setParam4(apiResponseModel.getEntity().toString());
//			setException.setParam5(apiResponseModel.getException());
//			setException.setParam6(splitstr[7]); // ipaddress
//			setException.setParam7(splitstr[0]); // loginid			
//			setException.setParam8(splitstr[8]); // browserdetails			
//			setException.setParam9(splitstr[9]); // osdetails			
//			setException.setParam10(splitstr[10]); // Application Type (Web/Mobile)			
//			wrap.insetException(setException);
//			
//			
			/* Close Connection */
			if (con != null)
				DBConnection.getCloseConnection(con);

		}

		
		return apiResponseModel;
		
	}	

	
		
	/**
	 * Developer : Prem Gaigole Date : 2020-07-09 Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel alertconfiguredetails(String[] spltstr, RequestSelectDataDao devdata) {
		RequestSelectDataDao obj = null;
		List<RequestSelectDataDao> detailsdata = new ArrayList<RequestSelectDataDao>();
		ListDataResponse listData = new ListDataResponse();
		APIResponseModel apiResponseModel = new APIResponseModel();
		ResultSet rscount = null;

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdev = null;
		String searchByOffset = PaginationControl.getPaginationControl(devdata.getParam2(), devdata.getParam3()); // param2
																								// ItemPerPage
		String[] searchByOffsetSplit = searchByOffset.split("&&");
		try {

//		select * from masters.selecteventmanagement('alertconfiguredetails', 'companyid', roleid', 'ownersid', 'loginid', 'search', 
//				'offset', 'count', 'searchtype', '',  '', '', '', '', '', '');

			getdev = con.prepareStatement(QueryConstants.alert_configure_details);
			getdev.setString(1, "alertconfiguredetails");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// search by
			getdev.setString(7, searchByOffsetSplit[0]);// offset
			getdev.setString(8, "Count");
			getdev.setString(9, devdata.getParam4());//searchtype
			System.out.println("Query == > " + getdev.toString());
			rscount = getdev.executeQuery();
			

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);

				getdev = con.prepareStatement(QueryConstants.alert_configure_details);
				getdev.setString(1, "alertconfiguredetails");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam1());// search by
				getdev.setString(7, searchByOffsetSplit[0]);// offset
				getdev.setString(8, "Count");
				getdev.setString(9, devdata.getParam4());//searchtype
			
				System.out.println("count " + getdev);
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					acf.idx, acf.loginid, acf.mobileno, acf.name, acf.emailid, acf.roleid, acf.ownersid, acf.vehicleid, 
//					acf.alertid, acf.alertvalue, acf.scheduled, acf.createdat, rm.rolename, ownersname,  vehicleno,
//					acf.vendorid, vdr2.vendorname
				
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // acf.idx
					obj.setParam3(rs.getString(2)); // acf.loginid
					obj.setParam4(rs.getString(3)); // acf.mobileno
					obj.setParam5(rs.getString(4)); // acf.name
					obj.setParam6(rs.getString(5)); // acf.emailid
					obj.setParam7(rs.getString(6)); // acf.roleid
					obj.setParam8(rs.getString(7)); // acf.ownersid
					obj.setParam9(rs.getString(8)); // acf.vehicleid
					obj.setParam10(rs.getString(9)); // acf.alertid
					obj.setParam11(rs.getString(10)); // acf.alertvalue
					obj.setParam12(rs.getString(11)); // acf.scheduled
					obj.setParam13(rs.getString(12)); // acf.createdat
					obj.setParam14(rs.getString(13)); // rm.rolename
					obj.setParam15(rs.getString(14)); // ownersname
					obj.setParam16(rs.getString(15)); // vehicleno
					obj.setParam17(rs.getString(16));//acf.vendorid
					obj.setParam18(rs.getString(17));//vdr2.vendorname
					obj.setParam19(rs.getString(18));
					obj.setParam20(rs.getString(19));
					obj.setParam21(rs.getString(20));
					obj.setParam22(rs.getString(21));
					obj.setParam23(rs.getString(22));
					obj.setParam24(rs.getString(23));
					obj.setParam25(rs.getString(24));
					obj.setParam26(rs.getString(25));
					detailsdata.add(obj);

				}

				listData.setListData(detailsdata);
				apiResponseModel.setEntity(listData);
				System.out.println("list data is ==> " + listData);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(200);
			} else {
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

//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			RequestInsertDataDao setException = new RequestInsertDataDao();
////				[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("alert configure details");
//			setException.setParam2("SELECT");
//			setException.setParam3(getdev.toString());
//			setException.setParam4(apiResponseModel.getEntity().toString());
//			setException.setParam5(apiResponseModel.getException());
//			setException.setParam6(spltstr[7]); // ipaddress
//			setException.setParam7(spltstr[0]); // loginid
//			setException.setParam8(spltstr[8]); // browserdetails
//			setException.setParam9(spltstr[9]); // osdetails
//			setException.setParam10(spltstr[10]); // Application Type (Web/Mobile)
//			wrap.insetException(setException);
			/* Close Connection */
			if (con != null)
				DBConnection.getCloseConnection(con);

		}
		return apiResponseModel;

	}
	
	/**
	 * Developer : Prem Gaigole Date : 2020-07-26 
	 * Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel alerttypedetails(String[] spltstr, RequestSelectDataDao devdata) {
		RequestSelectDataDao obj = null;
		List<RequestSelectDataDao> detailsdata = new ArrayList<RequestSelectDataDao>();
		ListDataResponse listData = new ListDataResponse();
		APIResponseModel apiResponseModel = new APIResponseModel();
		ResultSet rscount = null;

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdev = null;
		String searchByOffset = PaginationControl.getPaginationControl(devdata.getParam2(), devdata.getParam3()); // param2
																								// ItemPerPage
		String[] searchByOffsetSplit = searchByOffset.split("&&");

		try {
//			select * from masters.selecteventmanagement('alerttypedetails', 'companyid', roleid', 'ownersid', 'loginid',
//			'search', 'offset', 'count', '', '',  '', '', '', '', '', '');


			getdev = con.prepareStatement(QueryConstants.reportdetails);
			getdev.setString(1, "alerttypedetails");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// search by
			getdev.setString(7, searchByOffsetSplit[0]);// offset
			getdev.setString(8, "Count");
			
			rscount = getdev.executeQuery();
			System.out.println("Query == > " + getdev.toString());

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);

				getdev = con.prepareStatement(QueryConstants.menu_list);
				getdev.setString(1, "getmainmenu");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid	
				getdev.setString(6, devdata.getParam1());// search by
				getdev.setString(7, searchByOffsetSplit[0]);// offset
				getdev.setString(8, "");
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					alertid, alertname

				
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // alertid
					obj.setParam3(rs.getString(2)); // alertname
					obj.setParam4(rs.getString(3)); 
					obj.setParam5(rs.getString(4)); 
					obj.setParam6(rs.getString(5)); 
					obj.setParam7(rs.getString(6)); 
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
					detailsdata.add(obj);

				}

				listData.setListData(detailsdata);
				apiResponseModel.setEntity(listData);
				System.out.println("list data is ==> " + listData);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(200);
			} else {
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

//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			RequestInsertDataDao setException = new RequestInsertDataDao();
////				[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("alert type details");
//			setException.setParam2("SELECT");
//			setException.setParam3(getdev.toString());
//			setException.setParam4(apiResponseModel.getEntity().toString());
//			setException.setParam5(apiResponseModel.getException());
//			setException.setParam6(spltstr[7]); // ipaddress
//			setException.setParam7(spltstr[0]); // loginid
//			setException.setParam8(spltstr[8]); // browserdetails
//			setException.setParam9(spltstr[9]); // osdetails
//			setException.setParam10(spltstr[10]); // Application Type (Web/Mobile)
//			wrap.insetException(setException);
			/* Close Connection */
			if (con != null)
				DBConnection.getCloseConnection(con);

		}
		return apiResponseModel;

	}
	
}
