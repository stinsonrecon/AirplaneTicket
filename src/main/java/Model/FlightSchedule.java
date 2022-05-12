package Model;

public class FlightSchedule {
	private int flightId;
    private String departure;
    private String destination;
    private int distance;
    private String flightTime;

    public FlightSchedule(int flightId, String departure, String destination, int distance, String flightTime) {
        this.flightId = flightId;
        this.departure = departure;
        this.destination = destination;
        this.distance = distance;
        this.flightTime = flightTime;
    }
    
    public FlightSchedule(int flightId) {
    	this.flightId = flightId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
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

    @Override
    public String toString() {
        return "FlightSchedule{" +
                "flightId=" + flightId +
                ", departure='" + departure + '\'' +
                ", destination='" + destination + '\'' +
                ", distance=" + distance +
                ", flightTime='" + flightTime + '\'' +
                '}';
    }
}
