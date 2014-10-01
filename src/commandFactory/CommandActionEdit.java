package commandFactory;

import java.util.ArrayList;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.Task;

public class CommandActionEdit implements CommandAction {
	@Override
	public void execute(){
		StorageList strageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = strageListInstance.getTaskList();
		
		taskList.remove(ParsedResult.getTaskDetails().getId());
		taskList.add(ParsedResult.getTaskDetails());
	}
	@Override
	public void undo(){
		System.out.println("undo edit <-- CommandActionEdit.java");
	}
	@Override
	public void updateSummaryReport(){
		System.out.println("updateSummaryReport -- Edit <-- CommandActionEdit.java");
	}
}
