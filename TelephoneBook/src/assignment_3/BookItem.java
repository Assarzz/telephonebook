package assignment_3;

import java.awt.Window.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.ListModel;

// this is a very big class that stores the entire list of elements that is displayed aswell as having a lot of methods for adding, updating, deleting and changing gui elements
public class BookItem{
	
	static ArrayList<BookItem> ContentSortedById = new ArrayList<BookItem>();
	static ArrayList<BookItem> ContentSortedByName = new ArrayList<BookItem>();

	static int TotalBooks = 0;
	static String sort = "Id";
	String Name;
	String Number;
	String id;
	
	public BookItem(String name, String number) {
		this.Name = name;
		this.Number = number;
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
		
		// every time i make a change to the GUI i can make changes in the BookItem lists and then i update the gui afterwards
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
		
	public static void changeNumber(int guiIndex, String numberToChangeTo) {
		
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
				toChange.Number = numberToChangeTo;
			}
		}
		
		for (BookItem bookItem : ContentSortedByName) {
			
			if (Integer.valueOf(bookItem.id)  == Integer.valueOf(toChange.id)) {
				toChange.Number = numberToChangeTo;

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
		new BookItem(newName, toChange.Number);
		
	}
	
	private static String spaceManipulator(BookItem bookItem) {
		
		// 13 chars
		// 4 spaces
		// 12 numbers
		// 4 spaces
		// 3 numbers
		
		int mNaL = 13;
		int mNuL = 10;
		int mIL = 3;
		String toReturn = "  ";
		
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
		
		if (bookItem.Number.length() >= mNuL ) {
			toReturn+= bookItem.Number.substring(0, mNuL);
		}
		else {
			toReturn += bookItem.Number;
			for (int i = 0; i < (mNuL-bookItem.Number.length()); i++) {
				toReturn += " ";
			}
		}
		toReturn += " | ";
		
		toReturn += bookItem.id;

		return toReturn;
		//return bookItem.Name+"      "+bookItem.Number+"      "+bookItem.id;
	}
	
	public static void addNRandomElements(int n){
		
		Random r = new Random();
		
		
		for (int i = 0; i < n; i++) {
			int l =  r.nextInt(1, 13);
			String local = "";
			for (int j = 0; j <= l ; j++) {
				local += (char)r.nextInt(97, 123);
			}
			
			String localN = "07";
			for (int j = 0; j < 8; j++) {
				localN += Integer.toString(r.nextInt(0, 10));
			}
			
			new BookItem(local, localN);
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

