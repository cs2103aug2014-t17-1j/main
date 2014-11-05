package commandFactory;

import java.util.ArrayList;

import taskDo.Task;
import taskDo.UpdateSummaryReport;
import commonClasses.StorageList;
import commonClasses.SummaryReport;
import Parser.ParsedResult;
import taskDo.History;


public class CommandActionDelete implements CommandAction{	
	@Override
	public void execute(ParsedResult parsedResult){
		UpdateSummaryReport.unhighlightTask();
		StorageList.getInstance().getTaskList().remove(parsedResult.getTaskDetails());
		
		ArrayList<Task> displayList = new ArrayList<Task>();
		if(parsedResult.getCommandType().equals(CommandType.REDO)){
			displayList = History.getRedoDisplayHistory().pop();
		}
		else{
			displayList = SummaryReport.getDisplayList();
		}
		History.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		History.getUndoDisplayHistory().push(displayList);
		UpdateSummaryReport.updateForDeleteAndComplete(parsedResult, displayList);
		
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		History.getRedoTaskHistory().push(parsedResult.getTaskDetails());

		ArrayList<Task> displayList = History.getUndoDisplayHistory().pop();
		UpdateSummaryReport.updateForUndoDeleteAndComplete(parsedResult, displayList);
		displayList = SummaryReport.getDisplayList();
		History.getRedoDisplayHistory().push(displayList);
		UpdateSummaryReport.highlightTask(parsedResult.getTaskDetails().getId());
		
		StorageList.getInstance().saveToFile();	
	}
}
