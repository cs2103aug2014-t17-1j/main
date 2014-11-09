package parser;

import java.security.InvalidParameterException;

import org.joda.time.DateTime;

import taskDo.CategoryList;
import taskDo.SearchType;
import taskDo.Task;
import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class MainCommandInterpreter extends CommandInterpreter {

	//@author Boo Tai Yi  A0111936J
	// Members
	private CommandType currentCommand;

	public CommandType getCommand() {
		return this.currentCommand;
	}

	public void identifyAndSetCommand(String command)
			throws InvalidParameterException {
		switch (command) {
		case Constants.COMMAND_STRING_NEW:
		case Constants.COMMAND_STRING_CREATE:
		case Constants.COMMAND_STRING_ADD:
			currentCommand = CommandType.ADD;
			break;

		case Constants.COMMAND_STRING_SHOW:
		case Constants.COMMAND_STRING_VIEW:
		case Constants.COMMAND_STRING_DISPLAY:
			currentCommand = CommandType.DISPLAY;
			break;

		case Constants.COMMAND_STRING_DEL:
		case Constants.COMMAND_STRING_REMOVE:
		case Constants.COMMAND_STRING_DELETE:
			currentCommand = CommandType.DELETE;
			break;

		case Constants.COMMAND_STRING_MOD:
		case Constants.COMMAND_STRING_EDIT:
			currentCommand = CommandType.EDIT;
			break;
		case Constants.COMMAND_STRING_UNDO:
			currentCommand = CommandType.UNDO;
			break;

		case Constants.COMMAND_STRING_SEARCH:
			currentCommand = CommandType.SEARCH;
			break;

		case Constants.COMMAND_STRING_TICK:
		case Constants.COMMAND_STRING_DONE:
		case Constants.COMMAND_STRING_COMPLETE:
			currentCommand = CommandType.COMPLETED;
			break;

		case Constants.COMMAND_STRING_REDO:
			currentCommand = CommandType.REDO;
			break;

		case Constants.COMMAND_STRING_QUIT:
		case Constants.COMMAND_STRING_EXIT:
			currentCommand = CommandType.EXIT;
			break;

		default:
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_COMMAND);
			throw new InvalidParameterException(
					Constants.MESSAGE_INVALID_COMMAND);
		}
	}

	public String removeCommandWord(String input) {
		if (input.indexOf(Constants.CHAR_SPACING) == -1) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_MISSING_PARAM);
			throw new InvalidParameterException(Constants.MESSAGE_MISSING_PARAM);
		}
		return input.substring(input.indexOf(Constants.CHAR_SPACING) + 1);
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
			int selection = getSelection(commandParam);
			result.setTask(SummaryReport.getDisplayList().get(selection));
			result.setSelectedItem(selection);
			if (result.getTaskDetails().isCompleted()) {
				SummaryReport
						.setFeedBackMsg(Constants.MESSAGE_TASK_ALREADY_COMPLETED);
				throw new InvalidParameterException(
						Constants.MESSAGE_TASK_ALREADY_COMPLETED);
			}
		} else {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
			throw new InvalidParameterException(
					Constants.MESSAGE_INVALID_SELECTION);
		}
	}

	private int getSelection(String commandParam) {
		return Integer.valueOf(commandParam) - 1;
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
			DateTime date;
			try {
				date = CommonInterpreterMethods.getDate(commandParam);
			} catch (Exception e) {
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_DATE_HAS_PASSED);
				throw new InvalidParameterException(
						Constants.MESSAGE_DATE_HAS_PASSED);
			}
			if (CommonInterpreterMethods.isInvalidDate(date)) {
				SummaryReport
						.setFeedBackMsg(Constants.MESSAGE_INVALID_DISPLAY_SELECTION);
				throw new InvalidParameterException(
						Constants.MESSAGE_INVALID_DISPLAY_SELECTION);
			} else {
				task.setDueDate(date);
				result.setSearchMode(SearchType.DATE);
			}
		}
	}

	

	private void updateForEditCase(ParsedResult result, String commandParam) {
		if (CommonInterpreterMethods.isValidSelection(commandParam)) {
			int selection = getSelection(commandParam); 
			copyTaskParamToParsedResult(result, selection);
			result.setSelectedItem(selection);
		} else {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
			throw new InvalidParameterException(
					Constants.MESSAGE_INVALID_SELECTION);
		}
	}

	private void updateForDeleteCase(ParsedResult result, String commandParam) {
		if (CommonInterpreterMethods.isValidSelection(commandParam)) {
			int selection = getSelection(commandParam);
			result.setTask(SummaryReport.getDisplayList().get(selection));
			result.setSelectedItem(selection);
		} else {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
			throw new InvalidParameterException(
					Constants.MESSAGE_INVALID_SELECTION);
		}
	}

	public boolean commandDoesNotRequireParam() {

		if (currentCommand == CommandType.UNDO
				|| currentCommand == CommandType.REDO
				|| currentCommand == CommandType.EXIT)
			return true;

		return false;

	}

}
