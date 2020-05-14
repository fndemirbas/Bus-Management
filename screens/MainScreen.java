package screens;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainScreen extends JFrame{
	
	private JButton addCompanyButton =null ;
	private JButton addDriverButton=null;
	private JButton addBusButton=null;
	private JButton addBusServiceButton=null;
	private JButton bookingButton=null;
	
	
	public MainScreen() {
		this.setTitle("Ana Sayfa");
		this.setSize(new Dimension(400, 400));
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void open() {
		
		
		this.addCompanyButton = new JButton("Firma Ekle");
		this.add(addCompanyButton);
		
		this.addDriverButton = new JButton("Þoför Ekle");
		this.add(addDriverButton);
		
		this.addBusButton = new JButton("Otobüs Ekle");
		this.add(addBusButton);
		
		this.addBusServiceButton=new JButton("Sefer Ekle");
		this.add(addBusServiceButton);
				
		this.bookingButton = new JButton("Bilet Al");
		this.add(bookingButton);
		
		this.addEventListener();
		
		this.setVisible(true);
		
	}
	

	   private void addEventListener() {
		
		   this.addCompanyButton.addMouseListener(new MouseAdapter() {
			   @Override
			public void mouseClicked(MouseEvent e) {
				AddCompanyScreen companyPage = new  AddCompanyScreen();
				companyPage.open();
				  
			}
			   
		});

		  
		   this.addDriverButton.addMouseListener(new MouseAdapter() {
			  @Override
			public void mouseClicked(MouseEvent e) {
				  AddDriverScreen driverPage= new AddDriverScreen();
				  driverPage.open();
			}
			   
		});
		   
		   
		   this.addBusButton.addMouseListener(new MouseAdapter() {
			   @Override
			public void mouseClicked(MouseEvent e) {
				  AddBusScreen busPage = new AddBusScreen();
				  busPage.open();
			}
		});
		   
		   this.addBusServiceButton.addMouseListener(new MouseAdapter() {
			   @Override
			public void mouseClicked(MouseEvent e) {
				AddBusServiceScreen busServicePage= new AddBusServiceScreen();
				busServicePage.open();
			}
			   
		});
		   
		   this.bookingButton.addMouseListener(new MouseAdapter() {
			   @Override
			public void mouseClicked(MouseEvent e) {
				   BookingScreen bookingPage = new BookingScreen();
				   bookingPage.open();
			}
		});
	}
	
	
}

