package uiView;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import taskDo.Executor;
import taskDo.UpdateSummaryReport;
import Parser.ParsedResult;
import Parser.Parser;
import commonClasses.Constants;
import commonClasses.SummaryReport;

/*
 * @author Paing Zin Oo(Jack)
 */
public class UiViewModifier extends JFrame implements WindowListener,UiParent{

	private JFrame mainFrame;

	private Executor executor; 

	private UIPanelList uiList;
	private HeaderPanel headerPanel;
	private ShorcutPanel helpPanel;
	private CommandBoxPanel commandBoxPanel;
	private DetailPanel detailPanel; 
	private ContentTablePanel contentPanel;
	private int rowSelected;
	private Parser parser;
	private ParsedResult parseResult;
	
	
	int x =0;
	int y =0;
	
	public UiViewModifier(){
		
		mainFrame = this;
		mainFrame.setUndecorated(true);
		handleDrag(mainFrame);
	

		System.out.println("SCREEN SIZE "+Constants.SCREEN_SIZE.height+"  WID"+Constants.SCREEN_SIZE.width);
		rowSelected = Constants.DEFAULT_ROW_SELECTED;
		parser = new Parser();
		executor = new Executor();
		//mainFrame = new JFrame();
		mainFrame.setLayout(new BorderLayout());
		

		uiList = new UIPanelList();
		headerPanel = new HeaderPanel(new GridBagLayout(),this);
		mainFrame.add(headerPanel,BorderLayout.NORTH);
		
		//helpPanel = new ShorcutPanel();
		//mainFrame.add(helpPanel,BorderLayout.WEST);
		
		
		commandBoxPanel = new CommandBoxPanel(this);
		commandBoxPanel.setBorder(new EmptyBorder(15,25,15,25));
		commandBoxPanel.setFocusToCommandBox();
		UpdateSummaryReport.init();
		contentPanel = new ContentTablePanel(this);
		JPanel parentContentPanel = new JPanel();
		parentContentPanel.add(contentPanel);
		parentContentPanel.setBorder(new EmptyBorder(15,25,15,25));
		parentContentPanel.setBackground(Constants.COLOR_CENTRE_PANEL_BG);
		mainFrame.add(parentContentPanel, BorderLayout.CENTER);
		
		
		mainFrame.add(commandBoxPanel,BorderLayout.SOUTH);
		
		uiList.addUI(contentPanel);
		uiList.addUI(commandBoxPanel);
		setJFrameProperties();
		updateFrame();
		
	}
	
	public void updateAllPanels(){
		System.out.println("UPDATE");
		uiList.notifyUIs();
	}
	
	public Component getCurrentFocusComponent(){
		return mainFrame.getFocusOwner();
	}
	
	public boolean isFocusOnCommandBox(){
		if(getCurrentFocusComponent() instanceof JTextField){
			return true;
		}
		return false;
	}
	
	public boolean isFocusOnJTable(){
		if(getCurrentFocusComponent() instanceof JTable){
			return true;
		}
		return false;
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
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("image/Task.Do Icon.png"));
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.addWindowListener(this);
		
		//Center Screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width /2 - mainFrame.getSize().width / 2, dim.height / 2 - mainFrame.getSize().height / 2);
	}

	
	public void pressedF1(){
		if(isDetailPanelExisting()){
			mainFrame.remove(detailPanel);
		} else{
			createDetailPanel(HotKeyType.F1);
			
		}
		updateAllPanels();
		updateFrame();
	}
	

	
	public void pressedF2(){
		if(rowSelected != Constants.DEFAULT_ROW_SELECTED){
			if(isDetailPanelExisting()){
				mainFrame.remove(detailPanel);
			} else{
				createDetailPanel(HotKeyType.F2);
				
			}
			updateAllPanels();
			setFocus();
			updateFrame();
		}
	
	}
	
	public void pressedF3(){
		if(isDetailPanelExisting()){
			mainFrame.remove(detailPanel);
		} else {
			createDetailPanel(HotKeyType.F2);
			detailPanel.setFocustoTable();
			uiList.addUI(detailPanel);
		}
		updateAllPanels();
		
		updateFrame();
	}
	
	private void createDetailPanel(HotKeyType hotkey) {
		switch(hotkey){
			case F1:
				detailPanel = new DetailPanel(HotKeyType.F1,this);
				break;
			case F3:
				detailPanel = new DetailPanel(SummaryReport.getDisplayList().get(rowSelected));
				break;
			case F2:
				detailPanel = new DetailPanel(HotKeyType.F2,this);
				break;
			default:
				break;
				
		}
		mainFrame.add(detailPanel,BorderLayout.EAST);	
	}
	
	public void updateDetailPanel(){
		if(rowSelected != -1){
			System.out.println(rowSelected);
			if(isDetailPanelExisting()){
				mainFrame.remove(detailPanel);
			}
			detailPanel = new DetailPanel(SummaryReport.getDisplayList().get(rowSelected));
			mainFrame.add(detailPanel,BorderLayout.EAST);
			detailPanel.revalidate();
			updateFrame();
		}
	}

	private boolean isDetailPanelExisting(){
		if(detailPanel != null){
			if(detailPanel.isDisplayable()){
				return true;
			}
		}
		return false;
	}
	
	public void updateFrame() {
		mainFrame.pack();
		mainFrame.revalidate();
		mainFrame.repaint();
		
		
	}
	
	public void setFocus(){
		if(isFocusOnJTable()){
			System.out.println("FOCUS ON TABLE");
			contentPanel.highlightRow();
		}
		if(isFocusOnCommandBox()){
			System.out.println("FOCUS ON COMMANDBOX");
			commandBoxPanel.setFocusToCommandBox();
		}
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
	
    public void handleDrag(final JFrame frame) {
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                y = me.getY();
            }
        });
        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                me.translatePoint(me.getComponent().getLocation().x - x, me
                        .getComponent().getLocation().y - y);
                frame.setLocation(me.getX(), me.getY());
            }
        });
    }
    
    public void removeDetailPanel(){
    	mainFrame.remove(detailPanel);
    	setFocusToCommandBox();
    	updateFrame();
    }
    
    public void setFocusToCommandBox(){
    	commandBoxPanel.setFocusToCommandBox();
    }

	public JFrame getMainFrame() {
		return mainFrame;
	}
    
    



}
