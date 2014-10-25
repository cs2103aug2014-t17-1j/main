package commandFactory;

import commonClasses.StorageList;
import commonClasses.Constants;
import Parser.ParsedResult;
import taskDo.History;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionDelete implements CommandAction{	
	@Override
	public void execute(ParsedResult parsedResult){
		Search search = new Search();

		search.searchById(parsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != Constants.NO_TASK){
			StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
			History.getUndoTaskHistory().push(parsedResult.getTaskDetails());
		}
		
		UpdateSummaryReport.update(parsedResult);
	}

	@Override
	public void undo(Task lastTask) {
		StorageList.getInstance().getTaskList().add(lastTask);
	}
}
