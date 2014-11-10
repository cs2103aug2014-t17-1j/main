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
 * complete task action, set complete as yes inside taskDetails
 * undo is to set complete back to no
 *
 */
public class CommandActionComplete implements CommandAction {
	// @Author  A0112508R
	@Override
	public void execute(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();
		Search targetTask = new Search();

		updateSR.unhighlightTask();

		int taskIndex = findTaskIndex(parsedResult, taskList, targetTask);

		pushToUndoStacks(taskList, history, taskIndex);
		updateDisplayList(parsedResult, updateSR, history);
		updateTaskList(parsedResult, taskList, taskIndex);

		saveIntoFile();
	}

	private int findTaskIndex(ParsedResult parsedResult,
			ArrayList<Task> taskList, Search targetTask) {
		int index = parsedResult.getTaskDetails().getId();
		int taskIndex = targetTask.searchById(index, taskList);
		return taskIndex;
	}

	private void saveIntoFile() {
		StorageList.getInstance().saveToFile();
	}

	private void updateTaskList(ParsedResult parsedResult,
			ArrayList<Task> taskList, int taskIndex) {
		parsedResult.getTaskDetails().setCompleted(true);
		taskList.set(taskIndex, parsedResult.getTaskDetails());
	}

	private void updateDisplayList(ParsedResult parsedResult,
			UpdateSummaryReport updateSR, History history) {
		ArrayList<Task> displayList;
		if (parsedResult.getCommandType().equals(CommandType.REDO)) {
			displayList = history.getRedoDisplayHistory().pop();
		} else {
			displayList = SummaryReport.getDisplayList();
		}
		history.getUndoDisplayHistory().push(displayList);

		updateSR.updateForDeleteAndComplete(parsedResult, displayList);
	}

	private void pushToUndoStacks(ArrayList<Task> taskList, History history,
			int taskIndex) {
		history.getUndoTaskHistory().push(taskList.get(taskIndex));
		history.getUndoCommandHistory().push(CommandType.COMPLETED);
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		Task lastTask = parsedResult.getTaskDetails();
		History history = History.getInstance();
		Search targetTask = new Search();

		int taskIndex = findLastTaskIndex(taskList, lastTask, targetTask);

		history.getRedoTaskHistory().push(taskList.get(taskIndex));

		updateTaskList(taskList, lastTask, taskIndex);
		updateDisplayList(parsedResult, updateSR, lastTask, history);
		pushDisplayListForRedo(history);

		saveIntoFile();
	}

	private void pushDisplayListForRedo(History history) {
		ArrayList<Task> displayList;
		displayList = SummaryReport.getDisplayList();
		history.getRedoDisplayHistory().push(displayList);
		history.getRedoCommandHistory().push(CommandType.COMPLETED);
	}

	private void updateDisplayList(ParsedResult parsedResult,
			UpdateSummaryReport updateSR, Task lastTask, History history) {
		ArrayList<Task> displayList;
		displayList = history.getUndoDisplayHistory().pop();
		updateSR.updateForUndoDeleteAndComplete(parsedResult, displayList);
		updateSR.highlightTask(lastTask.getId());
	}

	private void updateTaskList(ArrayList<Task> taskList, Task lastTask,
			int taskIndex) {
		lastTask.setCompleted(false);
		taskList.set(taskIndex, lastTask);
	}

	private int findLastTaskIndex(ArrayList<Task> taskList, Task lastTask,
			Search targetTask) {
		int index = lastTask.getId();
		int taskIndex = targetTask.searchById(index, taskList);
		return taskIndex;
	}
}
