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
import com.parameters.Parameters;

public class GenerateDocument {
	
	 private static ArrayList<String> temporaryListofMainProjects;
	 private String dest = Parameters.getSavingPath_Lista_brakow();;
	
	public static void main(String[] args)
	{
		
	}
	

	public void Generate() throws DocumentException, IOException
	{
		
		
		 PdfPTable table = null;
		 PdfPTable table1 = null;

		System.out.println("\ngenerowanie PDf1\n");
		// GENEROWANIE DOKUMENTU PDF
	    Document document = new Document(PageSize.A2);
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        
        
        document.open();

		   document.add(new Paragraph("Data wygenerowania raportu: "+new Date().toString()));
		   document.add(new Paragraph("\n"));
		   document.add(new Paragraph("Informacja: '0' w kolumnie 'Cena' oznacza calkowity brak ceny dla danego art."));
		   document.add(new Paragraph("wtedy w Kalkulacja Koncowa rekord z tym art. zaznaczony jest na czerwono "));
		   document.add(new Paragraph("W przypadku kiedy w kolumnie jest jakas wartosc, oznacza to ze brak sredniej wazonej artykulu"));
		   document.add(new Paragraph("w kalkulacji koncowej taki rekord oznaczony jest jako normalny, choc cena jest tylko teoretyczna "));
		   document.add(new Paragraph("\n\n"));

		   
		   	table = new PdfPTable(new float[] {80,80,100,170,360,60,190,100});
		   	table.setTotalWidth(1140);
		   	table.setLockedWidth(true);
		   	
		    BaseFont bf = BaseFont.createFont(
                    BaseFont.TIMES_ROMAN,
                    BaseFont.CP1252,
                    BaseFont.EMBEDDED
                    );
		   	
            Font font = new Font(bf, 14);    		
	   		 document.add(table);


	   		        
	   		 List<MainObject> listOfMissinObjects = Storenotesdetail_projects_with_lacks.missing_objects_storenotesdetail;    
	   		 temporaryListofMainProjects= new ArrayList<String>();
	   		 
	   		 for(int i = 0 ; i < listOfMissinObjects.size(); i++)
	   		 {
	   			 
				
	   			temporaryListofMainProjects.add(listOfMissinObjects.get(i).getProject()); 
	   			 // if project occurs for the first time, it will be saved as main projects, same projects in list will be ignored
   				if(temporaryListofMainProjects.size() >=2 && temporaryListofMainProjects.get(temporaryListofMainProjects.size()-2).equals(listOfMissinObjects.get(i).getProject()))	
	   			 {
	   				 
	   			 }	   			 
	   			 else 
	   			 {
	   	              Font font_main_project = new Font(bf, 23);
	   	  
	   				 
	   	
	   				   document.add(new Paragraph("\n\n"));
	   				PdfPTable table00;
		   			table00 = new PdfPTable(new float[] {1140});
		   			table00.setTotalWidth(1140);
		   			table00.setLockedWidth(true);

		   			
		   		 PdfPCell table_cell0000 = new PdfPCell(new Phrase("PROJEKT  : "+ listOfMissinObjects.get(i).getProject(),font_main_project));
		   		 table_cell0000.setHorizontalAlignment(Element.ALIGN_CENTER);
		   		 table00.addCell(table_cell0000);
		   			
		   		 
		   	  table.addCell(new PdfPCell(new Phrase("Project",font)));
		        table.addCell(new PdfPCell(new Phrase("leverancier",font)));
		        table.addCell(new PdfPCell(new Phrase("ordernummer",font)));
		        table.addCell(new PdfPCell(new Phrase("artikelcode",font)));		       
		        table.addCell(new PdfPCell(new Phrase("artikelomschrijving",font)));
		        table.addCell(new PdfPCell(new Phrase("montage",font)));
		        table.addCell(new PdfPCell(new Phrase("montageomschirjving",font)));
		        table.addCell(new PdfPCell(new Phrase("eenhejdprijs",font)));
		   		 
		   		PdfPCell cell10 = new PdfPCell(new Phrase("Projekt",font));
		   		cell10.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell10);
   				
   				PdfPCell cell11 = new PdfPCell(new Phrase("Dostawca",font));
   				cell11.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell11);
   				
   				PdfPCell cell12 = new PdfPCell(new Phrase("Nr.Zamowienia",font));
   				cell12.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell12);
   				
   				PdfPCell cell13 = new PdfPCell(new Phrase("Artykul",font));
   				cell13.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell13);
   				
   				
   				PdfPCell cell14 = new PdfPCell(new Phrase("Opis Artykuly",font));
   				cell14.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell14);
   				
   				PdfPCell cell15 = new PdfPCell(new Phrase("Montaz",font));
   				cell15.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell15);
   				
   				PdfPCell cell16 = new PdfPCell(new Phrase("Opis dostawcy",font));
   				cell16.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell16);
   				
   				PdfPCell cell17 = new PdfPCell(new Phrase("Cena",font));
   				cell17.setHorizontalAlignment(Element.ALIGN_CENTER);
   				table.addCell(cell17);
		   		 
		   	  
		   		 
		        
		   		 	document.add(table00);
		   		 	document.add(table);
			        table.flushContent();

		   			
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
