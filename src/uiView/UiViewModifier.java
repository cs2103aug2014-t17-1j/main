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
	private ShorcutPanel helpPanel;
	private static CommandBoxPanel commandBoxPanel;
	private static DetailPanel detailPanel; 
	private static ContentTablePanel contentPanel;
	private static int rowSelected;
	
	public UiViewModifier(){
		executor = new Executor();
		mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
		

		uiList = new UIPanelList();
		headerPanel = new HeaderPanel();
		mainFrame.add(headerPanel,BorderLayout.NORTH);
		
		helpPanel = new ShorcutPanel();
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
	
	public static void passToParser(String command){
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

	public static void setRowSelected (int selected){
		this.rowSelected = selected;
	}
	public static void pressedTab(boolean isCommandBox) {
		if(isCommandBox){
			contentPanel.requestFocusInWindow();
		} else{
			commandBoxPanel.setFocusToCommandBox();
		}		
	}

}
