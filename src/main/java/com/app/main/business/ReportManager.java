package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.APIResponseModel;
import com.app.connection.DBConnection;
// import com.app.connection.ExceptionMasterUtil;
import com.app.main.dao.ListDataResponse;
// import com.app.main.dao.RequestInsertDataDao;
import com.app.main.dao.RequestSelectDataDao;
import com.app.queryconstants.PaginationControl;
import com.app.queryconstants.QueryConstants;

public class ReportManager {
	/**
	 * Developer : Prem Gaigole Date : 2020-07-26 
	 * Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel distancereport(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from masters.reportproceduer('distancereport', 'companyid', 'roleid', 'ownersid', 'loginid', 'searchby',
//					'offset', 'Count', 'fromdate', 'todate', 'selectcustomerid', 'selectvehicleid', '', '', '', '', '', '', '', '', '');
			getdev = con.prepareStatement(QueryConstants.OVERSPEED_STATMENT);
			getdev.setString(1, "distancereport");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// search by
			getdev.setString(7, searchByOffsetSplit[0]);// offset
			getdev.setString(8, "Count");
			getdev.setString(9,devdata.getParam4());//fromdate
			getdev.setString(10,devdata.getParam5());//todate
			getdev.setString(11,devdata.getParam6());//selectcustomerid
			getdev.setString(12,devdata.getParam7());//selectvehicleid
			rscount = getdev.executeQuery();
			System.out.println("Query == > " + getdev.toString());

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);

				getdev = con.prepareStatement(QueryConstants.OVERSPEED_STATMENT);
				getdev.setString(1, "distancereport");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam1());// search by
				getdev.setString(7, searchByOffsetSplit[0]);// offset
				getdev.setString(8, "Count");
				getdev.setString(9,devdata.getParam4());//fromdate
				getdev.setString(10,devdata.getParam5());//todate
				getdev.setString(11,devdata.getParam6());//selectcustomerid
				getdev.setString(12,devdata.getParam7());//selectvehicleid
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					vehicleid, vehicleno, gpsfdate, gpstdate, gpstdate - gpsfdate as duration, gpsdistance, 
//				       flat, flon, floc, tlat, tlon, tloc, locdetails, ododistance, deviceimeino, gpscount, 
//				       workingtime, idletime, stoppagetime, maxspeed, avgspeed

				
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // vehicleid
					obj.setParam3(rs.getString(2)); // vehicleno
					obj.setParam4(rs.getString(3)); //gpsfdate
					obj.setParam5(rs.getString(4)); //gpstdate
					obj.setParam6(rs.getString(5)); //gpsdistance
					obj.setParam7(rs.getString(6)); //flat
					obj.setParam8(rs.getString(7)); //flon
					obj.setParam9(rs.getString(8)); //tlat
					obj.setParam10(rs.getString(9)); //tlon
					obj.setParam11(rs.getString(10)); //tloc
					obj.setParam12(rs.getString(11)); //locdetails
					obj.setParam13(rs.getString(12)); //ododistance
					obj.setParam14(rs.getString(13)); //deviceimeino
					obj.setParam15(rs.getString(14)); //gpscount
					obj.setParam16(rs.getString(15)); //workingtime
					obj.setParam17(rs.getString(16));//idletime
					obj.setParam18(rs.getString(17));//stoppagetime
					obj.setParam19(rs.getString(18));//maxspeed
					obj.setParam20(rs.getString(19));//avgspeed
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
//			setException.setParam1("distance report");
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
