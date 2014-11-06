package commonClasses;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import org.joda.time.DateTime;

import uiView.ColorBox;

/*
 * @author Paing Zin Oo(Jack)
 */

public class Constants {
	/*
	 *  @author Paing Zin Oo(Jack)
	 */
	
	public static final String PRODUCT_TASKDO = "Task.Do";
	
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final Dimension DIMENION_TABLE = new Dimension((int)(SCREEN_SIZE.width * 0.36 ), (int)(SCREEN_SIZE.height * 0.5));
//	public static final Dimension DIMENION_TABLE = new Dimension((int)(SCREEN_SIZE.width * 0.36 ), (int)(SCREEN_SIZE.height * 0.5));
	public static final Dimension DIMENSION_SHORCUT_PANEL = new Dimension((int)(SCREEN_SIZE.width * 0.146),(int)(SCREEN_SIZE.height * 0.5));
	public static final Dimension DIMENSION_DETAIL_PANEL = new Dimension((int)(SCREEN_SIZE.width * 0.3),(int)(SCREEN_SIZE.height * 0.46));
	public static final Dimension DIMENSION_HELP_PANEL = new Dimension((int) (SCREEN_SIZE.width * 0.29), (int) (SCREEN_SIZE.height * 0.3));
	public static final Dimension DIMESION_JSCROLL_PANEL = new Dimension((int)(SCREEN_SIZE.width * 0.36),(int)(SCREEN_SIZE.height * 0.49));
	
	public static final int MAX_WIDTH_CATEGORY_NAME = (int)(SCREEN_SIZE.height *0.5);
	public static final int MAX_WIDTH_CATEGORY_COUNT = (int)(SCREEN_SIZE.height *0.2);
	
	
	public static final String FILENAME_TASKDO = "TaskDo.json";
	
	public static final String STRING_STRING = "";
	public static final String STRING_OPEN_HTML = "<html>";
	public static final String STRING_CLOSE_HTML = "</html>";
	public static final String STRING_NA = "NA";
	public static final String STRING_F1_HELP = "F1 Help";
	public static final String STRING_F2_DETAILS = "F2 Details";
	public static final String STRING_F3_CATEGORIES = "F2 Categories";
	public static final String CATEGORY_COLUMN_TITLE[] = {"Name","Count"};
	public static final String TASKKEYS[] = {"Title", "category" ,"important","startDate","dueDate","completed","taskType","taskNote"} ;
	
	public static final String MESSAGE_LOAD_ERROR = "Loading Error! Please check that file format is correct!";
	public static final String CATEGORYKEYS[] = {"Name","Count"};
	public static final String HINT[] ={"<html>add [title] -due [date] -category [categoryname] -impt [y/n] -note [extra notes]<br/>"
			+ "Reminder: Optional commands have '-' in front</html>",
		"<html>edit [ID] -title [new title] -due [duedate] -category [category] -impt [y/n] -note [new note]<br/>"
		+ "Reminder: Optional commands have '-' in front</html>",
		"<html>delete [ID]<br>"
		+ "Tip: you can also try del, remove</html>",
		"<html>complete [ID]</br>"
		+ "Tip: You can also try tick,done",
		"<html>display [date/category]</br/>"
		+ "Tip: you can also try show, view. <br>"
		+ "Look at help panel for advanced display commands</html>",
		"<html>search [keyword]"
		
	};

	public static final String [] HELPCOMMANDS = {
		"<html><h3><u><i><b>Main Commands Group 1 <\b</i></u></h3></html>",
			"<html><font color='red'>add</font> *insert task title*</html>",
			"<html><font color='red'>edit</font> *corresponding ID*</html>",
			"<html><h3><u><i>Main Commands Group 2</i></h3></u></html>",
			"<html><font color='red'>delete</font> *corresponding ID*</html>",
			"<html><font color='red'>display</font> *date or category*</html>",
			"<html><font color='red'>undo</font>",
			"<html><font color='red'>redo</font>",
			"<html><font color='red'>complete</font> *corresponding ID*</html>",
			"<html><font color='red'>search</font> *keyword*</html>",
			"<html><h3><u><i>Common Optional Commands</i></h3></u></html>",
			"Addtional commands that works with main commands group 1",
			"<html><font color='#1de9b6'>Note: Optional Command words are recognised by '-'</font></html>",
			"<html><font color='#1de9b6'>-</font><font color='ff7997'>category</font> *insert category name*</html>",
			"<html><font color='#1de9b6'>-</font><font color='ff7997'>due</font> *duedate*</html>",
			"<html><font color='#1de9b6'>-</font><font color='ff7997'>from</font> *startdate* <font color='#1de9b6'>-</font><font color='ff7997'>to</font> *duedate*</html>",
			"<html><font color='#1de9b6'>-</font><font color='ff7997'>impt</font> *Y or N*</html>",
			"<html><font color='#1de9b6'>-</font><font color='ff7997'>note</font> *insert reminder notes*</html>",
			"Example:",
			"add Homework1 -due 10/09/2014 -category School -impt Y",
			"add Homework2 -from 20 aug 16:00 -to 10 sep 18:00",
			"<html><h3><u><i>Specific Optional Commands</i></h3></u></html>",
			"Addtional commands that only works with some main commands",
			"<html>For <font color='red'>edit</font>: <font color='#1de9b6'>-</font><font color='ff7997'>task</font> *new Title*</html>",
			"Example: edit 1 -task homework3",
			"<html><h3><u><i>Advanced Display Command</i></h3></u></html>",
			"<html><font color='red'>display</font> *startdate* <font color='#1de9b6'>-</font><font color='ff7997'>to</font> *duedate*</html>",
			"<html><font color='red'>display</font> all</html>",
			"<html><font color='red'>display</font> overdue</html>",
			"<html><font color='red'>display</font> completed</html>",
			"Example: display 23 mar -to 10 apr"
			
			
	};
	public static final String[] TASK_ATTRIBUTE = {"Note","Category","From"
		, "To","Completed","Important"};
	
	public static final String STRING_YES = "Yes";
	public static final String STRING_NO = "No";
	public static final String STRING_SOMEDAY = "someday";
	public static final String STRING_SPACE = " ";
	
	public static final String []COLUMNTITLES = {"ID","Title","Due On"};
	public static final String HEADER_SHORTCUTS = "SHORTCUTS";
	public static final String HEADER_TAKSLIST = "Tasks List";
	public static final String HEADER_HELP = "HELP PANEL";
	public static final String HEADER_DETAIL = "DETAILS";
	public static final String HEADER_CATEGORIES = "CATEGORIES";
	
	public static final Color COLOR_LEFT_PANEL_BG = ColorBox.colorPool[0];
	public static final Color COLOR_LEFT_PANEL_TEXT = Color.BLACK;
	public static final Color COLOR_LEFT_PANEL_HEADER = Color.BLACK;
	
	public static final Color COLOR_FEEDBACK_MSG = ColorBox.colorPool[8];
	
	public static final Color COLOR_DETAIL_PANEL_TEXT = Color.BLACK;
	public static final Color COLOR_DETAIL_PANEL_BG = ColorBox.colorPool[0];
	public static final Color COLOR_DETAIL_PANEL_HEADER_BG =ColorBox.colorPool[7];
	
	public static final Color COLOR_TABLE_TEXT = Color.BLACK;
	public static final Color COLOR_TABLE_TEXT_HIGHLIGHT = Color.WHITE;
	public static final Color COLOR_TABLE_EVEN_ROW = ColorBox.colorPool[1];
	public static final Color COLOR_TABLE_ODD_ROW = ColorBox.colorPool[2];
	public static final Color COLOR_TABLE_IMPT_ROW = ColorBox.colorPool[3];
	public static final Color COLOR_TABLE_OVERDUE_ROW = ColorBox.colorPool[9];
	public static final Color COLOR_TABLE_TEXT_IMPT = Color.WHITE;
	public static final Color COLOR_TABLE_ROW_HIGHLIGHT = ColorBox.colorPool[4];
	public static final Color COLOR_TABLE_HEADER_BG =ColorBox.colorPool[0];
	public static final Color COLOR_TABLE_HEADER_TEXT = Color.BLACK;
	public static final Color COLOR_TABLE_GRID = Color.WHITE;
	
	public static final Color COLOR_JSCROLL_BG =ColorBox.colorPool[5];
	
	public static final Color COLOR_CENTRE_PANEL_BG = ColorBox.colorPool[5];
	
	public static final Color COLOR_COMMAND_PANEL_BG = ColorBox.colorPool[6];
	public static final Color COLOR_COMMAND_PANEL_TEXT = Color.BLACK;

	public static final Color COLOR_HEADER_PANEL_BG = ColorBox.colorPool[6];
	public static final Color COLOR_HEADER_PANEL_TEXT = Color.WHITE;
	
	public static final int DEFAULT_ROW_SELECTED = -1;
	public static final int TABLE_HEIGHT = 60;
	public static final int JSON_IDENTATION = 3;
	/*
	 *  @author Huang Li
	 */

	// Command Actions
	public static final String MESSAGE_SUCCESS_COMPLETED = "TASK %1$s IS MARKED AS COMPLETED";
	public static final String MESSAGE_SUCCESS_ADD = "TASK IS ADDED SUCCESSFULLY";
	public static final String MESSAGE_SUCCESS_DELETE = "TASK %1$s IS DELETED";
	public static final String MESSAGE_SUCCESS_EDIT = "TASK %1$s IS EDITED";

	public static final String MESSAGE_SUCCESS_SEARCH_SINGLE = "POSSIBLE RESULT LISTED FOR \"%1$s\"";
	public static final String MESSAGE_SUCCESS_SEARCH_MUL = "POSSIBLE RESULTS LISTED FOR \"%1$s\"";
	public static final String MESSAGE_FAIL_SEARCH = "NO TASK IS MATCHED";
	
	public static final String MESSAGE_SUCCESS_UNDO = "UNDO %1$s SUCCESSFULLY";
	public static final String MESSAGE_SUCCESS_REDO = "REDO %1$s SUCCESSFULLY";
	public static final String MESSAGE_FAIL_UNDO = "NO MORE COMMAND FOR UNDO";
	public static final String MESSAGE_FAIL_REDO = "NO MORE COMMAND FOR REDO";

	public static final String MESSAGE_DISPLAY_RANGE = "DISPLAY TASKS FROM %1$s TO %2$s";
	public static final String MESSAGE_DISPLAY_ALL = "DISPLAY ALL UNCOMPLETED TASKS";
	public static final String MESSAGE_DISPLAY_COMPLETED = "DISPLAY COMPLETED TASKS";
	public static final String MESSAGE_DISPLAY_CATEGORY = "DISPLAY CATEGORY: %1$s";
	public static final String MESSAGE_DISPLAY_DATE = "DISPLAY TASKS DUE ON %1$s";
	public static final String MESSAGE_DISPLAY_OVERDUE = "DISPLAY OVERDUE TASKS";
	
	public static final int NILL_YEAR = 0;
	
	/*
	 *  @author Boo Tai Yi
	 */

	// Parser
	public static String[] dateFormats = { "dd/MM/yyyy HH:mm", "dd/MM/yyyy", "ddMMyyyy HH:mm", "ddMMyyyy", "dd MMM HH:mm", "dd MMM", "dd MMM yyyy HH:mm", "dd MMM yyyy" };
	public static final int DATE_FORMAT_ITERATIONS = 8;
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
	public static final String DISPLAY_COMPLETED = "completed";
	public static final String DISPLAY_ALL = "all";
	public static final String MESSAGE_MISSING_PARAM = "Please insert parameters";

	public static final String MESSAGE_MISSING_SIGN_FROMTO_COMMAND = "Please make sure '-' sign is used for 'to' command";

	public static final String MESSAGE_TASK_ALREADY_COMPLETED = "Task is already completed!";

	public static final String DISPLAY_OVERDUE = "overdue";

	public static final int NOTHING_SELECTED = -1;
	
	
	
}
