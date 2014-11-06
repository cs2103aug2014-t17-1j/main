package readAndWriteFile;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.DateTime;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import taskDo.Task;
import taskDo.TaskType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

/*
 * @author Paing Zin Oo(Jack)
 */

public class ReadAndWriteToFile {
	private String jsonText;
	private static final Logger log = Logger.getLogger(ReadAndWriteToFile.class.getName() );

	public String getjSonText() {
		return jsonText;
	}

	public void setjSonText(String jSonText) {
		this.jsonText = jSonText;
	} 
	
	public boolean writeToFile(boolean isTask){
		FileWriter file = null; 
		try {
			file = new FileWriter(Constants.FILENAME_TASKDO);
			file.write(jsonText);
			file.flush();
			file.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public ArrayList<Task> readTasksFromFile(){
		ArrayList<Task> taskList = new ArrayList<Task>();
		File f = new File(Constants.FILENAME_TASKDO);
		if(f.exists()){
			if(f.length()!=0){
		/*		JSONParser parser = new JSONParser();
				try{
					JSONArray jsonObjectArr = (JSONArray) parser.parse(new FileReader(Constants.FILENAME_TASKDO));
					for(Object obj : jsonObjectArr){
						JSONObject jsonObject = (JSONObject) obj;
						Task task = extractTaskFields(jsonObject);
						taskList.add(task);
					}
				}catch(FileNotFoundException e){
					e.printStackTrace();
				}catch(IOException e){
					e.printStackTrace();
				}catch(ParseException e){
					e.printStackTrace();
				} */
				try {	
					JSONTokener tokener = new JSONTokener(new FileReader(Constants.FILENAME_TASKDO));
					JSONArray jsonArray = new JSONArray(tokener);
					Task task;
					for(int i=0;i<jsonArray.length();i++) {
						JSONObject jsonObject = (JSONObject)jsonArray.getJSONObject(i);
						try {
							task = extractTaskFields(jsonObject);
						} catch (Exception e) {
							continue;
						}
						taskList.add(task);
					} 
				} catch(Exception e) {
					log.log(Level.FINER, e.toString(), e);
					SummaryReport.setFeedBackMsg(Constants.MESSAGE_LOAD_ERROR);
				}
			}	
		}
		return taskList;
}

//	public ArrayList<Category> readCategoriesFromFile(){
//		ArrayList<Category> categoryList = new ArrayList<Category>();
//		File f = new File(Constants.FILENAME_CATEGORY);
//		if(f.exists()){
//			if(f.length()!=0){
//				JSONParser parser = new JSONParser();
//				
//				try{
//					JSONArray jsonObjectArr = (JSONArray) parser.parse(new FileReader(Constants.FILENAME_CATEGORY));
//					for(Object obj : jsonObjectArr){
//						JSONObject jsonObject = (JSONObject) obj;
//						Category category = extractCategoryFields(jsonObject);
//						categoryList.add(category);
//					}
//				}catch(FileNotFoundException e){
//					e.printStackTrace();
//				}catch(IOException e){
//					e.printStackTrace();
//				}catch(ParseException e){
//					e.printStackTrace();
//				}
//			}
//			
//		}
//		
//		
//		return categoryList;
//	}
//	private Category extractCategoryFields(JSONObject jsonObject) {
//		Category category = new Category((String)jsonObject.get(Constants.CATEGORYKEYS[0]));
//		String count = (String)jsonObject.get(Constants.CATEGORYKEYS[1]);
//		category.setCount(Integer.parseInt(count));
//		return null;
//	}

	private Task extractTaskFields(JSONObject jsonObject) throws JSONException {
		Task task = new Task();
		task.setTitle((String) jsonObject.get(Constants.TASKKEYS[0]));
		task.setCategory((String) jsonObject.get(Constants.TASKKEYS[1]));
		task.setImportant((boolean) jsonObject.get(Constants.TASKKEYS[2]));
		String str_startDate = (String) jsonObject.get(Constants.TASKKEYS[3]);
		if(str_startDate.isEmpty()){
			task.setStartDate(null);
		}else{
			DateTime startDate = DateTime.parse(str_startDate);
			task.setStartDate(startDate);
		}
		String str_dueDate = (String) jsonObject.get(Constants.TASKKEYS[4]);
		if(str_dueDate.isEmpty()){
			task.setDueDate(null);
		}else{
			DateTime dueDate = DateTime.parse(str_dueDate);
			task.setDueDate(dueDate);
		}
		task.setCompleted((boolean) jsonObject.get(Constants.TASKKEYS[5]));
		task.setTaskType(TaskType.valueOf((String)jsonObject.get(Constants.TASKKEYS[6])));
		task.setNote((String) jsonObject.get(Constants.TASKKEYS[7]));
		
		return task;
	}
}
