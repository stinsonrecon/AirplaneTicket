package Model;

public class GeneralTicket extends Ticket{
	private final int price;

    public GeneralTicket(int ticketId, String cusName, String cusId, String departure, String destination, int distance, String flightTime, String saleDate, int ticketClass, int flightId) {
        super(ticketId, cusName, cusId, departure, destination, distance, flightTime, saleDate, ticketClass, flightId);
        price = getDistance() * 2000;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "General Ticket{" +
                "ticketId=" + ticketId +
                ", cusName='" + cusName + '\'' +
                ", cusId='" + cusId + '\'' +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", distance=" + distance +
                ", flightTime='" + flightTime + '\'' +
                ", saleDate='" + saleDate + '\'' +
                ", ticketClass='" + ticketClass + '\'' +
                ", flightId='" + flightId + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
