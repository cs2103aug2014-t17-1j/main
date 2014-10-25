package taskDo;

import Parser.ParsedResult;
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
			if(!History.getCommandActionHistory().empty()){
				CommandAction commandAction = History.getCommandActionHistory().pop();
				Task lastTask = History.getTaskHistory().pop();
				commandAction.undo(lastTask);
				parsedResult.setTask(lastTask);

				CategoryList.updateCategoryList(StorageList.getInstance().getTaskList());
				UpdateSummaryReport.update(parsedResult);
			}else{SummaryReport.setFeedBackMsg(Constants.MESSAGE_FAIL_UNDO);}

		} else {
			CommandAction commandAction = commandFactory.getCommandAction(commandType);			
			commandAction.execute(parsedResult);
			if(!commandType.equals(CommandType.DISPLAY)){
				History.getCommandActionHistory().push(commandAction);
			}
			CategoryList.updateCategoryList(StorageList.getInstance().getTaskList());
		}
		StorageList.getInstance().saveToFile();
	}
}