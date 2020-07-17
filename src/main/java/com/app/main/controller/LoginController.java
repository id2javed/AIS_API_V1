package com.app.main.controller;


/* Developer    : Pallavi Dhage
 * Created Date : 2019-07-26 
 * Updated Date : 
 * Description  : API Controller For Login Master
 *  */

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.LoginManager;
import com.app.main.dao.LoginData;
import com.app.main.model.LoginResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class LoginController {
	static LoginManager  loginManager=new LoginManager();
	
	@PostMapping(value = ApiRestURIConstants.GET_LOGIN)
	public Response testapipost(@RequestBody LoginData paramsLoginData, HttpServletResponse paramsHttpServletResponse) {
		System.out.println("in login");
		List<LoginResponse> loginResponse = loginManager.login(paramsLoginData);
		if(loginResponse.isEmpty()) {
			loginResponse = null;
		} else {
			String mLogoName = loginResponse.get(0).getLogoFilePath();
			Cookie mCookie = new Cookie("logo", mLogoName);
			mCookie.setMaxAge(30 * 24 * 60 * 60);
			mCookie.setPath("/");
			paramsHttpServletResponse.addCookie(mCookie);
			System.out.println("Cookie created...");
		}
		if(loginResponse==null || loginResponse.size()==0) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("User Name Or Password Wrong ")
					.header("Access-Control-Allow-Origin", "*").build();
		} else {
			return Response.status(200).entity(loginResponse).build();
		}
	}
}
