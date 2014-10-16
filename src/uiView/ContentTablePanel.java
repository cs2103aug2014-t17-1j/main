package uiView;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import taskDo.Task;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class ContentTablePanel extends JPanel implements Observer,KeyListener{
	private ArrayList<Task> taskList; 
	private JTable contentTable;
	private JScrollPane jsp;
	private int rowSelected;
	private UiParent parent;
	
	public ContentTablePanel(UiParent parent){
		this.parent = parent; 
		setPreferredSize(new Dimension(500,400));
		taskList = SummaryReport.getDisplayList();
		removeAllComponentsFromCentrePanel();
		setContentIntoTable();
		setBackground(Constants.COLOR_CENTRE_PANEL_BG);
		
	}


	private void addListActionListener() {
		contentTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent select) {
				rowSelected = contentTable.getSelectedRow();
				parent.setRowSelected(rowSelected);
			}
			
		});
		
	}
	
	public int getSelectedTableRow(){
		return rowSelected;
	}


	private void setContentIntoTable() {
		if(taskList.size()!=0){
			String []columnTitle = Constants.COLUMNTITLES;
			contentTable = new JTable(changeToTwoDArray(taskList),columnTitle){
				public boolean isCellEditable(int row, int column){
					return false;
				};
				
			};
			setTableCellProperties(contentTable);
			setContentTableColumnWidth(contentTable);
			setContentTableProperties();
			setJScrollPanePropCentrePane();
			add(jsp);	
			addListActionListener();
		}
		
	}
	
	

	private void setContentTableProperties() {
		contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		contentTable.getTableHeader().setReorderingAllowed(false);
		contentTable.getTableHeader().setForeground(Constants.COLOR_TABLE_HEADER_TEXT);
		contentTable.getTableHeader().setResizingAllowed(false);
		contentTable.getTableHeader().setBackground(Constants.COLOR_TABLE_HEADER_BG);
		contentTable.setGridColor(Constants.COLOR_TABLE_GRID);
		contentTable.setRowHeight(40);
		contentTable.setRowSelectionAllowed(true);
		contentTable.setColumnSelectionAllowed(false);
		contentTable.requestFocus();
		contentTable.setFocusable(true);
		tabKeyPressedAction();
		f2KeyPressedAction();
		f1KeyPressedAction();
	
		contentTable.addFocusListener(new FocusListener(){

			@Override
			public void focusGained(FocusEvent arg0) {
				// TODO Auto-generated method stub
				contentTable.setRowSelectionInterval(0, 0);
				contentTable.changeSelection(0, 0, false, false);
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				// TODO Auto-generated method stub
				contentTable.clearSelection();
			}
			
		});
	}


	private void tabKeyPressedAction() {
		contentTable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0), "Changed Focus");
		contentTable.getActionMap().put("Changed Focus", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("TAB PRESSED IN TABLE");
				parent.pressedTab(false);
				
			}
			
		});
	}
	
	private void f1KeyPressedAction() {
		contentTable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F1,0), "Event");
		contentTable.getActionMap().put("Event", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				parent.pressedF1();
				
			}
			
		});
	}
	
	private void f2KeyPressedAction() {
		contentTable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0), "e");
		System.out.println("F2 KEy is pressed");
		contentTable.getActionMap().put("e", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				parent.pressedF2();
				
			}
			
		});
	}

	private void removeAllComponentsFromCentrePanel() {
		removeAll();
		
	}
	private String[][] changeToTwoDArray(ArrayList<Task> taskList){
		String tableContent[][] = new String [taskList.size()][3];
		System.out.println("CHANGE TO TWOD ARRAY "+taskList.size());
		for (int i = 0; i < taskList.size(); i++){
			tableContent[i][0] = (i+1)+"";
			tableContent[i][1] = taskList.get(i).getDescription();
			tableContent[i][2] = " ";
 		}
		return tableContent;
	}
	
	private void setContentTableColumnWidth(JTable contentTable) {
		contentTable.getColumnModel().getColumn(0).setMaxWidth(20);
		contentTable.getColumnModel().getColumn(1).setMaxWidth(600);
		contentTable.getColumnModel().getColumn(2).setMaxWidth(100);
	}
	
	private void setTableCellProperties(JTable contentTable) {
		for(int i =0 ; i < taskList.size(); i++){
			contentTable.getColumnModel().getColumn(0).setCellRenderer(new CustomTableRender());
			contentTable.getColumnModel().getColumn(1).setCellRenderer(new CustomTableRender());
			contentTable.getColumnModel().getColumn(2).setCellRenderer(new CustomTableRender());	
		}
	}
	
	private void setJScrollPanePropCentrePane() {
		jsp = new JScrollPane(contentTable);
		TitledBorder jScrollTitledBorder = BorderFactory.createTitledBorder(null,Constants.HEADER_TAKSLIST, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), ColorBox.colorPool[24]);
		jsp.setBorder(jScrollTitledBorder);
		jsp.setPreferredSize(new Dimension(450,380));
		jsp.setBackground(Constants.COLOR_JSCROLL_BG);
		jsp.getViewport().setBackground(Constants.COLOR_JSCROLL_BG);
	}

	@Override
	public void update() {
		taskList = SummaryReport.getDisplayList();
		removeAllComponentsFromCentrePanel();
		setContentIntoTable();
		setBackground(Constants.COLOR_CENTRE_PANEL_BG);
		UiViewModifier.update();
		
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
