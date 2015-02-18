package constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.4
 * @since       2015-02-05
 */ 

public final class Skills
{
	private static List<String> names = new ArrayList<String>(Arrays.asList(
		"Attack",
		"Defense",
		"Speed"
	));
	
	public static List<String> getNames () {
		return Collections.unmodifiableList(names);
	}
}
