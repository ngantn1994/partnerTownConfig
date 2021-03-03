

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Button2BeforeCtrl {
	
	private static final String TITLE = "S\u1ef1 c\u1ed1";
	private static final String NOT_AVAIBLE = "Tên \u0111\u0103ng nh\u1EADp này không \u0111úng.";
	
	private static Button2BeforeCtrl ctrl = new Button2BeforeCtrl();
	
	public static Button2BeforeCtrl getInstance(){
		return ctrl;
	}
	
	private static Button2Before view;
	
	private ActionListener getAddActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(UserDAO.getInstance().checkAvaible(view.getUsername())){
					View.changeContentPane(Button2Ctrl.getContentPane());
				} else {
					JOptionPane.showMessageDialog(null, NOT_AVAIBLE, TITLE, JOptionPane.INFORMATION_MESSAGE);
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
	
	private Button2BeforeCtrl(){
		view = Button2Before.getInstance();
		view.setAddActionListener(getAddActionListener());
		view.setBackActionListener(getBackActionListener());
	}
	
	public static Container getContentPane(){
		return view.getContentPane();
	}
}
