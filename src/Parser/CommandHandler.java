package Parser;

public interface CommandHandler {
	
	public void identifyAndSetCommand(String command);
	public String removeCommandWord(String input);
	public ParsedResult updateResults(ParsedResult result, String Param);
}
