package commandFactory;

import commonClasses.StorageList;
import Parser.ParsedResult;
import taskDo.History;
import taskDo.Task;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(ParsedResult parsedResult){
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		History.getTaskHistory().push(parsedResult.getTaskDetails());
	}

	@Override
	public void undo(Task lastTask) {
		Search search = new Search();
		search.searchById(lastTask.getId());
		StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
	}
}
