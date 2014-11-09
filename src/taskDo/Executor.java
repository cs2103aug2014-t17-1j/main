package taskDo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import parser.ParsedResult;

import commandFactory.CommandAction;
import commandFactory.CommandFactory;
import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.StorageList;
import commonClasses.SummaryReport;

public class Executor {
	
	private static final Logger log = LogManager.getLogger(Executor.class);

	public Executor() {
		StorageList.getInstance().loadFile();
	}

	public void execute(ParsedResult parsedResult) {		
		CommandFactory commandFactory = new CommandFactory();
		CommandType commandType = parsedResult.getCommandType();
		log.info("Command [" + commandType.toString() + "].");
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
		History history = History.getInstance();
		if(!history.getUndoActionHistory().empty()){
			CommandAction commandAction = history.getUndoActionHistory().pop();
			Task lastTask = history.getUndoTaskHistory().pop();

			history.getRedoActionHistory().push(commandAction);

			parsedResult.setTask(lastTask);
			commandAction.undo(parsedResult);
		}else{
			history.getUndoTaskHistory().clear();
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_FAIL_UNDO);
		}
	}

	private void executeRedo(ParsedResult parsedResult) {
		History history = History.getInstance();
		if(!history.getRedoActionHistory().empty()){
			CommandAction commandAction = history.getRedoActionHistory().pop();
			Task lastTask = history.getRedoTaskHistory().pop();
			history.getUndoActionHistory().push(commandAction);
			parsedResult.setTask(lastTask);
			commandAction.execute(parsedResult);
		}else{
			history.getRedoTaskHistory().clear();
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_FAIL_REDO);
		}
	}

	private void executeCommand(ParsedResult parsedResult,
			CommandFactory commandFactory, CommandType commandType) {
		CommandAction commandAction = commandFactory.getCommandAction(commandType);			
		commandAction.execute(parsedResult);
		if(!commandType.equals(CommandType.DISPLAY)&&!commandType.equals(CommandType.SEARCH)){
			History.getInstance().getUndoActionHistory().push(commandAction);
		}
	}
}