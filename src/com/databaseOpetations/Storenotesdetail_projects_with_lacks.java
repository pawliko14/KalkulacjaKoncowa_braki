package com.databaseOpetations;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.mariadb.jdbc.internal.com.read.dao.Results;

import com.DocumentGenerator.GenerateDocument;
import com.itextpdf.text.DocumentException;
import com.objectsDAO.MainObject;



/*
 *   DANE SA POPRWANIE POBIERANE, ZAPISYWANE.
 *   ZOSTALO DODAC ZAPISANIE DO PLIKU PDF
 *   
 *   NA KONIEC KOMPLETNY REFAKTOR TEGO PSEUDOKODU
 */

public class Storenotesdetail_projects_with_lacks {
	
	private static List<String> List_of_Project_with_lacks;
	public static List<MainObject> missing_objects_storenotesdetail; // do zmian
	private static List<MainObject> missing_objects_bestellingdetail;

	
	public static void main(String[] args) throws SQLException, DocumentException, IOException
	{
		
		
		System.out.println("program started");
		
		
		List_of_Project_with_lacks = new ArrayList<String>();
		missing_objects_storenotesdetail = new ArrayList<MainObject>();
		
		Connection conn=DriverManager.getConnection("jdbc:mariadb://192.168.90.123/fatdb","listy","listy1234");
		System.out.println("connection has been set");

		
		List<String> listOfProjects_withLack = RetriveList( conn);
		
		System.out.println("list retrieved");

//		printList();
		
		
		// sprawdzanie dla kazdego projektu pojdeynczo
		
		System.out.println("retrieve all objects");
	//	RetrieveMissingObjectsStorenotesDetail(conn);
		
		System.out.println("print all objects of projects");

//		for(MainObject s : missing_objects)
//			s.PrintList();
//		
		
		
		 RetriveAllobjectFromallProjects( conn ,listOfProjects_withLack) ;

		
		System.out.println("RetriveAllobjectFromallProjects done " );
		
		
		System.out.println("size of missing object list : " + missing_objects_storenotesdetail.size());

		
		
		/////////////////

		PrintStream out = new PrintStream(new FileOutputStream("C:\\Users\\el08\\Desktop\\programiki\\Lista_brakow_kalkulacja_koncowa\\Lista.txt"));

		System.out.println("Print to file ended");
		for(MainObject s : missing_objects_storenotesdetail)
			s.PrintToFile(out);
		
		
		////////////////
		System.out.println("Document genetare");

		GenerateDocument doc = new GenerateDocument();
		
			doc.Generate();
		
		
		System.out.println("program ended");
		
		
	}
	
	
	
	

	
	public static void RetriveAllobjectFromallProjects(Connection conn, List<String> list) throws SQLException
	{
		
		// storenotesdetail
		for(int i = 0 ; i < list.size(); i++)
		{			
		Statement s = conn.createStatement();
				
				ResultSet rs = s.executeQuery("select s.Leverancier, s.ORDERNUMMER, s.ARTIKELCODE, s.ARTIKELOMSCHRIJVING, \r\n" + 
						"s.MONTAGE, s.MONTAGEOMSCHRIJVING, s.EENHEIDSPRIJS\r\n" + 
						"from storenotesdetail s \r\n" + 
						"left join artikel_kostprijs a \r\n" + 
						"on s.ARTIKELCODE = a.ARTIKELCODE\r\n" + 
						"where a.soort is null \r\n" + 
						"and s.PROJECTNUMMER = '"+list.get(i)+"'");
				
				while(rs.next())
				{
					String project = list.get(i);
					String Leverancier = rs.getString("Leverancier");
					String ORDERNUMMER = rs.getString("ORDERNUMMER");
					String ARTIKELCODE = rs.getString("ARTIKELCODE");
					String ARTIKELOMSCHRIJVING = rs.getString("ARTIKELOMSCHRIJVING");
					String MONTAGE = rs.getString("MONTAGE");
					String MONTAGEOMSCHRIJVING = rs.getString("MONTAGEOMSCHRIJVING");
					String EENHEIDSPRIJS = rs.getString("EENHEIDSPRIJS");
		
					MainObject obj = new MainObject(project,Leverancier,ORDERNUMMER,ARTIKELCODE,ARTIKELOMSCHRIJVING,MONTAGE,MONTAGEOMSCHRIJVING,EENHEIDSPRIJS);
					
					
					missing_objects_storenotesdetail.add(obj);
				}
				
				s.close();
				rs.close();
		}
		
		//bestellingdetail <- bardzo spowalnia program a rezultat jest niewielki
//		for(int i = 0 ; i < list.size(); i++)
//		{			
//		Statement s = conn.createStatement();
//				
//				ResultSet rs = s.executeQuery("select s.Leverancier, s.ORDERNUMMER, s.ARTIKELCODE, s.ARTIKELOMSCHRIJVING, s.EENHEIDSPRIJS \r\n" + 
//						"                        from bestellingdetail s \r\n" + 
//						"                        left join artikel_kostprijs a  \r\n" + 
//						"                        on s.ARTIKELCODE = a.ARTIKELCODE\r\n" + 
//						"                        where a.soort is null \r\n" + 
//						"                        and s.AFDELING = '"+list.get(i).substring(0, 1)+"'\r\n" + 
//						"                        and s.AFDELINGSEQ = '"+list.get(i).substring(1, list.get(i).length())+"'");
//				
//				while(rs.next())
//				{
//					String Leverancier = rs.getString("Leverancier");
//					String ORDERNUMMER = rs.getString("ORDERNUMMER");
//					String ARTIKELCODE = rs.getString("ARTIKELCODE");
//					String ARTIKELOMSCHRIJVING = rs.getString("ARTIKELOMSCHRIJVING");
//					String MONTAGE  = " -- ";
//					String MONTAGEOMSCHRIJVING = " -- ";
//					String EENHEIDSPRIJS = rs.getString("EENHEIDSPRIJS");
//		
//					MainObject obj = new MainObject(Leverancier,ORDERNUMMER,ARTIKELCODE,ARTIKELOMSCHRIJVING,MONTAGE,MONTAGEOMSCHRIJVING,EENHEIDSPRIJS);
//					
//					
//					missing_objects_storenotesdetail.add(obj);
//				}
//				
//				s.close();
//				rs.close();
//		}
		
		
		
	}
	
	
//	public static void RetrieveMissingObjectsStorenotesDetail(Connection conn) throws SQLException
//	{
//		Statement s = conn.createStatement();
//		
//		ResultSet rs = s.executeQuery("select s.Leverancier, s.ORDERNUMMER, s.ARTIKELCODE, s.ARTIKELOMSCHRIJVING, \r\n" + 
//				"s.MONTAGE, s.MONTAGEOMSCHRIJVING, s.EENHEIDSPRIJS\r\n" + 
//				"from storenotesdetail s \r\n" + 
//				"left join artikel_kostprijs a \r\n" + 
//				"on s.ARTIKELCODE = a.ARTIKELCODE\r\n" + 
//				"where a.soort is null \r\n" + 
//				"and s.PROJECTNUMMER = '2/190550'");
//		
//		while(rs.next())
//		{
//			String Leverancier = rs.getString("Leverancier");
//			String ORDERNUMMER = rs.getString("ORDERNUMMER");
//			String ARTIKELCODE = rs.getString("ARTIKELCODE");
//			String ARTIKELOMSCHRIJVING = rs.getString("ARTIKELOMSCHRIJVING");
//			String MONTAGE = rs.getString("MONTAGE");
//			String MONTAGEOMSCHRIJVING = rs.getString("MONTAGEOMSCHRIJVING");
//			String EENHEIDSPRIJS = rs.getString("EENHEIDSPRIJS");
//
//			MainObject obj = new MainObject(Leverancier,ORDERNUMMER,ARTIKELCODE,ARTIKELOMSCHRIJVING,MONTAGE,MONTAGEOMSCHRIJVING,EENHEIDSPRIJS);
//			
//			
//			missing_objects_storenotesdetail.add(obj);
//		}
//		
//		s.close();
//		rs.close();
//
//	}
	

	
	
	
	
	public static List<String> RetriveList(Connection conn) throws SQLException
	{
		
		
		// storenotes detail
		
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery("select s.AFDELING, s.AFDELINGSEQ from storenotesdetail s \r\n" + 
						"left join artikel_kostprijs a \r\n" + 
						"on s.ARTIKELCODE = a.ARTIKELCODE\r\n" + 
						"where a.soort is null \r\n" + 
						"and s.AFDELING <> '500'\r\n" + 
						"group by s.AFDELINGSEQ "); 

				
				while(rs.next()) {
					
					String afdeling = rs.getString("AFDELING");
					String afdelingseq = rs.getString("AFDELINGSEQ");
					
					String project = afdeling + "/" + afdelingseq;
					
					
					List_of_Project_with_lacks.add(project);
					
				}
				
				s.close();
				rs.close();
				
		// bestelling detail
				
				 s = conn.createStatement();
				 rs = s.executeQuery("select s.AFDELING, s.AFDELINGSEQ from bestellingdetail s \r\n" + 
						"left join artikel_kostprijs a \r\n" + 
						"on s.ARTIKELCODE = a.ARTIKELCODE\r\n" + 
						"where a.soort is null \r\n" + 
						"and s.AFDELING <> '500'\r\n" + 
						"group by s.AFDELINGSEQ "); 

				
				while(rs.next()) {
					
					String afdeling = rs.getString("AFDELING");
					String afdelingseq = rs.getString("AFDELINGSEQ");
					
					String project = afdeling + "/" + afdelingseq;
					
					
					List_of_Project_with_lacks.add(project);
					
				}
				
				s.close();
				rs.close();
				
				
			// remove duplicates
				
		List<String> newList = 	List_of_Project_with_lacks.stream().distinct().collect(Collectors.toList());
		
		return newList;
				
				
	}
	
	public static void printList()
	{
		for(String s : List_of_Project_with_lacks)
			System.out.println(s);
	}

}
