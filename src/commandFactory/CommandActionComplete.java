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
		
		ArrayList<Task> displayList = new ArrayList<Task>();
		if(parsedResult.getCommandType().equals(CommandType.REDO)){
			displayList = History.getRedoDisplayHistory().pop();
		}
		else{
			displayList = SummaryReport.getDisplayList();
		}
		History.getUndoDisplayHistory().push(displayList);
		UpdateSummaryReport.updateForDeleteAndComplete(parsedResult, displayList);
		
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
		
		ArrayList<Task> displayList = History.getUndoDisplayHistory().pop();
		UpdateSummaryReport.updateForUndoDeleteAndComplete(parsedResult, displayList);
		displayList = SummaryReport.getDisplayList();
		History.getRedoDisplayHistory().push(displayList);
	}
}
