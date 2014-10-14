package commandFactory;

import commonClasses.StorageList;

import taskDo.ParsedResult;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(){
		StorageList.getInstance().getTaskList().add(ParsedResult.getTaskDetails());
	}
}
