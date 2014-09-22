package commandFactory;

import java.util.ArrayList;

import taskDo.ParsedResult;
import taskDo.Task;
import taskDo.TaskList;


public class CommandActionDelete implements CommandAction{
	@Override
	public void execute(){
		ArrayList<Task> taskList = TaskList.getTaskList();
		taskList.remove(ParsedResult.getTaskDetails().getId());
		System.out.println("task is deleted <-- CommandActionDelete.java");
	}
	@Override
	public void undo(){
		
		System.out.println("undo delete, how to add back?  <-- CommandActionDelete.java");
	}
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- delete <-- CommandActionDelete.java");
	}
}
