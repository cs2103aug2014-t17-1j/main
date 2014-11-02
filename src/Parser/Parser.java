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
			if (isInteger(input)) {
				if (CommonInterpreterMethods.isValidSelection(input)) {
					SummaryReport.setRowIndexHighlight(Integer.valueOf(input)-1); //get correct index in list
					result.setIsExecutorApplicable(false);
					return result;
				} else {
					SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
					throw new InvalidParameterException();
				}
			}
			String[] seperatedInput = input.split("-");
			String commandWord = getCommandWord(seperatedInput[0].trim());
			mainHandler.identifyAndSetCommand(commandWord.toLowerCase());
			if (commandDoesNotRequireParam(mainHandler.getCommand())) {
				result.setCommandType(mainHandler.getCommand());
				return result;
			}
			String remainingInput = mainHandler
					.removeCommandWord(seperatedInput[0].trim());

			result = mainHandler.updateResults(result, remainingInput.trim());

			// Processing optional commands
			processOptionalCommand(seperatedInput);

			if (result.getTaskDetails().getDueDate() == null) {
				result.getTaskDetails().setDueDate(Constants.SOMEDAY);

			}
		} catch (Exception e) {
			result.setIsExecutorApplicable(false);
		}
		return result;
	}

	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input, 10);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void processOptionalCommand(String[] remainingInput) {
		String commandWord;

		for (int i = 1; i < remainingInput.length; i++) {
			commandWord = getCommandWord(remainingInput[i].trim());

			optionHandler.identifyAndSetCommand(commandWord.toLowerCase());

			remainingInput[i] = optionHandler
					.removeCommandWord(remainingInput[i].trim());

			result = optionHandler.updateResults(result,
					remainingInput[i].trim());

		}
	}

	private static boolean commandDoesNotRequireParam(CommandType command) {

		if (command == CommandType.UNDO || command == CommandType.REDO)
			return true;

		return false;

	}

	private static String getCommandWord(String input) {
		String[] splittedCommand = input.split(" ");

		return splittedCommand[0];
	}

}
