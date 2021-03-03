

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class Edit1Ctrl {
	
	private static final String ERROR_TITLE = "ERROR";
	private static final String ERROR_PASSWORD1 = "M\u1EADt kh\u1EA9u ph\u1EA3i n\u1EB1m trong kho\u1EA3ng t\u1EEB 6 \u0111\u1EBFn 16 ký t\u1EF1.";
	private static final String ERROR_PASSWORD2 = "M\u1EADt kh\u1EA9u không \u0111\u01B0\u1EE3c ch\u1EE9a d\u1EA5u cách.";
	
	private User model;
	private Edit1 view;
	
	public Edit1Ctrl(User model){
		this.model = model;
		view = new Edit1();
		
		fillData();
		
		view.setModal(true);
		view.setSaveActionListerner(getSaveActionListener());
		view.setUndoActionListerner(getUndoActionListener());
		view.setVisible(true);
	}
	
	private void fillData() {
		if(model != null && view != null) {
			view.setPassword(model.getPassword());
			view.setEmail(model.getEmail());
			view.setAdress(model.getAddress());
			view.setSex(model.isSex());
		}
	}
	
	private void getData() {
		if(view != null) {
			int temp = testPassword();
			if(temp == 0){
				model.setPassword(view.getPassword());
				model.setEmail(view.getEmail());
				model.setAddress(view.getAdress());
				model.setSex(view.getSex());
			} else {
				switch(temp) {
				case 1:
					JOptionPane.showMessageDialog(null, ERROR_PASSWORD1, ERROR_TITLE, JOptionPane.INFORMATION_MESSAGE);
					return;
				case 2:
					JOptionPane.showMessageDialog(null, ERROR_PASSWORD2, ERROR_TITLE, JOptionPane.INFORMATION_MESSAGE);
					return;
				default:
					return;
				}
			}
		}
	}
	
	private ActionListener getSaveActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getData();
				view.dispose();
			}
		};
		return listener;
	}
	
	private ActionListener getUndoActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fillData();
			}
		};
		return listener;
	}
	
	public int testPassword(){
		String s = view.getPassword();
		int temp = s.length();
		if(temp<6 || temp >16){
			return 1;
		}
		for(int i=0;i<temp;i++){
			if(s.charAt(i) == ' '){
				return 2;
			}
		}
		return 0;
	}
	
	public User getUser() {
		return model;
	}

	public Edit1 getView() {
		return view;
	}

}
