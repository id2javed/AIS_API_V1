package com.app.main.controller;

import java.io.FileNotFoundException;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.RestURIConstants.ApiRestURIConstants;
import com.app.configuration.Configuration;
import com.app.main.business.FileUploaderReaderManager;
import com.app.main.business.ImageFileManager;
import com.app.main.business.ManufacturerManager;
import com.app.main.dao.ManufacturerData;
import com.app.main.encryption.EncryptionData;
import com.app.main.model.ManufacturerResponse;
import com.sun.jersey.api.client.ClientResponse.Status;

@RestController
@EnableAutoConfiguration
public class ManufacturerController {
	
	public static String[] spltstr = null;
	private String iUploadedFileName = ("");
	private String iUploadedFilePath = ("");
	private ManufacturerManager iManufacturerManager = new ManufacturerManager();
	private FileUploaderReaderManager iFileUploaderManager = new FileUploaderReaderManager();
	
	@CrossOrigin(origins = "${angularOrigin}")
	@PostMapping(value = ApiRestURIConstants.UPLOAD_IMAGE)
	@Consumes(MediaType.MULTIPART_FORM_DATA) 
	public Response uploadImage(@RequestParam("file") MultipartFile file) {
		
		iUploadedFileName = "";
		String manuResponse = null;
			
		//hosting path
		String uploadedFileLocation = Configuration.serverUploadLocation;

		//local path    
		// String uploadedFileLocation = Configuration.systemUploadLocation;
		// iFileUploaderManager.uploadedLogoExist(uploadedFileLocation + file.getOriginalFilename());
		uploadedFileLocation = iFileUploaderManager.storeFile(file, uploadedFileLocation);
		String[] fileParam = uploadedFileLocation.split("&&");
		iUploadedFilePath = fileParam[0];
		iUploadedFileName = fileParam[1];
		manuResponse = iUploadedFileName;
		if(manuResponse.equals("Error") || manuResponse==null || manuResponse.equals("") || manuResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)                       
					.entity("Details Failed to Save")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(manuResponse.equals("Successfully Saved.")) {
			return Response.status(200)
					.entity(manuResponse).header("Access-Control-Allow-Origin", "*").build();
		}else{	
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(manuResponse).build();
		}
		
	}
	
	@PostMapping(value = ApiRestURIConstants.SET_MANUFACTURER)
	public Response setmanu(@RequestBody ManufacturerData manuData, @RequestHeader("headersparam") String headersparam) throws FileNotFoundException {
		String manuResponse = "";
		spltstr = EncryptionData.getparamencryption(headersparam);
		manuResponse = iManufacturerManager.setManufac(manuData, spltstr, "insertcompany");
		if(manuResponse.equals("Error") || manuResponse==null || manuResponse.equals("") || manuResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Details Failed to Save")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(manuResponse.equals("Successfully Saved.")) {
			try {
				System.out.println("uploadedFileName : " +iUploadedFileName);
				String dbFilePath = iUploadedFilePath + iUploadedFileName;
				ImageFileManager.saveImageFileIntoDB(iUploadedFileName, dbFilePath, manuData.getMancode());
				iUploadedFileName = "";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(200)
					.entity(manuResponse).header("Access-Control-Allow-Origin", "*").build();
		} else {	
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(manuResponse).build();
		}

	}

	@PostMapping(value = ApiRestURIConstants.UPDATE_MANFUFACTURER)
	public Response updateManufacturer(@RequestBody ManufacturerData mManufacturerData, @RequestHeader("headersparam") String headersparam) throws FileNotFoundException {
		System.out.println("in update...");
		String manuResponse = "";
		spltstr = EncryptionData.getparamencryption(headersparam);
		manuResponse = iManufacturerManager.updateManufacturer();
		if(manuResponse.equals("Error") || manuResponse==null || manuResponse.equals("") || manuResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Details Failed to Save")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(manuResponse.equals("Successfully Saved.")) {
			try {
				if(!iUploadedFileName.equals("")) {
					mManufacturerData.setCompanyLogo(iUploadedFileName);
				}
				manuResponse = iManufacturerManager.updateManufacturer(mManufacturerData, spltstr, "updatecompany");
				iUploadedFileName = "";
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Response.status(200)
					.entity(manuResponse).header("Access-Control-Allow-Origin", "*").build();
		} else {	
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(manuResponse).build();
		}
	}
	
	@PostMapping(value = ApiRestURIConstants.GET_MANUFACTURER)
	public Response getmanu(@RequestBody ManufacturerData manuData,@RequestHeader("headersparam") String headersparam) {
		ManufacturerResponse response=new ManufacturerResponse();
		spltstr=EncryptionData.getparamencryption(headersparam);
		response=iManufacturerManager.getManufac(manuData, spltstr); 
		if(response.getResponse().equals("Error") || response.getResponse()==null || response.getResponse().equals("") || response.getResponse().equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Details Failed to Save")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(response.getResponse().equals("Successfully Saved!")) {
			return Response.status(200)
					.entity(response)
					.header("Access-Control-Allow-Origin", "*").build();
		}else{	
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(response).build();
		}		
	}
	
	@PostMapping(value = ApiRestURIConstants.DELETE_MANUFACTURER)
	public Response deleteManufacturer(@RequestBody ManufacturerData paramsManufacturerData, @RequestHeader("headersparam") String headersparam) throws FileNotFoundException {
		System.out.println("in delete...");
		String mManufacturerResponse = "";
		spltstr = EncryptionData.getparamencryption(headersparam);
		mManufacturerResponse = iManufacturerManager.deleteManufacturer(paramsManufacturerData, spltstr, "deletecompany");
		if(mManufacturerResponse.equals("Error") || mManufacturerResponse == null || mManufacturerResponse.equals("") || mManufacturerResponse.equals("null")) {
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity("Details Failed to Save")
					.header("Access-Control-Allow-Origin", "*").build();
		}else if(mManufacturerResponse.equals("Successfully Deleted.")) {
			return Response.status(200)
					.entity(mManufacturerResponse).header("Access-Control-Allow-Origin", "*").build();
		} else {	
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(mManufacturerResponse).build();
		}
	}
	
}
