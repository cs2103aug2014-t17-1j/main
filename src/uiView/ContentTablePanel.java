package uiView;

import java.awt.Color;
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

import taskDo.Task;
import commonClasses.Constants;
import commonClasses.SummaryReport;

public class ContentTablePanel extends JPanel implements Observer,KeyListener{
	private ArrayList<Task> taskList; 
	private JTable contentTable;
	private JScrollPane jsp;
	
	public ContentTablePanel(){
		setPreferredSize(new Dimension(500,400));
		taskList = SummaryReport.getDisplayList();
		removeAllComponentsFromCentrePanel();
		setContentIntoTable();
		setBackground(Color.BLACK);
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
		}
	}
	
	

	private void setContentTableProperties() {
		contentTable.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0),"");
		contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		contentTable.getTableHeader().setReorderingAllowed(false);
		contentTable.getTableHeader().setForeground(ColorBox.colorPool[24]);
		contentTable.getTableHeader().setResizingAllowed(false);
		contentTable.getTableHeader().setBackground(Color.black);
		contentTable.setGridColor(Color.CYAN);
		contentTable.setRowHeight(40);
		contentTable.setRowSelectionAllowed(true);
		contentTable.setColumnSelectionAllowed(false);
		contentTable.requestFocus();
		contentTable.setFocusable(true);
		contentTable.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB,0), "Changed Focus");
		contentTable.getActionMap().put("Changed Focus", new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.out.println("TAB PRESSED IN TABLE");
				UiViewModifier.pressedTab(false);
				
			}
			
		});
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
		jsp.setBackground(Color.BLACK);
		jsp.getViewport().setBackground(Color.BLACK);
	}

	@Override
	public void update() {
		taskList = SummaryReport.getDisplayList();
		removeAllComponentsFromCentrePanel();
		setContentIntoTable();
		setBackground(Color.BLACK);
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
