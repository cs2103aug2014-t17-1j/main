package parser;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.joestelmach.natty.DateGroup;

import commonClasses.Constants;
import commonClasses.SummaryReport;

public class CommonInterpreterMethods {

	// This method checks if it is a valid selection on the displayList of
	// summaryReport
	// @author Boo Tai Yi A0111936J
	static boolean isValidSelection(String commandParam) {
		int selection;
		try {
			selection = Integer.valueOf(commandParam);
		} catch (Exception e) {
			return false;
		}

		if (selection >= 1
				&& selection <= SummaryReport.getDisplayList().size()) {
			return true;
		}
		return false;
	}

	static boolean noDeadLine(String commandParam) {
		if (commandParam.toUpperCase().equals(Constants.FLOATING_TASK)) {
			return true;
		}

		return false;
	}

	static DateTime getDate(String commandParam)
			throws InvalidParameterException {
		boolean dateHasPassed = false;
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

				if (!isYearSpecified(dates)) {
					dates = dates.withYear(new DateTime().getYear());
				}
				if (dateHasPassed(dates)) {
					dateHasPassed = true;
					throw new InvalidParameterException(
							Constants.MESSAGE_DATE_HAS_PASSED);
				}
				if (!isTimeSpecifiedForDateTimeFormatter(dates)) {
					dates = updateTime(dates);
				}
				return dates;
			} catch (Exception e) {
				if (dateHasPassed == true) {
					throw new InvalidParameterException(
							Constants.MESSAGE_DATE_HAS_PASSED);
				}
			}
		}
		if (containsDigits(commandParam)) {
			if (!checkRelativeDateFormat(commandParam)) {
				return null;
			}
		}
		group = parser.parse(commandParam);
		if (group.isEmpty()) {
			return null; // Not a valid date
		}
		DateTime dates = new DateTime(group.get(0).getDates().get(0));
		if (!isTimeSpecifiedForNatty(dates)) {
			dates = updateTime(dates);
		}

		return dates;
	}

	private static boolean isTimeSpecifiedForNatty(DateTime dates) {
		DateTime currentTime = new DateTime();

		String parsed = dates.minuteOfDay().getAsText();
		String current = currentTime.minuteOfDay().getAsText();
		// When time is not specified, Natty will use the time now.
		return !parsed.equals(current);
	}

	private static boolean isYearSpecified(DateTime dates) {
		// For DateTimeFormatter, when year is not specified, it will be
		// defaulted to 2000
		return !(dates.getYear() == 2000);
	}

	// This methods updates the time of the dateTime object to 2359
	private static DateTime updateTime(DateTime dates) {
		dates = dates.withHourOfDay(23);
		dates = dates.withMinuteOfHour(59);
		return dates;
	}

	private static boolean dateHasPassed(DateTime dates) {

		DateTime currentTime = new DateTime();

		if (dates.toLocalDate().isBefore(currentTime.toLocalDate())) {
			return true;
		}

		return false;
	}

	private static boolean isTimeSpecifiedForDateTimeFormatter(DateTime dates) {
		// When time is not specified, DateTimeFormatter will update time as 0
		return !(dates.getMinuteOfDay() == 0);
	}

	// This method checks if the relativeDateFormat entered is supported by
	// Task.Do
	private static boolean checkRelativeDateFormat(String commandParam) {
		Pattern pattern = Pattern
				.compile(Constants.REGEX_STRING_RELATIVE_DATE_FORMAT);
		Matcher matcher = pattern.matcher(commandParam);

		if (matcher.find()) {
			return true;
		}

		return false;

	}

	static String getCommandWord(String input) {
		String[] splittedCommand = input.split(" ");

		return splittedCommand[0];
	}

	private static boolean containsDigits(String commandParam) {
		Pattern pattern = Pattern
				.compile(Constants.REGEX_STRING_CHECK_FOR_DIGITS);
		Matcher matcher = pattern.matcher(commandParam);

		if (matcher.find()) {
			return true;
		}

		return false;
	}

	static boolean isInvalidDate(DateTime date) {
		// getDate method returns null if it is a invalid date
		return date == null;
	}

}
