package taskDo;

import commandFactory.CommandAction;
import commandFactory.CommandFactory;
import commandFactory.CommandType;

public class Executor {
	public void Executor() {
//		StorageList.getInstance().loadFile();
	}
	
	public void execute() {
		CommandFactory commandFactory = new CommandFactory();
		CommandType commandType = ParsedResult.getCommandType();
		CommandAction commandAction = null;
		if (commandType.equals(CommandType.UNDO)) {
			// pop from stack commandAction = stack.pop();
			commandAction.undo();
		} else {
			commandAction = commandFactory.getCommandAction(commandType);
			commandAction.execute();
//			StorageList.getInstance().save();
			// push commandAction into stack
		}
		commandAction.updateSummaryReport();
	}
}