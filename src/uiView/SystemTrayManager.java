package uiView;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import com.tulskiy.keymaster.common.Provider;
import commonClasses.Constants;

public class SystemTrayManager implements MouseListener{
		private JFrame frame;
	//@author Boo Tai Yi  A0111936J
	public SystemTrayManager(final JFrame frame, final Provider provide) {
		this.frame = frame;
	
	    if (!SystemTray.isSupported()) {
	        return;
	      }

	      SystemTray tray = SystemTray.getSystemTray();
	      ImageIcon tray_icon = new ImageIcon(getClass().getResource(Constants.STRING_IMG_MAIN_ICON));
	      Image image = tray_icon.getImage();

	      PopupMenu menu = new PopupMenu();   
	      MenuItem openItem = new MenuItem(Constants.STRING_OPEN_TASKDO);
	      openItem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          frame.setVisible(true);
	        }
	      });
	      menu.add(openItem);
	      MenuItem closeItem = new MenuItem(Constants.STRING_QUIT_TASKDO);
	      closeItem.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	provide.reset();
	        	provide.stop();
	        	System.exit(0);
	        }
	      });
	      menu.add(closeItem);
	      TrayIcon icon = new TrayIcon(image, Constants.PRODUCT_TASKDO, menu);
	      icon.addMouseListener(this);
	      icon.setImageAutoSize(true);

	      try {
			tray.add(icon);
		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		frame.setVisible(true);
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
	