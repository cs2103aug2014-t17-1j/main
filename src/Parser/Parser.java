package Parser;

import java.security.InvalidParameterException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import taskDo.SearchType;
import taskDo.StringConstants;
import taskDo.SummaryReport;
import taskDo.Task;

import com.joestelmach.natty.DateGroup;

import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class Parser {

	enum OptionalCommand {
		DUE, FROM, TO, CATEGORY, IMPT, TASK
	}
	
	private ParsedResult result;
	private MainCommandHandler mainHandler;
	public Parser() {
		mainHandler = new MainCommandHandler();
	}

	public ParsedResult parseString(String input) { 
		result = new ParsedResult();

		//Processing first command and getting command param
		try {
			String commandWord = getCommandWord(input);
			mainHandler.identifyAndSetCommand(commandWord.toLowerCase());
			if(commandDoesNotRequireParam(mainHandler.getCommand())) {
				result.setCommandType(mainHandler.getCommand());
				return result;
			}
			String remainingInput = mainHandler.removeCommandWord(input);
			String commandParam = getParam(remainingInput);

			remainingInput = removeParam(remainingInput);
			result = mainHandler.updateResults(result,commandParam);

			//Processing optional commands
			while (remainingInput.isEmpty() == false) {
				remainingInput = identifyOptionalCommandAndUpdate(remainingInput, result);
			}
<<<<<<< HEAD:src/taskDo/Parser.java
			if(ParsedResult.getTaskDetails().getDueDate() == null) {
				ParsedResult.getTaskDetails().setDueDate(Constants.SOMEDAY);
=======
			if(result.getTaskDetails().getDueDate() == null) {
				result.getTaskDetails().setDueDate(StringConstants.SOMEDAY);
>>>>>>> 159447362887a1ed928e035af8f94e0322bef7c0:src/Parser/Parser.java
			}
			return result;
		}
		catch (Exception e) {
			result.setValidationResult(false);
			return result;
		}



		/*	System.out.println(commandWord);

		System.out.println(commandWord);
		System.out.println(commandParam);
		System.out.println(ParsedResult.getTaskDetails().getDueDate()
				.toLocalDate().toString("dd/MM/yyyy"));
		System.out.println(ParsedResult.getTaskDetails().getDueDate()
				.toLocalTime().toString("HH:mm"));  */
	}

	private static boolean commandDoesNotRequireParam(CommandType command) {
		
		if(command == CommandType.UNDO || command == CommandType.SAVE)
			return true;
		
		return false;
	}

	private static String identifyOptionalCommandAndUpdate(String remainingInput, ParsedResult result) throws InvalidParameterException {
		String commandWord = getCommandWord(remainingInput);

		OptionalCommand command = identifyOptionalCommand(commandWord
				.toLowerCase());

		remainingInput = removeOptionalCommand(remainingInput, command);
		String commandParam = getParam(remainingInput);

		optionsUpdateParsedResult(command, commandParam, result);

		remainingInput = removeParam(remainingInput);
		return remainingInput;
	}

	private static void optionsUpdateParsedResult(OptionalCommand command,
			String commandParam, ParsedResult result) throws InvalidParameterException {

		Task task = result.getTaskDetails();
		DateTime date = null;
		switch (command) {
		case DUE:
			if(noDeadLine(commandParam)) {
				task.setDueDate(Constants.SOMEDAY);
				task.setStartDate(null);
				break;
			}
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setDueDate(date);
				task.setStartDate(null);
			}
			break;

		case FROM:
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setStartDate(date);
			}
			break;

		case TO:
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setDueDate(date);
			}
			break;

		case CATEGORY:
			task.setCategory(commandParam);
			break;

		case TASK:
			task.setDescription(commandParam);
			break;

		case IMPT:
			if(commandParam.equals("Y")) {
				task.setImportant(true);
			}
			else if(commandParam.equals("N")) {
				task.setImportant(false);
			}
			else {
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_IMPORTANCE_PARAM);
				throw new InvalidParameterException();
			}
			break;

		default:// do nothing
		}
	}

	private static DateTime getDate(String commandParam) {

		com.joestelmach.natty.Parser parser = new com.joestelmach.natty.Parser();
		DateTimeFormatter df;
		DateTime date;
		List<DateGroup> group;
		for (int i = 0; i < Constants.DATE_FORMAT_ITERATIONS; i++) {
			try {
				df = DateTimeFormat.forPattern(Constants.dateFormats[i]);
				date = df.parseDateTime(commandParam);
				group = parser.parse(date.toString());
				DateTime dates = new DateTime(group.get(0).getDates().get(0));

				DateTime currentTime = new DateTime();

				if (dates.hourOfDay().toString()
						.equals(currentTime.hourOfDay().toString())) {
					dates = dates.withHourOfDay(23);
					dates = dates.withMinuteOfHour(59); // update the time to
					// 2359
				}

				return dates;
			} catch (Exception e) {

			}
		}
		group = parser.parse(commandParam);
		if (group.isEmpty()) {
			return null; // Not a valid date
		}
		DateTime dates = new DateTime(group.get(0).getDates().get(0));

		DateTime currentTime = new DateTime();

		if (dates.hourOfDay().toString()
				.equals(currentTime.hourOfDay().toString())) {
			dates = dates.withHourOfDay(23);
			dates = dates.withMinuteOfHour(59); // update the time to 2359
		}

		return dates;
	}

	private static String removeOptionalCommand(String remainingInput,
			OptionalCommand commandWord) {

		switch (commandWord) {

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

	private static OptionalCommand identifyOptionalCommand(String commandWord) throws InvalidParameterException {

		switch (commandWord) {

		case "due":
			return OptionalCommand.DUE;

		case "from":
			return OptionalCommand.FROM;

		case "to":
			return OptionalCommand.TO;

		case "category":
			return OptionalCommand.CATEGORY;

		case "impt":
			return OptionalCommand.IMPT;

		case "task":
			return OptionalCommand.TASK;

		default:
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_OPTIONAL_COMMAND);
			throw new InvalidParameterException();
		}

	}

<<<<<<< HEAD:src/taskDo/Parser.java
	private static void updateParsedResult(CommandType command,
			String commandParam) throws InvalidParameterException {
		ParsedResult.setCommandType(command);
		Task task = ParsedResult.getTaskDetails();
		switch (command) {

		case ADD:
			task.setDescription(commandParam);
			break;

		case DELETE:
			if(isValidSelection(commandParam)) {
				ParsedResult.setTask(SummaryReport.getDisplayList().get(Integer.valueOf(commandParam)-1));

			}
			else {
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
				throw new InvalidParameterException();
			}
			break;

		case EDIT:
			if(isValidSelection(commandParam)) {
				int selection = Integer.valueOf(commandParam) - 1; //adjust to get the correct index in list
				ParsedResult.setTask(SummaryReport.getDisplayList().get(selection));
			}
			else {
				SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
				throw new InvalidParameterException();
			}
			break;

		case DISPLAY:
			if (isCategory(commandParam)) {
				task.setCategory(commandParam);
				ParsedResult.setSearchMode(SearchType.CATEGORY);
			} else {
				if(noDeadLine(commandParam)) {
					task.setDueDate(Constants.SOMEDAY);
					task.setStartDate(null);
					break;
				}
				DateTime date = getDate(commandParam);
				if(date == null) {
					SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DISPLAY_SELECTION);
					throw new InvalidParameterException();
				}
				else {
					task.setDueDate(date);
					ParsedResult.setSearchMode(SearchType.DATE);
				}
			}
			break;
		case UNDO:
			// do nothing
			break;

		default:
			// do nothing

		}
	}

	private static boolean isValidSelection(String commandParam) {
		int selection;
		try {
			selection = Integer.valueOf(commandParam);
		}
		catch (Exception e) {
			return false;
		}

		if(selection >= 1 && selection <= SummaryReport.getDisplayList().size()) {
			return true;
		}
		return false;
	}

	private static boolean isCategory(String commandParam) {
		// Check from a list of categories
		return false;
	}

	private static void resetParsedResult() {
		ParsedResult.clear();
	}

=======
>>>>>>> 159447362887a1ed928e035af8f94e0322bef7c0:src/Parser/Parser.java
	private static String removeParam(String remainingInput) {
		int indexEndOfParam = remainingInput.indexOf(']');

		// Adjust index to take substring after ']'
		indexEndOfParam++;
		remainingInput = remainingInput.substring(indexEndOfParam);

		return remainingInput.trim();
	}

	private static String getCommandWord(String input) {
		String[] splittedCommand = input.split(" ");

		return splittedCommand[0];
	}

<<<<<<< HEAD:src/taskDo/Parser.java
	private static CommandType identifyCommand(String command) throws InvalidParameterException {

		switch (command) {
		case "add":
			return CommandType.ADD;

		case "display":
			return CommandType.DISPLAY;

		case "delete":
			return CommandType.DELETE;

		case "edit":
			return CommandType.EDIT;

		case "undo":
			return CommandType.UNDO;

		case "search":
			return CommandType.SEARCH;

			// not sure if I need init and save to be here
		default:
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_COMMAND);
			throw new InvalidParameterException();
		}
	}

	private static String removeCommandWord(String input,
			CommandType commandWord) {
		switch (commandWord) {
		case ADD:
			return input.substring(4); // 4 is length of word "add "

		case DISPLAY:
			return input.substring(8); // 8 is length of word "display "

		case DELETE:
			return input.substring(7); // 7 is length of word "delete "

		case SEARCH:
			return input.substring(7); // 7 is length of word "search "

		case EDIT:
			return input.substring(5);

		default:
			return "";

		}

	}
=======
>>>>>>> 159447362887a1ed928e035af8f94e0322bef7c0:src/Parser/Parser.java

	private static String getParam(String remainingInput) throws InvalidParameterException {
		int indexStartOfParam = remainingInput.indexOf('[');
		int indexEndOfParam = remainingInput.indexOf(']');

		if(indexStartOfParam == -1 || indexEndOfParam == -1) {
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_PARAM_FORMATTING);
			throw new InvalidParameterException();
			//return remainingInput;
		}
		indexStartOfParam++; //adjust to get first letter of param
		remainingInput = remainingInput.substring(indexStartOfParam,
				indexEndOfParam);

		return remainingInput.trim();
	}
	
	private static boolean noDeadLine(String commandParam) {
		if(commandParam.toUpperCase().equals("SOMEDAY")) {
			return true;
		}
		
		return false;
	}
}
