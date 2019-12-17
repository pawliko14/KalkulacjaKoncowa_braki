package com.parameters;

public class Parameters {
		
	// path to local computer - tests
	private static String SavingPath_Lista_brakow  = "C://Users/el08/Desktop/programiki/Lista_brakow_kalkulacja_koncowa/Lista_brakow.pdf";
	private static String Path_to_Log_File = "C://Users//el08//Desktop//programiki//Lista_brakow_kalkulacja_koncowa//Log.txt";
	private static String Path_to_folder = "C://Users//el08//Desktop//programiki//Lista_brakow_kalkulacja_koncowa//";
	
	

	// final path on dataserver
//	private static String SavingPath_Lista_brakow  = "\\\\dataserver\\Common\\Programy\\Kalkulacja_koncowa\\Lista_brakow.pdf";
//	private static String Path_to_Log_File = "\\\\dataserver\\Common\\Programy\\Kalkulacja_koncowa\\Log.txt";
//	private static String Path_to_folder = "\\\\dataserver\\Common\\Programy\\Kalkulacja_koncowa\\";

	
	
	public static String getPath_to_folder() {
		return Path_to_folder;
	}



	public static String GetPath_to_Log_file()
	{
		return Path_to_Log_File;
	}
	
	
	
	public static String getSavingPath_Lista_brakow() {
		return SavingPath_Lista_brakow;
	}

	
	

}
