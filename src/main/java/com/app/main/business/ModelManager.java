package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.app.connection.DBConnection;
import com.app.main.dao.ModelData;
import com.app.main.model.ModelResponse;
import com.app.queryconstants.QueryConstants;

public class ModelManager {

	public ModelResponse model(ModelData modelData, String[] spltstr) {
		ModelResponse data = new ModelResponse();

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getmodel;

		try {
			getmodel = con.prepareStatement(QueryConstants.modelinsertquery);
			
			getmodel.setString(1, "insertmodel");
			getmodel.setString(2, "");
			getmodel.setString(3, modelData.getDevmodelcode());
			getmodel.setString(4, modelData.getDevmodelname());
			getmodel.setString(5, modelData.getDevmodelcertby());
			getmodel.setString(6, modelData.getDevmodtaccertno());
			getmodel.setString(7, modelData.getDevmodtaccertdate());
			getmodel.setString(8, modelData.getDevmodtaccertvaildity());
			getmodel.setString(9, modelData.getDevmodproddate());
			getmodel.setString(10, modelData.getDevmodcopcertno());
			getmodel.setString(11, modelData.getDevmodcopcertdate());
			getmodel.setString(12, modelData.getDevmodcopcertvalidity());
			getmodel.setString(13, "");// integer 0 or 1
			getmodel.setString(14, spltstr[4]);
			getmodel.setString(15, "");
			getmodel.setString(16,"");
			getmodel.setString(17, "");
			getmodel.setString(18, "");// integer 0 or 1

			System.out.println("getmodel query : "+getmodel.toString());
			ResultSet rs = getmodel.executeQuery();
			System.out.println("getmodel query : "+getmodel.toString());
			
			while (rs.next()) {
				data.setResponse(rs.getString(1));
			}
			getmodel.close();
			rs.close();

		} catch (Exception e) {
			System.out.println("Query ==>> "+e);
			data.setResponse("Error");
		} finally {
			DBConnection.getCloseConnection(con);
		}

		return data;
	}

}
