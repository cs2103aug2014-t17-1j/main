package commandFactory;

import java.util.ArrayList;

import commonClasses.StorageList;
import commonClasses.SummaryReport;
import taskDo.History;
import taskDo.Task;
import taskDo.UpdateSummaryReport;
import Parser.ParsedResult;

public class CommandActionComplete implements CommandAction{

	@Override
	public void execute(ParsedResult parsedResult) {
		Search targetTask = new Search();
		int taskIndex = targetTask.searchById(parsedResult.getTaskDetails().getId());

		History.getUndoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
		History.getDisplayHistory().push(SummaryReport.getDisplayList());
		UpdateSummaryReport.updateForDeleteAndComplete(parsedResult);
		
		parsedResult.getTaskDetails().setCompleted(true);
		StorageList.getInstance().getTaskList().set(taskIndex, parsedResult.getTaskDetails());

	}

	@Override
	public void undo(ParsedResult parsedResult) {
		Search targetTask = new Search();
		parsedResult.getTaskDetails().setCompleted(false);
		Task lastTask = parsedResult.getTaskDetails();
		int taskIndex = targetTask.searchById(lastTask.getId());
		History.getRedoTaskHistory().push(StorageList.getInstance().getTaskList().get(taskIndex));
		StorageList.getInstance().getTaskList().set(taskIndex, lastTask);
		
		ArrayList<Task> displayList = History.getDisplayHistory().pop();
		UpdateSummaryReport.updateForUndoDeleteAndComplete(parsedResult, displayList);
	}
}
