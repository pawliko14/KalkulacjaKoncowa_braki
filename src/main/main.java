package main;

import java.io.IOException;
import java.sql.SQLException;

import com.databaseOpetations.Storenotesdetail_projects_with_lacks;
import com.itextpdf.text.DocumentException;

public class main {

	public static void main(String[] args) throws SQLException, DocumentException, IOException {


		Storenotesdetail_projects_with_lacks ob = new Storenotesdetail_projects_with_lacks();	
		ob.StartProgram();
		
		
		System.out.println("program ended");

	}

}
