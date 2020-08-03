package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.dao.DeviceAssignDealerData;
import com.app.main.dao.DeviceData;
import com.app.main.model.DeviceAssignDealerResponse;
import com.app.queryconstants.QueryConstants;

public class DeviceAssignDealerManager {

	public DeviceAssignDealerResponse diviceassigndealer(String dealerid, String[] spltstr, List<DeviceData> data2) {
		DeviceAssignDealerResponse data = new DeviceAssignDealerResponse();

		Connection con = DBConnection.getMainConnection();

		try {
			for (int i = 0; i <= data2.size(); i++) {
				DeviceData devdata = data2.get(i);
				System.out.println("Hiii Dealer Assign");
				PreparedStatement getdevdea;
				getdevdea = con.prepareStatement(QueryConstants.deviceassigndealerquery);
				getdevdea.setString(1, "assigndevicetodealer");
				getdevdea.setString(2, devdata.getDeviceId());
				getdevdea.setString(3, dealerid);
				getdevdea.setString(4, spltstr[0]);

				ResultSet rs = getdevdea.executeQuery();

				while (rs.next()) {
					data.setResponse(rs.getString(1));
					System.out.println(rs.getString(1));
				}
			}
		} catch (Exception e) {

			data.setResponse("Error");
		} finally {
			DBConnection.getCloseConnection(con);
		}

		return data;
	}

	public String deviceUnassignFromDealer(String dealerid, String[] spltstr, List<DeviceData> data2) {
		System.out.println("Hiii Dealer UnAssign");
		String response = new String();

		Connection con = DBConnection.getMainConnection();

		try {
			for (int i = 0; i <= data2.size(); i++) {
				DeviceData devdata = data2.get(i);
				System.out.println("Hiii Dealer UnAssign");
				PreparedStatement getdevdea;
				getdevdea = con.prepareStatement(QueryConstants.unassignfromdealer);
				getdevdea.setString(1, "unassigndevicetodealer");
				getdevdea.setString(2, devdata.getDeviceId());
				getdevdea.setString(3, dealerid);
				getdevdea.setString(4, spltstr[0]);

				System.out.println("device id=" + devdata.getDeviceId());

				System.out.println("dealerid=" + dealerid);
				ResultSet rs = getdevdea.executeQuery();

				while (rs.next()) {
					response = rs.getString(1);
					System.out.println(response);
				}
			}
		} catch (Exception e) {

			response = "Error";
		} finally {
			DBConnection.getCloseConnection(con);
		}

		return response;
	}

}
