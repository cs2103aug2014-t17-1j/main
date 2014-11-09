package uiView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import taskDo.Category;
import taskDo.CategoryList;
import taskDo.Task;
import commonClasses.Constants;

/* This is the class for additional panel on the right of the frame
 * and varies according such as categoryList Table, Help Panel or details Panel
 * 
 * 
 */
public class DetailPanel extends JPanel implements Observer {
	private static final long serialVersionUID = 1L;
	// @author Paing Zin Oo(Jack) A0112581N
	private UiParent parent;
	private JTable categoryListTable;
	private SoftShadowJPanel detailPanel;

	public DetailPanel(HotKeyType hotkey, UiParent parent) {
		switch (hotkey) {
			case F1:
				createHelpPanel();
				break;
			case F2:
				createCategoryListPanel(parent);
				break;
			default:
				break;
		}

	}

	private void createHelpPanel() {
		String[] helpCommands = Constants.HELPCOMMANDS;
		setUpLayout(helpCommands, Constants.HEADER_HELP, 1);
		for (int i = 0; i < helpCommands.length; i++) {
			JLabel lbl_helpCommand = new JLabel(helpCommands[i]);
			lbl_helpCommand.setForeground(Constants.COLOR_DETAIL_PANEL_TEXT);
			add(lbl_helpCommand);

		}
	}

	private void setUpLayout(String[] helpCommands, String title, int numOfCol) {
		setLayout(new GridLayout(helpCommands.length, numOfCol));
		setPreferredSize(Constants.DIMENSION_HELP_PANEL);
		setBorder(BorderFactory.createTitledBorder(null, title,
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, Constants.FONT_TIME_NEW_ROMAN,
				Constants.COLOR_DETAIL_PANEL_TEXT));
		setBackground(Constants.COLOR_DETAIL_PANEL_BG);
	}

	// @author Boo Tai Yi A0111936J
	public DetailPanel(Task task) {

		String taskAttribute[] = Constants.TASK_ATTRIBUTE;
		String taskDetail[] = changetoArr(task);
		setLayout(new BorderLayout());
		setPreferredSize(Constants.DIMENSION_DETAIL_PANEL);
		setBorder(Constants.EMPTY_BORDER_DETAIL_PANEL);
		setBackground(Constants.COLOR_DETAIL_PANEL_BG);
		Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
		detailPanel = new SoftShadowJPanel();
		detailPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		addAttributesAndDetails(taskAttribute, taskDetail, size, c);
		add(detailPanel);

	}

	private void addAttributesAndDetails(String[] taskAttribute,
			String[] taskDetail, Dimension size, GridBagConstraints c) {
		addFirstAttribute(taskAttribute, size, c);
		addFirstDetail(taskDetail, size, c);
		addSecondAttribute(taskAttribute, size, c);
		addSecondDetail(taskDetail, size, c);
		addThirdAttribute(taskAttribute, size, c);
		addFourthAttribute(taskAttribute, size, c);
		addThirdDetail(taskDetail, size, c);
		addFourthDetail(taskDetail, size, c);
		addFifthAttribute(taskAttribute, size, c);
		addFifthDetail(taskDetail, size, c);
		addSixthAttribute(taskAttribute, size, c);
		addSixthDetail(taskDetail, size, c);
	}

	private void addSixthDetail(String[] taskDetail, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskDetail[5]);
		label.setBackground(Color.white);
		label.setForeground(Color.black);
		label.setFont(new Font("Calibiri", Font.PLAIN, 15));
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.049)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 9;
		detailPanel.add(label, c);
	}

	private void addSixthAttribute(String[] taskAttribute, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskAttribute[5]);
		label.setBackground(Constants.COLOR_DETAIL_PANEL_HEADER_BG);
		label.setForeground(Color.white);
		label.setFont(new Font("Calibiri", Font.BOLD, 15));
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.049)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 8;
		detailPanel.add(label, c);
	}

	private void addFifthDetail(String[] taskDetail, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskDetail[4]);
		label.setBackground(Color.white);
		label.setForeground(Color.black);
		label.setFont(new Font("Calibiri", Font.PLAIN, 15));
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.049)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 7;
		detailPanel.add(label, c);
	}

	private void addFifthAttribute(String[] taskAttribute, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskAttribute[4]);
		label.setBackground(Constants.COLOR_DETAIL_PANEL_HEADER_BG);
		label.setForeground(Color.white);
		label.setFont(new Font("Calibiri", Font.BOLD, 15));
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.049)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 6;
		detailPanel.add(label, c);
	}

	private void addFourthDetail(String[] taskDetail, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskDetail[3]);
		label.setBackground(Color.white);
		label.setForeground(Color.black);
		label.setFont(new Font("Calibiri", Font.PLAIN, 15));
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.049)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 5;
		detailPanel.add(label, c);
	}

	private void addThirdDetail(String[] taskDetail, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskDetail[2]);
		label.setBackground(Color.white);
		label.setForeground(Color.black);
		label.setFont(new Font("Calibiri", Font.PLAIN, 15));
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.049)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 5;
		detailPanel.add(label, c);
	}

	private void addFourthAttribute(String[] taskAttribute, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskAttribute[3]);
		label.setBackground(Constants.COLOR_DETAIL_PANEL_HEADER_BG);
		label.setForeground(Color.white);
		label.setOpaque(true);
		label.setFont(new Font("Calibiri", Font.BOLD, 15));
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.049)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 4;
		detailPanel.add(label, c);
	}

	private void addThirdAttribute(String[] taskAttribute, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskAttribute[2]);
		label.setBackground(Constants.COLOR_DETAIL_PANEL_HEADER_BG);
		label.setForeground(Color.white);
		label.setFont(new Font("Calibiri", Font.BOLD, 15));
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.049)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 4;
		detailPanel.add(label, c);
	}

	private void addSecondDetail(String[] taskDetail, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskDetail[1]);
		label.setBackground(Color.white);
		label.setForeground(Color.black);
		label.setFont(new Font("Calibiri", Font.PLAIN, 15));
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.049)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 3;
		detailPanel.add(label, c);
	}

	private void addSecondAttribute(String[] taskAttribute, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskAttribute[1]);
		label.setBackground(Constants.COLOR_DETAIL_PANEL_HEADER_BG);
		label.setForeground(Color.white);
		label.setFont(new Font("Calibiri", Font.BOLD, 15));
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.049)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		detailPanel.add(label, c);
	}

	private void addFirstDetail(String[] taskDetail, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskDetail[0]);
		label.setBackground(Color.white);
		label.setForeground(Color.black);
		label.setFont(new Font("Calibiri", Font.PLAIN, 15));
		label.setOpaque(true);
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.050)));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		detailPanel.add(label, c);
	}

	private void addFirstAttribute(String[] taskAttribute, Dimension size,
			GridBagConstraints c) {
		JLabel label;
		label = new JLabel(taskAttribute[0]);
		label.setBackground(Constants.COLOR_DETAIL_PANEL_HEADER_BG);
		label.setForeground(Color.white);
		label.setFont(new Font("Calibiri", Font.BOLD, 15));
		label.setPreferredSize(new Dimension(0, (int) (size.height * 0.051)));
		label.setOpaque(true);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1.0;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		detailPanel.add(label, c);
	}

	// @author Paing Zin Oo(Jack) A0112581N
	private void createCategoryListPanel(UiParent uiparent) {
		parent = uiparent;
		ArrayList<Category> categoryList = CategoryList.getCategoryList();

		setBackground(Constants.COLOR_CENTRE_PANEL_BG);
		setPreferredSize(Constants.DIMENSION_DETAIL_PANEL);
		setBorder(Constants.EMPTY_BORDER_CATEGORY_TABLE);
		if (categoryList.size() != 0) {
			createTableWithContent(categoryList);
		} else {
			createEmptyTable();
		}
		setTableProperties(categoryList);

	}

	private void createEmptyTable() {
		DefaultTableModel model = new DefaultTableModel(0,
				Constants.CATEGORYKEYS.length);
		model.setColumnIdentifiers(Constants.CATEGORYKEYS);
		categoryListTable = new JTable(model);
		categoryListTable.setFocusable(false);
	}

	private void createTableWithContent(ArrayList<Category> categoryList) {
		String dataArr[][] = changetoTwoDArr(categoryList);
		categoryListTable = new JTable(dataArr, Constants.CATEGORYKEYS) {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			};
		};
		categoryListTable.setFocusable(true);
		categoryListTable.requestFocus();
		addFocusListener(categoryListTable);
	}

	private void setTableProperties(ArrayList<Category> categoryList) {
		setContentTableColumnWidth(categoryListTable);
		setTableCellProperties(categoryListTable, categoryList);
		setTableHeaderProperties(categoryListTable);
		categoryListTable.setGridColor(Constants.COLOR_TABLE_GRID);
		setKeysPressed(categoryListTable);
		add(setJScrollPanePropCentrePane(categoryListTable));
	}

	public void removeAllComponentsFromPanel() {
		removeAll();
	}

	public void setFocustoTable() {
		if (categoryListTable != null) {
			categoryListTable.requestFocus();
		}

	}

	private void setKeysPressed(JTable categoryListTable) {
		tabKeyPressedAction(categoryListTable);
		f2KeyPressedAction(categoryListTable);
		f1KeyPressedAction(categoryListTable);
	}

	private void tabKeyPressedAction(JTable categoryListTable) {
		categoryListTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0),
				Constants.STRING_EVENT);
		categoryListTable.getActionMap().put(Constants.STRING_EVENT,
				new AbstractAction() {
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						parent.pressedTab(false);
					}
				});
	}

	private void f2KeyPressedAction(JTable categoryListTable) {
		categoryListTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0),
				Constants.STRING_EVENT);
		categoryListTable.getActionMap().put(Constants.STRING_EVENT,
				new AbstractAction() {
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						parent.removeDetailPanel();
					}
				});
	}

	private void f1KeyPressedAction(JTable categoryListTable) {
		categoryListTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0),
				Constants.STRING_EVENT);
		categoryListTable.getActionMap().put(Constants.STRING_EVENT,
				new AbstractAction() {
					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent actionEvent) {
						parent.pressedF1();
					}

				});
	}

	private void setTableHeaderProperties(JTable categoryListTable) {
		categoryListTable.getTableHeader().setReorderingAllowed(false);
		categoryListTable.getTableHeader().setForeground(
				Constants.COLOR_TABLE_HEADER_TEXT);
		categoryListTable.getTableHeader().setResizingAllowed(false);
		categoryListTable.getTableHeader().setBackground(
				Constants.COLOR_TABLE_HEADER_BG);

		DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
		headerRenderer.setBackground(ColorBox.colorPool[7]);
		headerRenderer.setForeground(Color.WHITE);
		headerRenderer.setFont(new Font("Serif", Font.BOLD, 15));

		for (int i = 0; i < categoryListTable.getModel().getColumnCount(); i++) {
			categoryListTable.getColumnModel().getColumn(i)
					.setHeaderRenderer(headerRenderer);
		}
	}

	private void addFocusListener(final JTable categoryListTable) {
		categoryListTable.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent arg0) {
				categoryListTable.changeSelection(0, 0, false, false);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				categoryListTable.clearSelection();
			}
		});
	}

	private void setContentTableColumnWidth(JTable contentTable) {
		contentTable.getColumnModel().getColumn(0)
				.setMaxWidth(Constants.MAX_WIDTH_CATEGORY_NAME);
		contentTable.getColumnModel().getColumn(1)
				.setMaxWidth(Constants.MAX_WIDTH_CATEGORY_COUNT);

	}

	private SoftShadowJPanel setJScrollPanePropCentrePane(
			JTable categoryListTable) {
		SoftShadowJPanel parentJsp = new SoftShadowJPanel();
		JScrollPane jsp = new JScrollPane(categoryListTable);
		jsp.setBorder(Constants.EMPTY_BORDER);
		jsp.setBackground(Constants.COLOR_JSCROLL_BG);
		jsp.getViewport().setBackground(Constants.COLOR_JSCROLL_BG);

		parentJsp.setPreferredSize(Constants.DIMENSION_DETAIL_PANEL);
		parentJsp.add(jsp);
		return parentJsp;
	}

	private void setTableCellProperties(JTable contentTable,
			ArrayList<Category> categoryList) {
		for (int i = 0; i < categoryList.size(); i++) {
			contentTable.getColumnModel().getColumn(0)
					.setCellRenderer(new CategoryCustomTableRender());
			contentTable.getColumnModel().getColumn(1)
					.setCellRenderer(new CategoryCustomTableRender());
		}
	}

	public String[][] changetoTwoDArr(ArrayList<Category> categoryList) {
		String[][] result = new String[categoryList.size()][2];
		for (int i = 0; i < categoryList.size(); i++) {
			result[i][0] = categoryList.get(i).getName();
			result[i][1] = categoryList.get(i).getCount()
					+ Constants.STRING_STRING;
		}
		return result;
	}

	public String[] changetoArr(Task task) {
		String arr[] = new String[6];
		assert task.getTitle() != null;
		assert task.getCategory() != null;
		arr[1] = Constants.STRING_OPEN_HTML + task.getCategory()
				+ Constants.STRING_CLOSE_HTML;
		if (task.getCategory() == null) {
			arr[1] = Constants.STRING_NA;
		}
		if (task.getStartDate() == null) {
			arr[2] = Constants.STRING_DASH;
		} else {
			DateTimeFormatter dateFormat = DateTimeFormat
					.forPattern(Constants.STRING_DATEFORMAT);
			arr[2] = dateFormat.print(task.getStartDate());
		}
		if (task.getDueDate().getYear() == Constants.NILL_YEAR) {
			arr[3] = Constants.STRING_SOMEDAY;
		} else {
			DateTimeFormatter dateFormat = DateTimeFormat
					.forPattern(Constants.STRING_DATEFORMAT);
			arr[3] = dateFormat.print(task.getDueDate());
		}
		if (task.isImportant()) {
			arr[5] = Constants.STRING_YES;
		} else {
			arr[5] = Constants.STRING_NO;
		}
		if (task.isCompleted()) {
			arr[4] = Constants.STRING_YES;
		} else {
			arr[4] = Constants.STRING_NO;
		}
		assert (task.getNote() != null);
		if (task.getNote() == null) {
			arr[0] = Constants.STRING_NA;
		} else {
			arr[0] = Constants.STRING_OPEN_HTML + task.getNote()
					+ Constants.STRING_CLOSE_HTML;
		}

		return arr;
	}

	public boolean isExisting() {
		if (isDisplayable()) {
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		if (categoryListTable != null) {
			removeAll();
			createCategoryListPanel(parent);
			repaint();
			revalidate();
			parent.updateFrame();
		}

	}

}
