package taskDo;

import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import parser.ParsedResult;
import commandFactory.CommandType;
import commandFactory.Search;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class UpdateSummaryReport {

	private static UpdateSummaryReport updateSR;
	
	public static UpdateSummaryReport getInstance(){
		if (updateSR == null){
			updateSR = new UpdateSummaryReport();
		}
		return updateSR;
	}
	
	public void init(){
		ArrayList<Task> displayList = new ArrayList<Task>();
		Search search = new Search();
		displayList = search.searchByOverdueAndToday();
		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
	}

	public void updateByDueDate(ParsedResult parsedResult){
		Search search = new Search();
		updateDisplayTaskList(search.searchByDate(parsedResult));
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult);
	}

	public void updateForEdit(ParsedResult parsedResult, ArrayList<Task> displayList){
		updateDisplayTaskList(displayList);
		determineFeedbackMsg(parsedResult);
	}

	public void updateForDeleteAndComplete(ParsedResult parsedResult, ArrayList<Task> displayList){
		displayList.remove(parsedResult.getTaskDetails());
		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult);
	}

	public void updateForUndoDeleteAndComplete(ParsedResult parsedResult, ArrayList<Task> displayList){
		displayList.add(parsedResult.getTaskDetails());
		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult);
	}

	public void updateForDisplay(ParsedResult parsedResult, ArrayList<Task> displayList){

		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		determineFeedbackMsg(parsedResult);
	}

	public void updateForSearch(ParsedResult parsedResult, ArrayList<Task> displayList){
		updateDisplayTaskList(displayList);
		SummaryReport.sortByDueDate();
		
		if(displayList.isEmpty()){
			updateFeedbackMsg(Constants.MESSAGE_FAIL_SEARCH);
		}else{
			determineFeedbackMsg(parsedResult);
		}
	}

	private void updateDisplayTaskList(ArrayList<Task> displayList) {
		SummaryReport.setDisplayList(displayList);
	}

	private void determineFeedbackMsg(ParsedResult parsedResult) {
		String searchInput = parsedResult.getTaskDetails().getTitle();
		String feedbackMsg = new String();
		String command = new String();

		int taskID = parsedResult.getSelectedItem()+1;

		CommandType commandType = parsedResult.getCommandType();

		switch(commandType){
		case ADD:
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_ADD);
			break;

		case DELETE:
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_DELETE, taskID);
			break;

		case EDIT:
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_EDIT, taskID);
			break;

		case DISPLAY:
			feedbackMsg = determineDisplayContent(parsedResult);
			break;

		case UNDO:
			command = History.getInstance().getUndoCommandHistory().pop().toString();
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_UNDO, command);
			break;

		case REDO:
			command = History.getInstance().getRedoCommandHistory().pop().toString();
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_REDO, command);
			break;

		case COMPLETED:
			feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_COMPLETED, taskID);
			break;

		case SEARCH:
			if(SummaryReport.getDisplayList().size()==1){
				feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_SEARCH_SINGLE, searchInput);
			}else{
				feedbackMsg = String.format(Constants.MESSAGE_SUCCESS_SEARCH_MUL, searchInput);
			}
			break;

		default:break;
		}
		updateFeedbackMsg(feedbackMsg);
	}

	private String determineDisplayContent(ParsedResult parsedResult) {
		String feedbackMsg = new String();
		DateTimeFormatter fmt = DateTimeFormat.forPattern("dd/MM/YYYY");

		switch(parsedResult.getSearchMode()){
		case ALL:
			feedbackMsg = Constants.MESSAGE_DISPLAY_ALL;
			break;

		case DATE: 
			DateTime displayDate = parsedResult.getTaskDetails().getDueDate();
			String date = fmt.print(displayDate);
			feedbackMsg = String.format(Constants.MESSAGE_DISPLAY_DATE, date);
			break;

		case RANGEOFDATES:
			DateTime strDate = parsedResult.getTaskDetails().getStartDate();
			String start = fmt.print(strDate);

			DateTime endDate = parsedResult.getTaskDetails().getDueDate();
			String end = fmt.print(endDate);

			feedbackMsg = String.format(Constants.MESSAGE_DISPLAY_RANGE, start, end);
			break;

		case COMPLETED:
			feedbackMsg = Constants.MESSAGE_DISPLAY_COMPLETED;
			break;

		case CATEGORY:
			String category = parsedResult.getTaskDetails().getCategory().toString();
			feedbackMsg = String.format(Constants.MESSAGE_DISPLAY_CATEGORY, category);
			break;

		case OVERDUE:
			feedbackMsg = Constants.MESSAGE_DISPLAY_OVERDUE;
			break;

		default:break;
		}
		return feedbackMsg;
	}

	private void updateFeedbackMsg(String feedbackMsg) {
		SummaryReport.setFeedBackMsg(feedbackMsg);
	}

	private int searchIndex(int taskID){
		Search search = new Search();
		return search.searchById(taskID, SummaryReport.getDisplayList());
	}

	public void highlightTask(int taskID){
		int highlightTask = searchIndex(taskID); 
		SummaryReport.setRowIndexHighlight(highlightTask);
	}

	public void unhighlightTask(){
		SummaryReport.setRowIndexHighlight(-1);
	}
}

