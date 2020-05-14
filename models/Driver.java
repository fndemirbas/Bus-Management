package models;

public class Driver {
	
	private String name;
	private String surname;
	private int age;
	
	private Company company = null;

	public Driver() {
		// TODO Auto-generated constructor stub
	}
	
	public Driver(String name, String surname, int age) {
		super();
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	

	public String getSurname() {
		return surname;
	}

	

	public int getAge() {
		return age;
	}

	

	public Company getCompany() {
		return company;
	}

	
	
	public String toFileFormat() {
		return this.getName() + ":" + this.getSurname() + ":" + this.getAge();
	}
	
	public static Driver fromFile(String line) {
		
		String[] parts = line.split(":");
		Driver d = new Driver(parts[0], parts[1], Integer.valueOf(parts[2]));
		return d;
	}
	
}
