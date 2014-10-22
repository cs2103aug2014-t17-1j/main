package taskDo;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import commandFactory.CommandType;
import commandFactory.Search;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class UpdateSummaryReport {

	
	public static void update(CommandType commandType, DateTime dueDate){
		Search search = new Search();
		search.searchDueDate(dueDate);
		
		updateHeader(dueDate);
		updateDisplayTaskList(search);
		determineFeedbackMsg(commandType);
	}

	private static void updateHeader(DateTime dueDate) {
		if(isSomeday(dueDate)) {
			SummaryReport.setHeader(Constants.MESSAGE_SOMEDAY);
		} else {
			assert dueDate != null;
			DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
			String dateDisplay = dateFormat.print(dueDate.toLocalDate());
			SummaryReport.setHeader(dateDisplay);
		}
	}

	private static boolean isSomeday(DateTime dueDate) {
		assert dueDate != null;
		return dueDate.toLocalDate().getYear() == Constants.NILL_YEAR;
	}

	private static void updateDisplayTaskList(Search search) {
		SummaryReport.setDisplayList(search.getReturnList());
	}

	private static void determineFeedbackMsg(CommandType commandType) {
		switch(commandType){
		case ADD:
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_ADD);	
			break;
		case DELETE:
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_DELETE);
			break;
		case EDIT:
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_EDIT);
			break;
		case DISPLAY:
			updateFeedbackMsg(Constants.MESSAGE_DISPLAY);
			break;
		case UNDO:
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_UNDO);
			break;
		default:
			break;
		}
	}

	private static void updateFeedbackMsg(String feedbackMsg) {
		SummaryReport.setFeedBackMsg(feedbackMsg);
	}
}

