package taskDo;

import java.util.Stack;

import commandFactory.CommandAction;

public class History {

	private static Stack<CommandAction> undoActionHistory = new Stack<CommandAction>();
	private static Stack<Task> undoTaskHistory = new Stack<Task>();
	
	private static Stack<CommandAction> redoActionHistory = new Stack<CommandAction>();
	private static Stack<Task> redoTaskHistory = new Stack<Task>();
	
	private History() {}

	public static Stack<CommandAction> getUndoActionHistory() {
		return undoActionHistory;
	}

	public static Stack<Task> getUndoTaskHistory() {
		return undoTaskHistory;
	}

	public static Stack<CommandAction> getRedoActionHistory() {
		return redoActionHistory;
	}

	public static Stack<Task> getRedoTaskHistory() {
		return redoTaskHistory;
	}
}
