

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Button2Before extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final Font BIGGER_FONT = new Font(Font.SERIF, Font.BOLD, 30);
	private int fgValue = Integer.parseInt( "FF004D", 16);
	private Color fgColor = new Color(fgValue);
	private int bgValue = Integer.parseInt( "FFDBE6", 16);
	private Color bgColor = new Color(bgValue);
	
	private static final String TITLE = "Partner's Town Config";
	
	private static final String ADD1 = "add1.png";
	private static final String ADD2 = "add2.png";
	private static final String BACK1 = "back1.png";
	private static final String BACK2 = "back2.png";
	
	private static Button2Before view = new Button2Before();
	private BufferedImageButton addButton;
	private BufferedImageButton backButton;
	
	private JTextField usernameTextField;
	
	public static Button2Before getInstance(){
		return view;
	}
	
	private Button2Before(){
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setBounds(150, 5, 1020, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel bgPanel = new JPanel();
		bgPanel.setBackground(bgColor);
		bgPanel.setForeground(fgColor);
		setContentPane(bgPanel);
		bgPanel.setLayout(new FlowLayout());
		
		JLabel label = new JLabel("Username:", JLabel.RIGHT);
		label.setForeground(fgColor);
		label.setFont(BIGGER_FONT);
		label.setOpaque(false);
		label.setPreferredSize(new Dimension(200, 50));
		
		bgPanel.add(label);
		
		usernameTextField = new JTextField();
		usernameTextField.setForeground(fgColor);
		usernameTextField.setPreferredSize(new Dimension(500,50));
		usernameTextField.setFont(BIGGER_FONT);
		
		bgPanel.add(usernameTextField);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setPreferredSize(new Dimension(800,60));
		bgPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());
		
		addButton = new BufferedImageButton(ADD1, ADD2, 200, 50);
		buttonPanel.add(addButton);
		
		backButton = new BufferedImageButton(BACK1, BACK2, 200, 50);
		buttonPanel.add(backButton);
		
		bgPanel.add(buttonPanel);
		
		setContentPane(bgPanel);
	}
	
	public void setAddActionListener(ActionListener listener){
		addButton.addActionListener(listener);
	}
	
	public void setBackActionListener(ActionListener listener){
		backButton.addActionListener(listener);
	}

	public String getUsername(){
		return usernameTextField.getText();
	}
}
