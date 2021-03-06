package uiView;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import taskDo.Task;
import commonClasses.Constants;
import commonClasses.SummaryReport;

/** 
 * This is the content Table Panel where tasks title,seq no and due date
 * 
 */
public class ContentTablePanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	// @author  A0112581N
	private ArrayList<Task> taskList;
	private JTable contentTable;
	private int rowSelected;
	private UiParent parent;
	private SoftShadowJPanel parentJsp;

	public ContentTablePanel(UiParent parent) {
		this.parent = parent;
		setPreferredSize(Constants.DIMENION_TABLE);
		taskList = SummaryReport.getDisplayList();
		removeAllComponentsFromCentrePanel();
		setContentIntoTable();
		setBackground(Constants.COLOR_CENTRE_PANEL_BG);
	}

	private void addListActionListener() {
		contentTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent select) {
						createDetailPanelForSelectedRow();
					}
				});
	}

	public int getSelectedTableRow() {
		return rowSelected;
	}

	private void createDetailPanelForSelectedRow() {
		rowSelected = contentTable.getSelectedRow();
		SummaryReport.setRowIndexHighlight(rowSelected);
		parent.setRowSelected(SummaryReport.getRowIndexHighlight());
		parent.updateDetailPanel();
	}

	private void setContentIntoTable() {
		if (taskList.size() != 0) {
			createTableWithContent();
		} else {
			createEmptyTable();
		}

	}

	private void createTableWithContent() {
		String[] columnTitle = Constants.COLUMNTITLES;
		contentTable = new JTable(changeToTwoDArray(taskList), columnTitle) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};

		};
		setTableCellProperties(contentTable);
		setContentTableColumnWidth(contentTable);
		setContentTableProperties();
		setJScrollPanePropCentrePane();
		addListActionListener();
		add(parentJsp);
	}

	private void createEmptyTable() {
		String[] columnTitle = Constants.COLUMNTITLES;
		DefaultTableModel model = new DefaultTableModel(0, columnTitle.length);
		model.setColumnIdentifiers(columnTitle);
		contentTable = new JTable(model);
		setTableCellProperties(contentTable);
		setContentTableColumnWidth(contentTable);
		setContentTableProperties();
		setJScrollPanePropCentrePane();
		contentTable.setFocusable(false);
		add(parentJsp);
	}

	public void selectRowHightlight(int rowSelected) {
		if (SummaryReport.getRowIndexHighlight() != Constants.NOTHING_SELECTED) {
			contentTable.setRowSelectionInterval(rowSelected, rowSelected);
			createDetailPanelForSelectedRow();
			contentTable.scrollRectToVisible(contentTable.getCellRect(
					rowSelected, 0, true));
		}
	}

	private void setContentTableProperties() {
		setTableHeaderProperties();
		setRowAndColumnSelectionMode();
		contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		contentTable.setDefaultRenderer(Object.class, new CustomTableRender());
		setKeysPressed();
		addFocusListener();
	}

	private void addFocusListener() {
		contentTable.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				contentTable.setBorder(Constants.TABLE_FOCUS_BORDER);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				contentTable.setBorder(Constants.EMPTY_BORDER);

			}
		});
	}

	private void setKeysPressed() {
		tabKeyPressedAction();
		f2KeyPressedAction();
		f1KeyPressedAction();
	}

	private void setRowAndColumnSelectionMode() {
		contentTable.setGridColor(Constants.COLOR_TABLE_GRID);
		contentTable.setRowHeight(Constants.TABLE_HEIGHT);
		contentTable.setRowSelectionAllowed(true);
		contentTable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		contentTable.setColumnSelectionAllowed(false);
	}

	private void setTableHeaderProperties() {
		contentTable.getTableHeader().setReorderingAllowed(false);
		contentTable.getTableHeader().setForeground(
				Constants.COLOR_TABLE_HEADER_TEXT);
		contentTable.getTableHeader().setResizingAllowed(false);
		contentTable.getTableHeader().setBackground(
				Constants.COLOR_TABLE_HEADER_BG);

		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(ColorBox.colorPool[7]);
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setFont(new Font("Serif", Font.BOLD, 15));

		for (int i = 0; i < contentTable.getModel().getColumnCount(); i++) {
			contentTable.getColumnModel().getColumn(i)
					.setHeaderRenderer(headerRenderer);
		}
	}

	private void tabKeyPressedAction() {
		contentTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
				Constants.STRING_EVENT);
		contentTable.getActionMap().put(Constants.STRING_EVENT,
				new AbstractAction() {
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						parent.pressedTab(false);
					}
				});
	}

	private void f2KeyPressedAction() {
		contentTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),
				Constants.STRING_EVENT);
		contentTable.getActionMap().put(Constants.STRING_EVENT,
				new AbstractAction() {

					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						parent.removeDetailPanel();
						parent.pressedF3();
					}
				});
	}

	private void f1KeyPressedAction() {
		contentTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0),
				Constants.STRING_EVENT);
		contentTable.getActionMap().put(Constants.STRING_EVENT,
				new AbstractAction() {
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						parent.pressedF1();
					}

				});
	}

	private void removeAllComponentsFromCentrePanel() {
		removeAll();

	}

	private String[][] changeToTwoDArray(ArrayList<Task> taskList) {
		String tableContent[][] = new String[taskList.size()][3];
		String dueDate = Constants.STRING_STRING;
		for (int i = 0; i < taskList.size(); i++) {
			tableContent[i][0] = (i + 1) + Constants.STRING_STRING;
			tableContent[i][1] = taskList.get(i).getTitle();
			if (taskList.get(i).getDueDate().toLocalDate().getYear() == Constants.NILL_YEAR) {
				dueDate = Constants.STRING_SOMEDAY;
			} else {
				DateTimeFormatter dateFormat = DateTimeFormat
						.forPattern("dd-MM-yyyy");
				dueDate = dateFormat.print(taskList.get(i).getDueDate()
						.toLocalDate());
			}
			tableContent[i][2] = dueDate;
		}
		return tableContent;
	}

	private void setContentTableColumnWidth(JTable contentTable) {
		contentTable.getColumnModel().getColumn(0).setMaxWidth(20);
		contentTable.getColumnModel().getColumn(1).setMaxWidth(600);
		contentTable.getColumnModel().getColumn(2).setMaxWidth(100);
	}

	private void setTableCellProperties(JTable contentTable) {
		contentTable.getColumnModel().getColumn(1)
				.setCellRenderer(new LineWrapCellRenderer());
	}

	private void setJScrollPanePropCentrePane() {
		parentJsp = new SoftShadowJPanel();
		JScrollPane jsp = new JScrollPane(contentTable);
		jsp.setBorder(Constants.EMPTY_BORDER);
		jsp.setBackground(Constants.COLOR_JSCROLL_BG);
		jsp.getViewport().setBackground(Constants.COLOR_JSCROLL_BG);

		parentJsp.setPreferredSize(Constants.DIMESION_JSCROLL_PANEL);
		parentJsp.add(jsp);
	}

	public void highlightRow() {
		contentTable.setRowSelectionInterval(rowSelected, rowSelected);
	}

	@Override
	public void update() {
		taskList = SummaryReport.getDisplayList();
		removeAllComponentsFromCentrePanel();
		setContentIntoTable();
		setBackground(Constants.COLOR_CENTRE_PANEL_BG);
		repaint();
		revalidate();
		parent.updateFrame();

	}
}
