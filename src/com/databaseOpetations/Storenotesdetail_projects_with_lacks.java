package com.databaseOpetations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.mariadb.jdbc.internal.com.read.dao.Results;

import com.DocumentGenerator.GenerateDocument;
import com.itextpdf.text.DocumentException;
import com.objectsDAO.MainObject;
import com.parameters.Parameters;

import DatabaseConnection.RCPdatabaseConnection;



public class Storenotesdetail_projects_with_lacks {
	
	private static List<String> List_of_Project_with_lacks;
	public static List<MainObject> missing_objects_storenotesdetail; // do zmian
	private SimpleDateFormat godz = new SimpleDateFormat("HH-mm");
	private SimpleDateFormat doNazwy2 = new SimpleDateFormat("yyyy.MM.dd");
	private Calendar date = Calendar.getInstance();
	private Connection conn = RCPdatabaseConnection.dbConnector();


	public void StartProgram() throws SQLException, DocumentException, IOException
	{
		 
		PrintStream out;	
		
		try {
			out = new PrintStream(new FileOutputStream(Parameters.GetPath_to_Log_file()));
			System.setOut(out);
			System.setErr(out);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}		
	
		File theDir = new File(Parameters.getPath_to_folder()+doNazwy2.format(date.getTime()));
		if (!theDir.exists()) {
		    try{
		        theDir.mkdir();
		    } 
		    catch(SecurityException se){
		    	System.out.println("Blad w tworzeniu folderu z listami");
		    }  
		}
		
		
		 String path = Parameters.getPath_to_folder()+"\\"+doNazwy2.format(date.getTime());	
		
		System.out.println("program started");
		List_of_Project_with_lacks = new ArrayList<String>();
		missing_objects_storenotesdetail = new ArrayList<MainObject>();
		
		
		System.out.println("connection has been set");

		
		List<String> listOfProjects_withLack = RetriveList( conn);
		
		System.out.println("list retrieved");

		printList();
		
		
	//	GenerateDocument d = new GenerateDocument();	
	//	d.Generate_Informations(listOfProjects_withLack);
		
		
		
		
		 RetriveAllobjectFromallProjects( conn ,listOfProjects_withLack) ;
		System.out.println("RetriveAllobjectFromallProjects done " );
		
		
		System.out.println("size of missing object list : " + missing_objects_storenotesdetail.size());

		
		// sort
		System.out.println("Sorting array:");
		Collections.sort(missing_objects_storenotesdetail);
		
		
		////////////////
		System.out.println("Document genetare");
		
		String name = "Lista_brakow.pdf";
		File f = new File(path+ "\\" +name);
		if(f.exists() && !f.isDirectory())
			name = godz.format(date.getTime())+"_" + name;
		
		
		
		GenerateDocument doc = new GenerateDocument();		
			doc.Generate(path + "\\" + name);
				
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
		
		for(int i = 0 ; i < list.size(); i++)
		{			
			

			
		Statement s = conn.createStatement();
				
				ResultSet rs = s.executeQuery("select s.Leverancier, s.ORDERNUMMER, s.ARTIKELCODE, s.ARTIKELOMSCHRIJVING, s.EENHEIDSPRIJS \r\n" + 
						"                        from bestellingdetail s \r\n" + 
						"                        left join artikel_kostprijs a  \r\n" + 
						"                        on s.ARTIKELCODE = a.ARTIKELCODE\r\n" + 
						"                        where a.soort is null \r\n" + 
						"                        and s.AFDELING = '"+list.get(i).substring(0, 1)+"'\r\n" + 
						"                        and s.AFDELINGSEQ = '"+list.get(i).substring(2, list.get(i).length())+"'");
		
		
				
				while(rs.next())
				{
					String project = list.get(i);
					String Leverancier = rs.getString("Leverancier");
					String ORDERNUMMER = rs.getString("ORDERNUMMER");
					String ARTIKELCODE = rs.getString("ARTIKELCODE");
					String ARTIKELOMSCHRIJVING = rs.getString("ARTIKELOMSCHRIJVING");
					String MONTAGE  = " -- ";
					String MONTAGEOMSCHRIJVING = " -- ";
					String EENHEIDSPRIJS = rs.getString("EENHEIDSPRIJS");
		
					MainObject obj = new MainObject(project,Leverancier,ORDERNUMMER,ARTIKELCODE,ARTIKELOMSCHRIJVING,MONTAGE,MONTAGEOMSCHRIJVING,EENHEIDSPRIJS);
					
					
					missing_objects_storenotesdetail.add(obj);
				}
				
				s.close();
				rs.close();
		}
		
		
		
	}
	

	
	
	public static List<String> RetriveList(Connection conn) throws SQLException
	{	
		// storenotes detail  - > only for projects 2,6,14
				Statement s = conn.createStatement();
				ResultSet rs = s.executeQuery("select s.AFDELING, s.AFDELINGSEQ from storenotesdetail s \r\n" + 
						"                        left join artikel_kostprijs a \r\n" + 
						"                        on s.ARTIKELCODE = a.ARTIKELCODE\r\n" + 
						"                        where a.soort is null \r\n" + 
						"                        and s.AFDELING <> '500'\r\n" + 
						"                        and (s.AFDELING = '2' or s.AFDELING ='14')\r\n" + 
						"                        group by s.AFDELINGSEQ"); 

				
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
				 		"                        left join artikel_kostprijs a \r\n" + 
				 		"                        on s.ARTIKELCODE = a.ARTIKELCODE\r\n" + 
				 		"                        where a.soort is null \r\n" + 
				 		"                        and s.AFDELING <> '500'\r\n" + 
				 		"                        and (s.AFDELING = '2' or s.AFDELING ='14')\r\n" + 
				 		"                        group by s.AFDELINGSEQ"); 

				
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
