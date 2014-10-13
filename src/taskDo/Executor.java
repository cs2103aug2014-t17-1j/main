package taskDo;

import commandFactory.CommandAction;
import commandFactory.CommandFactory;
import commandFactory.CommandType;

public class Executor {
	
	public Executor() {
//		StorageList.getInstance().loadFile();
	}

	public void execute() {
		CommandFactory commandFactory = new CommandFactory();
		CommandType commandType = ParsedResult.getCommandType();
		CommandAction commandAction = null;
		commandAction = commandFactory.getCommandAction(commandType);
		commandAction.execute();
//		StorageList.getInstance().save();
		UpdateSummaryReport.update(commandType);
	}
}