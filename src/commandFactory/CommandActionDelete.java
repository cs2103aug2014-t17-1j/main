package commandFactory;

import java.util.ArrayList;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.SummaryReport;
import taskDo.Task;


public class CommandActionDelete implements CommandAction{
	@Override
	public void execute(){
		StorageList storageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = storageListInstance.getTaskList();

//		for(Task task: taskList){
//			if(ParsedResult.getTaskDetails().getId() == task.getId()){
//				taskList.remove(task);
//				break;
//			}
//		}
		
		Search search = new Search();
		search.setId(ParsedResult.getTaskDetails().getId());
		search.searchById();
		if(search.getTaskIndex() != -1){
			taskList.remove(search.getTaskIndex());
			System.out.println(taskList);
		}
	}
	
	@Override
	public void undo(){
		// NEED IMPLEMENT A STACK CALLED HISTORY AS A DUSTBIN
		System.out.println("undo delete  <-- CommandActionDelete.java");
	}
	@Override
	public void updateSummaryReport(){
		Search search = new Search();
		search.setDueDate(ParsedResult.getTaskDetails().getDueDate());
		search.searchDueDate();
		
		SummaryReport.setHeader(ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString());
		SummaryReport.setFeedBackMsg("Deleted successfully");	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
