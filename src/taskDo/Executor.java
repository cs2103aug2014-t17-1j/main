package taskDo;

import Parser.ParsedResult;
import commandFactory.CommandAction;
import commandFactory.CommandFactory;
import commandFactory.CommandType;

public class Executor {
	
	public Executor() {
//		StorageList.getInstance().loadFile();
	}

	public void execute(ParsedResult parsedResult) {
		CommandFactory commandFactory = new CommandFactory();
		CommandType commandType = parsedResult.getCommandType();
		CommandAction commandAction = null;
		
		commandAction = commandFactory.getCommandAction(commandType);
		commandAction.execute(parsedResult);
		
//		StorageList.getInstance().save();
		UpdateSummaryReport.update(commandType);
	}
}