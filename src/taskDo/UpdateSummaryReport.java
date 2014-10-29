package taskDo;

import java.util.ArrayList;

import Parser.ParsedResult;
import commandFactory.CommandType;
import commandFactory.Search;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class UpdateSummaryReport {

	public static void init(){
		ArrayList<Task> displayList = new ArrayList<Task>();
		Search search = new Search();
		displayList = search.searchOverdueAndTodayTasks();
		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
	}
	
	public static void update(ParsedResult parsedResult){
		Search search = new Search();

		updateDisplayTaskList(search.searchByDate(parsedResult));
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult.getCommandType());
	}
	
	public static void updateForDeleteAndComplete(ParsedResult parsedResult, ArrayList<Task> displayList){
		displayList.remove(parsedResult.getTaskDetails());
		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult.getCommandType());
	}
	
	public static void updateForUndoDeleteAndComplete(ParsedResult parsedResult, ArrayList<Task> displayList){
		displayList.add(parsedResult.getTaskDetails());
		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult.getCommandType());
	}
	
	public static void updateForDisplay(ParsedResult parsedResult, ArrayList<Task> displayList){

		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult.getCommandType());
	}
	
	public static void updateForSearch(ParsedResult parsedResult, ArrayList<Task> displayList){

		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		if(displayList.isEmpty()){
			updateFeedbackMsg(Constants.MESSAGE_FAIL_SEARCH);
		}else{updateFeedbackMsg(Constants.MESSAGE_SUCCESS_SEARCH);}
	}

	private static void updateDisplayTaskList(ArrayList<Task> displayList) {
		SummaryReport.setDisplayList(displayList);
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
		case REDO:
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_REDO);
			break;
		case COMPLETED:
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_COMPLETED);
			break;
		case SEARCH:
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_SEARCH);
			break;
		default:
			break;
		}
	}

	private static void updateFeedbackMsg(String feedbackMsg) {
		SummaryReport.setFeedBackMsg(feedbackMsg);
	}
}

