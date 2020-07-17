package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.dao.DeviceData;
import com.app.queryconstants.QueryConstants;

public class FileUploadWriterManager {
	public static void writeDeviceEntry(List<DeviceData> listpojo, DeviceData dev, String[] spltstr) {
		    /* Developer    : Pallavi Dhage
			 * Created Date : 2019-07-29
			 * Updated Date : 
			 * Description  : Method Implementation For Write Device Entry code in Database
			 *  */
		String response=null;
		for(int i=1;i<listpojo.size();i++){
			DeviceData deviceData = listpojo.get(i);
			Connection con=DBConnection.getMainConnection();
			PreparedStatement insertDevice;
			
			try {
				insertDevice = con.prepareStatement(QueryConstants.insertdevice);
				
				insertDevice.setString(1, "insertdevice");
				insertDevice.setString(2, "");
				insertDevice.setString(3, spltstr[4]);
				insertDevice.setString(4, dev.getModelId());
				insertDevice.setString(5, deviceData.getDeviceImeiNo());
				insertDevice.setString(6, deviceData.getDeviceSerialNo());
				insertDevice.setString(7, deviceData.getDeviceIccidNo());
				insertDevice.setString(8, spltstr[0]);
				insertDevice.setString(9, deviceData.getMobileNumber1());
				insertDevice.setString(10, deviceData.getMobileNumber2());
				insertDevice.setString(11, deviceData.getNetworkId1());
				insertDevice.setString(12, deviceData.getNetworkId2());
				insertDevice.setString(13, dev.getM2mproviderId());
				insertDevice.setString(14, "EXCEL");
				insertDevice.setString(15, dev.getFilename());
				insertDevice.setString(16, dev.getFilelocaction());
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
			
        }
		
	}

	
	
}
