package commandFactory;

import Parser.ParsedResult;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionSearch implements CommandAction{

	public void execute(ParsedResult parsedResult) {
		Search search = new Search();
		UpdateSummaryReport.updateForDisplay(parsedResult, search.searchByKeyword(parsedResult));
	}

	public void undo(Task lastTask) {

	}
}
