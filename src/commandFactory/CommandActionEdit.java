package commandFactory;

import commonClasses.StorageList;
import Parser.ParsedResult;
import taskDo.History;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionEdit implements CommandAction {	
	@Override
	public void execute(ParsedResult parsedResult){
		Search targetTask = new Search();
		int taskIndex = targetTask.searchById(parsedResult.getTaskDetails().getId());	
		History.getUndoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
		StorageList.getInstance().getTaskList().set(taskIndex, parsedResult.getTaskDetails());

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
