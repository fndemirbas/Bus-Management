package screens;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import exceptions.IncorrectFormatException;
import exceptions.IncorrectTextException;
import models.Bus;
import models.BusService;
import models.Company;
import models.Driver;
import providers.BusFileProvider;
import providers.BusServiceFileProvider;
import providers.CompanyFileProvider;
import providers.DriverFileProvider;


public class AddBusServiceScreen extends JFrame{
	
	private JLabel busServiceNameLbl=null;
	private JTextField busServiceNameTxt=null;
	private JLabel companiesLbl =null;
	private JComboBox<String> companiesCbox =null;
	private JLabel busesLbl =null;
	private JComboBox<String> buses = null;
	private JLabel whereLbl=null;
	private JTextField whereTxt=null;
	private JLabel toLbl=null;
	private JTextField toTxt=null;
	private JLabel whenLbl=null;
	private JTextField whenTxt=null;
	private JButton saveBusService=null;
	private JLabel result=null;
	
	public AddBusServiceScreen() {
		this.setTitle("Sefer Ekle");
		this.setSize(new Dimension(400,400));
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void open() {
		this.result=new JLabel();
		this.add(result);
		this.result.setVisible(false);
		
		this.busServiceNameLbl=new JLabel("Seferin Adý : ");
		this.add(busServiceNameLbl);
		
		this.busServiceNameTxt=new JTextField();
		this.busServiceNameTxt.setPreferredSize(new Dimension(100, 20));
		this.add(busServiceNameTxt);
		
		this.companiesLbl =new JLabel("Firmalar : ");
		this.add(companiesLbl);
		
		this.companiesCbox=new JComboBox<String>();
		this.companiesCbox.addItem("Select Company");
		CompanyFileProvider provider = new CompanyFileProvider("companies.txt");
		ArrayList<Company> companies = provider.all();
		
		for(Company c : companies) {
			this.companiesCbox.addItem(c.getName());
		}
		this.add(companiesCbox);
		
		this.busesLbl =new JLabel("Otobüsler : ");
		this.add(busesLbl);
		
		this.buses=new JComboBox<String>();
		this.add(buses);
		
		this.whereLbl=new JLabel("Nerden : ");
		this.add(whereLbl);
		
		this.whereTxt=new JTextField();
		this.whereTxt.setPreferredSize(new Dimension(100, 20));
		this.add(whereTxt);
		
		this.toLbl=new JLabel("Nereye : ");
		this.add(toLbl);
		
		this.toTxt=new JTextField();
		this.toTxt.setPreferredSize(new Dimension(100, 20));
		this.add(toTxt);
		
		this.whenLbl=new JLabel("Ne Zaman : ");
		this.add(whenLbl);
		
		this.whenTxt=new JTextField();
		this.whenTxt.setPreferredSize(new Dimension(100, 20));
		this.add(whenTxt);
		
		this.saveBusService=new JButton("KAYDET");
		this.add(saveBusService);
		
		this.addEventListeners();
		this.setVisible(true);
	}
	public void addEventListeners() {
		
		
		this.companiesCbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
					AddBusServiceScreen.this.getBuses().removeAllItems();
					AddBusServiceScreen.this.getBuses().addItem("Select Bus");
					
					if(!e.getItem().toString().equals("Select Company")) {
						BusFileProvider provider = new BusFileProvider("_buses.txt");
						ArrayList<Bus> buse = provider.all(AddBusServiceScreen.this.getCompaniesCbox().getSelectedItem().toString());
						for(Bus b : buse) {
							AddBusServiceScreen.this.getBuses().addItem(b.getName() + "-" + b.getDriverNameSurname()+"-" + b.getCapacity());
							
						}
					}
				}
			}
		});
		
		this.saveBusService.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String travelname=AddBusServiceScreen.this.getBusServiceNameTxt().getText().toString();
					String firmName=AddBusServiceScreen.this.getCompaniesCbox().getSelectedItem().toString();
					String bus=AddBusServiceScreen.this.getBuses().getSelectedItem().toString();
					String where=AddBusServiceScreen.this.getWhereTxt().getText().toString();
					String to= AddBusServiceScreen.this.getToTxt().getText().toString();
					String when=AddBusServiceScreen.this.getWhenTxt().getText().toString();
					String [] spliteed = when.split("\\.");
					//System.out.println(spliteed[0]);
					if (when.contains(".")) {
						
						if (contain(spliteed[0])&contain(spliteed[1])&contain(spliteed[2])) {
							if (contain(to)|contain(where)) {
								throw new IncorrectTextException();
							} 
							else {
								System.out.println("Hereeeeeeeee");
								BusService busService=new  BusService(travelname, where, to, when,firmName,bus);
								BusServiceFileProvider provider = new BusServiceFileProvider("_travels.txt");
								boolean result =provider.save(firmName, busService);
								AddBusServiceScreen.this.clear();
								if(result) {
									System.out.println("Bus Service Added");
									AddBusServiceScreen.this.getResult().setText("Otobüs Seferi baþarýyla eklendi.");
									AddBusServiceScreen.this.getResult().setVisible(true);
									clear();

								}else {
									System.out.println("Bus Service Could Not Added");
								}
							}
						} 
					else {
							throw new IncorrectFormatException();
						}
						} 
				else {
							throw new  IncorrectFormatException();
						}
						
					
					
				} catch ( IncorrectTextException e1) {
					AddBusServiceScreen.this.getResult().setText(" Otobüsün hareket noktasý ve varýþ noktasý rakam içeremez!Lütfen tekrar giriniz...");
					AddBusServiceScreen.this.getResult().setVisible(true);
					clear();
				}
				catch (IncorrectFormatException e2) {
					AddBusServiceScreen.this.getResult().setText("Lütfen rakamlardan oluþan gg.aa.yyyy formantýnda tarih giriniz!");
					AddBusServiceScreen.this.getResult().setVisible(true);
					clear();
				}
				
				
				
				
			}
		});
	}

	public JComboBox<String> getBuses() {
		return buses;
	}

	

	public JLabel getResult() {
		return result;
	}

	public JTextField getToTxt() {
		return toTxt;
	}

	public JTextField getBusServiceNameTxt() {
		return busServiceNameTxt;
	}

	public JComboBox<String> getCompaniesCbox() {
		return companiesCbox;
	}

	public JTextField getWhereTxt() {
		return whereTxt;
	}

	public JTextField getWhenTxt() {
		return whenTxt;
	}
	
	private void clear() {
		this.getBusServiceNameTxt().setText(" ");
		this.getWhereTxt().setText(" ");
		this.getWhenTxt().setText(" ");
		this.getToTxt().setText(" ");
		
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
