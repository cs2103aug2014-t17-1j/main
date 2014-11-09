package taskDo;

import java.util.ArrayList;
import java.util.Stack;

import commandFactory.CommandAction;
import commandFactory.CommandType;

public class History {
	private static History history;
	
	private Stack<ArrayList<Task>> undoDisplayHistory = new Stack<ArrayList<Task>>();
	private Stack<ArrayList<Task>> redoDisplayHistory = new Stack<ArrayList<Task>>();
	private Stack<CommandAction> undoActionHistory = new Stack<CommandAction>();
	private Stack<CommandAction> redoActionHistory = new Stack<CommandAction>();
	private Stack<CommandType> undoCommandHistory = new Stack<CommandType>();
	private Stack<CommandType> redoCommandHistory = new Stack<CommandType>();
	private Stack<Task> redoTaskHistory = new Stack<Task>();
	private Stack<Task> undoTaskHistory = new Stack<Task>();
	
	private History() {}

	public static History getInstance(){
		if(history == null){
			history = new History();
		}
		return history;
	}
	
	public Stack<CommandAction> getUndoActionHistory() {
		return undoActionHistory;
	}

	public Stack<Task> getUndoTaskHistory() {
		return undoTaskHistory;
	}

	public Stack<CommandAction> getRedoActionHistory() {
		return redoActionHistory;
	}

	public Stack<Task> getRedoTaskHistory() {
		return redoTaskHistory;
	}

	public Stack<ArrayList<Task>> getUndoDisplayHistory() {
		return undoDisplayHistory;
	}

	public Stack<ArrayList<Task>> getRedoDisplayHistory() {
		return redoDisplayHistory;
	}

	public Stack<CommandType> getUndoCommandHistory() {
		return undoCommandHistory;
	}

	public Stack<CommandType> getRedoCommandHistory() {
		return redoCommandHistory;
	}
}
