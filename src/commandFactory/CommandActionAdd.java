package commandFactory;

import java.util.ArrayList;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.SummaryReport;
import taskDo.Task;


public class CommandActionAdd implements CommandAction{
	
	private static final String MESSAGE_SOMEDAY = "Someday";
	private static final String MESSAGE_SUCCESS_ADD = "Added successfully";

	
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
		search.searchDueDate(ParsedResult.getTaskDetails().getDueDate());
		
		if(ParsedResult.getTaskDetails().getDueDate().toLocalDate().getYear() == 0) {
			SummaryReport.setHeader(MESSAGE_SOMEDAY);
		} else {
			SummaryReport.setHeader(ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString());
		}
		SummaryReport.setFeedBackMsg(MESSAGE_SUCCESS_ADD);	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
