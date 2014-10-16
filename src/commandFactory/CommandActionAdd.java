package commandFactory;


import commonClasses.StorageList;
import taskDo.History;
import Parser.ParsedResult;


public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(ParsedResult parsedResult){
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		
		History.getCommandHistory().push(CommandType.ADD);
		History.getTaskHistory().add(parsedResult.getTaskDetails());
	}
}
