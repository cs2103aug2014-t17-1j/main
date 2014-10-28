package commandFactory;

import Parser.ParsedResult;
import taskDo.UpdateSummaryReport;

public class CommandActionSearch implements CommandAction{

	public void execute(ParsedResult parsedResult) {
		Search search = new Search();
		UpdateSummaryReport.updateForSearch(parsedResult, search.searchForDisplay(parsedResult));
	}

	public void undo(ParsedResult parsedResult) {

	}
}
