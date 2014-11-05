package commandFactory;

import java.util.ArrayList;

import Parser.ParsedResult;
import taskDo.Task;
import taskDo.UpdateSummaryReport;

public class CommandActionDisplay implements CommandAction{	
	@Override
	public void execute(ParsedResult parsedResult){
		UpdateSummaryReport.unhighlightTask();
		Search search = new Search();
		ArrayList<Task> displayList = search.searchForDisplay(parsedResult);
		UpdateSummaryReport.updateForDisplay(parsedResult, displayList);
	}

	@Override
	public void undo(ParsedResult parsedResult) {}
}
