package constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.1
 * @since       2015-02-05
 */ 

public final class Vitals
{
	private static List<String> names = new ArrayList<String>(Arrays.asList(
		"Health",
		"Energy"
	));
	
	public static List<String> getNames () {
		return Collections.unmodifiableList(names);
	}
}
