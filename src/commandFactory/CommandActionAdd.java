package commandFactory;

import parser.ParsedResult;
import commonClasses.StorageList;
import taskDo.History;
import taskDo.UpdateSummaryReport;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(ParsedResult parsedResult){
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();
		
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		
		history.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		history.getUndoCommandHistory().push(CommandType.ADD);
		
		updateSR.updateByDueDate(parsedResult);
		updateSR.highlightTask(parsedResult.getTaskDetails().getId());
		
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		History history = History.getInstance();
		
		updateSR.unhighlightTask();
		
		history.getRedoTaskHistory().push(parsedResult.getTaskDetails());
		history.getRedoCommandHistory().push(CommandType.ADD);
		
		StorageList.getInstance().getTaskList().remove(parsedResult.getTaskDetails());
		
		updateSR.updateByDueDate(parsedResult);
		
		StorageList.getInstance().saveToFile();	
	}
}
