package commandFactory;

import taskDo.ParsedResult;
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
		
		SummaryReport.setHeader(ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString());
		SummaryReport.setFeedBackMsg("Display by request");	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
