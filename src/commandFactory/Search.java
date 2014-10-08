package commandFactory;

import java.util.ArrayList;
import org.joda.time.DateTime;
import taskDo.SearchType;
import taskDo.StorageList;
import taskDo.Task;

public class Search {
	int taskIndex;
	ArrayList<Task> returnList;
	SearchType searchType;
	
	StorageList storageListInstance = StorageList.getInstance();
	ArrayList<Task> taskList = storageListInstance.getTaskList();
	
	public int getTaskIndex() {
		return taskIndex;
	}
	
	public ArrayList<Task> getReturnList() {
		return returnList;
	}
	
	public Search(){
		taskIndex = -1;
		returnList = new ArrayList<Task>();
	}
	
//	public void identifySearchTyep (){
//		switch(searchType){
//		case ID:
//			searchById(null);	// ID referring to taskID not ScreenID
//			break;
//		case KEYWORD:
////			searchByKeyword();
//			break;
//		case CATEGORY:
////			searchByCategory();
//			break;
//		case DATE:
//			searchDueDate(null);
//			break;
//		default: break;
//		}
//	}

	public void searchById(int id){
		
		for(Task task: taskList){
			if(id == task.getId()){
				taskIndex = taskList.indexOf(task);
				break;
			}
		}
	}
	
	public void searchDueDate(DateTime dueDate) {
		
		for(Task task: taskList){
			if(task.getDueDate().toLocalDate().equals(dueDate.toLocalDate())){
				returnList.add(task);
			}
		}
	}
}
