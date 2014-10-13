package taskDo;

import java.util.ArrayList;
import java.util.Stack;

import commandFactory.CommandType;

public class History {

	private static Stack<CommandType> commandHistory = new Stack<CommandType>();
	private static Stack<ArrayList<Task>> taskHistory = new Stack<ArrayList<Task>>();
	
	private History() {}

	public static Stack<CommandType> getCommandHistory() {
		return commandHistory;
	}

	public static Stack<ArrayList<Task>> getTaskHistory() {
		return taskHistory;
	}
}
