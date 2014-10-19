package Parser;

import java.security.InvalidParameterException;

import org.joda.time.DateTime;

import taskDo.Task;
import taskDo.TaskType;
import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class OptionalCommandInterpreter extends CommandInterpreter {

	enum OptionalCommand {
		DUE, FROM, TO, CATEGORY, IMPT, TASK
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

		default:
			SummaryReport
					.setFeedBackMsg(Constants.MESSAGE_INVALID_OPTIONAL_COMMAND);
			throw new InvalidParameterException();
		}
	}

	public String removeCommandWord(String remainingInput) {
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

		default:
			return "";
		}
	}

	public ParsedResult updateResults(ParsedResult result, String commandParam)
			throws InvalidParameterException {
		Task task = result.getTaskDetails();

		switch (currentCommand) {
		case DUE:
			updateDueCase(commandParam, task);
			break;

		case FROM:
			updateFromCase(commandParam, task);
			break;

		case TO:
			updateToCase(result, commandParam, task);
			break;

		case CATEGORY:
			task.setCategory(commandParam);
			break;

		case TASK:
			task.setDescription(commandParam);
			break;

		case IMPT:
			updateImportantCase(commandParam, task);
			break;

		default:// do nothing
		}

		return result;
	}

	private void updateImportantCase(String commandParam, Task task) {
		if (commandParam.equals("Y")) {
			task.setImportant(true);
		} else if (commandParam.equals("N")) {
			task.setImportant(false);
		} else {
			SummaryReport
					.setFeedBackMsg(Constants.MESSAGE_INVALID_IMPORTANCE_PARAM);
			throw new InvalidParameterException();
		}
	}

	private void updateToCase(ParsedResult result, String commandParam, Task task) {
		DateTime date;
		date = CommonInterpreterMethods.getDate(commandParam);
		if (date == null) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
			throw new InvalidParameterException();
		} else {
			if(result.getCommandType() == CommandType.DISPLAY) {
				task.setStartDate(task.getDueDate());
			} //This is when user wants to display task within a range of dates
			task.setDueDate(date);
		}
	}

	private void updateFromCase(String commandParam, Task task) {
		DateTime date;
		date = CommonInterpreterMethods.getDate(commandParam);
		if (date == null) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
			throw new InvalidParameterException();
		} else {
			task.setStartDate(date);
			task.setTaskType(TaskType.TIMED);
		}
	}

	private void updateDueCase(String commandParam, Task task) {
		DateTime date;
		if (CommonInterpreterMethods.noDeadLine(commandParam)) {
			task.setDueDate(Constants.SOMEDAY);
			task.setStartDate(null);
		} else {
			date = CommonInterpreterMethods.getDate(commandParam);
			if (date == null) {
				SummaryReport
						.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setDueDate(date);
				task.setStartDate(null);
				task.setTaskType(TaskType.DEADLINE);
			}
		}
	}

}
