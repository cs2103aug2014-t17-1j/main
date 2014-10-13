package commandFactory;

import taskDo.ParsedResult;

public class CommandActionUndo implements CommandAction{
	@Override
	public void execute(){		
//		pop command 
		UndoCommandFactory undoCommandFactory = new UndoCommandFactory();
		UndoAction undoAction = null;
		
		undoAction = undoCommandFactory.getUndoAction(commandType);
		undoAction.undo();
	}
}