package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionSearch implements CommandAction{

	public void execute(ParsedResult parsedResult) {
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		ArrayList<Task> displayList = new ArrayList<Task>();
		Search search = new Search();
		
		updateSR.unhighlightTask();
		
		displayList = search.searchForDisplay(parsedResult);
		updateSR.updateForSearch(parsedResult, displayList);
	}

	public void undo(ParsedResult parsedResult) {}
}
