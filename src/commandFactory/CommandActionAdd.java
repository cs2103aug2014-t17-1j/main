package commandFactory;

import Parser.ParsedResult;
import taskDo.StorageList;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(){
		StorageList.getInstance().getTaskList().add(ParsedResult.getTaskDetails());
	}
}
