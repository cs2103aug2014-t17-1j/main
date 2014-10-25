package commandFactory;

import commonClasses.StorageList;
import Parser.ParsedResult;
import taskDo.History;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(ParsedResult parsedResult){
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		History.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		
		UpdateSummaryReport.update(parsedResult);
	}

	@Override
	public void undo(Task lastTask) {
		Search search = new Search();
		int taskIndex = search.searchById(lastTask.getId());
		StorageList.getInstance().getTaskList().remove(taskIndex);
	}
}
