package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.app.connection.DBConnection;
import com.app.main.model.DashboardResponse;
import com.app.queryconstants.QueryConstants;

public class DashboardManager {
	public DashboardResponse dashboard(String[] spltstr) {
		DashboardResponse obj = new DashboardResponse();

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdash;
		try {
			getdash = con.prepareStatement(QueryConstants.dashboardcount);
			getdash.setString(1, "count");
			getdash.setString(2, spltstr[4]); // owners id
			getdash.setString(3, spltstr[3]);// role id
			getdash.setString(4, spltstr[4]); // owners id
			getdash.setString(5, spltstr[0]); // loginid id
			getdash.setString(6, ""); // device id
			System.out.println(getdash);
			ResultSet rs = getdash.executeQuery();

			while (rs.next()) {

				obj.setDistributor(rs.getString(1));
				obj.setDealer(rs.getString(2));
				obj.setCustomer(rs.getString(3));
				obj.setDevice(rs.getString(4));
				obj.setDistributordevice(rs.getString(5));
				obj.setDealerdevice(rs.getString(6));
				obj.setInstallvehicle(rs.getString(7));
				obj.setTodayinstallvehicle(rs.getString(8));
				obj.setPolling(rs.getString(9));
				obj.setNonpolling(rs.getString(10));
				obj.setWorkingdevice(rs.getString(11));
				obj.setNonworkingdevie(rs.getString(12));

			}
			obj.setResponse("Success!");
			getdash.close();
			rs.close();
		} catch (Exception e) {
			
			obj.setResponse("Error!");
			System.out.println("DASHBOARD COUNT Exception ==>> " + e);

		} finally {
			DBConnection.getCloseConnection(con);
		}

		return obj;
	}

}
