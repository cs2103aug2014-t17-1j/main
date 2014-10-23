package uiView;

import javax.swing.JLabel;
import javax.swing.JPanel;

import commonClasses.Constants;
import commonClasses.SummaryReport;
/*
 * @author Paing Zin Oo(Jack)
 */
public class HeaderPanel extends JPanel implements Observer{
	JLabel lbl_header;
	public HeaderPanel(){
		lbl_header = new JLabel();
		lbl_header.setForeground(Constants.COLOR_HEADER_PANEL_TEXT);
		lbl_header.setText(SummaryReport.getHeader());
		add(lbl_header);
		setBackground(Constants.COLOR_HEADER_PANEL_BG);
	
	}
	
	public void update(){
		lbl_header.setText(SummaryReport.getHeader());	
	}
}
