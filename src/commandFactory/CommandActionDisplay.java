package commandFactory;

import taskDo.ParsedResult;
import taskDo.SummaryReport;



public class CommandActionDisplay implements CommandAction{
	private static final String MESSAGE_SOMEDAY = "Someday";
	private static final String MESSAGE_DISPLAY = "Display by request";
	
	@Override
	public void execute(){}
	
	@Override
	public void undo(){}
	
	@Override
	public void updateSummaryReport(){
		Search search = new Search();
		search.searchDueDate(ParsedResult.getTaskDetails().getDueDate());
		
		if(ParsedResult.getTaskDetails().getDueDate().toLocalDate().getYear() == 0) {
			SummaryReport.setHeader(MESSAGE_SOMEDAY);
		} else {
			SummaryReport.setHeader(ParsedResult.getTaskDetails().getDueDate().toLocalDate().toString());
		}
		SummaryReport.setFeedBackMsg(MESSAGE_DISPLAY);	
		SummaryReport.setDisplayList(search.getReturnList());
	}
}
