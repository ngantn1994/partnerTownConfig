

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AllCtrl {
	
	private static AllCtrl ctrl = new AllCtrl();
	
	public static AllCtrl getInstance(){
		return ctrl;
	}
	
	public static All view;
	
	private AllCtrl(){
		view = All.getInstance();
		view.setButton1ActionListener(getButton1ActionListener());
		view.setButton2ActionListener(getButton2ActionListener());
		view.setButton4ActionListener(getButton4ActionListener());
	}
	
	private ActionListener getButton1ActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				View.changeContentPane(Button1Ctrl.getContentPane());
			}
		};
		return listener;
	}
	
	private ActionListener getButton2ActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				View.changeContentPane(Button2BeforeCtrl.getContentPane());
			}
		};
		return listener;
	}
	
	private ActionListener getButton4ActionListener(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				View.changeContentPane(ReportCtrl.getContentPane());
			}
		};
		return listener;
	}
	
	public static Container getContentPane(){
		return view.getContentPane();
	}

}
