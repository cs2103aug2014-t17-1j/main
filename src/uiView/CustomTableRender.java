package uiView;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import commonClasses.Constants;
import commonClasses.SummaryReport;
/*
 * @author Paing Zin Oo(Jack)
 */
public class CustomTableRender extends DefaultTableCellRenderer{
	public ArrayList<Integer> imptRowIndexList = new ArrayList<Integer>();
	public Component getTableCellRendererComponent(JTable table, Object value,
		    boolean isSelected, boolean hasFocus, int row, int column) {

		   Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
		     row, column);

		   //Set foreground color
		   rendererComp.setForeground(Constants.COLOR_TABLE_TEXT);

		   //Set background color
		   imptRowIndexList = SummaryReport.getImptRowIndexList();
		   if(row %2 == 0){
			   rendererComp.setBackground(Constants.COLOR_TABLE_EVEN_ROW);
		   } else{
			   rendererComp.setBackground(Constants.COLOR_TABLE_ODD_ROW);
		   }
	
		   for(Integer i: imptRowIndexList){
			   if(row == i){
				   System.out.println("IMPT is "+i);
				   rendererComp.setBackground(Constants.COLOR_TABLE_IMPT_ROW);
				   rendererComp.setForeground(Constants.COLOR_TABLE_TEXT_IMPT);
			   }
		   }
		   
		   //Set background while isSelected
		   if(isSelected){
			   rendererComp.setBackground(Constants.COLOR_TABLE_ROW_HIGHLIGHT);
			   rendererComp.setForeground(Constants.COLOR_TABLE_TEXT_HIGHLIGHT);
		   }
		  
		   setBorder(noFocusBorder);
		   return rendererComp ;
		  }
	

}
