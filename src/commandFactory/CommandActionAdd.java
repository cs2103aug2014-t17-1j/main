package commandFactory;

import parser.ParsedResult;
import commonClasses.StorageList;
import taskDo.History;
import taskDo.UpdateSummaryReport;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(ParsedResult parsedResult){
		History history = History.getInstance();
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		history.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		history.getUndoCommandHistory().push(CommandType.ADD);
		
		UpdateSummaryReport.updateByDueDate(parsedResult);
		UpdateSummaryReport.highlightTask(parsedResult.getTaskDetails().getId());
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		History history = History.getInstance();
		UpdateSummaryReport.unhighlightTask();
		history.getRedoTaskHistory().push(parsedResult.getTaskDetails());
		history.getRedoCommandHistory().push(CommandType.ADD);
		StorageList.getInstance().getTaskList().remove(parsedResult.getTaskDetails());
		UpdateSummaryReport.updateByDueDate(parsedResult);
		StorageList.getInstance().saveToFile();	
	}
}
