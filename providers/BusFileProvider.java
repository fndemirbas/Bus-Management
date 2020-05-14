package providers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import models.Bus;
import models.Company;
import models.Driver;

public class BusFileProvider {
	
	private String filePostFix = "_buses.txt";
	
	public BusFileProvider(String postFix) {
		this.filePostFix = postFix;
	}
	
	
	
	
	public boolean save(String companyName ,Bus bus) {
		
		boolean result = false;
		
		try {
			
			String fileName = companyName + this.filePostFix;
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName), true));
			writer.write(bus.toFileFormat() + "\n");
			writer.close();
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Bus> all(String companyName){
		
		ArrayList<Bus> buses = new ArrayList<Bus>();
		
		try {
			String fileName = companyName + this.filePostFix;
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			
			String line = reader.readLine();
			
			while(line != null) {
				buses.add(Bus.fromFile(line,companyName));
				line = reader.readLine();
			}
			
			reader.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return buses;
	}
	
	
	
}
