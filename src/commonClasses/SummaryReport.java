package commonClasses;
import java.util.ArrayList;
import java.util.Collections;

import taskDo.Task;
import uiView.Observer;

/*
 * @author Paing Zin Oo(Jack)
 */
public class SummaryReport{
	private ArrayList<Observer> uiList;
	private static String feedBackMsg;
	private static String header;
	private static ArrayList<Task> displayList;

	public SummaryReport(){
		uiList = new ArrayList<Observer>();
	}
	
	public static String getFeedBackMsg() {
		return feedBackMsg;
	}
	public static void setFeedBackMsg(String feedBackMsg) {
		SummaryReport.feedBackMsg = feedBackMsg;
	}
	public static String getHeader() {
		return header;
	}
	public static void setHeader(String header) {
		SummaryReport.header = header;
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
	
	public void addUI(Observer o){
		uiList.add(o);
	}
	public void notifyUIs(){
		
		for(Observer o: uiList){
			o.update();
		}
	}
}
