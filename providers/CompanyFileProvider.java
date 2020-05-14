package providers;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import models.Company;

public class CompanyFileProvider {
	
	private String fileName = "companies.txt";
	
	public CompanyFileProvider(String fileName) {
		this.fileName = fileName;
	}
	
	public boolean save(Company company) {
		
		boolean result = false;
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(this.fileName), true));
			writer.write(company.toFileFormat() + "\n");
			writer.close();
			result = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
	public Company detail(String companyName) {
		
		ArrayList<Company> companies = this.all();
		Company foundedCompany = null;
		for(Company c : companies) {
			if(c.getName().equals(companyName)) {
				foundedCompany = c;
			}
		}
		return foundedCompany;
	}
	
	public ArrayList<Company> all(){
		
		ArrayList<Company> companies = new ArrayList<Company>();
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(new File(this.fileName)));
			
			String line = reader.readLine();
			
			while(line != null) {
				companies.add(Company.fromFile(line));
				line = reader.readLine();
			}
			
			reader.close();
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return companies;
	}
}
