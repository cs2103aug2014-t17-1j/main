package commandFactory;

import java.util.ArrayList;

import taskDo.StorageList;
import taskDo.SummaryReport;
import taskDo.Task;


public class CommandActionDisplay implements CommandAction{
	@Override
	public void execute(){}
	
	@Override
	public void undo(){}
	
	@Override
	public void updateSummaryReport(){
		StorageList strageListInstance = StorageList.getInstance();
		ArrayList<Task> taskList = strageListInstance.getTaskList();
		
		SummaryReport.setFeedBackMsg("Display Task List");
		SummaryReport.setHeader("Display Task List");
		SummaryReport.setDsiplayList(taskList);
	}
}
