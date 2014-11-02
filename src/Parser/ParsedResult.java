package Parser;

import taskDo.SearchType;
import taskDo.Task;
import commandFactory.CommandType;
import commonClasses.Constants;



public class ParsedResult {
	
	//Members
	private CommandType parsedCommand;
	private Task task;
	private SearchType searchMode;
	private boolean isExecutorApplicable;
	
	public ParsedResult() {
		this.parsedCommand = null;
		this.task = new Task();
		this.searchMode = null;
		this.isExecutorApplicable = true;
	}
	
	
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
	public CommandType getCommandType() {
		return this.parsedCommand;
	}
	
	public Task getTaskDetails() {
		return this.task;
	}
	
	public SearchType getSearchMode() {
		return this.searchMode;
	}
	
	public boolean getIsExecutorApplicable() {
		return this.isExecutorApplicable;
	}
	
	//Mutators
	public void setCommandType(CommandType setCommand) {
		this.parsedCommand = setCommand;
	}
	
	public void setSearchMode(SearchType setSearch) {
		this.searchMode = setSearch;
	}
	
	public void setTask(Task setTask) {
		this.task = setTask;
	}
	
	public void setIsExecutorApplicable(boolean validation) {
		this.isExecutorApplicable = validation;
	}
}
