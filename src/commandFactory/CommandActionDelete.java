package commandFactory;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.SummaryReport;

public class CommandActionDelete implements CommandAction{
	private static final String MESSAGE_SOMEDAY = "Someday";
	private static final String MESSAGE_SUCCESS_DELETE = "Deleted successfully";
	
	@Override
	public void execute(){		
		Search search = new Search();
		search.searchById(ParsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != -1){
			StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
		}
	}
	
	@Override
	public void undo(){
		// NEED IMPLEMENT A STACK CALLED HISTORY AS A DUSTBIN
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
		SummaryReport.setFeedBackMsg(MESSAGE_SUCCESS_DELETE);	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
