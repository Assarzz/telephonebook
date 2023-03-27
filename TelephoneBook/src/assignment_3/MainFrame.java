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
		setBounds(100, 100, 1531, 846);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("Lägg till");
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = usefulMethods.AskForName();
				String number = usefulMethods.AskForNumber();

				
				BookItem newbokitem = new BookItem(name, number);
				
				listModel.clear();
				listModel.addAll(BookItem.UpdateGUI());
			}
		});
		btnAdd.setBounds(366, 60, 140, 78);
		contentPane.add(btnAdd);
		
		listModel = new DefaultListModel<String>();
		
		ItemList = new JList(listModel);
		ItemList.setBackground(new Color(255, 255, 255));
		ItemList.setFont(new Font("Times New Roman", Font.BOLD, 30));
		ItemList.setBorder(new LineBorder(new Color(0, 0, 0), 4, true));
		ItemList.setBounds(366, 246, 713, 523);
		contentPane.add(ItemList);
	
		JButton btndelete = new JButton("Ta bort");
		btndelete.setFocusPainted(false);
		btndelete.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btndelete.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

					BookItem.deleteItemByGUIIndex(ItemList.getSelectedIndex());
					listModel.clear();
					listModel.addAll(BookItem.UpdateGUI());
					

			}
		});
		btndelete.setBounds(559, 60, 140, 78);
		contentPane.add(btndelete);
		
		JLabel lblName = new JLabel("Name");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setOpaque(true);
		lblName.setBounds(419, 199, 100, 50);
		contentPane.add(lblName);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumber.setOpaque(true);
		lblNumber.setBounds(606, 199, 100, 50);
		contentPane.add(lblNumber);
		
		JLabel lblId = new JLabel("Id");
		lblId.setOpaque(true);
		lblId.setHorizontalAlignment(SwingConstants.CENTER);
		lblId.setBounds(749, 199, 100, 50);
		contentPane.add(lblId);
		
		String[] DifferentSorts = { "Id", "Name" };
		JComboBox cbSortBy = new JComboBox(DifferentSorts);
		cbSortBy.setName("");
		cbSortBy.setToolTipText("");
		cbSortBy.setBounds(972, 213, 76, 22);
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
				
				// change the currently clicked elements number
				BookItem.changeNumber(ItemList.getSelectedIndex(), usefulMethods.AskForNumber());
				
				listModel.clear();
				listModel.addAll(BookItem.UpdateGUI());
				
			}
		});
		btnchange.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnchange.setFocusPainted(false);
		btnchange.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnchange.setBounds(749, 60, 140, 35);
		contentPane.add(btnchange);
		
		JButton btnChangeName = new JButton("Ändra Namn");
		btnChangeName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// method for changing name. It unfortunately changes the elements id. This is necessary if i want it to work with my sorting system.
				BookItem.ChangeName(usefulMethods.AskForName(), ItemList.getSelectedIndex());
				listModel.clear();
				listModel.addAll(BookItem.UpdateGUI());
				
			}
		});
		btnChangeName.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnChangeName.setFocusPainted(false);
		btnChangeName.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnChangeName.setBounds(749, 103, 140, 35);
		contentPane.add(btnChangeName);
		
		

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
		
		
		
		
	}
}
