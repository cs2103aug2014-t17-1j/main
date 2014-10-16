package commandFactory;

import Parser.ParsedResult;
import taskDo.Task;

public class CommandActionDisplay implements CommandAction{	
	@Override
	public void execute(ParsedResult parsedResult){}

	@Override
	public void undo(Task lastTask) {}
}
