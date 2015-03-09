package constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Contains all vitals existing in the game
 * 
 * @author	Mattias Benngård	
 * @version 1.0					
 * @since   2015-02-05
 */ 

public final class Vitals
{
	/**
	 * Important: Not the order, change the order of initialization and it may cause grave errors.
	 */
	private static List<String> names = new ArrayList<String>(Arrays.asList(
		"Health",
		"Energy"
	));
	
	/**
	 * Get an unmodifiable list of vital names.
	 * @return a copy of names
	 */
	public static List<String> getNames () {
		return Collections.unmodifiableList(names);
	}
}
