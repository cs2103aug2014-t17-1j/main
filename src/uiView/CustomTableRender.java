package uiView;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
/*
 * @author Paing Zin Oo(Jack)
 */
public class CustomTableRender extends DefaultTableCellRenderer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Integer> imptRowIndexList = new ArrayList<Integer>();
	public Component getTableCellRendererComponent(JTable table, Object value,
		    boolean isSelected, boolean hasFocus, int row, int column) {

		   Component rendererComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
		     row, column);

		   DefaultTableColor.setDefaultBackGroundColor(rendererComp, row, table.getSelectedRow());		
		   setBorder(noFocusBorder);
		   
		   return rendererComp ;
		  }
	

}
