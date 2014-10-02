package taskDo;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import org.jdesktop.xswingx.PromptSupport;

/*
 * @author Paing Zin Oo(Jack)
 */
public class UiViewModifier extends Frame implements KeyListener,WindowListener{
	int taskSeq = 1;
	String command;
	JFrame mainFrame;
	JPanel centrePanel;
	JPanel headerPanel;
	JLabel lbl_header;
	JPanel btmPanel;
	JLabel feedBack_msg;
	JPanel leftHelpPanel;
	JPanel rightDetailPanel;
	ArrayList<Task> taskList;
	Controller controller;
	
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
		headerPanel = new JPanel();
		lbl_header = new JLabel("HEADER");
		headerPanel.add(lbl_header);
		add(headerPanel, BorderLayout.NORTH);
		
		/*
		 * Left Help panel include F1,F2,F3 description
		 */
		createLeftHelpPanel();
		
		//createRightDetailPanel();
		
		generateCentrePanel();
		
		initBtmPanel();
	    
	    setTitle("Task.Do"); // "super" Frame sets title
	    //setSize(700, 500);         // "super" Frame sets initial size
	    setVisible(true);  
	    pack();
	    addWindowListener(this);
	    addKeyListener(this);
	}

	private void createRightDetailPanel() {
		// TODO Auto-generated method stub
		JLabel test = new JLabel("jkfsjdlf");
		
		rightDetailPanel = new JPanel();
		rightDetailPanel.add(test);
		rightDetailPanel.setPreferredSize(new Dimension(400,300));
		rightDetailPanel.setBorder(BorderFactory.createTitledBorder("HELP PANEL"));
		add(rightDetailPanel,BorderLayout.EAST);
	}

	private void createLeftHelpPanel() {
		leftHelpPanel = new JPanel(new GridLayout(3,1));
		leftHelpPanel.setBorder(BorderFactory.createTitledBorder("Shortcut"));
		leftHelpPanel.setPreferredSize(new Dimension(200,350));
		JLabel label_f1 = new JLabel ("F1 ==> Help");
		JLabel label_f2 = new JLabel ("F2 ==> View Details");
		JLabel label_f3 = new JLabel ("F3 ==> View Category List");
		leftHelpPanel.add(label_f1);
		leftHelpPanel.add(label_f2);
		leftHelpPanel.add(label_f3);
		add(leftHelpPanel,BorderLayout.WEST);
	}
	
	

	private void initBtmPanel() {
		btmPanel = new JPanel(new BorderLayout());
		feedBack_msg = new JLabel("",JLabel.LEFT);
		feedBack_msg.validate();
		btmPanel.add(feedBack_msg,BorderLayout.NORTH);
	    final JTextField commandBox = initCommandBox();
	    btmPanel.add(commandBox,BorderLayout.SOUTH);
	    add(btmPanel,BorderLayout.SOUTH);
	}

	private JTextField initCommandBox() {
		final JTextField commandBox = new JTextField(); 
	    PromptSupport.setPrompt("Enter your command here", commandBox);
	    commandBox.addActionListener(new ActionListener(){
		 	/*
	    	 * (non-Javadoc)
	    	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	    	 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				 PromptSupport.setPrompt("Enter your command here", commandBox);
				
				 command= commandBox.getText();
				 controller.setUserCommand(command);
				 controller.parseToParser();
				 commandBox.setText("");
				
				 System.out.println(command);
			}
	    	
	    });
	   commandBox.addKeyListener(this);
	   commandBox.setForeground(Color.BLACK);
		return commandBox;
	}

	private void generateCentrePanel() {
		taskList = SummaryReport.getDisplayList();
		System.out.println("GENERERATE CENTRE PANEL ARRAYLIST SIZE" + taskList.size());
		removeAllComponentsFromCentrePanel();
		refreshFrame();
		
		if(taskList.size()!=0){
			String []columnTitle = {"ID","Description","Due Date"};
			JTable contentTable = new JTable(changeToTwoDArray(taskList),columnTitle){
				public boolean isCellEditable(int row, int column){
					return false;
				};
				
			};
			contentTable.setRowHeight(40);
			contentTable.getTableHeader().setReorderingAllowed(false);
			contentTable.getTableHeader().setResizingAllowed(false);
			JScrollPane jsp = new JScrollPane(contentTable);
			jsp.setBorder(BorderFactory.createTitledBorder("Tasks List"));
			jsp.setPreferredSize(new Dimension(450,380));
			centrePanel.add(jsp);
			
		}
		add(centrePanel,BorderLayout.CENTER);
		refreshFrame();
	}
	
	private String[][] changeToTwoDArray(ArrayList<Task> taskList){
		String tableContent[][] = new String [taskList.size()][3];
		System.out.println("CHANGE TO TWOD ARRAY "+taskList.size());
		for (int i = 0; i < taskList.size(); i++){
			tableContent[i][0] = (i+1)+"";
			tableContent[i][1] = taskList.get(i).getDescription();
			tableContent[i][2] = taskList.get(i).getDueDate().toLocalDate().toString();
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
		// TODO Auto-generated method stub
		if(arg0.getKeyCode()==KeyEvent.VK_F1){
			System.out.println("you have entered F1");
			createRightDetailPanel();
			checkForRightPanel();
			refreshFrame();
		}
		if(arg0.getKeyCode()==KeyEvent.VK_F2){
			remove(rightDetailPanel);
			refreshFrame();
		}
		System.out.println("You Have Entered "+arg0.getKeyText(arg0.getKeyCode()));
		
	}
	
	private boolean checkForRightPanel(){
		boolean isExisting = false;
		for (Component c : mainFrame.getRootPane().getComponents()) {
			if(c.equals(rightDetailPanel)){
				
			}
		}
		return isExisting;
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
