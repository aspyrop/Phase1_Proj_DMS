package Entity;

import java.io.File;

public class FileToDigitize implements Comparable<FileToDigitize> {

	File dfileObj = null;
	
	public FileToDigitize(File fileObj) {
		this.dfileObj = fileObj;
	}
	
	public String getName_D() {
		return this.dfileObj.getName();
	}
	
	public File getStandardFileObj() {
		return this.dfileObj;
	}

	@Override
	public int compareTo(FileToDigitize otherFileObj) {
		return this.getName_D().compareTo(otherFileObj.getName_D());
	}

}
