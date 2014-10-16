package Parser;

import java.security.InvalidParameterException;

import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class Parser {

	private ParsedResult result;
	private MainCommandInterpreter mainHandler;
	private OptionalCommandInterpreter optionHandler;

	public Parser() {
		mainHandler = new MainCommandInterpreter();
		optionHandler = new OptionalCommandInterpreter();
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
<<<<<<< HEAD
			while (remainingInput.isEmpty() == false) {
				remainingInput = identifyOptionalCommandAndUpdate(remainingInput, result);
			}
=======
			processOptionalCommand(remainingInput);
			
>>>>>>> c84f0659520769fcd5ce8eaabbfacbd0da7469c6
			if(result.getTaskDetails().getDueDate() == null) {
				result.getTaskDetails().setDueDate(Constants.SOMEDAY);
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

	private void processOptionalCommand(String remainingInput) {
		String commandWord;
		String commandParam;
		
<<<<<<< HEAD
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
				task.setDueDate(StringConstants.SOMEDAY);
				task.setStartDate(null);
				break;
			}
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setDueDate(date);
				task.setStartDate(null);
			}
			break;

		case FROM:
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setStartDate(date);
			}
			break;

		case TO:
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_DATE);
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
				SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_IMPORTANCE_PARAM);
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
		for (int i = 0; i < StringConstants.DATE_FORMAT_ITERATIONS; i++) {
			try {
				df = DateTimeFormat.forPattern(StringConstants.dateFormats[i]);
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
=======
		while (remainingInput.isEmpty() == false) {
			commandWord = getCommandWord(remainingInput);
>>>>>>> c84f0659520769fcd5ce8eaabbfacbd0da7469c6

			optionHandler.identifyAndSetCommand(commandWord.toLowerCase());

			remainingInput = optionHandler.removeCommandWord(remainingInput);
			commandParam = getParam(remainingInput);

<<<<<<< HEAD
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
			SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_OPTIONAL_COMMAND);
			throw new InvalidParameterException();
		}

=======
			result = optionHandler.updateResults(result, commandParam);

			remainingInput = removeParam(remainingInput);
		}
	}

	private static boolean commandDoesNotRequireParam(CommandType command) {

		if(command == CommandType.UNDO)
			return true;

		return false;
>>>>>>> c84f0659520769fcd5ce8eaabbfacbd0da7469c6
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

<<<<<<< HEAD

=======
>>>>>>> c84f0659520769fcd5ce8eaabbfacbd0da7469c6
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

}
