package commandFactory;

import commonClasses.Constants;
import commonClasses.StorageList;
import taskDo.History;
import taskDo.Task;
import taskDo.UpdateSummaryReport;
import Parser.ParsedResult;

public class CommandActionComplete implements CommandAction{

	@Override
	public void execute(ParsedResult parsedResult) {
		Search targetTask = new Search();
		int taskIndex = targetTask.searchById(parsedResult.getTaskDetails().getId());
		if(taskIndex != Constants.NO_TASK){	
			History.getUndoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
			StorageList.getInstance().getTaskList().set(taskIndex, parsedResult.getTaskDetails());
		}
		
		UpdateSummaryReport.update(parsedResult);
	}

	@Override
	public void undo(ParsedResult parsedResult) {
		Search targetTask = new Search();
		Task lastTask = parsedResult.getTaskDetails();
		int taskIndex = targetTask.searchById(lastTask.getId());
		History.getRedoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
		StorageList.getInstance().getTaskList().set(taskIndex, lastTask);
		
		UpdateSummaryReport.update(parsedResult);
	}
}
