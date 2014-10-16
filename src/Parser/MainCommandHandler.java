package Parser;

import java.security.InvalidParameterException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import taskDo.CategoryList;
import taskDo.SearchType;
import taskDo.Task;

import com.joestelmach.natty.DateGroup;

import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class MainCommandHandler implements CommandHandler {

	//Members
	CommandType currentCommand;
	
	public MainCommandHandler() {

	}
	
	public CommandType getCommand() {
		return this.currentCommand;
	}
	public void identifyAndSetCommand(String command) throws InvalidParameterException {
		switch (command) {
		case "add":
			currentCommand = CommandType.ADD;
			break;
			
		case "display":
			currentCommand = CommandType.DISPLAY;
			break;
			
		case "delete":
			currentCommand = CommandType.DELETE;
			break;
		case "edit":
			currentCommand = CommandType.EDIT;
			break;
		case "undo":
			currentCommand = CommandType.UNDO;
			break;
			
		case "search":
			currentCommand = CommandType.SEARCH;
			break;
	
		default:
			SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_COMMAND);
			throw new InvalidParameterException();
		}
	}
	
	public String removeCommandWord(String input) {
			switch (currentCommand) {
			case ADD:
				return input.substring(4); // 4 is length of word "add "

			case DISPLAY:
				return input.substring(8); // 8 is length of word "display "

			case DELETE:
				return input.substring(7); // 7 is length of word "delete "

			case SEARCH:
				return input.substring(7); // 7 is length of word "search "

			case EDIT:
				return input.substring(5);

			default:
				return "";
			}
	}
			
	public ParsedResult updateResults(ParsedResult result, String commandParam) throws InvalidParameterException {
			result.setCommandType(currentCommand);
			Task task = result.getTaskDetails();
			switch (currentCommand) {
			
			case ADD:
				task.setDescription(commandParam);
				break;

			case DELETE:
				if(isValidSelection(commandParam)) {
					result.setTask(SummaryReport.getDisplayList().get(Integer.valueOf(commandParam)-1));
				} else {
					SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
					throw new InvalidParameterException();
				}
				break;

			case EDIT:
				if(isValidSelection(commandParam)) {
					int selection = Integer.valueOf(commandParam) - 1; //adjust to get the correct index in list
					result.setTask(SummaryReport.getDisplayList().get(selection));
				} else {
					SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_SELECTION);
					throw new InvalidParameterException();
				}
				break;

			case DISPLAY:
				if (CategoryList.isExistingCategory(commandParam)) {
					task.setCategory(commandParam);
					result.setSearchMode(SearchType.CATEGORY);
				} else {
					if(noDeadLine(commandParam)) {
						task.setDueDate(Constants.SOMEDAY);
						task.setStartDate(null);
						break;
					}
					DateTime date = getDate(commandParam);
					if(date == null) {
						SummaryReport.setFeedBackMsg(Constants.MESSAGE_INVALID_DISPLAY_SELECTION);
						throw new InvalidParameterException();
					}
					else {
						task.setDueDate(date);
						result.setSearchMode(SearchType.DATE);
					}
				}
				break;
			case UNDO:
				// do nothing
				break;

			default:
				// do nothing

			}
			return result;
	}
	
	//Misc methods
	private static boolean isValidSelection(String commandParam) {
		int selection;
		try {
			selection = Integer.valueOf(commandParam);
		}
		catch (Exception e) {
			return false;
		}

		if(selection >= 1 && selection <= SummaryReport.getDisplayList().size()) {
			return true;
		}
		return false;
	}
	
	private static boolean noDeadLine(String commandParam) {
		if(commandParam.toUpperCase().equals("SOMEDAY")) {
			return true;
		}
		
		return false;
	}
	
	private static DateTime getDate(String commandParam) {

		com.joestelmach.natty.Parser parser = new com.joestelmach.natty.Parser();
		DateTimeFormatter df;
		DateTime date;
		List<DateGroup> group;
		for (int i = 0; i < Constants.DATE_FORMAT_ITERATIONS; i++) {
			try {
				df = DateTimeFormat.forPattern(Constants.dateFormats[i]);
				date = df.parseDateTime(commandParam);
				group = parser.parse(date.toString());
				DateTime dates = new DateTime(group.get(0).getDates().get(0));

				DateTime currentTime = new DateTime();

				if (dates.hourOfDay().toString()
						.equals(currentTime.hourOfDay().toString())) {
					dates = dates.withHourOfDay(23);
					dates = dates.withMinuteOfHour(59); // update the time to
					// 2359
				}

				return dates;
			} catch (Exception e) {

			}
		}
		group = parser.parse(commandParam);
		if (group.isEmpty()) {
			return null; // Not a valid date
		}
		DateTime dates = new DateTime(group.get(0).getDates().get(0));

		DateTime currentTime = new DateTime();

		if (dates.hourOfDay().toString()
				.equals(currentTime.hourOfDay().toString())) {
			dates = dates.withHourOfDay(23);
			dates = dates.withMinuteOfHour(59); // update the time to 2359
		}

		return dates;
	}
	
}
