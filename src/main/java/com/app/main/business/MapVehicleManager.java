package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.ObjLongConsumer;

import com.app.connection.DBConnection;

import com.app.main.model.LiveVehicleResponse;
import com.app.main.model.MapAlldataResponse;
import com.app.queryconstants.QueryConstants;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
public class MapVehicleManager {
	private String jsnstr;
	private String jsnstrstart;
	private String jsnstrend;


	
	public String getJsnstr() {
		return jsnstr;
	}

	public void setJsnstr(String jsnstr) {
		this.jsnstr = jsnstr;
	}

	public String getJsnstrstart() {
		return jsnstrstart;
	}

	public void setJsnstrstart(String jsnstrstart) {
		this.jsnstrstart = jsnstrstart;
	}

	public String getJsnstrend() {
		return jsnstrend;
	}

	public void setJsnstrend(String jsnstrend) {
		this.jsnstrend = jsnstrend;
	}

	public String getIGN(String val) {
		if (val.equals("1")) {
			return "ON";
		} else {
			return "OFF";
		}
	}

	public String getGPS(String val) {
		if (val.contains("1")) {
			return "VALID";
		} else {
			return "INVALID";
		}
	}

	public String getDir(int head) {
		String dir;
		if (head >= 345 && head < 15) {
			dir = "North";
		} else if (head >= 15 && head < 45) {
			dir = "North-West";
		} else if (head >= 45 && head < 75) {
			dir = "North-West";
		} else if (head >= 75 && head < 150) {
			dir = "West";
		} else if (head >= 105 && head < 135) {
			dir = "South-West";
		} else if (head >= 135 && head < 165) {
			dir = "South-West";
		} else if (head >= 165 && head < 195) {
			dir = "South";
		} else if (head >= 195 && head < 225) {
			dir = "South-East";
		} else if (head >= 225 && head < 255) {
			dir = "South-East";
		} else if (head >= 255 && head < 285) {
			dir = "East";
		} else if (head >= 285 && head < 315) {
			dir = "North-East";
		} else if (head >= 315 && head < 345) {
			dir = "North-East";
		} else {
			dir = "North";
		}
		return (dir);
	}

	public Integer reticonColor(int head) {
		// alert("head"+head);
		Integer icon;
		if (head >= 345 && head < 15) {
			icon = 0;
		} else if (head >= 15 && head < 60) {
			icon = 45;
		} else if (head >= 60 && head < 120) {
			icon = 90;
		} else if (head >= 120 && head < 160) {
			icon = 135;
		} else if (head >= 160 && head < 210) {
			icon = 180;
		} else if (head >= 210 && head < 235) {
			icon = 225;
		} else if (head >= 235 && head < 290) {
			icon = 270;
		} else if (head >= 290 && head < 345) {
			icon = 315;
		} else {
			icon = 0;
		}
		return icon;
	}



	public MapAlldataResponse livevehicle(String vehicledata,String[] spltstr) {
		List<LiveVehicleResponse> liveVehicleDetails  = new ArrayList<LiveVehicleResponse>(); 
		Connection con=DBConnection.getMainConnection();
		List<LiveVehicleResponse> data = new ArrayList<LiveVehicleResponse>();
		MapAlldataResponse responsedata=null;
		List<MapAlldataResponse> datamap = new ArrayList<MapAlldataResponse>();
		List<String> datalist = new ArrayList<String>();
		System.out.println(datalist);
		String str = null, fence = null;
		PreparedStatement getVehicle;
		//select * from masters.selectmapprocedure('singlevehiclelivedata', 'companyid', 'roleid', 'ownersid', 'loginid',  'searchby', 'seraccbyoffset', 'iscount', 'vehicleno', '',  '');";

				try {
			getVehicle = con.prepareStatement(QueryConstants.livevehicle);
			//spltstr
			getVehicle.setString(1, "singlevehiclelivedata");
			getVehicle.setString(2, spltstr[4]);	
			getVehicle.setString(3, spltstr[3]);
			getVehicle.setString(4, spltstr[4]);
			getVehicle.setString(5, spltstr[0]);
			getVehicle.setString(6, "");	
			getVehicle.setString(7, "");
			getVehicle.setString(8, "");
			getVehicle.setString(9,vehicledata);
			
			

			ResultSet compRS = getVehicle.executeQuery();
			System.out.println("hiii live vehicle details Details    " +getVehicle.toString());
			int indexno = 1;
			
			while(compRS.next()) {
				System.out.println("Bantayy");
				
				
				try {


					try {

						java.util.Date parseTimestamp=null;
							LiveVehicleResponse dd = new LiveVehicleResponse();
							try {dd.setUnitid(compRS.getString(3 + 1));}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
							
							try {dd.setVehical_no(compRS.getString(11));}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
							
							SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							SimpleDateFormat sdfnew = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a");
							
							try { parseTimestamp = sdf1.parse(compRS.getString(1 + 1));}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
							
							try {	dd.setDatereceived(sdfnew.format(parseTimestamp));}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
						
							try {	dd.setTrackdate(parseTimestamp.toLocaleString());}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
						
							try {dd.setLatitude(compRS.getString(6 + 1));}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
							
							try {dd.setLongitude(compRS.getString(7 + 1));}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
							
							try {dd.setSpeed(compRS.getDouble(8 + 1));}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
							
							try {dd.setHeadings(getDir(compRS.getInt(13)));}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
							
							try {dd.setGpsstatus(getGPS(compRS.getString(14)));}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
							
							try {dd.setIgnnumber(getIGN(compRS.getString(15)));}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
							
							try {
								if (compRS.getString(15).equals("0") && dd.getSpeed() > 0.00) {
									dd.setIgnnumber(getIGN("1"));
								}
								dd.setVtype(compRS.getString(16));
								try {
									dd.setDistrict(compRS.getString(17));
								} catch (Exception e) {
									dd.setDistrict("");
								}
								try {
								} catch (Exception e) {
									dd.setDistrict("");
								}
							}catch(Exception e) {System.out.println("exception 1 ===   "+e);};
							Integer head = compRS.getInt(18);
							try {
								dd.setCompany(compRS.getString(18));
								
								//System.out.println(compRS.getInt(19));
								dd.setDirdeg(reticonColor(head));
								dd.setColor("green");
								dd.setGpscolor("");
								dd.setStatename(compRS.getString(23));
								dd.setAlertname(compRS.getString(24));
							}catch(Exception e) {System.out.println("rest of the code ==== "+e);};

							
						fence = "{" + "  \"type\":\"Feature\"," + "\"icon_url\":" + reticonColor(head) + ""
								+ ",\"track_date\":\"" + dd.getTrackdate() + "\"" + ",\"vehicleno\":\""
								+ dd.getVehical_no() + "\"" + ",\"track_speed\":\"" + compRS.getDouble(8 + 1) + "\""
								+ ",\"track_ign\":\"" + dd.getIgnnumber() + "\"," + "        			\"properties\":"
								+ "{\"message\":\"" + dd.getVehical_no() + "\",\"trackdate\":\"" + dd.getDatereceived()
								+ "\",\"track_speed\":\"" + dd.getSpeed() + "\",\"track_ign\":\"" + dd.getIgnnumber()
								+ "\",\"track_gps\":\"" + dd.getGpsstatus() + "\",\"color\":\"" + dd.getColor()
								+ "\",\"gpscolor\":\"" + dd.getGpscolor() + "\",\"dirdeg\":\"" + dd.getDirdeg()
								+ "\",\"alertid\":\"" + dd.getAlertname() + "\",\"state\":\"" + dd.getStatename()
								+ "\",\"company\":\"" + dd.getCompany() + "\"}," + "\"geometry\":{"
								+ "\"type\":\"Point\"," + "\"coordinates\":[" + ""
								+ compRS.getString(7) + "," + "" + compRS.getString(6) + ""
								+ " ]" + "}" + " }";

							//datalist.add(fence);
							 dd.setMapfencedata(fence);
							 
							data.add(dd);//
							
							
							//indexno++;
							System.out.println("result   ====  "+data.toString());
						

					} catch (Exception e) {
						System.err.println("Exception "+e);
					}
				
				}catch(Exception e) {System.out.println("exception at indfjdf  "+e);}
			}
			  getVehicle.close();
			  compRS.close();
		} catch (SQLException e) {
			System.err.println(e);
		}finally {
			DBConnection.getCloseConnection(con);
		}
	System.out.println("data at herer   ==== "+data.toString());
	responsedata=new MapAlldataResponse();
	responsedata.setDataarr(data);

	
	//String jsonString=json.minify(str)
			
	//		str = "{\"type\":\"LineString\",\"coordinates\":" + datalist.toString() + "}";
	//JSONMinify json = new JSONMinify();
	// jsonstr = "{\"type\":\"Feature\",\"properties\":{},\"geometry\":"+ json.minify(str)+"}";
				return  responsedata;
		
	}
	
	
	public  List<MapVehicleManager> historyvehicle(String vehicleno,String date,String time1,String time2) {
		MapVehicleManager mm = new MapVehicleManager();
		List<MapVehicleManager> datastr = new ArrayList<MapVehicleManager>();
		
				List<LiveVehicleResponse> liveVehicleDetails  = new ArrayList<LiveVehicleResponse>(); 
				Connection con=DBConnection.getMainConnection();
				List<LiveVehicleResponse> data1 = new ArrayList<LiveVehicleResponse>();
				List<MapAlldataResponse> datamap = new ArrayList<MapAlldataResponse>();
				List<String> datalist = new ArrayList<String>();
				List<String> datalistsp = new ArrayList<String>();
				List<String> datalistdp = new ArrayList<String>();
				//List<LiveVehicleResponse> data = new ArrayList<LiveVehicleResponse>();
				String str = null;
				String fence = null;
				PreparedStatement getvehicleHistory;
				PreparedStatement getvehicleimei;
				Integer Tamperedcnt = 0;
				Integer Over_Speedcnt = 0;
				Integer Emergency_state_ONcnt = 0;
				Integer Battery_Disconnectcnt = 0;
				Integer Harsh_Accelerationcnt = 0;
				Integer Harsh_Brakingcnt = 0;
				Integer Rash_Turningcnt = 0;
				Integer Low_batterycnt = 0;
				Integer Geo_Fence_Exitcnt = 0;
				String jsonstr=null;
				Integer other = 0;
				ResultSet compRS;
//
				SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
//				SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//				SimpleDateFormat datefrmt = new SimpleDateFormat("dd-MMM-yyyy");
//				SimpleDateFormat datefrmtd = new SimpleDateFormat("yyyy-MM-dd");
//				SimpleDateFormat datefrmtt = new SimpleDateFormat("yyyyMMdd");

				
				
				
				
				try {
					Date parsedTimeStamp = dateFormat.parse(time1);
					Date parsedTimeStamp1 = dateFormat.parse(time2);
					Timestamp timest1 = new Timestamp(parsedTimeStamp.getTime());
					Timestamp timest2 = new Timestamp(parsedTimeStamp1.getTime());
					
				}catch(Exception e) {System.out.println("time parse error");}

				

				


				//default, ISO_LOCAL_DATE
		        LocalDate receivinglocalDate = LocalDate.parse(date);
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = LocalDate.now();
				String getpevdate=dtf.format(localDate.minusDays(14));
				 LocalDate prevlocalDate = LocalDate.parse(getpevdate);
			
			
				try {
					//public static String historyvehicle="select * from masters.selectmapprocedure('historydata','MH40BH2475', '2019-10-05', '00:00:01', '23:59:59', '', '', '', '', '', '')";			
					
					getvehicleHistory = con.prepareStatement(QueryConstants.historyvehicle);
					
					getvehicleHistory.setString(1, "historydata");
					getvehicleHistory.setString(2, vehicleno);	
					getvehicleHistory.setString(3, date);	
					getvehicleHistory.setString(4, time1);	
					getvehicleHistory.setString(5, time2);	
					compRS = getvehicleHistory.executeQuery();
					//System.out.println("hiii live vehicle details Details    " +getvehicleHistory.toString());
				int count=1;
				LiveVehicleResponse obj = new LiveVehicleResponse();

				LiveVehicleResponse dd = new LiveVehicleResponse();
				if(compRS.next())
				{
					while (compRS.next()) {

						int inde = 1;

						try {

							SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							SimpleDateFormat sdfnew = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss a");
							java.util.Date parseTimestamp = sdf1.parse(compRS.getString(2));

								dd = new LiveVehicleResponse();
								dd.setUnitid(compRS.getString(1));
								dd.setVehical_no(vehicleno);
								dd.setDatereceived(sdfnew.format(parseTimestamp));
								dd.setTrackdate(parseTimestamp.toLocaleString());
								dd.setLatitude(compRS.getString(3));
								dd.setLongitude(compRS.getString(4));
								dd.setSpeed(Math.round(compRS.getDouble(5)));
								dd.setGpsstatus(getGPS(compRS.getString(6)));
								int head = compRS.getInt(7);
								dd.setHeadings(getDir(head));

								dd.setIgnnumber(getIGN(compRS.getString(8)));

//								dd.setColor(compRS.getString(9));
//								dd.setGpscolor(compRS.getString(10));
//								if (dd.getIgnnumber().equals("0") && dd.getSpeed() > 0.00) {
//									dd.setIgnnumber(getIGN("1"));
//								}
								data1.add(dd);
								
								
								
								try {

								if (Double.parseDouble(dd.getLatitude()) > 0 && Double.parseDouble(dd.getLongitude()) > 0) {
									
									
									//System.out.println("here data list==="+data1.toString());
									fence = "[" + dd.getLongitude() + "," + dd.getLatitude() +"]";
									datalist.add(fence);
//									

									fence = "{" + "  \"type\":\"Feature\"," + "\"icon_url\":" + reticonColor(head) + ""
											+ ",\"track_date\":\"" + dd.getTrackdate() + "\"" + ",\"vehicleno\":\""
											+ dd.getVehical_no() + "\"" + ",\"track_speed\":\"" + dd.getSpeed() + "\""
											+ ",\"track_ign\":\"" + dd.getIgnnumber() + "\"," + " \"properties\":"
											+ "{\"message\":\"" + dd.getVehical_no() + "\",\"trackdate\":\"" + dd.getDatereceived()
											+ "\",\"track_speed\":\"" + dd.getSpeed() + "\",\"track_ign\":\"" + dd.getIgnnumber()
											+ "\",\"track_gps\":\"" + dd.getGpsstatus() + "\",\"color\":\"" + dd.getColor()
											+ "\",\"gpscolor\":\"" + dd.getGpscolor() + "\",\"dirdeg\":\"" + dd.getDirdeg()
											+ "\",\"alertid\":\"" + dd.getAlertname() + "\",\"state\":\"" + dd.getStatename()
											+ "\",\"company\":\"" + dd.getCompany() + "\"}," + " \"geometry\":{"
											+ " \"type\":\"Point\","
											+ " \"coordinates\":[" + dd.getLongitude() + "," + dd.getLatitude() + "] } }";
									
									if(count==1) {									
										
										datalistsp.add(fence);
									}
									else {
										datalistdp = new ArrayList<String>();										
										datalistdp.add(fence);
									}
									//datalist.add(obj);

									count++;

								}
								
								
						}catch (Exception e) {
							System.out.println("getlivevehicles error is : " + e);
						}
					}catch (Exception e) {
						System.out.println("getlivevehicles error is : " + e);
					}
						
					
					 
				} 
			
				try {
					str = "{\"type\":\"LineString\",\"coordinates\":" + datalist.toString() + "}";
					JSONMinify json = new JSONMinify();
					 jsonstr = "{\"type\":\"Feature\",\"properties\":{},\"geometry\":"+ json.minify(str)+"}";
					
				}catch(Exception e) {System.out.println("JSON error==="+e);
				}	
				
				
				mm.setJsnstrstart(datalistsp.toString());
				mm.setJsnstrend(datalistdp.toString());
				mm.setJsnstr(jsonstr);
				
			
				datastr.add(mm);
				//System.out.println("Here data===   "+datastr.toString());
				getvehicleHistory.close();
				compRS.close();
				
				
				}else {
					
				}
				
				
				
				
			}catch (SQLException e) {
				System.err.println(e);
			}finally {
				DBConnection.getCloseConnection(con); 
			}

			return datastr;

	}

	

	


}
