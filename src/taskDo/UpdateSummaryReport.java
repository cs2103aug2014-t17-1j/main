package taskDo;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import Parser.ParsedResult;
import commandFactory.CommandType;
import commandFactory.Search;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class UpdateSummaryReport {

	
	public static void update(CommandType commandType, ParsedResult parsedResult){
		Search search = new Search();
		assert parsedResult.getTaskDetails().getDueDate() == null;
		search.searchDueDate(parsedResult.getTaskDetails().getDueDate());
		
		updateHeader(parsedResult);
		updateDisplayTaskList(search);
		
		switch(commandType){
		case ADD:
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_ADD);	
			break;
		case DELETE:
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_DELETE);
		case EDIT:
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_EDIT);
		case DISPLAY:
			updateFeedbackMsg(Constants.MESSAGE_DISPLAY);
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
			SummaryReport.setHeader(Constants.MESSAGE_SOMEDAY);
		} else {
			DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
			
			assert parsedResult.getTaskDetails().getDueDate() != null;
			
			String dateDisplay = dateFormat.print(parsedResult.getTaskDetails().getDueDate().toLocalDate());
			
			SummaryReport.setHeader(dateDisplay);
		}
	}

	private static boolean isSomeday(ParsedResult parsedResult) {
		assert parsedResult.getTaskDetails().getDueDate() != null;
		return parsedResult.getTaskDetails().getDueDate().toLocalDate().getYear() == Constants.NILL_YEAR;
	}
}

