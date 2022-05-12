package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import DAO.TicketDAO;
import Model.BusinessTicket;

@WebServlet("/business_ticket")
public class BusinessTicketServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private final TicketDAO ticketDao = new TicketDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Gson gson = new Gson();
		
		ArrayList<BusinessTicket> businessTicketList = new ArrayList<>();
		
		PrintWriter out = response.getWriter();

		try {
			businessTicketList = ticketDao.getAllBusinessTicket();
			String result = gson.toJson(businessTicketList);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			out.write(result);
			out.close();
		}
		catch (SQLException | ParseException e) {
			e.printStackTrace();
		}		
	}
}