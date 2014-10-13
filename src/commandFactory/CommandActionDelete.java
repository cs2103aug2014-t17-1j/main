package commandFactory;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.StringConstants;

public class CommandActionDelete implements CommandAction{	
	@Override
	public void execute(){		
		Search search = new Search();
		search.searchById(ParsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != StringConstants.NO_TASK){
			StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
		}
	}
}
