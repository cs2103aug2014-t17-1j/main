package commandFactory;

import commonClasses.StorageList;
import commonClasses.StringConstants;
import taskDo.ParsedResult;

public class CommandActionEdit implements CommandAction {	
	@Override
	public void execute(){
		Search search = new Search();
		search.searchById(ParsedResult.getTaskDetails().getId());
		if(search.getTaskIndex() != StringConstants.NO_TASK){
			StorageList.getInstance().getTaskList().remove(search.getTaskIndex());
		}
		
		StorageList.getInstance().getTaskList().add(ParsedResult.getTaskDetails());
	}
}
