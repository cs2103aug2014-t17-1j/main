package uiView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import commonClasses.Constants;
import commonClasses.SummaryReport;

/*
 * @author Paing Zin Oo(Jack)
 */
public class HeaderPanel extends JPanel implements Observer {
	JLabel lblHeader;
	JButton btnHelp, btnCategory;
	private static final int FONT_SIZE = 24;

	public HeaderPanel(LayoutManager layout) {
		super(layout);

		lblHeader = new JLabel();
		lblHeader.setFont(new Font("Calibri", Font.BOLD, FONT_SIZE));
		lblHeader.setForeground(Constants.COLOR_HEADER_PANEL_TEXT);
		lblHeader.setText(SummaryReport.getHeader());
		lblHeader.setText(Constants.PRODUCT_TASKDO);
		lblHeader.setHorizontalAlignment(JLabel.CENTER);

		setBackground(Constants.COLOR_HEADER_PANEL_BG);
		GridBagConstraints c = new GridBagConstraints();

		btnHelp = new JButton("F1 Help");
		btnHelp.setOpaque(false);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorderPainted(false);
		btnHelp.setPreferredSize(new Dimension(80, 20));
		btnHelp.setForeground(Color.WHITE);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 0;
		add(btnHelp, c);

		JButton btnDetails = new JButton("F2 Details");
		btnDetails.setOpaque(false);
		btnDetails.setContentAreaFilled(false);
		btnDetails.setBorderPainted(false);
		btnDetails.setPreferredSize(new Dimension(90, 20));
		btnDetails.setForeground(Color.WHITE);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0.0;
		c.gridx = 1;
		c.gridy = 0;
		add(btnDetails, c);

		btnCategory = new JButton("F3 Categories");
		btnCategory.setOpaque(false);
		btnCategory.setContentAreaFilled(false);
		btnCategory.setBorderPainted(false);
		btnCategory.setPreferredSize(new Dimension(120, 20));
		btnCategory.setForeground(Color.WHITE);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0.0;
		c.gridx = 2;
		c.gridy = 0;
		add(btnCategory, c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.gridx = 3;
		c.gridy = 0;
		add(lblHeader, c);

		JButton btnDummy = new JButton();
		btnDummy.setOpaque(false);
		btnDummy.setContentAreaFilled(false);
		btnDummy.setBorderPainted(false);
		btnDummy.setPreferredSize(new Dimension(250, 20));
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0.0;
		c.gridx = 4;
		c.gridy = 0;
		add(btnDummy, c);

		ImageIcon img;
		img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/delete-icon.png"));
		JButton btnMin = new JButton(img);
		btnMin.setOpaque(false);
		btnMin.setContentAreaFilled(false);
		btnMin.setBorderPainted(false);
		btnMin.setPreferredSize(new Dimension(20, 20));
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0.0;
		c.gridx = 5;
		c.gridy = 0;
		add(btnMin, c);

		img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(
				"image/cancel-icon.png"));
		JButton btnClose = new JButton(img);
		btnClose.setOpaque(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setBorderPainted(false);
		btnClose.setPreferredSize(new Dimension(20, 20));
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0.0;
		c.gridx = 6;
		c.gridy = 0;
		add(btnClose, c);
	}

	public void update() {

	}
}
