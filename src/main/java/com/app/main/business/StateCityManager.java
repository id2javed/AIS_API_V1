package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.model.CityResponse;
import com.app.main.model.StateResponse;
import com.app.queryconstants.QueryConstants;
/*
* Developer    : Pallavi Dhage
* Created Date : 2019-07-27
* Updated Date : 
* Description  : Method Implementation For State & City Master List (Drop-Down Master)
*  */

public class StateCityManager {

	public List<StateResponse> getStateList(String[] spltstr) {
		List<StateResponse> stateList  = new ArrayList<StateResponse>(); 
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getStateList;
		try {
			getStateList = con.prepareStatement(QueryConstants.selectstate);
			
			getStateList.setString(1, "statelist");
			getStateList.setString(2, spltstr[4]);	
			getStateList.setString(3, spltstr[3]);
			getStateList.setString(4, spltstr[4]);
			getStateList.setString(5, spltstr[0]);
			ResultSet stateRS = getStateList.executeQuery();
			  while(stateRS.next()) {
				  StateResponse obj=new StateResponse();
				  obj.setStateId(stateRS.getString(1));
				  obj.setStateName(stateRS.getString(2));
				  obj.setStateCode(stateRS.getString(3));
				  stateList.add(obj);
					
				}
			  
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return stateList;
	}

	public List<CityResponse> getCityList(String[] spltstr, String stateid) {
		// TODO Auto-generated method stub
		List<CityResponse> cityList  = new ArrayList<CityResponse>(); 
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getStateList;
		try {
			getStateList = con.prepareStatement(QueryConstants.selectcity);
			
			getStateList.setString(1, "citylist");
			getStateList.setString(2, spltstr[4]);	
			getStateList.setString(3, spltstr[3]);
			getStateList.setString(4, spltstr[4]);
			getStateList.setString(5, spltstr[0]);
			getStateList.setString(6, stateid);
			ResultSet stateRS = getStateList.executeQuery();
			  while(stateRS.next()) {
				  CityResponse obj=new CityResponse();
				  obj.setCityId(stateRS.getString(1));
				  obj.setCityName(stateRS.getString(2));
				  obj.setCityStateId(stateRS.getString(3));
				  cityList.add(obj);
					
				}
			  
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
		return cityList;
	}

}
