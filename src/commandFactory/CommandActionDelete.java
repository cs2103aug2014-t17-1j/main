package commandFactory;

import taskDo.UpdateSummaryReport;
import commonClasses.StorageList;
import Parser.ParsedResult;
import taskDo.History;
import taskDo.Task;


public class CommandActionDelete implements CommandAction{	
	@Override
	public void execute(ParsedResult parsedResult){
		StorageList.getInstance().getTaskList().remove(parsedResult.getTaskDetails());
		History.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		
		UpdateSummaryReport.update(parsedResult);
	}

	@Override
	public void undo(Task lastTask) {
		StorageList.getInstance().getTaskList().add(lastTask);
		History.getRedoTaskHistory().push(lastTask);
	}
}
