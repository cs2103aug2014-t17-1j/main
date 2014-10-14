package commonClasses;

import org.joda.time.DateTime;

/*
 * @author Paing Zin Oo(Jack)
 */

public class StringConstants {
	/*
	 *  @author Paing Zin Oo(Jack)
	 */
	
	public static final String FILENAME = "TaskDo.json";
	public static final String TASKKEYS[] = {"category","description","important","dueDate","startDate","completed"} ;

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
