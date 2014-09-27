package commandFactory;

import java.util.ArrayList;

import taskDo.ParsedResult;
import taskDo.StorageList;
import taskDo.SummaryReport;
import taskDo.Task;


public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(){
		// get the singleton instance and get the tasklist
		StorageList strageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = strageListInstance.getTaskList();
		
		taskList.add(ParsedResult.getTaskDetails());
	}
	
	@Override
	public void undo(){
		StorageList strageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = strageListInstance.getTaskList();
		
		taskList.remove(ParsedResult.getTaskDetails().getId());
	}
	
	@Override
	public void updateSummaryReport(){
		// for V0.1 undo function is not considered yet
		StorageList strageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = strageListInstance.getTaskList();
		
		SummaryReport.setFeedBackMsg("Display Task List");
		SummaryReport.setHeader("Display Task List");
		SummaryReport.setDsiplayList(taskList);
	}
}
