package com.credittransferform;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

public class PdfCreator {
	private CreditTransferFrame creditTransferFrame;
	private static final int DISTANCE_BETWEEN_FORMS=310;
	private static final String FILE_NAME="gen.pdf";
	
	public PdfCreator(CreditTransferFrame creditTransferFrame) {
		this.creditTransferFrame=creditTransferFrame;
	}
	public void create() throws IOException, DocumentException {
		byte[] sourceContent =Files.readAllBytes(new File("polecenie-przelewu-wplata-gotowkowa-druk-jednostronicowy.pdf").toPath());

    	
    	Document document = new Document();
    	
    	PdfWriter writer = PdfWriter.getInstance(document,
    		    new FileOutputStream(FILE_NAME));
    	document.addCreationDate();
    	document.addProducer();
    	document.open();

    	PdfContentByte cb = writer.getDirectContent();
    	PdfReader reader = new PdfReader(sourceContent);
    	//int n = reader.getNumberOfPages();

    	
    	PdfImportedPage page = writer.getImportedPage(reader, 1);
    	cb.addTemplate(page, 0.0F, 0.0F);

    	    
    	BaseFont arial = BaseFont.createFont("C:\\Windows\\Fonts\\arial.ttf", "Identity-H", BaseFont.NOT_EMBEDDED); 
    	float fontSize = 13.9f; 
    	int x = 103; 
    	int y = 779; 
    		   
    	cb.beginText();
    	cb.setFontAndSize(arial, fontSize);
    	int textRecLength=creditTransferFrame.getRecipientName().getText().length();
    	if(textRecLength<=27) {
    		drawText(cb,creditTransferFrame.getRecipientName().getText(),x,y);
    		drawText(cb,creditTransferFrame.getRecipientName().getText(),x,y-DISTANCE_BETWEEN_FORMS);
    	}else {
    		drawText(cb,creditTransferFrame.getRecipientName().getText().substring(0,27),x,y);
    		drawText(cb,creditTransferFrame.getRecipientName().getText().substring(27),x,y-25);
    		drawText(cb,creditTransferFrame.getRecipientName().getText().substring(0,27),x,y-DISTANCE_BETWEEN_FORMS);
    		drawText(cb,creditTransferFrame.getRecipientName().getText().substring(27),x,y-25-DISTANCE_BETWEEN_FORMS);
    	}
    	
    	drawText(cb,creditTransferFrame.getAccountNumber().getText(),x,y-50);
    	drawText(cb,creditTransferFrame.getAccountNumber().getText(),x,y-50-DISTANCE_BETWEEN_FORMS);
    	
    	if(creditTransferFrame.getPayment().isSelected()) {
    		drawText(cb,"x",x+128,y-72);
    		drawText(cb,"x",x+128,y-72-DISTANCE_BETWEEN_FORMS);
    		String amount=creditTransferFrame.getAmount().getText();
    		NumberToWord numberToWord=new NumberToWord();
    		cb.setFontAndSize(arial, 5f);
    		if(amount.contains(",")) {
    			drawSmallText(cb,numberToWord.changeNumberToWord(Long.parseLong(amount.substring(0, amount.indexOf(","))))+" z³",x,y-90);
    			drawSmallText(cb,numberToWord.changeNumberToWord(Long.parseLong(amount.substring(0, amount.indexOf(","))))+" z³",x,y-90-DISTANCE_BETWEEN_FORMS);
    			drawSmallText(cb,numberToWord.changeNumberToWord(Long.parseLong(amount.substring (amount.indexOf(",")+1)))+" gr",x,y-97);
    			drawSmallText(cb,numberToWord.changeNumberToWord(Long.parseLong(amount.substring( amount.indexOf(",")+1)))+" gr",x,y-97-DISTANCE_BETWEEN_FORMS);
    		}else {
    			drawSmallText(cb,numberToWord.changeNumberToWord(Long.parseLong(amount))+" z³",x,y-90);
    			drawSmallText(cb,numberToWord.changeNumberToWord(Long.parseLong(amount))+" z³",x,y-90-DISTANCE_BETWEEN_FORMS);
    		}
    		cb.setFontAndSize(arial, fontSize);
    	}else {
    		drawText(cb,"x",x+115,y-72);
    		drawText(cb,"x",x+115,y-72-DISTANCE_BETWEEN_FORMS);
    		drawText(cb,creditTransferFrame.getAccountNumberOfThePayer().getText(),x,y-97);
    		drawText(cb,creditTransferFrame.getAccountNumberOfThePayer().getText(),x,y-97-DISTANCE_BETWEEN_FORMS);
    	}
    		
    		
    	drawText(cb,creditTransferFrame.getAmount().getText(),x+215,y-72);
    	drawText(cb,creditTransferFrame.getAmount().getText(),x+215,y-72-DISTANCE_BETWEEN_FORMS);
    	
    	int textPayLength=creditTransferFrame.getNameOfThePayer().getText().length();
    	if(textPayLength<=27) {
    		drawText(cb,creditTransferFrame.getNameOfThePayer().getText(),x,y-120);
    		drawText(cb,creditTransferFrame.getNameOfThePayer().getText(),x,y-120-DISTANCE_BETWEEN_FORMS);
    	}else {
    		drawText(cb,creditTransferFrame.getNameOfThePayer().getText().substring(0,27),x,y-120);
    		drawText(cb,creditTransferFrame.getNameOfThePayer().getText().substring(27),x,y-145);
    		drawText(cb,creditTransferFrame.getNameOfThePayer().getText().substring(0,27),x,y-120-DISTANCE_BETWEEN_FORMS);
    		drawText(cb,creditTransferFrame.getNameOfThePayer().getText().substring(27),x,y-145-DISTANCE_BETWEEN_FORMS);
    	}
    	
    	int textTitleLength=creditTransferFrame.getTitleOfTheTransfer().getText().length();
    	if(textTitleLength<=27) {
    		drawText(cb,creditTransferFrame.getTitleOfTheTransfer().getText(),x,y-170);
    		drawText(cb,creditTransferFrame.getTitleOfTheTransfer().getText(),x,y-170-DISTANCE_BETWEEN_FORMS);
    	}else {
    		drawText(cb,creditTransferFrame.getTitleOfTheTransfer().getText().substring(0,27),x,y-170);
    		drawText(cb,creditTransferFrame.getTitleOfTheTransfer().getText().substring(27),x,y-195);
    		drawText(cb,creditTransferFrame.getTitleOfTheTransfer().getText().substring(0,27),x,y-170-DISTANCE_BETWEEN_FORMS);
    		drawText(cb,creditTransferFrame.getTitleOfTheTransfer().getText().substring(27),x,y-195-DISTANCE_BETWEEN_FORMS);
    	}
    	
    	
    	
    	cb.endText();

    	   
    	cb.stroke();
    	document.newPage();
    	

    	
    	   

    	
    	document.close();
    	File file = new File(FILE_NAME);  
    	Desktop desktop = Desktop.getDesktop();  
    	if(file.exists()) {  
    	desktop.open(file);               
    	}  
	}
	
	private void drawText(PdfContentByte pdfContentByte,String text,int x, int y) {
		
		String textUpperCase=text.toUpperCase();
		
		char[] arrayOfChars=textUpperCase.toCharArray();
		for(int i=0;i<arrayOfChars.length;i++) {
			pdfContentByte.setTextMatrix(x, y);
			pdfContentByte.showText(String.valueOf(arrayOfChars[i]));
			x+=14.9999999999999800001;
			
		}
		
		
	}
	
	
	private void drawSmallText(PdfContentByte pdfContentByte,String text,int x, int y) {
		
		String textUpperCase=text.toUpperCase();
		
		char[] arrayOfChars=textUpperCase.toCharArray();
		for(int i=0;i<arrayOfChars.length;i++) {
			pdfContentByte.setTextMatrix(x, y);
			pdfContentByte.showText(String.valueOf(arrayOfChars[i]));
			x+=4;
			
		}
		
	}
	
	

}
