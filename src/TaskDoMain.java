import commandFactory.CommandAction;

/*
 * @author Paing Zin Oo(Jack)
 */
public class TaskDoMain {
	public static void main(String args[]){
		executor(CommandType.CLEAR);
	}
	
	public static void executor(CommandType commandType){
		CommandFactory commandFactory = new CommandFactory();
		CommandAction commandAction = commandFactory.getCommandAction(commandType);
		commandAction.execute();
		commandAction.updateSummaryReport();
	}
}
