package commandFactory;

public class CommandFactory {

	public CommandAction getCommandAction(CommandType commandType){
		switch (commandType) {
		case ADD:
			return new CommandActionAdd();
		case DELETE:
			return new CommandActionDelete();
		case DISPLAY:
			return new CommandActionDisplay();
		case CLEAR:
			return new CommandActionClear();
		default:
			return null;
		}
	}
}
