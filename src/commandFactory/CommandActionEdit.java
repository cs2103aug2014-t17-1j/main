package commandFactory;

import java.util.ArrayList;

import commonClasses.StorageList;
import commonClasses.SummaryReport;
import Parser.ParsedResult;
import taskDo.History;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionEdit implements CommandAction {	
	@Override
	public void execute(ParsedResult parsedResult){
		Search targetTask = new Search();
		int taskId = parsedResult.getTaskDetails().getId();
		int taskIndex = targetTask.searchById(taskId, StorageList.getInstance().getTaskList());	
		History.getUndoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
		History.getUndoCommandHistory().push(CommandType.EDIT);
		StorageList.getInstance().getTaskList().set(taskIndex, parsedResult.getTaskDetails());

		ArrayList<Task> displayList = new ArrayList<Task>();
		if(parsedResult.getCommandType().equals(CommandType.REDO)){
			displayList = History.getRedoDisplayHistory().pop();
		}else{
			displayList = SummaryReport.getDisplayList();
		}
		History.getUndoDisplayHistory().push(displayList);
		targetTask = new Search();
		taskIndex = targetTask.searchById(taskId, displayList);	
		displayList.set(taskIndex, parsedResult.getTaskDetails());
		UpdateSummaryReport.updateForEdit(parsedResult, displayList);
		UpdateSummaryReport.highlightTask(parsedResult.getTaskDetails().getId());
		History.getUndoDisplayHistory().push(displayList);
		
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		Search targetTask = new Search();
		Task lastTask = parsedResult.getTaskDetails();
		int taskId = lastTask.getId();
		int taskIndex = targetTask.searchById(taskId, StorageList.getInstance().getTaskList());
		History.getRedoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
		StorageList.getInstance().getTaskList().set(taskIndex, lastTask);

		ArrayList<Task> displayList = History.getUndoDisplayHistory().pop();
		targetTask = new Search();
		taskIndex = targetTask.searchById(taskId, displayList);	
		displayList.set(taskIndex, lastTask);
		UpdateSummaryReport.updateForEdit(parsedResult, displayList);
		UpdateSummaryReport.highlightTask(parsedResult.getTaskDetails().getId());
		History.getRedoDisplayHistory().push(displayList);
		History.getRedoCommandHistory().push(CommandType.EDIT);
		
		StorageList.getInstance().saveToFile();	
	}
}
