package screens;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import exceptions.IncorrectTextException;

import models.Company;
import models.Driver;
import providers.CompanyFileProvider;
import providers.DriverFileProvider;

public class AddDriverScreen extends JFrame {
	private JLabel companiesLbl=null;
	private JComboBox<String> companiesCbox = null;
	private JLabel driverNameLbl=null;
	private JTextField driverNameTxt=null;
	private JLabel driverSurnameLbl=null;
	private JTextField driverSurnameTxt=null;
	private JLabel driverAgeLbl=null;
	private JTextField driverAgeTxt=null;
	private JButton saveDriver=null;
	private JLabel result=null;
	
	public AddDriverScreen() {
		this.setSize(new Dimension(400,400));
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	public void open() {
		this.result=new JLabel();
		this.add(result);
		this.result.setVisible(false);
		
		this.companiesLbl=new JLabel("Firma : ");
		this.add(companiesLbl);
		
		this.companiesCbox = new JComboBox<String>();
		this.companiesCbox.addItem("Select Company");
		CompanyFileProvider provider = new CompanyFileProvider("companies.txt");
		ArrayList<Company> companies = provider.all();
		
		for(Company c : companies) {
			this.companiesCbox.addItem(c.getName());
		}
		this.add(companiesCbox);
		
		this.driverNameLbl=new JLabel("Ad : ");
		this.add(driverNameLbl);
		
		this.driverNameTxt=new JTextField();
		this.driverNameTxt.setPreferredSize(new Dimension(100, 20));
		this.add(driverNameTxt);
		
		this.driverSurnameLbl=new JLabel("Soyad : ");
		this.add(driverSurnameLbl);
		
		this.driverSurnameTxt=new JTextField();
		this.driverSurnameTxt.setPreferredSize(new Dimension(100, 20));
		this.add(driverSurnameTxt);

		this.driverAgeLbl=new JLabel("Yaþ : ");
		this.add(driverAgeLbl);
		
		this.driverAgeTxt=new JTextField();
		this.driverAgeTxt.setPreferredSize(new Dimension(100, 20));
		this.add(driverAgeTxt);
		
		this.saveDriver=new JButton("KAYDET");
		this.add(saveDriver);
		
		this.addEventListeners();
		
		this.setVisible(true);
		
		
		
	
	}
	public void addEventListeners() {
		
		this.saveDriver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String name = AddDriverScreen.this.getDriverNameTxt().getText().toString();
					String surname = AddDriverScreen.this.getDriverSurnameTxt().getText().toString();
					int age =Integer.valueOf(AddDriverScreen.this.getDriverAgeTxt().getText().toString());
					String companyName = AddDriverScreen.this.getCompaniesCbox().getSelectedItem().toString();
					if (contain(name)|contain(surname)) {
						throw new IncorrectTextException();
					} else {
						Driver newDriver = new Driver(name, surname,age);
						DriverFileProvider provider = new DriverFileProvider("_drivers.txt");
						
						boolean result = provider.save(companyName, newDriver);
						
						if(result) {
							System.out.println("Driver Added");
							AddDriverScreen.this.getResult().setText("Þoför baþarýyla eklenmiþtir.");
							AddDriverScreen.this.getResult().setVisible(true);
							clear();
						}else {
							System.out.println("Driver Could Not Added");
						}
					}
					
				} 
				catch (IncorrectTextException e1) {
					AddDriverScreen.this.getResult().setText("Ad ve Soyad rakam içeremez!Lütfen tekrar giriniz...");
					AddDriverScreen.this.getResult().setVisible(true);
					clear();
				}
				catch (NumberFormatException e2) {
					AddDriverScreen.this.getResult().setText("Yaþ rakamlardan oluþmak zorundadýr!Lütfen tekrar giriniz...");
					AddDriverScreen.this.getResult().setVisible(true);
					clear();
				}
				
				
				
			}

			private void clear() {
				AddDriverScreen.this.getDriverNameTxt().setText(" ");
				AddDriverScreen.this.getDriverSurnameTxt().setText(" ");
				AddDriverScreen.this.getDriverAgeTxt().setText(" ");
			}
		});
	}
	
	public JTextField getDriverNameTxt() {
		return driverNameTxt;
	}
	
	public JTextField getDriverSurnameTxt() {
		return driverSurnameTxt;
	}
	
	public JTextField getDriverAgeTxt() {
		return driverAgeTxt;
	}
	
	public JComboBox<String> getCompaniesCbox() {
		return companiesCbox;
	}
	
	
	public JLabel getResult() {
		return result;
	}


	public void setResult(JLabel result) {
		this.result = result;
	}


	private boolean contain(String string) {
		boolean result=false;
		if (string.contains("0")||string.contains("1")||string.contains("2")||string.contains("3")||string.contains("4")
				||string.contains("5")||string.contains("6")||string.contains("7")||string.contains("8")
				||string.contains("9")) {
			return result=true;
			
		}
		return result;
	}
	
}
