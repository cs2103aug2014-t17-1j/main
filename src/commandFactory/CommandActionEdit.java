package commandFactory;


import commonClasses.StorageList;
import commonClasses.Constants;
import taskDo.History;
import Parser.ParsedResult;


public class CommandActionEdit implements CommandAction {	
	@Override
	public void execute(ParsedResult parsedResult){
		Search search = new Search();

		search.searchById(parsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != Constants.NO_TASK){

			StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
			
			History.getCommandHistory().push(CommandType.EDIT);
			History.getTaskHistory().add(search.getTask());
		}
		
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
	}
}
