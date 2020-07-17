package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.dao.RawData;
import com.app.main.model.RawDataResponse;

public class RawdataManager {

	public List<RawDataResponse> rawdatamethod(RawData rawD, String[] spltstr) {
		Connection con=null;
		Connection con2=null;
		System.out.println("Manager");
	   List<RawDataResponse> data=new ArrayList<RawDataResponse>();
	   RawDataResponse obj = new RawDataResponse();
		try {
			
		PreparedStatement ps;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdfnew = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		SimpleDateFormat sdfnew1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfnew2 = new SimpleDateFormat("dd-MMM-yyyy");
		
		
		 con=DBConnection.getMainConnection();
		 con2=DBConnection.gethistoryDataConnection();
		
		
		String sqlselect4 = null,cond = ""	;
		String seraccbyoffset = "";
	
		int cnt = 5601;
		
		String sqlportcomp = null;
		try {
			
			
			
//			sqlportcomp = " select pm.portno from dblocator.msttblmapports mp "
//					+ "inner join  dblocator.msttblports pm on pm.portid =mp.portid  where  mp.companyid =" +spltstr[4]
//					+ " limit 1 ";
//			
//			ps = con.prepareStatement(sqlportcomp);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				cnt = rs.getInt(1);
//			}
//			System.out.println("Date1 "+rawD.getDate());
//			
//			System.out.println("Date2 ==>> "+sdfnew2.parse(rawD.getDate()));
			
			//rawD.setDate( sdfnew1.format(sdfnew2.parse(rawD.getDate())));
				cond="  where datamessage::varchar like '%"+ rawD.getImeino() + "%'::varchar   ";
			
			System.out.println("Manager 3");
			sqlselect4 = " SELECT recordid, datatimestamp, datamessage, "
					+ " case when clientfqdn like '%10.0.0.4%' then Replace(clientfqdn,'10.0.0.4','device.AIS-140.com')  end as clientfqdn, "
					+ " clientport FROM \"devicedata_logs_" +rawD.getDate()+"\""
					+ ""+cond+"  ORDER BY datatimestamp desc   ";

			ps = con2.prepareStatement(sqlselect4);
			ResultSet rs4 = ps.executeQuery();
			Integer i = null;
			
			while (rs4.next()) {
				try {
					
					obj.setRecordid(rs4.getString(1));

					java.util.Date parseTimestamp = sdf.parse(rs4.getString(2));
					obj.setDatatimestamp(sdfnew.format(parseTimestamp).toString());
					obj.setDatamessage(rs4.getString(3));
					obj.setClientfqdn(rs4.getString(4));
					obj.setClientport(rs4.getString(5));
					obj.setRowno(i++);
					data.add(obj);
				} catch (Exception e) {
					 System.out.println("rawdata error is " + e);

				}
			}
			

		} catch (Exception e) {
			
			System.out.println("Exception ==>> "+e);
			obj.setResponse("Error");
		}


		} catch (Exception e) {
			obj.setResponse("Error");
			 System.out.println("rawdata error is " + e);
		
		}finally {
			DBConnection.getCloseConnection(con);
			DBConnection.getCloseConnection(con2);
		}
		
		
		return data;
	}

	
}
