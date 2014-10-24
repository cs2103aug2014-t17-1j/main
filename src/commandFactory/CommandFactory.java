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
		case EDIT:
			return new CommandActionEdit();
		case COMPLETE:
			return new CommandActionComplete();
		case SEARCH:
			return new CommandActionSearch();
		default:
			return null;
		}
	}
}
