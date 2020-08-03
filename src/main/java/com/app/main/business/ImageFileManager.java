/* Developer 	: Javed Shaikh 
 * Created Date : 18-06-2020 
 * Updated Date :
 * Description 	: Method Implementation For Write Excel Code
 */

package com.app.main.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.app.connection.DBConnection;
import com.app.queryconstants.QueryConstants;

public class ImageFileManager {
	public static void saveImageFileIntoDB(String pFileName, String pFilePath, String pCompanyCode) {
		Connection mConnection = null;
		PreparedStatement mPreparedStatement = null;
		FileInputStream mFileInputStream = null;
		try {
			mConnection = DBConnection.getMainConnection();  
			mPreparedStatement = mConnection.prepareStatement(QueryConstants.UPDATE_IMAGE_FILE);
		    File mFile = new File(pFilePath);
		    if(mFile.exists()) {
		    	mFileInputStream = new FileInputStream(mFile);
		    	mPreparedStatement.setString(1, pFileName);
		    	mPreparedStatement.setBinaryStream(2, mFileInputStream);
		    	mPreparedStatement.setString(3, pCompanyCode);
				mPreparedStatement.execute();
			    System.out.println("Image  upload Query:- " + mPreparedStatement.toString());
				System.out.println("Image Successfully saved");
		    } else {
		    	System.out.println("File not exists...."+pFilePath);
		    }
		} catch (Exception mException) {
			System.out.println(mException);
		} finally {
			if (mConnection != null) {
				try {
					mConnection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(mFileInputStream!=null){
			     try {
					mFileInputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	// working method for reading image from database...
	public static void getImageFIleFromDB(String mFileName) {
		Connection mConnection = null;
		PreparedStatement mPreparedStatement = null;
		FileInputStream mFileInputStream = null;
		FileOutputStream mFileOutputStream = null;
		// byte[] mInputStreamBuffer = new byte[1024];
		try {
			mConnection = DBConnection.getMainConnection();  
			mPreparedStatement = mConnection.prepareStatement(QueryConstants.GET_IMAGE);
		    ResultSet mResultSet = mPreparedStatement.executeQuery();
		    if(mResultSet.next()) {
		    	InputStream mInputStream = mResultSet.getBinaryStream(1);
		    	mFileOutputStream = new FileOutputStream(mFileName);
		    	// mInputStream.read(mInputStreamBuffer);
		    	mFileOutputStream.write(mInputStream.read());
		    	System.out.println("Image store in D Drive...");
		    } else {
		    	System.out.println("No Record Found...");
		    }
		} catch (Exception mException) {
			System.out.println(mException);
			mException.printStackTrace();
		} finally {
			if (mConnection != null) {
				try {
					mConnection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(mFileInputStream!=null){
			     try {
					mFileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(mFileOutputStream!=null){
			     try {
			    	 mFileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
