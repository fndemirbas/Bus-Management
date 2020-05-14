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

import exceptions.IncorrectTextException;
import exceptions.IndexOutOfCapacityException;
import exceptions.NotAddPassgenger;
import models.Booking;
import models.Bus;
import models.BusService;
import models.Company;
import providers.BookingFileProvider;
import providers.BusFileProvider;
import providers.BusServiceFileProvider;
import providers.CompanyFileProvider;

public class BookingScreen extends JFrame{

	private JLabel companiesLbl=null;
	private JComboBox<String> companiesCbox =null;
	private JLabel busServiceNameLbl=null;
	private JComboBox<String> busServiceCbox=null;
	private JLabel passengerInformationLbl=null;
	private JTextField passengerInformationTxt=null;
	private JButton savePassenger;
	private JLabel result=null;
	
	public BookingScreen() {
		this.setTitle("Bilet Al");
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
		
		this.companiesCbox=new JComboBox<String>();
		this.companiesCbox.addItem("Select Company");
		CompanyFileProvider provider = new CompanyFileProvider("companies.txt");
		ArrayList<Company> companies = provider.all();
		
		for(Company c : companies) {
			this.companiesCbox.addItem(c.getName());
		}
		this.add(companiesCbox);
		
		this.busServiceNameLbl=new JLabel("Sefer : ");
		this.add(busServiceNameLbl);
		
		this.busServiceCbox=new JComboBox<String>();
		this.add(busServiceCbox);
		
		this.passengerInformationLbl=new JLabel("Yolcu Ad ve Soyad : ");
		this.add(passengerInformationLbl);
		
		this.passengerInformationTxt=new JTextField();
		this.passengerInformationTxt.setPreferredSize(new Dimension(100,20));
		this.add(passengerInformationTxt);
		
		this.savePassenger=new JButton("KAYDET");
		this.add(savePassenger);
		this.addEventListeners();
		this.setVisible(true);
		
		
	}
	public void addEventListeners() {
		
		this.companiesCbox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
					BookingScreen.this.getBusServiceCbox().removeAllItems();
					BookingScreen.this.getBusServiceCbox().addItem("Select Travel");
					
					if(!e.getItem().toString().equals("Select Company")) {
						BusServiceFileProvider provider = new BusServiceFileProvider("_travels.txt");
						ArrayList<BusService> busService = provider.all(BookingScreen.this.getCompaniesCbox().getSelectedItem().toString());
						for(BusService b : busService) {
						
							BookingScreen.this.getBusServiceCbox().addItem(b.getName() + "-" +b.getBus().getName()+"-"+b.getBus().getDriverNameSurname()+"-"+b.getBus().getCapacity()+"-"+b.getWhere()+"-" +b.getTo()+"-"+b.getWhen());
							
						}
					}
				}
				
			}
		});
		
		
		
		this.savePassenger.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				try {
					String firmName= BookingScreen.this.getCompaniesCbox().getSelectedItem().toString();
					String travel=BookingScreen.this.getBusServiceCbox().getSelectedItem().toString();
					String passengerNameSurname=BookingScreen.this.getPassengerInformationTxt().getText().toString();
					
					if (contain(passengerNameSurname)) {
						throw new IncorrectTextException();
					} else {
						Booking booking = new Booking(passengerNameSurname,firmName ,travel);
						BookingFileProvider provider = new BookingFileProvider("_passengers.txt",firmName);
						int result =provider.save(firmName, booking.getBusService().getName(), booking);
						if (result==1) {
							//System.out.println("Passenger added");
							BookingScreen.this.getResult().setText("Yolcu baþarýyla eklenmiþtir.");
							BookingScreen.this.getResult().setVisible(true);
							clear();
							
						} else if(result==0){
							throw new IndexOutOfCapacityException();
		
						}
						else {
							throw new NotAddPassgenger();
						}
					}
					
					
				} catch (IncorrectTextException e1) {
					BookingScreen.this.getResult().setText("Yolcu ad ve soyad rakam içeremez!");
					BookingScreen.this.getResult().setVisible(true);
					clear();
				}
				catch (IndexOutOfCapacityException e2) {
					BookingScreen.this.getResult().setText("Otobüs dolu , iþlem gerçekleþemedi!");
					BookingScreen.this.getResult().setVisible(true);
					clear();
				}
				catch (NotAddPassgenger e3) {
					BookingScreen.this.getResult().setText("Ayný sefere bir kez daha bilet alamazsýnýz !");
					BookingScreen.this.getResult().setVisible(true);
					clear();
				}
				
				
				
				
			}

			private void clear() {
				BookingScreen.this.passengerInformationTxt.setText(" ");
				
			}
		});
	}

	

	public JComboBox<String> getCompaniesCbox() {
		return companiesCbox;
	}


	public JLabel getResult() {
		return result;
	}

	public JComboBox<String> getBusServiceCbox() {
		return busServiceCbox;
	}

	
	public JTextField getPassengerInformationTxt() {
		return passengerInformationTxt;
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
