package Model;

public class Ticket {
	protected int ticketId;
    protected String cusName;
    protected String cusId;
    protected String departure;
    protected String destination;
    protected int distance;
    protected String flightTime;
    protected String saleDate;
    protected int ticketClass;
    protected int flightId;

    public Ticket(int ticketId, String cusName, String cusId, String departure, String destination, int distance, String flightTime, String saleDate, int ticketClass,int flightId) {
        this.ticketId = ticketId;
        this.cusName = cusName;
        this.cusId = cusId;
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
        this.flightTime = flightTime;
        this.saleDate = saleDate;
        this.ticketClass = ticketClass;
        this.flightId = flightId;
    }

    public Ticket(String cusName, String cusId, int ticketClass, int flightId) {
    	this.cusName = cusName;
        this.cusId = cusId;
        this.ticketClass = ticketClass;
        this.flightId = flightId;
    }
    
    public Ticket(String cusName, String departure, String destination, String flightTime) {
    	this.cusName = cusName;
    	this.departure = departure;
        this.destination = destination;
        this.flightTime = flightTime;
    }
    
    public Ticket(int ticketId,String cusName,String cusId,int ticketClass){
        this.ticketId = ticketId;
        this.cusName = cusName;
        this.cusId = cusId;
        this.ticketClass = ticketClass;
    }
    
    public Ticket(int ticketId){
        this.ticketId = ticketId;
    }
    
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusId() {
        return cusId;
    }

    public void setCusId(String cusId) {
        this.cusId = cusId;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(String flightTime) {
        this.flightTime = flightTime;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public int getTicketClass() {
		return ticketClass;
	}

	public void setTicketClass(int ticketClass) {
		this.ticketClass = ticketClass;
	}

	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	@Override
    public String toString() {
        return "Ticket{" +
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
                '}';
    }
}
