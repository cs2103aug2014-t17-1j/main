package commandFactory;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import taskDo.ParsedResult;
import taskDo.StringConstants;
import taskDo.SummaryReport;

public class CommandActionDisplay implements CommandAction{	
	@Override
	public void execute(){}
	
	@Override
	public void updateSummaryReport(){
		Search search = new Search();
		search.searchDueDate(ParsedResult.getTaskDetails().getDueDate());
		
		if(isSomeday()) {
			SummaryReport.setHeader(StringConstants.MESSAGE_SOMEDAY);
		} else {
			DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
			String dateDisplay = dateFormat.print(ParsedResult.getTaskDetails().getDueDate().toLocalDate());
			SummaryReport.setHeader(dateDisplay);
		}
		SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_DISPLAY);	
		SummaryReport.setDisplayList(search.getReturnList());
	}
	
	private boolean isSomeday() {
		return ParsedResult.getTaskDetails().getDueDate().toLocalDate().getYear() == StringConstants.NILL_YEAR;
	}
}
