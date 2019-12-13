package com.objectsDAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class MainObject {
	
	private String Project;
	private String leverancier;
	private String ordernummer;
	private String  artikelcode;
	private String artikelomschrijving;
	private String  montage;
	private String  montageomschirjving;
	private String  eenhejdprijs;
	
	
	public MainObject(String project, String leverancier ,String ordernummer, String artikelcode, String artikelomschrijving, String montage,
			String montageomschirjving, String eenhejdprijs) {
		this.Project = project;
		this.leverancier = leverancier;
		this.ordernummer = ordernummer;
		this.artikelcode = artikelcode;
		this.artikelomschrijving = artikelomschrijving;
		this.montage = montage;
		this.montageomschirjving = montageomschirjving;
		this.eenhejdprijs = eenhejdprijs;
	}


	public MainObject() {
		// TODO Auto-generated constructor stub
	}
	
	


	public String getProject() {
		return Project;
	}


	public void setProject(String project) {
		Project = project;
	}


	public String getOrdernummer() {
		return ordernummer;
	}


	public void setOrdernummer(String ordernummer) {
		this.ordernummer = ordernummer;
	}


	public String getArtikelcode() {
		return artikelcode;
	}


	public void setArtikelcode(String artikelcode) {
		this.artikelcode = artikelcode;
	}


	public String getArtikelomschrijving() {
		return artikelomschrijving;
	}


	public void setArtikelomschrijving(String artikelomschrijving) {
		this.artikelomschrijving = artikelomschrijving;
	}


	public String getMontage() {
		return montage;
	}


	public void setMontage(String montage) {
		this.montage = montage;
	}


	public String getMontageomschirjving() {
		return montageomschirjving;
	}


	public void setMontageomschirjving(String montageomschirjving) {
		this.montageomschirjving = montageomschirjving;
	}


	public String getEenhejdprijs() {
		return eenhejdprijs;
	}


	public void setEenhejdprijs(String eenhejdprijs) {
		this.eenhejdprijs = eenhejdprijs;
	}





	@Override
	public String toString() {
		return "MainObject [leverancier=" + leverancier + ", ordernummer=" + ordernummer + ", artikelcode="
				+ artikelcode + ", artikelomschrijving=" + artikelomschrijving + ", montage=" + montage
				+ ", montageomschirjving=" + montageomschirjving + ", eenhejdprijs=" + eenhejdprijs + "]";
	}


	public String getLeverancier() {
		return leverancier;
	}


	public void setLeverancier(String leverancier) {
		this.leverancier = leverancier;
	}
	

	public void PrintList()
	{
		System.out.println("---------------------");
		System.out.println("Obiekt: ");
		System.out.println("Projekt:  " + getProject());
		System.out.println("leverancier" + getLeverancier());
		System.out.println("ordernummer" + getOrdernummer());
		System.out.println("artikelcode" + getArtikelcode());
		System.out.println("artikelomschrijving" + getArtikelomschrijving());
		System.out.println("montage" + getMontage());
		System.out.println("montageomschirjving" + getMontageomschirjving());
		System.out.println("eenhejdprijs" + getEenhejdprijs());

	}
	
	public  void PrintToFile(PrintStream out) throws FileNotFoundException
	{
		
		System.setOut(out);
		
		out.println("---------------------");
		out.println("Obiekt: ");
		out.println("Projekt:  " + getProject());
		out.println("leverancier: " + getLeverancier());
		out.println("ordernummer: " + getOrdernummer());
		out.println("artikelcode: " + getArtikelcode());
		out.println("artikelomschrijving: " + getArtikelomschrijving());
		out.println("montage: " + getMontage());
		out.println("montageomschirjving: " + getMontageomschirjving());
		out.println("eenhejdprijs: " + getEenhejdprijs());
	
	}
	
	

}
