package com.app.main.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.app.main.dao.DeviceData;
import com.sun.jersey.core.header.FormDataContentDisposition;

/* Developer : Pallavi Dhage
 * Created Date : 2019-07-28
 * Updated Date : 
 * Description  : Method Implementation
 *  */
public class FileUploaderReaderManager {

	public String uploadDevices(String modelcode, String m2m, String[] spltstr, InputStream uploadedInputStream,
			FormDataContentDisposition fileDetail) {
		System.out.println("in uploadDevices");
		String uploadedFileLocation = null, uploadedFileLocation2 = null;
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = fileDetail.getFileName();
		String[] words = fileName.split("\\.");

		File file = new File("D:\\PALLAVI\\AIS140APP\\OTHER\\FILEUPLOADS\\");

		String filename = modelcode + "_" + words[0] + "_" + df.format(new Date()) + "." + words[1];
		uploadedFileLocation = "D:\\PALLAVI\\AIS140APP\\OTHER\\FILEUPLOADS\\" + filename;
		if (!file.exists()) {
			if (file.mkdir()) {
				System.out.println("Directory is created!");
			} else {
				System.out.println("Failed to create directory!");
			}
			uploadedFileLocation = "D:\\PALLAVI\\AIS140APP\\OTHER\\FILEUPLOADS\\" + filename;
		} else {
			uploadedFileLocation = "D:\\PALLAVI\\AIS140APP\\OTHER\\FILEUPLOADS\\" + filename;
		}

		try {

			System.out.println(uploadedFileLocation);
			// save it
			writeToFile(uploadedInputStream, uploadedFileLocation);
		} catch (Exception e) {
			System.out.println(e);
		}
		InputStream inStream = null;
		OutputStream outStream = null;
		// COPY TO THIS LOCATION
		uploadedFileLocation2 = "D:\\PALLAVI\\AIS140APP\\OTHER\\FILEUPLOADS\\COPY\\" + filename;
		try {
			inStream = new FileInputStream(uploadedFileLocation);
			outStream = new FileOutputStream(uploadedFileLocation2);
			byte[] buffer = new byte[1024];

			int length;
			// copy the file content in bytes
			while ((length = inStream.read(buffer)) > 0) {

				outStream.write(buffer, 0, length);

			}

			inStream.close();
			outStream.close();
			System.out.println("File is copied successful!");
		} catch (Exception e) {
			System.out.println("File copied success erroe " + e);
		}
		String output = "File uploaded to : " + uploadedFileLocation2;
		return uploadedFileLocation2;
	}

	public void uploadedLogoExist(String pUploadedFileLocation) {
		/*
		 * Developer : Javed Shaikh 
		 * Created Date : 01-07-2020 
		 * Updated Date : 
		 * Description  : Method Implementation For File Upload Exists
		 */
		
		try {
			System.out.println(pUploadedFileLocation);
			File mFile = new File(pUploadedFileLocation);
			if(mFile.exists()) {
				if(mFile.delete()) {
					System.out.println("File deleted...");
				} else {
					System.out.println("File not deleted...");
				}
			} else {
				System.out.println("File not exists...");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void uploadCompanyLogo(InputStream uploadedInputStream, FormDataContentDisposition fileDetail) {
		/*
		 * Developer    : Javed Shaikh 
		 * Created Date : 15-06-2020 
		 * Updated Date : 
		 * Description  : Method Implementation For File Upload
		 */

		String uploadedFileLocation = null;
		DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		String originalFileName = fileDetail.getFileName();
		String[] words = originalFileName.split("\\.");
		String fileName = "_" + words[0] + "_" + df.format(new Date()) + "." + words[1];
		uploadedFileLocation = "D:\\New folder\\11-06-2020\\AIS_API_V1\\src\\main\\resources\\CompanyLogo\\" + fileName;

		try {
			System.out.println(uploadedFileLocation);
			writeToFile(uploadedInputStream, uploadedFileLocation);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	final DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");

	public String storeFile(MultipartFile file, String uploadedFileLocation) {
		// Normalize file name
		String uploadFileLocation = "";
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		String[] words = fileName.split("\\.");
		fileName = words[0] + "_" + df.format(new Date()) + "." + words[1];
		try {
			uploadFileLocation = uploadedFileLocation + fileName;
			System.out.println("Upload Location is: "+uploadFileLocation);
			writeToFile(file.getInputStream(), uploadFileLocation);
			System.out.println("Uploaded File..");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
		uploadFileLocation = uploadedFileLocation + "&&" + fileName;
		fileName = "";
		uploadedFileLocation = "";
		return uploadFileLocation;
	}

	private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
		/*
		 * Developer : Pallavi Dhage Created Date : 2019-07-29 Updated Date :
		 * Description : Method Implementation For Write Excel Code
		 */

		try {
			OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			System.out.println("e    " + e);
			e.printStackTrace();
		}
	}

	public List<DeviceData> readDeviceExcel(DeviceData dev, String[] spltstr) {
		/*
		 * Developer : Pallavi Dhage Created Date : 2010-07-29 Updated Date :
		 * Description : Method Implementation For read device entry excel
		 */
		try {
			FileInputStream file = new FileInputStream(new File(dev.getFilelocaction()));
			org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(file);

			// Get first/desired sheet from the workbook
			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

			// Iterate through each rows one by one
			Iterator<Row> rowIterator = sheet.iterator();

			List<DeviceData> listpojo = new ArrayList<DeviceData>();
			while (rowIterator.hasNext()) {
				DeviceData obj = new DeviceData();
				Row row = rowIterator.next();
				// For each row, iterate through all the columns
				Iterator<Cell> cellIterator = row.cellIterator();
				int k = 0;
				while (cellIterator.hasNext()) {

					Cell cell = cellIterator.next();
					// Check the cell type and format accordingly

					switch (cell.getCellType()) {
					case Cell.CELL_TYPE_NUMERIC:
						if (k == 0) {
							String did = String.valueOf(cell.getNumericCellValue());
							did = did.replace(".0", "");
							did = did.replace("E14", "");
							did = did.replace("E12", "");
							did = did.replace("E7", "");
							did = did.replace("E8", "");
							did = did.replaceAll("[^A-Za-z0-9]", "");
							obj.setDeviceImeiNo(did);
							System.out.println("srno  " + obj.getDeviceImeiNo() + "\t");
						}
						if (k == 1) {
							String did = String.valueOf(cell.getNumericCellValue());
							did = did.replace("N", "");
							did = did.replace(".0", "");
							did = did.replace("E14", "");
							did = did.replace("E12", "");
							did = did.replace("E7", "");
							did = did.replace("E8", "");
							did = did.replaceAll("[^A-Za-z0-9]", "");
							Double dd = Double.parseDouble(did);
							obj.setDeviceSerialNo(did);
							System.out.print("device id " + did + "\t");
						}
						if (k == 2) {
							String cellv = String.valueOf(cell.getNumericCellValue());
							cellv = cellv.replace(".", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");
							obj.setDeviceIccidNo(cellv);
							;
							System.out.print("imeino  " + obj.getDeviceIccidNo() + "\t");
						}
						if (k == 3) {
							String cellv = String.valueOf(cell.getNumericCellValue());
							cellv = cellv.replace(".", "");
							cellv = cellv.replace("E9", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");
							if (cellv.length() < 13 && cellv.length() > 10) {
								int l = 10 - cellv.length();
								for (int o = 0; o < l; o++) {
									cellv = cellv + "0";
								}
							} else if (cellv.length() < 10) {
								int l = 10 - cellv.length();
								for (int o = 0; o < l; o++) {
									cellv = cellv + "0";
								}
							}
							obj.setMobileNumber1(cellv);
							System.out.print("setMobno no  " + obj.getMobileNumber1() + "\t");
						}
						if (k == 4) {
							String cellv = String.valueOf(cell.getNumericCellValue());
							cellv = cellv.replace(".", "");
							cellv = cellv.replace("E9", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");
							if (cellv.length() < 13 && cellv.length() > 10) {
								int l = 10 - cellv.length();
								for (int o = 0; o < l; o++) {
									cellv = cellv + "0";
								}
							} else if (cellv.length() < 10) {
								int l = 10 - cellv.length();
								for (int o = 0; o < l; o++) {
									cellv = cellv + "0";
								}
							}
							obj.setMobileNumber2(cellv);
							System.out.print("setSimnw1  no   " + obj.getMobileNumber2() + "\t");
						}
						if (k == 5) {
							String cellv = String.valueOf(cell.getNumericCellValue());
							cellv = cellv.replace(".", "");
							cellv = cellv.replace("E9", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");

							obj.setNetworkId1(cellv);
							System.out.print("setMobno2  no  " + obj.getNetworkId1() + "\t");
						}
						if (k == 6) {
							String cellv = String.valueOf(cell.getNumericCellValue());
							cellv = cellv.replace(".", "");
							cellv = cellv.replace("E9", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");
							obj.setNetworkId2(cellv);
							System.out.print("Simnw2    " + obj.getNetworkId2() + "\t");
						}
						break;
					case Cell.CELL_TYPE_STRING:
						if (k == 0) {
							String cellv = String.valueOf(cell.getStringCellValue());
							cellv = cellv.replace(".0", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");
							obj.setDeviceImeiNo(cellv);
							System.out.print("srno  " + obj.getDeviceImeiNo() + "\t");
						}
						if (k == 1) {
							String cellv = cell.getStringCellValue();
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");
							obj.setDeviceSerialNo(cellv);
							System.out.print("device  " + cellv + "\t");
						}
						if (k == 2) {
							String cellv = cell.getStringCellValue();
							cellv = cellv.replace(".", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");

							obj.setDeviceIccidNo(cellv);
							System.out.print("imeino  " + obj.getDeviceIccidNo() + "\t");
						}
						if (k == 3) {
							String val = cell.getStringCellValue();
							val = val.replace("U", "");
							val = val.replace(".", "");
							val = val.replace("E8", "");
							val = val.replace("E9", "");
							val = val.replace("E14", "");
							val = val.replace("E12", "");
							val = val.replace("E14", "");
							val = val.replace("E12", "");
							val = val.replace("E14", "");
							val = val.replace("E12", "");
							val = val.replace("E7", "");
							val = val.replace("E8", "");
							val = val.replaceAll("[^A-Za-z0-9]", "");
							if (val.length() < 13 && val.length() > 10) {
								int l = 10 - val.length();
								for (int o = 0; o < l; o++) {
									val = val + "0";
								}
							} else if (val.length() < 10) {
								int l = 10 - val.length();
								for (int o = 0; o < l; o++) {
									val = val + "0";
								}
							}
							obj.setMobileNumber1(val);
							System.out.print("setMobno1  " + obj.getMobileNumber1() + "\t");
						}
						if (k == 4) {
							String cellv = cell.getStringCellValue();
							cellv = cellv.replace(".", "");
							cellv = cellv.replace("E9", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");
							if (cellv.length() < 13 && cellv.length() > 10) {
								int l = 10 - cellv.length();
								for (int o = 0; o < l; o++) {
									cellv = cellv + "0";
								}
							} else if (cellv.length() < 10) {
								int l = 10 - cellv.length();
								for (int o = 0; o < l; o++) {
									cellv = cellv + "0";
								}
							}
							obj.setMobileNumber2(cellv);
							System.out.print("setSimnw1  no   " + obj.getMobileNumber2() + "\t");
						}
						if (k == 5) {
							String cellv = cell.getStringCellValue();
							cellv = cellv.replace(".", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replace("E9", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");
							obj.setNetworkId1(cellv);
							System.out.print("setMobno2  no  " + obj.getNetworkId1() + "\t");
						}

						if (k == 6) {
							String cellv = cell.getStringCellValue();
							cellv = cellv.replace(".", "");
							cellv = cellv.replace("E9", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E14", "");
							cellv = cellv.replace("E12", "");
							cellv = cellv.replace("E7", "");
							cellv = cellv.replace("E8", "");
							cellv = cellv.replaceAll("[^A-Za-z0-9]", "");
							obj.setNetworkId2(cellv);
							System.out.print("Simnw2    " + obj.getNetworkId2() + "\t");
						}
						break;

					}
					k++;

				}
				listpojo.add(obj);
				// System.out.println("length = "+listpojo.size());
			}
			file.close();

			FileUploadWriterManager.writeDeviceEntry(listpojo, dev, spltstr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<DeviceData> listpojo = null;
		return listpojo;

	}

}
