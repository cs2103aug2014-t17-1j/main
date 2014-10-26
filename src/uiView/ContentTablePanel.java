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
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import taskDo.Task;

import commonClasses.Constants;
import commonClasses.SummaryReport;

/*
 * @author Paing Zin Oo(Jack)
 */
public class ContentTablePanel extends JPanel implements Observer {

	private ArrayList<Task> taskList;
	private JTable contentTable;
	// private JScrollPane jsp;
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
						rowSelected = contentTable.getSelectedRow();
						parent.setRowSelected(rowSelected);
						parent.updateDetailPanel();
					}

				});

	}

	public int getSelectedTableRow() {
		return rowSelected;
	}

	private void setContentIntoTable() {
		if (taskList.size() != 0) {
			String[] columnTitle = Constants.COLUMNTITLES;
			contentTable = new JTable(changeToTwoDArray(taskList), columnTitle) {
				public boolean isCellEditable(int row, int column) {
					return false;
				};

			};
			setTableCellProperties(contentTable);
			setContentTableColumnWidth(contentTable);
			setContentTableProperties();
			setJScrollPanePropCentrePane();
			add(parentJsp);
			addListActionListener();
		}

	}

	private void setContentTableProperties() {
		setTableHeaderProperties();
		setRowAndColumnSelectionMode();
		contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		contentTable.requestFocus();
		contentTable.setFocusable(true);
		setKeysPressed();
		addFocusListener();
	}

	private void addFocusListener() {
		contentTable.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				// contentTable.setRowSelectionInterval(0, 0);
				contentTable.changeSelection(0, 0, false, false);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				contentTable.clearSelection();
			}
		});
	}

	private void setKeysPressed() {
		tabKeyPressedAction();
		f2KeyPressedAction();
		f1KeyPressedAction();
		f3KeyPressedAction();
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
		headerRenderer.setBackground(ColorBox.colorPool[957]);
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setFont(new Font("Serif", Font.BOLD, 15));

		for (int i = 0; i < contentTable.getModel().getColumnCount(); i++) {
			contentTable.getColumnModel().getColumn(i)
					.setHeaderRenderer(headerRenderer);
		}
	}

	private void tabKeyPressedAction() {
		contentTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Changed Focus");
		contentTable.getActionMap().put("Changed Focus", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("TAB PRESSED IN TABLE");
				parent.pressedTab(false);
			}
		});
	}

	private void f3KeyPressedAction() {
		contentTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0), "F3");
		contentTable.getActionMap().put("F3", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("F3 Presed IN TABLE");
				parent.pressedF3();
			}
		});
	}

	private void f1KeyPressedAction() {
		contentTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "Event");
		contentTable.getActionMap().put("Event", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				parent.pressedF1();

			}

		});
	}

	private void f2KeyPressedAction() {
		contentTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "e");
		System.out.println("F2 KEy is pressed");
		contentTable.getActionMap().put("e", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				contentTable.clearSelection();
				parent.pressedF2();
				//contentTable.setRowSelectionInterval(rowSelected, rowSelected);
				parent.removeDetailPanel();
			}

		});
	}

	private void removeAllComponentsFromCentrePanel() {
		removeAll();

	}

	private String[][] changeToTwoDArray(ArrayList<Task> taskList) {
		String tableContent[][] = new String[taskList.size()][3];
		String dueDate = "";
		System.out.println("CHANGE TO TWOD ARRAY " + taskList.size());
		for (int i = 0; i < taskList.size(); i++) {
			tableContent[i][0] = (i + 1) + "";
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
		for (int i = 0; i < taskList.size(); i++) {
			contentTable.getColumnModel().getColumn(0)
					.setCellRenderer(new CustomTableRender());
			contentTable.getColumnModel().getColumn(1)
					.setCellRenderer(new CustomTableRender());
			contentTable.getColumnModel().getColumn(2)
					.setCellRenderer(new CustomTableRender());
		}
	}

	private void setJScrollPanePropCentrePane() {
		parentJsp = new SoftShadowJPanel();
		JScrollPane jsp = new JScrollPane(contentTable);
		// TitledBorder jScrollTitledBorder = BorderFactory.createTitledBorder(
		// null, Constants.HEADER_TAKSLIST,
		// TitledBorder.DEFAULT_JUSTIFICATION,
		// TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"),
		// Constants.COLOR_TABLE_HEADER_TEXT);
		jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		jsp.setBackground(Constants.COLOR_JSCROLL_BG);
		jsp.getViewport().setBackground(Constants.COLOR_JSCROLL_BG);

		parentJsp.setPreferredSize(Constants.DIMESION_JSCROLL_PANEL);
		parentJsp.add(jsp);
	}

	public void highlightRow() {
		contentTable.setRowSelectionInterval(rowSelected, rowSelected);
		contentTable.requestFocusInWindow();
	}

	@Override
	public void update() {
		taskList = SummaryReport.getDisplayList();
		removeAllComponentsFromCentrePanel();
		setContentIntoTable();
		setBackground(Constants.COLOR_CENTRE_PANEL_BG);
		parent.updateFrame();

	}
}
