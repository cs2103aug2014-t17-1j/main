package readAndWriteFile;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

	public void setCategoryList(ArrayList<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public void setTaskList(ArrayList<Task> taskList) {
		this.taskList = taskList;
	}

	public String changeToJsonObj(boolean isTask) {
		String result = "";
		try {
			result = extractTaskFields();
		} catch (Exception e) {

		}

		return result;
	}

	/*
	 * private String extractCategoryFields() { JSONArray categories = new
	 * JSONArray(); for (int i = 0 ; i < categoryList.size(); i++){ Category
	 * category = categoryList.get(i); JSONObject categoryJSonObj = new
	 * JSONObject(); // categoryJSonObj.put(Constants.CATEGORYKEYS[0],
	 * category.getName()); // categoryJSonObj.put(Constants.CATEGORYKEYS[1],
	 * category.getCount()); categories.put(categoryJSonObj);
	 * 
	 * 
	 * } return categories.toString(); }
	 */

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
