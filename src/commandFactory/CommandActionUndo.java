package commandFactory;

import taskDo.History;

public class CommandActionUndo implements CommandAction{
	@Override
	public void execute(){		
		UndoCommandFactory undoCommandFactory = new UndoCommandFactory();
		UndoAction undoAction = null;
		
		undoAction = undoCommandFactory.getUndoAction(History.getCommandHistory().pop());
		undoAction.undo();
	}
}