package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import taskDo.Task;
import taskDo.UpdateSummaryReport;
import commonClasses.StorageList;
import commonClasses.SummaryReport;
import taskDo.History;

/**
 * Delete action, remove task into task list
 * undo is to add task back
 *
 */
public class CommandActionDelete implements CommandAction {
	// @Author  A0112508R
	@Override
	public void execute(ParsedResult parsedResult) {
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		ArrayList<Task> displayList = new ArrayList<Task>();
		History history = History.getInstance();

		updateSR.unhighlightTask();

		removeFromTaskList(parsedResult);

		displayList = getDisplayList(parsedResult, history);
		pushToUndoStacks(parsedResult, displayList, history);

		updateSR.updateForDeleteAndComplete(parsedResult, displayList);

		saveIntoFile();
	}

	private void removeFromTaskList(ParsedResult parsedResult) {
		StorageList.getInstance().getTaskList()
				.remove(parsedResult.getTaskDetails());
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

	private void pushToUndoStacks(ParsedResult parsedResult,
			ArrayList<Task> displayList, History history) {
		history.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		history.getUndoDisplayHistory().push(displayList);
		history.getUndoCommandHistory().push(CommandType.DELETE);
	}

	private void saveIntoFile() {
		StorageList.getInstance().saveToFile();
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();

		addIntoTaskList(parsedResult);
		history.getRedoTaskHistory().push(parsedResult.getTaskDetails());

		updateDisplayList(parsedResult, updateSR, history);
		pushToRedoStacks(history);

		saveIntoFile();
	}

	private void pushToRedoStacks(History history) {
		ArrayList<Task> displayList;
		displayList = SummaryReport.getDisplayList();
		history.getRedoDisplayHistory().push(displayList);
		history.getRedoCommandHistory().push(CommandType.DELETE);
	}

	private void updateDisplayList(ParsedResult parsedResult,
			UpdateSummaryReport updateSR, History history) {
		ArrayList<Task> displayList;
		displayList = history.getUndoDisplayHistory().pop();
		updateSR.updateForUndoDeleteAndComplete(parsedResult, displayList);
		updateSR.highlightTask(parsedResult.getTaskDetails().getId());
	}

	private void addIntoTaskList(ParsedResult parsedResult) {
		StorageList.getInstance().getTaskList()
				.add(parsedResult.getTaskDetails());
	}
}
