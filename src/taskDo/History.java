package taskDo;

import java.util.ArrayList;
import java.util.Stack;

import commandFactory.CommandAction;
import commandFactory.CommandType;

public class History {

	private static Stack<CommandAction> undoActionHistory = new Stack<CommandAction>();
	private static Stack<Task> undoTaskHistory = new Stack<Task>();
	
	private static Stack<CommandAction> redoActionHistory = new Stack<CommandAction>();
	private static Stack<Task> redoTaskHistory = new Stack<Task>();
	
	private static Stack<ArrayList<Task>> undoDisplayHistory = new Stack<ArrayList<Task>>();

	private static Stack<ArrayList<Task>> redoDisplayHistory = new Stack<ArrayList<Task>>();
	
	private static Stack<CommandType> undoCommandHistory = new Stack<CommandType>();
	
	private static Stack<CommandType> redoCommandHistory = new Stack<CommandType>();
	
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

	public static Stack<ArrayList<Task>> getUndoDisplayHistory() {
		return undoDisplayHistory;
	}

	public static Stack<ArrayList<Task>> getRedoDisplayHistory() {
		return redoDisplayHistory;
	}

	public static Stack<CommandType> getUndoCommandHistory() {
		return undoCommandHistory;
	}

	public static Stack<CommandType> getRedoCommandHistory() {
		return redoCommandHistory;
	}
}
