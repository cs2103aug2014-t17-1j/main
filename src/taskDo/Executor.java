package taskDo;

import Parser.ParsedResult;
import commandFactory.CommandAction;
import commandFactory.CommandFactory;
import commandFactory.CommandType;

public class Executor {

	public Executor() {
		// StorageList.getInstance().loadFile();
	}

	public void execute(ParsedResult parsedResult) {
		assert parsedResult != null;

		CommandFactory commandFactory = new CommandFactory();
		CommandType commandType = parsedResult.getCommandType();

		if (commandType.equals(CommandType.UNDO)) {
			CommandAction commandAction = History.getCommandActionHistory().pop();
			commandAction.undo();
		} else {
			CommandAction commandAction = commandFactory.getCommandAction(commandType);
			History.getCommandActionHistory().push(commandAction);
			History.getTaskHistory().push(parsedResult.getTaskDetails());
			
			commandAction.execute(parsedResult);
		}
		// StorageList.getInstance().save();
		UpdateSummaryReport.update(commandType, parsedResult);
	}
}