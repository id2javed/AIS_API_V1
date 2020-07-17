package com.app.main.business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.app.connection.DBConnection;
import com.app.main.dao.VehicleData;
import com.app.main.dao.VehicleInstallationData;
import com.app.main.model.DeviceDetailsResponse;
import com.app.main.model.VehicleDetailsResponse;
import com.app.queryconstants.QueryConstants;

public class VehicleDetailsManager {

	public VehicleDetailsResponse vehicle(String[] spltstr, VehicleData vehdata) {
		VehicleDetailsResponse vehdet = new VehicleDetailsResponse();

		List<VehicleDetailsResponse> data = new ArrayList<VehicleDetailsResponse>();

		long start = 0, end = 0;
		String seraccbyoffset = "";
		try {
			if (vehdata.getPageno().equals("NA")) {
				seraccbyoffset = "";
			} else if (vehdata.getItemsPerPage().equals("NA")) {
				seraccbyoffset = "";
			} else

			if (Integer.parseInt(vehdata.getPageno()) != 1) {
				start = Integer.parseInt(vehdata.getItemsPerPage()) * (Integer.parseInt(vehdata.getPageno()) - 1);
				end = start + Integer.parseInt(vehdata.getItemsPerPage());
				seraccbyoffset = " offset " + start + " limit " + vehdata.getItemsPerPage() + "";
				start++;
			} else {
				start = 0;
				end = start + Integer.parseInt(vehdata.getItemsPerPage());
				seraccbyoffset = " offset " + start + " limit " + vehdata.getItemsPerPage() + "  ";
				start++;
			}

		} catch (Exception e) {
			System.err.println("e ----  " + e);
		}

		Connection con = DBConnection.getMainConnection();
		PreparedStatement getveh;
		try {
			getveh = con.prepareStatement(QueryConstants.vehicledetailsquery);
			getveh.setString(1, "vehicledetails");
			getveh.setString(2, spltstr[4]);
			getveh.setString(3, spltstr[3]);
			getveh.setString(4, spltstr[4]);
			getveh.setString(5, spltstr[0]);
			getveh.setString(6, vehdata.getSearchby());
			getveh.setString(7, seraccbyoffset);
			getveh.setString(8, "");

			System.out.println("Vehicle Details Query ==>> " + getveh.toString());

			ResultSet rs = getveh.executeQuery();

//			mv.vehicleid, vehiclemakeid, vehiclemodelid, vehicletypeid, vehicleregno, vehiclechasisno, vehicleengineno, 
//			vehicleregdate, vehiclereceiptno, 
//			vehiclemfgyear, mv.customerid, vehiclestatecode, vehiclertoid, vehicletype,

			while (rs.next()) {
				VehicleDetailsResponse obj = new VehicleDetailsResponse();
				obj.setVehicleid(rs.getString(1));
				obj.setVehiclemakeid(rs.getString(2));
				obj.setVehiclemodelid(rs.getString(3));
				obj.setVehicletypeid(rs.getString(4));

				obj.setVehicleregno(rs.getString(5));
				obj.setVehiclechasisno(rs.getString(6));
				obj.setVehicleengineno(rs.getString(7));
				obj.setVehicleregdate(rs.getString(8));
				obj.setVehiclereceiptno(rs.getString(9));

				obj.setVehiclemfgyear(rs.getString(10));
				obj.setCustomerid(rs.getString(11));
				obj.setVehiclestatecode(rs.getString(12));
				obj.setVehiclertoid(rs.getString(13));
				obj.setVehicletype(rs.getString(14));

				obj.setVehiclemakename(rs.getString(15));
				obj.setVehiclemodelname(rs.getString(16));
				obj.setVehicleclass(rs.getString(17));
				obj.setDeviceimeino(rs.getString(18));
				obj.setDeviceuniqueno(rs.getString(19));
				obj.setDeviceiccidno(rs.getString(20));
				obj.setDistributorname(rs.getString(21));
				obj.setDealername(rs.getString(22));
				obj.setDevmodelname(rs.getString(23));

				obj.setCustomername(rs.getString(24));
				obj.setCustomermobileno(rs.getString(25));
				obj.setCustomeraddress(rs.getString(26));
				obj.setCustomercity(rs.getString(27));
				obj.setCustomerstate(rs.getString(28));
				obj.setDealercontactmobile1(rs.getString(29));

				obj.setDevmodelid(rs.getString(30));
				obj.setDevmodelcode(rs.getString(31));
				// System.out.println("Device Details Query Count ==>>
				// "+obj.getVehiclemakename());

				data.add(obj);
			}
			int count = 0;
			ResultSet rs4 = null;

			try {
				PreparedStatement getvehcount;
				getvehcount = con.prepareStatement(QueryConstants.vehicledetailsquery);
				getvehcount.setString(1, "vehicledetails");
				getvehcount.setString(2, spltstr[4]);// company id send owners id
				getvehcount.setString(3, spltstr[3]);// roleid
				getvehcount.setString(4, spltstr[4]);// ownersid
				getvehcount.setString(5, spltstr[0]);// loginid
				getvehcount.setString(6, vehdata.getSearchby());// search by
				getvehcount.setString(7, "");
				getvehcount.setString(8, "Count");

				// System.out.println("Device Details Query Count ==>>
				// "+getdevcount.toString());

				rs4 = getvehcount.executeQuery();

				while (rs4.next()) {

					count = rs4.getInt(1);
				}

			} catch (Exception e) {
				System.out.println("Error: " + e);
			}

			vehdet = new VehicleDetailsResponse();
			vehdet.setData(data);
			vehdet.setTotal(count);

			vehdet.setResponse("Successfully Saved!");

		} catch (Exception e) {

			System.out.println("Vehicle Details Exception ==>> " + e);

		} finally {
			DBConnection.getCloseConnection(con);
		}

		// return data;
		return vehdet;

	}

}
