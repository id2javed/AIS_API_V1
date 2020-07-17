package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.connection.DBConnection;
import com.app.main.dao.VehicleInstallationData;
import com.app.queryconstants.QueryConstants;

/*
* Developer    : Pallavi Dhage
* Created Date : 2019-07-27
* Updated Date : 
* Description  : Method Implementation For Vehicle Installation
*  */

public class VehicleInstallationManager {

	public String insertvehicleInstallatin(VehicleInstallationData deviceData, String[] spltstr) {
		String response=null;
		Connection con=DBConnection.getMainConnection();
		PreparedStatement installVehicle;
		
		try {
			installVehicle = con.prepareStatement(QueryConstants.installVehicle);
			
			installVehicle.setString(1, "vehicleinstall");
			installVehicle.setString(2, "");
			installVehicle.setString(3, deviceData.getVehiclemakeid());
			installVehicle.setString(4, deviceData.getVehiclemodelid());
			installVehicle.setString(5, deviceData.getVehicletypeid());
			installVehicle.setString(6, deviceData.getVehicleregno());
			installVehicle.setString(7, deviceData.getVehiclechasisno());
			installVehicle.setString(8, deviceData.getVehicleengineno());
			installVehicle.setString(9, deviceData.getVehiclemfgyear());
			installVehicle.setString(10, deviceData.getCustomerid());
			installVehicle.setString(11, spltstr[0]);
			installVehicle.setString(12, deviceData.getVehiclestatecode());
			installVehicle.setString(13, deviceData.getRtocode());
			installVehicle.setString(14, deviceData.getVehicletype());
			installVehicle.setString(15, deviceData.getDeviceid());
			installVehicle.setString(16, deviceData.getCustid());
			installVehicle.setString(17, deviceData.getAddressproof());
			installVehicle.setString(18, deviceData.getRc());
			installVehicle.setString(19, deviceData.getInvoice());
			installVehicle.setString(20, deviceData.getPanic());
			installVehicle.setString(21, deviceData.getDevInst());
			System.out.println("Vehicle Installation Query==>> "+installVehicle);
			ResultSet deviceRS = installVehicle.executeQuery();
			  while(deviceRS.next()) {
				  response=deviceRS.getString(1);
				}
			  installVehicle.close(); 
			  deviceRS.close();
		} catch (SQLException e) {
			response="";
			e.printStackTrace();
		}finally {
			DBConnection.getCloseConnection(con);
		}
		
		return response;
	}

}
