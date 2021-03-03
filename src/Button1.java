

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Button1 extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static final Font SMALLER_FONT = new Font(Font.SERIF, Font.PLAIN, 20);
	private int fgValue = Integer.parseInt( "FF004D", 16);
	private Color fgColor = new Color(fgValue);
	private int bgValue = Integer.parseInt( "FFDBE6", 16);
	private Color bgColor = new Color(bgValue);
	
	private static final String TITLE = "Partner's Town Config";

	private static final String BACK1 = "back1.png";
	private static final String BACK2 = "back2.png";
	private static final String DEL1 = "del1.png";
	private static final String DEL2 = "del2.png";
	private static final String EDIT1 = "edit1.png";
	private static final String EDIT2 = "edit2.png";
	
	private static final String NAME = "Tên \u0111\u0103ng nh\u1EADp";
	private static final String PASSWORD = "Password";
	private static final String EMAIL = "E-mail";
	private static final String ADDRESS = "\u0110\u1ecba ch\u1ec9";
	private static final String SEX = "Gi\u1edbi t\u00ednh";
	private static final String MONEY = "Money";
	private static final String ENERGY = "Energy";
	
	private BufferedImageButton backButton;
	private BufferedImageButton delButton;
	private BufferedImageButton editButton;

	private JTable table;
	private MainTableModel tbmodel;
	
	private static Button1 view = new Button1(UserDAO.getInstance());
	
	public static Button1 getInstance(){
		return view;
	}
	
	private Button1(UserDAO model){
		this.setResizable(false);
		this.setTitle(TITLE);
		this.setBounds(150, 5, 1020, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JPanel bgPanel = new JPanel();
		bgPanel.setBackground(bgColor);
		bgPanel.setForeground(fgColor);
		setContentPane(bgPanel);
		bgPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setOpaque(false);
		bgPanel.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		table.setFont(SMALLER_FONT);
		table.setForeground(fgColor);
		table.setOpaque(false);
		table.setRowHeight(30);
		scrollPane.setViewportView(table);

		tbmodel = new MainTableModel();
		tbmodel.setUsers(model.getAllUsers());
		table.setModel(tbmodel);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		buttonPanel.setPreferredSize(new Dimension(800,60));
		bgPanel.add(buttonPanel, BorderLayout.SOUTH);
		buttonPanel.setLayout(new FlowLayout());
		
		editButton = new BufferedImageButton(EDIT1, EDIT2, 200, 50);
		buttonPanel.add(editButton);
		
		delButton = new BufferedImageButton(DEL1, DEL2, 200, 50);
		buttonPanel.add(delButton);
		
		backButton = new BufferedImageButton(BACK1, BACK2, 200, 50);
		buttonPanel.add(backButton);
	}
	
	
	class MainTableModel extends DefaultTableModel {

		private static final long serialVersionUID = 1L;
		private List<User>	users;
		

		public MainTableModel () {
			super();
			users = new ArrayList<User>();
		}

		private final String[]	HEADER_NAME	= new String[] {NAME, PASSWORD, EMAIL, ADDRESS, SEX, MONEY, ENERGY};

		@Override
		public String getColumnName(int column) {
			return column >= 0 && column < HEADER_NAME.length ? HEADER_NAME[column] : super.getColumnName(column);
		}


		@Override
		public int getColumnCount() {
			return HEADER_NAME.length;
		}

		@Override
		public int getRowCount() {
			return users != null ? users.size() : super.getRowCount();
		}

		@Override
		public Object getValueAt(int row, int column) {
			if (users != null && 0 <= row && row < users.size()) {
				User user = users.get(row);
				if (user != null) {
					switch (column) {
						case 0:
							return user.getUsername();
						case 1:
							return user.getPassword();
						case 2:
							return user.getEmail();
						case 3:
							return user.getAddress();
						case 4:
							return user.getSex();
						case 5:
							return user.getMoney() + "";
						case 6:
							return user.getEnergy() + "";
						default:
							return null;
					}
				}
			}
			return super.getValueAt(row, column);
		}
		
		public void setUsers(List<User> users) {
			this.users = users;
			fireTableDataChanged();
		}
		
		public User getUser(int index) {
			if(users != null && 0 <= index && index <= users.size())
				return users.get(index);
			
			return null;
		}
	}
	
	public void setBackActionListener(ActionListener listener){
		backButton.addActionListener(listener);
	}
	
	public void setDelActionListener(ActionListener listener){
		delButton.addActionListener(listener);
	}
	
	public void setEditActionListener(ActionListener listener){
		editButton.addActionListener(listener);
	}
	
	public User getSelectedUser() {
		if(table != null) {
			int rowSelectedIndex = table.getSelectedRow();
			
			MainTableModel tableModel = (MainTableModel)table.getModel();
			return tableModel.getUser(rowSelectedIndex);
		}
		return null;
	}
	
	public void setUsers(List<User> users) {
		MainTableModel tableModel = (MainTableModel)table.getModel();
		tableModel.setUsers(users);
	}
}
