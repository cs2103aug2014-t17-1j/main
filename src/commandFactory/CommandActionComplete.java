package commandFactory;

import commonClasses.Constants;
import commonClasses.StorageList;
import taskDo.History;
import taskDo.Task;
import Parser.ParsedResult;

public class CommandActionComplete implements CommandAction{

	@Override
	public void execute(ParsedResult parsedResult) {
		Search targetTask = new Search();
		targetTask.searchById(parsedResult.getTaskDetails().getId());
		if(targetTask.getTaskIndex() != Constants.NO_TASK){
			int index = targetTask.getTaskIndex();			
			History.getTaskHistory().push(StorageList.getInstance().getTaskList().get(index));
			StorageList.getInstance().getTaskList().remove(index);
			StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		}
	}

	@Override
	public void undo(Task lastTask) {
		Search targetTask = new Search();
		targetTask.searchById(lastTask.getId());
		StorageList.getInstance().getTaskList().remove(targetTask.getTaskIndex());
		StorageList.getInstance().getTaskList().add(lastTask);
	}
}
