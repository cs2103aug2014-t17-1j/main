import commandFactory.CommandAction;
import commandFactory.CommandFactory;

/*
 * @author Paing Zin Oo(Jack)
 */
public class TaskDoMain {
	public static void main(String args[]){
		String parsedResultCommand = "add";
		executor(parsedResultCommand);
	}
	
	public static void executor(String command){
		CommandFactory commandFactory = new CommandFactory();
		CommandAction commandAction = commandFactory.getCommandAction(command);
		commandAction.execute();
		commandAction.updateSummaryReport();
	}
}
