package uiView;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import taskDo.Category;
import taskDo.CategoryList;
import taskDo.Task;
import commonClasses.Constants;
/*
 * @author Paing Zin Oo(Jack)
 */
public class DetailPanel extends JPanel implements Observer{
	private UiParent parent;
	private JTable categoryListTable;
	public DetailPanel(HotKeyType hotkey,UiParent parent){
		switch(hotkey){
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
		String []helpCommands = Constants.HELPCOMMANDS;
		setUpLayout(helpCommands,Constants.HEADER_HELP,1);
		for(int i = 0 ; i < helpCommands.length; i++){
			JLabel lbl_helpCommand = new JLabel(helpCommands[i]);
			lbl_helpCommand.setForeground(Constants.COLOR_DETAIL_PANEL_TEXT);
			add(lbl_helpCommand);
			
		}
	}
	
	private void setUpLayout(String[] helpCommands,String title,int numOfCol) {
		setLayout(new GridLayout(helpCommands.length,numOfCol));
		setPreferredSize(Constants.DIMENSION_HELP_PANEL);
		setBorder(BorderFactory.createTitledBorder(null,title, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), Constants.COLOR_DETAIL_PANEL_TEXT));
		setBackground(Constants.COLOR_DETAIL_PANEL_BG);
	}
	
	public DetailPanel(Task task){
		String taskAttribute[] = Constants.TASK_ATTRIBUTE;
		String taskDetail[] = changetoArr(task);
		System.out.println("DETAIL PANEL FOR TASK");
		setLayout(new GridLayout(6,1));
		setPreferredSize(Constants.DIMENSION_DETAIL_PANEL);
		setBorder(BorderFactory.createTitledBorder(null,Constants.HEADER_DETAIL, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), Constants.COLOR_DETAIL_PANEL_TEXT));
		setBackground(Constants.COLOR_DETAIL_PANEL_BG);
		
		for(int i = 0 ; i < taskAttribute.length; i++){
			JLabel label = new JLabel();
			label.setText("<html>"+taskAttribute[i]+ "   "+ taskDetail[i]+"</html>");
			label.setForeground(Constants.COLOR_DETAIL_PANEL_TEXT);
			add(label);
			label.setBorder(BorderFactory.createLineBorder(Color.white));
				
			}
		
	}
	
	public void createCategoryListPanel(UiParent uiparent){
		parent = uiparent;
		ArrayList<Category> categoryList = CategoryList.getCategoryList();
		
		setBackground(Constants.COLOR_CENTRE_PANEL_BG);
		setPreferredSize(Constants.DIMENSION_DETAIL_PANEL);
		setBorder(new EmptyBorder(15,25,15,25));
		if(categoryList.size() != 0 ){
			createTableWithContent(categoryList);
		} else{
			createEmptyTable();
		}
		setTableProperties(categoryList);
		
	}
	private void createEmptyTable() {
		DefaultTableModel model = new DefaultTableModel(0,Constants.CATEGORYKEYS.length);
		model.setColumnIdentifiers(Constants.CATEGORYKEYS);
		categoryListTable = new JTable(model);
		categoryListTable.setFocusable(false);
	}
	private void createTableWithContent(ArrayList<Category> categoryList) {
		String dataArr[][] = changetoTwoDArr(categoryList);
		categoryListTable = new JTable(dataArr,Constants.CATEGORYKEYS){
			public boolean isCellEditable(int row, int column){
				return false;
			};
		};
		categoryListTable.setFocusable(true);
		categoryListTable.requestFocus();
		addFocusListener(categoryListTable);
	}
	private void setTableProperties(ArrayList<Category> categoryList) {
		setContentTableColumnWidth(categoryListTable);
		setTableCellProperties(categoryListTable,categoryList);
		setTableHeaderProperties(categoryListTable);
		categoryListTable.setGridColor(Constants.COLOR_TABLE_GRID);
		setKeysPressed(categoryListTable);
		add(setJScrollPanePropCentrePane(categoryListTable));
	}
	
	
	public void removeAllComponentsFromPanel(){
		removeAll();
	}
	public void setFocustoTable(){
		categoryListTable.requestFocus();
	}
	private void setKeysPressed(JTable categoryListTable) {
		tabKeyPressedAction(categoryListTable);
		f2KeyPressedAction(categoryListTable);
		f1KeyPressedAction(categoryListTable);
	}
	
	private void tabKeyPressedAction(JTable categoryListTable) {
		categoryListTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "Changed Focus");
		categoryListTable.getActionMap().put("Changed Focus", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("TAB PRESSED IN TABLE");
				parent.pressedTab(false);
			}
		});
	}
	
	private void f2KeyPressedAction(JTable categoryListTable) {
		categoryListTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0), "F3");
		categoryListTable.getActionMap().put("F3", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("F3 Presed IN TABLE");
				parent.removeDetailPanel();
				//parent.pressedF3();
			}
		});
	}
	
	private void f1KeyPressedAction(JTable categoryListTable) {
		categoryListTable.getInputMap().put(
				KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0), "Event");
		categoryListTable.getActionMap().put("Event", new AbstractAction() {
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
		headerRenderer.setBackground(ColorBox.colorPool[957]);
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
				// TODO Auto-generated method stub
				// contentTable.setRowSelectionInterval(0, 0);
				categoryListTable.changeSelection(0, 0, false, false);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				categoryListTable.clearSelection();
			}
		});
	}
	
	private void setContentTableColumnWidth(JTable contentTable) {
		contentTable.getColumnModel().getColumn(0).setMaxWidth(300);
		contentTable.getColumnModel().getColumn(1).setMaxWidth(100);
		
	}
	
	private SoftShadowJPanel setJScrollPanePropCentrePane(JTable categoryListTable) {
		SoftShadowJPanel parentJsp = new SoftShadowJPanel();
		JScrollPane jsp = new JScrollPane(categoryListTable);
		// TitledBorder jScrollTitledBorder = BorderFactory.createTitledBorder(
		// null, Constants.HEADER_TAKSLIST,
		// TitledBorder.DEFAULT_JUSTIFICATION,
		// TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"),
		// Constants.COLOR_TABLE_HEADER_TEXT);
		jsp.setBorder(new EmptyBorder(0, 0, 0, 0));
		jsp.setBackground(Constants.COLOR_JSCROLL_BG);
		jsp.getViewport().setBackground(Constants.COLOR_JSCROLL_BG);

		parentJsp.setPreferredSize(Constants.DIMENSION_DETAIL_PANEL);
		parentJsp.add(jsp);
		return parentJsp;
	}
	
	private void setTableCellProperties(JTable contentTable,ArrayList<Category> categoryList) {
		for (int i = 0; i < categoryList.size(); i++) {
			contentTable.getColumnModel().getColumn(0)
					.setCellRenderer(new CategoryCustomTableRender());
			contentTable.getColumnModel().getColumn(1)
					.setCellRenderer(new CategoryCustomTableRender());
	}
	}
	
	public String[][] changetoTwoDArr(ArrayList<Category> categoryList){
		String [][] result = new String[categoryList.size()][2];
		for(int i = 0 ; i < categoryList.size(); i++){
			System.out.println("COUNT"+categoryList.get(i).getCount());
			result[i][0] = categoryList.get(i).getName();
			result[i][1] = categoryList.get(i).getCount()+Constants.STRING_STRING;
		}
		return result;
	}
	public String [] changetoArr(Task task){
		String arr[] = new String[6];
		assert task.getTitle() != null;
		arr[0] = task.getTitle();
		assert task.getCategory() != null;
		arr[1] = task.getCategory();
		if(task.getCategory() == null){
			arr[1] = Constants.STRING_NA;
		}
		if(task.getDueDate().getYear()==Constants.NILL_YEAR){
			arr[2] = Constants.STRING_SOMEDAY;
		} else{
			DateTimeFormatter dateFormat = DateTimeFormat.forPattern("dd-MM-yyyy");
			arr[2] = dateFormat.print(task.getDueDate().toLocalDate());
		}
		if(task.isImportant()){
			arr[3] = Constants.STRING_YES;
		} else{
			arr[3] = Constants.STRING_NO;
		}
		if(task.isCompleted()){
			arr[4] = Constants.STRING_YES;
		} else{
			arr[4] = Constants.STRING_NO;
		}
		assert(task.getNote()!=null);
		if(task.getNote()==null){
			arr[5] = Constants.STRING_NA;
		}else{
			arr[5] = task.getNote();
		}
		
		return arr;
	}
	public boolean isExisting(){
		if(isDisplayable()){
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(categoryListTable!=null){
			removeAll();
			createCategoryListPanel(parent);
			System.out.println("REFRESH IN CATEGORY");
			repaint();
			revalidate();
			parent.updateFrame();
		}
		
		
	}
	
}
