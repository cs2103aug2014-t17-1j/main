package Parser;

import java.util.List;

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
		if (commandParam.toUpperCase().equals("TODO")) {
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
