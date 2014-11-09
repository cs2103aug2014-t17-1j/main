package commandFactory;

import parser.ParsedResult;

//@Author Huang Li A0112508R
public interface CommandAction {
	public void execute(ParsedResult parsedResult);

	public void undo(ParsedResult parsedResult);
}