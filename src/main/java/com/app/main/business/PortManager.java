package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.app.connection.APIResponseModel;
import com.app.connection.DBConnection;
import com.app.main.dao.RequestInsertDataDao;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.model.ListDataResponse;
import com.app.queryconstants.PaginationControl;
import com.app.queryconstants.QueryConstants;

public class PortManager {
	APIResponseModel apiResponseModel = null;
	public APIResponseModel insertport(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;
//		RequestSelectDataDao data=null;

		String[] result=null;
		try {
			con = DBConnection.getMainConnection();
			try {
				preparedStatement = con.prepareStatement(QueryConstants.insertport);
				System.out.println("enter in catch");
				preparedStatement.setString(1, "insertport");
				preparedStatement.setString(2,"");//portid
				preparedStatement.setString(3, requestInsertDataDao.getParam1());//portno
				preparedStatement.setString(4, splitstr[0]); //creatorloginid
				preparedStatement.setString(5,requestInsertDataDao.getParam2());//ip/domain
				preparedStatement.setString(6,requestInsertDataDao.getParam21());//port status
			} catch (Exception e) {
				System.out.println("exception in this try == "+e.toString());
			}//
//			select * from masters.insertprocedure('insertport',  'portid',  'portno', 'creatorloginid', 'ip/domain', '',  '',  '',
//					'', '', '','', '', '',  '', '','', '', '', '',  '', '', '', '','', '', '', '', '', '', '', '','', '', '', '','',   '', '',
//					'', '','', '', '', '','', '', '', '','','');
			
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("query==> insert " +preparedStatement.toString());
			apiResponseModel = new APIResponseModel();
			
			if (resultSet.next()) {
				result=resultSet.getString(1).split("#");
				apiResponseModel.setEntity(result[1]);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(Integer.parseInt(result[0]));		
			} else {
				apiResponseModel.setEntity("INTERNAL SERVER ERROR");
				apiResponseModel.setStatus(false);
				apiResponseModel.setStatuscode(412);
			}
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(500);
			apiResponseModel.setException("exception " +e.toString());
		}
		finally {
						if(con !=null) {
				DBConnection.getCloseConnection(con);
			}
		}
		return apiResponseModel;
	}
	
	

	public APIResponseModel assignporttovendor(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		APIResponseModel apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String[] result=null;
		try {
			con = DBConnection.getMainConnection();
			try {
				preparedStatement = con.prepareStatement(QueryConstants.assignvendor);
				System.out.println("enter in catch");
				preparedStatement.setString(1, "assignporttovendor");
				preparedStatement.setString(2, requestInsertDataDao.getParam1());//portid
				preparedStatement.setString(3, requestInsertDataDao.getParam2());//vendorid
				preparedStatement.setString(4, splitstr[0]); //creatorloginid
				
			} catch (Exception e) {
				System.out.println("exception in this try == "+e.toString());
			}
//				select * from masters.insertmasterprocedure('assignporttovendor',  'portid',  'vendorid', 'creatorloginid', '', '',  '', 
//				'', '', '', '','', '', '',  '', '','', '', '', '',  '', '', '', '','', '', '', '', '', '', '', '','', '', '', '','',   '',
//				'', '', '','', '', '', '','', '', '', '','','');
			
			System.out.println("Query==> " +preparedStatement);
			ResultSet resultSet = preparedStatement.executeQuery();
			apiResponseModel = new APIResponseModel();
			
			if (resultSet.next()) {
				result=resultSet.getString(1).split("#");
				apiResponseModel.setEntity(result[1]);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(Integer.parseInt(result[0]));		
			} else {
				apiResponseModel.setEntity("INTERNAL SERVER ERROR");
				apiResponseModel.setStatus(false);
				apiResponseModel.setStatuscode(412);
			}
		} catch (Exception e) {
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(500);
			apiResponseModel.setException("exception " +e.toString());
		}
		finally {
						if(con !=null) {
				DBConnection.getCloseConnection(con);
			}
		}
		return apiResponseModel;
	}
	
	
	
	
	public APIResponseModel portdetails(String[] spltstr, RequestSelectDataDao devdata) {
		RequestSelectDataDao obj = null;
		List<RequestSelectDataDao> detailsdata = new ArrayList<RequestSelectDataDao>();
		ListDataResponse listData = new ListDataResponse();
		APIResponseModel apiResponseModel = new APIResponseModel();
		ResultSet rscount = null;
		String totalcounts=null;

		
		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdev = null;
		String searchByOffset=PaginationControl.getPaginationControl(devdata.getParam2(),devdata.getParam3()); // param2 = pageno & param 3 = ItemPerPage
		String [] searchByOffsetSplit=searchByOffset.split("&&");
		
		try {
//		select * from masters.selectmasterdetails('portdetails', 'compantid', 'ownersid', 'roleid', 'loginid',
//		'searchby', 'seraccbyoffset', 'iscount', '', '', '');

			getdev = con.prepareStatement(QueryConstants.selectdealerdetails);
			getdev.setString(1, "portdetails");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// search by
			getdev.setString(7, searchByOffsetSplit[0]);//offset
			getdev.setString(8, "iscount");
			
			rscount = getdev.executeQuery();
			System.out.println("Query == > " + getdev.toString());

			
			
			int rownumber=0;
			
			if(rscount.next())
			{
				System.out.println("enter in catch");
				 totalcounts=rscount.getString(1);
				rownumber=Integer.parseInt(searchByOffsetSplit[1]);
				
				getdev = con.prepareStatement(QueryConstants.selectdealerdetails);
				getdev.setString(1, "portdetails");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam1());// search by
				getdev.setString(7, searchByOffsetSplit[0]);//offset
				getdev.setString(8, "");
				
				System.out.println("getdev "+getdev);
             ResultSet rs=getdev.executeQuery();
			while (rs.next()) {
				
//				mp.portid, mp.portno, mp.ip,  mp.createdat
				
				System.out.println("enter in while");
				obj = new RequestSelectDataDao();
				obj.setParam1(Integer.toString(rownumber++)); // Srno
				obj.setParam2(rs.getString(1)); // portid
				obj.setParam3(rs.getString(2)); // portno
				obj.setParam4(rs.getString(3)); // ip
				obj.setParam5(rs.getString(4)); // createdat
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
				obj.setParam51(totalcounts);
				detailsdata.add(obj);
				
			}

			apiResponseModel.setEntity(detailsdata);
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

	

	/**
	   Developer : Prem Gaigole
	   Date      : 2020-07-09
	   Description : Select framework Manager
	   Update Date :
	   Updation  
	 * 
	 **/
	
	public APIResponseModel portassigndetails(String[] spltstr, RequestSelectDataDao devdata) {
		RequestSelectDataDao obj = null;
		List<RequestSelectDataDao> detailsdata = new ArrayList<RequestSelectDataDao>();
		ListDataResponse listData = new ListDataResponse();
		APIResponseModel apiResponseModel = new APIResponseModel();
		ResultSet rscount = null;
		

		
		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdev = null;
		String searchByOffset=PaginationControl.getPaginationControl(devdata.getParam2(),devdata.getParam3()); // param2 = pageno & param 3 = ItemPerPage
		String [] searchByOffsetSplit=searchByOffset.split("&&");
		
		String totalcounts=null;
		try {

//			select * from masters.selectmasterdetails('portassigndetails', 'compantid', 'ownersid', 'roleid', 'loginid', 'searchby',
//					'seraccbyoffset', 'iscount', 'selectcompanyid', '', '');

//
			getdev = con.prepareStatement(QueryConstants.selectportassign);
			getdev.setString(1, "portassigndetails");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// search by
			getdev.setString(7, searchByOffsetSplit[0]);//offset
			getdev.setString(8, "Count");
			getdev.setString(9, devdata.getParam4());//selectcompanyid
			rscount = getdev.executeQuery();
			System.out.println("Query == > " + getdev.toString());


			
			int rownumber=0;
//			System.out.println("staus == "+rscount.next());
			if(rscount.next())
			{
				
				totalcounts=rscount.getString(1);
				System.out.println("enter in catch");
				System.out.println("coun================"+rscount.getString(1));
				rownumber=Integer.parseInt(searchByOffsetSplit[1]);

				getdev = con.prepareStatement(QueryConstants.selectportassign);
				getdev.setString(1, "portassigndetails");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam1());// search by
				getdev.setString(7, searchByOffsetSplit[0]);//offset
				getdev.setString(8, "");
				getdev.setString(9, devdata.getParam4());//selectcompanyid
				System.out.println("getdev "+getdev);
             ResultSet rs=getdev.executeQuery();
			while (rs.next()) {
				
//			 	mappv.portid, mappv.vendorid, mp.portno, mp.ip, 
//				mv.vendorname, mappv.createdat::date, portstatus
				
				obj = new RequestSelectDataDao();
				obj.setParam1(Integer.toString(rownumber++)); // Srno
				obj.setParam2(rs.getString(1)); // portid
				obj.setParam3(rs.getString(2)); // vendorid
				obj.setParam4(rs.getString(3)); // portno
				obj.setParam5(rs.getString(4)); // createdat
				obj.setParam6(rs.getString(5)); //Assigndate
				obj.setParam7(rs.getString(6)); //open/close
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
				obj.setParam51(totalcounts);
				detailsdata.add(obj);
				
			}

		
					
						apiResponseModel.setEntity(detailsdata);
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
	
	/**
	   Developer : Prem Gaigole
	   Date      :  2020-07-18
	   Description : Delete framework Manager
	   Update Date :
	   Updation  
	 * 
	 **/
	
	public APIResponseModel deleteport(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String[] result=null;
		try {
//			select * from commonmaster.insertprocedure('deleteport',  'portid',  'portno', 'creatorloginid', 'ip/domain', '',
//					'',  '', '', '', '','', '', '',  '', '','', '', '', '',  '', '', '', '','', '', '', '', '', '', '', '','', 
//					'', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','');
			try {
			con = DBConnection.getMainConnection();
			
			preparedStatement = con.prepareStatement(QueryConstants.DELETE_PORT);
			
			preparedStatement.setString(1, "deleteport");
			preparedStatement.setString(2, requestInsertDataDao.getParam1());//portid
			preparedStatement.setString(3, requestInsertDataDao.getParam2());//portno
			preparedStatement.setString(4, splitstr[0]);//creatorloginid
			preparedStatement.setString(5, requestInsertDataDao.getParam4());//ip/domain
			}catch (Exception e) {
				System.out.println("exception in catch");
			}


	ResultSet resultSet = preparedStatement.executeQuery();
	System.out.println("Query == > " +preparedStatement.toString());


			
			if(resultSet.next()) {
				
				result=resultSet.getString(1).split("#");
				apiResponseModel.setEntity(result[1]);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(Integer.parseInt(result[0]));
			}
			else {
				apiResponseModel.setEntity("Intenal Sever Error");
				apiResponseModel.setStatus(false);
				apiResponseModel.setStatuscode(412);
			}
		} catch (Exception e) {
			
		}
		finally {
			
			if(con!=null)
			DBConnection.getCloseConnection(con);
		}
		return apiResponseModel;
	}


	/**
	   Developer : Prem Gaigole
	   Date      :  2020-07-18
	   Description : Delete framework Manager
	   Update Date :
	   Updation  
	 * 
	 **/
	
	public APIResponseModel unassignporttovendor(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String[] result=null;
		try {
//			select * from masters.insertprocedure('unassignporttovendor',  'portid',  'vendorid', 'creatorloginid', '', '',  '', 
//					'', '', '', '','', '', '',  '', '','', '', '', '',  '', '', '', '','', '', '', '', '', '', '', '','', '', '',
//					'','',   '', '', '', '','', '', '', '','', '', '', '','','');
			try {
			con = DBConnection.getMainConnection();
			
			preparedStatement = con.prepareStatement(QueryConstants.UNASSIGNPORTTOVENDER);
			
			preparedStatement.setString(1, "unassignporttovendor");
			preparedStatement.setString(2, requestInsertDataDao.getParam1());//portid
			preparedStatement.setString(3, requestInsertDataDao.getParam2());//vendorid
			preparedStatement.setString(4, splitstr[0]);//creatorloginid
			
			}catch (Exception e) {
				System.out.println("exception in catch");
			}

			System.out.println("Query == > " +preparedStatement.toString());
	ResultSet resultSet = preparedStatement.executeQuery();
	


			
			if(resultSet.next()) {
				
				result=resultSet.getString(1).split("#");
				apiResponseModel.setEntity(result[1]);
				System.out.println("result set response = "+result[1]);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(Integer.parseInt(result[0]));
			}
			else {
				apiResponseModel.setEntity("Intenal Sever Error");
				apiResponseModel.setStatus(false);
				apiResponseModel.setStatuscode(412);
			}
		} catch (Exception e) {
			
		}
		finally {
			
			if(con!=null)
			DBConnection.getCloseConnection(con);
		}
		return apiResponseModel;
	}

}
