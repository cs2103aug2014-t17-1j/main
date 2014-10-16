package taskDo;

import java.util.Stack;

import commandFactory.CommandType;

public class History {

	private static Stack<CommandType> commandHistory = new Stack<CommandType>();
	private static Stack<Task> taskHistory = new Stack<Task>();
	
	private History() {}

	public static Stack<CommandType> getCommandHistory() {
		return commandHistory;
	}

	public static Stack<Task> getTaskHistory() {
		return taskHistory;
	}
}
