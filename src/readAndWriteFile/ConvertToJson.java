package readAndWriteFile;

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
	
	public ArrayList<Category> getCategoryList(){
		return categoryList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}
	
	public void setCategoryList(ArrayList<Category> categoryList){
		this.categoryList = categoryList;
	}

	public String changeToJSonObj(boolean isTaskList){
		String jsonText ="";
		if(isTaskList){
			jsonText = taskToJsonConverter();
		}else {
			jsonText = categoryToJsonConverter();
		}
		return jsonText;
	}

	@SuppressWarnings("unchecked")
	private String taskToJsonConverter() {
		JSONArray tasks = new JSONArray();
		for (int i = 0 ; i < taskList.size(); i++){
			Task task = taskList.get(i);
			JSONObject taskJSonObj = new JSONObject();
			taskJSonObj.put(Constants.TASKKEYS[0], task.getCategory());
			taskJSonObj.put(Constants.TASKKEYS[1], task.getDescription());
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
			taskJSonObj.put(Constants.TASKKEYS[6], task.getTaskNote());
			assert(task.getTaskType()!=null);
			taskJSonObj.put(Constants.TASKKEYS[7], task.getTaskType().toString());
			tasks.add(taskJSonObj);
			
			
		}
		return tasks.toJSONString();
	}
	
	@SuppressWarnings("unchecked")
	private String categoryToJsonConverter() {
		JSONArray categories = new JSONArray();
		for (int i = 0 ; i < categoryList.size(); i++){
			Category category = categoryList.get(i);
			JSONObject categoryJSonObj = new JSONObject();
			categoryJSonObj.put(Constants.CATEGORYKEYS[0], category.getName());
			categoryJSonObj.put(Constants.CATEGORYKEYS[1], category.getCount());
			categories.add(categoryJSonObj);
			
			
		}
		return categories.toJSONString();
	}
	
}
