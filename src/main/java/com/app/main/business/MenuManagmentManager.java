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

public class MenuManagmentManager {
	APIResponseModel apiResponseModel = null;
	
	public APIResponseModel insertmainmenu(String[] splitstr,RequestInsertDataDao requestInsertDataDao ) {
		 apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;
 
		
		try {
			con = DBConnection.getMainConnection();
			try {
//				select * from menu.insertmenumanagement('insertmainmenu', 'menuid', 'menuname', 'menudescription', 'menuurl', 'roleid',
//						'creatorloginid', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
			
				preparedStatement = con.prepareStatement(QueryConstants.insert_main_menu);
				
				preparedStatement.setString(1, "insertmainmenu");
				preparedStatement.setString(2, requestInsertDataDao.getParam1());//menuid			
				preparedStatement.setString(3, requestInsertDataDao.getParam2());//menuname			
				preparedStatement.setString(4, requestInsertDataDao.getParam3()); //menudescription				
				preparedStatement.setString(5, requestInsertDataDao.getParam4());//menuurl				
				preparedStatement.setString(6, splitstr[3]);//roleid				
				preparedStatement.setString(7, splitstr[0]);//creatorloginid
				

				
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
//			setException.setParam1("insert main menu");
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
			
			
			/* Close Connection */
			if (con != null)
				DBConnection.getCloseConnection(con);

		}

		
		return apiResponseModel;
		
	}	

	public APIResponseModel insertsubmenu(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		APIResponseModel apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = DBConnection.getMainConnection();
			try {
//				select * from menu.insertmenumanagement('insertsubmenu', 'mainmenuid', 'submenuname', 'submenudescription', 
//						'submenuurl', 'roleid', 'creatorloginid', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
			
				preparedStatement = con.prepareStatement(QueryConstants.insert_main_menu);
				
				preparedStatement.setString(1, "insertsubmenu");
				preparedStatement.setString(2, requestInsertDataDao.getParam1());//menuid
				preparedStatement.setString(3, requestInsertDataDao.getParam2());//menuname
				preparedStatement.setString(4, requestInsertDataDao.getParam3()); //menudescription
				preparedStatement.setString(5, requestInsertDataDao.getParam4());//menuurl
				preparedStatement.setString(6, splitstr[3]);//roleid
				preparedStatement.setString(7, splitstr[0]);//creatorloginid

				
			} catch (Exception e) {
				System.out.println("exception in this try == "+e.toString());
			}

	
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("query==> " +preparedStatement.toString());

			if (resultSet.next()) {
			
				splitstr=resultSet.getString(1).split("#");
				apiResponseModel.setEntity(splitstr[1]);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(Integer.parseInt(splitstr[0]));		
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

//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			RequestInsertDataDao setException = new RequestInsertDataDao();
////				[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("insert sub menu");
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
			/* Close Connection */
			if (con != null)
				DBConnection.getCloseConnection(con);

		}

		
		return apiResponseModel;
		
	}	
		
	/**
	   Developer : Prem Gaigole
	   Date      :  2020-07-14
	   Description : Update framework Manager
	   Update Date :
	   Updation  
	 *
	 **/
		
	public APIResponseModel updatemenu(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		APIResponseModel apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		String[] result=null;
		try {
			con = DBConnection.getMainConnection();
			try {
				
//				select * from menu.insertmenumanagement('updatemenu', 'menuid', 'menuname', 'menudescription', 'menuurl',
//						'mainmenuid', 'creatorloginid', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
				
				preparedStatement = con.prepareStatement(QueryConstants.insert_main_menu);
				preparedStatement.setString(1, "updatemenu");
				preparedStatement.setString(2, requestInsertDataDao.getParam1());//menuid
				preparedStatement.setString(3, requestInsertDataDao.getParam2());//menuname
				preparedStatement.setString(4, requestInsertDataDao.getParam3()); //menudescription
				preparedStatement.setString(5, requestInsertDataDao.getParam4());//menuurl
				preparedStatement.setString(6, requestInsertDataDao.getParam5());//mainmenuid
				preparedStatement.setString(7, splitstr[0]);//creatorloginid
			
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
//
//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			RequestInsertDataDao setException = new RequestInsertDataDao();
////				[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("update menu");
//			setException.setParam2("UPDATE");
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
	   Date      :  2020-07-14
	   Description : Delete framework Manager
	   Update Date :
	   Updation  
	 * 
	 **/
	
	public APIResponseModel deletemenu(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
//			select * from menu.insertmenumanagement('deletemenu', 'menuid', 'menuname', 'menudescription', 'menuurl', 
//					'mainmenuid', 'creatorloginid', '', '', '', '', '', '', '', '', '', '', '', '', '', '');		
			try {
			con = DBConnection.getMainConnection();
			preparedStatement = con.prepareStatement(QueryConstants.insert_main_menu);
			preparedStatement.setString(1, "deletemenu");
			preparedStatement.setString(2, requestInsertDataDao.getParam1());//menuid
			preparedStatement.setString(3, requestInsertDataDao.getParam2());//menuname
			preparedStatement.setString(4, requestInsertDataDao.getParam3()); //menudescription
			preparedStatement.setString(5, requestInsertDataDao.getParam4());//menuurl
			preparedStatement.setString(6, requestInsertDataDao.getParam5());//mainmenuid
			preparedStatement.setString(7, splitstr[0]);//creatorloginid
			}catch (Exception e) {
				System.out.println("exception in catch");
			}
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("Query == > " +preparedStatement.toString());
			if(resultSet.next()) {
				splitstr=resultSet.getString(1).split("#");
				apiResponseModel.setEntity(splitstr[1]);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(Integer.parseInt(splitstr[0]));
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
//			setException.setParam1("delete menu");
//			setException.setParam2("DELETE");
//			setException.setParam3(preparedStatement.toString());
//			setException.setParam4(apiResponseModel.getEntity().toString());
//			setException.setParam5(apiResponseModel.getException());
//			setException.setParam6(splitstr[7]); // ipaddress
//			setException.setParam7(splitstr[0]); // loginid
//			setException.setParam8(splitstr[8]); // browserdetails
//			setException.setParam9(splitstr[9]); // osdetails
//			setException.setParam10(splitstr[10]); // Application Type (Web/Mobile)
//			wrap.insetException(setException);
			/* Close Connection */
			if (con != null)
				DBConnection.getCloseConnection(con);

		}

		return apiResponseModel;
	}

	public APIResponseModel assignmenurolewise(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		APIResponseModel apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = DBConnection.getMainConnection();
			try {
//				select * from menu.insertmenumanagement('assignmenurolewise', 'mainid', 'roleid', 'status', 'orderno',
//						'creatorloginid', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');

			
				preparedStatement = con.prepareStatement(QueryConstants.assign_menu_role_wise);
				
				preparedStatement.setString(1, "assignmenurolewise");
				preparedStatement.setString(2, requestInsertDataDao.getParam1());//menuid
				preparedStatement.setString(3, splitstr[3]);//roleid
				preparedStatement.setString(4, requestInsertDataDao.getParam3()); //status
				preparedStatement.setString(5, requestInsertDataDao.getParam4());//orderno
				preparedStatement.setString(6, splitstr[0]);//creatorloginid

				
			} catch (Exception e) {
				System.out.println("exception in this try == "+e.toString());
			}

	
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("query==> " +preparedStatement.toString());

			if (resultSet.next()) {
				splitstr=resultSet.getString(1).split("#");
				apiResponseModel.setEntity(splitstr[1]);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(Integer.parseInt(splitstr[0]));		
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

//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			RequestInsertDataDao setException = new RequestInsertDataDao();
////				[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("insert main menu");
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
			/* Close Connection */
			if (con != null)
				DBConnection.getCloseConnection(con);

		}

		
		return apiResponseModel;
		
	}	
	
	public APIResponseModel assignmenuuserwise(String[] splitstr , RequestInsertDataDao requestInsertDataDao) {
		APIResponseModel apiResponseModel = new APIResponseModel();
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = DBConnection.getMainConnection();
			try {
//				select * from menu.insertmenumanagement('assignmenuuserwise', 'mainid', 'roleid', 'status', 'orderno',
//						'ownersid', 'creatorloginid', '', '', '', '', '', '', '', '', '', '', '', '', '', '');		
				preparedStatement = con.prepareStatement(QueryConstants.insert_main_menu);
				
				preparedStatement.setString(1, "assignmenuuserwise");
				preparedStatement.setString(2, requestInsertDataDao.getParam1());//menuid
				preparedStatement.setString(3, splitstr[3]);//roleid
				preparedStatement.setString(4, requestInsertDataDao.getParam3()); //status
				preparedStatement.setString(5, requestInsertDataDao.getParam4());//orderno
				preparedStatement.setString(6, splitstr[4]);//ownersid
				preparedStatement.setString(7, splitstr[0]);//creatorloginid

				
			} catch (Exception e) {
				System.out.println("exception in this try == "+e.toString());
			}

	
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("query==> " +preparedStatement.toString());

			if (resultSet.next()) {
				splitstr=resultSet.getString(1).split("#");
				apiResponseModel.setEntity(splitstr[1]);
				apiResponseModel.setStatus(true);
				apiResponseModel.setStatuscode(Integer.parseInt(splitstr[0]));		
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

//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			RequestInsertDataDao setException = new RequestInsertDataDao();
////				[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("insert main menu");
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
			/* Close Connection */
			if (con != null)
				DBConnection.getCloseConnection(con);

		}

		return apiResponseModel;	
	}	

	/**
	 * Developer : Prem Gaigole Date : 2020-07-24 
	 * Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel getmainmenu(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from menu.selectmenudetails('getmainmenu', 'companyid', 'roleid', 'ownersid', 'loginid', '', '', '', '', '',  '');

			getdev = con.prepareStatement(QueryConstants.menu_list);
			getdev.setString(1, "getmainmenu");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			
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
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					menuid, menuname, menudescription, menuurl, orderno, mainmenuid, createdat, issubmenucount

				
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // menuid
					obj.setParam3(rs.getString(2)); // menuname
					obj.setParam4(rs.getString(3)); // menudescription
					obj.setParam5(rs.getString(4)); // menuurl
					obj.setParam6(rs.getString(5)); // orderno
					obj.setParam7(rs.getString(6)); // mainmenuid
					obj.setParam8(rs.getString(7)); // createdat
					obj.setParam9(rs.getString(8)); // issubmenucount
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
//			setException.setParam1("get main menu");
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
	 * Developer : Prem Gaigole Date : 2020-07-24 
	 * Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel getmainmenuassign(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from menu.selectmenudetails('getmainmenuassign', 'companyid', 'roleid', 'ownersid', 'loginid', 
//					'selectroleid', '', '', '', '',  '');

			getdev = con.prepareStatement(QueryConstants.menu_list_assign);
			getdev.setString(1, "getmainmenuassign");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam4());//selectroleid
			rscount = getdev.executeQuery();
			System.out.println("Query == > " + getdev.toString());

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);

				getdev = con.prepareStatement(QueryConstants.menu_list_assign);
				getdev.setString(1, "getmainmenuassign");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam4());//selectroleid	
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					menuid, menuname, menudescription, menuurl, orderno, mainmenuid, createdat, submenucount

				
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // menuid
					obj.setParam3(rs.getString(2)); // menuname
					obj.setParam4(rs.getString(3)); // menudescription
					obj.setParam5(rs.getString(4)); // menuurl
					obj.setParam6(rs.getString(5)); // orderno
					obj.setParam7(rs.getString(6)); // mainmenuid
					obj.setParam8(rs.getString(7)); // createdat
					obj.setParam9(rs.getString(8)); // submenucount
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
					obj.setParam51(rs.getString(50));
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
//			setException.setParam1("get main menu");
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
	 * Developer : Prem Gaigole Date : 2020-07-24 
	 * Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel finalmainmenu(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from menu.selectmenudetails('final_mainmenu', 'ownersid', 'roleid', '', '', '', '', '', '', '',  '');

			getdev = con.prepareStatement(QueryConstants.final_menu_list);
			getdev.setString(1, "final_mainmenu");		
			getdev.setString(2, spltstr[3]);// roleid
			getdev.setString(3, spltstr[4]);// owerid
			rscount = getdev.executeQuery();
			System.out.println("Query == > " + getdev.toString());

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);

				getdev.setString(1, "final_mainmenu");		
				getdev.setString(2, spltstr[3]);// roleid
				getdev.setString(3, spltstr[4]);// owerid
				
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//				m.menuid, m.menuname, m.menudescription, m.menuurl, m.orderno, 	mainmenuid, createdat, submenucount


				
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // m.menuid
					obj.setParam3(rs.getString(2)); // m.menuname
					obj.setParam4(rs.getString(3)); // m.menudescription
					obj.setParam5(rs.getString(4)); // m.menuurl
					obj.setParam6(rs.getString(5)); // m.orderno
					obj.setParam7(rs.getString(6)); // mainmenuid
					obj.setParam8(rs.getString(7)); // createdat
					obj.setParam9(rs.getString(8)); // submenucount
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
					obj.setParam51(rs.getString(50));
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
//			setException.setParam1("final_mainmenu");
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
	 * Developer : Prem Gaigole Date : 2020-07-24 
	 * Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel finalallassignmenu(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from menu.selectmenudetails('final_allassignmenu', 'ownersid', 'roleid', '', '', '', '', '', '', '',  '');

			getdev = con.prepareStatement(QueryConstants.final_menu_list);
			getdev.setString(1, "final_allassignmenu");		
			getdev.setString(2, spltstr[3]);// roleid
			getdev.setString(3, spltstr[4]);// owerid
			rscount = getdev.executeQuery();
			System.out.println("Query == > " + getdev.toString());

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);

				getdev.setString(1, "final_mainmenu");		
				getdev.setString(2, spltstr[3]);// roleid
				getdev.setString(3, spltstr[4]);// owerid
				
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					m.menuid, m.menuname, m.menudescription, m.menuurl, m.orderno,
//					CASE WHEN m.mainmenuid = 0 THEN ''Main'' ELSE ''Sub'' end as menutype, mainmenuid, issubmenucount, checkedstatus


				
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // m.menuid
					obj.setParam3(rs.getString(2)); // m.menuname
					obj.setParam4(rs.getString(3)); // m.menudescription
					obj.setParam5(rs.getString(4)); // m.menuurl
					obj.setParam6(rs.getString(5)); // m.orderno
					obj.setParam7(rs.getString(6)); // menutype
					obj.setParam8(rs.getString(7)); // mainmenuid
					obj.setParam9(rs.getString(8)); // submenucount
					obj.setParam10(rs.getString(9)); //issubmenucount
					obj.setParam11(rs.getString(10)); //checkedstatus
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
//			setException.setParam1("final all assign menu");
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
	 * Developer : Prem Gaigole Date : 2020-07-24 
	 * Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel getsubmenu(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from menu.selectmenudetails('getsubmenu', 'companyid', 'roleid', 'ownersid', 'loginid', 'mainmeuid', '', '', '', '',  '');


			getdev = con.prepareStatement(QueryConstants.menu_list_assign);
			getdev.setString(1, "getsubmenu");		
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam4());//mainmenuid
			System.out.println("Query == > " + getdev.toString());
			rscount = getdev.executeQuery();
			

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);

				getdev = con.prepareStatement(QueryConstants.menu_list_assign);
				getdev.setString(1, "getsubmenu");		
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam4());//mainmenuid
				
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					menuid, menuname, menudescription, menuurl, orderno, mainmenuid, createdat,issubmenucount

				
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // menuid
					obj.setParam3(rs.getString(2)); // menuname
					obj.setParam4(rs.getString(3)); // menudescription
					obj.setParam5(rs.getString(4)); // menuurl
					obj.setParam6(rs.getString(5)); // orderno
					obj.setParam7(rs.getString(6)); // mainmenuid
					obj.setParam8(rs.getString(7)); // mainmenuid
					obj.setParam9(rs.getString(8)); // createdat
					obj.setParam10(rs.getString(9)); //issubmenucount
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
//			setException.setParam1("get sub menu");
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
	 * Developer : Prem Gaigole Date : 2020-07-24 
	 * Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel getmenudetails(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from menu.selectmenudetails('getmenudetails', 'companyid', 'roleid', 'ownersid', 'loginid', 'deviceid', '', ''
//					, '', '',  '');

			getdev = con.prepareStatement(QueryConstants.menu_list_assign);
			getdev.setString(1, "getmenudetails");		
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam4());//deviceid
			rscount = getdev.executeQuery();
			System.out.println("Query == > " + getdev.toString());

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);


				getdev = con.prepareStatement(QueryConstants.menu_list_assign);
				getdev.setString(1, "getmenudetails");		
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam4());//deviceid
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					m1.menuid, m1.menuname, m1.menudescription, m1.menuurl, m1.orderno, m1.mainmenuid, m1.createdat, 
//					 mainmenuname, issubmenucount


				
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // m.menuid
					obj.setParam3(rs.getString(2)); // m.menuname
					obj.setParam4(rs.getString(3)); // m.menudescription
					obj.setParam5(rs.getString(4)); // m.menuurl
					obj.setParam6(rs.getString(5)); // m.orderno
					obj.setParam7(rs.getString(6)); // m1.mainmenuid
					obj.setParam8(rs.getString(7)); // m1.createdat
					obj.setParam9(rs.getString(8)); // mainmenuname
					obj.setParam10(rs.getString(9)); //issubmenucount
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
//
//			ExceptionMasterUtil wrap = new ExceptionMasterUtil();
//			RequestInsertDataDao setException = new RequestInsertDataDao();
////				[0] ==>> loginid, [1] ==>> Username, [2] ==>> LoginName , [3]==>RoleId ,[4]==>OwnersId,[5]==>CreatedBy,[6]==>LogoFilePath
////			[7]==>ipaddress , [8]==>Browserdetails,[9]==> osdetails ,[10]==>web
//			setException.setParam1("get menu details");
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
	 * Developer : Prem Gaigole Date : 2020-07-24 
	 * Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel getmenurolewise(String[] spltstr, RequestSelectDataDao devdata) {
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
			
//			select * from menu.selectmenudetails('getmenurolewise', '', 'roleid', 'ownsersid', '', 'menuid', 'login_roleid', 
//					'login_ownsersid', '', '',  '');



			getdev = con.prepareStatement(QueryConstants.menu_role_wise);
			getdev.setString(1, "getmenurolewise");		
			getdev.setString(2, ""); 
			getdev.setString(3,devdata.getParam1()); // Role Id
			getdev.setString(4,devdata.getParam2()); // Owners Id
			getdev.setString(5,""); 
			getdev.setString(6,devdata.getParam3()); // Menuid
			getdev.setString(7, spltstr[3]);		// login_roleid
			getdev.setString(8, spltstr[4]);	//login_ownsersid
			System.out.println("Query == > " + getdev.toString());
			rscount = getdev.executeQuery();
			

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);

				getdev = con.prepareStatement(QueryConstants.menu_role_wise);
				getdev.setString(1, "getmenurolewise");		
				getdev.setString(2, "");
				getdev.setString(3,devdata.getParam1()); // Role Id
				getdev.setString(4,devdata.getParam2()); // Owners Id
				getdev.setString(5,""); 
				getdev.setString(6,devdata.getParam3()); // Menuid
				getdev.setString(7, spltstr[3]);		// login_roleid
				getdev.setString(8, spltstr[4]);	//login_ownsersid
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					m.menuid, m.menuname, m.menudescription, m.menuurl, 
//					(CASE WHEN mas2.orderno > 0 THEN mas2.orderno ELSE  (CASE WHEN mas.orderno > 0 THEN mas.orderno ELSE m.orderno END) END), 
//					checkedstatus, issubmenucount,
//					mas2.orderno,mas.orderno,m.orderno

					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // m.menuid
					obj.setParam3(rs.getString(2)); // m.menuname
					obj.setParam4(rs.getString(3)); // m.menudescription
					obj.setParam5(rs.getString(4)); // m.menuurl
					obj.setParam6(rs.getString(5)); // m.orderno
					obj.setParam7(rs.getString(6)); // checkedstatus
					obj.setParam8(rs.getString(7)); // issubmenucount
					obj.setParam9(rs.getString(8)); // mas2.orderno
					obj.setParam10(rs.getString(9)); //mas.orderno
					obj.setParam11(rs.getString(10)); //m.orderno
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
//			setException.setParam1("getmenu role wise");
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
	 * Developer : Prem Gaigole Date : 2020-07-24 
	 * Description : Select framework
	 * Manager Update Date : Updation
	 * 
	 **/

	public APIResponseModel getallassignmenuuser(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from menu.selectmenudetails('getallassignmenu_user', 'ownersid', 'roleid', '', '', '', '', '', '', '',  '');

			getdev = con.prepareStatement(QueryConstants.final_menu_list);
			getdev.setString(1, "getallassignmenu_user");		
			getdev.setString(2, spltstr[4]);// ownersid
			getdev.setString(3, spltstr[3]);// roleid
			System.out.println("Query == > " + getdev.toString());
			rscount = getdev.executeQuery();
			

			int rownumber = 0;
			if (rscount.next()) {

				listData.setParam1(rscount.getString(1));
				rownumber = Integer.parseInt(searchByOffsetSplit[1]);

				getdev = con.prepareStatement(QueryConstants.final_menu_list);
				getdev.setString(1, "getallassignmenu_user");		
				getdev.setString(2, spltstr[4]);// ownersid
				getdev.setString(3, spltstr[3]);// roleid
				
				System.out.println("Count " + getdev);//
				ResultSet rs = getdev.executeQuery();

				while (rs.next()) {

//					m.menuid, m.menuname, m.menudescription, m.menuurl, m.orderno,
//					CASE WHEN m.mainmenuid = 0 THEN ''Main'' ELSE ''Sub'' end as menutype, m.mainmenuid, issubmenucount

	
					obj = new RequestSelectDataDao();
					obj.setParam1(Integer.toString(rownumber++)); // Srno
					obj.setParam2(rs.getString(1)); // m.menuid
					obj.setParam3(rs.getString(2)); // m.menuname
					obj.setParam4(rs.getString(3)); // m.menudescription
					obj.setParam5(rs.getString(4)); // m.menuurl
					obj.setParam6(rs.getString(5)); // m.orderno
					obj.setParam7(rs.getString(6)); // menutype
					obj.setParam8(rs.getString(7)); // mainmenuid
					obj.setParam9(rs.getString(8)); // issubmenucount
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
					obj.setParam51(rs.getString(50));
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
//			setException.setParam1("getall assign menu user");
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
