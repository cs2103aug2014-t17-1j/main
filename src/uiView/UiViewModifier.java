package uiView;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import parser.ParsedResult;
import parser.Parser;
import taskDo.Executor;
import taskDo.UpdateSummaryReport;

import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;
import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

/* The main controller for all the panels 
 * 
 * @author Paing Zin Oo(Jack) A0112581N
 */
public class UiViewModifier extends JFrame implements WindowListener, UiParent {
	private static final long serialVersionUID = 1L;
	private JFrame mainFrame;
	private Executor executor;
	private UiPanelList uiList;
	private HeaderPanel headerPanel;
	private CommandBoxPanel commandBoxPanel;
	private DetailPanel detailPanel;
	private ContentTablePanel contentPanel;
	private int rowSelected;
	private Parser parser;
	private ParsedResult parseResult;
	private boolean isMinimized;
	private Provider provide;
	private int x = 0;
	private int y = 0;

	//@author Paing Zin Oo(Jack)  A0112581N
	public UiViewModifier() {
		provide = Provider.getCurrentProvider(false);
		setMinimized(false);
		mainFrame = this;
		removeOriginalBoundaryFrame();
		rowSelected = Constants.DEFAULT_ROW_SELECTED;
		initParserAndExecutor();

		mainFrame.setLayout(new BorderLayout());
		initObserverList();
		setJFrameProperties();
		updateFrame();
		new SystemTrayManager(this, provide);
		setFocusToCommandBox();
		addGlobalKey(provide);
	}

	private void initObserverList() {
		uiList = new UiPanelList();
		initCommandBoxPanel();
		headerPanel = new HeaderPanel(new GridBagLayout(), this);
		JPanel parentContentPanel = initContentPanel();
		mainFrame.add(headerPanel, BorderLayout.NORTH);
		mainFrame.add(parentContentPanel, BorderLayout.CENTER);
		mainFrame.add(commandBoxPanel, BorderLayout.SOUTH);
		uiList.addUI(contentPanel);
		uiList.addUI(commandBoxPanel);
	}

	private void initParserAndExecutor() {
		parser = new Parser();
		executor = new Executor();
		UpdateSummaryReport.init();
	}

	private void removeOriginalBoundaryFrame() {
		mainFrame.setUndecorated(true);
		handleDrag(mainFrame);
	}

	private JPanel initContentPanel() {
		contentPanel = new ContentTablePanel(this);
		JPanel parentContentPanel = new JPanel();
		parentContentPanel.add(contentPanel);
		parentContentPanel.setBorder(Constants.EMPTY_BORDER_CONTENT_TABLE);
		parentContentPanel.setBackground(Constants.COLOR_CENTRE_PANEL_BG);
		return parentContentPanel;
	}

	private void initCommandBoxPanel() {
		commandBoxPanel = new CommandBoxPanel(this);
		commandBoxPanel.setBorder(Constants.EMPTY_BORDER_COMMAND_BOX);
		commandBoxPanel.setFocusToCommandBox();
	}

	public void updateAllPanels() {
		uiList.notifyUIs();
	}

	public void passToParser(String command) {
		if (command != null && !command.trim().isEmpty()) {
			parseResult = parser.parseString(command);
			if (parseResult.getIsExecutorApplicable()) {
				executor.execute(parseResult);
			}
			if (parseResult.getCommandType().equals(CommandType.EXIT)) {
				exitApp();
			}
			updateAllPanels();
			updateDetailPanel();
			removeDetailPanel();
			contentPanel.selectRowHightlight(SummaryReport
					.getRowIndexHighlight());
			updateFrame();
		}
	}

	private void exitApp() {
		provide.reset();
		provide.stop();
		System.exit(0);

	}

	private void addGlobalKey(Provider provide) {
		HotKeyListener quickLaunch = new QuickLaunchHotKey(this);
		provide.register(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.ALT_MASK | InputEvent.SHIFT_MASK), quickLaunch);
	}

	private void setJFrameProperties() {
		ImageIcon icon = new ImageIcon(getClass().getResource(
				Constants.STRING_IMG_MAIN_ICON));
		mainFrame.setIconImage(icon.getImage());
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.pack();
		mainFrame.addWindowListener(this);
		setFrametoCentre();
	}

	private void setFrametoCentre() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(dim.width / 2 - mainFrame.getSize().width / 2,
				dim.height / 2 - mainFrame.getSize().height / 2);
	}

	public void pressedF1() {
		if (isDetailPanelExisting()) {
			mainFrame.remove(detailPanel);
		} else {
			createDetailPanel(HotKeyType.F1);

		}
		updateAllPanels();
		updateFrame();
	}

	public void pressedF3() {
		if (isDetailPanelExisting()) {
			mainFrame.remove(detailPanel);
		} else {
			createDetailPanel(HotKeyType.F2);
			uiList.addUI(detailPanel);
		}
		updateAllPanels();
		detailPanel.setFocustoTable();
		updateFrame();
	}

	private void createDetailPanel(HotKeyType hotkey) {
		switch (hotkey) {
		case F1:
			detailPanel = new DetailPanel(HotKeyType.F1, this);
			break;
		case F3:
			detailPanel = new DetailPanel(SummaryReport.getDisplayList().get(
					rowSelected));
			break;
		case F2:
			detailPanel = new DetailPanel(HotKeyType.F2, this);
			break;
		default:
			break;

		}
		mainFrame.add(detailPanel, BorderLayout.EAST);
	}

	public void updateDetailPanel() {
		if (rowSelected != Constants.DEFAULT_ROW_SELECTED) {
			if (isDetailPanelExisting()) {
				mainFrame.remove(detailPanel);
			}
			if (SummaryReport.getRowIndexHighlight() != Constants.NOTHING_SELECTED) {
				detailPanel = new DetailPanel(SummaryReport.getDisplayList()
						.get(SummaryReport.getRowIndexHighlight()));
			}
			
			mainFrame.add(detailPanel, BorderLayout.EAST);
			detailPanel.revalidate();
			updateFrame();
		}
	}

	private boolean isDetailPanelExisting() {
		if (detailPanel != null) {
			if (detailPanel.isDisplayable()) {
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

	public void setRowSelected(int selected) {
		rowSelected = selected;
	}

	public void pressedTab(boolean isCommandBox) {
		if (isCommandBox) {
			contentPanel.requestFocusInWindow();
		} else {
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
		setMinimized(true);
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

	public void removeDetailPanel() {
		if (detailPanel != null) {
			mainFrame.remove(detailPanel);
		}
		setFocusToCommandBox();
		updateFrame();
	}

	public void setFocusToCommandBox() {
		commandBoxPanel.setFocusToCommandBox();
	}

	public JFrame getMainFrame() {
		return mainFrame;
	}

	public boolean isMinimized() {
		return isMinimized;
	}

	public void setMinimized(boolean isMinimized) {
		this.isMinimized = isMinimized;
	}
}
