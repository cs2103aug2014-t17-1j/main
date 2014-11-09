package commandFactory;

import parser.ParsedResult;
import taskDo.UpdateSummaryReport;

public class CommandActionSearch implements CommandAction{

	public void execute(ParsedResult parsedResult) {
		UpdateSummaryReport updateSR = UpdateSummaryReport.getInstance();
		updateSR.unhighlightTask();
		
		Search search = new Search();
		updateSR.updateForSearch(parsedResult, search.searchForDisplay(parsedResult));
	}

	public void undo(ParsedResult parsedResult) {}
}
