package commandFactory;


import taskDo.History;
import Parser.ParsedResult;
import taskDo.StorageList;

public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(){
		ParsedResult parsedResult = new ParsedResult();
		StorageList.getInstance().getTaskList().add(parsedResult.getTaskDetails());
		
		History.getCommandHistory().push(CommandType.ADD);
		History.getTaskHistory().add(parsedResult.getTaskDetails());
	}
}
