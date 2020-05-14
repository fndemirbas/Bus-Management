package models;



public class BusService {
	private String name=null;;
	private String where=null;
	private String to=null;
	private String when=null ;
	private Bus bus=null;
	private Company company=null;
	
	public BusService(String name, String where, String to, String when, String companyName,String bus) {
		super();	
		this.name = name;
		this.where = where;
		this.to = to;
		this.when = when;
		String []splited=bus.split("-");	
		this.bus = new Bus(splited[0],splited[1], Integer.valueOf(splited[2]));
		this.company=new Company(companyName);
	}
	
	public BusService(String travelName ,String busName ,String driverNameSurname,int capacity ,String where , String to , String when) {
		this.name=travelName;
		this.bus=new Bus(busName, driverNameSurname, capacity);
		this.where=where;
		this.to=to;
		this.when=when;
	}
	
	public String getName() {
		return name;
	}
	public String getWhere() {
		return where;
	}
	public String getTo() {
		return to;
	}
	public String getWhen() {
		return when;
	}
	
	
	public Bus getBus() {
		return bus;
	}

	public Company getCompany() {
		return company;
	}

	public String toFileFormat() {
		return this.getName() + ':'+this.bus.getName()+"-"+this.bus.getDriverNameSurname()+"-"+this.bus.getCapacity()+":"+this.getWhere()+":"+this.getTo()+":"+this.getWhen();
	}
	
	public static BusService fromFile(String line,String company) {
		
		String[] parts = line.split(":");
		BusService bs = new BusService(parts[0], parts[2], parts[3], parts[4], company, parts[1]);
		
		return bs;
		
	}
	
	
	
	
}
