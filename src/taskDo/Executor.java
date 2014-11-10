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

/**
 * Executor is to executing commands
 * commands based on commandType
 * commands taken from parsedResult
 *
 */
public class Executor {
	// @Author  A0112508R
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
		} else if (commandType.equals(CommandType.REDO)) {
			executeRedo(parsedResult);
		} else {
			executeCommand(parsedResult, commandFactory, commandType);
		}

		CategoryList
				.updateCategoryList(StorageList.getInstance().getTaskList());
	}

	private void executeUndo(ParsedResult parsedResult) {
		History history = History.getInstance();
		CommandAction commandAction = null;

		try {
			commandAction = history.getUndoActionHistory().pop();
			Task lastTask = history.getUndoTaskHistory().pop();

			history.getRedoActionHistory().push(commandAction);

			parsedResult.setTask(lastTask);
			commandAction.undo(parsedResult);
		} catch (Exception e) {
			log.info("no more undo command [" + e + "].");

			history.getUndoTaskHistory().clear();
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_FAIL_UNDO);
		}
	}

	private void executeRedo(ParsedResult parsedResult) {
		History history = History.getInstance();
		CommandAction commandAction = null;

		try {
			commandAction = history.getRedoActionHistory().pop();
			Task lastTask = history.getRedoTaskHistory().pop();

			history.getUndoActionHistory().push(commandAction);

			parsedResult.setTask(lastTask);
			commandAction.execute(parsedResult);
		} catch (Exception e) {
			log.info("no more redo command [" + e + "].");
			history.getRedoTaskHistory().clear();
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_FAIL_REDO);
		}
	}

	private void executeCommand(ParsedResult parsedResult,
			CommandFactory commandFactory, CommandType commandType) {

		History history = History.getInstance();
		CommandAction commandAction = null;

		commandAction = commandFactory.getCommandAction(commandType);
		commandAction.execute(parsedResult);

		if (isNotDisplayAndSearch(commandType)) {
			history.getUndoActionHistory().push(commandAction);
		}
	}

	private boolean isNotDisplayAndSearch(CommandType commandType) {
		boolean isNotDisplay = !commandType.equals(CommandType.DISPLAY);
		boolean isNotSearch = !commandType.equals(CommandType.SEARCH);
		return isNotDisplay && isNotSearch;
	}
}