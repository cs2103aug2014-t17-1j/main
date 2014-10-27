package commandFactory;

import Parser.ParsedResult;

public interface CommandAction {
	public void execute(ParsedResult parsedResult);
	public void undo(ParsedResult parsedResult);
}