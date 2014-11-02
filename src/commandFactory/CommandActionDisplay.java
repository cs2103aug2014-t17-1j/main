package commandFactory;

import Parser.ParsedResult;
import taskDo.UpdateSummaryReport;

public class CommandActionDisplay implements CommandAction{	
	@Override
	public void execute(ParsedResult parsedResult){
		UpdateSummaryReport.unhighlightTask();
		Search search = new Search();
		UpdateSummaryReport.updateForDisplay(parsedResult, search.searchForDisplay(parsedResult));
	}

	@Override
	public void undo(ParsedResult parsedResult) {}
}
