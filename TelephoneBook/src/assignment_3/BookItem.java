package assignment_3;

import java.util.ArrayList;

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
		
		int newlowest = LowestIndex;
		int newhighest = HighestIndex;
		
		if (TotalBooks == 0) {
			ContentSortedByName.add(ItemToAdd);

			return;
		}
		else if (HighestIndex+1 == LowestIndex) {
			System.out.println("not here");
			ContentSortedByName.add(LowestIndex, ItemToAdd);
			return;
		}
		
//		else if (loop == ContentSortedByName.get(LowestIndex).Name.length()) {
//			ContentSortedByName.add(HighestIndex+1, ItemToAdd);
//			System.out.println("here");
//			return;
//		}
		
		//fails for:
		//a
		//albus
		//albuss
		//albu   <-----
		//b
		
		//a
		//as
		//ass
		//a <-----
		//b
		

		else {
			boolean onlySpaces = true;
			String toAddElementName = ItemToAdd.Name;
			for (int i = 0; i < loop; i++) {
				toAddElementName += " ";
			}
			for (int i = LowestIndex; i <= HighestIndex; i++) {
				
				String inthElementName = ContentSortedByName.get(i).Name;
				for (int j = 0; j < loop; j++) {
					inthElementName +=" ";

				}
				
				if (inthElementName.charAt(loop) != ' ') {
					onlySpaces = false;
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
			// :)
			
			if (onlySpaces) {
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
		
		printContents();
		BookItem toDelete = null;
		if (sort == "Name") {
			toDelete = ContentSortedByName.get(guiIndex);
		}
		else if (sort == "Id") {
			toDelete = ContentSortedById.get(guiIndex);
		}
		//System.out.println(toDelete.Name);
		for (int i = 0; i < ContentSortedById.size(); i++) {
			if (Integer.valueOf(ContentSortedById.get(i).id)  == Integer.valueOf(toDelete.id) ) {
				ContentSortedById.remove(i);
				break;
			}
		}
		for (int i = 0; i < ContentSortedByName.size(); i++) {
			if (Integer.valueOf(ContentSortedByName.get(i).id)  == Integer.valueOf(toDelete.id) ) {
				ContentSortedByName.remove(i);
				break;
			}
		}


		TotalBooks --;
		printContents();

	}
	
	public static void changeNumber(int guiIndex, String numberToChangeTo) {
		
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
	
	private static String spaceManipulator(BookItem bookItem) {
		
		int maxNameLenth = 10;
		int maxNumberLenth = 10;
		// doesnt work for lenth 10 for some reason
		String toReturn = "";

		
		// make my own character width calculator!! that is a cool idea :)
		toReturn += bookItem.id;
		
		return bookItem.Name+"      "+bookItem.Number+"      "+bookItem.id;
	}
}
