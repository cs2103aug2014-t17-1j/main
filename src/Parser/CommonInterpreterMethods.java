package Parser;

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
		if (commandParam.toUpperCase().equals("SOMEDAY")) {
			return true;
		}

		return false;
	}

	static DateTime getDate(String commandParam) {

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

				if (dates.getYear() == 2000) {
					dates = dates.withYear(new DateTime().getYear());
				}
				DateTime currentTime = new DateTime();
				if (dates.toLocalDate().isBefore(currentTime.toLocalDate()))
					;

				String parsed = dates.minuteOfDay().getAsText();
				String current = currentTime.minuteOfDay().getAsText();
				if (parsed.equals(current)) {
					dates = dates.withHourOfDay(23);
					dates = dates.withMinuteOfHour(59); // update the time to
					// 2359
				}

				return dates;
			} catch (Exception e) {

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

		DateTime currentTime = new DateTime();
		String parsed = dates.minuteOfDay().getAsText();
		String current = currentTime.minuteOfDay().getAsText();
		if (parsed.equals(current)) {
			dates = dates.withHourOfDay(23);
			dates = dates.withMinuteOfHour(59); // update the time to 2359
		}

		return dates;
	}

	private static boolean checkRelativeDateFormat(String commandParam) {
		Pattern pattern = Pattern.compile("^\\w+ \\d{1,2}:\\d\\d$");
		Matcher matcher = pattern.matcher(commandParam);

		if (matcher.find()) {
			return true;
		}

		return false;

	}

	private static boolean containsDigits(String commandParam) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(commandParam);

		if (matcher.find()) {
			return true;
		}

		return false;
	}
}
