package commandFactory;

import java.util.ArrayList;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.Task;


public class CommandActionDelete implements CommandAction{
	@Override
	public void execute(){
		StorageList storageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = storageListInstance.getTaskList();
		
		// remove by ID
		taskList.remove(ParsedResult.getTaskDetails().getId());
		
		System.out.println("task is deleted <-- CommandActionDelete.java");
		System.out.println(taskList.toString());
	}
	@Override
	public void undo(){
		// NEED IMPLEMENT A STACK CALLED HISTORY AS A DUSTBIN
		System.out.println("undo delete  <-- CommandActionDelete.java");
	}
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- delete <-- CommandActionDelete.java");
	}
}
