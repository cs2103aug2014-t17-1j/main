package commandFactory;

import commonClasses.StorageList;
import Parser.ParsedResult;
import taskDo.History;
import taskDo.UpdateSummaryReport;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(ParsedResult parsedResult){
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		History.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		
		UpdateSummaryReport.updateByDueDate(parsedResult);
		UpdateSummaryReport.highlightTask(parsedResult.getTaskDetails().getId());
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		History.getRedoTaskHistory().push(parsedResult.getTaskDetails());
		StorageList.getInstance().getTaskList().remove(parsedResult.getTaskDetails());
		UpdateSummaryReport.updateByDueDate(parsedResult);
		UpdateSummaryReport.unhighlightTask();
	}
}
