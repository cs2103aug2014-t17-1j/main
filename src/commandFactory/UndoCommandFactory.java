package commandFactory;

public class UndoCommandFactory {
	public UndoAction getUndoAction(CommandType commandType){
		switch (commandType) {
		case ADD:
			return new UndoActionAdd();
		case DELETE:
			return new UndoActionDelete();
		case EDIT:
			return new UndoActionEdit();
		default:
			return null;
		}
	}
}
