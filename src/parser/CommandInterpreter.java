package parser;

public abstract class CommandInterpreter {

	// @author  A0111936J
	abstract void identifyAndSetCommand(String command);

	abstract String removeCommandWord(String input);

	abstract ParsedResult updateResults(ParsedResult result, String Param);
}
