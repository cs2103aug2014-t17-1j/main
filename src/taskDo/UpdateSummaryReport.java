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
		assert parsedResult.getTaskDetails().getDueDate() != null;
		search.searchDueDate(parsedResult.getTaskDetails().getDueDate());
		
		updateHeader(parsedResult);
		updateDisplayTaskList(search);
		
		switch(commandType){
		case ADD:
<<<<<<< HEAD
			updateFeedbackMsg(StringConstants.MESSAGE_SUCCESS_ADD);	
			break;
		case DELETE:
			updateFeedbackMsg(StringConstants.MESSAGE_SUCCESS_DELETE);
		case EDIT:
			updateFeedbackMsg(StringConstants.MESSAGE_SUCCESS_EDIT);
		case DISPLAY:
			updateFeedbackMsg(StringConstants.MESSAGE_DISPLAY);
=======
			updateHeader();
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_ADD);	
			updateDisplayTaskList(search);
			break;
		case DELETE:
			updateHeader();
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_DELETE);	
			updateDisplayTaskList(search);
		case EDIT:
			updateHeader();
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_EDIT);	
			updateDisplayTaskList(search);
		case DISPLAY:
			updateHeader();
			updateFeedbackMsg(Constants.MESSAGE_DISPLAY);	
			updateDisplayTaskList(search);
>>>>>>> 25d04164930ae234af8d9c664a639510378e21ca
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

<<<<<<< HEAD
	private static void updateHeader(ParsedResult parsedResult) {
		if(isSomeday(parsedResult)) {
			SummaryReport.setHeader(StringConstants.MESSAGE_SOMEDAY);
=======
	private static void updateHeader() {
		if(isSomeday()) {
			SummaryReport.setHeader(Constants.MESSAGE_SOMEDAY);
>>>>>>> 25d04164930ae234af8d9c664a639510378e21ca
		} else {
			DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
			
			assert parsedResult.getTaskDetails().getDueDate() != null;
			
			String dateDisplay = dateFormat.print(parsedResult.getTaskDetails().getDueDate().toLocalDate());
			
			SummaryReport.setHeader(dateDisplay);
		}
	}

<<<<<<< HEAD
	private static boolean isSomeday(ParsedResult parsedResult) {
		assert parsedResult.getTaskDetails().getDueDate() != null;
		return parsedResult.getTaskDetails().getDueDate().toLocalDate().getYear() == StringConstants.NILL_YEAR;
=======
	private static boolean isSomeday() {
		return ParsedResult.getTaskDetails().getDueDate().toLocalDate().getYear() == Constants.NILL_YEAR;
>>>>>>> 25d04164930ae234af8d9c664a639510378e21ca
	}
}

