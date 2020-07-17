package com.app.main.controller;

import java.io.InputStream;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.main.business.FileUploaderReaderManager;
import com.app.main.dao.DeviceData;
import com.app.main.dao.DevicesUploadData;
import com.app.main.encryption.EncryptionData;
import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/* Developer    : Pallavi Dhage
 * Created Date : 2019-07-28
 * Updated Date : 
 * Description  : API Controller For File Upload
 *  */

@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "${angularOrigin}")
public class FileUploaderController {
	
	FileUploaderReaderManager fileUploaderManager = new FileUploaderReaderManager();	
	public static String[] spltstr = null;
	
	
	@PostMapping(value = ApiRestURIConstants.UPLOAD_DEVICE)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response testapipost(@RequestParam("modelcode") String modelcode,@RequestParam("m2m") String m2m,
			@RequestHeader("headersparam") String headersparam,
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {
		String deviceResponse=null;
		spltstr=EncryptionData.getparamencryption(headersparam);
		deviceResponse=fileUploaderManager.uploadDevices(modelcode,m2m,spltstr,uploadedInputStream,fileDetail);
		if(deviceResponse==null || deviceResponse.equals("") || deviceResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("INTERNAL_SERVER_ERROR")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(deviceResponse.equals("Successfully Saved.")) {
			return Response.status(200)
					.entity(deviceResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(deviceResponse).build();
		}
		
	}
	
	
	@PostMapping(value = ApiRestURIConstants.READ_DEVICE)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response readdevicedetailsExcel(@RequestBody DeviceData dev) {
		System.err.println("inmuploadedFileLocation  "+dev.getFilename());
		List<DeviceData> deviceResponse=null;
		spltstr=EncryptionData.getparamencryption(dev.getHeadersparam());
		deviceResponse=fileUploaderManager.readDeviceExcel(dev,spltstr);
		if(deviceResponse==null || deviceResponse.equals("") || deviceResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("INTERNAL_SERVER_ERROR")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(deviceResponse.equals("Successfully Saved.")) {
			return Response.status(200)
					.entity(deviceResponse)
					.header("Access-Control-Allow-Origin", "*").build();
		}else{
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(deviceResponse).build();
		}
		
	}

}
