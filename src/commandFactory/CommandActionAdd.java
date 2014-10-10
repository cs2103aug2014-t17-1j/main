package commandFactory;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.StringConstants;
import taskDo.SummaryReport;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(){
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
			SummaryReport.setHeader(StringConstants.MESSAGE_SOMEDAY);
		} else {
			SummaryReport.setHeader(ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString());
		}
		SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_SUCCESS_ADD);	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
