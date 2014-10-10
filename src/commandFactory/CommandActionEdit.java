package commandFactory;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.StringConstants;
import taskDo.SummaryReport;

public class CommandActionEdit implements CommandAction {	
	@Override
	public void execute(){
		Search search = new Search();
		search.searchById(ParsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != StringConstants.NO_TASK){
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
		
		if(isSomeday()) {
			SummaryReport.setHeader(StringConstants.MESSAGE_SOMEDAY);
		} else {
			DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
			String dateDisplay = dateFormat.print(ParsedResult.getTaskDetails().getDueDate().toLocalDate());
			SummaryReport.setHeader(dateDisplay);
		}
		SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_SUCCESS_EDIT);	
		SummaryReport.setDisplayList(search.getReturnList());
	}
	
	private boolean isSomeday() {
		return ParsedResult.getTaskDetails().getDueDate().toLocalDate().getYear() == StringConstants.NILL_YEAR;
	}
}
