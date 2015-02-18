package combat;

import java.util.Random;

import character.Character;

import combat.Combat;

/**
 * @author      Andreas Bäckevik
 * @version     0.1
 * @since       2015-02-01
 */

public class Enemy extends Character
{
	Random rand;
	public Enemy (String name) {
		super(name);
		rand = new Random();
	}
	
	private void AI(){

	}
	
	// AI and other stuff to do
}
