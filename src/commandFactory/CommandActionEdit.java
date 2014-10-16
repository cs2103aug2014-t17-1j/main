package commandFactory;


import commonClasses.StorageList;
import commonClasses.Constants;
import taskDo.History;
import taskDo.Task;
import Parser.ParsedResult;


public class CommandActionEdit implements CommandAction {	
	@Override
	public void execute(ParsedResult parsedResult){
		Search search = new Search();

		search.searchById(parsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != Constants.NO_TASK){
			StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
		}
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
	}

	@Override
	public void undo() {
		Task historyTask = History.getTaskHistory().pop();
		Search search = new Search();
		
		search.searchById(historyTask.getId());
		StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
		StorageList.getInstance().getTaskList().add(historyTask);
	}
}
