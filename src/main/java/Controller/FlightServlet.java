package Controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Common.Common;
import DAO.FlightDAO;
import Model.FlightSchedule;

@WebServlet("/flight_schedule")
public class FlightServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final FlightDAO flightDao = new FlightDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		
		String flightId = request.getParameter("flight_id");
		
		ArrayList<FlightSchedule> list = new ArrayList<>();
		
		PrintWriter out = response.getWriter();
		if(flightId == null) {
			try {
				list = flightDao.getAllFlightSchedule();
				String result = gson.toJson(list);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-16");
				out.write(result);
			}
			catch(SQLException | ParseException e) {
				e.printStackTrace();
			}
		}
		else {
			FlightSchedule flight = flightDao.getFlightSchedule(Integer.parseInt(flightId));
			String result = gson.toJson(flight);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(result);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		InputStream input = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(input);
		
		FlightSchedule flight = gson.fromJson(result, FlightSchedule.class);
		
		try {
			flightDao.insert(flight.getDeparture(), flight.getDestination(), flight.getDistance(), flight.getFlightTime());
			out.write("Insert successfully");
		}
		catch (SQLException e ) {
			e.printStackTrace();
			out.write("Failed to insert");
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);
		
		FlightSchedule flight = gson.fromJson(result, FlightSchedule.class);
		
		try {
			flightDao.update(flight.getDeparture(), flight.getDestination(), flight.getDistance(), flight.getFlightTime(), flight.getFlightId());
			out.write("Update successfully");
		}
		catch (SQLException e) {
			e.printStackTrace();
			out.write("Failed to update");
		}
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);
		
		FlightSchedule flight = gson.fromJson(result, FlightSchedule.class);
		
		try {
			flightDao.delete(flight.getFlightId());
			out.write("Delete successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
			out.write("Failed to delete");
		}
	}
}
