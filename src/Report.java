

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Report extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private int fgValue = Integer.parseInt( "FF004D", 16);
	private Color fgColor = new Color(fgValue);
	private int bgValue = Integer.parseInt( "FFDBE6", 16);
	private Color bgColor = new Color(bgValue);
	
	private static final String TITLE = "Partner's Town Config";
	
	private static final String ICON5_1 = "5_1.png";
	private static final String ICON5_2 = "5_2.png";
	private static final String ICON6_1 = "6_1.png";
	private static final String ICON6_2 = "6_2.png";
	private static final String BACK1 = "back1.png";
	private static final String BACK2 = "back2.png";
	
	private BufferedImageButton b5Button;
	private BufferedImageButton b6Button;
	private BufferedImageButton backButton;

	private static Report view = new Report();
	
	public static Report getInstance(){
		return view;
	}
	
	private Report(){
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setBounds(150, 5, 1020, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel bgPanel = new JPanel();
		bgPanel.setBackground(bgColor);
		bgPanel.setForeground(fgColor);
		setContentPane(bgPanel);
		bgPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setOpaque(false);
		centerPanel.setLayout(new FlowLayout());
		centerPanel.setPreferredSize(new Dimension(900, 450));
		
		bgPanel.add(centerPanel, BorderLayout.CENTER);
		
		b5Button = new BufferedImageButton(ICON5_1, ICON5_2, 400, 200);
		centerPanel.add(b5Button);
		
		b6Button = new BufferedImageButton(ICON6_1, ICON6_2, 400, 200);
		centerPanel.add(b6Button);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setPreferredSize(new Dimension(800,60));
		bottomPanel.setLayout(new FlowLayout());
		bottomPanel.setOpaque(false);
		
		backButton = new BufferedImageButton(BACK1, BACK2, 200, 50);
		bottomPanel.add(backButton);
		
		bgPanel.add(bottomPanel, BorderLayout.SOUTH);
	}
	
	public void setB5ActionListener(ActionListener listener){
		b5Button.addActionListener(listener);
	}
	
	public void setB6ActionListener(ActionListener listener){
		b6Button.addActionListener(listener);
	}

	public void setBackActionListener(ActionListener listener){
		backButton.addActionListener(listener);
	}
}
