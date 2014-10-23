package commonClasses;

import java.awt.Color;
import java.awt.Dimension;

import org.joda.time.DateTime;

import uiView.ColorBox;

/*
 * @author Paing Zin Oo(Jack)
 */

public class Constants {
	/*
	 *  @author Paing Zin Oo(Jack)
	 */
	
	public static final String FILENAME_TASKDO = "TaskDo.json";
	public static final String FILENAME_CATEGORY = "Category.json";
	
	public static final String STRING_STRING = "";
	public static final String STRING_NA = "NA";
	
	public static final String TASKKEYS[] = {"category","description","important","dueDate","startDate","completed","taskType","taskNote"} ;
	public static final String CATEGORYKEYS[] = {"name","count"};
	public static final String [] SHORTCUTS = { "F1 ==> Help",
			"F2 ==> View Details",
			"F3 ==> View Category List"
			
	};
	public static final String [] HELPCOMMANDS = {
		"<html><h3><u><i><b>Main Commands Group 1 </b></i></u></h3></html>",
			"(A1) add task",
			"(A2) edit id",
			"",
			"<html><h3><u><i>Main Commands Group 2</i></h3></u></html>",
			"(B1) delete id",
			"(B2) display date/category",
			"(B3) undo",
			"(B4) complete id",
			"",
			"<html><h3><u><i>Common Optional Commands</i></h3></u></html>",
			"Addtional commands that works with main commands group 1",
			"Note: Optional Command words are recognised by '-",
			"(C1) -category name",
			"(C2) -due duedate",
			"(C3) -from startdate -to duedate(You can only use either (C3) or (C4))",
			"(C4) -important Y/N",
			"(C5) -note extra notes related to the task",
			"",
			"Example:",
			"add Homework1 -due 5th oct -category School -important Y",
			"add Homework2 -from 20 aug -to 10 sep",
			"",
			"<html><h3><u><i>Specific Optional Commands</i></h3></u></html>",
			"Addtional commands thats only works with specific main commands",
			"For Edit: -task task description",
			"Example: edit id task -new description",
			"",
			"<html><h3><u><i>Advanced Display Command</i></h3></u></html>",
			"display date -to date",
			"Example: display 23 mar -to 10 apr"
			
			
	};
	public static final String[] TASK_ATTRIBUTE = {"<b><i>Description</i></b>","<b><i>Category</i></b>","<b><i>Due on</i></b>"
		, "<b><i>Important</b></i>","<b><i>Completed</b></i>","<b><i>Note</b></i>"};
	public static final String STRING_YES = "Yes";
	public static final String STRING_NO = "No";
	public static final String STRING_SOMEDAY = "someday";
	
	public static final String []COLUMNTITLES = {"ID","Description","Due On"};
	public static final String HEADER_SHORTCUTS = "SHORTCUTS";
	public static final String HEADER_TAKSLIST = "Tasks List";
	public static final String HEADER_HELP = "HELP PANEL";
	public static final String HEADER_DETAIL = "DETAILS";
	public static final String HEADER_CATEGORIES = "CATEGORIES";
	
	public static final Color COLOR_LEFT_PANEL_BG = Color.BLACK;
	public static final Color COLOR_LEFT_PANEL_TEXT = ColorBox.colorPool[24];
	public static final Color COLOR_LEFT_PANEL_HEADER = ColorBox.colorPool[24];
	
	public static final Color COLOR_DETAIL_PANEL_TEXT = ColorBox.colorPool[24];
	public static final Color COLOR_DETAIL_PANEL_BG = Color.BLACK;
	
	public static final Color COLOR_TABLE_TEXT = ColorBox.colorPool[24];
	public static final Color COLOR_TABLE_EVEN_ROW = Color.BLACK;
	public static final Color COLOR_TABLE_ODD_ROW = Color.WHITE;
	public static final Color COLOR_TABLE_ROW_HIGHLIGHT = ColorBox.colorPool[105];
	public static final Color COLOR_TABLE_HEADER_BG = Color.BLACK;
	public static final Color COLOR_TABLE_HEADER_TEXT = ColorBox.colorPool[24];
	public static final Color COLOR_TABLE_GRID = Color.CYAN;
	
	public static final Color COLOR_JSCROLL_BG = Color.BLACK;
	
	public static final Color COLOR_CENTRE_PANEL_BG = Color.BLACK;

	public static final Dimension DIMENSION_SHORCUT_PANEL = new Dimension(200,350);
	public static final Dimension DIMENSION_DETAIL_PANEL = new Dimension(420,400);
	
	public static final int DEFAULT_ROW_SELECTED = -1;
	/*
	 *  @author Huang Li
	 */

	// Command Actions
	public static final String MESSAGE_SUCCESS_DELETE = "Deleted successfully";
	public static final String MESSAGE_SUCCESS_EDIT = "Edited successfully";
	public static final String MESSAGE_SUCCESS_ADD = "Added successfully";
	public static final String MESSAGE_SUCCESS_UNDO = "Undo successfully";
	public static final String MESSAGE_DISPLAY = "Display by request";
	public static final String MESSAGE_SOMEDAY = "Someday";
	public static final int NILL_YEAR = 0;
	public static final int NO_TASK = -1;
	
	/*
	 *  @author Boo Tai Yi
	 */

	// Parser
	public static String[] dateFormats = { "dd/MM/yyyy", "yyyy/MM/dd", "ddMMyyyy", "yyyyMMdd", "dd MMM", "dd MMM yyyy" };
	public static final int DATE_FORMAT_ITERATIONS = 6;
	public static final String MESSAGE_INVALID_COMMAND = "Invalid Command";
	public static final String MESSAGE_INVALID_OPTIONAL_COMMAND = "Invalid Optional Command";
	public static final String MESSAGE_INVALID_DATE = "Date not recognized";
	public static final String MESSAGE_INVALID_DISPLAY_SELECTION = "Either Category does not exist or date not recognized";
	public static final String MESSAGE_INVALID_SELECTION = "Invalid selection";
	public static final String MESSAGE_INVALID_IMPORTANCE_PARAM = "Importance level not recognized";
	public static final String MESSAGE_INVALID_COMBINATION_DUE_AND_FROMTO = "Cannot use 'due' and 'from to' combination in one command";
	public static final String MESSAGE_MISSING_START_DATE_FOR_TASK = "Missing start date for task";
	public static final String MESSAGE_END_DATE_EARLIER_THAN_START_DATE = "End date cannot be earlier than start date";
	public static final String IMPT_NO = "N";
	public static final String IMPT_YES = "Y";
	
	public static final DateTime SOMEDAY = new DateTime(0,1,1,0,0);
	
}
