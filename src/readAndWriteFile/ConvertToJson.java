package readAndWriteFile;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import commonClasses.Constants;

import taskDo.Task;
/*
 * @author Paing Zin Oo(Jack)
 */
public class ConvertToJson {
	private ArrayList<Task> taskList;

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}
	
	@SuppressWarnings("unchecked")
	public String changeToJSonObj(){
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
			taskJSonObj.put(Constants.TASKKEYS[6], task.getTaskType()+"");
			taskJSonObj.put(Constants.TASKKEYS[7], task.getTaskNote());
			tasks.add(taskJSonObj);
			
			
		}
		return tasks.toJSONString();
	}
	
}
