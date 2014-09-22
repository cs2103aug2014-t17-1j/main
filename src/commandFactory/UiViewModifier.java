package commandFactory;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import org.jdesktop.xswingx.PromptSupport;

/*
 * @author Paing Zin Oo(Jack)
 */
public class UiViewModifier extends Frame implements KeyListener,WindowListener{
	String command;
	JFrame mainFrame;
	JPanel centrePanel;
	JPanel headerPanel;
	JLabel lbl_header;
	JPanel btmPanel;
	JLabel feedBack_msg;
	ArrayList<Task> taskList;
	
	
	public String getCommand() {
		return command;
	}
	
	public UiViewModifier(){
		mainFrame = new JFrame();
		setLayout(new BorderLayout());
		
		/*
		 * Centre Panel and scroll pane added 
		 */
		centrePanel = new JPanel();
		centrePanel.setPreferredSize(new Dimension(400,150));
		int v=ScrollPaneConstants. VERTICAL_SCROLLBAR_AS_NEEDED;
		int h=ScrollPaneConstants. HORIZONTAL_SCROLLBAR_AS_NEEDED;
		JScrollPane jsp = new JScrollPane(centrePanel,v,h);
		
		/*
		 * Header panel and it lies on North part of JFrame
		 */
		headerPanel = new JPanel();
		lbl_header = new JLabel("HEADER");
		headerPanel.add(lbl_header);
		add(headerPanel, BorderLayout.NORTH);
		
		/*
		 * Testing for the output
		 */
		generateCentrePanel();
		
		initBtmPanel(jsp);
	    
	    setTitle("Task.Do"); // "super" Frame sets title
	    setSize(600, 300);         // "super" Frame sets initial size
	    setVisible(true);   
	    addWindowListener(this);
	}

	private void initBtmPanel(JScrollPane jsp) {
		btmPanel = new JPanel(new BorderLayout());
		feedBack_msg = new JLabel("jfklsdjfld",JLabel.LEFT);
		feedBack_msg.validate();
		btmPanel.add(feedBack_msg,BorderLayout.NORTH);
	
	    final JTextField commandBox = initCommandBox();
	   
	    btmPanel.add(commandBox,BorderLayout.SOUTH);
	    add(btmPanel,BorderLayout.SOUTH);
	    add(jsp,BorderLayout.CENTER);
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
				 commandBox.setText("");
				 System.out.println(command);
			}
	    	
	    });
	   commandBox.setForeground(Color.BLACK);
		return commandBox;
	}

	private void generateCentrePanel() {
		taskList = SummaryReport.getDsiplayList();
		if(taskList.size()!=0){
			for (int i=0 ;i<taskList.size() ; i++){
				
				JTextArea taskDes = new JTextArea(2, 50);
				taskDes.setEditable(false);
				taskDes.setText(taskList.get(i).getDescription());
				taskDes.setBackground(new Color(200, 200, 200));
				taskDes.setForeground(Color.RED);
			    centrePanel.add(taskDes);
			}
		}
	}
	
	public void updateUi(){
		if(SummaryReport.getFeedBackMsg()==null){
			feedBack_msg.setVisible(false);
		}
		centrePanel.removeAll();
		generateCentrePanel();
		
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
