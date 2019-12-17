package com.DocumentGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
	 private static int page_width = 1060;
	
	public static void main(String[] args)
	{
		
	}
	

	public void Generate(String dest) throws DocumentException, IOException
	{
		
		
		 PdfPTable table = null;
		// GENEROWANIE DOKUMENTU PDF
	    Document document = new Document(PageSize.A2);
	    
	    System.out.println("File destination and name: " + dest);
	    
        PdfWriter.getInstance(document, new FileOutputStream(dest));
        
        
        document.open();

		   document.add(new Paragraph("Data wygenerowania raportu: "+new Date().toString()));
		   document.add(new Paragraph("\n"));
		   document.add(new Paragraph("Informacja: '0' w kolumnie 'Cena' oznacza calkowity brak ceny dla danego art."));
		   document.add(new Paragraph("wtedy w Kalkulacja Koncowa rekord z tym art. zaznaczony jest na czerwono "));
		   document.add(new Paragraph("W przypadku kiedy w kolumnie jest jakas wartosc, oznacza to ze brak sredniej wazonej artykulu"));
		   document.add(new Paragraph("w kalkulacji koncowej taki rekord oznaczony jest jako normalny, choc cena jest tylko teoretyczna "));
		   document.add(new Paragraph("\n\n"));

		   
		   	table = new PdfPTable(new float[] {80,100,170,360,60,190,100});
		   	table.setTotalWidth(page_width);
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
	   	              Font font_main_project = new Font(bf, 22);
	   	  
	   				 
	   	
	   				   document.add(new Paragraph("\n\n"));
	   				PdfPTable table00;
		   			table00 = new PdfPTable(new float[] {page_width});
		   			table00.setTotalWidth(page_width);
		   			table00.setLockedWidth(true);

		   			
		   		 PdfPCell table_cell0000 = new PdfPCell(new Phrase("PROJEKT  : "+ listOfMissinObjects.get(i).getProject(),font_main_project));
		   		 table_cell0000.setHorizontalAlignment(Element.ALIGN_CENTER);
		   		 table00.addCell(table_cell0000);
		
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

	
	// not used right no
	public void Generate_Informations(List<String> lista, String destination) throws DocumentException, IOException
	{
		List<String> Projects_2 = new ArrayList<>();
		List<String> Projects_7 = new ArrayList<>();
		List<String> Projects_14 = new ArrayList<>();
		List<String> Projects_others = new ArrayList<>();
		
		// 1st parameter is a source of list to split, other are the target lists
		Split_array_to_4(lista,Projects_2,Projects_7,Projects_14,Projects_others);
		
		Collections.sort(Projects_2);


		
		    PdfPTable table = null;
			// GENEROWANIE DOKUMENTU PDF
		    Document document = new Document(PageSize.A2);
	        PdfWriter.getInstance(document, new FileOutputStream(destination));
	        
	        
	        document.open();

			   document.add(new Paragraph("Data wygenerowania raportu: "+new Date().toString()));
			   document.add(new Paragraph("\n"));
			   document.add(new Paragraph("Informacja: '0' w kolumnie 'Cena' oznacza calkowity brak ceny dla danego art."));
			   document.add(new Paragraph("wtedy w Kalkulacja Koncowa rekord z tym art. zaznaczony jest na czerwono "));
			   document.add(new Paragraph("W przypadku kiedy w kolumnie jest jakas wartosc, oznacza to ze brak sredniej wazonej artykulu"));
			   document.add(new Paragraph("w kalkulacji koncowej taki rekord oznaczony jest jako normalny, choc cena jest tylko teoretyczna "));
			   document.add(new Paragraph("\n\n"));
			   
				table = new PdfPTable(new float[] {200});
			   	table.setTotalWidth(200);
			   	table.setLockedWidth(true);
			   	
	            
   				document.add(table);
   				
   			// add 4 tables parrarel
   				PdfPTable mainTable = new PdfPTable(4);
   				mainTable.setWidthPercentage(100.0f);
   				

   		        
   				// table 1, with 3 columns
   		        PdfPTable Table_1 = new PdfPTable(3);
   		        Table_1.setWidthPercentage(40.0f);	
   		        
   		        for(int i = 0 ; i < Projects_2.size();i++)
   		        {
   		        	PdfPCell  cell1 = new PdfPCell(new Phrase(Projects_2.get(i)));	 
   		        	cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
   		        	Table_1.addCell(cell1);
   		        }
   		        mainTable.addCell(Table_1);
   		        
   		        
   		        // table 2
   		        PdfPTable Table_2 = new PdfPTable(1);
		        Table_2.setWidthPercentage(20.0f);
		        for(int i = 0 ; i < Projects_7.size();i++)
   		        {
   		        	PdfPCell  cell1 = new PdfPCell(new Phrase(Projects_7.get(i)));	  
   		        	cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
   		        	Table_2.addCell(cell1);
   		        }
      		     mainTable.addCell(Table_2);

      		    
    		    // table 3
		        PdfPTable Table_3 = new PdfPTable(1);
   		        Table_3.setWidthPercentage(20.0f);
   		        for(int i = 0 ; i < Projects_14.size();i++)
		        {
		        	PdfPCell  cell1 = new PdfPCell(new Phrase(Projects_14.get(i)));	  
   		        	cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	Table_3.addCell(cell1);
		        }        
      		     mainTable.addCell(Table_3);

   		        
     		    // table 4
   		        PdfPTable Table_4 = new PdfPTable(1);
	            Table_4.setWidthPercentage(20.0f);
	 	        for(int i = 0 ; i < Projects_others.size();i++)
		        {
		        	PdfPCell  cell1 = new PdfPCell(new Phrase(Projects_others.get(i)));	 
   		        	cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
		        	Table_4.addCell(cell1);
		        }     
      		     mainTable.addCell(Table_4);

      		     
      		     
      		     
		        document.add(mainTable);
		   		document.close();			   
	}


	private void Split_array_to_4(List<String> lista, List<String> projects_2, List<String> projects_7, List<String> projects_14, List<String> projects_others) 
	{
		
		for(int i = 0 ; i < lista.size();i++)
		{
			if(lista.get(i).startsWith("2/"))
				projects_2.add(lista.get(i));
			else if(lista.get(i).startsWith("7/"))
				projects_7.add(lista.get(i));
			else if(lista.get(i).startsWith("14/"))
				projects_14.add(lista.get(i));
			else
				projects_others.add(lista.get(i));			
		}
	}
	
	
	
}
