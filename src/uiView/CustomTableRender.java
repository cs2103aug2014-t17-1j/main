package uiView;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import taskDo.ColorBox;

public class CustomTableRender extends DefaultTableCellRenderer{
	public Component getTableCellRendererComponent(JTable table, Object value,
		    boolean isSelected, boolean hasFocus, int row, int column) {

		   Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
		     row, column);

		   //Set foreground color
		   rendererComp.setForeground(ColorBox.colorPool[24]);

		   //Set background color
		   rendererComp .setBackground(Color.BLACK);
		   
		   return rendererComp ;
		  }

}
