package com.app.queryconstants;

/* Developer    : Pallavi Dhage
 * Created Date : 2019-07-26 
 * Updated Date : 
 * Description  : All the Queries 
 *  */

public class QueryConstants {
	public static final String roll_list = "select * from menu.selectmenudetails(?,?,?,?,'','','','','','',''); ";
	public static final String DELETE_PORT="select * from masters.insertprocedure(?,?,?,?,?,'','','','','','','','','','','','','','','','','','','','','','', '','', '', '', '','', '', '', '','', '', '', '', '','', '', '', '','', '', '', '','','') ";
	public static final String UNASSIGNPORTTOVENDER="select * from masters.insertprocedure(?,?,?,?,'','','','','','','','','','','','','','','','','','','','','','','', '','', '', '', '','', '', '', '','', '', '', '', '','', '', '', '','', '', '', '','','') ";
	
	public static String selectportassign ="select * from masters.selectmasterdetails(?,?,?,?, ?, ?, ?, ?, ?, '', '');";
	public static String selectdatadetails ="select * from masters.selectmasterdetails(?,?,?,?, ?, ?, ?, ?, ?, '', '');";
	
	//insert port master and portAssign
	public static String insertport=" select * from masters.insertprocedure(?,?,?,?,?,?,'','','','','','','','','','','','','','','','','','','','','', '','', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','') ";
	public static String assignvendor=" select * from masters.insertprocedure(?,?,?,?,'','','','','','','','','','','','','','','','','','','','','','','', '','', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','') ";
	
	public static String  selectcompliancestatuses="select * from masters.selectcompliancestatus(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,'');";
	public static final String SINGLE_VEHICLE_STATMENT = "select * from masters.selectmapprocedure(?,?,?,?,?,?,?,?,?,'',''); ";
	public static final String HISTORY_STATMENT = "select * from masters.selectmapprocedure(?,?,?,?,?,?,?,?,?,?,?); ";
	public static String customerdashboardcount=" select * from masters.selectdashboard(?, ?, ?, ?, ?, '', '', '', '', '',  '') ";
	public static String selectcompliancestatus="select * from masters.selectcompliancestatus(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?);";
	public static final String OVERSPEED_STATMENT = "select * from masters.reportproceduer(?,?,?,?,?,?,?,?,?,?,?,?,'','','', '','', '', '', '',''); ";
	
	// public static final String OVERSPEED_STATMENT = "select * from masters.reportproceduer(?,?,?,?,?,?,?,?,?,?,?,?,'','','', '','', '', '', '',''); "; prem
	
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

	public static String unassignfromdealer="select * from masters.insertdeviceprocedure(?,?,?,?,'', '',  '', '', '', '','', '', '',  '', '','', '', '', '',  '', '', '', '','', '', '', '', '', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','', '')";
	
	public static String unassignfromdistributor="select * from masters.insertdeviceprocedure(?,?,?,?,'', '',  '', '', '', '','', '', '',  '', '','', '', '', '',  '', '', '', '','', '', '', '', '', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', '','','', '')";

	public static final String insert_alert_confi = "select *  from masters.inserteventprocedure(?,?,?,?,?,?,?,?,?,?,?,?,'','','', '','', '', '', '',''); ";

	public static final String alert_configure_details = "select *  from masters.selecteventmanagement(?,?,?,?,?,?,?,?,?,'','','','','','',''); ";
	public static final String reportdetails = "select *  from masters.selecteventmanagement(?,?,?,?,?,?,?,?,'','','','','','','',''); ";
	public static final String menu_list = "select * from menu.selectmenudetails(?,?,?,?,?,'','','','','',''); ";
	public static final String insert_main_menu="select * from menu.insertmenumanagement(?,?,?,?,?,?,?,'','','','','','','','','','','','','','') ";
	public static final String assign_menu_role_wise="select * from menu.insertmenumanagement(?,?,?,?,?,?,'','','','','','','','','','','','','','','') ";


	public static final String menu_list_assign = "select * from menu.selectmenudetails(?,?,?,?,?,?,'','','','',''); ";
	public static final String final_menu_list = "select * from menu.selectmenudetails(?,?,?,'','','','','','','',''); ";
	public static final String menu_role_wise = "select * from menu.selectmenudetails(?,?,?,?,?,?,?,?,'','',''); ";
	
	//================================================== User Management ==========================================================================
	public static final String insert_user = "select * from masters.insertprocedure(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,'','','','','','','','','','','', '','', '', '', '','', '', '', '','', '', '', '', '','', '', '', '','', '', '', '','','') ";
	public static final String update_user = "select * from masters.insertprocedure(?,?,?,?,?,?,?,?,?,?,?,?,?,?,'','','','','','','','','','','','','', '','', '', '', '','', '', '', '','', '', '', '', '','', '', '', '','', '', '', '','','') ";
	public static final String delete_user = "select * from masters.insertprocedure(?,?,?,?,'','','','','','','','','','','','','','','','','','','','','','','', '','', '', '', '','', '', '', '','', '', '', '', '','', '', '', '','', '', '', '','','') ";
	public static final String user_details = "select * from masters.selectmasterdetails(?,?,?,?, ?, ?, ?, ?, ?, '', '');";
	
	//================================================== Map APIS ===========================================================================
	public static String livevehicle="select * from masters.selectmapprocedure(?, ?, ?, ?, ?,  ?, ?, ?, ?, '',  '');";
	public static String historyvehicle="select * from masters.selectmapprocedure(?,?, ?, ?, ?, '', '', '', '', '', '')";


}