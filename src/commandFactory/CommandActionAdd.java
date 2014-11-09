package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import commonClasses.StorageList;
import taskDo.History;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(ParsedResult parsedResult){
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();
		
		taskList.add(parsedResult.getTaskDetails());
		
		history.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		history.getUndoCommandHistory().push(CommandType.ADD);
		
		updateSR.updateByDueDate(parsedResult);
		updateSR.highlightTask(parsedResult.getTaskDetails().getId());
		
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();
		
		updateSR.unhighlightTask();
		
		history.getRedoTaskHistory().push(parsedResult.getTaskDetails());
		history.getRedoCommandHistory().push(CommandType.ADD);
		
		taskList.remove(parsedResult.getTaskDetails());
		
		updateSR.updateByDueDate(parsedResult);
		
		StorageList.getInstance().saveToFile();	
	}
}
