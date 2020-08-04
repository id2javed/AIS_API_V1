package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.APIResponseModel;
import com.app.connection.DBConnection;
import com.app.main.dao.ListDataResponse;
import com.app.main.dao.RequestSelectDataDao;
import com.app.queryconstants.PaginationControl;
import com.app.queryconstants.QueryConstants;

public class HistoryDataManager {

	/**
	   Developer : Prem Gaigole
	   Date      : 2020-07-09
	   Description : Select framework Manager
	   Update Date :
	   Updation  
	 * 
	 **/
	
	public APIResponseModel historydata(String[] spltstr, RequestSelectDataDao devdata) {
		RequestSelectDataDao obj = null;
		List<RequestSelectDataDao> detailsdata = new ArrayList<RequestSelectDataDao>();
		ListDataResponse listData = new ListDataResponse();
		APIResponseModel apiResponseModel = new APIResponseModel();
		ResultSet rscount = null;
		

		
		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdev = null;
		String searchByOffset=PaginationControl.getPaginationControl(devdata.getParam2(),devdata.getParam3()); // param2 = pageno & param 3 = ItemPerPage
		String [] searchByOffsetSplit=searchByOffset.split("&&");
		
		try {
//			select * from masters.selectmapprocedure('historydata', 'companyid', 'roleid', 'ownersid', 'loginid',  'searchby', 
//					'seraccbyoffset', 'iscount', 'vehicleno', 'fromdatetime',  'todatetime');
			
			getdev = con.prepareStatement(QueryConstants.HISTORY_STATMENT);
			getdev.setString(1, "historydata");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// searchby
			getdev.setString(7, searchByOffsetSplit[0]);//seraccbyoffset
			getdev.setString(8, "Count");//iscount
			getdev.setString(9, devdata.getParam4());//vehicleno
			getdev.setString(10, devdata.getParam5());//fromdatetime
			getdev.setString(11, devdata.getParam6());//todatetime
			System.out.println("Query == > " + getdev.toString());
			rscount = getdev.executeQuery();
			

			int rownumber=0;
			
			if(rscount.next())
			{
				
				listData.setParam1(rscount.getString(1));
				rownumber=Integer.parseInt(searchByOffsetSplit[1]);
				
				getdev = con.prepareStatement(QueryConstants.HISTORY_STATMENT);
				getdev.setString(1, "historydata");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam1());// searchby
				getdev.setString(7, searchByOffsetSplit[0]);//offset
				getdev.setString(8, "");
				getdev.setString(9, devdata.getParam4());//vehicleno
				getdev.setString(10, devdata.getParam5());//fromdatetime
				getdev.setString(11, devdata.getParam6());//todatetime
				System.out.println("getdev "+getdev);
             ResultSet rs=getdev.executeQuery();
			while (rs.next()) {
				
//				
//				 lpp.imeino, lpp.packetdate+lpp.packettime,lpp.latitude, lpp.longitude,lpp.vehiclespeed::numeric(5), lpp.gpsstatus
//				 ,lpp.vehicledirection::numeric(5),lpp.ignumber, 
//					CASE WHEN  lpp.ignumber = ''1'' THEN ''green'' ELSE  ''red'' end as color, CASE WHEN  lpp.gpsstatus = ''1'' THEN 
//					''green'' ELSE  ''red'' end as gpscolor		
	
				obj = new RequestSelectDataDao();
				obj.setParam1(Integer.toString(rownumber++)); // Srno
				obj.setParam2(rs.getString(1)); // lpp.imeino
				obj.setParam3(rs.getString(2)); // lpp.packetdate
				obj.setParam4(rs.getString(3)); // lpp.packettime
				obj.setParam5(rs.getString(4)); // lpp.latitude
				obj.setParam6(rs.getString(5)); // lpp.longitude
				obj.setParam7(rs.getString(6)); // lpp.vehiclespeed
				obj.setParam8(rs.getString(7)); // lpp.gpsstatus
				obj.setParam9(rs.getString(8)); // longitudedirection
				obj.setParam10(rs.getString(9)); // lpp.ignumber
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
			}else
			{
				
				apiResponseModel.setEntity("NO Data Found");
				apiResponseModel.setStatus(false);
				apiResponseModel.setStatuscode(201);
			}
			
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(500);
			apiResponseModel.setException("exception " + e.toString());
		} finally {
			DBConnection.getCloseConnection(con);
		}
		return apiResponseModel;

	}

}
