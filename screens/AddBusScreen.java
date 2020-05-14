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
import models.Bus;
import models.Company;
import models.Driver;
import providers.BusFileProvider;
import providers.CompanyFileProvider;
import providers.DriverFileProvider;

public class AddBusScreen extends JFrame {

	private JLabel busNameLbl=null;
	private JTextField busNameTxt=null;
	private JLabel companiesLbl = null;
	private JComboBox<String> companiesCbox = null;
	private JLabel driversLbl = null;
	private JComboBox<String> driversCbox = null;
	private JLabel brandLbl = null;
	private JTextField brandTxt = null;
	private JLabel modelLbl = null;
	private JTextField modelTxt = null;
	private JLabel capacityLbl = null;
	private JTextField capacityTxt = null;
	private JButton saveBus = null;
	private JLabel result=null;

	public AddBusScreen() {
		this.setTitle("Otobüs Ekle");
		this.setSize(new Dimension(400, 400));
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void open() {
		this.result=new JLabel();
		this.add(result);
		this.result.setVisible(false);
		
		this.busNameLbl=new JLabel("Otobüsün Ýsmi : ");
		this.add(busNameLbl);
		
		this.busNameTxt=new JTextField();
		this.busNameTxt.setPreferredSize(new Dimension(100, 20));
		this.add(busNameTxt);
		
		this.companiesLbl = new JLabel("Firmalar : ");
		this.add(companiesLbl);

		this.companiesCbox = new JComboBox<String>();
		this.companiesCbox.addItem("Select Company");
		CompanyFileProvider provider = new CompanyFileProvider("companies.txt");
		ArrayList<Company> companies = provider.all();
		
		for(Company c : companies) {
			this.companiesCbox.addItem(c.getName());
		}
		this.add(companiesCbox);

		this.driversLbl = new JLabel("Þoförler : ");
		this.add(driversLbl);

		this.driversCbox = new JComboBox<String>();
		this.add(driversCbox);

		this.brandLbl = new JLabel("Marka : ");
		this.add(brandLbl);

		this.brandTxt = new JTextField();
		this.brandTxt.setPreferredSize(new Dimension(100, 20));
		this.add(brandTxt);

		this.modelLbl = new JLabel("Model : ");
		this.add(modelLbl);

		this.modelTxt = new JTextField();
		this.modelTxt.setPreferredSize(new Dimension(100, 20));
		this.add(modelTxt);

		this.capacityLbl = new JLabel("Kapasite : ");
		this.add(capacityLbl);

		this.capacityTxt = new JTextField();
		this.capacityTxt.setPreferredSize(new Dimension(100, 20));
		this.add(capacityTxt);

		this.saveBus = new JButton("KAYDET");
		this.add(saveBus);

		this.addEventListeners();

		this.setVisible(true);

	}

	private void addEventListeners() {
		
		this.companiesCbox.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					
					AddBusScreen.this.getDriversCbox().removeAllItems();
					AddBusScreen.this.getDriversCbox().addItem("Select Driver");
					
					if(!e.getItem().toString().equals("Select Company")) {
						DriverFileProvider provider = new DriverFileProvider("_drivers.txt");
						ArrayList<Driver> drivers = provider.all(e.getItem().toString());
						for(Driver d  : drivers) {
							AddBusScreen.this.getDriversCbox().addItem(d.getName() + " " + d.getSurname());
						}
					}
				}
			}
		});

		this.saveBus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
				String firmName=AddBusScreen.this.getCompaniesCbox().getSelectedItem().toString();
				String driver =AddBusScreen.this.getDriversCbox().getSelectedItem().toString();
				String brand= AddBusScreen.this.getBrandTxt().getText().toString();
				String model=AddBusScreen.this.getModelTxt().getText().toString();
				int capacity=Integer.valueOf(AddBusScreen.this.getCapacityTxt().getText().toString());
				String busName=AddBusScreen.this.getBusNameTxt().getText();
					if (contain(brand)) {
						throw new IncorrectTextException();
					} else {
						Bus bus = new Bus(busName, brand, model, capacity, driver,firmName);
						BusFileProvider provider= new BusFileProvider("_buses.txt");
						
						boolean result =provider.save(firmName, bus);
						
						if(result) {
							System.out.println("Bus Added");
							AddBusScreen.this.getResult().setText("Otobüs baþarýyla eklendi.");
							AddBusScreen.this.getResult().setVisible(true);
							clear();
						}else {
							System.out.println("Bus Could Not Added");
						}
					}
				}
				catch ( IncorrectTextException e1) {
					AddBusScreen.this.getResult().setText("Marka rakam içeremez!Lütfen tekrar giriniz...");
					AddBusScreen.this.getResult().setVisible(true);
					clear();
				}
				catch (NumberFormatException e2) {
					AddBusScreen.this.getResult().setText("Kapasite rakamlardan oluþmak zorundadýr!Lütfen tekrar giriniz...");
					AddBusScreen.this.getResult().setVisible(true);
					clear();
				}
				
				
				
				
				
				
			}

		});

	}
	
	public JTextField getBrandTxt() {
		return brandTxt;
	}

	public JTextField getBusNameTxt() {
		return busNameTxt;
	}

	public JTextField getModelTxt() {
		return modelTxt;
	}

	public JTextField getCapacityTxt() {
		return capacityTxt;
	}

	public JLabel getResult() {
		return result;
	}

	public JComboBox<String> getDriversCbox() {
		return driversCbox;
	}
	public JComboBox<String> getCompaniesCbox(){
		return companiesCbox;
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
	 private void clear() {
		 AddBusScreen.this.getBusNameTxt().setText("");
		 AddBusScreen.this.getBrandTxt().setText(" ");
		 AddBusScreen.this.getModelTxt().setText(" ");
		 AddBusScreen.this.getCapacityTxt().setText(" ");
	}
	
	
}
