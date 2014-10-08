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
		
		Search search = new Search();
		search.searchById(ParsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != -1){
			taskList.remove(search.getTaskIndex());
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
		search.searchDueDate(ParsedResult.getTaskDetails().getDueDate());
		
		SummaryReport.setHeader(ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString());
		SummaryReport.setFeedBackMsg("Deleted successfully");	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
