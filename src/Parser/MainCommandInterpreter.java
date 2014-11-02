package Parser;

import java.security.InvalidParameterException;

import org.joda.time.DateTime;

import taskDo.CategoryList;
import taskDo.SearchType;
import taskDo.Task;
import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class MainCommandInterpreter extends CommandInterpreter {

	// Members
	CommandType currentCommand;

	public MainCommandInterpreter() {

	}

	public CommandType getCommand() {
		return this.currentCommand;
	}

	public void identifyAndSetCommand(String command)
			throws InvalidParameterException {
		switch (command) {
		case "new":
		case "create":
		case "add":
			currentCommand = CommandType.ADD;
			break;

		case "show":
		case "view":
		case "display":
			currentCommand = CommandType.DISPLAY;
			break;

		case "del":
		case "remove":
		case "delete":
			currentCommand = CommandType.DELETE;
			break;

		case "mod":
		case "edit":
			currentCommand = CommandType.EDIT;
			break;
		case "undo":
			currentCommand = CommandType.UNDO;
			break;

		case "search":
			currentCommand = CommandType.SEARCH;
			break;

		case "tick":
		case "done":
		case "complete":
			currentCommand = CommandType.COMPLETED;
			break;

		case "redo":
			currentCommand = CommandType.REDO;
			break;

		default:
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_COMMAND);
			throw new InvalidParameterException();
		}
	}

	public String removeCommandWord(String input) {
		try {
//			switch (currentCommand) {
//			case ADD:
//				return input.substring(4); // 4 is length of word "add "
//
//			case DISPLAY:
//				return input.substring(8); // 8 is length of word "display "
//
//			case DELETE:
//				return input.substring(7); // 7 is length of word "delete "
//
//			case SEARCH:
//				return input.substring(7); // 7 is length of word "search "
//
//			case EDIT:
//				return input.substring(5);
//
//			case COMPLETED:
//				return input.substring(9);
//
//			default:
//				return "";
//		}
			return input.substring(input.indexOf(' ')+1);
		} catch (Exception e) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_MISSING_PARAM);
			throw new InvalidParameterException();
		}
	}

	public ParsedResult updateResults(ParsedResult result, String commandParam)
			throws InvalidParameterException {
		result.setCommandType(currentCommand);
		Task task = result.getTaskDetails();
		switch (currentCommand) {

		case ADD:
			task.setTitle(commandParam);
			break;

		case DELETE:
			updateForDeleteCase(result, commandParam);
			break;

		case EDIT:
			updateForEditCase(result, commandParam);
			break;

		case DISPLAY:
			updateDisplayCase(result, commandParam);
			break;

		case UNDO:
			// do nothing
			break;

		case COMPLETED:
			updateCompleteCase(result, commandParam);
			break;

		case SEARCH:
			updateSearchCase(result, commandParam);

		default:
			// do nothing

		}
		return result;
	}

	private void updateSearchCase(ParsedResult result, String commandParam) {
		result.getTaskDetails().setTitle(commandParam);
		result.setSearchMode(SearchType.KEYWORD);
	}

	private void updateCompleteCase(ParsedResult result, String commandParam) {
		if (CommonInterpreterMethods.isValidSelection(commandParam)) {
			int selection = Integer.valueOf(commandParam) - 1; // adjust to get
			// correct index
			result.setTask(SummaryReport.getDisplayList().get(selection));
			if (result.getTaskDetails().isCompleted()) {
				SummaryReport
						.setFeedBackMsg(Constants.MESSAGE_TASK_ALREADY_COMPLETED);
				throw new InvalidParameterException();
			}
		} else {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
			throw new InvalidParameterException();
		}
	}

	private void copyTaskParamToParsedResult(ParsedResult result, int selection) {
		Task selectedTask = new Task();

		selectedTask = SummaryReport.getDisplayList().get(selection);

		result.getTaskDetails().setId(selectedTask.getId());
		result.getTaskDetails().setTitle(selectedTask.getTitle());
		result.getTaskDetails().setCategory(selectedTask.getCategory());
		result.getTaskDetails().setStartDate(selectedTask.getStartDate());
		result.getTaskDetails().setDueDate(selectedTask.getDueDate());
		result.getTaskDetails().setCompleted(selectedTask.isCompleted());
		result.getTaskDetails().setImportant(selectedTask.isImportant());
		result.getTaskDetails().setTaskType(selectedTask.getTaskType());
		result.getTaskDetails().setNote(selectedTask.getNote());

	}

	private void updateDisplayCase(ParsedResult result, String commandParam) {
		Task task = result.getTaskDetails();

		if (CategoryList.isExistingCategory(commandParam)) {
			task.setCategory(commandParam);
			result.setSearchMode(SearchType.CATEGORY);
		} else if (CommonInterpreterMethods.noDeadLine(commandParam)) {
			task.setDueDate(Constants.SOMEDAY);
			task.setStartDate(null);
			result.setSearchMode(SearchType.DATE);
		} else if (commandParam.toLowerCase().equals(Constants.DISPLAY_ALL)) {
			result.setSearchMode(SearchType.ALL);
		} else if (commandParam.toLowerCase().equals(
				Constants.DISPLAY_COMPLETED)) {
			result.setSearchMode(SearchType.COMPLETED);
		} else if (commandParam.toLowerCase().equals(Constants.DISPLAY_OVERDUE)) {
			result.setSearchMode(SearchType.OVERDUE);
		} else {
			DateTime date = CommonInterpreterMethods.getDate(commandParam);
			if (date == null) {
				SummaryReport
						.setFeedBackMsg(Constants.MESSAGE_INVALID_DISPLAY_SELECTION);
				throw new InvalidParameterException();
			} else {
				task.setDueDate(date);
				result.setSearchMode(SearchType.DATE);
			}
		}
	}

	private void updateForEditCase(ParsedResult result, String commandParam) {
		if (CommonInterpreterMethods.isValidSelection(commandParam)) {
			int selection = Integer.valueOf(commandParam) - 1; // adjust to get
			// the correct
			// index in list
			copyTaskParamToParsedResult(result, selection);
		} else {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
			throw new InvalidParameterException();
		}
	}

	private void updateForDeleteCase(ParsedResult result, String commandParam) {
		if (CommonInterpreterMethods.isValidSelection(commandParam)) {
			int selection = Integer.valueOf(commandParam) - 1;
			result.setTask(SummaryReport.getDisplayList().get(selection));
		} else {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
			throw new InvalidParameterException();
		}
	}

}
