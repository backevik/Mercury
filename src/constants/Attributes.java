package constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author	Mattias Benngård	<mbengan@gmail.com>
 * @version 1.0					<2015-02-27>
 * @since   2015-02-01
 */ 

public final class Attributes
{
	/**
	 * Important: Not the order, change the order of initialization and it may cause grave errors.
	 */
	private static List<String> names = new ArrayList<String>(Arrays.asList(
		"Strength",
		"Quickness",
		"Endurance",
		"Focus"
	));
	
	/**
	 * Get an unmodifiable list of attribute names.
	 * @return a copy of names
	 */
	public static List<String> getNames () {
		return Collections.unmodifiableList(names);
	}
}
