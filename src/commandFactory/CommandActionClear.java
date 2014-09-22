package commandFactory;

import java.util.ArrayList;

import taskDo.Task;
import taskDo.TaskList;


public class CommandActionClear implements CommandAction{
	@Override
	public void execute(){
		ArrayList<Task> taskList = TaskList.getTaskList();
		taskList.clear();
		System.out.println("task is cleared <-- CommandActionClear.java");
	}
	@Override
	public void undo(){
		System.out.println("how to undo clear??? <-- CommandActionClear.java");
	}
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- clear <-- CommandActionClear.java");
	}
}
