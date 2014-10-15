package Parser;

import java.security.InvalidParameterException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import taskDo.StringConstants;
import taskDo.SummaryReport;
import taskDo.Task;

import com.joestelmach.natty.DateGroup;

public class OptionalCommandHandler implements CommandHandler {
	
	enum OptionalCommand {
		DUE, FROM, TO, CATEGORY, IMPT, TASK
	}
	
	//Members
	OptionalCommand currentCommand;
	
	public OptionalCommandHandler() {
		
	}
	
	public void identifyAndSetCommand(String command) throws InvalidParameterException {
		
		switch (command) {

		case "due":
			currentCommand = OptionalCommand.DUE;
			break;
			
		case "from":
			currentCommand = OptionalCommand.FROM;
			break;
			
		case "to":
			currentCommand = OptionalCommand.TO;
			break;
			
		case "category":
			currentCommand = OptionalCommand.CATEGORY;
			break;
			
		case "impt":
			currentCommand = OptionalCommand.IMPT;
			break;
			
		case "task":
			currentCommand = OptionalCommand.TASK;
			break;
			
		default:
			SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_OPTIONAL_COMMAND);
			throw new InvalidParameterException();
		}
	}

	public String removeCommandWord(String remainingInput) {
		switch (currentCommand) {

		case DUE:
			return remainingInput.substring(4); // Length of word "due "

		case FROM:
			return remainingInput.substring(5);

		case TO:
			return remainingInput.substring(3);

		case IMPT:
			return remainingInput.substring(5);

		case CATEGORY:
			return remainingInput.substring(9);

		case TASK:
			return remainingInput.substring(5);

		default:
			return "";
		}
	}

	public ParsedResult updateResults(ParsedResult result, String commandParam) throws InvalidParameterException {
		Task task = result.getTaskDetails();
		DateTime date = null;
		switch (currentCommand) {
		case DUE:
			if(noDeadLine(commandParam)) {
				task.setDueDate(StringConstants.SOMEDAY);
				task.setStartDate(null);
				break;
			}
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setDueDate(date);
				task.setStartDate(null);
			}
			break;

		case FROM:
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setStartDate(date);
			}
			break;

		case TO:
			date = getDate(commandParam);
			if (date == null) {
				SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_DATE);
				throw new InvalidParameterException();
			} else {
				task.setDueDate(date);
			}
			break;

		case CATEGORY:
			task.setCategory(commandParam);
			break;

		case TASK:
			task.setDescription(commandParam);
			break;

		case IMPT:
			if(commandParam.equals("Y")) {
				task.setImportant(true);
			}
			else if(commandParam.equals("N")) {
				task.setImportant(false);
			}
			else {
				SummaryReport.setFeedBackMsg(StringConstants.MESSAGE_INVALID_IMPORTANCE_PARAM);
				throw new InvalidParameterException();
			}
			break;

		default:// do nothing
		}
		
		return result;
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
		for (int i = 0; i < StringConstants.DATE_FORMAT_ITERATIONS; i++) {
			try {
				df = DateTimeFormat.forPattern(StringConstants.dateFormats[i]);
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
