package com.app.queryconstants;

/* Developer    : Pallavi Dhage
 * Created Date : 2019-07-26 
 * Updated Date : 
 * Description  : All the Queries 
 *  */

public class QueryConstants {
	
	
	/* ********************************************Select Login Procedure ********************************************/
																							 // 9
	public static String loginquery="select * from masters.selectlogin(?, ?, ?, ?, ?, ?, ?, '', '', '',  '');";
	// public static String getImageQry="select logofile from masters.tblloginmaster where loginflag = 0 and loginid = ? ";
	
	
	/* ********************************************Select Dashboard Procedure ********************************************/
	
	public static String dashboardcount=" select * from masters.selectdashboard(?, ?, ?, ?, ?, ?, '', '', '', '',  '') ";
	
	/* ********************************************Select Master Details Procedure ********************************************/
	
	public static String selectdealerdetails ="select * from masters.selectmasterdetails(?,?, ?,?, ?, '', '', '', '', '',  '');";
	public static String selectdistributordetails ="select * from masters.selectmasterdetails(?, ?, ?, ?, ?, '', '', '', '', '', '');";
	public static String vehicledetailsquery=" select * from masters.selectdevicedetails(?, ?, ?, ?, ?, ?, ?, ?, '', '',  '');";
	public static String modeldetailsquery="select * from masters.selectmasterdetails(?, ?, ?, ?, ?, ?, '', '', '', '',  '');";
	public static String devicedetailsquery="select * from masters.selectdevicedetails(?, ?, ?, ?, ?, ?, ?, ?, '', '',  '');";
	public static String devicedetailsquerycount="select * from masters.selectdevicedetails(?, ?, ?, ?, ?, ?, ?, ?, '', '',  '');";
	public static String devicetypequery="select * from masters.selectmasterdetails(?, ?, ?, ?, ?, ?, ?, ?, '', '','' )";
	
	/* ********************************************Select Master Insert Procedure ********************************************/
	public static String inserdealer=" select * from masters.insertprocedure(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?,?, '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','') ";
	public static String distributorinsertquery=" select * from masters.insertprocedure(?,  ?,  ?, ?, ?, ?,  ?,  ?, ?, ?, ?, ?, ?, ?,  ?, ?,?, ?, ?, ?,  ?, ?, ?, ?,?, ?, ?, ?,?, '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','');";
	public static String modelinsertquery="select * from masters.insertprocedure(?, ?,  ?, ?, ?, ?,  ?,  ?, ?, ?, ?,?, ?, ?,  ?, ?,?, ?, '', '',  '', '', '', '','', '', '', '','', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','');";

	
	/* ********************************************Select Master Device  Insert Procedure ********************************************/
	public static String insertdevice= " select * from masters.insertdeviceprocedure("
			+ "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "  	// 10 question marks
			+ "?, ?, ?, ?, ?, ?, ?, ?, '',  '', "	// 8 question marks
			+ "'', '', '','', '', '', '', '', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','', ''); ";
	
	public static String installVehicle="select * from masters.insertdeviceprocedure(?,?,?,?,?,?,?,?,?,?,?,?, ?, ?,?,?, ?,?,?,?,?, '', '','', '', '', '', '', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','', ''); ";
	
	public static String devicetypdeletequery="select * from masters.insertprocedure("
			+ "?, ?, ?, ?, '', '',  '',  '', '', '', "
			+ "'', '', '', '', '', '', '', '', '', '', "
			+ "'', '', '', '', '', '', '', '', '', '', "
			+ "'', '', '', '', '', '', '', '', '', '', "
			+ "'', '', '', '', '', '', '', '', '', '', '');"; 
	
	
	/* ********************************************Select Details List  Procedure ********************************************/
	public static String selectstate=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String selectcity=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, ?, '', '', '', '',  '') ";
	public static String selectmodellist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String selectnetworklist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String selectvendorlist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	
	
	public static String selectvendorlistNew= "select * from masters.selectmasterdetails(?, ?, ?, ?, ?, ?, '', '', '', '',  '')";
	public static String selectdealerlist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String selectcertauthlist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String m2mproviderlist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String selectdistributorlist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String rtolist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, ?, '', '', '', '',  '') ";
	public static String devicelist_Assign_vehicle=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String vehiclemakelist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String vehiclemodellist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, ?, '', '', '', '',  '') ";
	public static String customerlist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String vehicleclasslist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String documentlist=" select * from masters.selectlistdetails(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";

	
	
	public static String deviceassigndealerquery="select * from masters.insertdeviceprocedure(?,  ?,  ?, ?, '', '',  '', '', '', '','', '', '',  '', '','', '', '', '',  '', '', '', '','', '', '', '', '', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','', '');";
	public static String deviceassigndistriquery="select * from masters.insertdeviceprocedure(?,  ?,  ?, ?, '', '',  '', '', '', '','', '', '',  '', '','', '', '', '',  '', '', '', '','', '', '', '', '', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','', '');";
	public static String insertcustomer="select * from masters.insertprocedure(?,  ?, ?, ?, ?, ?,  ?,  ?, ?, ?, ?,?, ?, ?,  ?, ?,'', '', '', '',  '', '', '', '','', '', '', '','', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','');";
	public static String getcustomerdetails="select * from masters.selectmasterdetails(?, ?, ?, ?, ?, ?, ?, ?, '', '',  '');";
	
	
	public static String manufacturerinsertquery = " select * from masters.insertprocedure("+
			"			?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + 	// 10 question marks
			"			?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + 	// 10 question marks
			"			?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +	// 10 question marks
			" 			?, ?, ?, ?, ?, ?, " + 				// 06 question marks
			"			'', '', '', '', '', '', '', '', '', '', '', '', '', '', '');";  	// total 51 parameters

	public static String manufacturerdetailsquery="select * from masters.selectmasterdetails(?, ?, ?, ?, ?, ?, ?, ?, '', '',  '');";

	public static String manufacturerDetailUpdate = " select * from masters.insertprocedure("+
			"			?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + 	// 10 question marks
			"			?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " + 	// 10 question marks
			"			?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +	// 10 question marks
			" 			?, ?, ?, ?, ?, ?, " + 				// 06 question marks
			"			'', '', '', '', '', '', '', '', '', '', '', '', '', '', '');";  	// total 51 parameters
	
	public static String manufacturerDetailDelete = "select * from masters.insertprocedure(" + 
			"			?, ?, ?, ?, '', '', '', '', '', '', " + 		// 04 question marks
			"			'', '', '', '', '', '',  '', '', '', ''," + 	// 0 question marks
			"			'', '', '', '', '', '',  '', '', '', ''," + 	// 0 question marks
			"			'', '', '', '', '', '',  '', '', '', ''," + 	// 0 question marks
			"			'', '', '', '', '', '', '', '', '', '', '');";  // total 51 parameters			
	
	public static String UPDATE_IMAGE_FILE = "update masters.tblloginmaster set logofilepath = ?, logofile = ? where loginroleid = 11 and " +
			" loginownersid = (SELECT vendorid FROM masters.tblvendormaster WHERE mfgcode = ?)";
	
	public static final String GET_IMAGE = "SELECT LOGOFILE FROM MASTERS.TBLLOGINMASTER WHERE LOGINID = 66";
	
	
	public static String devicetypemasterquery="select * from masters.insertprocedure("
			+ "?, ?, ?, ?, ?, '',  '',  '', '', '', "
			+ "'', '', '', '', '', '', '', '', '', '', "
			+ "'', '', '', '', '', '', '', '', '', '', "
			+ "'', '', '', '', '', '', '', '', '', '', "
			+ "'', '', '', '', '', '', '', '', '', '', '');"; // total 51

	public static String alertprocedure="select * from masters.alertproceduer(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, '','','','','','','','','','');";
	
}
