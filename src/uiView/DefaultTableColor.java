package uiView;

import java.awt.Component;
import java.util.ArrayList;

import commonClasses.Constants;
import commonClasses.SummaryReport;

/* This is the class default color table cell renderer
 * 
 *
 */
public class DefaultTableColor {
	// @author Paing Zin Oo(Jack) A0112581N
	public static ArrayList<Integer> imptRowIndexList;
	public static ArrayList<Integer> overdueRowIndexList;

	public static void setDefaultBackGroundColor(Component component, int row,
			int selectedRow) {
		component.setForeground(Constants.COLOR_TABLE_TEXT);

		if (row % 2 == 0) {
			component.setBackground(Constants.COLOR_TABLE_EVEN_ROW);
		} else {
			component.setBackground(Constants.COLOR_TABLE_ODD_ROW);
		}

		imptRowIndexList = SummaryReport.getImptRowIndexList();
		for (Integer i : imptRowIndexList) {
			if (row == i) {
				component.setBackground(Constants.COLOR_TABLE_IMPT_ROW);
				component.setForeground(Constants.COLOR_TABLE_TEXT_IMPT);
			}
		}
		overdueRowIndexList = SummaryReport.getOverdueIndexList();
		for (Integer i : overdueRowIndexList) {
			if (row == i) {
				component.setBackground(Constants.COLOR_TABLE_OVERDUE_ROW);
				component.setForeground(Constants.COLOR_TABLE_TEXT);
			}
		}
		if (row == selectedRow) {
			component.setBackground(Constants.COLOR_TABLE_ROW_HIGHLIGHT);
			component.setForeground(Constants.COLOR_TABLE_TEXT_HIGHLIGHT);
		}

	}
}
