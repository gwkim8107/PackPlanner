import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import factory.Item;
import factory.Pack;

public class PackPlanner {

	public static void main(String[] args) throws FileNotFoundException {
		List<Item> itemlist = new ArrayList<Item>();
		List<Pack> packlist= new ArrayList<Pack>();
		Pack pack = null;
        Checker checker = new Checker();
        
        File input = new File("./src/input");
		Scanner scan = new Scanner(input);
		String[] conditionsArr = scan.nextLine().split(",");
		
		String sortKind = conditionsArr[0];
		int maxPieces = Integer.parseInt(conditionsArr[1]);
		float maxWeight = Float.parseFloat(conditionsArr[2]);
        		
    	try {
			String str = " ";
	    	while(scan.hasNextLine()) {
	    		String[] arrItems = scan.nextLine().split(",");
	    		
	    		// To prevent halting
	    		if(arrItems[0].equals("")) {
	    			break;
	    		}
	    		
	    		Item item = new Item(Integer.parseInt(arrItems[0]), Integer.parseInt(arrItems[1]), 
	    				Integer.parseInt(arrItems[2]), Float.parseFloat(arrItems[3]));
	    		
	    		if(arrItems.length == 4) {
	    			itemlist.add(item);
	    		}
	    	}
	    	scan.close();
    	}catch(NumberFormatException ne) {
    		System.out.println("Error msg : "+ne.getMessage());
    	}
    	
    	// Sorting at here
    	if(sortKind.equals("SHORT_TO_LONG")) {
    		Collections.sort(itemlist,checker);
    	}else if(sortKind.equals("LONG_TO_SHORT")) {
    		Collections.sort(itemlist,checker);
    		Collections.reverse(itemlist);
    	}
    	
    	float currentWeight = 0.0f;
    	int currentQuantity = 0;
    	int packNumber = 1;
    	for(Item item : itemlist ) {
    		
    		// if remaining available quantity at the same pack, fill it up.
			if(currentWeight > 0 || currentQuantity > 0 ) {
				
				pack = new Pack(packNumber-1, item.getId(), item.getPieceWeight(), currentQuantity, item.getLength());
				packlist.add(pack);
			}
			
			
    		pack = new Pack(packNumber, item.getId(), item.getPieceWeight(), item.getQuantity() - currentQuantity , item.getLength());
    		
			
    		// updating current weight, pieces of items
    		if(item.getId() == pack.getId()) {
        		
    			currentWeight = maxWeight - ( item.getPieceWeight() * item.getQuantity());
    			currentQuantity = maxPieces - item.getQuantity();
    			
    			packlist.add(pack);
    			
    		}
    		
    		packNumber++;
    	}
    	
//    	#Example output for the above input:
//    		Pack Number: 1
//    		1001,6200,30,9.653
//    		2001,7200,10,11.21
//    		Pack Length: 7200, Pack Weight: 401.69
    	
    	String printPackNo = ""; 
    	System.out.println("**************************************************************************");
    	int lastIndex = packlist.size();
    	int countedIndex = 0;
    	for(Pack printPack : packlist) {
//    		printPackNo = printPack.getNumber() +"";
    		if(!printPackNo.equals(printPack.getNumber() +"")) {
    			printPackNo = printPack.getNumber() +"";
    			System.out.println("Pack Number: "+ printPack.getNumber());
    			System.out.println(printPack.getId()+ ", "+ printPack.getLength() + ", "
    					+ printPack.getCurrentItems() + ", "+ printPack.getCurrentWeight() );
    		}else {
    			System.out.println(printPack.getId()+ ", "+ printPack.getLength() + ", "
    					+ printPack.getCurrentItems() + ", "+ printPack.getCurrentWeight() );
    			
    			System.out.println("Pack Length: "+getPackLength(itemlist) + ", Pack Weight: "+ getPackWeight(packlist, printPackNo ) );
    			
    		}
    		
    		countedIndex++;
    		
    		if(lastIndex == countedIndex) {
    			System.out.println("Pack Length: "+getPackLength(itemlist) + ", Pack Weight: "+ getPackWeight(packlist, printPackNo ) );
    		}
    		
    		
    	}
    	System.out.println("**************************************************************************");
	}
	
	public static int getPackLength(List<Item> itemlist) {
        int maxLength = 0;
        for (Item item : itemlist) {
            if (item != null) {
            	if(maxLength < item.getLength())
            		maxLength = item.getLength();
            }
        }
        return maxLength;
    }
	
	public static float getPackWeight(List<Pack> packlist, String packNumber) {
        float sum = 0;
        String printPackNo = packNumber;
        for (Pack pack : packlist) {
            if (pack != null) {
            	if(printPackNo.equals(pack.getNumber()+"")) {
            		sum = sum + (pack.getCurrentWeight() * pack.getCurrentItems());
            	}
            }
        }
        return sum;
    }
}

class Checker implements Comparator<Item> {
	@Override
	public int compare(Item o1, Item o2) {
		if(o1.getLength() > o2.getLength()) {
			return -1;
		}else if(o1.getLength() < o2.getLength()) {
			return 1;
		}
		else {
			return 0;
		}
	}
}
