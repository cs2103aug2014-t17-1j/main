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
		displayList = search.searchByOverdueAndToday();
		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
	}

	public static void updateByDueDate(ParsedResult parsedResult){
		Search search = new Search();
		updateDisplayTaskList(search.searchByDate(parsedResult));
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult);
	}

	public static void updateForEdit(ParsedResult parsedResult, ArrayList<Task> displayList){
		updateDisplayTaskList(displayList);
		determineFeedbackMsg(parsedResult);
	}

	public static void updateForDeleteAndComplete(ParsedResult parsedResult, ArrayList<Task> displayList){
		displayList.remove(parsedResult.getTaskDetails());
		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult);
	}

	public static void updateForUndoDeleteAndComplete(ParsedResult parsedResult, ArrayList<Task> displayList){
		displayList.add(parsedResult.getTaskDetails());
		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult);
	}

	public static void updateForDisplay(ParsedResult parsedResult, ArrayList<Task> displayList){

		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult);
	}

	public static void updateForSearch(ParsedResult parsedResult, ArrayList<Task> displayList){

		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		if(displayList.isEmpty()){
			updateFeedbackMsg(Constants.MESSAGE_FAIL_SEARCH);
		}else{determineFeedbackMsg(parsedResult);}
	}

	private static void updateDisplayTaskList(ArrayList<Task> displayList) {
		SummaryReport.setDisplayList(displayList);
	}

	private static void determineFeedbackMsg(ParsedResult parsedResult) {
		String feedbackMsg;
//		int taskID = searchIndex(parsedResult.getTaskDetails().getId());
		CommandType commandType = parsedResult.getCommandType();
		switch(commandType){
		case ADD:
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_ADD);
			updateFeedbackMsg(feedbackMsg);	
			break;
		case DELETE:
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_DELETE);
			updateFeedbackMsg(feedbackMsg);
			break;
		case EDIT:
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_EDIT);
			updateFeedbackMsg(feedbackMsg);
			break;
		case DISPLAY:
			updateFeedbackMsg(Constants.MESSAGE_DISPLAY);
			break;
		case UNDO:
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_UNDO);
			updateFeedbackMsg(feedbackMsg);
			break;
		case REDO:
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_REDO);
			updateFeedbackMsg(feedbackMsg);
			break;
		case COMPLETED:
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_COMPLETED);
			updateFeedbackMsg(feedbackMsg);
			break;
		case SEARCH:
//			if(SummaryReport.getDisplayList().size()==1){
//				feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_SEARCH_SINGLE, taskID);
//			}else{
//				feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_SEARCH_MUL, taskID);
//			}
//			updateFeedbackMsg(feedbackMsg);
			updateFeedbackMsg(Constants.MESSAGE_SUCCESS_SEARCH);

			break;
		default:
			break;
		}
	}

	private static void updateFeedbackMsg(String feedbackMsg) {
		SummaryReport.setFeedBackMsg(feedbackMsg);
	}

	private static int searchIndex(int taskID){
		Search search = new Search();
		return search.searchById(taskID, SummaryReport.getDisplayList());
	}
	
	public static void highlightTask(int taskID){
		int highlightTask = searchIndex(taskID); 
		SummaryReport.setRowIndexHighlight(highlightTask);
	}
	
	public static void unhighlightTask(){
		SummaryReport.setRowIndexHighlight(-1);
	}
}

