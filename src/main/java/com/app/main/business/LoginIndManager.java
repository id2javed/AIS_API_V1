package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.dao.LoginIndData;
import com.app.main.encryption.AESCrypt;
import com.app.main.model.LoginIndResponse;
import com.app.queryconstants.QueryConstants;

public class LoginIndManager {
	
	public List<LoginIndResponse> loginind(LoginIndData det)
	{
		List<LoginIndResponse> data = new ArrayList<LoginIndResponse>();
		
		Connection con=DBConnection.getMainConnection();
		PreparedStatement getLogin;
		try {
			getLogin = con.prepareStatement(QueryConstants.loginquery);
//			String username = EncryptionData.encryptId(loginData.getUsername());
//			String password = EncryptionData.encryptId(loginData.getPassword());
			
			
			getLogin.setString(1, "login");
			getLogin.setString(2, det.getUsername());
			getLogin.setString(3, det.getPassword());
			getLogin.setString(4, det.getBrowsername());
			getLogin.setString(5, det.getBrowserversion());
			getLogin.setString(6, det.getOpersatingsys());
			getLogin.setString(7, det.getOsversion());
			
			
			System.out.println("Login Query==>> "+getLogin.toString());
			ResultSet loginRS = getLogin.executeQuery();
			  while(loginRS.next()) {
				  LoginIndResponse obj = new LoginIndResponse();
				  obj.setLoginId(loginRS.getString(1));
				  obj.setUserName(loginRS.getString(2));
				  obj.setLoginName(loginRS.getString(3));
				  obj.setRoleId(loginRS.getString(4));
				  obj.setOwnersId(loginRS.getString(5));
				  obj.setCreatedBy(loginRS.getString(6));
				  
				  
					String str = obj.getLoginId() + "$$" + obj.getUserName() + "$$" + obj.getLoginName() + "$$"
							+ obj.getRoleId() + "$$" + obj.getOwnersId() + "$$" + obj.getCreatedBy() ;

					String k;
					try {
						k = AESCrypt.encrypt(str);
						k = k.replace("+", "pvt");

						obj.setKey(str);
						obj.setEncryptKey(k);;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					
					data.add(obj);
					
				}
			  
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.getCloseConnection(con);
		}
		
		return data;
	}

}
