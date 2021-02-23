package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controllayer.ControlPrice;
import databaselayer.DBConnection;
import databaselayer.DatabaseLayerException;
import modellayer.PPrice;

public class TestUpdateFromServer {
	
	private ControlPrice ctrPrice;
	Connection con;
	ResultSet resultset;
	PreparedStatement preparedstatement;
	java.sql.Date dateNow;
	
	@Before
	public void setup() {
		ctrPrice = new ControlPrice();
		con = DBConnection.getInstance().getDBcon();
		Calendar calendar = Calendar.getInstance();
		dateNow = new java.sql.Date(calendar.getTime().getTime());
	
	}
	
	@Test
	public void checkIfDatabaseUpdates() throws DatabaseLayerException, SQLException {
		
		int zoneID = 1;
		int DBPrice = 0;

		String select = "SELECT price FROM PPrice WHERE pZone_id = '"+zoneID+"' AND startTime < '" + dateNow + "' ";
		preparedstatement = con.prepareStatement(select);
		resultset = preparedstatement.executeQuery();
		if (resultset.next()) {
			DBPrice = resultset.getInt(1);
		}

		
		
		PPrice price = ctrPrice.getPriceRemote(zoneID);
		
		assertEquals(DBPrice, price.getParkingPrice());
	}
	
	
	@After
	public void cleanUp() {
		DBConnection.closeConnection();
	}	
}
