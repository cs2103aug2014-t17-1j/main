package commandFactory;

import commonClasses.StorageList;
import commonClasses.Constants;
import taskDo.ParsedResult;

public class CommandActionDelete implements CommandAction{	
	@Override
	public void execute(){		
		Search search = new Search();
		search.searchById(ParsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != Constants.NO_TASK){
			StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
		}
	}
}
