package zlibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import core.Drawable;
import core.EventAdder;
import core.Entity;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.4
 * @since	2015-02-16
 * 
 * Class for creating buttons that are viewable for the player.
 * Buttons can implement events on mouse click.
 * ZButtons are ZImages.
 * 
 * Can be constructed with an Image or using a default appearance.
 */

public class ZButton extends ZEntity implements Entity, Drawable
{
	public ZButton (Image image, int x, int y, EventAdder eventAdder, String eventOnClick) {
		setImage(image);
		setX(x);
		setY(y);
		setEventAdder(eventAdder);
		setEventOnClick(eventOnClick);
	}
	
	public ZButton (String string, int x, int y, int w, int h, EventAdder eventAdder, String eventOnClick) {
		setImage(createButtonFromString(string, w, h));
		setX(x);
		setY(y);
		setEventAdder (eventAdder);
		setEventOnClick (eventOnClick);
	}
		
	/**
	 * Checks if the button is in the area where the mouse click was detected using parameters x & y.
	 * If it was returns true otherwise false.
	 * 
	 * @param mouseX
	 * @param mouseY
	 * @return boolean
	 */
	private boolean isMouseInside (int mouseX, int mouseY) {
		return (mouseX >= getX() && mouseY >= getY() &&
				mouseX <= getX() + getImage().getWidth(null) &&
				mouseY <= getY() + getImage().getHeight(null));
	}
	
	/**
	 * 
	 */
	private Image createButtonFromString (String s, int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        
        // setting funky constants
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setFont(new Font("Verdana", Font.PLAIN, 18));
        FontMetrics fm = g.getFontMetrics();

        // draw button border and background
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(2, 2, w-4, h-4);
		
		// draw string centered in button
		g.setColor(Color.BLACK);
		g.drawString(s, (float)((w-fm.stringWidth(s))/2), (float) (h+fm.getHeight()/2)/2);

		//releasing resources
		g.dispose();
		
		return img;
	}
}
