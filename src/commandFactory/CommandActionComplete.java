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
			StorageList.getInstance().getTaskList().remove(taskIndex);
			StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		}
		
		UpdateSummaryReport.update(parsedResult);
	}

	@Override
	public void undo(Task lastTask) {
		Search targetTask = new Search();
		int taskIndex = targetTask.searchById(lastTask.getId());
		StorageList.getInstance().getTaskList().remove(taskIndex);
		StorageList.getInstance().getTaskList().add(lastTask);
	}
}
