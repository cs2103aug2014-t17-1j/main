package commandFactory;

import java.util.ArrayList;
import org.joda.time.DateTime;
import taskDo.SearchType;
import taskDo.StorageList;
import taskDo.Task;

public class Search {
	int id;
	Task returnTask;
	ArrayList<Task> returnList;
	DateTime dueDate;
	SearchType searchType;
	
	StorageList storageListInstance = StorageList.getInstance();
	ArrayList<Task> taskList = storageListInstance.getTaskList();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Task getReturnTask() {
		return returnTask;
	}

	public void setReturnTask(Task returnTask) {
		this.returnTask = returnTask;
	}
	
	public ArrayList<Task> getReturnList() {
		return returnList;
	}

	public void setReturnList(ArrayList<Task> returnList) {
		this.returnList = returnList;
	}
	
	public DateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}
	
	public Search (){
		switch(searchType){
		case ID:
			returnTask = searchById();	// ID referring to taskID not ScreenID
			break;
		case KEYWORD:
//			searchByKeyword();
			break;
		case CATEGORY:
//			searchByCategory();
			break;
		case DATE:
			returnList = searchDueDate();
			break;
		default: break;
		}
	}

	public Task searchById(){
		for(Task task: taskList){
			if(id == task.getId()){
				return task;
			}
		}
		return null;
	}
	
	public ArrayList<Task> searchDueDate() {	
		ArrayList<Task> returnList = new ArrayList<Task>();
		
		for(Task task: taskList){
			if(task.getDueDate().toLocalDate().equals(dueDate.toLocalDate())){
				returnList.add(task);
			}
		}
		return returnList;
	}
}
