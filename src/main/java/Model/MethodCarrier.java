package Model;

public class MethodCarrier {
	private int price;
	private String startDate;
	private String endDate;
	
	public MethodCarrier(int price) {
		this.price = price;
	}
	
	public MethodCarrier(String startDate, String endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
