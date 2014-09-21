import commandFactory.CommandAction;
import commandFactory.AddCommandAction;
import commandFactory.ClearCommandAction;
import commandFactory.DeleteCommandAction;
import commandFactory.DisplayCommandAction;

public class CommandFactory {

	public CommandAction getCommandAction(CommandType commandType){
		switch (commandType) {
		case ADD:
			return new AddCommandAction();
		case DELETE:
			return new DeleteCommandAction();
		case DISPLAY:
			return new DisplayCommandAction();
		case CLEAR:
			return new ClearCommandAction();
		default:
			return null;
		}
	}
}
