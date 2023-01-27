package Service;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import Database.Database;
import Entity.FileToDigitize;

public class Service {

	private Database database;
	
	static final String COMPANY_NAME = "Lockers Pvt. Ltd";
	static final String APPLICATION_NAME = "DMS | Digitization Management System";
	static final String ACTIVE_PATH = 
			"C:\\Users\\aspyr\\git\\repository\\JavaTraining\\Phase1_Proj_DMS\\SampleFiles";
	static final String SYSTEM_OUTPUT_SYMBOL = " [DMS] ===> ";
	
	public Service(Database database) {
	  	this.database = database;
	}
	
	/*****************************************************************************************
	 * SECTION : PROCESSES FOR BASIC DB HANDLINGS 
	 *****************************************************************************************/

	//------------------------------------------------------------------
	public Database LoadDatabase() {
		File dirObj = new File(ACTIVE_PATH);
		if (dirObj.listFiles() == null) {
			return null;
		}
		for (File f : dirObj.listFiles()) {
			this.database.add(new FileToDigitize(f));
		}
		return this.database;
	}
	
	//------------------------------------------------------------------
	public void clearDatabase() {
		if (!this.database.isEmpty())
			this.database.clear();
	}
	
	//------------------------------------------------------------------
	public boolean isEmpty() {
		if (this.database.isEmpty())
			return true;
		else
			return false;
	}

	//------------------------------------------------------------------
	public File createFileObj (String filenameStr) {
		File fileObj = new File(ACTIVE_PATH + "\\" + filenameStr);
		return fileObj;
	}
	
	//------------------------------------------------------------------
	public FileToDigitize searchFileObj (String filenameStr) {
		for (FileToDigitize fileObj : this.database.getAllProducts())
			if (fileObj.getName_D().equals(filenameStr))
				return fileObj;
		return null;
	}
	
	//------------------------------------------------------------------
	public void sort() {
		database.sort();
	}
	
	/*****************************************************************************************
	 * SECTION : PROCESSES FOR ADDING, DELETING, SEARCHING, DISPLAYING FILES
	 *****************************************************************************************/
	
	//------------------------------------------------------------------
	public void displayFileNames() {
		
		System.out.println(SYSTEM_OUTPUT_SYMBOL + "Files in Active Folder:\n");
		
		for (FileToDigitize fileObj : database.getAllProducts())
			System.out.println(" - " + fileObj.getName_D());
		System.out.println("_".repeat(3));
		System.out.println("Total files: " + database.size());
	}
	
	//------------------------------------------------------------------
	public void addNewFile(String filenameStr) {
		
		System.out.print(SYSTEM_OUTPUT_SYMBOL);
		
		try {
			FileToDigitize fileObj = searchFileObj(filenameStr);
			if (fileObj == null) {
				// create the new file
				File f = createFileObj(filenameStr);
				f.createNewFile();
				// add the new file in the database (collection)
				FileToDigitize fileObjToAdd = new FileToDigitize(f);
				database.add(fileObjToAdd);
				System.out.println("New file '" + filenameStr + "' created & added to active folder.\n");
			}
			else
				System.out.println("File '" + filenameStr + "' already exists.\n");
		}
		catch (IOException e) {
			System.out.println("Filename '" + filenameStr + "' NOT valid.\n");	
		}
	}
	
	//------------------------------------------------------------------
	public void removeExistingFile(String filenameStr) {

		System.out.print(SYSTEM_OUTPUT_SYMBOL);
		
		FileToDigitize fileObj = searchFileObj(filenameStr);
		if(fileObj == null)
			System.out.println("File '" + filenameStr + "' does NOT exist.\n");
		else {
			File f = fileObj.getStandardFileObj(); // get corresponding obj of type File
			if (!f.delete())
				System.out.println("Existing file '" + filenameStr + "' NOT deleted. Check file attributes.\n");						
			else {
				database.remove(fileObj);
				System.out.println("File '" + filenameStr + "' deleted.\n");	
			}
		}
	}
	
	//------------------------------------------------------------------
	public void getFileData(String filenameStr) {

		System.out.print(SYSTEM_OUTPUT_SYMBOL);
		
		FileToDigitize fileObj = searchFileObj(filenameStr);
		if(fileObj == null) {
			System.out.println("File '" + filenameStr + "' does NOT exist.\n");
		}
		else {
			File f = fileObj.getStandardFileObj(); // get corresponding obj of type File
			System.out.println("File '" + filenameStr + "' Found. Main Attributes:\n");
			System.out.println(" - Path    : " + f.getAbsolutePath());
			System.out.println(" - KBytes  : " + Math.round(f.length()/1024.0));
			System.out.println(" - Read    : " + f.canRead());
			System.out.println(" - Write   : " + f.canWrite());
			System.out.println(" - Execute : " + f.canExecute());			
		}	
	}

	/*****************************************************************************************
	 * SECTION : GETTING USER INPUT
	 *****************************************************************************************/
	
	//------------------------------------------------------------------
	public int userMenuChoice(Scanner sc) {

		int i = -1;
		while (i < 0) {
			String input = sc.nextLine();
			try { i = Integer.parseInt(input); }
			catch (NumberFormatException e) { i = 0; }
		}
		return i;
	}
	
	//------------------------------------------------------------------
	public String userInput_FileName(Scanner sc) {
		
		System.out.print(SYSTEM_OUTPUT_SYMBOL);
		System.out.println("Enter filename.filetype ?");
		String fileStrName = sc.next();
		sc.nextLine();
		return fileStrName;
	}
	
	/*****************************************************************************************
	 * SECTION : DISPLAYING MENU / MESSAGES
	 *****************************************************************************************/
	
	//------------------------------------------------------------------
	public void displayMenu(boolean isMainMenu) {
		
		String menuTitle = "F I L E     O P E R A T I O N S";
		String authorData = "Author: Agis.Spyropoulos@vodafone.com";
		String stripeElement = "-";
		String activePath = "Active Folder: " + ACTIVE_PATH;

		byte maxWidth = 90;
		
		System.out.println();
		System.out.println(" ".repeat(maxWidth - 1) + "-");
		
		if (isMainMenu) {
			stripeElement = "=";
			menuTitle = "M A I N     M E N U";
			System.out.println(" ".repeat(maxWidth - COMPANY_NAME.length()) + COMPANY_NAME);
			System.out.println(" ".repeat(maxWidth - APPLICATION_NAME.length()) + APPLICATION_NAME);
			System.out.println(" ".repeat(maxWidth - authorData.length()) + authorData);
			System.out.println(" ".repeat(maxWidth - activePath.length()) + activePath + "\n");
		}
		else {
			System.out.println(" ".repeat(maxWidth - APPLICATION_NAME.length()) + APPLICATION_NAME);
		}
		System.out.println(stripeElement.repeat(maxWidth));
		System.out.println(" ".repeat((maxWidth - menuTitle.length())/2) + menuTitle);
		System.out.println(stripeElement.repeat(maxWidth));
		System.out.println("\nChoose one of the following options:\n");
		
		if (isMainMenu) {
			System.out.println("1. Display File Names");
			System.out.println("2. File Operations");
			System.out.println("3. Exit DMS");			
		}
		else {
			System.out.println("1. Add New File (ignore case sensitivity)");
			System.out.println("2. Delete Existing File (case sensitive");
			System.out.println("3. Find Existing File (case sensitive)");
			System.out.println("4. Back to MAIN MENU");					
		}
		System.out.println("?");
	}
	
	//------------------------------------------------------------------
	public void applicationExitMessage () {
		System.out.print(SYSTEM_OUTPUT_SYMBOL);
		System.out.println("Application Exit!");
		System.out.println("Thanks for using our " + APPLICATION_NAME + ".");
		System.out.println("Have a nice day!");
		System.out.println("___");
		System.out.println(COMPANY_NAME);
	}

	//------------------------------------------------------------------
	public void displayMsgFolderEmpty() {
		System.out.print(SYSTEM_OUTPUT_SYMBOL);
		System.out.println("Active folder empty! No files to delete OR search for.\n");
	}	
	
	//------------------------------------------------------------------
	public void displayMsgFolderNotValid() {
		System.out.print(SYSTEM_OUTPUT_SYMBOL);
		System.out.println(ACTIVE_PATH + "\n");
		System.out.println("Above 'static final String ACTIVE_PATH' for the active folder, is NOT valid!");
		System.out.println("Please specify in code a valid path for 'static final String ACTIVE_PATH'.\n");
	}

	//------------------------------------------------------------------
	public void displayMsgWrongChoice() {
		System.out.print(SYSTEM_OUTPUT_SYMBOL);
		System.out.println("Choice NOT valid.\n");
	}
		
}
