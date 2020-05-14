package models;

public class Bus {
	
	private String name = null;
	private String marquee = null;
	private String model = null;
	private int capacity;
	
	private String driverNameSurname = null;
	
	private Company company = null;
	
	public Bus(String name , String driverNameSurname) {
		this.name=name;
		this.driverNameSurname=driverNameSurname;
		
		}
	
	
	public Bus(String name ,String driverNameSurname ,int capacity) {
		this.capacity=capacity;
		this.name=name;
		this.driverNameSurname=driverNameSurname;
	}

	public Bus(String name, String marquee, String model, int capacity, String driverNameSurname,String companyName) {
		
		this.company=new Company(companyName); 
		this.name = name;
		this.marquee = marquee;
		this.model = model;
		this.capacity = capacity;
		this.driverNameSurname = driverNameSurname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMarquee() {
		return marquee;
	}

	public void setMarquee(String marquee) {
		this.marquee = marquee;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int b) {
		this.capacity -= b  ;
	}

	public String getDriverNameSurname() {
		return driverNameSurname;
	}

	public void setDriverNameSurname(String driverNameSurname) {
		this.driverNameSurname = driverNameSurname;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
	
	public String toFileFormat() {
		return this.getName() + ":"+this.getDriverNameSurname() +":"+ this.getMarquee() + ":" + this.getModel()+":"+this.getCapacity();
	}
	
	public static Bus fromFile(String line , String companyName) {
		String[] parts = line.split(":");
		Bus b = new Bus(parts[0], parts[2], parts[3],Integer.valueOf(parts[4]),parts[1], companyName);
		return b; 
	}

}
