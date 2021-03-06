package uiView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import commonClasses.Constants;

/** 
 * This is the header Panel where the name of the application and the buttons 
 *  such as F1 and F2 come in
 *  
 */
public class HeaderPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	// @author  A0112581N
	JLabel lblHeader;
	JButton btnHelp, btnCategory;
	UiParent uiParent;

	public HeaderPanel(LayoutManager layout, UiParent uiParent) {
		super(layout);
		this.uiParent = uiParent;
		createHeaderLbl();

		setBackground(Constants.COLOR_HEADER_PANEL_BG);
		GridBagConstraints c = new GridBagConstraints();

		createHelpBtn(c);
		createCategoriesBtn(c);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.CENTER;
		c.weightx = 1.0;
		c.gridx = 3;
		c.gridy = 0;
		add(lblHeader, c);

		createDummyBtn(c);
		createMinimizeBtn(c);
		createCloseBtn(c);

	}

	private void createCategoriesBtn(GridBagConstraints c) {
		JButton btnDetails = new JButton(Constants.STRING_F3_CATEGORIES);
		btnDetails.setOpaque(false);
		btnDetails.setContentAreaFilled(false);
		btnDetails.setBorderPainted(false);
		btnDetails.setPreferredSize(new Dimension(120, 20));
		btnDetails.setForeground(Color.WHITE);
		btnDetails.setFocusable(false);
		btnDetails.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				uiParent.pressedF3();

			}
		});
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0.0;
		c.gridx = 1;
		c.gridy = 0;
		add(btnDetails, c);
	}

	private void createHelpBtn(GridBagConstraints c) {
		btnHelp = new JButton(Constants.STRING_F1_HELP);
		btnHelp.setOpaque(false);
		btnHelp.setContentAreaFilled(false);
		btnHelp.setBorderPainted(false);
		btnHelp.setPreferredSize(new Dimension(80, 20));
		btnHelp.setForeground(Color.WHITE);
		btnHelp.setFocusable(false);
		btnHelp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uiParent.pressedF1();

			}

		});
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.WEST;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 0;
		add(btnHelp, c);
	}

	private void createHeaderLbl() {
		lblHeader = new JLabel();
		lblHeader.setFont(new Font("Calibri", Font.BOLD,
				Constants.HEADER_FONT_SIZE));
		lblHeader.setForeground(Constants.COLOR_HEADER_PANEL_TEXT);
		lblHeader.setText(Constants.PRODUCT_TASKDO);
		lblHeader.setHorizontalAlignment(JLabel.CENTER);
	}

	private void createDummyBtn(GridBagConstraints c) { // To center Task.Do
														// label
		JButton btnDummy = new JButton();
		btnDummy.setOpaque(false);
		btnDummy.setContentAreaFilled(false);
		btnDummy.setBorderPainted(false);
		btnDummy.setPreferredSize(new Dimension(160, 20));
		btnDummy.setFocusable(false);
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0.0;
		c.gridx = 4;
		c.gridy = 0;
		add(btnDummy, c);
	}

	private void createMinimizeBtn(GridBagConstraints c) {
		ImageIcon icon = new ImageIcon(getClass().getResource(
				Constants.STRING_IMG_DEL_PNG));
		JButton btnMin = new JButton(icon);
		btnMin.setOpaque(false);
		btnMin.setContentAreaFilled(false);
		btnMin.setBorderPainted(false);
		btnMin.setPreferredSize(new Dimension(20, 20));
		btnMin.setFocusable(false);
		btnMin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				uiParent.getMainFrame().setState(Frame.ICONIFIED);

			}

		});
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0.0;
		c.gridx = 5;
		c.gridy = 0;
		add(btnMin, c);
	}

	private void createCloseBtn(GridBagConstraints c) {
		ImageIcon img = new ImageIcon(getClass().getResource(
				Constants.STRING_IMG_CANCEL_PNG));
		JButton btnClose = new JButton(img);
		btnClose.setOpaque(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setBorderPainted(false);
		btnClose.setPreferredSize(new Dimension(20, 20));
		btnClose.setFocusable(false);
		btnClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				uiParent.setVisible(false);
			}

		});
		c.fill = GridBagConstraints.NONE;
		c.anchor = GridBagConstraints.EAST;
		c.weightx = 0.0;
		c.gridx = 6;
		c.gridy = 0;
		add(btnClose, c);
	}

}
