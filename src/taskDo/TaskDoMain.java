package taskDo;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.ArrayList;

import uiView.UiViewModifier;
import commonClasses.StorageList;
import commonClasses.SummaryReport;

/*
 * @author Paing Zin Oo(Jack)
 */

public class TaskDoMain {
	/*
	 * TO TAKE NOTE ....MAIN WILL CREATE CONTROLLER OBJECT AND DEN CONTROLLER
	 * WILL CREATE UI OBJECT AND FROM THERE PROCEED
	 */
	  static FileLock lock;
	  static FileChannel channel;

	public static void main(String args[]) {
		/*
		 * Testing for integration of all the controller,executor, parser and UI
		 */
		try {
			if (isAppActive()) {
				System.exit(1);
			} 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList<Task> taskList = StorageList.getInstance().getTaskList();
		System.out.println(taskList);
		SummaryReport.setDisplayList(taskList);
		UiViewModifier uiVM = new UiViewModifier();

		// Testing for JSON OBJ

		//
		// Task t3 = new Task ("jsfkdlfjk");
		// t1.setDueDate(new DateTime(0,1,1,0,0));
		// taskList.add(t3);
		// taskList.add(t1);
		// taskList.add(t2);
		// ConvertToJSonObject ctj = new ConvertToJSonObject();
		// ctj.setTaskList(taskList);
		// System.out.println(ctj.changeToJSonObj());
		// ReadAndWriteToFile rwf = new ReadAndWriteToFile();
		// rwf.setjSonText(ctj.changeToJSonObj());
		// System.out.println(rwf.writeToFile());
		//
		// ArrayList<Task> taskLIST = rwf.readFromFile();
		// for(Task task : taskLIST){
		// if(task.getDueDate() == null){
		// task.setDueDate(new DateTime(0,1,1,0,0));
		// System.out.println("HERE");
		// }
		// System.out.println("TASK DES "+ task.getDescription() + " DATE "+
		// task.getDueDate().toLocalDate().toString());
		// }
		//

		// SummaryReport.setDsiplayList(taskList);
		// UiViewModifier ui = new UiViewModifier();
		// SummaryReport.setFeedBackMsg(null);
		// taskList.clear();
		// Task t4 = new Task("CS 21032222");
		// Task t5 = new Task ("CS 11112222");
		// Task t6 = new Task ("jsfkdlfjk2222");
		//
		// taskList.add(t4);
		// taskList.add(t5);
		// taskList.add(t6);
		// ui.updateUi();
		//
		// Task t = new Task ();
		// t.setDescription("fklsdjflk");
		// System.out.println(taskList);

		// Task t = new Task ();
		// t.setDescription("this is a test task");
		// ParsedResult.setTask(t);
		// ParsedResult.setCommandType(CommandType.ADD);
		// Executor executor = new Executor();
		// executor.execute();
	}
	
	public static boolean isAppActive() throws Exception {
		File file = new File(System.getProperty("user.home"), "TaskDoLock.tmp");
		channel = new RandomAccessFile(file, "rw").getChannel();

		lock = channel.tryLock();
		if (lock == null) {
			channel.close();
			return true;
		}
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try {
					lock.release();
					channel.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return false;
	}
}
