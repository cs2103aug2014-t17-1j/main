package taskDo;
import java.util.ArrayList;

import org.joda.time.DateTime;

import commonClasses.StorageList;
import commonClasses.SummaryReport;
import readAndWriteFile.ConvertToJson;
import readAndWriteFile.ReadAndWriteToFile;
import uiView.UiViewModifier;

/*
 * @author Paing Zin Oo(Jack)
 */


public class TaskDoMain {
	/*
	 * TO TAKE NOTE ....MAIN WILL CREATE CONTROLLER OBJECT AND DEN CONTROLLER WILL 
	 * CREATE UI OBJECT AND FROM THERE PROCEED
	 */
	public static void main(String args[]){
		/*
		 *Testing for integration of all the controller,executor, parser and UI
		 * 
		 */
		
		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		System.out.println(taskList);
		SummaryReport.setDisplayList(taskList);
		UiViewModifier uiVM = new UiViewModifier();

		

		//Testing for JSON OBJ
		
//		
//		Task t3 = new Task ("jsfkdlfjk");
//		t1.setDueDate(new DateTime(0,1,1,0,0));	
//		taskList.add(t3);
//		taskList.add(t1);
//		taskList.add(t2);
//		ConvertToJSonObject ctj = new ConvertToJSonObject();
//		ctj.setTaskList(taskList);
//		System.out.println(ctj.changeToJSonObj());
//		ReadAndWriteToFile rwf = new ReadAndWriteToFile();
//		rwf.setjSonText(ctj.changeToJSonObj());
//		System.out.println(rwf.writeToFile());
//		
//		ArrayList<Task> taskLIST = rwf.readFromFile();
//		for(Task task : taskLIST){
//			if(task.getDueDate() == null){
//				task.setDueDate(new DateTime(0,1,1,0,0));
//				System.out.println("HERE");
//			}
//			System.out.println("TASK DES "+ task.getDescription() + " DATE "+ task.getDueDate().toLocalDate().toString());
//		}
//		
		
//		SummaryReport.setDsiplayList(taskList);
//		UiViewModifier ui = new UiViewModifier();
//		SummaryReport.setFeedBackMsg(null);
//		taskList.clear();
//		Task t4 = new Task("CS 21032222");
//		Task t5 = new Task ("CS 11112222");
//		Task t6 = new Task ("jsfkdlfjk2222");
//		
//		taskList.add(t4);
//		taskList.add(t5);
//		taskList.add(t6);
//		ui.updateUi();
//		
//		Task t = new Task ();
//		t.setDescription("fklsdjflk");
//		System.out.println(taskList);
		
//		Task t = new Task ();
//		t.setDescription("this is a test task");
//		ParsedResult.setTask(t);
//		ParsedResult.setCommandType(CommandType.ADD);
//		Executor executor = new Executor();
//		executor.execute();
	}
}
