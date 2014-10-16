package commandFactory;

import commonClasses.StorageList;
import commonClasses.Constants;
import Parser.ParsedResult;
import taskDo.History;
import taskDo.Task;

public class CommandActionEdit implements CommandAction {	
	@Override
	public void execute(ParsedResult parsedResult){
		Search targetTask = new Search();
		targetTask.searchById(parsedResult.getTaskDetails().getId());
		if(targetTask.getTaskIndex() != Constants.NO_TASK){
			History.getTaskHistory().push(targetTask.getTask());
			StorageList.getInstance().getTaskList().remove(targetTask.getTaskIndex());
		}
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
	}

	@Override
	public void undo(Task lastTask) {
		Search targetTask = new Search();
		targetTask.searchById(lastTask.getId());
		StorageList.getInstance().getTaskList().remove(targetTask.getTaskIndex());
		StorageList.getInstance().getTaskList().add(lastTask);
	}
}
