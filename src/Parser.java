
public class Parser {
	
	public static void parserInit() {
		parseString("add 123");
	}
	
	public static void parseString(String input) {
		String commandWord = getCommandWord(input);
		CommandType command = identifyCommand(commandWord.toLowerCase());
		String remainingInput = removeCommandWord(input, command);
		
		System.out.println(commandWord);
		System.out.println(remainingInput);
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
		}
		
		return ""; //by default return nothing
	}
}
