package commandFactory;

import taskDo.History;
import taskDo.ParsedResult;
import taskDo.StorageList;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(){
		StorageList.getInstance().getTaskList().add(ParsedResult.getTaskDetails());
		
		History.getCommandHistory().push(CommandType.ADD);
		History.getTaskHistory().add(ParsedResult.getTaskDetails());
	}
}
