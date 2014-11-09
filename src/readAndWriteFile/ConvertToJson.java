package readAndWriteFile;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import taskDo.Task;

import commonClasses.Constants;

/* Convert the tasks into String 
 *
 */
public class ConvertToJson {
	//@author Paing Zin Oo(Jack)  A0112581N
	private ArrayList<Task> taskList;

	public ArrayList<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}

	public String changeToJsonObj(boolean isTask) {
		String result = Constants.STRING_STRING;
		try {
			result = extractTaskFields();
		} catch (Exception e) {

		}

		return result;
	}

	private String extractTaskFields() throws JSONException {
		JSONArray tasks = new JSONArray();
		for (int i = 0; i < taskList.size(); i++) {
			Task task = taskList.get(i);
			JSONObject taskJsonObj = new JSONObject();
			taskJsonObj.put(Constants.TASKKEYS[0], task.getTitle());
			if (task.getCategory() == null) {
				taskJsonObj.put(Constants.TASKKEYS[1], "");
			} else {
				taskJsonObj.put(Constants.TASKKEYS[1], task.getCategory());
			}
			taskJsonObj.put(Constants.TASKKEYS[2], task.isImportant());
			if (task.getStartDate() == null) {
				taskJsonObj.put(Constants.TASKKEYS[3], "");
			} else {
				taskJsonObj.put(Constants.TASKKEYS[3], task.getStartDate()
						.toString());
			}
			if (task.getDueDate() == null) {
				taskJsonObj.put(Constants.TASKKEYS[4], "");

			} else {
				taskJsonObj.put(Constants.TASKKEYS[4], task.getDueDate()
						.toString());
			}
			taskJsonObj.put(Constants.TASKKEYS[5], task.isCompleted());
			taskJsonObj.put(Constants.TASKKEYS[6], task.getTaskType() + "");
			if (task.getNote() == null) {
				taskJsonObj.put(Constants.TASKKEYS[7], "");
			} else {
				taskJsonObj.put(Constants.TASKKEYS[7], task.getNote());
			}

			tasks.put(taskJsonObj);

		}
		return tasks.toString(Constants.JSON_IDENTATION);
	}

}
