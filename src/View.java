

import java.awt.Container;

import javax.swing.JFrame;

public class View extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final String TITLE = "Partner's Town Config";
	
	private static View view = new View();
	
	public static void changeContentPane(Container bgPanel){
		view.getContentPane().setVisible(false);
		System.gc();
		bgPanel.setVisible(true);
		view.setContentPane(bgPanel);
	}
	
	public static void getInstance(){
		view.setVisible(true);
	}
	
	private View(){
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setBounds(150, 5, 1020, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
