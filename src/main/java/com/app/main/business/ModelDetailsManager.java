package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.model.ModelDetailsResponse;
import com.app.queryconstants.QueryConstants;

public class ModelDetailsManager {
	
	
	public List<ModelDetailsResponse> modeldetails(String[] spltstr)
	{
		List<ModelDetailsResponse> data = new ArrayList<ModelDetailsResponse>();

		Connection con=DBConnection.getMainConnection();
		PreparedStatement getdistri;
		try {
			getdistri = con.prepareStatement(QueryConstants.modeldetailsquery);

			getdistri.setString(1, "modeldetails");
			getdistri.setString(2, spltstr[4]);//company id send owners id
			getdistri.setString(3, spltstr[3]);//roleid
			getdistri.setString(4, spltstr[4]);//ownersid
			getdistri.setString(5,spltstr[0]);//loginid
			getdistri.setString(6,"");
			
			System.out.println("Select : "+getdistri.toString());
			
			ResultSet rs=getdistri.executeQuery();
			while(rs.next())
			{
				ModelDetailsResponse obj = new ModelDetailsResponse();
				obj.setDevmodelid(rs.getString(1));
				obj.setDevmodelcode(rs.getString(2));
				obj.setDevmodelname(rs.getString(3));
				obj.setDevmodelcertby(rs.getString(4));
				obj.setDevmodtaccertno(rs.getString(5));
				obj.setDevmodtaccertdate(rs.getString(6));
				obj.setDevmodtaccertvaildity(rs.getString(7));
				obj.setDevmodproddate(rs.getString(8));
				obj.setDevmodcopcertno(rs.getString(9));
				obj.setDevmodcopcertdate(rs.getString(10));
				obj.setDevmodcopcertvalidity(rs.getString(11));
				obj.setDevmodcertupload(rs.getString(12));
				obj.setVendorid(rs.getString(13));
				obj.setVendorname(rs.getString(14));
				
				data.add(obj);
			}
		} catch (Exception e) {
			System.out.println("Model Details Exception ==>> "+e);
		}finally {
			DBConnection.getCloseConnection(con);
		}	
		return data;
	}
}
