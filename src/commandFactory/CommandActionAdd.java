package commandFactory;

import java.util.ArrayList;

import taskDo.ParsedResult;
import taskDo.Task;
import taskDo.TaskList;


public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(){
		ArrayList<Task> taskList = TaskList.getTaskList();
		taskList.add(ParsedResult.getTaskDetails());
		System.out.println("added successfully <-- print from CommandActionAdd.java");
	}
	
	@Override
	public void undo(){
		ArrayList<Task> taskList = TaskList.getTaskList();
		taskList.remove(ParsedResult.getTaskDetails().getId());
		System.out.println("undo add <-- print from CommandActionAdd.java");
	}
	
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- add <-- print from CommandActionAdd.java");
	}
}
