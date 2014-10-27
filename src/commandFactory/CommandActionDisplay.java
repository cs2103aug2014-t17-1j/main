package commandFactory;

import Parser.ParsedResult;
import taskDo.UpdateSummaryReport;

public class CommandActionDisplay implements CommandAction{	
	@Override
	public void execute(ParsedResult parsedResult){
		Search search = new Search();
		UpdateSummaryReport.updateForDisplay(parsedResult, search.searchForDisplay(parsedResult));
	}

	@Override
	public void undo(ParsedResult parsedResult) {}
}
