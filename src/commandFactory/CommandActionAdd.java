package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import commonClasses.StorageList;
import taskDo.History;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionAdd implements CommandAction {
	// @Author Huang Li A0112508R
	@Override
	public void execute(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();

		addIntoList(parsedResult, taskList);
		pushToUndoStacks(parsedResult, history);
		updateDisplayList(parsedResult, updateSR);
		saveIntoFile();
	}

	private void addIntoList(ParsedResult parsedResult, ArrayList<Task> taskList) {
		taskList.add(parsedResult.getTaskDetails());
	}

	private void pushToUndoStacks(ParsedResult parsedResult, History history) {
		history.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		history.getUndoCommandHistory().push(CommandType.ADD);
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();

		clearHighlight(updateSR);
		pushToRedoStacks(parsedResult, history);
		removeFromList(parsedResult, taskList);
		updateDisplayList(parsedResult, updateSR);
		saveIntoFile();
	}

	private void clearHighlight(UpdateSummaryReport updateSR) {
		updateSR.unhighlightTask();
	}

	private void removeFromList(ParsedResult parsedResult,
			ArrayList<Task> taskList) {
		taskList.remove(parsedResult.getTaskDetails());
	}

	private void pushToRedoStacks(ParsedResult parsedResult, History history) {
		history.getRedoTaskHistory().push(parsedResult.getTaskDetails());
		history.getRedoCommandHistory().push(CommandType.ADD);
	}

	private void updateDisplayList(ParsedResult parsedResult,
			UpdateSummaryReport updateSR) {
		updateSR.updateByDueDate(parsedResult);
		updateSR.highlightTask(parsedResult.getTaskDetails().getId());
	}

	private void saveIntoFile() {
		StorageList.getInstance().saveToFile();
	}
}
