package uiView;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import commonClasses.Constants;

public class CustomTableRender extends DefaultTableCellRenderer{
	public Component getTableCellRendererComponent(JTable table, Object value,
		    boolean isSelected, boolean hasFocus, int row, int column) {

		   Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
		     row, column);

		   //Set foreground color
		   rendererComp.setForeground(Constants.COLOR_TABLE_TEXT);

		   //Set background color
		   if(row %2 == 0){
			   rendererComp.setBackground(Constants.COLOR_TABLE_EVEN_ROW);
		   } else{
			   rendererComp.setBackground(Constants.COLOR_TABLE_ODD_ROW);
		   }
	
		   
		   
		   //Set background while isSelected
		   if(isSelected){
			   rendererComp.setBackground(Constants.COLOR_TABLE_ROW_HIGHLIGHT);
		   }
		  
		   setBorder(noFocusBorder);
		   return rendererComp ;
		  }
	

}
