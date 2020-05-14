package providers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import models.Bus;
import models.BusService;

public class BusServiceFileProvider {
	private String filePostFix = "_travels.txt";
	
	public BusServiceFileProvider(String filePostFix) {
		this.filePostFix=filePostFix;
	}
	
public boolean save(String companyName ,BusService busService) {
		
		boolean result = false;
		
		try {
			String fileName = companyName + this.filePostFix;
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName), true));
			writer.write(busService.toFileFormat() + "\n");
			writer.close();
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<BusService> all(String companyName){
		
		ArrayList<BusService> buseServices = new ArrayList<BusService>();
		
		try {
		
			String fileName = companyName + this.filePostFix;
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line = reader.readLine();
			while(line != null) {
				
				buseServices.add(BusService.fromFile(line, companyName));
				
				line = reader.readLine();
			}
			
			reader.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return buseServices;
	}
	
	
	
}
