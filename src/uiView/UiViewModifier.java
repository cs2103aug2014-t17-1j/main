package uiView;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import commonClasses.ColorBox;
import commonClasses.SummaryReport;
import taskDo.Controller;
import taskDo.Task;

/*
 * @author Paing Zin Oo(Jack)
 */
public class UiViewModifier extends Frame implements KeyListener,WindowListener{
	private int typeCount = 0;
	private int taskSeq = 1;
	private JFrame mainFrame;
	private JPanel centrePanel;
	private JPanel headerPanel;
	private JLabel lbl_header;
	private JPanel btmPanel;
	private JLabel feedBack_msg;
	private JPanel leftHelpPanel;
	private JPanel rightDetailPanel;
	private JTable contentTable;
	private JScrollPane jsp;
	private JTextField commandBox;
	private ArrayList<Task> taskList;
	private Controller controller;
	private String command;
	private String [] helpCommand = {"<html><h3><u><i><b>Main Commands</b></i></u></h3></html>",
			"add [task]",
			"edit [index]",
			"delete [index]",
			"display [date]",
			"",
			"<html><h3><u><i>Common Optional Commands</i></h3></u></html>",
			"<html><i>(Addtional commands that works with main commands)</i></html>",
			"category [name]",
			"due [duedate]",
			"from [startdate] to [duedate]",
			"impt [Y/N]",
			"",
			"Example: add [Homework1] due [5th oct]",
			"",
			"<html><h3><u><i>Specific Optional Commands</i></h3></u></html>",
			"<html><i>(Addtional commands thats only works with specific main commands)</i></html>",
			"For Edit: task [taskdescription]",
			"",
			"Example: edit [index] task [new description]"
			
	};
	private String [] shortcuts = { "F1 ==> Help",
			"F2 ==> View Details",
			"F3 ==> View Category List"
			
	};
	
	public UiViewModifier(){
		controller = new Controller();
		mainFrame = new JFrame();
		setLayout(new BorderLayout());
		
		/*
		 * Centre Panel and scroll pane added 
		 */
		centrePanel = new JPanel();

		centrePanel.setPreferredSize(new Dimension(500,400));

		/*
		 * Header panel and it lies on North part of JFrame
		 */
		initHeaderPanel();
		/*
		 * Left Help panel include F1,F2,F3 description
		 */
		createLeftHelpPanel();
		
		//createRightDetailPanel();
		
		generateCentrePanel();
		initBtmPanel();
	    setJFrameProperties();
	}

	private void setJFrameProperties() {
		setTitle("Task.Do"); 
	    setVisible(true);
	    setResizable(false);
	    pack();
	    addWindowListener(this);
	    addKeyListener(this);
	}

	private void initHeaderPanel() {
		headerPanel = new JPanel();
		lbl_header = new JLabel();
		lbl_header.setForeground(ColorBox.colorPool[24]);
		headerPanel.add(lbl_header);
		headerPanel.setBackground(Color.BLACK);
		add(headerPanel, BorderLayout.NORTH);
	}

	private void createRightDetailPanel() {
		// TODO Auto-generated method stub
		rightDetailPanel = new JPanel(new GridLayout(helpCommand.length,1));
		rightDetailPanel.setPreferredSize(new Dimension(420,400));
		rightDetailPanel.setBorder(BorderFactory.createTitledBorder(null,"HELP PANEL", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), ColorBox.colorPool[24]));
		rightDetailPanel.setBackground(Color.BLACK);
		for(int i = 0 ; i < helpCommand.length; i++){
			JLabel lbl_helpCommand = new JLabel(helpCommand[i]);
			lbl_helpCommand.setForeground(ColorBox.colorPool[24]);
			rightDetailPanel.add(lbl_helpCommand);
			
		}
		add(rightDetailPanel,BorderLayout.EAST);
	}

	private void createLeftHelpPanel() {
		
		leftHelpPanel = new JPanel(new GridLayout(shortcuts.length,1));
		leftHelpPanel.setBorder(BorderFactory.createTitledBorder(null,"SHORTCUTS", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), ColorBox.colorPool[24]));
		leftHelpPanel.setPreferredSize(new Dimension(200,350));
		for(int i = 0; i < shortcuts.length; i++){
			JLabel label_shortcut = new JLabel (shortcuts[i]);
			label_shortcut.setForeground(ColorBox.colorPool[24]);
			leftHelpPanel.add(label_shortcut);
		}
		leftHelpPanel.setBackground(Color.BLACK);
		add(leftHelpPanel,BorderLayout.WEST);
	}

	

	private void initBtmPanel() {
		btmPanel = new JPanel(new BorderLayout());
		feedBack_msg = new JLabel("",JLabel.LEFT);
		feedBack_msg.validate();
		feedBack_msg.setForeground(ColorBox.colorPool[24]);
		btmPanel.add(feedBack_msg,BorderLayout.NORTH);
	    initCommandBox();
	    btmPanel.add(commandBox,BorderLayout.SOUTH);
	    btmPanel.setBackground(Color.BLACK);
	    add(btmPanel,BorderLayout.SOUTH);
	    commandBox.requestFocusInWindow();
	}

	private void initCommandBox() {
		//final JTextField commandBox = new JTextField(); 
		commandBox = new JTextField();
		if(typeCount == 0 ){
			setIntroTextInCommandBox();
		}
		
		commandBox.requestFocusInWindow();
		
	  //  PromptSupport.setPrompt("Enter your command here", commandBox);
	    commandBox.addActionListener(new ActionListener(){
		 	/*
	    	 * (non-Javadoc)
	    	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    	 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			//	 PromptSupport.setPrompt("Enter your command here", commandBox);
				
				 command= commandBox.getText();
				 controller.setUserCommand(command);
				 controller.parseToParser();
				 commandBox.setText("");
				 System.out.println(command);
			}
	    	
	    });
	   commandBox.addKeyListener(this);

	}

	private void setIntroTextInCommandBox() {
		commandBox.setForeground(Color.GRAY);
		commandBox.setText("Enter your command here" );
		
	}

	private void generateCentrePanel() {
		taskList = SummaryReport.getDisplayList();
		System.out.println("GENERERATE CENTRE PANEL ARRAYLIST SIZE" + taskList.size());
		removeAllComponentsFromCentrePanel();
		refreshFrame();
		if(taskList.size()!=0){
			String []columnTitle = {"ID","Description"," "};
			contentTable = new JTable(changeToTwoDArray(taskList),columnTitle){
				public boolean isCellEditable(int row, int column){
					return false;
				};
				
			};
			setTableCellProperties(contentTable);
			
			contentTable.setRowHeight(40);
			setContentTableColumnWidth(contentTable);
			contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			contentTable.getTableHeader().setReorderingAllowed(false);
			contentTable.getTableHeader().setForeground(ColorBox.colorPool[24]);
			contentTable.getTableHeader().setResizingAllowed(false);
			contentTable.getTableHeader().setBackground(Color.black);
			contentTable.setGridColor(Color.CYAN);
			
			setJScrollPanePropCentrePane();
			centrePanel.add(jsp);
			contentTable.setRowSelectionInterval(0, 0);
			contentTable.setRowSelectionAllowed(true);
			contentTable.setColumnSelectionAllowed(false);
			contentTable.changeSelection(1, 1, false, false);
			//contentTable.requestFocus();
			contentTable.setFocusable(true);
			contentTable.addFocusListener(new FocusListener(){

				@Override
				public void focusGained(FocusEvent arg0) {
					// TODO Auto-generated method stub
					contentTable.changeSelection(1, 1, false, false);
				}

				@Override
				public void focusLost(FocusEvent arg0) {
					// TODO Auto-generated method stub
					
				}
				
			});
			
		}
		centrePanel.setBackground(Color.BLACK);
		add(centrePanel,BorderLayout.CENTER);
		refreshFrame();
	}

	private void setJScrollPanePropCentrePane() {
		jsp = new JScrollPane(contentTable);
		TitledBorder jScrollTitledBorder = BorderFactory.createTitledBorder(null,"Tasks List", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), ColorBox.colorPool[24]);
		jsp.setBorder(jScrollTitledBorder);
		jsp.setPreferredSize(new Dimension(450,380));
		jsp.setBackground(Color.BLACK);
		jsp.getViewport().setBackground(Color.BLACK);
	}

	private void setTableCellProperties(JTable contentTable) {
		for(int i =0 ; i < taskList.size(); i++){
			contentTable.getColumnModel().getColumn(0).setCellRenderer(new CustomTableRender());
			contentTable.getColumnModel().getColumn(1).setCellRenderer(new CustomTableRender());
			contentTable.getColumnModel().getColumn(2).setCellRenderer(new CustomTableRender());	
		}
	}

	private void setContentTableColumnWidth(JTable contentTable) {
		contentTable.getColumnModel().getColumn(0).setMaxWidth(20);
		contentTable.getColumnModel().getColumn(1).setMaxWidth(600);
		contentTable.getColumnModel().getColumn(2).setMaxWidth(100);
	}
	
	private String[][] changeToTwoDArray(ArrayList<Task> taskList){
		String tableContent[][] = new String [taskList.size()][3];
		System.out.println("CHANGE TO TWOD ARRAY "+taskList.size());
		for (int i = 0; i < taskList.size(); i++){
			tableContent[i][0] = (i+1)+"";
			tableContent[i][1] = taskList.get(i).getDescription();
			//tableContent[i][2] = taskList.get(i).getDueDate().toLocalDate().toString();
			tableContent[i][2] = " ";
 		}
		return tableContent;
	}

	private void refreshFrame() {
		pack();
		revalidate();
		repaint();
	}
	
	public void updateUi(){
		if(SummaryReport.getFeedBackMsg()==null){
			feedBack_msg.setVisible(false);
		}
		feedBack_msg.setText(SummaryReport.getFeedBackMsg());
		lbl_header.setText(SummaryReport.getHeader());
		generateCentrePanel();
	}

	public String getCommand() {
		return command;
	}
	
	public Controller getControllerObject(){
		return controller;
	}
	private void removeAllComponentsFromCentrePanel() {
		centrePanel.removeAll();
	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
		
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if(typeCount == 0){
			commandBox.setText("");
			commandBox.setForeground(Color.BLACK);
		}
		typeCount++;
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_F1){
			System.out.println("you have entered F1");
			if(isRightPanelExisting()){
				remove(rightDetailPanel);
			}
			else{
				createRightDetailPanel();
			}
			refreshFrame();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_F2){
			remove(rightDetailPanel);
			refreshFrame();
		}
		System.out.println("You Have Entered "+arg0.getKeyText(arg0.getKeyCode()));
		
	}
	
	private boolean isRightPanelExisting(){
		if(rightDetailPanel != null){
			if(rightDetailPanel.isDisplayable()){
				return true;
			}
		}
		return false;
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
