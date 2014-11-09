package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import commonClasses.StorageList;
import commonClasses.SummaryReport;
import taskDo.History;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionComplete implements CommandAction{

	@Override
	public void execute(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		ArrayList<Task> displayList = new ArrayList<Task>();
		History history = History.getInstance();
		Search targetTask = new Search();
		
		updateSR.unhighlightTask();
		
		// find the index in storage task list
		int index = parsedResult.getTaskDetails().getId();
		int taskIndex = targetTask.searchById(index, taskList);

		history.getUndoTaskHistory().push(taskList.get(taskIndex));
		history.getUndoCommandHistory().push(CommandType.COMPLETED);
		
		// amend the display list
		if(parsedResult.getCommandType().equals(CommandType.REDO)){
			displayList = history.getRedoDisplayHistory().pop();
		}else{
			displayList = SummaryReport.getDisplayList();
		}
		history.getUndoDisplayHistory().push(displayList);
		
		updateSR.updateForDeleteAndComplete(parsedResult, displayList);
		
		// amend the storage list
		parsedResult.getTaskDetails().setCompleted(true);
		taskList.set(taskIndex, parsedResult.getTaskDetails());
		
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		ArrayList<Task> displayList = new ArrayList<Task>();
		Task lastTask = parsedResult.getTaskDetails();
		History history = History.getInstance();
		Search targetTask = new Search();
		
		int taskIndex = targetTask.searchById(lastTask.getId(), taskList);
		history.getRedoTaskHistory().push(taskList.get(taskIndex));
		
		lastTask.setCompleted(false);		
		taskList.set(taskIndex, lastTask);

		// display changed task list base on current list showing
		displayList = history.getUndoDisplayHistory().pop();
		updateSR.updateForUndoDeleteAndComplete(parsedResult, displayList);
		updateSR.highlightTask(lastTask.getId());
		
		// save displaying list for redo
		displayList = SummaryReport.getDisplayList();
		history.getRedoDisplayHistory().push(displayList);
		history.getRedoCommandHistory().push(CommandType.COMPLETED);
		
		StorageList.getInstance().saveToFile();	
	}
}
