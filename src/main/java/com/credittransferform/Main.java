package com.credittransferform;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		
		EventQueue.invokeLater(()->
		{
			var frame=new CreditTransferFrame();
			frame.setTitle("Generator druku przelewu");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setLocationByPlatform(true);
			frame.setVisible(true);
			
		});

	}

}
