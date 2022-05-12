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

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import Common.Common;
import DAO.TicketDAO;
import Model.MethodCarrier;
import Model.Ticket;

@WebServlet("/ticket_info")
public class TicketServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final TicketDAO ticketDao = new TicketDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		
		ArrayList<Object> ticketList = new ArrayList<>();
		
		
		PrintWriter out = response.getWriter();
		
		try {
			ticketList = ticketDao.getAllTicket();
			String result = gson.toJson(ticketList);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(result);
			out.close();
		}
		catch(SQLException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String action = request.getQueryString();
		if(action.equals("action=insert")) {
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			
			InputStream input = new BufferedInputStream(request.getInputStream());
			String result = Common.convertToString(input);
			
			System.out.println(result);
			
			Ticket ticket = gson.fromJson(result, Ticket.class);
			
			try {
				ticketDao.insert(ticket.getCusName(), ticket.getCusId(),  ticket.getTicketClass(), ticket.getFlightId());
				out.write("Insert successfully");
			}
			catch (SQLException e ) {
				e.printStackTrace();
				out.write("Failed to insert");
			}
		}
		if(action.equals("action=find")){
			PrintWriter out = response.getWriter();
			Gson gson = new Gson();
			
			InputStream input = new BufferedInputStream(request.getInputStream());
			String result = Common.convertToString(input);
			
			Ticket ticket = gson.fromJson(result, Ticket.class);
			
			Object resTicket = ticketDao.findTicket(ticket.getCusName(), ticket.getDestination(), ticket.getDeparture(), ticket.getFlightTime());
			String res = gson.toJson(resTicket);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(res);
			out.close();
		}
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);
		
		Ticket ticket = gson.fromJson(result, Ticket.class);
		
		try {
			ticketDao.update(ticket.getTicketId(), ticket.getCusName(), ticket.getCusId(), ticket.getTicketClass());
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
		
		Ticket ticket = gson.fromJson(result, Ticket.class);
		
		try {
			ticketDao.delete(ticket.getTicketId());
			out.write("Delete successfully");
		}
		catch(SQLException e) {
			e.printStackTrace();
			out.write("Failed to delete");
		}
	}
	
	protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		Gson gson = new Gson();
		
		InputStream i = new BufferedInputStream(request.getInputStream());
		String result = Common.convertToString(i);
		
		JSONArray jsonArray = new JSONArray(result);
		JSONObject object = jsonArray.getJSONObject(0);
		
		MethodCarrier methodCarrier = gson.fromJson(object.toString(), MethodCarrier.class);
		
		if(methodCarrier.getPrice() != 0) {
			ArrayList<Object> list;
			try {
				list = ticketDao.statisticsPrice(methodCarrier.getPrice());
				String res = gson.toJson(list);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(res);
				out.close();
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				int price = ticketDao.totalRevenue(methodCarrier.getStartDate(), methodCarrier.getEndDate());
				String res = gson.toJson(price);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.write(res);
				out.close();
			} catch (SQLException | ParseException e) {
				e.printStackTrace();
			}
		}
	}
}
