package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.dao.LiveEmergrncy;
import com.app.main.model.LiveEmergrncyResponse;
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
}
