package commonClasses;
import java.util.ArrayList;
import java.util.Collections;

import taskDo.Task;
import uiView.Observer;

/*
 * @author Paing Zin Oo(Jack)
 */
public class SummaryReport{
	private static String feedBackMsg;
	private static ArrayList<Task> displayList = new ArrayList<Task>();
	private static ArrayList<Integer> imptRowIndexList;
	private static int rowIndexHighlight = -1;

	
	

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
	
	public static int getTaskId(int id){
		return displayList.get(id-1).getId();
	}
	
	public static void sortByDueDate(){
		Collections.sort(displayList);
	}
	
	
	public static ArrayList<Integer> getImptRowIndexList() {
		extractImptRowIndex();
		return imptRowIndexList;
	}

	public static void extractImptRowIndex(){
		imptRowIndexList = new ArrayList<Integer>();
		for(int i=0 ; i < displayList.size(); i++){
			if(displayList.get(i).isImportant()){
				imptRowIndexList.add(i);
			}
		}
	}
	
}
