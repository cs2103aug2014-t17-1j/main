package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionDisplay implements CommandAction{	
	@Override
	public void execute(ParsedResult parsedResult){
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		updateSR.unhighlightTask();
		
		Search search = new Search();
		ArrayList<Task> displayList = search.searchForDisplay(parsedResult);
		updateSR.updateForDisplay(parsedResult, displayList);
	}

	@Override
	public void undo(ParsedResult parsedResult) {}
}
