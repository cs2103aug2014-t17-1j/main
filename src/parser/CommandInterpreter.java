package parser;

public abstract class CommandInterpreter {

	abstract void identifyAndSetCommand(String command);

	abstract String removeCommandWord(String input);

	abstract ParsedResult updateResults(ParsedResult result, String Param);
}
