package providers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import models.Company;
import models.Driver;

public class DriverFileProvider {
	
	private String filePostFix = "_drivers.txt";
	
	public DriverFileProvider(String postFix) {
		this.filePostFix = postFix;
	}
	
	public boolean save(String companyName, Driver driver) {
		
		boolean result = false;
		
		try {
			
			String fileName = companyName + this.filePostFix;
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName), true));
			writer.write(driver.toFileFormat() + "\n");
			writer.close();
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Driver> all(String companyName){
		
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		
		try {
			String fileName = companyName + this.filePostFix;
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			
			String line = reader.readLine();
			
			while(line != null) {
				drivers.add(Driver.fromFile(line));
				line = reader.readLine();
			}
			
			reader.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return drivers;
	}
}
