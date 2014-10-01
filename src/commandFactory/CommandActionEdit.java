package commandFactory;

import java.util.ArrayList;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.SummaryReport;
import taskDo.Task;

public class CommandActionEdit implements CommandAction {
	@Override
	public void execute(){
		StorageList storageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = storageListInstance.getTaskList();
		
		Search search = new Search();
		search.setId(ParsedResult.getTaskDetails().getId());
		search.searchById();
		if(search.getTaskIndex() != -1){
			taskList.remove(search.getTaskIndex());
		}
		
		taskList.add(ParsedResult.getTaskDetails());
	}
	@Override
	public void undo(){
		System.out.println("undo edit <-- CommandActionEdit.java");
	}
	@Override
	public void updateSummaryReport(){
		Search search = new Search();
		search.setDueDate(ParsedResult.getTaskDetails().getDueDate());
		search.searchDueDate();
		
		SummaryReport.setHeader(ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString());
		SummaryReport.setFeedBackMsg("Edited successfully");	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
