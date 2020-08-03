package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.app.connection.APIResponseModel;
import com.app.connection.DBConnection;
import com.app.main.dao.ListDataResponse;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.dao.VehicleData;
import com.app.main.dao.VehicleInstallationData;
import com.app.main.model.DeviceDetailsResponse;
import com.app.main.model.VehicleDetailsResponse;
import com.app.queryconstants.PaginationControl;
import com.app.queryconstants.QueryConstants;

public class VehicleDetailsManager {

	public VehicleDetailsResponse vehicle(String[] spltstr, VehicleData vehdata) {
		VehicleDetailsResponse vehdet = new VehicleDetailsResponse();

		List<VehicleDetailsResponse> data = new ArrayList<VehicleDetailsResponse>();

		long start = 0, end = 0;
		String seraccbyoffset = "";
		try {
			if (vehdata.getPageno().equals("NA")) {
				seraccbyoffset = "";
			} else if (vehdata.getItemsPerPage().equals("NA")) {
				seraccbyoffset = "";
			} else

			if (Integer.parseInt(vehdata.getPageno()) != 1) {
				start = Integer.parseInt(vehdata.getItemsPerPage()) * (Integer.parseInt(vehdata.getPageno()) - 1);
				end = start + Integer.parseInt(vehdata.getItemsPerPage());
				seraccbyoffset = " offset " + start + " limit " + vehdata.getItemsPerPage() + "";
				start++;
			} else {
				start = 0;
				end = start + Integer.parseInt(vehdata.getItemsPerPage());
				seraccbyoffset = " offset " + start + " limit " + vehdata.getItemsPerPage() + "  ";
				start++;
			}

		} catch (Exception e) {
			System.err.println("e ----  " + e);
		}

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getveh;
		try {
			getveh = con.prepareStatement(QueryConstants.vehicledetailsquery);
			getveh.setString(1, "vehicledetails");
			getveh.setString(2, spltstr[4]);
			getveh.setString(3, spltstr[3]);
			getveh.setString(4, spltstr[4]);
			getveh.setString(5, spltstr[0]);
			getveh.setString(6, vehdata.getSearchby());
			getveh.setString(7, seraccbyoffset);
			getveh.setString(8, "");

			System.out.println("Vehicle Details Query ==>> " + getveh.toString());

			ResultSet rs = getveh.executeQuery();

//			mv.vehicleid, vehiclemakeid, vehiclemodelid, vehicletypeid, vehicleregno, vehiclechasisno, vehicleengineno, 
//			vehicleregdate, vehiclereceiptno, 
//			vehiclemfgyear, mv.customerid, vehiclestatecode, vehiclertoid, vehicletype,

			while (rs.next()) {
				VehicleDetailsResponse obj = new VehicleDetailsResponse();
				obj.setVehicleid(rs.getString(1));
				obj.setVehiclemakeid(rs.getString(2));
				obj.setVehiclemodelid(rs.getString(3));
				obj.setVehicletypeid(rs.getString(4));

				obj.setVehicleregno(rs.getString(5));
				obj.setVehiclechasisno(rs.getString(6));
				obj.setVehicleengineno(rs.getString(7));
				obj.setVehicleregdate(rs.getString(8));
				obj.setVehiclereceiptno(rs.getString(9));

				obj.setVehiclemfgyear(rs.getString(10));
				obj.setCustomerid(rs.getString(11));
				obj.setVehiclestatecode(rs.getString(12));
				obj.setVehiclertoid(rs.getString(13));
				obj.setVehicletype(rs.getString(14));

				obj.setVehiclemakename(rs.getString(15));
				obj.setVehiclemodelname(rs.getString(16));
				obj.setVehicleclass(rs.getString(17));
				obj.setDeviceimeino(rs.getString(18));
				obj.setDeviceuniqueno(rs.getString(19));
				obj.setDeviceiccidno(rs.getString(20));
				obj.setDistributorname(rs.getString(21));
				obj.setDealername(rs.getString(22));
				obj.setDevmodelname(rs.getString(23));

				obj.setCustomername(rs.getString(24));
				obj.setCustomermobileno(rs.getString(25));
				obj.setCustomeraddress(rs.getString(26));
				obj.setCustomercity(rs.getString(27));
				obj.setCustomerstate(rs.getString(28));
				obj.setDealercontactmobile1(rs.getString(29));

				obj.setDevmodelid(rs.getString(30));
				obj.setDevmodelcode(rs.getString(31));
				// System.out.println("Device Details Query Count ==>>
				// "+obj.getVehiclemakename());

				data.add(obj);
			}
			int count = 0;
			ResultSet rs4 = null;

			try {
				PreparedStatement getvehcount;
				getvehcount = con.prepareStatement(QueryConstants.vehicledetailsquery);
				getvehcount.setString(1, "vehicledetails");
				getvehcount.setString(2, spltstr[4]);// company id send owners id
				getvehcount.setString(3, spltstr[3]);// roleid
				getvehcount.setString(4, spltstr[4]);// ownersid
				getvehcount.setString(5, spltstr[0]);// loginid
				getvehcount.setString(6, vehdata.getSearchby());// search by
				getvehcount.setString(7, "");
				getvehcount.setString(8, "Count");

				// System.out.println("Device Details Query Count ==>>
				// "+getdevcount.toString());

				rs4 = getvehcount.executeQuery();

				while (rs4.next()) {

					count = rs4.getInt(1);
				}

			} catch (Exception e) {
				System.out.println("Error: " + e);
			}

			vehdet = new VehicleDetailsResponse();
			vehdet.setData(data);
			vehdet.setTotal(count);

			vehdet.setResponse("Successfully Saved!");

		} catch (Exception e) {

			System.out.println("Vehicle Details Exception ==>> " + e);

		} finally {
			DBConnection.getCloseConnection(con);
		}

		// return data;
		return vehdet;

	}
	
	public APIResponseModel Livevehiclestatus(String[] spltstr, RequestSelectDataDao devdata) {
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
//			select * from masters.selectcompliancestatus('Livevehiclestatus', 'companyid', 'roleid', 'ownersid', 'loginid', 
//					'searchby', 'seraccbyoffset', 'Count', 'searchtype', 'values',  '');

			
			getdev = con.prepareStatement(QueryConstants.selectcompliancestatuses);
			getdev.setString(1, "Livevehiclestatus");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// search by
			getdev.setString(7, searchByOffsetSplit[0]);//offset
			getdev.setString(8, "Count");
			getdev.setString(9, devdata.getParam4());//searchtype
			getdev.setString(10, devdata.getParam5());//values
			
			rscount = getdev.executeQuery();
			System.out.println("  Query == > " + getdev.toString());



			int rownumber=0;
			if(rscount.next())
			{
				
				listData.setParam1(rscount.getString(1));
				rownumber=Integer.parseInt(searchByOffsetSplit[1]);
				
				getdev = con.prepareStatement(QueryConstants.selectcompliancestatuses);
				getdev.setString(1, "Livevehiclestatus");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam1());// search by
				getdev.setString(7, searchByOffsetSplit[0]);//offset
				getdev.setString(8, "");
				getdev.setString(9, devdata.getParam4());//searchtype
				getdev.setString(10, devdata.getParam5());//values
				
				System.out.println("getdev "+getdev);
				
					 ResultSet rs=getdev.executeQuery();
			
               
			while (rs.next()) {
				
//				md.deviceid, md.devicemakeid, md.deviceimeino, md.deviceuniqueno, md.deviceiccidno, 
//				vdr.vendorid, vdr.vendorname, mv.vehicleid, (CASE WHEN length(mv.vahanregno) > 0 THEN mv.vahanregno else 
//				(CASE  WHEN length(mv.vehicleregno) > 0 then mv.vehicleregno else mv.vehiclechasisno end) end)as vehicleregno,
//				mv.vehiclechasisno, CASE WHEN vehicleactivated = true then TO_CHAR(mv.vehicleregdate::date, ''dd-Mon-yyyy'') else ''---'' end as vehicleregdate, 
//				TO_CHAR(mv.vehiclecreatedat::date, ''dd-Mon-yyyy'') as installeddate,
//				vehiclestatecode, (cityrtocode || ''--'' || rtoname) as rtoname,  
//				CASE WHEN vehicleactivated = true then ''Activated'' else ''Pending OTP'' end as activationstatus,
//				mv.customerid, mc.customername, mc.customermobileno, (select statename from masters.tblstatemaster where statertocode = mv.vehiclestatecode) as statename,
//				CASE WHEN cmd.packetdate::date = now()::date then ''Connected'' else (CASE WHEN cmd.packetdate::date is null then ''Never Connected'' else ''Not Connected'' end) end
//				as pollingstatus, (cmd.packetdate+cmd.packettime) as pollingdatatime, cmd.packetdate, cmd.packettime, cmd.latitude, cmd.longitude, 
//				cmd.vehiclespeed::numeric(8, 0), (CASE WHEN cmd.gpsstatus = ''1'' THEN ''Valid'' ELSE ''Invalid'' END) as gpsstatus,
//				(CASE WHEN cmd.ignumber = 1 THEN ''ON'' ELSE ''OFF'' END) as ignitionstatus, 
//				(CASE WHEN (emr.packetdate+emr.packettime) IS NULL THEN ''2020-05-02 13:30:00'' ELSE (emr.packetdate+emr.packettime)::text END) as emergencydatatime,
//				replace(replace(cmd.clientkey,''/'',''''),''10.0.0.4:'' || cmd.port || ''-'','''') AS clientkey, cmd.networkoperator, 
//				cmd.nmr, cmd.mcc, cmd.mnc, cmd.lac, cmd.cellid, dim.countrycode, dim.country, cmd.port, cmd.firmwareversion, cmd.altitude, cmd.distancekm, cmd.batteryvoltage,
//				masters.fngpsstatus(''pollingstatus'', (cmd.packetdate+cmd.packettime)::text, (cmd.vehiclespeed::numeric(8, 0))::text, cmd.ignumber::text, cmd.gpsstatus::text, '''') as vehiclestatus,
//				dmod.devmodelname, dmod.devmodelcode, ca.certauthid, ca.certauthcode, ca.certauthagency, case when cmd.vehiclespeed > 60  then ''Yes'' else ''NA'' end as overpeed,
//				cmd.latitudedirection, cmd.longitudedirection


				obj = new RequestSelectDataDao();
				obj.setParam1(Integer.toString(rownumber++)); // Srno
				obj.setParam2(rs.getString(1)); // md.deviceid, 
				obj.setParam3(rs.getString(2)); // devicemakeid
				obj.setParam4(rs.getString(3)); // deviceimeino
				obj.setParam5(rs.getString(4)); // deviceuniqueno
				obj.setParam6(rs.getString(5)); // deviceiccidno
				obj.setParam7(rs.getString(6)); // vendorid
				obj.setParam8(rs.getString(7)); // vendorname
				obj.setParam9(rs.getString(8)); // vehicleid
				obj.setParam10(rs.getString(9)); // vehicleregno
				obj.setParam11(rs.getString(10)); // vehiclechasisno
				obj.setParam12(rs.getString(11)); // vehicleregdate
				obj.setParam13(rs.getString(12)); // installeddate
				obj.setParam14(rs.getString(13)); // vehiclestatecode
				obj.setParam15(rs.getString(14)); // rtoname
				obj.setParam16(rs.getString(15)); // activationstatus
				obj.setParam17(rs.getString(16)); //mv.customerid
				obj.setParam18(rs.getString(17));	//mc.customername
				obj.setParam19(rs.getString(18));//mc.customermobileno
				obj.setParam20(rs.getString(19));//statename
				obj.setParam21(rs.getString(20));//pollingstatus
				obj.setParam22(rs.getString(21));//pollingdatatime
				obj.setParam23(rs.getString(22));//cmd.packetdate
				obj.setParam24(rs.getString(23));//cmd.packettime
				obj.setParam25(rs.getString(24));//cmd.latitude
				obj.setParam26(rs.getString(25));// cmd.longitude
				obj.setParam27(rs.getString(26));//gpsstatus
				obj.setParam28(rs.getString(27));//ignitionstatus
				obj.setParam29(rs.getString(28));//emergencydatatime
				obj.setParam30(rs.getString(29));//clientkey
				obj.setParam31(rs.getString(30));//cmd.networkoperator
				obj.setParam32(rs.getString(31));//cmd.nmr
				obj.setParam33(rs.getString(32));//cmd.mcc
				obj.setParam34(rs.getString(33));//cmd.mnc
				obj.setParam35(rs.getString(34));//cmd.lac
				obj.setParam36(rs.getString(35));//cmd.cellid
				obj.setParam37(rs.getString(36));//dim.countrycode
				obj.setParam38(rs.getString(37));//dim.country
				obj.setParam39(rs.getString(38));//cmd.port
				obj.setParam40(rs.getString(39));//cmd.firmwareversion
				obj.setParam41(rs.getString(40));//cmd.altitude
				obj.setParam42(rs.getString(41));//cmd.distancekm
				obj.setParam43(rs.getString(42));//cmd.batteryvoltage
				obj.setParam44(rs.getString(43));//dmod.devmodelname
				obj.setParam45(rs.getString(44));//dmod.devmodelcode
				obj.setParam46(rs.getString(45));//ca.certauthid
				obj.setParam47(rs.getString(46));//ca.certauthcode
				obj.setParam48(rs.getString(47));//ca.certauthagency
				obj.setParam49(rs.getString(48));//overpeed
				obj.setParam50(rs.getString(49));//cmd.latitudedirection
				obj.setParam51(rs.getString(50));//cmd.longitudedirection
				
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
	
	
	public APIResponseModel singlevehiclelivedata(String[] spltstr, RequestSelectDataDao devdata) {
		RequestSelectDataDao obj = null;
		List<RequestSelectDataDao> detailsdata = new ArrayList<RequestSelectDataDao>();
		ListDataResponse listData = new ListDataResponse();
		APIResponseModel apiResponseModel = new APIResponseModel();
		ResultSet rscount = null;
	

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdev = null;
		String searchByOffset = PaginationControl.getPaginationControl(devdata.getParam2(), devdata.getParam3()); // param2	// ItemPerPage
		String[] searchByOffsetSplit = searchByOffset.split("&&");

		
		try {
			
//			select * from masters.selectmapprocedure('singlevehiclelivedata', 'companyid', 'roleid', 'ownersid', 'loginid',  'searchby',
//					'seraccbyoffset', 'iscount', 'vehicleno', '',  '');

			
			getdev = con.prepareStatement(QueryConstants.SINGLE_VEHICLE_STATMENT);
			getdev.setString(1, "singlevehiclelivedata");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// owerid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getParam1());// search by
			getdev.setString(7, searchByOffsetSplit[0]);// offset
			getdev.setString(8, "Count");	
			getdev.setString(9, devdata.getParam4());//vehicleno
		
			rscount = getdev.executeQuery();
			System.out.println(" Count Query == > " + getdev.toString());
			int rownumber=0;
			if(rscount.next())
			{	
				
				listData.setParam1(rscount.getString(1));
				rownumber=Integer.parseInt(searchByOffsetSplit[1]);
				
				getdev = con.prepareStatement(QueryConstants.SINGLE_VEHICLE_STATMENT);
				getdev.setString(1, "singlevehiclelivedata");
				getdev.setString(2, spltstr[4]);// company id send owners id
				getdev.setString(3, spltstr[3]);// roleid
				getdev.setString(4, spltstr[4]);// owerid
				getdev.setString(5, spltstr[0]);// loginid
				getdev.setString(6, devdata.getParam1());// search by
				getdev.setString(7, searchByOffsetSplit[0]);//offset
				getdev.setString(8, "");//iscount
				getdev.setString(9, devdata.getParam4());//vehicleno

				System.out.println("getdev "+getdev);
				
					ResultSet  rs=getdev.executeQuery();
			
               
			while (rs.next()) {
				
//				lpp.reactorkey as recordid, datatimestamp, lpp.packettime, lpp.imeino, lpp.packetdate, lpp.packettime,lpp.latitude, 
//				lpp.longitude, lpp.vehiclespeed, 
//				lpp.gpsstatus, lpp.gpsstatus, vehicleregno,lpp.vehicledirection ,lpp.gpsstatus, lpp.ignumber, mvt.vehicleclass,
//				md.loginid,   mc.vendorname,lpp.vehicledirection ,
//				ignitioncolor, gpscolor, lpp.cellid,srt.rtoname, lpp.alertid				
				
				
				obj = new RequestSelectDataDao();
				obj.setParam1(Integer.toString(rownumber++)); // Srno
				obj.setParam2(rs.getString(1)); // recordid
				obj.setParam3(rs.getString(2)); // datatimestamp
				obj.setParam4(rs.getString(3)); // lpp.packettime
				obj.setParam5(rs.getString(4)); // lpp.imeino
				obj.setParam6(rs.getString(5)); // lpp.packetdate
				obj.setParam7(rs.getString(6)); // lpp.packettime
				obj.setParam8(rs.getString(7)); // lpp.latitude
				obj.setParam9(rs.getString(8)); // lpp.longitude
				obj.setParam10(rs.getString(9)); // lpp.vehiclespeed
				obj.setParam11(rs.getString(10)); // lpp.gpsstatus
				obj.setParam12(rs.getString(11)); // vehicleregno
				obj.setParam13(rs.getString(12)); // lpp.vehicledirection
				obj.setParam14(rs.getString(13)); // lpp.ignumber
				obj.setParam15(rs.getString(14)); // mvt.vehicleclass
				obj.setParam16(rs.getString(15)); // md.loginid
				obj.setParam17(rs.getString(16)); //mc.vendorname
				obj.setParam18(rs.getString(17));	//lpp.vehicledirection
				obj.setParam19(rs.getString(18));//ignitioncolor
				obj.setParam20(rs.getString(19));//gpscolor
				obj.setParam21(rs.getString(20));//lpp.cellid
				obj.setParam22(rs.getString(21));//srt.rtoname
				obj.setParam23(rs.getString(22));//lpp.alertid
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
			}
			else {
				apiResponseModel.setEntity("NO DATA FOUND");
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
