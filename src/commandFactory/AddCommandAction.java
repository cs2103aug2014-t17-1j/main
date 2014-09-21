package commandFactory;

import java.util.ArrayList;


public class AddCommandAction implements CommandAction{
	@Override
	public void execute(){
		ArrayList<Object> task = new ArrayList<Object>();
		// ======== get the global parsedResult ========
		task.add(parsedResult.getTask());
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
