package commandFactory;

import taskDo.UpdateSummaryReport;
import commonClasses.StorageList;
import Parser.ParsedResult;
import taskDo.History;


public class CommandActionDelete implements CommandAction{	
	@Override
	public void execute(ParsedResult parsedResult){
		StorageList.getInstance().getTaskList().remove(parsedResult.getTaskDetails());
		History.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		
		UpdateSummaryReport.updateForDeleteAndComplete(parsedResult);
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		History.getRedoTaskHistory().push(parsedResult.getTaskDetails());
		
		UpdateSummaryReport.updateForUndoDeleteAndComplete(parsedResult);
	}
}
