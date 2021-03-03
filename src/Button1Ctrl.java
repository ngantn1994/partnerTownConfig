

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Button1Ctrl {
	
	private static final String TITLE = "S\u1ef1 c\u1ed1";
	private static final String NOT_SELECTED = "Ch\u01b0a c\u00f3 d\u00f2ng n\u00e0o \u0111\u01b0\u1ee3c ch\u1ecdn.";
	
	private static Button1Ctrl ctrl = new Button1Ctrl();
	
	public static Button1Ctrl getInstance(){
		return ctrl;
	}
	
	private static Button1 view;

	private ActionListener getEditActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				User user = view.getSelectedUser();
				if(user == null) {
					JOptionPane.showMessageDialog(null, NOT_SELECTED, TITLE, JOptionPane.INFORMATION_MESSAGE);
				} else {
					Edit1Ctrl ctrl = new Edit1Ctrl(user);
					user = ctrl.getUser();
					UserDAO.getInstance().updateInformation(user);
					view.setUsers(UserDAO.getInstance().getAllUsers());
				}
			}
		};
		return listener;
	}
	
	private ActionListener getDelActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				User user = view.getSelectedUser();
				if(user == null) {
					JOptionPane.showMessageDialog(null, NOT_SELECTED, TITLE, JOptionPane.INFORMATION_MESSAGE);
				} else {
					UserDAO.getInstance().delete(user);
					view.setUsers(UserDAO.getInstance().getAllUsers());
				}
			}
		};
		return listener;
	}
	
	private ActionListener getBackActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				View.changeContentPane(AllCtrl.getContentPane());
			}
		};
		return listener;
	}
	
	private Button1Ctrl(){
		view = Button1.getInstance();
		view.setEditActionListener(getEditActionListener());
		view.setDelActionListener(getDelActionListener());
		view.setBackActionListener(getBackActionListener());
	}
	
	public static Container getContentPane(){
		return view.getContentPane();
	}
}
