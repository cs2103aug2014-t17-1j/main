package taskDo;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import Parser.ParsedResult;
import commandFactory.CommandType;
import commandFactory.Search;

public class UpdateSummaryReport {

	
	public static void update(CommandType commandType, ParsedResult parsedResult){
		Search search = new Search();
		search.searchDueDate(parsedResult.getTaskDetails().getDueDate());
		
		updateHeader(parsedResult);
		updateDisplayTaskList(search);
		
		switch(commandType){
		case ADD:
			updateFeedbackMsg(StringConstants.MESSAGE_SUCCESS_ADD);	
			break;
		case DELETE:
			updateFeedbackMsg(StringConstants.MESSAGE_SUCCESS_DELETE);
		case EDIT:
			updateFeedbackMsg(StringConstants.MESSAGE_SUCCESS_EDIT);
		case DISPLAY:
			updateFeedbackMsg(StringConstants.MESSAGE_DISPLAY);
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

	private static void updateHeader(ParsedResult parsedResult) {
		if(isSomeday(parsedResult)) {
			SummaryReport.setHeader(StringConstants.MESSAGE_SOMEDAY);
		} else {
			DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
			String dateDisplay = dateFormat.print(parsedResult.getTaskDetails().getDueDate().toLocalDate());
			
			SummaryReport.setHeader(dateDisplay);
		}
	}

	private static boolean isSomeday(ParsedResult parsedResult) {
		return parsedResult.getTaskDetails().getDueDate().toLocalDate().getYear() == StringConstants.NILL_YEAR;
	}
}

