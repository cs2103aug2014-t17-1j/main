package Parser;

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
		DUE, FROM, TO, CATEGORY, IMPT, TASK, NOTE
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

		case "task":
			currentCommand = OptionalCommand.TASK;
			break;
			
		case "note":
			currentCommand = OptionalCommand.NOTE;
			break;
			
		default:
			SummaryReport
					.setFeedBackMsg(Constants.MESSAGE_INVALID_OPTIONAL_COMMAND);
			throw new InvalidParameterException();
		}
	}

	public String removeCommandWord(String remainingInput) {
	try {
		switch (currentCommand) {

		case DUE:
			return remainingInput.substring(4); // Length of word "due "

		case FROM:
			return remainingInput.substring(5);

		case TO:
			return remainingInput.substring(3);

		case IMPT:
			return remainingInput.substring(5);

		case CATEGORY:
			return remainingInput.substring(9);

		case TASK:
			return remainingInput.substring(5);
			
		case NOTE:
			return remainingInput.substring(5);

		default:
			return "";
		}
	} catch (Exception e) {
		SummaryReport.setFeedBackMsg(Constants.MESSAGE_MISSING_PARAM);
		throw new InvalidParameterException();
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

		case TASK:
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

	private void updateImportantCase(String commandParam, Task task) throws InvalidParameterException {
		if (commandParam.toUpperCase().equals(Constants.IMPT_YES)) {
			task.setImportant(true);
		} else if (commandParam.toUpperCase().equals(Constants.IMPT_NO)) {
			task.setImportant(false);
		} else {
			SummaryReport
					.setFeedBackMsg(Constants.MESSAGE_INVALID_IMPORTANCE_PARAM);
			throw new InvalidParameterException();
		}
	}

	private void updateToCase(ParsedResult result, String commandParam) throws InvalidParameterException {
		DateTime date;
		date = CommonInterpreterMethods.getDate(commandParam);
		Task task = result.getTaskDetails();
		if (date == null) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
			throw new InvalidParameterException();
		}
		if(result.getCommandType() == CommandType.DISPLAY) {
			task.setStartDate(task.getDueDate());
			if(task.getStartDate().isAfter(date)) {
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_END_DATE_EARLIER_THAN_START_DATE);
				throw new InvalidParameterException();
			}
			result.setSearchMode(SearchType.RANGEOFDATES);
		} else if(result.getTaskDetails().getStartDate() == null) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_MISSING_START_DATE_FOR_TASK);
			throw new InvalidParameterException();
		}
		task.setDueDate(date);
		
	}

	private void updateFromCase(ParsedResult result, String commandParam) throws InvalidParameterException {
		DateTime date;
		Task task = result.getTaskDetails();
		date = CommonInterpreterMethods.getDate(commandParam);
		if(commandParam.contains(" to ")) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_MISSING_SIGN_FROMTO_COMMAND);
			throw new InvalidParameterException();
		} else if (date == null) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
			throw new InvalidParameterException();
		}
		if(task.getTaskType() == TaskType.DEADLINE && result.getCommandType() != CommandType.EDIT) { //Means previously already used due optional command
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_COMBINATION_DUE_AND_FROMTO);
			throw new InvalidParameterException();
		}
			task.setStartDate(date);
			task.setTaskType(TaskType.TIMED);
	}

	private void updateDueCase(ParsedResult result, String commandParam) throws InvalidParameterException  {
		DateTime date;
		Task task = result.getTaskDetails();
		if (CommonInterpreterMethods.noDeadLine(commandParam)) {
			task.setDueDate(Constants.SOMEDAY);
			task.setStartDate(null);
		} else {
			date = CommonInterpreterMethods.getDate(commandParam);
			if (date == null) {
				SummaryReport
						.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			}
			if(task.getTaskType() == TaskType.TIMED && result.getCommandType() != CommandType.EDIT) { //Means previously used from to command
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_COMBINATION_DUE_AND_FROMTO);
				throw new InvalidParameterException();
			}
				task.setDueDate(date);
				task.setStartDate(null);
				task.setTaskType(TaskType.DEADLINE);
		}
	}

}
