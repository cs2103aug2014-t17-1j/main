package commandFactory;

import java.util.ArrayList;
import org.joda.time.DateTime;
import taskDo.SearchType;
import taskDo.StorageList;
import taskDo.Task;

public class Search {
	int id;
	int taskIndex;
	ArrayList<Task> returnList;
	DateTime dueDate;
	SearchType searchType;
	
	StorageList storageListInstance = StorageList.getInstance();
	ArrayList<Task> taskList = storageListInstance.getTaskList();
	
//	public int getId() {
//		return id;
//	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getTaskIndex() {
		return taskIndex;
	}

//	public void setTaskIndex(int taskIndex) {
//		this.taskIndex = taskIndex;
//	}
	
	public ArrayList<Task> getReturnList() {
		return returnList;
	}

//	public void setReturnList(ArrayList<Task> returnList) {
//		this.returnList = returnList;
//	}
	
//	public DateTime getDueDate() {
//		return dueDate;
//	}

	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}
	
	public Search(){
		taskIndex = -1;
		returnList = new ArrayList<Task>();
	}
	
	public void identifySearchTyep (){
		switch(searchType){
		case ID:
			searchById();	// ID referring to taskID not ScreenID
			break;
		case KEYWORD:
//			searchByKeyword();
			break;
		case CATEGORY:
//			searchByCategory();
			break;
		case DATE:
			searchDueDate();
			break;
		default: break;
		}
	}

	public void searchById(){
		
		for(Task task: taskList){
			if(id == task.getId()){
				taskIndex = taskList.indexOf(task);
				break;
			}
		}
	}
	
	public void searchDueDate() {
		
		for(Task task: taskList){
			if(task.getDueDate().toLocalDate().equals(dueDate.toLocalDate())){
				returnList.add(task);
			}
		}
	}
}
