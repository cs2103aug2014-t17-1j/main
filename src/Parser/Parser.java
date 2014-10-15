package Parser;

import java.security.InvalidParameterException;

import taskDo.StringConstants;
import taskDo.SummaryReport;

import commandFactory.CommandType;

public class Parser {

	private ParsedResult result;
	private MainCommandHandler mainHandler;
	private OptionalCommandHandler optionHandler;

	public Parser() {
		mainHandler = new MainCommandHandler();
		optionHandler = new OptionalCommandHandler();
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
			processOptionalCommand(remainingInput);
			
			if(result.getTaskDetails().getDueDate() == null) {
				result.getTaskDetails().setDueDate(StringConstants.SOMEDAY);
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
		
		while (remainingInput.isEmpty() == false) {
			commandWord = getCommandWord(remainingInput);

			optionHandler.identifyAndSetCommand(commandWord.toLowerCase());

			remainingInput = optionHandler.removeCommandWord(remainingInput);
			commandParam = getParam(remainingInput);

			result = optionHandler.updateResults(result, commandParam);

			remainingInput = removeParam(remainingInput);
		}
	}

	private static boolean commandDoesNotRequireParam(CommandType command) {

		if(command == CommandType.UNDO)
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

	private static String getParam(String remainingInput) throws InvalidParameterException {
		int indexStartOfParam = remainingInput.indexOf('[');
		int indexEndOfParam = remainingInput.indexOf(']');

		if(indexStartOfParam == -1 || indexEndOfParam == -1) {
			SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_PARAM_FORMATTING);
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
