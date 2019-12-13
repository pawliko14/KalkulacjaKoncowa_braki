package com.DocumentGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.databaseOpetations.Storenotesdetail_projects_with_lacks;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.objectsDAO.MainObject;

public class GenerateDocument {
	
	 private static ArrayList<String> temporaryListofMainProjects;

	
	public static void main(String[] args)
	{
		
	}
	
	
	public void GenerateDocument()
	{
		
	}
	
	
	
	public void Generate() throws DocumentException, IOException
	{
		String dest = "C://Users/el08/Desktop/programiki/Lista_brakow_kalkulacja_koncowa/Lista_brakow.pdf";;
		
		 PdfPTable table = null;
		 PdfPTable table1 = null;

		System.out.println("\ngenerowanie PDf1\n");
		// GENEROWANIE DOKUMENTU PDF
	    Document document = new Document(PageSize.A3.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        
        
        document.open();

		   document.add(new Paragraph("Data wygenerowania raportu: "+new Date().toString()));
		   document.add(new Paragraph("\n\n"));
    

		   
		   	table = new PdfPTable(new float[] {80,50,100,170,360,50,190,100});
		   	table.setTotalWidth(1100);
		   	table.setLockedWidth(true);
		   	
		    BaseFont bf = BaseFont.createFont(
                    BaseFont.TIMES_ROMAN,
                    BaseFont.CP1252,
                    BaseFont.EMBEDDED
                    );
		   	
            Font font = new Font(bf, 14);
		   	
	        // header row:
	        table.addCell(new PdfPCell(new Phrase("Project",font)));
	        table.addCell(new PdfPCell(new Phrase("leverancier",font)));
	        table.addCell(new PdfPCell(new Phrase("ordernummer",font)));
	        table.addCell(new PdfPCell(new Phrase("artikelcode",font)));		       
	        table.addCell(new PdfPCell(new Phrase("artikelomschrijving",font)));
	        table.addCell(new PdfPCell(new Phrase("montage",font)));
	        table.addCell(new PdfPCell(new Phrase("montageomschirjving",font)));
	        table.addCell(new PdfPCell(new Phrase("eenhejdprijs",font)));
	        
	        		PdfPCell table_cell1 = new PdfPCell(new Phrase("1",font));
	   				table.addCell(table_cell1);
	   				
	   				PdfPCell table_cell2 = new PdfPCell(new Phrase(" ",font));
	   				table.addCell(table_cell2);
	   				
	   				PdfPCell table_cell3 = new PdfPCell(new Phrase("",font));
	   				table.addCell(table_cell3);
	   				
	   				PdfPCell table_cell4 = new PdfPCell(new Phrase("",font));
	   				table.addCell(table_cell4);
	   				
	   				PdfPCell table_cell5 = new PdfPCell(new Phrase("",font));
	   				table.addCell(table_cell5);
	   				
	   				PdfPCell table_cell6 = new PdfPCell(new Phrase("",font));
	   				table.addCell(table_cell6);
	   				
	   				PdfPCell table_cell7 = new PdfPCell(new Phrase("",font));
	   				table.addCell(table_cell7);
	   				
	   				PdfPCell table_cell8 = new PdfPCell(new Phrase("",font));
	   				table.addCell(table_cell8);
	   						
	   	
	   		      	    		
	   		        document.add(table);


	   		        
	   		 List<MainObject> listOfMissinObjects = Storenotesdetail_projects_with_lacks.missing_objects_storenotesdetail;    
	   		 
	   		 
	   		 boolean flaga = false;
	   		 
	   		 
	         temporaryListofMainProjects= new ArrayList<String>();
	   		 for(int i = 0 ; i < listOfMissinObjects.size(); i++)
	   		 {
	   			 
				
	   			temporaryListofMainProjects.add(listOfMissinObjects.get(i).getProject()); // add item(main project, equvalnt of 190521 or 170700 etc) from list
   				if(temporaryListofMainProjects.size() >=2 && temporaryListofMainProjects.get(temporaryListofMainProjects.size()-2).equals(listOfMissinObjects.get(i).getProject()))	
	   			 {
	   				 
	   			 }	   			 
	   			 else 
	   			 {
	   	              Font font_main_project = new Font(bf, 23);
	   	  
	   				 
	   	
	   				   document.add(new Paragraph("\n\n"));
	   				PdfPTable table00;
		   			table00 = new PdfPTable(new float[] {1100});
		   			table00.setTotalWidth(1100);
		   			table00.setLockedWidth(true);

		   			
		   		 PdfPCell table_cell0000 = new PdfPCell(new Phrase("PROJEKT  : "+ listOfMissinObjects.get(i).getProject()));
		   		 table_cell0000.setHorizontalAlignment(Element.ALIGN_CENTER);
		   		 table00.addCell(table_cell0000);
		   			
				
	   			
		   		 	document.add(table00);
		   			
	   			 }
	   			 
					
	   			PdfPCell cell1 = new PdfPCell(new Phrase(listOfMissinObjects.get(i).getProject(),font));
	   			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell1);
   				
   				PdfPCell cell2 = new PdfPCell(new Phrase(listOfMissinObjects.get(i).getLeverancier(),font));
   				cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell2);
   				
   				PdfPCell cell3 = new PdfPCell(new Phrase(listOfMissinObjects.get(i).getOrdernummer(),font));
   				cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell3);
   				
   				PdfPCell cell4 = new PdfPCell(new Phrase(listOfMissinObjects.get(i).getArtikelcode(),font));
   				cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell4);
   				
   				PdfPCell cell5 = new PdfPCell(new Phrase(listOfMissinObjects.get(i).getArtikelomschrijving(),font));
   				cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell5);
   				
   				PdfPCell cell6 = new PdfPCell(new Phrase(listOfMissinObjects.get(i).getMontage(),font));
   				cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell6);
   				
   				PdfPCell cell7 = new PdfPCell(new Phrase(listOfMissinObjects.get(i).getMontageomschirjving(),font));
   				cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell7);
   				
   				PdfPCell cell8 = new PdfPCell(new Phrase(listOfMissinObjects.get(i).getEenhejdprijs(),font));
   				cell8.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell8);
      	    		
   		        document.add(table);
		        table.flushContent();

	   			 
				}
	   		        
	   		        document.close();
 
	}

}
