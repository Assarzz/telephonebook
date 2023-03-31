package assignment_3;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	JList ItemList;
	DefaultListModel<String> listModel;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setForeground(new Color(0, 128, 192));
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1200, 550);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.setBackground(new Color(0, 128, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("Lägg till");
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String name = usefulMethods.AskForName();
					String number = usefulMethods.AskForNumber();
					
					BookItem newbokitem = new BookItem(name, number);
					
					listModel.clear();
					listModel.addAll(BookItem.UpdateGUI());
					
				} catch (Exception e2) {
					// TODO: handle exception
				}


				

			}
		});
		btnAdd.setBounds(767, 137, 140, 78);
		contentPane.add(btnAdd);
		
		listModel = new DefaultListModel<String>();
		
		ItemList = new JList(listModel);
		ItemList.setBackground(new Color(255, 255, 255));
		ItemList.setFont(new Font("Courier New", Font.BOLD, 30));
		ItemList.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		ItemList.setBounds(33, 94, 713, 400);
		contentPane.add(ItemList);
	
		JButton btndelete = new JButton("Ta bort");
		btndelete.setFocusPainted(false);
		btndelete.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btndelete.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				try {
					int selectedIndex = usefulMethods.getSelectedGUIIndex(ItemList);
					BookItem.deleteItemByGUIIndex(selectedIndex);
					listModel.clear();
					listModel.addAll(BookItem.UpdateGUI());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Du måste clicka på de elementet som du vill ta bort!");
				}

					

			}
		});
		btndelete.setBounds(767, 346, 140, 78);
		contentPane.add(btndelete);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setOpaque(true);
		lblName.setBounds(33, 61, 200, 36);
		contentPane.add(lblName);
		
		String[] DifferentSorts = { "Id", "Name" };
		JComboBox cbSortBy = new JComboBox(DifferentSorts);
		cbSortBy.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		cbSortBy.setName("");
		cbSortBy.setToolTipText("");
		cbSortBy.setBounds(831, 109, 76, 22);
		contentPane.add(cbSortBy);
		
		cbSortBy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// I simply swap which array to show the user which creates the impression that elements are being sorted
				BookItem.sort = cbSortBy.getSelectedItem().toString();
				listModel.clear();
				listModel.addAll(BookItem.UpdateGUI());
			}
		});
		
		JButton btnchange = new JButton("Ändra Nummer");
		btnchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int selectedIndex = usefulMethods.getSelectedGUIIndex(ItemList);
					BookItem.changeNumber(selectedIndex, usefulMethods.AskForNumber());
					
					listModel.clear();
					listModel.addAll(BookItem.UpdateGUI());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Du måste clicka på de elementet som du vill ändra!");
				}
				
				// change the currently clicked elements number

				
			}
		});
		btnchange.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnchange.setFocusPainted(false);
		btnchange.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnchange.setBounds(767, 267, 140, 35);
		contentPane.add(btnchange);
		
		JButton btnChangeName = new JButton("Ändra Namn");
		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// method for changing name. It unfortunately changes the elements id. This is necessary if i want it to work with my sorting system.

				try {
					int selectedIndex = usefulMethods.getSelectedGUIIndex(ItemList);
					BookItem.ChangeName(usefulMethods.AskForName(), selectedIndex);
					listModel.clear();
					listModel.addAll(BookItem.UpdateGUI());
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Du måste clicka på de elementet som du vill ändra!");
				}
				

				
			}
		});
		btnChangeName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnChangeName.setFocusPainted(false);
		btnChangeName.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnChangeName.setBounds(767, 305, 140, 35);
		contentPane.add(btnChangeName);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		lblNumber.setOpaque(true);
		lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumber.setBounds(300, 61, 200, 36);
		contentPane.add(lblNumber);
		
		JLabel lblId = new JLabel("Id");
		lblId.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		lblId.setOpaque(true);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(567, 61, 179, 36);
		contentPane.add(lblId);
		
		JLabel lblNewLabel = new JLabel("Sortera:");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBounds(767, 109, 65, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("This was made by Assar :)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBackground(new Color(255, 255, 255));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, -12, 190, 90);
		contentPane.add(lblNewLabel_1);
		
		JButton btnRandom = new JButton("Lägg till flera");
		btnRandom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int r = Integer.valueOf(JOptionPane.showInputDialog("Hur många slumpmässiga nummer vill du skapa?")); 
					
					BookItem.addNRandomElements(r);
					
					listModel.clear();
					listModel.addAll(BookItem.UpdateGUI());
					
				} catch (Exception e2) {
					// TODO: handle exception
				}

			}
		});
		btnRandom.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnRandom.setFocusPainted(false);
		btnRandom.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnRandom.setBounds(767, 220, 140, 36);
		contentPane.add(btnRandom);
		
		JButton btnTaBortAlla = new JButton("Ta bort alla");
		btnTaBortAlla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel.clear();
				BookItem.clearContent();
			}
		});
		btnTaBortAlla.setFont(new Font("Tahoma", Font.PLAIN, 21));
		btnTaBortAlla.setFocusPainted(false);
		btnTaBortAlla.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnTaBortAlla.setBounds(767, 431, 140, 36);
		contentPane.add(btnTaBortAlla);
		
		

	}
	

	class usefulMethods {
		
		static String AskForName() {
			
			String name = "";
			boolean repeat = true;
			while (repeat) {
				name = JOptionPane.showInputDialog("Ange Namn!");
				
				if (name.length() > 0) {
					repeat = false;
				}
			}
			
			return name;
			
		}
		
		static String AskForNumber() {
			
			
			String number = "";
			boolean repeat = true;
			while (repeat) {
				number = JOptionPane.showInputDialog("Ange Telefonnummer!");
				
				if (number.length() > 0) {
					repeat = false;
				}
			}
			
			return number;
			
		}
		
		static int getSelectedGUIIndex(	JList ItemList) {
			
			int selectedIndex = ItemList.getSelectedIndex();
			if (selectedIndex == -1) {
				throw  new IllegalArgumentException("An element must be selected");
			}
			else {
				return selectedIndex;
			}
		}
		
		
		
		
	}
}
