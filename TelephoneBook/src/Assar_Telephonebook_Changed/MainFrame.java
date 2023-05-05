package Assar_Telephonebook_Changed;
import java.util.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;

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
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	JList ItemList;
	DefaultListModel<String> listModel;
	JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
					
					JOptionPane.showMessageDialog(frame, "Detta är min ÄNDRADE telefonbok. Jag har bytt från att lagra telefonnumer till att lagra email adresser. Lyckligtvis behövde jag inte ändra mitt sorteringssystem. \n Jag behövde ändra min 'lägg till n antal slumpmässiga tillägg' funktionen och namn på massa knappar och variabler. \n Jag har också set till att alla emails som man lägger till måste följa en viss mall(både de man lägger till själv och de som genereras).\n Mallen tillåter inte vissa saker som att ha ickealfabetisk karaktär som första och vissa andra saker som är möjligt för en vanlig adress. Detta är ett design val då jag ville ha en viss struktur på telefonboken.");
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
		setTitle("Telefonboken :)");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Assar Lannerborn\\Desktop\\IconImage4.png"));
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setForeground(new Color(0, 128, 192));
		setBackground(new Color(0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1200, 550);
		contentPane = new JPanel();
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 30));
		
		// Random color every time new app is started
		Random r = new Random();
		int[] bg_rgb = new int[] {r.nextInt(0, 256),r.nextInt(0, 256),r.nextInt(0, 256)};
		contentPane.setBackground(new Color(bg_rgb[0], bg_rgb[1], bg_rgb[2]));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdd = new JButton("Lägg till");
		btnAdd.setForeground(new Color(0, 0, 0));
		btnAdd.setFocusPainted(false);
		btnAdd.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String name = usefulMethods.AskForName();
					String email = usefulMethods.AskForEmail();
					
					BookItem newbokitem = new BookItem(name, email);
					
					listModel.clear();
					listModel.addAll(BookItem.UpdateGUI());
					
				} catch (Exception e2) {
					// TODO: handle exception
				}


	
				
			}
		});
		btnAdd.setBounds(858, 153, 140, 78);
		contentPane.add(btnAdd);
		
		listModel = new DefaultListModel<String>();
		ItemList = new JList(listModel);

		
		scrollPane = new JScrollPane(ItemList);
	    scrollPane.setBounds(33, 94, 713, 400);
	    
	    
		//ItemList.setBackground(new Color(255, 255, 255));
		ItemList.setFont(new Font("Courier New", Font.BOLD, 19));
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 4));
	    contentPane.add(scrollPane);

	    
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
		btndelete.setBounds(858, 362, 140, 78);
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
		cbSortBy.setBounds(922, 67, 76, 22);
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
		
		JButton btnchange = new JButton("Ändra Email");
		btnchange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int selectedIndex = usefulMethods.getSelectedGUIIndex(ItemList);
					BookItem.changeEmail(selectedIndex, usefulMethods.AskForEmail());
					
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
		btnchange.setBounds(858, 283, 140, 35);
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
		btnChangeName.setBounds(858, 321, 140, 35);
		contentPane.add(btnChangeName);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setBorder(new LineBorder(new Color(0, 0, 0), 3));
		lblEmail.setOpaque(true);
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setBounds(300, 61, 200, 36);
		contentPane.add(lblEmail);
		
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
		lblNewLabel.setBounds(858, 67, 65, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("This was made by Assar :)");
		lblNewLabel_1.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
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
					int r = Integer.valueOf(JOptionPane.showInputDialog("Hur många slumpmässiga Email inlägg vill du skapa?")); 
					
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
		btnRandom.setBounds(858, 236, 140, 36);
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
		btnTaBortAlla.setBounds(858, 447, 140, 36);
		contentPane.add(btnTaBortAlla);
		
		JButton btnsearch = new JButton("Sök");
		btnsearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int searchedIndex = BookItem.search(JOptionPane.showInputDialog("Sök efter namn")); 
				if (searchedIndex != -1 ) {
					ItemList.setSelectedIndex(searchedIndex);

				}
				else {
					ItemList.clearSelection();
					JOptionPane.showMessageDialog(null, "Det sökta elementet måste vara identiskt med den du letar efter. Använd den alfabetiska sorteringen för att lät hitta de du letar efter!");

				}
				
				
			}
		});
		btnsearch.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnsearch.setFocusPainted(false);
		btnsearch.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		btnsearch.setBounds(858, 101, 140, 41);
		contentPane.add(btnsearch);
		
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
		
		
		// Denna är den största ändringen. Email som man lägger till måste följa en viss mall, annars måste man skriva om.
		// Kriterierna kan man se som boolean variabler.
		static String AskForEmail() {
			


			
			String email = "";
			boolean repeat = true;
			boolean alreadyFailed = false;
			while (repeat) {
				String message = "Ange email!";
				if (alreadyFailed) { message += " Email måste ha mallen: användarnamn@EmailServer.topdomän";}
				email = JOptionPane.showInputDialog(message);

				boolean firstCharIsFromAlphabet = false;
				boolean hasAtChar = false;
				boolean hasCharsAfterAtChar = false;
				boolean hasDotafterCharsThatComeAfterAtChar = false;
				boolean hasCharsAfterFinalDot = false;
				
				for (int i = 0; i < email.length(); i++) {
					
					if (isAlpabetical(email.charAt(0))) {
						firstCharIsFromAlphabet = true;
					}
					if ( firstCharIsFromAlphabet && email.charAt(i) == '@') {
						hasAtChar = true;
					}
					if (hasAtChar && isAlpabetical(email.charAt(i))) {
						hasCharsAfterAtChar = true;
					}
					
					if (hasCharsAfterAtChar && email.charAt(i) == '.') {
						hasDotafterCharsThatComeAfterAtChar = true;
					}
					if (hasDotafterCharsThatComeAfterAtChar && isAlpabetical(email.charAt(i))) {
						hasCharsAfterFinalDot = true;
					}

					
				}
				if (firstCharIsFromAlphabet && hasAtChar && hasCharsAfterAtChar && hasDotafterCharsThatComeAfterAtChar && hasCharsAfterFinalDot) {
					repeat = false;
					
				}
				else {
					repeat = true;
				}
				
				if (repeat == true) {
					alreadyFailed = true;
				}
			}
			
			return email;
		}
		// Detta är en av de nya metoderna jag lade till. Den säger om en char är från alfabetet ovasätt om den är stor eller liten (A eller a).
		static boolean isAlpabetical(char thechar) {
			
			
			if ((thechar >= 97 && thechar <= 122) || (thechar >= 65 && thechar <= 90) ) {
				return true;
			}
			else {
				return false;
			}
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
