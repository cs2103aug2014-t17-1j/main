package uiView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import commandFactory.CommandType;
import commonClasses.Constants;
import commonClasses.SummaryReport;

/* This class is for the commandBox Panel and whenever there is a command 
 * from user, it will be pass to uiViewModifier for further processing
 * 
 */
public class CommandBoxPanel extends JPanel implements KeyListener, Observer {
	private static final long serialVersionUID = -4442146738042473163L;
	// @author Paing Zin Oo(Jack) A0112581N
	private int typeCount;
	private JTextField commandBox;
	private String command;
	private JLabel lblHintMsg;
	private JLabel feedbackMsg;
	private UiParent parent;
	private CommandStack commandStack;
	private String pieceOfCommand;
	private String txtHintMsg;

	public CommandBoxPanel(UiParent parent) {
		pieceOfCommand = Constants.STRING_STRING;
		txtHintMsg = Constants.STRING_STRING;
		commandStack = new CommandStack();
		this.parent = parent;
		setLayout(new BorderLayout());
		initFeedbackMsg();
		initCommandBox();
		initHintMsg();
		add(commandBox, BorderLayout.CENTER);
		setBackground(Constants.COLOR_COMMAND_PANEL_BG);

	}

	private void initHintMsg() {
		lblHintMsg = new JLabel();
		add(lblHintMsg, BorderLayout.SOUTH);
	}

	private void addHintMsg() {
		if (isValidCommandType()) {
			remove(feedbackMsg);
			lblHintMsg.setText(txtHintMsg);
		} else {
			lblHintMsg.setText(Constants.STRING_STRING);
		}
		add(lblHintMsg, BorderLayout.SOUTH);
		revalidate();
		repaint();
		parent.updateFrame();

	}

	private void initFeedbackMsg() {
		assert feedbackMsg != null;
		String feedBack = SummaryReport.getFeedBackMsg();
		feedbackMsg = new JLabel(feedBack, JLabel.LEFT);
	}

	public void setFocusToCommandBox() {
		commandBox.requestFocusInWindow();
	}

	private void initCommandBox() {
		commandBox = new JTextField();
		commandBox.setBorder(Constants.EMPTY_BORDER_COMMAND_BOX_PANEL);
		if (typeCount == 0) {
			setIntroTextInCommandBox();
		}
		setFocusToCommandBox();
		addListenerToCommandBox();

	}

	private void addListenerToCommandBox() {
		commandBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				command = commandBox.getText();
				parent.passToParser(command);
				if (!command.trim().isEmpty()) {
					commandStack.insertCommand(command);
				}
				commandBox.setText(Constants.STRING_STRING);
			}
		});
		commandBox.addKeyListener(this);
	}

	private void setIntroTextInCommandBox() {
		commandBox.setForeground(Color.GRAY);
		commandBox.setText(Constants.STRING_DEFAULT_COMMAND_TEXT);

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		if (typeCount == 0) {
			commandBox.setText(Constants.STRING_STRING);
			commandBox.setForeground(Color.BLACK);
		}
		typeCount++;

		if (arg0.getKeyCode() == KeyEvent.VK_F1) {
			parent.pressedF1();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_F2) {
			parent.pressedF3();
		}
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			commandBox.setText(commandStack.retrieveCommandFromBackwardStack());
		}
		if (arg0.getKeyCode() == KeyEvent.VK_UP) {
			commandBox.setText(commandStack.retrieveCommandFromForwardStack());
		}
		keyBackSpace(arg0);
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		keyBackSpace(arg0);

	}

	private void keyBackSpace(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
			pieceOfCommand = commandBox.getText();
			addHintMsg();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		if (arg0.getKeyChar() != Constants.CHAR_BACKSPACE) {
			pieceOfCommand += arg0.getKeyChar();
			addHintMsg();
		}
	}

	// checking for whether command type is valid or not
	private boolean isValidCommandType() {
		String commandType = pieceOfCommand.trim()
				.split(Constants.STRING_SPACE)[0];
		if (commandType.equalsIgnoreCase(CommandType.ADD.toString()
				.toLowerCase())) {
			txtHintMsg = Constants.HINT[0];
			return true;
		} else if (commandType.equalsIgnoreCase(CommandType.EDIT.toString()
				.toLowerCase())) {
			txtHintMsg = Constants.HINT[1];
			return true;
		} else if (commandType.equalsIgnoreCase(CommandType.DELETE.toString()
				.toLowerCase())) {
			txtHintMsg = Constants.HINT[2];
			return true;
		} else if (commandType.equalsIgnoreCase(CommandType.COMPLETED
				.toString().toLowerCase())) {
			txtHintMsg = Constants.HINT[3];
			return true;
		} else if (commandType.equalsIgnoreCase(CommandType.DISPLAY.toString()
				.toLowerCase())) {
			txtHintMsg = Constants.HINT[4];
			return true;
		} else if (commandType.equalsIgnoreCase(CommandType.SEARCH.toString()
				.toLowerCase())) {
			txtHintMsg = Constants.HINT[5];
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		pieceOfCommand = Constants.STRING_STRING;
		String text_feedBack = SummaryReport.getFeedBackMsg();
		feedbackMsg.setFont(new Font("Calibri", Font.BOLD,
				Constants.FEEDBACK_MSG_FONT_SIZE));
		feedbackMsg.setForeground(Constants.COLOR_FEEDBACK_MSG);
		if (text_feedBack == null) {
			feedbackMsg.setText(Constants.STRING_STRING);
		} else {
			feedbackMsg.setText(SummaryReport.getFeedBackMsg());
			SummaryReport.setFeedBackMsg(Constants.STRING_STRING);
		}

		add(feedbackMsg, BorderLayout.SOUTH);
		setOpaque(true);
		revalidate();
		repaint();
		parent.updateFrame();

	}
}
