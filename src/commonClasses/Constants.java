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
	
	public static final String FILENAME = "TaskDo.json";
	
	public static final String TASKKEYS[] = {"category","description","important","dueDate","startDate","completed"} ;
	public static final String [] SHORTCUTS = { "F1 ==> Help",
			"F2 ==> View Details",
			"F3 ==> View Category List"
			
	};
	public static final String [] HELPCOMMANDS = {"<html><h3><u><i><b>Main Commands</b></i></u></h3></html>",
			"add [task]",
			"edit [index]",
			"delete [index]",
			"display [date]",
			"",
			"<html><h3><u><i>Common Optional Commands</i></h3></u></html>",
			"<html><i>(Addtional commands that works with main commands)</i></html>",
			"category [name]",
			"due [duedate]",
			"from [startdate] to [duedate]",
			"impt [Y/N]",
			"",
			"Example: add [Homework1] due [5th oct]",
			"",
			"<html><h3><u><i>Specific Optional Commands</i></h3></u></html>",
			"<html><i>(Addtional commands thats only works with specific main commands)</i></html>",
			"For Edit: task [taskdescription]",
			"",
			"Example: edit [index] task [new description]"
			
	};
	public static final String []COLUMNTITLES = {"ID","Description"," "};
	public static final String HEADER_SHORTCUTS = "SHORTCUTS";
	public static final String HEADER_TAKSLIST = "Tasks List";
	public static final String HEADER_HELP = "HELP PANEL";
	public static final String HEADER_DETAIL = "DETAILS";
	
	public static final Color COLOR_LEFT_PANEL_BG = Color.BLACK;
	public static final Color COLOR_LEFT_PANEL_TEXT = ColorBox.colorPool[24];
	public static final Color COLOR_LEFT_PANEL_HEADER = ColorBox.colorPool[24];
	
	public static final Color COLOR_DETAIL_PANEL_TEXT = ColorBox.colorPool[24];
	public static final Color COLOR_DETAIL_PANEL_BG = Color.BLACK;
	
	public static final Color COLOR_TABLE_TEXT = ColorBox.colorPool[24];
	public static final Color COLOR_TABLE_EVEN_ROW = Color.BLACK;
	public static final Color COLOR_TABLE_ODD_ROW = Color.CYAN;
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
	public static final int NO_TASK = -1;
	public static final int NILL_YEAR = 0;
	public static final String MESSAGE_SOMEDAY = "Someday";
	public static final String MESSAGE_SUCCESS_ADD = "Added successfully";
	public static final String MESSAGE_SUCCESS_DELETE = "Deleted successfully";
	public static final String MESSAGE_SUCCESS_EDIT = "Edited successfully";
	public static final String MESSAGE_DISPLAY = "Display by request";
	
	/*
	 *  @author Boo Tai Yi
	 */

	// Parser
	public static String[] dateFormats = { "dd/MM/yyyy", "yyyy/MM/dd", "dd-MM-yyyy", "yyyy-MM-dd" };
	public static final int DATE_FORMAT_ITERATIONS = 4;
	public static final String MESSAGE_INVALID_COMMAND = "INVALID COMMAND!";
	public static final String MESSAGE_INVALID_OPTIONAL_COMMAND = "INVALID OPTIONAL COMMAND!";
	public static final String MESSAGE_INVALID_DATE = "DATE NOT RECOGNIZED!";
	public static final String MESSAGE_INVALID_DISPLAY_SELECTION = "EITHER CATEGORY DOES NOT EXIST OR DATE NOT RECOGNIZED!";
	public static final String MESSAGE_INVALID_SELECTION = "INVALID SELECTION!";
	public static final String MESSAGE_INVALID_IMPORTANCE_PARAM = "IMPORTANCE LEVEL NOT RECOGNIZED!";
	public static final String MESSAGE_INVALID_PARAM_FORMATTING = "MISSING [] BRACKETS FOR COMMAND PARAMETER";

	public static final DateTime SOMEDAY = new DateTime(0,1,1,0,0);
	
}
