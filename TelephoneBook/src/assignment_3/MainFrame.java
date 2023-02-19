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

public class MainFrame extends JFrame {

	private JPanel contentPane;
	
	JList ItemList;
	DefaultListModel listModel;



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
				
				
				
				String value = JOptionPane.showInputDialog("Ange namn och telefonnummer");
				if (value.length() == 0) {
					System.out.println("Lenth must be greater than 0");
				}
				else {
					listModel.addElement(value);
				}
				

				
				

			}
		});
		btnAdd.setBounds(366, 136, 140, 78);
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
		btndelete.setBounds(559, 136, 140, 78);
		contentPane.add(btndelete);
		
	}
	
	
	class BookItem{
		String Name;
		String Number;
		
		public BookItem(String name, String number) {
			this.Name = name;
			this.Number = number;
			
			
		}
		
		
	}
}
