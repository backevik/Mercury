package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import core.Drawable;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.4
 * @since	2015-02-17
 * 
 * ZImage is the base class for all Z-components. It stores an Image and the location of said Image.
 * 
 * Can be constructed with an existing Image or by using a default appearance.
 *
 */
public class ZImage implements Drawable
{
	protected Image image;
	protected int x;
	protected int y;
	
	public ZImage (Image image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}
	
	public ZImage (String string, int x, int y, int w, int h) {
		this.image = createImageFromString (string, w, h);
		this.x = x;
		this.y = y;
	}
	
	protected Image createImageFromString (String s, int w, int h) {
		Font font = new Font("Verdana", Font.PLAIN, 14);
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		FontMetrics fontMetric = g.getFontMetrics();

		//set color and other parameters
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.WHITE);
		g.fillRect(2, 2, w-4, h-4);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(s, (float)((w-fontMetric.stringWidth(s))/2), (float) (h+fontMetric.getHeight())/2);

		//releasing resources
		g.dispose();
		
		return image;
	}
	
	/**
	 * Renders the Image that ZImage holds in the right position.
	 */
	public void render (Graphics g) {
		g.drawImage(image, x, y, null);
	}
}
