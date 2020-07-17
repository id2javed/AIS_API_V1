package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.connection.DBConnection;
import com.app.main.dao.DeviceData;
import com.app.queryconstants.QueryConstants;

public class DeviceMasterManager {

	public String insertDevice(DeviceData deviceData, String[] spltstr) {
		// TODO Auto-generated method stub
		String response=null;
		Connection con=DBConnection.getMainConnection();
		PreparedStatement insertDevice;
		
		try {
			insertDevice = con.prepareStatement(QueryConstants.insertdevice);
			
			insertDevice.setString(1, "insertdevice");
			insertDevice.setString(2, "");
			insertDevice.setString(3, spltstr[4]);
			insertDevice.setString(4, deviceData.getModelId());
			insertDevice.setString(5, deviceData.getDeviceImeiNo());
			insertDevice.setString(6, deviceData.getDeviceSerialNo());
			insertDevice.setString(7, deviceData.getDeviceIccidNo());
			insertDevice.setString(8, spltstr[0]);
			insertDevice.setString(9, deviceData.getMobileNumber1());
			insertDevice.setString(10, deviceData.getMobileNumber2());
			insertDevice.setString(11, deviceData.getNetworkId1());
			insertDevice.setString(12, deviceData.getNetworkId2());
			insertDevice.setString(13, deviceData.getM2mproviderId());
			insertDevice.setString(14, "APPLICATION");
			insertDevice.setString(15, ""); //filename
			insertDevice.setString(16, ""); //filepath
			insertDevice.setString(17, ""); //remarks
			insertDevice.setString(18, deviceData.getDeviceType()); //device type

			System.err.println(insertDevice);
			ResultSet deviceRS = insertDevice.executeQuery();
			  while(deviceRS.next()) {
				  response=deviceRS.getString(1);
				}
			  insertDevice.close(); 
			  deviceRS.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.getCloseConnection(con);
		}
		
		return response;
	}

}
