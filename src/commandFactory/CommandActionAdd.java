package commandFactory;

import taskDo.History;
import commonClasses.StorageList;
import Parser.ParsedResult;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(ParsedResult parsedResult){
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
	}

	@Override
	public void undo() {
		Search search = new Search();
		search.searchById(History.getTaskHistory().pop().getId());
		StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
	}
}
