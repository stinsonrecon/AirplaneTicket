package DAO;

import Model.BusinessTicket;
import Model.GeneralTicket;
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

public class TicketDAO {
	 public ArrayList<BusinessTicket> getAllBusinessTicket() throws SQLException, ParseException {
	        Connection connection = DBConnect.getConnection();
	        String select = "select * from ticket_infor" +
	                " inner join flight_schedule on ticket_infor.flight_id = flight_schedule.flight_id" +
	                " inner join ticket_class on ticket_infor.ticket_class = ticket_class.ticket_class" +
	                " where class_name = 'Vé thương gia'";
	        PreparedStatement pe = connection.prepareStatement(select);

	        ArrayList<BusinessTicket> list = new ArrayList<>();

	        ResultSet re = pe.executeQuery();
	        while (re.next()){
	            int ticketId = re.getInt("ticket_id");
	            String cusName = re.getString("cus_name");
	            String cusId = re.getString("cus_id");
	            String departure = re.getString("departure");
	            String destination = re.getString("destination");
	            int distance = re.getInt("distance");

	            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date flightDate = simpleDateFormat.parse(re.getString("flight_time"));
	            Date saleTime = simpleDateFormat.parse(re.getString("sale_date"));

	            String flightTime = simpleDateFormat.format(flightDate);
	            String saleDate = simpleDateFormat.format(saleTime);
	            
	            int ticketClass = re.getInt("ticket_class");
	            int flightId = re.getInt("flight_id");

	            BusinessTicket bt = new BusinessTicket(ticketId,cusName,cusId,departure,destination,distance,flightTime,saleDate, ticketClass, flightId);

	            list.add(bt);
	        }
	        connection.close();
	        return list;
	    }

	    public ArrayList<GeneralTicket> getAllGeneralTicket() throws SQLException, ParseException {
	        Connection connection = DBConnect.getConnection();
	        String select = "select * from ticket_infor" +
	                " inner join flight_schedule on ticket_infor.flight_id = flight_schedule.flight_id" +
	                " inner join ticket_class on ticket_infor.ticket_class = ticket_class.ticket_class" +
	                " where class_name = 'Vé phổ thông'";
	        PreparedStatement pe = connection.prepareStatement(select);

	        ArrayList<GeneralTicket> list = new ArrayList<>();

	        ResultSet re = pe.executeQuery();
	        while (re.next()){
	            int ticketId = re.getInt("ticket_id");
	            String cusName = re.getString("cus_name");
	            String cusId = re.getString("cus_id");
	            String departure = re.getString("departure");
	            String destination = re.getString("destination");
	            int distance = re.getInt("distance");

	            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date flightDate = simpleDateFormat.parse(re.getString("flight_time"));
	            Date saleTime = simpleDateFormat.parse(re.getString("sale_date"));

	            String flightTime = simpleDateFormat.format(flightDate);
	            String saleDate = simpleDateFormat.format(saleTime);
	            
	            int ticketClass = re.getInt("ticket_class");
	            int flightId = re.getInt("flight_id");

	            GeneralTicket bt = new GeneralTicket(ticketId,cusName,cusId,departure,destination,distance,flightTime,saleDate, ticketClass, flightId);

	            list.add(bt);
	        }
	        connection.close();
	        return list;
	    }

	    public ArrayList<Object> getAllTicket() throws SQLException, ParseException {
	        Connection connection = DBConnect.getConnection();
	        String select = "select * from ticket_infor" +
	                " inner join flight_schedule on ticket_infor.flight_id = flight_schedule.flight_id" +
	                " inner join ticket_class on ticket_infor.ticket_class = ticket_class.ticket_class";
	        PreparedStatement pe = connection.prepareStatement(select);

	        ArrayList<Object> list = new ArrayList<>();

	        ResultSet re = pe.executeQuery();
	        while (re.next()){
	            int ticketId = re.getInt("ticket_id");
	            String cusName = re.getString("cus_name");
	            String cusId = re.getString("cus_id");
	            String departure = re.getString("departure");
	            String destination = re.getString("destination");
	            int distance = re.getInt("distance");

	            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date flightDate = simpleDateFormat.parse(re.getString("flight_time"));
	            Date saleTime = simpleDateFormat.parse(re.getString("sale_date"));

	            String flightTime = simpleDateFormat.format(flightDate);
	            String saleDate = simpleDateFormat.format(saleTime);

	            int ticketClass = re.getInt("ticket_class");
	            int flightId = re.getInt("flight_id");

	            if(ticketClass == 1){
	                list.add(new GeneralTicket(ticketId,cusName,cusId,departure,destination,distance,flightTime,saleDate,ticketClass,flightId));
	            }
	            else if(ticketClass == 2){
	                list.add(new BusinessTicket(ticketId,cusName,cusId,departure,destination,distance,flightTime,saleDate,ticketClass,flightId));
	            }
	        }
	        connection.close();
	        return list;
	    }

	    public Object findTicket(String cusName, String destination, String departure,String flightTime){
	        Connection connection = DBConnect.getConnection();
	        String select = "select * from ticket_infor" +
	                " inner join flight_schedule on ticket_infor.flight_id = flight_schedule.flight_id" +
	                " inner join ticket_class on ticket_infor.ticket_class = ticket_class.ticket_class" +
	                " where cus_name = '" + cusName + "'" +
	                " and destination = '" + destination + "'" +
	                " and  departure = '" + departure + "'" +
	                " and flight_time = '" + flightTime + "'";
	        PreparedStatement pe;
	        
	        try {
	            pe = connection.prepareStatement(select);
	            ResultSet res = pe.executeQuery();
	            while (res.next()){
	                int ticketId = res.getInt("ticket_id");
	                String cusId = res.getString("cus_id");
	                int distance = res.getInt("distance");

	                DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	                Date saleTime = simpleDateFormat.parse(res.getString("sale_date"));

	                String saleDate = simpleDateFormat.format(saleTime);
	                int ticketClass = res.getInt("ticket_class");
		            int flightId = res.getInt("flight_id");
		            
	                if(ticketClass == 1){
	                    connection.close();
	                    return new GeneralTicket(ticketId,cusName,cusId,departure,destination,distance,flightTime,saleDate,ticketClass,flightId);
	                }
	                else if(ticketClass == 2){
	                    connection.close();
	                    return new BusinessTicket(ticketId,cusName,cusId,departure,destination,distance,flightTime,saleDate,ticketClass,flightId);
	                }
	            }
	        }
	        catch (SQLException | ParseException e){
	            return null;
	        }
	        return null;
	    }

	    public void insert(String cusName, String cusId, int ticketClass, int flightId) throws SQLException {
	        Connection connection = DBConnect.getConnection();
	        String insert = "insert into ticket_infor(cus_name, cus_id, ticket_class,flight_id)" +
	                " value ('" + cusName +
	                "', '" + cusId +
	                "'," + ticketClass +
	                ", " + flightId + ")";
	        System.out.println(insert);
	        PreparedStatement pe = connection.prepareStatement(insert);
	        pe.execute();
	        connection.close();
	    }

	    public void update(int ticketId, String cusName, String cusId, int ticketClass) throws SQLException{
	        Connection connection = DBConnect.getConnection();
	        String update = "update ticket_infor set cus_name = '" + cusName +
	                "', cus_id = '" + cusId +
	                "', ticket_class = " + ticketClass + " where ticket_id = " + ticketId;
	        PreparedStatement pe = connection.prepareStatement(update);
            pe.execute();
            connection.close();
	    }

	    public void delete(int ticketId) throws SQLException{
	        Connection connection = DBConnect.getConnection();
	        String delete = "delete from ticket_infor where ticket_id = " + ticketId;
	        PreparedStatement pe = connection.prepareStatement(delete);
            pe.execute();
            connection.close();
	    }

	    public ArrayList<Object> statisticsPrice(int price) throws SQLException, ParseException {
	        Connection connection = DBConnect.getConnection();
	        String select = "select * from ticket_infor" +
	                " inner join flight_schedule on ticket_infor.flight_id = flight_schedule.flight_id" +
	                " inner join ticket_class on ticket_infor.ticket_class = ticket_class.ticket_class" +
	                " where distance*2000 > " + price + " and class_name = 'Vé phổ thông'" +
	                " or distance*3000 + 1000000 > " + price + " and class_name = 'Vé thương gia';";
	        
	        PreparedStatement pe = connection.prepareStatement(select);
	        ResultSet re = pe.executeQuery();

	        ArrayList<Object> list = new ArrayList<>();

	        while (re.next()){
	            int ticketId = re.getInt("ticket_id");
	            String cusName = re.getString("cus_name");
	            String cusId = re.getString("cus_id");
	            String departure = re.getString("departure");
	            String destination = re.getString("destination");
	            int distance = re.getInt("distance");

	            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date flightDate = simpleDateFormat.parse(re.getString("flight_time"));
	            Date saleTime = simpleDateFormat.parse(re.getString("sale_date"));

	            String flightTime = simpleDateFormat.format(flightDate);
	            String saleDate = simpleDateFormat.format(saleTime);

	            int ticketClass = re.getInt("ticket_class");
	            int flightId = re.getInt("flight_id");

	            if(ticketClass == 1){
	                list.add(new GeneralTicket(ticketId,cusName,cusId,departure,destination,distance,flightTime,saleDate,ticketClass,flightId));
	            }
	            else if(ticketClass == 2){
	                list.add(new BusinessTicket(ticketId,cusName,cusId,departure,destination,distance,flightTime,saleDate,ticketClass,flightId));
	            }
	        }
	        return list;
	    }

	    public int totalRevenue(String start, String end) throws SQLException, ParseException {
	        Connection connection = DBConnect.getConnection();
	        String select = "select * from ticket_infor" +
	                " inner join flight_schedule on ticket_infor.flight_id = flight_schedule.flight_id" +
	                " inner join ticket_class on ticket_infor.ticket_class = ticket_class.ticket_class" +
	                " where sale_date between '" + start + "' and '" + end + "'";
	        PreparedStatement pe = connection.prepareStatement(select);
	        
	        ResultSet re = pe.executeQuery();

	        ArrayList<Integer> list = new ArrayList<>();

	        while (re.next()){
	            int ticketId = re.getInt("ticket_id");
	            String cusName = re.getString("cus_name");
	            String cusId = re.getString("cus_id");
	            String departure = re.getString("departure");
	            String destination = re.getString("destination");
	            int distance = re.getInt("distance");

	            DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Date flightDate = simpleDateFormat.parse(re.getString("flight_time"));
	            Date saleTime = simpleDateFormat.parse(re.getString("sale_date"));

	            String flightTime = simpleDateFormat.format(flightDate);
	            String saleDate = simpleDateFormat.format(saleTime);

	            int ticketClass = re.getInt("ticket_class");
	            int flightId = re.getInt("flight_id");

	            if(ticketClass == 1){
	                GeneralTicket generalTicket = new GeneralTicket(ticketId,cusName,cusId,departure,destination,distance,flightTime,saleDate,ticketClass,flightId);
	                list.add(generalTicket.getPrice());
	            }
	            else if(ticketClass == 2){
	                BusinessTicket businessTicket = new BusinessTicket(ticketId,cusName,cusId,departure,destination,distance,flightTime,saleDate,ticketClass,flightId);
	                list.add(businessTicket.getPrice());
	            }
	        }

	        int sum = 0;
	        for (Integer integer : list) {
	            sum += integer;
	        }
	        return sum;
	    }
}
