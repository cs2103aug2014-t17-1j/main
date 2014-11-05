package uiView;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableCellRenderer;

public class LineWrapCellRenderer  extends JTextArea implements TableCellRenderer {	
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
		
		setText((String) value);
		DefaultTableColor.setDefaultBackGroundColor(this, row, table.getSelectedRow());
        setSize(table.getColumnModel().getColumn(column).getWidth(),
				40);
        
        if (table.getRowHeight(row) != getPreferredSize().height) {
			table.setRowHeight(row, getPreferredSize().height);
		}
       
        return this;
    }

}