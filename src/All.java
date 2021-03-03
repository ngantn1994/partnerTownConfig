

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class All extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private int bgValue = Integer.parseInt( "FFDBE6", 16);
	private Color bgColor = new Color(bgValue);
	
	private static final String TITLE = "Partner's Town Config";
	
	private static final String HEADER = "header.png";
	private static final String ICON1_1 = "1_1.png";
	private static final String ICON1_2 = "1_2.png";
	private static final String ICON2_1 = "2_1.png";
	private static final String ICON2_2 = "2_2.png";
	private static final String ICON4_1 = "4_1.png";
	private static final String ICON4_2 = "4_2.png";

	private BufferedImageButton button1;
	private BufferedImageButton button2;
	private BufferedImageButton button4;
	
	private static All view = new All();
	
	public static All getInstance(){
		return view;
	}
	
	private All(){
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setBounds(150, 5, 1020, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel bgPanel = new JPanel();
		bgPanel.setLayout(new FlowLayout());
		bgPanel.setBackground(bgColor);
		
		JLabel headerLabel = new JLabel(new ImageIcon(getClass().getResource(HEADER)));
		bgPanel.add(headerLabel);
		
		button1 = new BufferedImageButton(ICON1_1, ICON1_2, 500, 200);
		bgPanel.add(button1);
		
		button2 = new BufferedImageButton(ICON2_1, ICON2_2, 500, 200);
		bgPanel.add(button2);
		
		button4 = new BufferedImageButton(ICON4_1, ICON4_2, 500, 200);
		bgPanel.add(button4);
		
		setContentPane(bgPanel);
	}
	
	public void setButton1ActionListener(ActionListener listener){
		button1.addActionListener(listener);
	}
	
	public void setButton2ActionListener(ActionListener listener){
		button2.addActionListener(listener);
	}
	
	public void setButton4ActionListener(ActionListener listener){
		button4.addActionListener(listener);
	}
}
