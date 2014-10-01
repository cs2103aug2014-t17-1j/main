package commandFactory;

import java.util.ArrayList;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.SummaryReport;
import taskDo.Task;


public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(){
		// get the singleton instance and get the tasklist
		StorageList strageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = strageListInstance.getTaskList();
		
		taskList.add(ParsedResult.getTaskDetails());
	}
	
	@Override
	public void undo(){

	}
	
	@Override
	public void updateSummaryReport(){
		Search search = new Search();
		search.setDueDate(ParsedResult.getTaskDetails().getDueDate());
		search.searchDueDate();
		
		SummaryReport.setHeader(ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString());
		SummaryReport.setFeedBackMsg("Added successfully");	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
