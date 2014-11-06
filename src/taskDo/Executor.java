package taskDo;

import parser.ParsedResult;
import commandFactory.CommandAction;
import commandFactory.CommandFactory;
import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.StorageList;
import commonClasses.SummaryReport;

public class Executor {

	public Executor() {
		StorageList.getInstance().loadFile();
	}

	public void execute(ParsedResult parsedResult) {		
		CommandFactory commandFactory = new CommandFactory();
		CommandType commandType = parsedResult.getCommandType();

		if (commandType.equals(CommandType.UNDO)) {
			executeUndo(parsedResult);
		}else	if(commandType.equals(CommandType.REDO)){
			executeRedo(parsedResult);
		}else{
			executeCommand(parsedResult, commandFactory, commandType);
		}
		
		CategoryList.updateCategoryList(StorageList.getInstance().getTaskList());
	}

	private void executeUndo(ParsedResult parsedResult) {
		if(!History.getUndoActionHistory().empty()){
			CommandAction commandAction = History.getUndoActionHistory().pop();
			Task lastTask = History.getUndoTaskHistory().pop();

			History.getRedoActionHistory().push(commandAction);

			parsedResult.setTask(lastTask);
			commandAction.undo(parsedResult);
		}else{
			History.getUndoTaskHistory().clear();
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_FAIL_UNDO);
		}
	}

	private void executeRedo(ParsedResult parsedResult) {
		if(!History.getRedoActionHistory().empty()){
			CommandAction commandAction = History.getRedoActionHistory().pop();
			Task lastTask = History.getRedoTaskHistory().pop();
			History.getUndoActionHistory().push(commandAction);
			parsedResult.setTask(lastTask);
			commandAction.execute(parsedResult);
		}else{
			History.getRedoTaskHistory().clear();
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_FAIL_REDO);
		}
	}

	private void executeCommand(ParsedResult parsedResult,
			CommandFactory commandFactory, CommandType commandType) {
		CommandAction commandAction = commandFactory.getCommandAction(commandType);			
		commandAction.execute(parsedResult);
		if(!commandType.equals(CommandType.DISPLAY)&&!commandType.equals(CommandType.SEARCH)){
			History.getUndoActionHistory().push(commandAction);
		}
	}
}