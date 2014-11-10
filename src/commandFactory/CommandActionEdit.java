package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import commonClasses.StorageList;
import commonClasses.SummaryReport;
import taskDo.History;
import taskDo.Search;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

/**
 * Edit action, to replace parsed task with task inside task list
 * accroding to taskID and task index
 * undo is to pop original task from stack and replace back
 *
 */
public class CommandActionEdit implements CommandAction {
	// @Author  A0112508R
	@Override
	public void execute(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();
		Search targetTask = new Search();

		int taskId = parsedResult.getTaskDetails().getId();
		int taskIndex = targetTask.searchById(taskId, taskList);

		pushToUndoStacks(taskList, history, taskIndex);

		taskList.set(taskIndex, parsedResult.getTaskDetails());

		ArrayList<Task> displayList = getDisplayList(parsedResult, history);
		history.getUndoDisplayHistory().push(displayList);

		replaceTaskInDisplayList(parsedResult, taskId, displayList);
		updateDisplayList(parsedResult, updateSR, displayList);
		history.getUndoDisplayHistory().push(displayList);

		StorageList.getInstance().saveToFile();
	}

	private void updateDisplayList(ParsedResult parsedResult,
			UpdateSummaryReport updateSR, ArrayList<Task> displayList) {
		updateSR.updateForEdit(parsedResult, displayList);
		updateSR.highlightTask(parsedResult.getTaskDetails().getId());
	}

	private void replaceTaskInDisplayList(ParsedResult parsedResult,
			int taskId, ArrayList<Task> displayList) {
		Search targetTask;
		int taskIndex;
		targetTask = new Search();
		taskIndex = targetTask.searchById(taskId, displayList);
		displayList.set(taskIndex, parsedResult.getTaskDetails());
	}

	private ArrayList<Task> getDisplayList(ParsedResult parsedResult,
			History history) {
		ArrayList<Task> displayList;
		if (parsedResult.getCommandType().equals(CommandType.REDO)) {
			displayList = history.getRedoDisplayHistory().pop();
		} else {
			displayList = SummaryReport.getDisplayList();
		}
		return displayList;
	}

	private void pushToUndoStacks(ArrayList<Task> taskList, History history,
			int taskIndex) {
		history.getUndoTaskHistory().push(taskList.get(taskIndex));
		history.getUndoCommandHistory().push(CommandType.EDIT);
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		Task lastTask = parsedResult.getTaskDetails();
		History history = History.getInstance();
		Search targetTask = new Search();

		int taskId = lastTask.getId();
		int taskIndex = targetTask.searchById(taskId, taskList);
		history.getRedoTaskHistory().push(taskList.get(taskIndex));

		taskList.set(taskIndex, lastTask);

		ArrayList<Task> displayList = updateDisplayList(lastTask, history,
				taskId);

		updateDisplayList(parsedResult, updateSR, displayList);
		pushToRedoStacks(displayList, history);

		StorageList.getInstance().saveToFile();
	}

	private ArrayList<Task> updateDisplayList(Task lastTask, History history,
			int taskId) {
		ArrayList<Task> displayList;
		Search targetTask;
		int taskIndex;

		displayList = history.getUndoDisplayHistory().pop();
		targetTask = new Search();
		taskIndex = targetTask.searchById(taskId, displayList);
		displayList.set(taskIndex, lastTask);
		return displayList;
	}

	private void pushToRedoStacks(ArrayList<Task> displayList, History history) {
		history.getRedoDisplayHistory().push(displayList);
		history.getRedoCommandHistory().push(CommandType.EDIT);
	}
}
