package taskDo;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import commandFactory.CommandType;
import commandFactory.Search;
import commonClasses.StringConstants;
import commonClasses.SummaryReport;

public class UpdateSummaryReport {

	
	public static void update(CommandType commandType){
		Search search = new Search();
		search.searchDueDate(ParsedResult.getTaskDetails().getDueDate());
		
		switch(commandType){
		case ADD:
			updateHeader();
			updateFeedbackMsg(StringConstants.MESSAGE_SUCCESS_ADD);	
			updateDisplayTaskList(search);
			break;
		case DELETE:
			updateHeader();
			updateFeedbackMsg(StringConstants.MESSAGE_SUCCESS_DELETE);	
			updateDisplayTaskList(search);
		case EDIT:
			updateHeader();
			updateFeedbackMsg(StringConstants.MESSAGE_SUCCESS_EDIT);	
			updateDisplayTaskList(search);
		case DISPLAY:
			updateHeader();
			updateFeedbackMsg(StringConstants.MESSAGE_DISPLAY);	
			updateDisplayTaskList(search);
		default:
			break;
		}
		



	}

	private static void updateDisplayTaskList(Search search) {
		SummaryReport.setDisplayList(search.getReturnList());
	}

	private static void updateFeedbackMsg(String feedbackMsg) {
		SummaryReport.setFeedBackMsg(feedbackMsg);
	}

	private static void updateHeader() {
		if(isSomeday()) {
			SummaryReport.setHeader(StringConstants.MESSAGE_SOMEDAY);
		} else {
			DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
			String dateDisplay = dateFormat.print(ParsedResult.getTaskDetails().getDueDate().toLocalDate());
			
			SummaryReport.setHeader(dateDisplay);
		}
	}

	private static boolean isSomeday() {
		return ParsedResult.getTaskDetails().getDueDate().toLocalDate().getYear() == StringConstants.NILL_YEAR;
	}
}

