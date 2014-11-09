package uiView;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

import commonClasses.Constants;

/* This is class for line wrapper used in table cell 
 *
 */
public class LineWrapCellRenderer extends JTextArea implements
		TableCellRenderer {
	private static final long serialVersionUID = 1L;

	// @author Paing Zin Oo(Jack) A0112581N
	public LineWrapCellRenderer() {
		super();
		setLineWrap(true);
		setWrapStyleWord(true);
		setOpaque(true);
		this.setBorder(Constants.EMPTY_BORDER_LINEWRAPPER);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {

		setText((String) value);
		DefaultTableColor.setDefaultBackGroundColor(this, row,
				table.getSelectedRow());
		setSize(table.getColumnModel().getColumn(column).getWidth(), 40);

		if (table.getRowHeight(row) != getPreferredSize().height) {
			table.setRowHeight(row, getPreferredSize().height);
		}

		return this;
	}

}