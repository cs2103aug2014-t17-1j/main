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

		// Processing first command and getting command param
		try {
			String[] seperatedInput = input.split("-");
			String commandWord = getCommandWord(seperatedInput[1]);
			mainHandler.identifyAndSetCommand(commandWord.toLowerCase());
			if (commandDoesNotRequireParam(mainHandler.getCommand())) {
				result.setCommandType(mainHandler.getCommand());
				return result;
			}
			String remainingInput = mainHandler.removeCommandWord(seperatedInput[1]);
			//String commandParam = getParam(remainingInput);

			//remainingInput = removeParam(remainingInput);
			result = mainHandler.updateResults(result, remainingInput.trim());

			// Processing optional commands


			processOptionalCommand(seperatedInput);

			if (result.getTaskDetails().getDueDate() == null) {
				result.getTaskDetails().setDueDate(Constants.SOMEDAY);

			}
			return result;
		} catch (Exception e) {
			result.setValidationResult(false);
			return result;
		}

		/*
		 * System.out.println(commandWord);
		 * 
		 * System.out.println(commandWord); System.out.println(commandParam);
		 * System.out.println(ParsedResult.getTaskDetails().getDueDate()
		 * .toLocalDate().toString("dd/MM/yyyy"));
		 * System.out.println(ParsedResult.getTaskDetails().getDueDate()
		 * .toLocalTime().toString("HH:mm"));
		 */
	}

	private void processOptionalCommand(String[] remainingInput) {
		String commandWord;
		String commandParam;

		for(int i=2; i<remainingInput.length;i++) {
			commandWord = getCommandWord(remainingInput[i]);

			optionHandler.identifyAndSetCommand(commandWord.toLowerCase());

			remainingInput[i] = optionHandler.removeCommandWord(remainingInput[i]);
			//commandParam = getParam(remainingInput[i]);


			result = optionHandler.updateResults(result, remainingInput[i].trim());

			//remainingInput = removeParam(remainingInput);
		}
	}

	private static boolean commandDoesNotRequireParam(CommandType command) {

		if (command == CommandType.UNDO)
			return true;

		return false;

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

	private static String getParam(String remainingInput)
			throws InvalidParameterException {
		int indexStartOfParam = remainingInput.indexOf('[');
		int indexEndOfParam = remainingInput.indexOf(']');

		if (indexStartOfParam == -1 || indexEndOfParam == -1) {
			SummaryReport
					.setFeedBackMsg(Constants.MESSAGE_INVALID_PARAM_FORMATTING);
			throw new InvalidParameterException();
			// return remainingInput;
		}
		indexStartOfParam++; // adjust to get first letter of param
		remainingInput = remainingInput.substring(indexStartOfParam,
				indexEndOfParam);

		return remainingInput.trim();
	}

}
