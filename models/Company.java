package models;

import java.util.ArrayList;

import providers.BusFileProvider;

public class Company {
	
	private String name = null;
	private String ownerName = null;
	
	public Company(String name) {
		this.name=name;
	}
	
	public Company(String name, String ownerName) {
		super();
		this.name = name;
		this.ownerName = ownerName;
	}
	
	public String getName() {
		return name;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toFileFormat() {
		return this.getName() + ':' + this.getOwnerName();
	}
	
	public static Company fromFile(String line) {
		
		String[] parts = line.split(":");
		Company c = new Company(parts[0], parts[1]);
		return c;
		
	}
	
	
	
	
}
