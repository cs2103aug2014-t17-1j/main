package readAndWriteFile;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import taskDo.Category;
import taskDo.Task;

import commonClasses.Constants;
/*
 * @author Paing Zin Oo(Jack)
 */
public class ConvertToJson {
	private ArrayList<Task> taskList;
	private ArrayList<Category> categoryList;

	public ArrayList<Task> getTaskList() {
		return taskList;
	}
	
	public void setCategoryList(ArrayList<Category> categoryList){
		this.categoryList = categoryList;
	}
	
	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}
	
	
	public String changeToJSonObj(boolean isTask){
		String result ="";
		if(isTask){
			result = extractTaskFields();
		} else{
			result = extractCategoryFields();
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	private String extractCategoryFields() {
		JSONArray categories = new JSONArray();
		for (int i = 0 ; i < categoryList.size(); i++){
			Category category = categoryList.get(i);
			JSONObject categoryJSonObj = new JSONObject();
			categoryJSonObj.put(Constants.CATEGORYKEYS[0], category.getName());
			categoryJSonObj.put(Constants.CATEGORYKEYS[1], category.getCount());
			categories.add(categoryJSonObj);
			
			
		}
		return categories.toString();
	}

	@SuppressWarnings("unchecked")
	private String extractTaskFields() {
		JSONArray tasks = new JSONArray();
		for (int i = 0 ; i < taskList.size(); i++){
			Task task = taskList.get(i);
			JSONObject taskJSonObj = new JSONObject();
			taskJSonObj.put(Constants.TASKKEYS[0], task.getCategory());
			taskJSonObj.put(Constants.TASKKEYS[1], task.getTitle());
			taskJSonObj.put(Constants.TASKKEYS[2], task.isImportant());
			if(task.getDueDate() == null){
				taskJSonObj.put(Constants.TASKKEYS[3], "");
				
			}else{
				taskJSonObj.put(Constants.TASKKEYS[3], task.getDueDate().toString());
			}
			if(task.getStartDate() == null){
				taskJSonObj.put(Constants.TASKKEYS[4], "");
			}else{
				taskJSonObj.put(Constants.TASKKEYS[4], task.getStartDate().toString());
			}
			taskJSonObj.put(Constants.TASKKEYS[5], task.isCompleted());
			taskJSonObj.put(Constants.TASKKEYS[6], task.getTaskType()+"");
			taskJSonObj.put(Constants.TASKKEYS[7], task.getNote());
			tasks.add(taskJSonObj);
			
			
		}		
		return tasks.toJSONString();
	}
	
}
