package taskDo;

import java.security.InvalidParameterException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.joestelmach.natty.DateGroup;
import commandFactory.CommandType;

public class Parser {

	private static String[] dateFormats = { "dd/MM/yyyy", "yyyy/MM/dd", "dd-MM-yyyy", "yyyy-MM-dd" };
	private static final int DATE_FORMAT_ITERATIONS = 4;

	private static final String MESSAGE_INVALID_COMMAND = "INVALID COMMAND!";
	private static final String MESSAGE_INVALID_OPTIONAL_COMMAND = "INVALID OPTIONAL COMMAND!";
	private static final String MESSAGE_INVALID_DATE = "DATE NOT RECOGNIZED!";
	private static final String MESSAGE_INVALID_DISPLAY_SELECTION = "EITHER CATEGORY DOES NOT EXIST OR DATE NOT RECOGNIZED!";
	private static final String MESSAGE_INVALID_SELECTION = "INVALID SELECTION!";
	private static final String MESSAGE_INVALID_IMPORTANCE_PARAM = "IMPORTANCE LEVEL NOT RECOGNIZED!";
	private static final String MESSAGE_INVALID_PARAM_FORMATTING = "MISSING [] BRACKETS FOR COMMAND PARAMETER";

	private static final DateTime SOMEDAY = new DateTime(0,1,1,0,0);

	enum OptionalCommand {
		DUE, FROM, TO, CATEGORY, IMPT, TASK
	}

	public static void parserInit() {

	}

	public static boolean parseString(String input) { 
		resetParsedResult();

		//Processing first command and getting command param
		try {
			String commandWord = getCommandWord(input);
			CommandType command = identifyCommand(commandWord.toLowerCase());

			String remainingInput = removeCommandWord(input, command);
			String commandParam = getParam(remainingInput);

			remainingInput = removeParam(remainingInput);
			updateParsedResult(command, commandParam);

			//Processing optional commands
			while (remainingInput.isEmpty() == false) {
				remainingInput = identifyOptionalCommandAndUpdate(remainingInput);
			}
			if(ParsedResult.getTaskDetails().getDueDate() == null) {
				ParsedResult.getTaskDetails().setDueDate(SOMEDAY);
			}
			return true;
		}
		catch (Exception e) {
			return false;
		}



		/*	System.out.println(commandWord);

		System.out.println(commandWord);
		System.out.println(commandParam);
		System.out.println(ParsedResult.getTaskDetails().getDueDate()
				.toLocalDate().toString("dd/MM/yyyy"));
		System.out.println(ParsedResult.getTaskDetails().getDueDate()
				.toLocalTime().toString("HH:mm"));  */
	}

	private static String identifyOptionalCommandAndUpdate(String remainingInput) throws InvalidParameterException {
		String commandWord = getCommandWord(remainingInput);

		OptionalCommand command = identifyOptionalCommand(commandWord
				.toLowerCase());

		remainingInput = removeOptionalCommand(remainingInput, command);
		String commandParam = getParam(remainingInput);

		optionsUpdateParsedResult(command, commandParam);

		remainingInput = removeParam(remainingInput);
		return remainingInput;
	}

	private static void optionsUpdateParsedResult(OptionalCommand command,
			String commandParam) throws InvalidParameterException {

		Task task = ParsedResult.getTaskDetails();
		DateTime date = null;
		switch (command) {
		case DUE:
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setDueDate(date);
				task.setStartDate(null);
			}
			break;

		case FROM:
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setStartDate(date);
			}
			break;

		case TO:
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(MESSAGE_INVALID_DATE);
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
				SummaryReport.setFeedBackMsg(MESSAGE_INVALID_IMPORTANCE_PARAM);
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
		for (int i = 0; i < DATE_FORMAT_ITERATIONS; i++) {
			try {
				df = DateTimeFormat.forPattern(dateFormats[i]);
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
			SummaryReport.setFeedBackMsg(MESSAGE_INVALID_OPTIONAL_COMMAND);
			throw new InvalidParameterException();
		}

	}

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
				SummaryReport.setFeedBackMsg(MESSAGE_INVALID_SELECTION);
				throw new InvalidParameterException();
			}
			break;

		case EDIT:
			if(isValidSelection(commandParam)) {
				int selection = Integer.valueOf(commandParam) - 1; //adjust to get the correct index in list
				ParsedResult.setTask(SummaryReport.getDisplayList().get(selection));
			}
			else {
				SummaryReport.setFeedBackMsg(MESSAGE_INVALID_SELECTION);
				throw new InvalidParameterException();
			}
			break;

		case DISPLAY:
			if (isCategory(commandParam)) {
				task.setCategory(commandParam);
				ParsedResult.setSearchMode(SearchType.CATEGORY);
			} else {
				DateTime date = getDate(commandParam);
				if(date == null) {
					SummaryReport.setFeedBackMsg(MESSAGE_INVALID_DISPLAY_SELECTION);
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
			SummaryReport.setFeedBackMsg(MESSAGE_INVALID_COMMAND);
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

	private static String getParam(String remainingInput) throws InvalidParameterException {
		int indexStartOfParam = remainingInput.indexOf('[');
		int indexEndOfParam = remainingInput.indexOf(']');

		if(indexStartOfParam == -1 || indexEndOfParam == -1) {
			SummaryReport.setFeedBackMsg(MESSAGE_INVALID_PARAM_FORMATTING);
			throw new InvalidParameterException();
			//return remainingInput;
		}
		indexStartOfParam++; //adjust to get first letter of param
		remainingInput = remainingInput.substring(indexStartOfParam,
				indexEndOfParam);

		return remainingInput.trim();
	}
}
