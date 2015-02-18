package constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author      Mattias Benng�rd	<mbengan@gmail.com>
 * @version     0.1
 * @since       2015-02-01
 */ 

public final class Attributes
{
	private static List<String> names = new ArrayList<String>(Arrays.asList(
		"Strength",
		"Quickness",
		"Endurance"
	));
	
	public static List<String> getNames () {
		return Collections.unmodifiableList(names);
	}
}
