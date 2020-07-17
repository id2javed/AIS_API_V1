package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.connection.DBConnection;
import com.app.main.dao.LoginData;
import com.app.main.encryption.AESCrypt;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.LoginResponse;
import com.app.queryconstants.QueryConstants;

/* Developer    : Pallavi Dhage
 * Created Date : 2019-07-26 
 * Updated Date : 
 * Description  : Method Implementation For Login Master
 *  */

public class LoginManager {

	public List<LoginResponse> login(LoginData loginData) {
		List<LoginResponse> loginResponse = new ArrayList<LoginResponse>();
		Connection con = DBConnection.getMainConnection();
		PreparedStatement getLogin;
		try {
			getLogin = con.prepareStatement(QueryConstants.loginquery);
			String username = EncryptionData.encryptId(loginData.getUsername());
			String password = EncryptionData.encryptId(loginData.getPassword());
			getLogin.setString(1, "login");
			getLogin.setString(2, username);
			getLogin.setString(3, password);  
			getLogin.setString(4, loginData.getBrowsername());
			getLogin.setString(5, loginData.getBrowserversion());
			getLogin.setString(6, loginData.getOpersatingsys());
			getLogin.setString(7, loginData.getOsversion());
			System.out.println("Login Query:- " + getLogin.toString());
			
			ResultSet loginRS = getLogin.executeQuery();
			while (loginRS.next()) {
				
				LoginResponse obj = new LoginResponse();
				obj.setLoginId(loginRS.getString(1));
				obj.setUserName(loginRS.getString(2));
				obj.setLoginName(loginRS.getString(3));
				obj.setRoleId(loginRS.getString(4));
				obj.setOwnersId(loginRS.getString(5));
				obj.setCreatedBy(loginRS.getString(6));
				obj.setLogoFilePath(loginRS.getString(7));

				String str = obj.getLoginId() + "$$" + obj.getUserName() + "$$" + obj.getLoginName() + "$$"
						+ obj.getRoleId() + "$$" + obj.getOwnersId() + "$$" + obj.getCreatedBy()+"$$"+obj.getLogoFilePath();

				String k;
				try {
					k = AESCrypt.encrypt(str);
					k = k.replace("+", "pvt");

					obj.setKey(str);
					obj.setEncryptKey(k);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				loginResponse.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.getCloseConnection(con);
		}
		return loginResponse;
	}

}
