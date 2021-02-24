package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controllayer.ControlPrice;
import databaselayer.DBConnection;
import databaselayer.DatabaseLayerException;
import modellayer.ParkingPrice;

public class TestUpdateFromServer {
	
	private ControlPrice ctrPrice;
	Connection con;
	ResultSet resultset;
	PreparedStatement preparedstatement;
	java.sql.Date dateNow;
	
	@BeforeEach
	public void setup() {
		ctrPrice = new ControlPrice();
		con = DBConnection.getInstance().getDBcon();
		Calendar calendar = Calendar.getInstance();
		dateNow = new java.sql.Date(calendar.getTime().getTime());
	
	}
	
	/**
	 * 
	 * @throws DatabaseLayerException
	 * @throws SQLException
	 * Case: UP1
	 */
	@Test
	public void checkIfDatabaseUpdates() throws DatabaseLayerException, SQLException {

		//Arrange
		int zoneID = 1;
		int DBPrice = 0;

		//Act
		String select = "SELECT price FROM PPrice WHERE pZone_id = '"+zoneID+"' AND startTime < '" + dateNow + "' ";
		preparedstatement = con.prepareStatement(select);
		resultset = preparedstatement.executeQuery();
		if (resultset.next()) {
			DBPrice = resultset.getInt(1);
		}

		ParkingPrice price = ctrPrice.getPriceRemote(zoneID);
		
		//Assert
		assertEquals(DBPrice, price.getParkingPrice());
	}
	
	
	@AfterEach
	public void cleanUp() {
		DBConnection.closeConnection();
	}	
}
