package commandFactory;

import taskDo.ParsedResult;
import taskDo.StringConstants;
import taskDo.SummaryReport;

public class CommandActionDisplay implements CommandAction{	
	@Override
	public void execute(){}
	
	@Override
	public void undo(){}
	
	@Override
	public void updateSummaryReport(){
		Search search = new Search();
		search.searchDueDate(ParsedResult.getTaskDetails().getDueDate());
		
		if(ParsedResult.getTaskDetails().getDueDate().toLocalDate().getYear() == 0) {
			SummaryReport.setHeader(StringConstants.MESSAGE_SOMEDAY);
		} else {
			SummaryReport.setHeader(ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString());
		}
		SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_DISPLAY);	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
