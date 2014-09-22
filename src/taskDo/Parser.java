package taskDo;

import commandFactory.CommandType;


public class Parser {
	
	private static final int PARAM_STARTING_INDEX = 1;
	
	enum OptionalCommand {
		DUE, FROM, TO, CATEGORY, IMPT, INVALID
	}
	public static void parserInit() {
		
	}
	
	public static void parseString(String input) {
		resetParsedResult();
		String commandWord = getCommandWord(input);
		CommandType command = identifyCommand(commandWord.toLowerCase());
		String remainingInput = removeCommandWord(input, command);
		
		String commandParam = getParam(remainingInput);
		remainingInput = removeParam(remainingInput);
		
		updateParsedResult(command,commandParam);

		while(remainingInput.isEmpty() == false) {
			remainingInput = identifyOptionalCommandAndUpdate(remainingInput);
		}
		System.out.println(commandWord);
		System.out.println(commandParam);
		System.out.println(remainingInput);
	}

	private static String identifyOptionalCommandAndUpdate(String remainingInput) {
		String commandWord = getCommandWord(remainingInput);
		
		OptionalCommand command = identifyOptionalCommand(commandWord.toLowerCase());
		remainingInput = removeOptionalCommand(remainingInput, command);
		String commandParam = getParam(remainingInput);
		optionsUpdateParsedResult(command,commandParam);
		
		remainingInput = removeParam(remainingInput);
		return remainingInput;
	}

	private static void optionsUpdateParsedResult(OptionalCommand command,
			String commandParam) {
		
		
	}

	private static String removeOptionalCommand(String remainingInput,
			OptionalCommand commandWord) {
		
		switch(commandWord) {
		
			case DUE:
				return remainingInput.substring(4); //Length of word "due "
				
			case FROM:
				return remainingInput.substring(5);
				
			case TO:
				return remainingInput.substring(3);
				
			case IMPT:
				return remainingInput.substring(5);
			
			case INVALID:
				return "INVALID!";
				
			default:
				return "";
		}
	}

	private static OptionalCommand identifyOptionalCommand(String commandWord) {
		
		switch(commandWord) {
			
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
				
			default:
				return OptionalCommand.INVALID;
		}
		
	}

	private static void updateParsedResult(CommandType command,
			String commandParam) {
		ParsedResult.setCommandType(command);
		Task task = ParsedResult.getTaskDetails();
		task.setDescription(commandParam);
		//Need to set endDueDate with some constant(No deadline)
	}

	private static void resetParsedResult() {
		ParsedResult.clear();
		
	}

	private static String removeParam(String remainingInput) {
		int indexEndOfParam = remainingInput.indexOf(']');
		
		//Adjust index to take substring after ']'
		indexEndOfParam++;
		remainingInput = remainingInput.substring(indexEndOfParam);
		
		return remainingInput.trim();
	}

	private static String getCommandWord(String input) {
		String[] splittedCommand = input.split(" ");
		
		return splittedCommand[0];
	}
	
	private static CommandType identifyCommand(String command) {

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
		
		//not sure if I need init and save to be here
		default:
			return CommandType.INVALID;
		}
	}
	private static String removeCommandWord(String input,
			CommandType commandWord) {
		switch (commandWord) {
		case ADD:
			return input.substring(4); //4 is length of word "add "
	
		case DISPLAY:
			return input.substring(8); //8 is length of word "display "
			
		case DELETE:
			return input.substring(7); //7 is length of word "delete "
		
		case SEARCH:
			return input.substring(7); //7 is length of word "search "
		
		case EDIT:
			return input.substring(5);
			
		case INVALID:
			return "INVALID!";
			
		default:
			return "";
			
		}
		
	}
	
	private static String getParam(String remainingInput) {
		int indexEndOfParam = remainingInput.indexOf(']');
		
		return remainingInput.substring(PARAM_STARTING_INDEX, indexEndOfParam);
	}
}
