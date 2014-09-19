import java.util.ArrayList;

/*
 * @author Paing Zin Oo(Jack)
 */
public class SummaryReport {
	
	private static String feedBackMsg;
	private static String header;
	private static ArrayList<Task> displayList;
	
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
	public static ArrayList<Task> getDsiplayList() {
		return displayList;
	}
	public static void setDsiplayList(ArrayList<Task> dsiplayList) {
		SummaryReport.displayList = dsiplayList;
	}
	
	public static int getTaskId(int id){
		return displayList.get(id-1).getId();
	}
	
	public static void sortByDueDate(){
		
	}
}
