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
	DefaultListModel listModel;
	JLabel lblSortBy = new JLabel("Id");


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
				
				String[] nameAndNumber = usefulMethods.AskForName();
				
				BookItem newbokitem = new BookItem(nameAndNumber[0], nameAndNumber[1]);
				
				listModel.addElement( newbokitem.Name +"   "+newbokitem.Number+ "   "+ newbokitem.id);

			}
		});
		btnAdd.setBounds(366, 60, 140, 78);
		contentPane.add(btnAdd);
		
		listModel = new DefaultListModel();
		
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
				
				try {
					listModel.remove(ItemList.getSelectedIndex());
				} catch (Exception e2) {
					System.out.println(e2.getMessage());
				}
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
				Object temp = cbSortBy.getSelectedItem();
				lblSortBy.setText(temp.toString());
			}
		});
		
		JButton btnchange = new JButton("Ändra");
		btnchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// change the currantly clicked element
				
			}
		});
		btnchange.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnchange.setFocusPainted(false);
		btnchange.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		btnchange.setBounds(749, 60, 140, 78);
		contentPane.add(btnchange);
		
		lblSortBy.setHorizontalAlignment(SwingConstants.CENTER);
		lblSortBy.setOpaque(true);
		lblSortBy.setBounds(920, 214, 49, 20);
		contentPane.add(lblSortBy);
		
		

	}
	
	class BookItem{
		static int TotalBooks = 0;
		String Name;
		String Number;
		int id;
		
		public BookItem(String name, String number) {
			this.Name = name;
			this.Number = number;
			this.id = TotalBooks;
			TotalBooks ++;
			
		}
	}
	
	class usefulMethods {
		
		static String[] AskForName() {
			
			String name = "";
			String number = "";
			boolean repeat = true;
			while (repeat) {
				name = JOptionPane.showInputDialog("Ange namn");
				number = JOptionPane.showInputDialog("Ange telefonnummer");
				
				if (name.length() > 0 && number.length() > 0) {
					repeat = false;
				}
			}
			
			// returns the name and number
			return new String[] {name, number};
			
		}
		
		
	}
}
