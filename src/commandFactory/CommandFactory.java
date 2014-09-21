package commandFactory;
public class CommandFactory {

	public CommandAction getCommandAction(String command){
		command = command.toLowerCase();
		
		switch (command) {
		case "add":
			return new AddCommandAction();
		case "delete":
			return new DeleteCommandAction();
		case "display":
			return new DisplayCommandAction();
		case "clear":
			return new ClearCommandAction();
		default:
			return null;
		}
	}
}
