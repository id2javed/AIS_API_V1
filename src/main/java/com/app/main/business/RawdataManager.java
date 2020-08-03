package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.APIResponseModel;
import com.app.connection.DBConnection;
import com.app.main.dao.ListDataResponse;
import com.app.main.dao.RawData;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.model.RawDataResponse;
import com.app.queryconstants.PaginationControl;
import com.app.queryconstants.QueryConstants;

public class RawdataManager {

	public List<RawDataResponse> rawdatamethod(RawData rawD, String[] spltstr) {
		Connection con=null;
		Connection con2=null;
		System.out.println("Manager");
	   List<RawDataResponse> data=new ArrayList<RawDataResponse>();
	   RawDataResponse obj = new RawDataResponse();
		try {
			
		PreparedStatement ps;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfnew = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		SimpleDateFormat sdfnew1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfnew2 = new SimpleDateFormat("dd-MMM-yyyy");
		
		
		 con=DBConnection.getMainConnection();
		 con2=DBConnection.gethistoryDataConnection();
		
		
		String sqlselect4 = null,cond = ""	;
		String seraccbyoffset = "";
	
		int cnt = 5601;
		
		String sqlportcomp = null;
		try {
			
			
			
//			sqlportcomp = " select pm.portno from dblocator.msttblmapports mp "
//					+ "inner join  dblocator.msttblports pm on pm.portid =mp.portid  where  mp.companyid =" +spltstr[4]
//					+ " limit 1 ";
//			
//			ps = con.prepareStatement(sqlportcomp);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				cnt = rs.getInt(1);
//			}
//			System.out.println("Date1 "+rawD.getDate());
//			
//			System.out.println("Date2 ==>> "+sdfnew2.parse(rawD.getDate()));
			
			//rawD.setDate( sdfnew1.format(sdfnew2.parse(rawD.getDate())));
				cond="  where datamessage::varchar like '%"+ rawD.getImeino() + "%'::varchar   ";
			
			System.out.println("Manager 3");
			sqlselect4 = " SELECT recordid, datatimestamp, datamessage, "
					+ " case when clientfqdn like '%10.0.0.4%' then Replace(clientfqdn,'10.0.0.4','device.AIS-140.com')  end as clientfqdn, "
					+ " clientport FROM \"devicedata_logs_" +rawD.getDate()+"\""
					+ ""+cond+"  ORDER BY datatimestamp desc   ";

			ps = con2.prepareStatement(sqlselect4);
			ResultSet rs4 = ps.executeQuery();
			Integer i = null;
			
			while (rs4.next()) {
				try {
					
					obj.setRecordid(rs4.getString(1));

					java.util.Date parseTimestamp = sdf.parse(rs4.getString(2));
					obj.setDatatimestamp(sdfnew.format(parseTimestamp).toString());
					obj.setDatamessage(rs4.getString(3));
					obj.setClientfqdn(rs4.getString(4));
					obj.setClientport(rs4.getString(5));
					obj.setRowno(i++);
					data.add(obj);
				} catch (Exception e) {
					 System.out.println("rawdata error is " + e);

				}
			}
			

		} catch (Exception e) {
			
			System.out.println("Exception ==>> "+e);
			obj.setResponse("Error");
		}


		} catch (Exception e) {
			obj.setResponse("Error");
			 System.out.println("rawdata error is " + e);
		
		}finally {
			DBConnection.getCloseConnection(con);
			DBConnection.getCloseConnection(con2);
		}
		
		
		return data;
	}

	public APIResponseModel rawdata(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from masters.selectcompliancestatus('rawdata', 'companyid', 'roleid', 'ownersid', 'loginid', 'searchby', 'seraccbyoffset', 
//				'Count', 'selectvendorid', 'imeino',  'date');
			
			getdev = con.prepareStatement(QueryConstants.selectcompliancestatus);
			getdev.setString(1, "rawdata");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// search by
			getdev.setString(7, searchByOffsetSplit[0]);//offset
			getdev.setString(8, "Count");
			getdev.setString(9, devdata.getParam4());//selectvendorid
			getdev.setString(10, devdata.getParam5());//imeino
			getdev.setString(11, devdata.getParam6());//date
			rscount = getdev.executeQuery();
			System.out.println(" Count Query == > " + getdev.toString());



			int rownumber=0;
			if(rscount.next())
			{
				
				listData.setParam1(rscount.getString(1));
				rownumber=Integer.parseInt(searchByOffsetSplit[1]);
				
				getdev = con.prepareStatement(QueryConstants.selectcompliancestatus);
				getdev.setString(1, "rawdata");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam1());// search by
				getdev.setString(7, searchByOffsetSplit[0]);//offset
				getdev.setString(8, "");
				getdev.setString(9, devdata.getParam4());//selectvendorid
				getdev.setString(10, devdata.getParam5());//imeino
				getdev.setString(11, devdata.getParam6());//date
				System.out.println("getdev "+getdev);
				
					ResultSet  rs=getdev.executeQuery();
			
               
			while (rs.next()) {
//				 recordid, TO_CHAR(datatimestamp, ''dd-Mon-yyyy HH:MI:SS AM'') as datatimestamp, datamessage,
//			Replace(clientfqdn,''10.0.0.4'',''device.vltsecurity.com'') as clientfqdn, clientport
//				
				
				obj = new RequestSelectDataDao();
				obj.setParam1(Integer.toString(rownumber++)); // Srno
				obj.setParam2(rs.getString(1)); // recordid
				obj.setParam3(rs.getString(2)); // datatimestamp
				obj.setParam4(rs.getString(3)); // datamessage
				obj.setParam5(rs.getString(4)); // clientfqdn
				obj.setParam6(rs.getString(5)); // clientport
				
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
	
	
	public APIResponseModel emergencyrawdata(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from masters.selectcompliancestatus('emergencyrawdata', 'companyid', 'roleid', 'ownersid', 'loginid', 
//					'searchby', 'seraccbyoffset', 'Count', 'selectvendorid', 'imeino',  'date');
			
			getdev = con.prepareStatement(QueryConstants.selectcompliancestatus);
			getdev.setString(1, "emergencyrawdata");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// search by
			getdev.setString(7, searchByOffsetSplit[0]);//offset
			getdev.setString(8, "Count");
			getdev.setString(9, devdata.getParam4());//selectvendorid
			getdev.setString(10, devdata.getParam5());//imeino
			getdev.setString(11, devdata.getParam6());//date
			rscount = getdev.executeQuery();
			System.out.println(" Count Query == > " + getdev.toString());



			int rownumber=0;
			if(rscount.next())
			{
				
				listData.setParam1(rscount.getString(1));
				rownumber=Integer.parseInt(searchByOffsetSplit[1]);
				
				getdev = con.prepareStatement(QueryConstants.selectcompliancestatus);
				getdev.setString(1, "emergencyrawdata");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam1());// search by
				getdev.setString(7, searchByOffsetSplit[0]);//offset
				getdev.setString(8, "");
				getdev.setString(9, devdata.getParam4());//selectvendorid
				getdev.setString(10, devdata.getParam5());//imeino
				getdev.setString(11, devdata.getParam6());//date
				System.out.println("getdev "+getdev);
				
					ResultSet  rs=getdev.executeQuery();
			
               
			while (rs.next()) {
				
//				recordid, TO_CHAR(datatimestamp, ''dd-Mon-yyyy HH:MI:SS AM'') as datatimestamp, datamessage,
//				clientfqdn as clientfqdn, clientport	
				
				
				obj = new RequestSelectDataDao();
				obj.setParam1(Integer.toString(rownumber++)); // Srno
				obj.setParam2(rs.getString(1)); // recordid
				obj.setParam3(rs.getString(2)); // datatimestamp
				obj.setParam4(rs.getString(3)); // datamessage
				obj.setParam5(rs.getString(4)); // clientfqdn
				obj.setParam6(rs.getString(5)); // clientport
				
				detailsdata.add(obj);
				System.out.println("into while==> " + detailsdata);
				
				
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
	
	
	public APIResponseModel overspeedreport(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from masters.reportproceduer('overspeedreport', 'companyid', 'roleid', 'ownersid', 'loginid', 'vehicleid', 
//					'offset', 'Count', 'date', 'fromtime', 'totime', 'overspeed', '', '', '', '', '', '', '', '', '');
			
			getdev = con.prepareStatement(QueryConstants.OVERSPEED_STATMENT);
			getdev.setString(1, "overspeedreport");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam4());// vehicleid
			getdev.setString(7, searchByOffsetSplit[0]);//offset
			getdev.setString(8, "Count");
			getdev.setString(9, devdata.getParam5());//date
			getdev.setString(10, devdata.getParam6());//fromtime
			getdev.setString(11, devdata.getParam7());//totime
			getdev.setString(12, devdata.getParam8());//overspeed
			rscount = getdev.executeQuery();
			System.out.println(" Count Query == > " + getdev.toString());
			
			int rownumber=0;
			if(rscount.next())
			{
				
				listData.setParam1(rscount.getString(1));
				rownumber=Integer.parseInt(searchByOffsetSplit[1]);
				
				getdev = con.prepareStatement(QueryConstants.OVERSPEED_STATMENT);
				getdev.setString(1, "overspeedreport");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam4());// vehicleid
				getdev.setString(7, searchByOffsetSplit[0]);//offset
				getdev.setString(8, "");
				getdev.setString(9, devdata.getParam5());//date
				getdev.setString(10, devdata.getParam6());//fromtime
				getdev.setString(11, devdata.getParam7());//totime
				getdev.setString(12, devdata.getParam8());//overspeed
				System.out.println("getdev "+getdev);
				
					ResultSet  rs=getdev.executeQuery();
			
               
			while (rs.next()) {
				
//				imeino, packetdate, packetdatetime , latitude, latitudedirection, longitude, longitudedirection, vehiclespeed,  
//				gsmsignal, satellite, batteryvoltage, vehicledirection, ignumber, alertidx, datamessage, 
//				swversion, distancekm, firmwareversion, packetstatus, ignitionstatus, gpsstus, alertname, 
//				deviceuniqueno, customername, rtoname, customermobile
				
				
				obj = new RequestSelectDataDao();
				obj.setParam1(Integer.toString(rownumber++)); // Srno
				obj.setParam2(rs.getString(1)); // imeino
				obj.setParam3(rs.getString(2)); // packetdate
				obj.setParam4(rs.getString(3)); // packetdatetime
				obj.setParam5(rs.getString(4)); // latitude
				obj.setParam6(rs.getString(5)); // latitudedirection
				obj.setParam7(rs.getString(6)); // longitude
				obj.setParam8(rs.getString(7)); // longitudedirection
				obj.setParam9(rs.getString(8)); // vehiclespeed
				obj.setParam10(rs.getString(9)); // gsmsignal
				obj.setParam11(rs.getString(10)); // satellite
				obj.setParam12(rs.getString(11)); // batteryvoltage
				obj.setParam13(rs.getString(12)); // vehicledirection
				obj.setParam14(rs.getString(13)); // ignumber
				obj.setParam15(rs.getString(14)); // alertidx
				obj.setParam16(rs.getString(15)); // datamessage
				obj.setParam17(rs.getString(16)); //swversion
				obj.setParam18(rs.getString(17));//distancekm
				obj.setParam19(rs.getString(18));//firmwareversion
				obj.setParam20(rs.getString(19));//packetstatus
				obj.setParam21(rs.getString(20));//ignitionstatus
				obj.setParam22(rs.getString(21));//gpsstus
				obj.setParam23(rs.getString(22));//alertname
				obj.setParam24(rs.getString(23));//deviceuniqueno
				obj.setParam25(rs.getString(24));//customername
				obj.setParam26(rs.getString(25));//customername
				obj.setParam27(rs.getString(26));//rtoname
				obj.setParam28(rs.getString(27));//customermobile
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
