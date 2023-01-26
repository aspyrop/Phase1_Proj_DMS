package UserInterface;

import java.util.Scanner;
import Database.Database;
import Service.Service;

public class UserInterface {
	
	public static void main(String[] args) {

		// initializations
		Scanner sc = new Scanner(System.in);
		Database dbObj = new Database(); // create database layer
		Service srvObj = new Service(dbObj); // create service layer
		srvObj.LoadDatabase(); // load database
		
		int choice, operchoice;
		
		boolean exitApplication = false;
		while (!exitApplication) {
			
			srvObj.displayMenu(true);
			choice = srvObj.userMenuChoice(sc);
			
			switch (choice) { // MAIN MENU
			
			case 1: // MAIN MENU: Display File Names (Asc Order)
				
				srvObj.sort();
				srvObj.displayFileNames();
				break;
			
			case 2: // MAIN MENU: File Operations
				
				boolean exitOperationsMenu = false;
				while (!exitOperationsMenu) {
					
					srvObj.displayMenu(false);
					operchoice = srvObj.userMenuChoice(sc);
					
					switch (operchoice) { // FILE OPERATIONS MENU
					
					case 1: // OPERATIONS MENU: Add New File (ignore case sensitivity)
						srvObj.addNewFile(srvObj.userInput_FileName(sc));
						break;
					
					case 2: // OPERATIONS MENU: Delete Existing File (add case sensitivity & display msg if NF)
						if (!srvObj.isEmpty())
							srvObj.removeExistingFile(srvObj.userInput_FileName(sc));
						else
							srvObj.displayMsgFolderEmpty();
						break;
						
					case 3: // OPERATIONS MENU: Find Existing File (add case sensitivity) & display msg F/NF
						if (!srvObj.isEmpty())
							srvObj.getFileData(srvObj.userInput_FileName(sc));
						else
							srvObj.displayMsgFolderEmpty();
						break;
						
					case 4: // OPERATIONS MENU: Back to MAIN MENU
						exitOperationsMenu = true;
						break;
					
					//******************************************************
					//    DELETE CASE 11 BEFORE ASSESSMENT'S SUBMISSION
					//******************************************************
					case 11: // OPERATIONS MENU: Display current database
						srvObj.displayFileNames();
						break;
						
					default:
						srvObj.displayMsgWrongChoice();
						
					} // end switch FILE OPERATIONS MENU
				} // end while related to OPERATIONS MENU
				
				break;
			
			case 3: // MAIN MENU: Exit application
				exitApplication = true;
				break;
			
			default:
				srvObj.displayMsgWrongChoice();
				
			} // // end switch MAIN MENU
		} // end while related to MAIN MENU
		
		srvObj.applicationExitMessage();
		
	} // end of public static void main
} // end of public class UserInterface
