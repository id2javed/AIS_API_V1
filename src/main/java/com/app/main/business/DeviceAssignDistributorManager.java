package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.app.connection.DBConnection;

import com.app.main.dao.DeviceData;
import com.app.main.model.DeviceAssignDistributorResponse;
import com.app.queryconstants.QueryConstants;

public class DeviceAssignDistributorManager {
	
	 public DeviceAssignDistributorResponse diviceassigndistri( String distributorid,String[] spltstr, List<DeviceData> data2)
	 {
		 DeviceAssignDistributorResponse data = new DeviceAssignDistributorResponse();
		 Connection con=DBConnection.getMainConnection();
		 try {
		
		 for(int i=0;i<=data2.size();i++) {
			 DeviceData devdata=data2.get(i);
			System.out.println("Device Assign");
				PreparedStatement getdevdistri;
				
					getdevdistri = con.prepareStatement(QueryConstants.deviceassigndistriquery);
					getdevdistri.setString(1, "assigndevicetodistribor");
					getdevdistri.setString(2, devdata.getDeviceId());
					getdevdistri.setString(3, distributorid);
					getdevdistri.setString(4, spltstr[0]);
					
					
					
					ResultSet rs=getdevdistri.executeQuery();
					
					
					while(rs.next())
					{
						data.setResponse(rs.getString(1));
					}
				
				
				
		 }
		 } catch (Exception e) {
				
				data.setResponse("Error");
			}finally {
				DBConnection.getCloseConnection(con);
			}
			
			
		 
		 return data;
	 }

	 public String deviceUnssignDistributor( String distributorid,String[] spltstr, List<DeviceData> data2)
	 {
		 String data = new String();
		 Connection con=DBConnection.getMainConnection();
		 System.out.println("Device Unassign");
		 try {
		
		 for(int i=0;i<=data2.size();i++) {
			 DeviceData devdata=data2.get(i);
			System.out.println("Device Unassign");
				PreparedStatement getdevdistri;
				
					getdevdistri = con.prepareStatement(QueryConstants.unassignfromdistributor);
					getdevdistri.setString(1, "unassigndevicetodistribor");
					getdevdistri.setString(2, devdata.getDeviceId());
					getdevdistri.setString(3, distributorid);
					getdevdistri.setString(4, spltstr[0]);
					
					
					
					ResultSet rs=getdevdistri.executeQuery();
					
					
					while(rs.next())
					{
						data=rs.getString(1);
					}
				
				
				
		 }
		 } catch (Exception e) {
				
				data="Error";
			}finally {
				DBConnection.getCloseConnection(con);
			}
			
			
		 
		 return data;
	 }
	 
}
