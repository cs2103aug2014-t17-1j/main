package taskDo;
import java.util.ArrayList;

/*
 * @author Paing Zin Oo(Jack)
 */
public class TaskDoMain {
	public static void main(String args[]){
//		executor(CommandType.CLEAR);

		ArrayList<Task> taskList = new ArrayList<Task>();
		
		Task t1 = new Task("CS 2103");
		Task t2 = new Task ("CS 1111");
		Task t3 = new Task ("jsfkdlfjk");
		
		taskList.add(t3);
		taskList.add(t1);
		taskList.add(t2);
		SummaryReport.setDsiplayList(taskList);
		UiViewModifier ui = new UiViewModifier();
		SummaryReport.setFeedBackMsg(null);
		taskList.clear();
		Task t4 = new Task("CS 21032222");
		Task t5 = new Task ("CS 11112222");
		Task t6 = new Task ("jsfkdlfjk2222");
		
		taskList.add(t4);
		taskList.add(t5);
		taskList.add(t6);
		ui.updateUi();
		
		Task t = new Task ();
		t.setDescription("fklsdjflk");
		System.out.println(t.getDescription());
	}
	
//	public static void executor(CommandType commandType){
//		CommandFactory commandFactory = new CommandFactory();
//		CommandAction commandAction = commandFactory.getCommandAction(commandType);
//		commandAction.execute();
//		commandAction.updateSummaryReport();
//	}
}
