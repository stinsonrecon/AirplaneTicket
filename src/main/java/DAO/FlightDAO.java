package DAO;

import Model.FlightSchedule;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import Connection.DBConnect;

public class FlightDAO {
	public ArrayList<FlightSchedule> getAllFlightSchedule() throws SQLException, ParseException {
        Connection connection = DBConnect.getConnection();
        String select = "select * from flight_schedule";
        PreparedStatement pe = connection.prepareStatement(select);

        ArrayList<FlightSchedule> list = new ArrayList<>();

        ResultSet re = pe.executeQuery();
        while (re.next()){
            int flightId = re.getInt("flight_id");
            String departure = re.getString("departure");
            String destination = re.getString("destination");
            int distance = re.getInt("distance");

            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date flightDate = simpleDateFormat.parse(re.getString("flight_time"));

            String flightTime = simpleDateFormat.format(flightDate);

            FlightSchedule bt = new FlightSchedule(flightId,departure,destination,distance,flightTime);

            list.add(bt);
        }
        connection.close();
        return list;
    }

    public FlightSchedule getFlightSchedule(int flightId) {
        Connection connection = DBConnect.getConnection();
        String select = "select * from flight_schedule where flight_id = " + flightId;
        PreparedStatement pe;

        try {
            pe = connection.prepareStatement(select);
            ResultSet re = pe.executeQuery();
            while (re.next()) {
                String departure = re.getString("departure");
                String destination = re.getString("destination");
                int distance = re.getInt("distance");

                DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date flightDate = simpleDateFormat.parse(re.getString("flight_time"));

                String flightTime = simpleDateFormat.format(flightDate);

                return new FlightSchedule(flightId,departure,destination,distance,flightTime);
            }
        }
        catch (SQLException | ParseException e){
            return null;
        }
        return null;
    }

    public void insert(String departure, String destination, int distance, String flightTime) throws SQLException {
        Connection connection = DBConnect.getConnection();
        String insert = "insert into flight_schedule( departure, destination, distance, flight_time)" +
                " value ('" + departure +
                "', '" + destination +
                "', " + distance +
                " , '" + flightTime + "')";
        PreparedStatement pe = connection.prepareStatement(insert);
        pe.execute();
        connection.close();
    }

    public void update(String departure, String destination, int distance, String flightTime, int flightId) throws SQLException{
        Connection connection = DBConnect.getConnection();
        String update = "update flight_schedule set departure = '" + departure +
                "', destination = '" + destination +
                "', distance = " + distance +
                ", flight_time = '" + flightTime +
                "' where flight_id = " + flightId;
        PreparedStatement pe = connection.prepareStatement(update);
        pe.execute();
        connection.close();
    }

    public void delete(int flightId) throws SQLException{
        Connection connection = DBConnect.getConnection();
        String delete = "delete from flight_schedule where flight_id = " + flightId;
        PreparedStatement pe = connection.prepareStatement(delete);
        pe.execute();
        connection.close();
    }
}
