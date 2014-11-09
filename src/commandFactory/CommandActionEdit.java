package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import commonClasses.StorageList;
import commonClasses.SummaryReport;
import taskDo.History;
import taskDo.Search;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionEdit implements CommandAction {	
	@Override
	public void execute(ParsedResult parsedResult){
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();
		Search targetTask = new Search();
		
		// push command and task into undo stacks
		int taskId = parsedResult.getTaskDetails().getId();
		int taskIndex = targetTask.searchById(taskId, taskList);	
		history.getUndoTaskHistory().push(taskList.get(taskIndex));
		history.getUndoCommandHistory().push(CommandType.EDIT);
		
		// amend task list
		taskList.set(taskIndex, parsedResult.getTaskDetails());

		// amend display list
		ArrayList<Task> displayList = new ArrayList<Task>();
		if(parsedResult.getCommandType().equals(CommandType.REDO)){
			displayList = history.getRedoDisplayHistory().pop();
		}else{
			displayList = SummaryReport.getDisplayList();
		}
		history.getUndoDisplayHistory().push(displayList);
		
		// replace task in display list
		targetTask = new Search();
		taskIndex = targetTask.searchById(taskId, displayList);	
		displayList.set(taskIndex, parsedResult.getTaskDetails());
		
		updateSR.updateForEdit(parsedResult, displayList);
		updateSR.highlightTask(parsedResult.getTaskDetails().getId());
		
		history.getUndoDisplayHistory().push(displayList);
		
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
		
		// amend task list
		int taskId = lastTask.getId();
		int taskIndex = targetTask.searchById(taskId, taskList);
		history.getRedoTaskHistory().push(taskList.get(taskIndex));
		
		taskList.set(taskIndex, lastTask);

		// amend display list
		displayList = history.getUndoDisplayHistory().pop();
		targetTask = new Search();
		taskIndex = targetTask.searchById(taskId, displayList);	
		displayList.set(taskIndex, lastTask);
		
		updateSR.updateForEdit(parsedResult, displayList);
		updateSR.highlightTask(parsedResult.getTaskDetails().getId());
		
		history.getRedoDisplayHistory().push(displayList);
		history.getRedoCommandHistory().push(CommandType.EDIT);
		
		StorageList.getInstance().saveToFile();	
	}
}
