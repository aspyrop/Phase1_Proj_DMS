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
		dbObj = srvObj.LoadDatabase(); // load database
		
		if (dbObj == null) {
			// active folder is NOT valid
			srvObj.displayMsgFolderNotValid();
		}
		else {
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
						
						case 11: // OPERATIONS MENU: Display current database
							//******************************************************
							//   Case 11 hidden to FILE OPERATIONS MENU, used for
							//   testing purposes (display the files of the database).
							//   NOTE: REMOVE BEFORE APPLICATION RELEASE.
							//******************************************************
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
		
		} // end else (i.e. dbObj != null)
		
		srvObj.applicationExitMessage();
		
	} // end of public static void main
	
} // end of public class UserInterface
