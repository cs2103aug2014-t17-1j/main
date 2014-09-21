
public class ParsedResult {
	
	//Members
	private static CommandType parsedCommand;
	private static Task task;
	private static SearchType searchMode;
	
	//constructor
/*	public ParsedResults() {
		parsedCommand = null;
		task = new Task();
		searchMode = null;
	}
	
	public ParsedResults(CommandType setCommand) {
		parsedCommand = setCommand;
		task = new Task();
		searchMode = null;
	}
	
	public ParsedResults(CommandType setCommand, Task setTask) {
		parsedCommand = setCommand;
		task = setTask;
		searchMode = null;
	}
	
	public ParsedResults(CommandType setCommand, SearchType setSearch) {
		parsedCommand = setCommand;
		task = new Task();
		searchMode = setSearch;
	}
	
	public ParsedResults(CommandType setCommand, Task setTask, SearchType setSearch) {
		parsedCommand = setCommand;
		task = setTask;
		searchMode = setSearch;
	}
	
	*/
	//Accessors
	public static CommandType getCommandType() {
		return parsedCommand;
	}
	
	public static Task getTaskDetails() {
		return task;
	}
	
	public static SearchType getSearchMode() {
		return searchMode;
	}
	
	//Mutators
	public static void setCommandType(CommandType setCommand) {
		parsedCommand = setCommand;
	}
	
	public static void setSearchMode(SearchType setSearch) {
		searchMode = setSearch;
	}
	
	public static void clear() {
		parsedCommand = null;
		task = new Task();
		searchMode = null;
	}
}
