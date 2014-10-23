package readAndWriteFile;

import java.io.File;
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

import taskDo.Task;
import taskDo.TaskType;
import commonClasses.Constants;

/*
 * @author Paing Zin Oo(Jack)
 */

public class ReadAndWriteToFile {
	private String jsonText;

	public String getjSonText() {
		return jsonText;
	}

	public void setjSonText(String jSonText) {
		this.jsonText = jSonText;
	} 
	
	public boolean writeToFile(){
		FileWriter file = null; 
		try {
			file = new FileWriter(Constants.FILENAME);
			file.write(jsonText);
			file.flush();
			file.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public ArrayList<Task> readFromFile(){
		ArrayList<Task> taskList = new ArrayList<Task>();
		File f = new File(Constants.FILENAME);
		if(f.exists()){
			if(f.length()!=0){
				JSONParser parser = new JSONParser();
				
				try{
					JSONArray jsonObjectArr = (JSONArray) parser.parse(new FileReader(Constants.FILENAME));
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
			}
			
		}
		
		
		return taskList;
	}

	private Task extractTaskField(JSONObject jsonObject) {
		Task task = new Task();
		task.setCategory((String) jsonObject.get(Constants.TASKKEYS[0]));
		task.setDescription((String) jsonObject.get(Constants.TASKKEYS[1]));
		task.setImportant((boolean) jsonObject.get(Constants.TASKKEYS[2]));
		task.setCompleted((boolean) jsonObject.get(Constants.TASKKEYS[5]));
		task.setTaskNote((String) jsonObject.get(Constants.TASKKEYS[7]));
		task.setTaskType(TaskType.valueOf((String)jsonObject.get(Constants.TASKKEYS[6])));
		String str_dueDate = (String) jsonObject.get(Constants.TASKKEYS[3]);
		if(str_dueDate.isEmpty()){
			task.setDueDate(null);
		}else{
			DateTime dueDate = DateTime.parse(str_dueDate);
			task.setDueDate(dueDate);
		}
		String str_startDate = (String) jsonObject.get(Constants.TASKKEYS[4]);
		if(str_startDate.isEmpty()){
			task.setStartDate(null);
		}else{
			DateTime startDate = DateTime.parse(str_startDate);
			task.setStartDate(startDate);
		}
		return task;
	}
}
