package commandFactory;

import parser.ParsedResult;
import commonClasses.StorageList;
import taskDo.History;
import taskDo.UpdateSummaryReport;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(ParsedResult parsedResult){
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		History.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		History.getUndoCommandHistory().push(CommandType.ADD);
		
		UpdateSummaryReport.updateByDueDate(parsedResult);
		UpdateSummaryReport.highlightTask(parsedResult.getTaskDetails().getId());
		StorageList.getInstance().saveToFile();	
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		UpdateSummaryReport.unhighlightTask();
		History.getRedoTaskHistory().push(parsedResult.getTaskDetails());
		History.getRedoCommandHistory().push(CommandType.ADD);
		StorageList.getInstance().getTaskList().remove(parsedResult.getTaskDetails());
		UpdateSummaryReport.updateByDueDate(parsedResult);
		StorageList.getInstance().saveToFile();	
	}
}
