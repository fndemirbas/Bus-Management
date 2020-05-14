package screens;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import exceptions.IncorrectTextException;
import models.Company;
import providers.CompanyFileProvider;

public class AddCompanyScreen extends JFrame{
	
	private JLabel companyNameLbl=null;
	private JTextField companyNameTxt = null;
	private JLabel companyFounderLbl=null;
	private JTextField companyFounderTxt=null;
	private JButton saveCompany=null;
	private JLabel result=null;
	
	public AddCompanyScreen() {
		this.setTitle("Firma Ekle");
		this.setSize(new Dimension(400, 400));
		this.setLayout(new FlowLayout());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	}
	
	public void open() {
		this.result=new JLabel();
		this.add(result);
		this.result.setVisible(false);

		
		this.companyNameLbl= new JLabel("Firma Adý : ");
		this.add(companyNameLbl);
		
		this.companyNameTxt= new JTextField();
		this.companyNameTxt.setPreferredSize(new Dimension(100, 20));
		this.add(companyNameTxt);
		
		this.companyFounderLbl=new JLabel("Firmanýn Kurucusu : ");
		this.add(companyFounderLbl);
		
		this.companyFounderTxt= new JTextField();
		this.companyFounderTxt.setPreferredSize(new Dimension(100, 20));
		this.add(companyFounderTxt);
		
		this.saveCompany = new JButton("KAYDET");
		this.add(saveCompany);
		
		this.addEventListeners();
		
		this.setVisible(true);
	
	}
	public void addEventListeners() {
		
		this.saveCompany.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					String companyName = AddCompanyScreen.this.getCompanyNameTxt().getText().toString();
					String companyOwnerName = AddCompanyScreen.this.getCompanyFounderTxt().getText().toString();
					
					if (contain(companyName)) {
						throw new IncorrectTextException();
					} else {
						if (contain(companyOwnerName)) {
						throw new IncorrectTextException()	;
						} else {
							Company newCompany = new Company(companyName, companyOwnerName);
							CompanyFileProvider provider = new CompanyFileProvider("companies.txt");
							
							boolean result = provider.save(newCompany);
							if(result) {
								AddCompanyScreen.this.getResult().setText("Firma baþarýyla kaydedildi.");
								AddCompanyScreen.this.getResult().setVisible(true);
								clear();
							}else {
								AddCompanyScreen.this.getResult().setText("Hata Oluþtu");
							}
						}

					}
					
					
				} catch (IncorrectTextException e1) {
					AddCompanyScreen.this.getResult().setText("Rakam içeremez!Lütfen tekrar giriniz...");
					AddCompanyScreen.this.getResult().setVisible(true);
					clear();
				}
				
			}

			private void clear() {
				AddCompanyScreen.this.getCompanyNameTxt().setText(" ");
				AddCompanyScreen.this.getCompanyFounderTxt().setText(" ");
			}
			
		});
		
	}
	
	public JLabel getResult() {
		return result;
	}

	public void setResult(JLabel result) {
		this.result = result;
	}

	public JTextField getCompanyNameTxt() {
		return companyNameTxt;
	}
	
	public JTextField getCompanyFounderTxt() {
		return companyFounderTxt;
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
