package uiView;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import commonClasses.SummaryReport;

import taskDo.Executor;
import Parser.ParsedResult;
import Parser.Parser;

/*
 * @author Paing Zin Oo(Jack)
 */
public class UiViewModifier implements KeyListener,WindowListener,UiParent{

	private static JFrame mainFrame;

	private static Executor executor; 

	private UIPanelList uiList;
	private HeaderPanel headerPanel;
	private ShorcutPanel helpPanel;
	private CommandBoxPanel commandBoxPanel;
	private DetailPanel detailPanel; 
	private ContentTablePanel contentPanel;
	private int rowSelected;
	private Parser parser;
	private ParsedResult parseResult;
	
	public UiViewModifier(){
		parser = new Parser();
		executor = new Executor();
		mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
		

		uiList = new UIPanelList();
		headerPanel = new HeaderPanel();
		mainFrame.add(headerPanel,BorderLayout.NORTH);
		
		helpPanel = new ShorcutPanel();
		mainFrame.add(helpPanel,BorderLayout.WEST);
		
		commandBoxPanel = new CommandBoxPanel(this);
		mainFrame.add(commandBoxPanel,BorderLayout.SOUTH);
		
		
		
		contentPanel = new ContentTablePanel(this);
		mainFrame.add(contentPanel,BorderLayout.CENTER);
		
		uiList.addUI(commandBoxPanel);
		uiList.addUI(headerPanel);
		uiList.addUI(contentPanel);

		
	    setJFrameProperties();
	}
	
	public void updateAllPanels(){
		uiList.notifyUIs();
	}
	
	public void passToParser(String command){
		if(command!=null){
			parseResult = parser.parseString(command);
			if(parseResult.getValidationResult()){
				System.out.println("Parse String reached here");
				executor.execute(parseResult);
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

	
	public void pressedF1(){
		if(isDetailPanelExisting()){
			mainFrame.remove(detailPanel);
		} else{
			createDetailPanel(HotKeyType.F1);
		}
		updateAllPanels();
	}
	
	public void pressedF3(){
		
	}
	
	public void pressedF2(){
		if(rowSelected != -1){
			if(isDetailPanelExisting()){
				mainFrame.remove(detailPanel);
			} else{
				createDetailPanel(HotKeyType.F2);
			}
			updateAllPanels();
		}
	}
	
	private void createDetailPanel(HotKeyType hotkey) {
		switch(hotkey){
			case F1:
				detailPanel = new DetailPanel();
				break;
			case F2:
				detailPanel = new DetailPanel(SummaryReport.getDisplayList().get(rowSelected));
				break;
			default:
				break;
				
		}
		mainFrame.add(detailPanel,BorderLayout.EAST);	
	}

	private boolean isDetailPanelExisting(){
		if(detailPanel != null){
			if(detailPanel.isDisplayable()){
				return true;
			}
		}
		return false;
	}
	
	public static void update() {
		// TODO Auto-generated method stub
		mainFrame.pack();
		mainFrame.revalidate();
		mainFrame.repaint();
		
	}

	public void setRowSelected (int selected){
		rowSelected = selected;
	}
	
	public void pressedTab(boolean isCommandBox) {
		if(isCommandBox){
			contentPanel.requestFocusInWindow();
		} else{
			commandBoxPanel.setFocusToCommandBox();
		}		
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
	

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	

}
