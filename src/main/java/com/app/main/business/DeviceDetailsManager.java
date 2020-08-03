package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.app.connection.APIResponseModel;
import com.app.connection.DBConnection;
import com.app.main.dao.DeviceData;
import com.app.main.dao.ListDataResponse;
import com.app.main.dao.RequestSelectDataDao;
import com.app.main.model.DeviceDetailsResponse;
import com.app.main.model.ModelDetailsResponse;
import com.app.queryconstants.PaginationControl;
import com.app.queryconstants.QueryConstants;

public class DeviceDetailsManager {

	public DeviceDetailsResponse devicedetails(String[] spltstr, @RequestBody DeviceData devdata) {
		DeviceDetailsResponse det = new DeviceDetailsResponse();
		List<DeviceDetailsResponse> data = new ArrayList<DeviceDetailsResponse>();
		long start = 0, end = 0;
		String seraccbyoffset = "";
		try {
			if (devdata.getPageno().equals("NA")) {
				seraccbyoffset = "";
			} else if (devdata.getItemsPerPage().equals("NA")) {
				seraccbyoffset = "";
			} else

			if (Integer.parseInt(devdata.getPageno()) != 1) {
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
			System.err.println("e ----  " + e);
		}

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdev;
		try {
			getdev = con.prepareStatement(QueryConstants.devicedetailsquery);

			getdev.setString(1, "devicedetails");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// ownersid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getSearchby());// search by
			getdev.setString(7, seraccbyoffset);
			getdev.setString(8, "");
			ResultSet rs = getdev.executeQuery();
			while (rs.next()) {
				DeviceDetailsResponse obj = new DeviceDetailsResponse();
				obj.setDeviceid(rs.getString(1));
				obj.setMakeid(rs.getString(2));
				obj.setModelid(rs.getString(3));
				obj.setDeviceimeino(rs.getString(4));
				obj.setDeviceuniqueno(rs.getString(5));
				obj.setDeviceiccidno(rs.getString(6));
				obj.setMobilenumber1(rs.getString(7));
				obj.setMobilenumber2(rs.getString(8));
				obj.setNetworkid1(rs.getString(9));
				obj.setNetworkid2(rs.getString(10));
				obj.setM2mproviderid(rs.getString(11));
				obj.setM2mprovidername(rs.getString(12));
				obj.setM2mprovidercode(rs.getString(13));
				obj.setDistributorname(rs.getString(14));
				obj.setDealername(rs.getString(15));
				obj.setDevmodelname(rs.getString(16));
				
				obj.setDeviceType(rs.getString(20)); // Device Type Parameter
				
				data.add(obj);
			}

			int count = 0;
			ResultSet rs4 = null;
			try {
				PreparedStatement getdevcount;
				getdevcount = con.prepareStatement(QueryConstants.devicedetailsquerycount);
				getdevcount.setString(1, "devicedetails");
				getdevcount.setString(2, spltstr[4]);// company id send owners id
				getdevcount.setString(3, spltstr[3]);// roleid
				getdevcount.setString(4, spltstr[4]);// ownersid
				getdevcount.setString(5, spltstr[0]);// loginid
				getdevcount.setString(6, devdata.getSearchby());// search by
				getdevcount.setString(7, "");
				getdevcount.setString(8, "Count");

				System.out.println("Device Details Query Count ==>> " + getdevcount.toString());

				rs4 = getdevcount.executeQuery();
				while (rs4.next()) {
					count = rs4.getInt(1);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			det = new DeviceDetailsResponse();
			det.setData(data);
			det.setTotal(count);
			det.setResponse("Successfully Saved!");
		} catch (Exception e) {
			System.out.println("Device Details Exception ==>> " + e);
		} finally {
			DBConnection.getCloseConnection(con);
		}
		return det;
	}
	}
