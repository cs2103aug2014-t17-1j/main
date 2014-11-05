package uiView;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import commonClasses.Constants;
import commonClasses.SummaryReport;

public class LineWrapCellRenderer  extends JTextArea implements TableCellRenderer {	
	public ArrayList<Integer> imptRowIndexList = new ArrayList<Integer>();

	
	
	public LineWrapCellRenderer(){
		super();
		setLineWrap(true);
		setWrapStyleWord(true);
		setOpaque(true);
		this.setBorder(new EmptyBorder(15,5,15,15));
	}
	
	@Override
    public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column) {
		

    	
		
	
        
        setSize(table.getColumnModel().getColumn(column).getWidth(),
				40);
        
        if (table.getRowHeight(row) != getPreferredSize().height) {
			table.setRowHeight(row, getPreferredSize().height);
		}
       

		 
 

		   //Set background color
		   imptRowIndexList = SummaryReport.getImptRowIndexList();
		   if(row %2 == 0){
			   setBackground(Constants.COLOR_TABLE_EVEN_ROW);
		   } else{
			   setBackground(Constants.COLOR_TABLE_ODD_ROW);
		   }
	
		   for(Integer i: imptRowIndexList){
			   if(row == i){
				   setBackground(Constants.COLOR_TABLE_IMPT_ROW);
//				   setForeground(Constants.COLOR_TABLE_TEXT_IMPT);
				   setForeground(table.getForeground());
			   }
		   }
		   
		   //Set background while isSelected
		   if(isSelected){
			  setBackground(Constants.COLOR_TABLE_ROW_HIGHLIGHT);
//			  setForeground(Constants.COLOR_TABLE_TEXT_HIGHLIGHT);
			  setForeground(table.getSelectionForeground());
		   }
		
		   //setBorder(noFocusBorder);
        System.out.println("LINE WRAPPER");
        setText((String) value);
        return this;
    }

}