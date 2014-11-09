package commonClasses;

import java.util.ArrayList;
import java.util.Collections;

import org.joda.time.DateTime;

import taskDo.Task;

/* This class is used to store list of tasks which need to be displayed to user 
 * 
 */
public class SummaryReport {
	// @author Paing Zin Oo(Jack) A0112581N
	private static String feedBackMsg;
	private static ArrayList<Task> displayList = new ArrayList<Task>();
	private static ArrayList<Integer> imptRowIndexList;
	private static ArrayList<Integer> overdueIndexList;
	private static int rowIndexHighlight = Constants.NOTHING_SELECTED;

	public static ArrayList<Integer> getOverdueIndexList() {
		extractOverDueIndex();
		return overdueIndexList;
	}

	public static int getRowIndexHighlight() {
		return rowIndexHighlight;
	}

	public static void setRowIndexHighlight(int rowIndexHighlight) {
		SummaryReport.rowIndexHighlight = rowIndexHighlight;
	}

	public static String getFeedBackMsg() {
		return feedBackMsg;
	}

	public static void setFeedBackMsg(String feedBackMsg) {
		SummaryReport.feedBackMsg = feedBackMsg;
	}

	public static ArrayList<Task> getDisplayList() {
		return displayList;
	}

	public static void setDisplayList(ArrayList<Task> displayList) {
		SummaryReport.displayList = displayList;
	}

	public static int getTaskId(int id) {
		return displayList.get(id - 1).getId();
	}

	public static void sortByDueDate() {
		Collections.sort(displayList);
	}

	public static ArrayList<Integer> getImptRowIndexList() {
		extractImptRowIndex();
		return imptRowIndexList;
	}

	private static void extractOverDueIndex() {
		DateTime today = new DateTime();
		overdueIndexList = new ArrayList<Integer>();
		for (int i = 0; i < displayList.size(); i++) {
			if (displayList.get(i).getDueDate().toLocalDate().getYear() != Constants.NILL_YEAR) {
				if (displayList.get(i).getDueDate().toLocalDate()
						.isBefore(today.toLocalDate())) {
					overdueIndexList.add(i);
				}
			}

		}
	}

	private static void extractImptRowIndex() {
		imptRowIndexList = new ArrayList<Integer>();
		for (int i = 0; i < displayList.size(); i++) {
			if (displayList.get(i).isImportant()) {
				imptRowIndexList.add(i);
			}
		}
	}

}
