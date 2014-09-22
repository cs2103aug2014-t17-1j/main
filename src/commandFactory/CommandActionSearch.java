package commandFactory;

import java.util.ArrayList;
import java.util.Iterator;

import taskDo.ParsedResult;
import taskDo.SearchType;
import taskDo.Task;
import taskDo.TaskList;

public class CommandActionSearch implements CommandAction {
	@Override
	public void execute(){
		switch(ParsedResult.getSearchMode()){
		case ID:
			searchByID();	// ID referring to taskID not ScreenID
			break;
		case KEYWORD:
			searchByKeyword();
			break;
		case CATEGORY:
			searchByCategory();
			break;
		case DATE:
			searchByDate();
			break;
		default: break;
		}
		System.out.println("added successfully <-- print from CommandActionSearch.java");
	}

	@Override
	public void undo(){
		System.out.println("CANNOT undo search <-- CommandActionSearch.java");
	}
	
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- add <-- print from CommandActionSearch.java");
	}
	
	private void searchByID() {
		ArrayList<Task> taskList = TaskList.getTaskList();

		Iterator<Task> task = taskList.iterator();
		while(task.hasNext()){
			int taskListItemID = (Integer)task.next().getId();
			int targetTaskID = ParsedResult.getTaskDetails().getId();
//			if(taskListItemID.equals(targetTaskID){
//			}
		}
	}
	
	private void searchByKeyword() {
		// TODO Auto-generated method stub
	}

	private void searchByCategory() {
		// TODO Auto-generated method stub
	}
	
	private void searchByDate() {
		// TODO Auto-generated method stub
	}
}
