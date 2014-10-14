package commandFactory;

import Parser.ParsedResult;
import taskDo.History;

public class CommandActionUndo implements CommandAction{
	@Override
	public void execute(ParsedResult parsedResult){		
		UndoCommandFactory undoCommandFactory = new UndoCommandFactory();
		UndoAction undoAction = null;
		
		undoAction = undoCommandFactory.getUndoAction(History.getCommandHistory().pop());
		undoAction.undo();
	}
}