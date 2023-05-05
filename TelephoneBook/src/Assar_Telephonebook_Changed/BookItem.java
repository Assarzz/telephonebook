package Assar_Telephonebook_Changed;

import java.awt.Window.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ListModel;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
// this is a very big class that stores the entire list of elements that is displayed aswell as having a lot of methods for adding, updating, deleting and changing gui elements
public class BookItem{
	
	static ArrayList<BookItem> ContentSortedById = new ArrayList<BookItem>();
	static ArrayList<BookItem> ContentSortedByName = new ArrayList<BookItem>();

	static int TotalBooks = 0;
	static String sort = "Id";
	String Name;
	String email;
	String id;
	
	public BookItem(String name, String email) {
		this.Name = name;
		this.email = email;
		this.id = Integer.toString(TotalBooks);
		
		ContentSortedById.add(this);
		addNewItemInNameList(this, 0, TotalBooks-1, 0);
		TotalBooks ++;
		

	}
	
	public static void printContents() {
		
		String sortedName = "";
		String sortedId = "";
		
		for (int i = 0; i <= TotalBooks-1; i++) {
			sortedName += ContentSortedByName.get(i).Name+ "   ";
			sortedId += ContentSortedByName.get(i).id+ "   ";
		}
		System.out.println("IDs:   "+sortedId);
		System.out.println("Names:   "+sortedName);
		
	}
	
	private void addNewItemInNameList(BookItem ItemToAdd, int LowestIndex, int HighestIndex, int loop) {
		

		// alphabetical sort with recursion :)
		String lowercaseItemToAdd = ItemToAdd.Name.toLowerCase();
		int newlowest = LowestIndex;
		int newhighest = HighestIndex;
		
		if (TotalBooks == 0) {
			ContentSortedByName.add(ItemToAdd);

			return;
		}
		else if (HighestIndex+1 == LowestIndex) {
			ContentSortedByName.add(LowestIndex, ItemToAdd);
			return;
		}

		else {
			
			// this is the ASCII character with value 1 :) i needed a value smaller than the ASCII value for space which has a value of 32
			// System.out.println((char)1);
			// System.out.println((int)'');   --> 1
			boolean onlyWeridCharLeft = true;
			String toAddElementName = lowercaseItemToAdd;
			for (int i = 0; i < loop; i++) {
				toAddElementName += "";
			}
			for (int i = LowestIndex; i <= HighestIndex; i++) {
				
				String inthElementName = ContentSortedByName.get(i).Name.toLowerCase();
				for (int j = 0; j < loop; j++) {
					inthElementName +="";

				}
				
				if (inthElementName.charAt(loop) != '') {
					onlyWeridCharLeft = false;
				}
						
				if (toAddElementName.charAt(loop) > inthElementName.charAt(loop)) {
					if (i == TotalBooks-1) {
						ContentSortedByName.add(ItemToAdd);
						return;
					}
					newlowest = i+1;
				}
				else if (toAddElementName.charAt(loop) < inthElementName.charAt(loop)){
					if (i == 0) {
						
						ContentSortedByName.add(0, ItemToAdd);
						return;
					}
					
					newhighest = i-1;
					break;
				}
			}
			// OMG it works :)
			
			if (onlyWeridCharLeft) {
				ContentSortedByName.add(HighestIndex+1, ItemToAdd);
				return;
			}
			addNewItemInNameList(ItemToAdd, newlowest, newhighest, loop+1);
		}
	}
	
	
	public static ArrayList<String> UpdateGUI() {
		
		// every time i make a change to the GUI i can make changes in the BookItem lists and then i update the gui afterwards with this method
		ArrayList<String> toReturn = new ArrayList<String>();
		if (sort == "Name") {

			for (int i = 0; i < ContentSortedByName.size(); i++) {
				BookItem bookItem = ContentSortedByName.get(i);
				toReturn.add(spaceManipulator(bookItem));
			}
		}
		else if (sort == "Id") {
			
			for (int i = 0; i < ContentSortedById.size(); i++) {
				
				BookItem bookItem = ContentSortedById.get(i);
				toReturn.add(spaceManipulator(bookItem));
			}
		}
		return toReturn;
	}
	
	public static void deleteItemByGUIIndex(int guiIndex) {
		

		// Here i delete the element i have selected from the ContentSortedByName and ContentSortedById arrays. afterwards i update the GUI with the updateGUI method.
		BookItem toDelete = null;
		if (sort == "Name") {
			toDelete = ContentSortedByName.get(guiIndex);
		}
		else if (sort == "Id") {
			toDelete = ContentSortedById.get(guiIndex);
		}
		
		ContentSortedById.remove(toDelete);
		ContentSortedByName.remove(toDelete);
		
		for (BookItem bookItem : ContentSortedById) {
			if (Integer.valueOf(bookItem.id)  > Integer.valueOf(toDelete.id)) {
				bookItem.id = Integer.toString(Integer.valueOf(bookItem.id)-1);

			}
		}
		TotalBooks --;
	}
		
	public static void changeEmail(int guiIndex, String EmailToChangeTo) {
		
		// very easy to do
		BookItem toChange = null;

		if (sort == "Name") {
			toChange = ContentSortedByName.get(guiIndex);
		}
		else if (sort == "Id") {
			toChange = ContentSortedById.get(guiIndex);
		}
		
		for (BookItem bookItem : ContentSortedById) {
			
			if (Integer.valueOf(bookItem.id)  == Integer.valueOf(toChange.id)) {
				toChange.email = EmailToChangeTo;
			}
		}
		
		for (BookItem bookItem : ContentSortedByName) {
			
			if (Integer.valueOf(bookItem.id)  == Integer.valueOf(toChange.id)) {
				toChange.email = EmailToChangeTo;

			}
		}
	}
	
	private static BookItem GetElementFromGUIIndex(int guiIndex) {
		
		BookItem toGet = null;
		if (sort == "Name") {
			toGet = ContentSortedByName.get(guiIndex);
		}
		else if (sort == "Id") {
			toGet = ContentSortedById.get(guiIndex);
		}
		return toGet;

	}
	
	public static void ChangeName(String newName, int guiIndex) {
		
		// this doesen't work because my sorting gets messed up :(
		BookItem toChange = GetElementFromGUIIndex(guiIndex);
		deleteItemByGUIIndex(guiIndex);
		new BookItem(newName, toChange.email);
		
	}
	
	private static String spaceManipulator(BookItem bookItem) {
		
		int mNaL = 15;
		int mNuL = 33;
		int mIL = 4;
		String toReturn = " ";
		
		if (bookItem.Name.length() >= mNaL) {
			toReturn+= bookItem.Name.substring(0, mNaL);
		}
		else {
			toReturn += bookItem.Name;
			for (int i = 0; i < (mNaL-bookItem.Name.length()); i++) {
				toReturn += " ";
			}
		}
		toReturn += " | ";
		
		if (bookItem.email.length() >= mNuL ) {
			toReturn+= bookItem.email.substring(0, mNuL);
		}
		else {
			toReturn += bookItem.email;
			for (int i = 0; i < (mNuL-bookItem.email.length()); i++) {
				toReturn += " ";
			}
		}
		toReturn += " | ";
		
		toReturn += bookItem.id;

		return toReturn;
	}
	
	public static void addNRandomElements(int n){
		
		int mNaL = 15;
		int mNuL = 33;
		int mIL = 4;
		
		Random r = new Random();

		for (int i = 0; i < n; i++) {
			int l =  r.nextInt(1, mNaL+1);
			String local = "";
			for (int j = 0; j <= l ; j++) {
				local += (char)r.nextInt(97, 123);
			}
			
			String[] endings = {"gmail", "icloud", "telia", "Outlook", "ProtonMail", "mail", "myself", "europe", "engineer"};
			String[] endings2 = {".com", ".se", ".gov", ".NET"};

			String emailFirstPart = "";
			String emailSecondPart = "";
			
			int firstEmailLenth = r.nextInt(2, 9);
			int secondEmailLenth = 18-firstEmailLenth-6;
			
			for (int j = 0; j < firstEmailLenth; j++) {
				emailFirstPart += (char)r.nextInt(97, 123);
			}
			for (int j = 0; j < secondEmailLenth; j++) {
				emailSecondPart += (char)r.nextInt(97, 123);
			}
			
			String localEmail = emailFirstPart+"."+emailSecondPart+"@"+endings[r.nextInt(0, endings.length)]+endings2[r.nextInt(0, endings2.length)];
			
			
			
			new BookItem(local, localEmail);
		}
		
	}
	
	public static void clearContent() {
		ContentSortedById.clear();
		ContentSortedByName.clear();
		TotalBooks = 0;
	}
	
	
	public static int search(String searchTerm) {
		
		if (sort == "Name") {
			for (int i = 0; i < ContentSortedByName.size(); i++) {
				
				if (ContentSortedByName.get(i).Name.equals(searchTerm)) {
					return i;
				}
			}
		}
		
		
		else if (sort == "Id") {
			for (int i = 0; i < ContentSortedById.size(); i++) {
					
				if (ContentSortedById.get(i).Name.equals(searchTerm)) {
					return i;
					
				}
			}
		}
		
		return -1;
	}
	
}

