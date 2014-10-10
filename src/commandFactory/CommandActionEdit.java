package commandFactory;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.SummaryReport;

public class CommandActionEdit implements CommandAction {
	private static final String MESSAGE_SOMEDAY = "Someday";
	private static final String MESSAGE_SUCCESS_EDIT = "Edited successfully";
	
	@Override
	public void execute(){
		Search search = new Search();
		search.searchById(ParsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != -1){
			StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
		}
		
		StorageList.getInstance().getTaskList().add(ParsedResult.getTaskDetails());
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
		SummaryReport.setFeedBackMsg(MESSAGE_SUCCESS_EDIT);	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
