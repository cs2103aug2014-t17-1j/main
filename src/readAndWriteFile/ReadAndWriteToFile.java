package readAndWriteFile;

import java.io.FileWriter;
import java.io.IOException;

/*
 * @author Paing Zin Oo(Jack)
 */

public class ReadAndWriteToFile {
	private String jSonText;

	public String getjSonText() {
		return jSonText;
	}

	public void setjSonText(String jSonText) {
		this.jSonText = jSonText;
	} 
	
	public boolean writeToFile(){
		FileWriter file = null; 
		try {
			file = new FileWriter("TaskDo.json");
			file.write(jSonText);
			file.flush();
			file.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
