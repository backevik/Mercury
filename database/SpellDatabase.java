package database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import character.Spell;

/**
 * @author		Daniel Edsinger 	<danieledsinger@hotmail.com>
 * @version		0.3
 * @since		2015-02-17
 */
	
public class SpellDatabase extends DataState implements Serializable
{
	
	
	private static final long serialVersionUID = 6973694815605562305L;
	private final List<Spell> spells = new ArrayList<>();
	
	public SpellDatabase(){
	}
	
	/**
	 * Add an item to the list
	 * @param item
	 */
	public void addItem(Spell spell) {
		spells.add(spell);
	}
	
	/**
	 * remove an item from the list
	 * @param item
	 */
	public void removeItem(Spell spell) {
		spells.remove(spell);
	}
	
	/**
	 * return this list of items
	 * @return
	 */
	public List<Spell> getSpells() {
		return spells;
	}
	
	
	@Override
	public void save() {
		try{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("SpellDatabase.dat"));
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
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("SpellDatabase.dat"));
			SpellDatabase tmp = (SpellDatabase)in.readObject();
			spells.clear();
			
			for(Spell item : tmp.getSpells())
			{
				spells.add(item);
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
