package readAndWriteFile;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import commonClasses.StringConstants;

import taskDo.Task;

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
			file = new FileWriter(StringConstants.FILENAME);
			file.write(jSonText);
			file.flush();
			file.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Task> readFromFile(){
		JSONParser parser = new JSONParser();
		ArrayList<Task> taskList = new ArrayList<Task>();
		try{
			JSONArray jsonObjectArr = (JSONArray) parser.parse(new FileReader(StringConstants.FILENAME));
			for(Object obj : jsonObjectArr){
				JSONObject jsonObject = (JSONObject) obj;
				Task task = extractTaskField(jsonObject);
				taskList.add(task);
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}catch(ParseException e){
			e.printStackTrace();
		}
		
		return taskList;
	}

	private Task extractTaskField(JSONObject jsonObject) {
		Task task = new Task();
		task.setCategory((String) jsonObject.get(StringConstants.TASKKEYS[0]));
		task.setDescription((String) jsonObject.get(StringConstants.TASKKEYS[1]));
		task.setImportant((boolean) jsonObject.get(StringConstants.TASKKEYS[2]));
		task.setCompleted((boolean) jsonObject.get(StringConstants.TASKKEYS[5]));
		String str_dueDate = (String) jsonObject.get(StringConstants.TASKKEYS[3]);
		if(str_dueDate.isEmpty()){
			task.setDueDate(null);
		}else{
			DateTime dueDate = DateTime.parse(str_dueDate);
			task.setDueDate(dueDate);
		}
		String str_startDate = (String) jsonObject.get(StringConstants.TASKKEYS[4]);
		if(str_startDate.isEmpty()){
			task.setStartDate(null);
		}else{
			DateTime startDate = DateTime.parse(str_startDate);
			task.setStartDate(startDate);
		}
		return task;
	}
}
