package providers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import exceptions.NotAddPassgenger;
import models.Booking;
import models.Bus;
import models.Company;
import models.Driver;

public class BookingFileProvider {

	private String filePostFix="_passengers.txt";
	//private ArrayList<Bus> buses =new ArrayList<Bus>();
	

	public BookingFileProvider(String postFix,String companyName ) {
		super();
		this.filePostFix = postFix;
		
		
	}
	
	public int save(String companyName,String busServiceName, Booking booking) {
		
		int result = 0;
		
		
		try {
			
			
			int capacity=Integer.valueOf(booking.getBusService().getBus().getCapacity());
			boolean r=sellTicket(companyName, busServiceName, capacity);
			boolean rs=equals(companyName, busServiceName, booking.getPassengerNameSurname());
			if (rs) {
				if (r) {
					String fileName = companyName +"_"+busServiceName+ this.filePostFix;
					BufferedWriter writer = new BufferedWriter(new FileWriter(new File(fileName), true));
					writer.write(booking.toFileFormat() + "\n");
					writer.close();
					result = 1;
				} else {
					System.out.println("Otobüs dolu");
				}
			} 
			else {
				result=-1;
				
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public ArrayList<Booking> all(String companyName , String busServiceName){
		
		ArrayList<Booking> booking = new ArrayList<Booking>();
		
		try {
	
			String fileName = companyName +"_"+busServiceName+ this.filePostFix;
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			
			String line = reader.readLine();
			
			while(line != null) {
				booking.add(Booking.fromFile(line,companyName,busServiceName));
				line = reader.readLine();
			}
			
			reader.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return booking;
	}
	
	public boolean sellTicket(String companyName , String travelName,int capacity) {
		
		int lines=getLineNumber(companyName, travelName);
		if (capacity-lines>0) {
			return true;
		} else {
			return false;
		}
		
		
			
	}
		
	public int getLineNumber(String companyName , String travelName) {
		int lineNumber=0;
		try {
			
			String fileName = companyName +"_"+travelName+ this.filePostFix;
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			
			String line = reader.readLine();
			
			while(line != null) {
				lineNumber++;
				line = reader.readLine();
			}
			
			reader.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return lineNumber;
	}

	
	public boolean equals(String companyName , String travelName,String passenger) {
		boolean r = true;
		try {
			String fileName = companyName +"_"+travelName+ this.filePostFix;
			BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
			String line = reader.readLine();
			
			while(line != null) {
				line = reader.readLine();
				if (line.equals(passenger)) {
					r= false;
				}
			}
			
			reader.close();
		
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return r;
	
	}
	
	
	
}
	

