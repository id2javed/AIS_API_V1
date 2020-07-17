package com.app.main.business;

/*developer :pritam laxane*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.connection.DBConnection;
import com.app.main.dao.DeviceData;
import com.app.main.dao.DeviceDataType;
import com.app.main.dao.DeviceType;
import com.app.main.dao.ManufacturerData;
import com.app.main.model.DeviceResponse;
import com.app.main.model.DeviceTypeResponse;
import com.app.main.model.ManufacturerResponse;
import com.app.queryconstants.QueryConstants;

@Service
public class DeviceTypeMasterManager {
	public String[] insertDeviceType(DeviceDataType deviceDataType, String[] spltstr) {
		// TODO Auto-generated method stub
		String[] response = null;
		Connection con = DBConnection.getMainConnection();
		PreparedStatement insertDeviceType = null;
		ResultSet deviceRS = null;

//		select * from masters.insertprocedure('insertdevicetype',  'devicetypeid',  
//				'devicetypename', 'loginid', 'remarks', '',  '',  '', '', '', '', '', 
//				'', '',  '', '','', '', '', '',  '', '', '', '','', '', '', '','', '', 
//				'', '','', '', '', '','',   '', '', '', '','', '', '', '','', '', '', 
//				'','','');

		try {
			insertDeviceType = con.prepareStatement(QueryConstants.devicetypemasterquery);

			insertDeviceType.setString(1, "insertdevicetype"); // 'insertdevicetype'
			insertDeviceType.setString(2, "0"); //
			insertDeviceType.setString(3, deviceDataType.getDevicetypename());
			insertDeviceType.setString(4, spltstr[0]);
			insertDeviceType.setString(5, deviceDataType.getRemarks());

			System.err.println(insertDeviceType.toString());
			deviceRS = insertDeviceType.executeQuery();
			while (deviceRS.next()) {
				deviceRS.getString(1);
				response = deviceRS.getString(1).split("#");

			}
			insertDeviceType.close();
			deviceRS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.getCloseConnection(con);
		}

		return response;
	}

	public DeviceTypeResponse devicetypedetails(String[] spltstr, DeviceType devdata) {
		DeviceTypeResponse det = new DeviceTypeResponse();
		List<DeviceTypeResponse> data = new ArrayList<DeviceTypeResponse>();
		long start = 0, end = 0;
		String seraccbyoffset = null;
		ResultSet rs = null;

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getdev = null;
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
			getdev = con.prepareStatement(QueryConstants.devicetypequery);

			getdev.setString(1, "devicetypedetails");
			getdev.setString(2, spltstr[4]);// company id send owners id
			getdev.setString(3, spltstr[3]);// roleid
			getdev.setString(4, spltstr[4]);// ownersid
			getdev.setString(5, spltstr[0]);// loginid
			getdev.setString(6, devdata.getSearchby());// search by
			getdev.setString(7, seraccbyoffset);
			getdev.setString(8, "");
			System.out.println("query   " + getdev.toString());

			rs = getdev.executeQuery();

//		devicetypeid, devicetypename, remarks, loginid, createdat, flag

			while (rs.next()) {

				DeviceTypeResponse obj = new DeviceTypeResponse();
				obj.setDevicetypeid(rs.getString(1));
				obj.setDevicetypename(rs.getString(2));
				obj.setRemarks(rs.getString(3));
				obj.setLoginid(rs.getString(4));
				obj.setCreatedat(rs.getString(5));
				obj.setFlag(rs.getString(6));

				data.add(obj);
			}

			int count = 0;

			try {

//			select * from masters.selectmasterdetails('devicetypedetails', 'companyid', 'roleid', 'ownersid', 'loginid',
//			'searchby', 'seraccbyoffset',
//			'iscount', '', '',  '');

				PreparedStatement getdevcount = null;
				;
				getdevcount = con.prepareStatement(QueryConstants.devicetypequery);
				getdevcount.setString(1, "devicetypedetails");
				getdevcount.setString(2, spltstr[4]);// company id send owners id
				getdevcount.setString(3, spltstr[3]);// roleid
				getdevcount.setString(4, spltstr[4]);// ownersid
				getdevcount.setString(5, spltstr[0]);// loginid
				getdevcount.setString(6, "");// search by
				getdevcount.setString(7, "");
				getdevcount.setString(8, "Count");

				System.out.println("Device type  Details Query Count ==>> " + getdevcount.toString());

				rs = getdevcount.executeQuery();
				if (rs.next()) {
					count = Integer.parseInt(rs.getString(1));
				}

			} catch (Exception e) {
				System.out.println("Excewption ==>> " + e.toString());
			}
			det = new DeviceTypeResponse();
			det.setResponse("Successfully Saved!");
			det.setData(data);
			det.setTotal(count);

		} catch (Exception e) {
			det.setResponse("Exception");

			System.out.println("Device type  Details Query Count ==>> " + e.toString());
		} finally {
			DBConnection.getCloseConnection(con);
		}
		return det;
	}

	public String[] deleteDeviceType(DeviceDataType deviceDataType, String[] spltstr) {
		// TODO Auto-generated method stub
		String[] response = null;
		Connection con = DBConnection.getMainConnection();
		PreparedStatement deleteDeviceType = null;
		ResultSet deviceRS = null;

//	select * from masters.insertprocedure('deletedevicetype',  'devicetypeid',
//			'loginid', 'remarks', '',  '',  '', '', '', '', '', 
//			'', '',  '', '','', '', '', '',  '', '', '', '','', '', '', '',
//			'', '', '', '','', '', '', '','',   '', '', '', '','', '', '', '','',
//			'', '', '','','', '');
//	
		try {
			deleteDeviceType = con.prepareStatement(QueryConstants.devicetypdeletequery);

			deleteDeviceType.setString(1, "deletedevicetype"); // 'deletedevicetype'
			deleteDeviceType.setString(2, deviceDataType.getDevicetypeid()); //
			deleteDeviceType.setString(3, spltstr[0]); // loginid
			deleteDeviceType.setString(4, deviceDataType.getRemarks());

			System.err.println(deleteDeviceType.toString());
			deviceRS = deleteDeviceType.executeQuery();
			while (deviceRS.next()) {
				deviceRS.getString(1);
				response = deviceRS.getString(1).split("#");

			}
			deleteDeviceType.close();
			deviceRS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.getCloseConnection(con);
		}

		return response;
	}
}
