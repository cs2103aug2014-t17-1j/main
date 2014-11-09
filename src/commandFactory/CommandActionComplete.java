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
		UpdateSummaryReport.unhighlightTask();
		
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
		UpdateSummaryReport.updateForDeleteAndComplete(parsedResult, displayList);
		
		parsedResult.getTaskDetails().setCompleted(true);
		StorageList.getInstance().getTaskList().set(taskIndex, parsedResult.getTaskDetails());
		
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		
		History history = History.getInstance();
		Search targetTask = new Search();
		
		parsedResult.getTaskDetails().setCompleted(false);
		Task lastTask = parsedResult.getTaskDetails();
		int taskIndex = targetTask.searchById(lastTask.getId(), StorageList.getInstance().getTaskList());
		history.getRedoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
		StorageList.getInstance().getTaskList().set(taskIndex, lastTask);
		
		ArrayList<Task> displayList = history.getUndoDisplayHistory().pop();
		UpdateSummaryReport.updateForUndoDeleteAndComplete(parsedResult, displayList);
		UpdateSummaryReport.highlightTask(parsedResult.getTaskDetails().getId());
		displayList = SummaryReport.getDisplayList();
		history.getRedoDisplayHistory().push(displayList);
		history.getRedoCommandHistory().push(CommandType.COMPLETED);
		
		StorageList.getInstance().saveToFile();	
	}
}
