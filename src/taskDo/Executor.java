package taskDo;

import Parser.ParsedResult;
import commandFactory.CommandAction;
import commandFactory.CommandFactory;
import commandFactory.CommandType;
import commonClasses.StorageList;

public class Executor {

	public Executor() {
		StorageList.getInstance().loadFile();
	}

	public void execute(ParsedResult parsedResult) {
		assert parsedResult != null;

		CommandFactory commandFactory = new CommandFactory();
		CommandType commandType = parsedResult.getCommandType();

		if (commandType.equals(CommandType.UNDO)) {
			CommandAction commandAction = History.getCommandActionHistory().pop();
			Task lastTask = History.getTaskHistory().pop();
			commandAction.undo(lastTask);
			UpdateSummaryReport.update(commandType, lastTask.getDueDate());
			
		} else {
			CommandAction commandAction = commandFactory.getCommandAction(commandType);			
			commandAction.execute(parsedResult);
			if(!commandType.equals(CommandType.DISPLAY)){
				History.getCommandActionHistory().push(commandAction);
			}
			UpdateSummaryReport.update(commandType, parsedResult.getTaskDetails().getDueDate());
		}
		StorageList.getInstance().saveToFile();
	}
}