package commandFactory;

import java.util.ArrayList;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.Task;
import taskDo.TaskList;


public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(){
		// get the singleton instance and get the tasklist
		StorageList strageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = strageListInstance.getTaskList();
		
		// add task into tasklist
		taskList.add(ParsedResult.getTaskDetails());
		
		// feedback message for testing purpose
		System.out.println(taskList.toString() + "added successfully <-- print from CommandActionAdd.java");
	}
	
	@Override
	public void undo(){
		StorageList strageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = strageListInstance.getTaskList();
		
		taskList.remove(ParsedResult.getTaskDetails().getId());
		System.out.println("undo add <-- print from CommandActionAdd.java");
	}
	
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- add <-- print from CommandActionAdd.java");
	}
}
