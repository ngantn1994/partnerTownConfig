

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class Edit1 extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private static final Font SMALLER_FONT = new Font(Font.SERIF, Font.PLAIN, 20);
	private int fgValue = Integer.parseInt( "FF004D", 16);
	private Color fgColor = new Color(fgValue);
	private int bgValue = Integer.parseInt( "FFDBE6", 16);
	private Color bgColor = new Color(bgValue);
	
	private static final String TITLE = "User's config";
	
	private static final String PASSWORD = "Password";
	private static final String EMAIL = "E-mail";
	private static final String ADDRESS = "\u0110\u1ecba ch\u1ec9";
	private static final String SEX = "Gi\u1edbi t\u00ednh";
	private static final String MALE = "Nam";
	private static final String FEMALE = "N\u1eef";
	
	private static final String SAVE1 = "save1.png";
	private static final String SAVE2 = "save2.png";
	private static final String UNDO1 = "undo1.png";
	private static final String UNDO2 = "undo2.png";
	
	private BufferedImageButton saveButton;
	private BufferedImageButton undoButton;
	private JTextField passwordTextField;
	private JTextField emailTextField;
	private JTextField adressTextField;
	private JRadioButton femaleRadioButton;
	private JRadioButton maleRadioButton;
	
	public Edit1(){
		setTitle(TITLE);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(350, 50, 500, 270);
		
		JPanel bgPanel = new JPanel();
		bgPanel.setPreferredSize(new Dimension(490,260));
		bgPanel.setBackground(bgColor);
		bgPanel.setForeground(fgColor);
		
		setContentPane(bgPanel);
		bgPanel.setLayout(new FlowLayout());
		
		JLabel passwordLabel = new JLabel(PASSWORD);
		passwordLabel.setPreferredSize(new Dimension(170, 40));
		passwordLabel.setForeground(fgColor);
		passwordLabel.setFont(SMALLER_FONT);
		bgPanel.add(passwordLabel);
		
		passwordTextField = new JTextField();
		passwordTextField.setPreferredSize(new Dimension(300, 40));
		passwordTextField.setForeground(fgColor);
		passwordTextField.setFont(SMALLER_FONT);
		bgPanel.add(passwordTextField);
		
		JLabel emailLabel = new JLabel(EMAIL);
		emailLabel.setPreferredSize(new Dimension(170, 40));
		emailLabel.setForeground(fgColor);
		emailLabel.setFont(SMALLER_FONT);
		bgPanel.add(emailLabel);
		
		emailTextField = new JTextField();
		emailTextField.setPreferredSize(new Dimension(300, 40));
		emailTextField.setForeground(fgColor);
		emailTextField.setFont(SMALLER_FONT);
		bgPanel.add(emailTextField);
		
		JLabel adressLabel = new JLabel(ADDRESS);
		adressLabel.setPreferredSize(new Dimension(170, 40));
		adressLabel.setForeground(fgColor);
		adressLabel.setFont(SMALLER_FONT);
		bgPanel.add(adressLabel);
		
		adressTextField = new JTextField();
		adressTextField.setPreferredSize(new Dimension(300, 40));
		adressTextField.setForeground(fgColor);
		adressTextField.setFont(SMALLER_FONT);
		bgPanel.add(adressTextField);
		
		JLabel sexLabel = new JLabel(SEX);
		sexLabel.setPreferredSize(new Dimension(170, 40));
		sexLabel.setForeground(fgColor);
		sexLabel.setFont(SMALLER_FONT);
		bgPanel.add(sexLabel);
		
		JPanel sexPanel = new JPanel();
		sexPanel.setOpaque(false);
		sexPanel.setPreferredSize(new Dimension(300, 40));
		
		ButtonGroup group = new ButtonGroup();
		
		maleRadioButton = new JRadioButton(MALE);
		maleRadioButton.setSelected(true);
		maleRadioButton.setFont(SMALLER_FONT);
		maleRadioButton.setForeground(fgColor);
		maleRadioButton.setOpaque(false);
		group.add(maleRadioButton);
		sexPanel.add(maleRadioButton);
		
		femaleRadioButton = new JRadioButton(FEMALE);
		femaleRadioButton.setFont(SMALLER_FONT);
		femaleRadioButton.setForeground(fgColor);
		femaleRadioButton.setOpaque(false);
		group.add(femaleRadioButton);
		sexPanel.add(femaleRadioButton);
		
		bgPanel.add(sexPanel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setPreferredSize(new Dimension(450, 60));
		buttonPanel.setOpaque(false);
		buttonPanel.setLayout(new FlowLayout());
		bgPanel.add(buttonPanel);
		
		saveButton = new BufferedImageButton(SAVE1, SAVE2, 150, 50);
		buttonPanel.add(saveButton);
		
		undoButton = new BufferedImageButton(UNDO1, UNDO2, 150, 50);
		buttonPanel.add(undoButton);
	}
	
	public void setSaveActionListerner(ActionListener listener){
		saveButton.addActionListener(listener);
	}
	
	public void setUndoActionListerner(ActionListener listener){
		undoButton.addActionListener(listener);
	}
	
	public void setPassword(String password){
		passwordTextField.setText(password);
	}
	
	public String getPassword(){
		return passwordTextField.getText();
	}
	
	public void setEmail(String email){
		emailTextField.setText(email);
	}
	
	public String getEmail(){
		return emailTextField.getText();
	}
	
	public void setAdress(String adress){
		adressTextField.setText(adress);
	}
	
	public String getAdress(){
		return adressTextField.getText();
	}
	
	public boolean getSex() {
		if (maleRadioButton != null)
			return maleRadioButton.isSelected();
		return false;
	}

	public void setSex(boolean sex) {
		if (maleRadioButton != null && femaleRadioButton != null) {
			maleRadioButton.setSelected(sex);
			femaleRadioButton.setSelected(!sex);
		}
	}
}
