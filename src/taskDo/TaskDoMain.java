package taskDo;
import java.util.ArrayList;

/*
 * @author Paing Zin Oo(Jack)
 */


public class TaskDoMain {
	/*
	 * TO TAKE NOTE ....MAIN WILL CREATE CONTROLLER OBJECT AND DEN CONTROLLER WILL 
	 * CREATE UI OBJECT AND FROM THERE PROCEED
	 */
	public static void main(String args[]){
		
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
		System.out.println(taskList);
		
//		ParsedResult.setCommandType(CommandType.ADD);
//		Executor executor = new Executor();
//		executor.execute();
	}
}