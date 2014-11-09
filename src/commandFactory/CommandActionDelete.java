package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import taskDo.Task;
import taskDo.UpdateSummaryReport;
import commonClasses.StorageList;
import commonClasses.SummaryReport;
import taskDo.History;


public class CommandActionDelete implements CommandAction{	
	@Override
	public void execute(ParsedResult parsedResult){
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		updateSR.unhighlightTask();
		History history = History.getInstance();
		
		StorageList.getInstance().getTaskList().remove(parsedResult.getTaskDetails());
		
		ArrayList<Task> displayList = new ArrayList<Task>();
		if(parsedResult.getCommandType().equals(CommandType.REDO)){
			displayList = history.getRedoDisplayHistory().pop();
		}
		else{
			displayList = SummaryReport.getDisplayList();
		}
		history.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		history.getUndoDisplayHistory().push(displayList);
		history.getUndoCommandHistory().push(CommandType.DELETE);
		updateSR.updateForDeleteAndComplete(parsedResult, displayList);
		
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();
		
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		history.getRedoTaskHistory().push(parsedResult.getTaskDetails());

		ArrayList<Task> displayList = history.getUndoDisplayHistory().pop();
		updateSR.updateForUndoDeleteAndComplete(parsedResult, displayList);
		displayList = SummaryReport.getDisplayList();
		history.getRedoDisplayHistory().push(displayList);
		history.getRedoCommandHistory().push(CommandType.DELETE);
		updateSR.highlightTask(parsedResult.getTaskDetails().getId());
		
		StorageList.getInstance().saveToFile();	
	}
}
