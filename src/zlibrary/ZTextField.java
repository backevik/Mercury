package zlibrary;

import java.awt.Graphics;


/**
 * @author	Anton Andrén
 * @version	0.1 (testing)
 * @since	2015-02-18
 * 
 * Public ZtextField (String, int, int, int, int)
 * creates a textfield editable by the user with the specified size and a first string to be displayed.
 * 
 * Public ZtextField (int, int, int, int)
 * creates a textfield editable by the user with the specified size
 * 
 * Public getString ()
 * Retrives the string currently beeing displayed.
 * 
 * Public setString (String)
 * Sets the currently displayed string to param.
 * 
 */
import core.Drawable;

public class ZTextField extends ZButton implements Drawable
{
	private static String displayedString;
	

	public ZTextField (String firstTextToBeDisplayed, int x, int y, int w, int h){
	}
	

	public ZTextField (int x, int y, int w, int h){
	}
	
	
	/**
	 * Displays S in the ZTextField
	 * @param S
	 */
	public void setText (String s) {		
	}
	
	/**
	 * Returns the String currently displayed in the ZTextField
	 * @return String inside the text field
	 */
	public String getText () {
		return displayedString;
	}

	/**
	 * adds the ZTextField to g for rendering
	 * @param Graphics g
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
	}

}
