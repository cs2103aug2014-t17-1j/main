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
		ArrayList<Task> displayList = new ArrayList<Task>();
		History history = History.getInstance();
		
		updateSR.unhighlightTask();
		
		StorageList.getInstance().getTaskList().remove(parsedResult.getTaskDetails());
		
		if(parsedResult.getCommandType().equals(CommandType.REDO)){
			displayList = history.getRedoDisplayHistory().pop();
		}else{
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
		ArrayList<Task> displayList = new ArrayList<Task>();
		History history = History.getInstance();
		
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		history.getRedoTaskHistory().push(parsedResult.getTaskDetails());

		displayList = history.getUndoDisplayHistory().pop();
		updateSR.updateForUndoDeleteAndComplete(parsedResult, displayList);
		updateSR.highlightTask(parsedResult.getTaskDetails().getId());
		
		displayList = SummaryReport.getDisplayList();
		history.getRedoDisplayHistory().push(displayList);
		history.getRedoCommandHistory().push(CommandType.DELETE);
		
		StorageList.getInstance().saveToFile();	
	}
}
