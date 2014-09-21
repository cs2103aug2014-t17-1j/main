
public class ParsedResults {
	
	//Members
	private CommandType parsedCommand;
	private Task task;
	private SearchType searchMode;
	
	//constructor
	public ParsedResults() {
		this.parsedCommand = null;
		this.task = new Task();
		this.searchMode = null;
	}
	
	public ParsedResults(CommandType setCommand) {
		this.parsedCommand = setCommand;
		this.task = new Task();
		this.searchMode = null;
	}
	
	public ParsedResults(CommandType setCommand, Task setTask) {
		this.parsedCommand = setCommand;
		this.task = setTask;
		this.searchMode = null;
	}
	
	public ParsedResults(CommandType setCommand, SearchType setSearch) {
		this.parsedCommand = setCommand;
		this.task = new Task();
		this.searchMode = setSearch;
	}
	
	public ParsedResults(CommandType setCommand, Task setTask, SearchType setSearch) {
		this.parsedCommand = setCommand;
		this.task = setTask;
		this.searchMode = setSearch;
	}
	
	
	//Accessors
	public CommandType getCommandType() {
		return this.parsedCommand;
	}
	
	public Task getTaskDetails() {
		return this.task;
	}
	
	public SearchType getSearchMode() {
		return this.searchMode;
	}
	
	//Mutators
	public void setCommandType(CommandType setCommand) {
		this.parsedCommand = setCommand;
	}
	
	public void setSearchMode(SearchType setSearch) {
		this.searchMode = setSearch;
	}
}
