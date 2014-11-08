package parser;

import java.security.InvalidParameterException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class Parser {

	private ParsedResult result;
	private MainCommandInterpreter mainHandler;
	private OptionalCommandInterpreter optionHandler;
	private static final Logger logger = LogManager.getLogger(Parser.class);

	public Parser() {
		mainHandler = new MainCommandInterpreter();
		optionHandler = new OptionalCommandInterpreter();
	}

	public ParsedResult parseString(String input) {
		result = new ParsedResult();
		try {
			// Check if user is selecting a task
			if (isInteger(input)) {
				if (CommonInterpreterMethods.isValidSelection(input)) {
					updateUiSelection(input);
					return result;
				} else {
					SummaryReport
							.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
					throw new InvalidParameterException(
							Constants.MESSAGE_INVALID_SELECTION);
				}
			}

			// Processing main commands
			String[] seperatedInput = input.split("-");
			processMainCommand(seperatedInput);
			if (mainHandler.commandDoesNotRequireParam()) {
				if (mainHandler.getCommand() == CommandType.EXIT) {
					result.setIsExecutorApplicable(false);
				}
				return result;
			}
			updateResultsForMainCommand(seperatedInput);

			// Processing optional commands
			processOptionalCommandAndUpdateResults(seperatedInput);

			// Floating task
			if (result.getTaskDetails().getDueDate() == null) {
				result.getTaskDetails().setDueDate(Constants.SOMEDAY);

			}
		} catch (Exception e) {
			logger.info("Exception:" + e.toString());
			result.setIsExecutorApplicable(false);
			result.setCommandType(CommandType.INVALID);
		}
		return result;
	}

	private void updateResultsForMainCommand(String[] seperatedInput) {
		String remainingInput = mainHandler.removeCommandWord(seperatedInput[0]
				.trim());
		result = mainHandler.updateResults(result, remainingInput.trim());
	}

	private void processMainCommand(String[] seperatedInput) {
		String commandWord = CommonInterpreterMethods
				.getCommandWord(seperatedInput[0].trim());
		mainHandler.identifyAndSetCommand(commandWord.toLowerCase());
		result.setCommandType(mainHandler.getCommand());
	}

	private void updateUiSelection(String input) {
		SummaryReport.setRowIndexHighlight(Integer.valueOf(input) - 1);
		// Adjust value of input to get correct index
		result.setCommandType(CommandType.OTHERS);
		result.setIsExecutorApplicable(false);
	}

	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input, 10);
			// Radix 10 used to parse integer
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private void processOptionalCommandAndUpdateResults(String[] remainingInput) {
		String commandWord;

		for (int i = 1; i < remainingInput.length; i++) {
			commandWord = CommonInterpreterMethods
					.getCommandWord(remainingInput[i].trim());

			optionHandler.identifyAndSetCommand(commandWord.toLowerCase());

			remainingInput[i] = optionHandler
					.removeCommandWord(remainingInput[i].trim());

			result = optionHandler.updateResults(result,
					remainingInput[i].trim());

		}
	}

}
