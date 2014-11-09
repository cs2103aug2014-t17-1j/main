package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import commonClasses.StorageList;
import commonClasses.SummaryReport;
import taskDo.History;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionEdit implements CommandAction {	
	@Override
	public void execute(ParsedResult parsedResult){
		History history = History.getInstance();
		Search targetTask = new Search();
		
		int taskId = parsedResult.getTaskDetails().getId();
		int taskIndex = targetTask.searchById(taskId, StorageList.getInstance().getTaskList());	
		history.getUndoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
		history.getUndoCommandHistory().push(CommandType.EDIT);
		StorageList.getInstance().getTaskList().set(taskIndex, parsedResult.getTaskDetails());

		ArrayList<Task> displayList = new ArrayList<Task>();
		if(parsedResult.getCommandType().equals(CommandType.REDO)){
			displayList = history.getRedoDisplayHistory().pop();
		}else{
			displayList = SummaryReport.getDisplayList();
		}
		history.getUndoDisplayHistory().push(displayList);
		targetTask = new Search();
		taskIndex = targetTask.searchById(taskId, displayList);	
		displayList.set(taskIndex, parsedResult.getTaskDetails());
		UpdateSummaryReport.updateForEdit(parsedResult, displayList);
		UpdateSummaryReport.highlightTask(parsedResult.getTaskDetails().getId());
		history.getUndoDisplayHistory().push(displayList);
		
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		
		History history = History.getInstance();
		Search targetTask = new Search();
		
		Task lastTask = parsedResult.getTaskDetails();
		int taskId = lastTask.getId();
		int taskIndex = targetTask.searchById(taskId, StorageList.getInstance().getTaskList());
		history.getRedoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
		StorageList.getInstance().getTaskList().set(taskIndex, lastTask);

		ArrayList<Task> displayList = history.getUndoDisplayHistory().pop();
		targetTask = new Search();
		taskIndex = targetTask.searchById(taskId, displayList);	
		displayList.set(taskIndex, lastTask);
		UpdateSummaryReport.updateForEdit(parsedResult, displayList);
		UpdateSummaryReport.highlightTask(parsedResult.getTaskDetails().getId());
		history.getRedoDisplayHistory().push(displayList);
		history.getRedoCommandHistory().push(CommandType.EDIT);
		
		StorageList.getInstance().saveToFile();	
	}
}
