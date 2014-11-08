package parser;

import java.security.InvalidParameterException;

import org.joda.time.DateTime;

import taskDo.SearchType;
import taskDo.Task;
import taskDo.TaskType;

import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class OptionalCommandInterpreter extends CommandInterpreter {

	enum OptionalCommand {
		DUE, FROM, TO, CATEGORY, IMPT, TITLE, NOTE
	}

	// Members
	OptionalCommand currentCommand;

	public OptionalCommandInterpreter() {

	}

	public void identifyAndSetCommand(String command)
			throws InvalidParameterException {

		switch (command) {
		case "due":
			currentCommand = OptionalCommand.DUE;
			break;

		case "from":
			currentCommand = OptionalCommand.FROM;
			break;

		case "to":
			currentCommand = OptionalCommand.TO;
			break;

		case "category":
			currentCommand = OptionalCommand.CATEGORY;
			break;

		case "impt":
			currentCommand = OptionalCommand.IMPT;
			break;

		case "title":
			currentCommand = OptionalCommand.TITLE;
			break;

		case "note":
			currentCommand = OptionalCommand.NOTE;
			break;

		default:
			SummaryReport
					.setFeedBackMsg(Constants.MESSAGE_INVALID_OPTIONAL_COMMAND);
			throw new InvalidParameterException(
					Constants.MESSAGE_INVALID_OPTIONAL_COMMAND);
		}
	}

	public String removeCommandWord(String remainingInput) {
		try {
			switch (currentCommand) {

			case DUE:
				return remainingInput.substring(4); // Length of word "due "

			case FROM:
				return remainingInput.substring(5); // Length of word "from "

			case TO:
				return remainingInput.substring(3); // Length of word "to "

			case IMPT:
				return remainingInput.substring(5); // Length of word "impt "

			case CATEGORY:
				return remainingInput.substring(9); // Length of word
													// "category "

			case TITLE:
				return remainingInput.substring(6); // Length of word "title "

			case NOTE:
				return remainingInput.substring(5); // Length of word "note "

			default:
				return "";
			}
		} catch (Exception e) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_MISSING_PARAM);
			throw new InvalidParameterException(Constants.MESSAGE_MISSING_PARAM);
		}
	}

	public ParsedResult updateResults(ParsedResult result, String commandParam)
			throws InvalidParameterException {
		Task task = result.getTaskDetails();

		switch (currentCommand) {
		case DUE:
			updateDueCase(result, commandParam);
			break;

		case FROM:
			updateFromCase(result, commandParam);
			break;

		case TO:
			updateToCase(result, commandParam);
			break;

		case CATEGORY:
			task.setCategory(commandParam);
			break;

		case TITLE:
			task.setTitle(commandParam);
			break;

		case IMPT:
			updateImportantCase(commandParam, task);
			break;

		case NOTE:
			task.setNote(commandParam);

		default:// do nothing
		}

		return result;
	}

	private void updateImportantCase(String commandParam, Task task)
			throws InvalidParameterException {
		if (commandParam.toUpperCase().equals(Constants.IMPT_YES)) {
			task.setImportant(true);
		} else if (commandParam.toUpperCase().equals(Constants.IMPT_NO)) {
			task.setImportant(false);
		} else {
			SummaryReport
					.setFeedBackMsg(Constants.MESSAGE_INVALID_IMPORTANCE_PARAM);
			throw new InvalidParameterException(
					Constants.MESSAGE_INVALID_IMPORTANCE_PARAM);
		}
	}

	private void updateToCase(ParsedResult result, String commandParam)
			throws InvalidParameterException {
		DateTime date;
		try {
			date = CommonInterpreterMethods.getDate(commandParam);
		} catch (Exception e) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_DATE_HAS_PASSED);
			throw new InvalidParameterException(
					Constants.MESSAGE_DATE_HAS_PASSED);
		}
		Task task = result.getTaskDetails();
		if (date == null) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
			throw new InvalidParameterException(Constants.MESSAGE_INVALID_DATE);
		}
		if (result.getCommandType() == CommandType.DISPLAY) {
			task.setStartDate(task.getDueDate());
			if (task.getStartDate().isAfter(date)) {
				SummaryReport
						.setFeedBackMsg(Constants.MESSAGE_END_DATE_EARLIER_THAN_START_DATE);
				throw new InvalidParameterException(
						Constants.MESSAGE_END_DATE_EARLIER_THAN_START_DATE);
			}
			result.setSearchMode(SearchType.RANGEOFDATES);
		} else if (result.getTaskDetails().getStartDate() == null) {
			SummaryReport
					.setFeedBackMsg(Constants.MESSAGE_MISSING_START_DATE_FOR_TASK);
			throw new InvalidParameterException(
					Constants.MESSAGE_MISSING_START_DATE_FOR_TASK);
		}
		task.setDueDate(date);

	}

	private void updateFromCase(ParsedResult result, String commandParam)
			throws InvalidParameterException {
		DateTime date;
		Task task = result.getTaskDetails();
		try {
			date = CommonInterpreterMethods.getDate(commandParam);
		} catch (Exception e) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_DATE_HAS_PASSED);
			throw new InvalidParameterException(
					Constants.MESSAGE_DATE_HAS_PASSED);
		}
		if (commandParam.contains(" to ")) {
			SummaryReport
					.setFeedBackMsg(Constants.MESSAGE_MISSING_SIGN_FROMTO_COMMAND);
			throw new InvalidParameterException(
					Constants.MESSAGE_MISSING_SIGN_FROMTO_COMMAND);
		} else if (date == null) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
			throw new InvalidParameterException(Constants.MESSAGE_INVALID_DATE);
		}
		if (task.getTaskType() == TaskType.DEADLINE
				&& result.getCommandType() != CommandType.EDIT) {
			SummaryReport
					.setFeedBackMsg(Constants.MESSAGE_INVALID_COMBINATION_DUE_AND_FROMTO);
			throw new InvalidParameterException(
					Constants.MESSAGE_INVALID_COMBINATION_DUE_AND_FROMTO);
		}
		task.setStartDate(date);
		task.setTaskType(TaskType.TIMED);
	}

	private void updateDueCase(ParsedResult result, String commandParam)
			throws InvalidParameterException {
		DateTime date;
		Task task = result.getTaskDetails();
		if (CommonInterpreterMethods.noDeadLine(commandParam)) {
			task.setDueDate(Constants.SOMEDAY);
			task.setStartDate(null);
		} else {
			try {
				date = CommonInterpreterMethods.getDate(commandParam);
			} catch (Exception e) {
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_DATE_HAS_PASSED);
				throw new InvalidParameterException(
						Constants.MESSAGE_DATE_HAS_PASSED);
			}
			if (date == null) {
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException(
						Constants.MESSAGE_INVALID_DATE);
			}
			if (task.getTaskType() == TaskType.TIMED
					&& result.getCommandType() != CommandType.EDIT) {
				SummaryReport
						.setFeedBackMsg(Constants.MESSAGE_INVALID_COMBINATION_DUE_AND_FROMTO);
				throw new InvalidParameterException(
						Constants.MESSAGE_INVALID_COMBINATION_DUE_AND_FROMTO);
			}
			task.setDueDate(date);
			task.setStartDate(null);
			task.setTaskType(TaskType.DEADLINE);
		}
	}

}
