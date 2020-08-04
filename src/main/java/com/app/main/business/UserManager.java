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
import com.app.main.dao.RequestInsertDataDao;
import com.app.main.dao.RequestSelectDataDao;
import com.app.queryconstants.PaginationControl;
import com.app.queryconstants.QueryConstants;

public class UserManager {
	APIResponseModel apiResponseModel = null;
	public APIResponseModel insertuser(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		APIResponseModel apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;
	
		String[] result=null;
		try {
			con = DBConnection.getMainConnection();
			try {
				
//				select * from masters.insertmasterprocedure('insertuser', 'userid', 'usercontactperson', 'usercontpersemailid', 
//				'usercontactmobile1'5,  'usercontactmobile2', 'userregaddress1', 'userregaddress2', 'userdesignation', 
//				'usercity'10,'userstate', 'usercitypincode', 'creatorloginid',  'roleid', 'loginname','loginpasswd', '',
//				'', '',  '', '', '', '','', '', '', '', '', '', '', '', '','', '', '', '','',   '', '', '', '','', '', '',
//				'','', '', '', '','','');				'', '','', '', '', '','', '', '', '','','');
				
				preparedStatement = con.prepareStatement(QueryConstants.insert_user);
				
				preparedStatement.setString(1, "insertuser");
				preparedStatement.setString(2, requestInsertDataDao.getParam1());//userid
				preparedStatement.setString(3, requestInsertDataDao.getParam2());//usercontactperson
				preparedStatement.setString(4, requestInsertDataDao.getParam3()); //usercontpersemailid
				preparedStatement.setString(5, requestInsertDataDao.getParam4());//usercontactmobile1
				preparedStatement.setString(6, requestInsertDataDao.getParam5());//usercontactmobile2
				preparedStatement.setString(7, requestInsertDataDao.getParam6());//userregaddress1
				preparedStatement.setString(8, requestInsertDataDao.getParam7()); //userregaddress2
				preparedStatement.setString(9, requestInsertDataDao.getParam8());//userdesignation
				preparedStatement.setString(10, requestInsertDataDao.getParam9());//usercity
				preparedStatement.setString(11, requestInsertDataDao.getParam10());//userstate
				preparedStatement.setString(12, requestInsertDataDao.getParam11()); //usercitypincode
				preparedStatement.setString(13, splitstr[0]);//creatorloginid
				// preparedStatement.setString(14, splitstr[3]);//roleid
				preparedStatement.setString(14, requestInsertDataDao.getParam13());//roleid
				preparedStatement.setString(15, requestInsertDataDao.getParam14());//loginname
				preparedStatement.setString(16, requestInsertDataDao.getParam15()); //loginpasswd
				
			} catch (Exception e) {
				System.out.println("exception in this try == "+e.toString());
			}

			System.out.println("query==> " +preparedStatement.toString());
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
			System.out.println("Error : " + e);
			apiResponseModel.setEntity("INTERNAL SEVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(500);
			apiResponseModel.setException("exception " +e.toString());
		}
		finally  {

//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			RequestInsertDataDao setException = new RequestInsertDataDao();
////			[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("live emergency alert");
//			setException.setParam2("SELECT");
//			setException.setParam3(preparedStatement.toString());
//			setException.setParam4(apiResponseModel.getEntity().toString());
//			setException.setParam5(apiResponseModel.getException());
//			setException.setParam6(result[7]); // ipaddress
//			setException.setParam7(result[0]); // loginid
//			setException.setParam8(result[8]); // browserdetails
//			setException.setParam9(result[9]); // osdetails
//			setException.setParam10(result[10]); // Application Type (Web/Mobile)
//			wrap.insetException(setException);
			
			/* Close Connection */
			if (con != null) {
				DBConnection.getCloseConnection(con);
			}
		}
		return apiResponseModel;
	}
	
	/**
	   Developer : Prem Gaigole
	   Date      :  2020-07-27
	   Description : Update framework Manager
	   Update Date :
	   Updation  
	 *
	 **/
		
	public APIResponseModel updateuser(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		APIResponseModel apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String[] result=null;
		try {
			con = DBConnection.getMainConnection();
			try {
//				select * from masters.insertprocedure('updateuser', 'userid', 'usercontactperson', 'usercontpersemailid', 
//						'usercontactmobile1'5,  'usercontactmobile2', 'userregaddress1', 'userregaddress2', 'userdesignation',
//						'usercity'10,'userstate', 'usercitypincode', 'creatorloginid'13,  'roleid', ''15,'', '', '', '',  '', '', 
//						'', '','', '', '', '', '', '', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','');

				preparedStatement = con.prepareStatement(QueryConstants.update_user);
				preparedStatement.setString(1, "updateuser");
				preparedStatement.setString(2, requestInsertDataDao.getParam1());//userid
				preparedStatement.setString(3, requestInsertDataDao.getParam2());//usercontactperson
				preparedStatement.setString(4, requestInsertDataDao.getParam3()); //usercontpersemailid
				preparedStatement.setString(5, requestInsertDataDao.getParam4());//usercontactmobile1
				preparedStatement.setString(6, requestInsertDataDao.getParam5());//usercontactmobile2
				preparedStatement.setString(7, requestInsertDataDao.getParam6());//userregaddress1
				preparedStatement.setString(8, requestInsertDataDao.getParam7());//userregaddress2
				preparedStatement.setString(9, requestInsertDataDao.getParam8());//userdesignation
				preparedStatement.setString(10, requestInsertDataDao.getParam9());//usercity
				preparedStatement.setString(11, requestInsertDataDao.getParam10());//userstate
				preparedStatement.setString(12, requestInsertDataDao.getParam11());//usercitypincode
				preparedStatement.setString(13, splitstr[0]);//creatorloginid
				preparedStatement.setString(14, requestInsertDataDao.getParam13());//roleid
				// preparedStatement.setString(14, splitstr[3]);//roleid
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("query==> " +preparedStatement.toString());
			apiResponseModel = new APIResponseModel();
			
			if(resultSet.next()) {	
				result=resultSet.getString(1).split("#");
				apiResponseModel.setEntity(result[1]);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(Integer.parseInt(result[0]));					
				}
			else {
				
				apiResponseModel.setEntity("INTERNAL SERVER ERROR");
				apiResponseModel.setStatus(false);
				apiResponseModel.setStatuscode(412);
			}
		} catch (Exception e) {

			apiResponseModel.setEntity("INTERNAL SERVER ERROR");
			apiResponseModel.setStatus(false);
			apiResponseModel.setStatuscode(500);
			apiResponseModel.setException("error occure"+e.toString());
		}
		finally {

//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			RequestInsertDataDao setException = new RequestInsertDataDao();
////				[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("update user");
//			setException.setParam2("SELECT");
//			setException.setParam3(preparedStatement.toString());
//			setException.setParam4(apiResponseModel.getEntity().toString());
//			setException.setParam5(apiResponseModel.getException());
//			setException.setParam6(result[7]); // ipaddress
//			setException.setParam7(result[0]); // loginid
//			setException.setParam8(result[8]); // browserdetails
//			setException.setParam9(result[9]); // osdetails
//			setException.setParam10(result[10]); // Application Type (Web/Mobile)
//			wrap.insetException(setException);
			/* Close Connection */
			if (con != null)
				DBConnection.getCloseConnection(con);

		}

		
		return apiResponseModel;
	}
	
	/**
	   Developer : Prem Gaigole
	   Date      :  2020-07-27
	   Description : Delete framework Manager
	   Update Date :
	   Updation  
	 * 
	 **/
	
	public APIResponseModel deleteuser(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String[] result=null;
		try {
			System.out.println("in manager delete....");
//			select * from masters.insertprocedure('deleteuser',  'userid',  'creatorloginid', 'remarks', 
//					'', '',  '',  '', '', '', '','', '', '',  '', '','', '', '', '',  '', '', '', '','', '', '', 
//					'', '', '', '', '', '', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','');
			try {
			con = DBConnection.getMainConnection();

			preparedStatement = con.prepareStatement(QueryConstants.delete_user);
			preparedStatement.setString(1, "deleteuser");
			preparedStatement.setString(2, requestInsertDataDao.getParam1());//userid
			preparedStatement.setString(3, splitstr[0]);//LoginId
			preparedStatement.setString(4, requestInsertDataDao.getParam3());//remarks
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

//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			RequestInsertDataDao setException = new RequestInsertDataDao();
////				[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("delete user");
//			setException.setParam2("SELECT");
//			setException.setParam3(preparedStatement.toString());
//			setException.setParam4(apiResponseModel.getEntity().toString());
//			setException.setParam5(apiResponseModel.getException());
//			setException.setParam6(result[7]); // ipaddress
//			setException.setParam7(result[0]); // loginid
//			setException.setParam8(result[8]); // browserdetails
//			setException.setParam9(result[9]); // osdetails
//			setException.setParam10(result[10]); // Application Type (Web/Mobile)
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

	public APIResponseModel userdetails(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from masters.selectmasterdetails('userdetails', 'compantid', 'ownersid', 'roleid', 'loginid',
//					'searchby', 'seraccbyoffset', 'iscount', 'selectcompanyid', '', '');


			getdev = con.prepareStatement(QueryConstants.user_details);
			getdev.setString(1, "userdetails");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// search by
			getdev.setString(7, searchByOffsetSplit[0]);// offset
			getdev.setString(8, "Count");
			getdev.setString(9, devdata.getParam4());// selectcompanyid
			
			rscount = getdev.executeQuery();
			System.out.println("Query == > " + getdev.toString());

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);

				getdev = con.prepareStatement(QueryConstants.user_details);
				getdev.setString(1, "userdetails");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam1());// search by
				getdev.setString(7, searchByOffsetSplit[0]);// offset
				getdev.setString(8, "");
				getdev.setString(9, devdata.getParam4());// selectcompanyid
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					mu.userid, mu.usercontactperson, mu.usercontpersemailid, mu.usercontactmobile1, mu.usercontactmobile2,
//					mu.userregaddress1, mu.userregaddress2, mu.userdesignation, 

				
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // mu.userid
					obj.setParam3(rs.getString(2)); // mu.usercontactperson
					obj.setParam4(rs.getString(3)); //mu.usercontactmobile1
					obj.setParam5(rs.getString(4)); //mu.usercontactmobile2
					obj.setParam6(rs.getString(5)); //mu.userregaddress1
					obj.setParam7(rs.getString(6)); //mu.userregaddress2
					obj.setParam8(rs.getString(7)); // mu.userdesignation
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
//			//		[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
//			//		[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("user details");
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
			if (con != null) {
				DBConnection.getCloseConnection(con);
			}
		}
		return apiResponseModel;
	}

}
