package commandFactory;

import java.util.ArrayList;

import taskDo.Task;


public class CommandActionAdd implements CommandAction{
	@Override
	public void execute(){
		ArrayList<Object> task = new ArrayList<Object>();
		// ======== get the global parsedResult ========
		Task t = new Task();
	}
	
	@Override
	public void undo(){
		System.out.println("test -- undo add");
	}
	
	@Override
	public void updateSummaryReport(){
		System.out.println("test -- updateSummaryReport -- add");
	}
}
