package uiView;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import taskDo.Executor;
import taskDo.Parser;

/*
 * @author Paing Zin Oo(Jack)
 */
public class UiViewModifier implements KeyListener,WindowListener{

	private static JFrame mainFrame;

	private static Executor executor; 

	private static UIPanelList uiList;
	private HeaderPanel headerPanel;
	private HelpPanel helpPanel;
	private static CommandBoxPanel commandBoxPanel;
	private static DetailPanel detailPanel; 
	private static ContentTablePanel contentPanel;
	
	
	
	public UiViewModifier(){
		executor = new Executor();
		mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
		

		uiList = new UIPanelList();
		headerPanel = new HeaderPanel();
		mainFrame.add(headerPanel,BorderLayout.NORTH);
		
		helpPanel = new HelpPanel();
		mainFrame.add(helpPanel,BorderLayout.WEST);
		
		commandBoxPanel = new CommandBoxPanel();
		mainFrame.add(commandBoxPanel,BorderLayout.SOUTH);
		
		detailPanel = new DetailPanel();
		
		contentPanel = new ContentTablePanel();
		mainFrame.add(contentPanel,BorderLayout.CENTER);
		
		uiList.addUI(commandBoxPanel);
		uiList.addUI(headerPanel);
		uiList.addUI(contentPanel);

		
	    setJFrameProperties();
	}
	
	public static void updateAllPanels(){
		uiList.notifyUIs();
	}
	
	public static void parseToParser(String command){
		if(command!=null){
			if(Parser.parseString(command)){
				System.out.println("Parse String reached here");
				executor.execute();
			}
			updateAllPanels();
		}
	}

	private void setJFrameProperties() {
		mainFrame.setTitle("Task.Do"); 
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.addWindowListener(this);
		mainFrame.addKeyListener(this);
	}
	
	//private void createRightDetailPanel() {
		// TODO Auto-generated method stub
//		rightDetailPanel = new JPanel(new GridLayout(helpCommand.length,1));
//		rightDetailPanel.setPreferredSize(new Dimension(420,400));
//		rightDetailPanel.setBorder(BorderFactory.createTitledBorder(null,"HELP PANEL", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), ColorBox.colorPool[24]));
//		rightDetailPanel.setBackground(Color.BLACK);
//		for(int i = 0 ; i < helpCommand.length; i++){
//			JLabel lbl_helpCommand = new JLabel(helpCommand[i]);
//			lbl_helpCommand.setForeground(ColorBox.colorPool[24]);
//			rightDetailPanel.add(lbl_helpCommand);
//			
//		}
//		mainFrame.add(detailPanel,BorderLayout.EAST);
//	}

//	private void initBtmPanel() {
//		btmPanel = new JPanel(new BorderLayout());
//		feedBack_msg = new JLabel("",JLabel.LEFT);
//		feedBack_msg.validate();
//		feedBack_msg.setForeground(ColorBox.colorPool[24]);
//		btmPanel.add(feedBack_msg,BorderLayout.NORTH);
//	    initCommandBox();
//	    btmPanel.add(commandBox,BorderLayout.SOUTH);
//	    btmPanel.setBackground(Color.BLACK);
//	    mainFrame.add(btmPanel,BorderLayout.SOUTH);
//	    commandBox.requestFocusInWindow();
//	}
//
//	private void initCommandBox() {
//		//final JTextField commandBox = new JTextField(); 
//		commandBox = new JTextField();
//		if(typeCount == 0 ){
//			setIntroTextInCommandBox();
//		}
//		
//		commandBox.requestFocusInWindow();
//	    commandBox.addActionListener(new ActionListener(){
//		 	/*
//	    	 * (non-Javadoc)
//	    	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
//	    	 */
//			@Override
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				
//				 command= commandBox.getText();
//				// controller.setUserCommand(command);
//				// controller.parseToParser();
//				 commandBox.setText("");
//				 System.out.println(command);
//			}
//	    	
//	    });
//	   commandBox.addKeyListener(this);
//
//	}

//	private void setIntroTextInCommandBox() {
//		commandBox.setForeground(Color.GRAY);
//		commandBox.setText("Enter your command here" );
//		
//	}

//	private void generateCentrePanel() {
//		taskList = SummaryReport.getDisplayList();
//		System.out.println("GENERERATE CENTRE PANEL ARRAYLIST SIZE" + taskList.size());
//		removeAllComponentsFromCentrePanel();
//		refreshFrame();
//		if(taskList.size()!=0){
//			String []columnTitle = {"ID","Description"," "};
//			contentTable = new JTable(changeToTwoDArray(taskList),columnTitle){
//				public boolean isCellEditable(int row, int column){
//					return false;
//				};
//				
//			};
//			setTableCellProperties(contentTable);
//			
//			contentTable.setRowHeight(40);
//			setContentTableColumnWidth(contentTable);
//			contentTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//			contentTable.getTableHeader().setReorderingAllowed(false);
//			contentTable.getTableHeader().setForeground(ColorBox.colorPool[24]);
//			contentTable.getTableHeader().setResizingAllowed(false);
//			contentTable.getTableHeader().setBackground(Color.black);
//			contentTable.setGridColor(Color.CYAN);
//			
//			setJScrollPanePropCentrePane();
//			centrePanel.add(jsp);
//			Action pressed = new AbstractAction(){
//
//				@Override
//				public void actionPerformed(ActionEvent arg0) {
//					
//					System.out.println("Presdddddd");
//				}
//				
//			};
//			setKeyAction("F2",contentTable,pressed);
//			
//			contentTable.setRowSelectionInterval(0, 0);
//			contentTable.setRowSelectionAllowed(true);
//			contentTable.setColumnSelectionAllowed(false);
//			contentTable.changeSelection(1, 1, false, false);
//			contentTable.requestFocus();
//			contentTable.setFocusable(true);
//			contentTable.addFocusListener(new FocusListener(){
//
//				@Override
//				public void focusGained(FocusEvent arg0) {
//					// TODO Auto-generated method stub
//					contentTable.changeSelection(1, 1, false, false);
//				}
//
//				@Override
//				public void focusLost(FocusEvent arg0) {
//					// TODO Auto-generated method stub
//					
//				}
//				
//			});
//			
//		}
//		centrePanel.setBackground(Color.BLACK);
//		mainFrame.add(centrePanel,BorderLayout.CENTER);
//		refreshFrame();
//	}
//	
//	private void setKeyAction(String key,JComponent component, Action action){
//		component.getInputMap().put(KeyStroke.getKeyStroke(key), "action");
//		component.getActionMap().put("action",action);
//	}
//
//	private void setJScrollPanePropCentrePane() {
//		jsp = new JScrollPane(contentTable);
//		TitledBorder jScrollTitledBorder = BorderFactory.createTitledBorder(null,"Tasks List", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, Font.getFont("times new roman"), ColorBox.colorPool[24]);
//		jsp.setBorder(jScrollTitledBorder);
//		jsp.setPreferredSize(new Dimension(450,380));
//		jsp.setBackground(Color.BLACK);
//		jsp.getViewport().setBackground(Color.BLACK);
//	}
//
//	private void setTableCellProperties(JTable contentTable) {
//		for(int i =0 ; i < taskList.size(); i++){
//			contentTable.getColumnModel().getColumn(0).setCellRenderer(new CustomTableRender());
//			contentTable.getColumnModel().getColumn(1).setCellRenderer(new CustomTableRender());
//			contentTable.getColumnModel().getColumn(2).setCellRenderer(new CustomTableRender());	
//		}
//	}
//
//	private void setContentTableColumnWidth(JTable contentTable) {
//		contentTable.getColumnModel().getColumn(0).setMaxWidth(20);
//		contentTable.getColumnModel().getColumn(1).setMaxWidth(600);
//		contentTable.getColumnModel().getColumn(2).setMaxWidth(100);
//	}
//	
//	private String[][] changeToTwoDArray(ArrayList<Task> taskList){
//		String tableContent[][] = new String [taskList.size()][3];
//		System.out.println("CHANGE TO TWOD ARRAY "+taskList.size());
//		for (int i = 0; i < taskList.size(); i++){
//			tableContent[i][0] = (i+1)+"";
//			tableContent[i][1] = taskList.get(i).getDescription();
//			//tableContent[i][2] = taskList.get(i).getDueDate().toLocalDate().toString();
//			tableContent[i][2] = " ";
// 		}
//		return tableContent;
//	}

//	private void refreshFrame() {
//		uiList.notifyUIs();
//		mainFrame.pack();
//		mainFrame.revalidate();
//		mainFrame.repaint();
//	}
	
//	
//	private void removeAllComponentsFromCentrePanel() {
//		centrePanel.removeAll();
//	}

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
		
	}
	
	public static void pressedF1(){
		if(isDetailPanelExisting()){
			mainFrame.remove(detailPanel);
		} else{
			createdetailPanel();
		}
		updateAllPanels();
	}
	
	public static void pressedF3(){
		
	}
	
	private static void createdetailPanel() {
		mainFrame.add(detailPanel,BorderLayout.EAST);	
	}

	private static boolean isDetailPanelExisting(){
		if(detailPanel != null){
			if(detailPanel.isDisplayable()){
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

	public static void update() {
		// TODO Auto-generated method stub
		mainFrame.pack();
		mainFrame.revalidate();
		mainFrame.repaint();
		
	}

	public static void pressedTab(boolean isCommandBox) {
		if(isCommandBox){
			contentPanel.requestFocusInWindow();
		} else{
			commandBoxPanel.setFocusToCommandBox();
		}		
	}

}
