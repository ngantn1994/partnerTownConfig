

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Button2Ctrl {
	private static final String POPUPTITLE = "X\u00e1c nh\u1eadn";
	private static final String POPUPQUESTION = "B\u1EA1n có mu\u1ED1n \u0111\u1ED5i tr\u1EA1ng thái c\u1EE7a thú c\u01B0ng này không? (T\u1EEB '\u0110ã b\u1ECF \u0111i' v\u1EC1 bình th\u01B0\u1EDDng và ng\u01B0\u1EE3c l\u1EA1i).";
	
	private static final String TITLE = "S\u1ef1 c\u1ed1";
	private static final String NOT_SELECTED = "Ch\u01b0a c\u00f3 d\u00f2ng n\u00e0o \u0111\u01b0\u1ee3c ch\u1ecdn.";
	
	private static Button2Ctrl ctrl = new Button2Ctrl();
	
	public static Button2Ctrl getInstance(){
		return ctrl;
	}
	
	private static Button2 view;

	private ActionListener getEditActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserPet pet = view.getSelectedPet();
				if(pet == null) {
					JOptionPane.showMessageDialog(null, NOT_SELECTED, TITLE, JOptionPane.INFORMATION_MESSAGE);
				} else {
					int reply = JOptionPane.showConfirmDialog(null, POPUPQUESTION, POPUPTITLE, JOptionPane.YES_NO_OPTION);
					if (reply == JOptionPane.YES_OPTION){
						pet.setVisible(!pet.getVisible());
						UserPetDAO.getInstance().updateVisible(pet);
						view.setPets(UserPetDAO.getInstance().getAllUserPet(pet.getUserName()));
						if(view != null)
							view.dispose();
					}
				}
			}
		};
		return listener;
	}
	
	private ActionListener getDelActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UserPet pet = view.getSelectedPet();
				if(pet == null) {
					JOptionPane.showMessageDialog(null, NOT_SELECTED, TITLE, JOptionPane.INFORMATION_MESSAGE);
				} else {
					UserPetDAO.getInstance().delete(pet);
					view.setPets(UserPetDAO.getInstance().getAllUserPet(pet.getUserName()));
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
	
	private Button2Ctrl(){
		view = new Button2(Button2Before.getInstance().getUsername());
		view.setEditActionListener(getEditActionListener());
		view.setDelActionListener(getDelActionListener());
		view.setBackActionListener(getBackActionListener());
	}
	
	public static Container getContentPane(){
		return view.getContentPane();
	}

}
