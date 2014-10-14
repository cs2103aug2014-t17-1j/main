package uiView;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import commonClasses.SummaryReport;

public class HeaderPanel extends JPanel implements Observer{
	JFrame frame;
	JLabel lbl_header;
	public HeaderPanel(final JFrame frame){
		this.frame = frame;
		lbl_header = new JLabel();
		lbl_header.setForeground(ColorBox.colorPool[24]);
		lbl_header.setText(SummaryReport.getHeader());
		add(lbl_header);
		setBackground(Color.BLACK);
		frame.add(this, BorderLayout.NORTH);

	}
	
	public void update(){
		lbl_header.setText(SummaryReport.getHeader());
		frame.revalidate();
		frame.repaint();
		frame.pack();
	}
}
