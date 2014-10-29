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
		StorageList.getInstance().getTaskList().remove(parsedResult.getTaskDetails());
		History.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		History.getDisplayHistory().push(SummaryReport.getDisplayList());
		
		UpdateSummaryReport.updateForDeleteAndComplete(parsedResult);
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		History.getRedoTaskHistory().push(parsedResult.getTaskDetails());
		
		ArrayList<Task> displayList = History.getDisplayHistory().pop();
		UpdateSummaryReport.updateForUndoDeleteAndComplete(parsedResult, displayList);
	}
}
