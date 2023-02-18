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

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(42, 28, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(204, 28, 96, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnAdd = new JButton("Lägg till");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println(ItemList.getSelectedValue());
				listModel.addElement("asdf");
				System.out.println("testeststs");
				System.out.println("testeststs");
				
				

			}
		});
		btnAdd.setBounds(312, 66, 89, 23);
		contentPane.add(btnAdd);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(312, 103, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.setBounds(312, 146, 89, 23);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("New button");
		btnNewButton_3.setBounds(312, 185, 89, 23);
		contentPane.add(btnNewButton_3);
		
		listModel = new DefaultListModel();
		
		ItemList = new JList(listModel);
		ItemList.setBounds(56, 91, 212, 161);
		contentPane.add(ItemList);
		
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
