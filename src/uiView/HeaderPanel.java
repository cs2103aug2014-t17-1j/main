package uiView;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import commonClasses.SummaryReport;
/*
 * @author Paing Zin Oo(Jack)
 */
public class HeaderPanel extends JPanel implements Observer{
	JLabel lbl_header;
	public HeaderPanel(){
		lbl_header = new JLabel();
		lbl_header.setForeground(ColorBox.colorPool[24]);
		lbl_header.setText(SummaryReport.getHeader());
		add(lbl_header);
		setBackground(Color.BLACK);
	
	}
	
	public void update(){
		lbl_header.setText(SummaryReport.getHeader());	
	}
}
