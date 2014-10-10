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
		
		for(Task task: StorageList.getInstance().getTaskList()){
			if(id == task.getId()){
				taskIndex = StorageList.getInstance().getTaskList().indexOf(task);
				break;
			}
		}
	}
	
	public void searchDueDate(DateTime dueDate) {
		
		for(Task task: StorageList.getInstance().getTaskList()){
			if(task.getDueDate().toLocalDate().equals(dueDate.toLocalDate())){
				returnList.add(task);
			}
		}
	}
}
