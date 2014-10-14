package commandFactory;

import taskDo.History;
import Parser.ParsedResult;
import taskDo.StorageList;
import taskDo.StringConstants;

public class CommandActionDelete implements CommandAction{	
	@Override
	public void execute(){		
		ParsedResult parsedResult = new ParsedResult();
		Search search = new Search();
		search.searchById(parsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != StringConstants.NO_TASK){
			StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
			
			History.getCommandHistory().push(CommandType.DELETE);
			History.getTaskHistory().add(search.getTask());
		}
	}
}
