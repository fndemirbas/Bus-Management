package models;

public class Booking {

	private String passengerNameSurname=null;
	private Company company=null;
	private BusService busService=null;
	
	
	public Booking(String passengerNameSurname, String company, String busService) {
		super();
		this.passengerNameSurname = passengerNameSurname;
		this.company = new Company(company);
		String[] splited=busService.split("-");
		this.busService = new BusService(splited[0], splited[1],splited[2],Integer.valueOf( splited[3]), splited[4], splited[5], splited[6]);
	}
	
	public String getPassengerNameSurname() {
		return passengerNameSurname;
	}
	public Company getCompany() {
		return company;
	}
	public BusService getBusService() {
		return busService;
	}
	

	public String toFileFormat() {
		return this.passengerNameSurname;
	}
	
	public static Booking fromFile(String line,String company,String busService) {
		
		Booking d = new Booking(line,company,busService);
		return d;
	}
	
	
	
	
	
	
	
	
	
}
