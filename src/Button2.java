

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

public class Button2 extends JFrame {

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
	
	public static final String STOCKID = "StockID";
	public static final String VISIBLE = "Visible";
	public static final String USERNAME = "UserName";
	public static final String PETNAME = "PetName";
	public static final String PETID = "PetID";
	public static final String CURRENTLV = "CurrentLv";
	public static final String MAXLV = "MaxLv";
	public static final String HP = "HP";
	public static final String SP = "SP";
	public static final String HAPPY = "Happy";
	public static final String WC = "WC";
	
	private BufferedImageButton backButton;
	private BufferedImageButton delButton;
	private BufferedImageButton editButton;
	
	private JTable table;
	private MainTableModel tbmodel;
	
	public Button2(String username){
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
		tbmodel.setPets(UserPetDAO.getInstance().getAllUserPet(username));
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
		private List<UserPet>	pets;
		

		public MainTableModel () {
			super();
			pets = new ArrayList<UserPet>();
		}

		private final String[]	HEADER_NAME	= new String[] {STOCKID, VISIBLE, USERNAME, PETNAME, PETID, CURRENTLV, MAXLV, HP, SP, HAPPY, WC};

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
			return pets != null ? pets.size() : super.getRowCount();
		}
		
		public String getVisible(boolean b){
			if(b){
				return "Có";
			} else {
				return "Không";
			}
		}

		@Override
		public Object getValueAt(int row, int column) {
			if (pets != null && 0 <= row && row < pets.size()) {
				UserPet pet = pets.get(row);
				if (pet != null) {
					switch (column) {
						case 0:
							return pet.getStockID() + "";
						case 1:
							return getVisible(pet.getVisible());
						case 2:
							return pet.getUserName();
						case 3:
							return pet.getPetName();
						case 4:
							return pet.getPetID() + "";
						case 5:
							return pet.getCurrentLv() + "";
						case 6:
							return pet.getMaxLv() + "";
						case 7:
							return pet.getHP() + "";
						case 8:
							return pet.getSP() + "";
						case 9:
							return pet.getHappy() + "";
						case 10:
							return pet.getWC() + "";

						default:
							return null;
					}
				}
			}
			return super.getValueAt(row, column);
		}
		
		public void setPets(List<UserPet> pets) {
			this.pets = pets;
			fireTableDataChanged();
		}
		
		public UserPet getPet(int index) {
			if(pets != null && 0 <= index && index <= pets.size())
				return pets.get(index);
			
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
	
	public UserPet getSelectedPet() {
		if(table != null) {
			int rowSelectedIndex = table.getSelectedRow();
			
			MainTableModel tableModel = (MainTableModel)table.getModel();
			return tableModel.getPet(rowSelectedIndex);
		}
		return null;
	}
	
	public void setPets(List<UserPet> pets) {
		MainTableModel tableModel = (MainTableModel)table.getModel();
		tableModel.setPets(pets);
	}
}
