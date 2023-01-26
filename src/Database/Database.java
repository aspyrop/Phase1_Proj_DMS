package Database;

import java.util.ArrayList;
import java.util.Collections;
import Entity.FileToDigitize;

public class Database {

	private ArrayList <FileToDigitize> database;

	public Database() {
		this.database = new ArrayList<>();
	}
	
	//------------------------------------------------------------------	
	public boolean isEmpty() {
		return (database.isEmpty());
	}

	//------------------------------------------------------------------
	public void clear() {
		database.clear();
	}
	
	//------------------------------------------------------------------
	public int size() {
		return database.size();
	}
	
	//------------------------------------------------------------------
	public ArrayList<FileToDigitize> getAllProducts() {
		return database;
	}
	
	//------------------------------------------------------------------
	public boolean add(FileToDigitize fileObj) {
		return database.add(fileObj);
	}
	
	//------------------------------------------------------------------
	public boolean remove(FileToDigitize fileObj) {
		return database.remove(fileObj);
	}	
	
	//------------------------------------------------------------------
	public String toString() {
		String AllData = "";
		for (FileToDigitize f : database)
			AllData = AllData.concat(f.getName_D()+ " ");
		return AllData;
	}

	//------------------------------------------------------------------
	public void sort() {
		Collections.sort(this.database);
	}

}
