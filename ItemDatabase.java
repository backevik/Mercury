package database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import character.Item;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */

public class ItemDatabase extends DataState implements Serializable
{
	
	private static final long serialVersionUID = 6973694815605562305L;
	private final List<Item> items = new ArrayList<>();
	
	public ItemDatabase(){
	}
	
	/**
	 * Add an item to the list
	 * @param item
	 */
	public void addItem(Item item) {
		items.add(item);
	}
	
	/**
	 * remove an item from the list
	 * @param item
	 */
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	/**
	 * return this list of items
	 * @return
	 */
	public List<Item> getItems() {
		return items;
	}
	
	
	@Override
	public void save() {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ItemDatabase.dat"));
			out.writeObject(this);
			
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		
	}
	
	@Override
	public void load() {
		try{
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("ItemDatabase.dat"));
			ItemDatabase tmp = (ItemDatabase)in.readObject();
			items.clear();
			
			for(Item item : tmp.getItems())
			{
				items.add(item);
			}
			in.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}
		
		
	}
	
}
