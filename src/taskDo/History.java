package taskDo;

import java.util.Stack;

import commandFactory.CommandAction;

public class History {

	private static Stack<CommandAction> commandActionHistory = new Stack<CommandAction>();
	private static Stack<Task> taskHistory = new Stack<Task>();
	
	private History() {}

	public static Stack<CommandAction> getCommandActionHistory() {
		return commandActionHistory;
	}

	public static Stack<Task> getTaskHistory() {
		return taskHistory;
	}
}
