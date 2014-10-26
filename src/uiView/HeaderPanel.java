package uiView;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import commonClasses.Constants;
import commonClasses.SummaryReport;
/*
 * @author Paing Zin Oo(Jack)
 */
public class HeaderPanel extends JPanel implements Observer{
	JLabel lblHeader;
	private static final int FONT_SIZE = 24;
	
	public HeaderPanel(){
		lblHeader = new JLabel();
		lblHeader.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
		lblHeader.setForeground(Constants.COLOR_HEADER_PANEL_TEXT);
		lblHeader.setText(SummaryReport.getHeader());
		add(lblHeader);
		setBackground(Constants.COLOR_HEADER_PANEL_BG);
	
	}
	
	public void update(){
		lblHeader.setText(SummaryReport.getHeader());	
	}
}
