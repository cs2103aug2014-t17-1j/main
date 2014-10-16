package commandFactory;

import taskDo.Task;
import Parser.ParsedResult;

public interface CommandAction {
	public void execute(ParsedResult parsedResult);
	public void undo(Task lastTask);
}