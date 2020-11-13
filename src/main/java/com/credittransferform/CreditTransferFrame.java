package com.credittransferform;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.itextpdf.text.DocumentException;

public class CreditTransferFrame extends JFrame{
	private JTextField recipientName;
	private JTextField accountNumber;
	private JTextField amount;
	private JRadioButton payment;
	private JRadioButton transfer;
	private JTextField nameOfThePayer;
	private JTextField accountNumberOfThePayer;
	private JTextField titleOfTheTransfer;
	private JButton submit;
	private static final long serialVersionUID = 1L;

	public CreditTransferFrame() {
		
		setLayout(new GridLayout(8,1));
		JPanel panelOne=new JPanel();
		JPanel panelTwo=new JPanel();
		JPanel panelThree=new JPanel();
		JPanel panelFour=new JPanel();
		JPanel panelFourFive=new JPanel();
		JPanel panelFive=new JPanel();
		JPanel panelSix=new JPanel();
		
		recipientName=new JTextField(30);
		accountNumber=new JTextField(30);
		amount=new JTextField(17);
		payment=new JRadioButton("wp³ata",false);
		transfer=new JRadioButton("przelew",true);
		ButtonGroup group=new ButtonGroup();
		group.add(payment);
		group.add(transfer);
		nameOfThePayer=new JTextField(30);
		accountNumberOfThePayer=new JTextField(30);
		titleOfTheTransfer=new JTextField(30);
		submit=new JButton("Generuj druk");
		
		
  
	
		panelOne.add(new JLabel("Nazwa odbiorcy              "));
		panelOne.add(recipientName);
		panelTwo.add(new JLabel("Nr rachunku odbiorcy    "));
		panelTwo.add(accountNumber);
		panelThree.add(new JLabel("Kwota "));
		panelThree.add(amount);
		
		panelThree.add(new JLabel("Typ przekazu   "));
		panelThree.add(payment);
		panelThree.add(transfer);
		panelFour.add(new JLabel("Nazwa zleceniodawcy   "));
		panelFour.add(nameOfThePayer);
		panelFourFive.add(new JLabel("Nr rach. zleceniodawcy "));
		panelFourFive.add(accountNumberOfThePayer);
		panelFive.add(new JLabel("Tytu³ przelewu                 "));
		panelFive.add(titleOfTheTransfer);
		panelSix.add(submit);
		
		payment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelFourFive.setVisible(false);
				
			}
			
		});
		
		transfer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panelFourFive.setVisible(true);
				
			}
			
		});
		
		String patternOneString="([0-9]+\\s*\\-*)*";
		String patternTwoString="[0-9]+(\\,[0-9]{2})?";
		Pattern patternOne=Pattern.compile(patternOneString);
		Pattern patternTwo=Pattern.compile(patternTwoString);
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				PdfCreator creator=new PdfCreator(CreditTransferFrame.this);
				Matcher matcherOne=patternOne.matcher(accountNumber.getText());
				Matcher matcherTwo=patternOne.matcher(accountNumberOfThePayer.getText());
				Matcher matcherThree=patternTwo.matcher(amount.getText());
				int accountNumberLength=accountNumber.getText().length();
				int accountNumberOfThePayerLength=accountNumberOfThePayer.getText().length();
				int amountLength=amount.getText().length();
				if(!matcherOne.matches()) {
					accountNumber.setForeground(Color.RED);
					accountNumber.setText("Dozwolone znaki: cyfry,spacja,myœlnik");
					accountNumber.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				
				if(!matcherTwo.matches()) {
					accountNumberOfThePayer.setForeground(Color.RED);
					accountNumberOfThePayer.setText("Dozwolone znaki: cyfry,spacja,myœlnik");
					accountNumberOfThePayer.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				
				if(!matcherThree.matches()) {
					amount.setForeground(Color.RED);
					amount.setText("Tylko cyfry, ew. przecinek i 2 cyfry");
					amount.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				
			
				if(matcherOne.matches()&&accountNumberLength>26) {
					accountNumber.setForeground(Color.RED);
					accountNumber.setText("Maksymalnie 26 liczb");
					accountNumber.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				
				if(matcherTwo.matches()&&accountNumberOfThePayerLength>26) {
					accountNumberOfThePayer.setForeground(Color.RED);
					accountNumberOfThePayer.setText("Maksymalnie 26 liczb");
					accountNumberOfThePayer.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				
				if(matcherThree.matches()&&amountLength>12) {
					amount.setForeground(Color.RED);
					amount.setText("Maksymalnie 12 liczb");
					amount.setBorder(BorderFactory.createLineBorder(Color.RED));
				}
				
				if(matcherOne.matches()&&matcherTwo.matches()&&matcherThree.matches()
						&&accountNumberLength<=26&&accountNumberOfThePayerLength<=26&&amountLength<=12) {
					
					try {
						creator.create();
					} catch (IOException e1) {
					
						e1.printStackTrace();
					} catch (DocumentException e1) {
					
						e1.printStackTrace();
					}
				}
				
			}
			
		});
		
		add(panelOne);
		add(panelTwo);
		add(panelThree);
		add(panelFour);
		add(panelFourFive);
		add(panelFive);
		add(panelSix);
		pack();
	
	}

	public JTextField getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(JTextField recipientName) {
		this.recipientName = recipientName;
	}

	public JTextField getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(JTextField accountNumber) {
		this.accountNumber = accountNumber;
	}

	public JTextField getAmount() {
		return amount;
	}

	public void setAmount(JTextField amount) {
		this.amount = amount;
	}

	public JRadioButton getPayment() {
		return payment;
	}

	public void setPayment(JRadioButton payment) {
		this.payment = payment;
	}

	public JRadioButton getTransfer() {
		return transfer;
	}

	public void setTransfer(JRadioButton transfer) {
		this.transfer = transfer;
	}

	public JTextField getNameOfThePayer() {
		return nameOfThePayer;
	}

	public void setNameOfThePayer(JTextField nameOfThePayer) {
		this.nameOfThePayer = nameOfThePayer;
	}

	public JTextField getTitleOfTheTransfer() {
		return titleOfTheTransfer;
	}

	public void setTitleOfTheTransfer(JTextField titleOfTheTransfer) {
		this.titleOfTheTransfer = titleOfTheTransfer;
	}

	public JTextField getAccountNumberOfThePayer() {
		return accountNumberOfThePayer;
	}

	public void setAccountNumberOfThePayer(JTextField accountNumberOfThePayer) {
		this.accountNumberOfThePayer = accountNumberOfThePayer;
	}
	
	
	
}
