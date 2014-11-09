package commandFactory;

import java.util.ArrayList;

import parser.ParsedResult;
import taskDo.Search;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionSearch implements CommandAction {
	// @Author Huang Li A0112508R
	@Override
	public void execute(ParsedResult parsedResult) {
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		ArrayList<Task> displayList = new ArrayList<Task>();
		Search search = new Search();

		updateSR.unhighlightTask();

		displayList = search.searchForDisplay(parsedResult);
		updateSR.updateForSearch(parsedResult, displayList);
	}

	@Override
	public void undo(ParsedResult parsedResult) {
	}
}
