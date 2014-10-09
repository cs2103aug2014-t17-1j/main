package readAndWriteFile;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import taskDo.Task;
/*
 * @author Paing Zin Oo(Jack)
 */
public class ConvertToJSonObject {
	private ArrayList<Task> taskList;

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}
	
	@SuppressWarnings("unchecked")
	public String changeToJSonObj(){
		String taskListResult = "";
		for (int i = 0 ; i < taskList.size(); i++){
			Task task = taskList.get(i);
			JSONObject taskJSonObj = new JSONObject();
			taskJSonObj.put("category", task.getCategory());
			taskJSonObj.put("description", task.getDescription());
			taskJSonObj.put("important", task.isImportant());
			if(task.getDueDate() == null){
				taskJSonObj.put("dueDate", "");
				
			}else{
				taskJSonObj.put("dueDate", task.getDueDate().toString());
			}
			if(task.getStartDate() == null){
				taskJSonObj.put("startDate", "");
			}else{
				taskJSonObj.put("startDate", task.getStartDate().toString());
			}
			taskJSonObj.put("completed", task.isCompleted());
			taskListResult += taskJSonObj.toString();
			
		}
		return taskListResult;
	}
	
}
