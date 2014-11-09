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
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		updateSR.unhighlightTask();
		
		History history = History.getInstance();
		Search targetTask = new Search();
		
		int index = parsedResult.getTaskDetails().getId();
		int taskIndex = targetTask.searchById(index, StorageList.getInstance().getTaskList());

		history.getUndoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
		history.getUndoCommandHistory().push(CommandType.COMPLETED);
		
		ArrayList<Task> displayList = new ArrayList<Task>();
		if(parsedResult.getCommandType().equals(CommandType.REDO)){
			displayList = history.getRedoDisplayHistory().pop();
		}
		else{
			displayList = SummaryReport.getDisplayList();
		}
		history.getUndoDisplayHistory().push(displayList);
		updateSR.updateForDeleteAndComplete(parsedResult, displayList);
		
		parsedResult.getTaskDetails().setCompleted(true);
		StorageList.getInstance().getTaskList().set(taskIndex, parsedResult.getTaskDetails());
		
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		Task lastTask = parsedResult.getTaskDetails();
		History history = History.getInstance();
		Search targetTask = new Search();
		
		// change completed task back to uncompleted
		parsedResult.getTaskDetails().setCompleted(false);
		int taskIndex = targetTask.searchById(lastTask.getId(), taskList);
		
		history.getRedoTaskHistory().push(taskList.get(taskIndex));
		
		StorageList.getInstance().getTaskList().set(taskIndex, lastTask);
		
		// display changed task list base on current list showing
		ArrayList<Task> displayList = history.getUndoDisplayHistory().pop();
		updateSR.updateForUndoDeleteAndComplete(parsedResult, displayList);
		updateSR.highlightTask(parsedResult.getTaskDetails().getId());
		
		// save displaying list for redo
		displayList = SummaryReport.getDisplayList();
		history.getRedoDisplayHistory().push(displayList);
		history.getRedoCommandHistory().push(CommandType.COMPLETED);
		
		StorageList.getInstance().saveToFile();	
	}
}
