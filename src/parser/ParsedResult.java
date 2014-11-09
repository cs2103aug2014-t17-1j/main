package parser;

import taskDo.SearchType;
import taskDo.Task;

import commandFactory.CommandType;

public class ParsedResult {

	// @author Boo Tai Yi A0111936J
	// Members
	private CommandType parsedCommand;
	private Task task;
	private SearchType searchMode;
	private boolean isExecutorApplicable;
	private int selectedItem;

	public ParsedResult() {
		this.parsedCommand = null;
		this.task = new Task();
		this.searchMode = null;
		this.isExecutorApplicable = true;
	}

	// Accessors
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

	public int getSelectedItem() {
		return selectedItem;
	}

	// Mutators
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

	public void setSelectedItem(int selectedItem) {
		this.selectedItem = selectedItem;
	}
}
