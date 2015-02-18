package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
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
	
	public ZImage (String string, int x, int y, int fontSize) {
		this.image = createImageFromString (string, fontSize);
		this.x = x;
		this.y = y;
	}
	
	public ZImage (String string, int x, int y, int w, int h) {
		this.image = createImageFromString (string, w, h);
		this.x = x;
		this.y = y;
	}
	
	public void centerImage (int w) {
		x = (w + image.getWidth(null))/2;
	}
	
	protected Image createImageFromString (String s, int fontSize) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        Font font = new Font("Verdana", Font.PLAIN, fontSize);
        g2d.setFont(font);
        FontMetrics fm = g2d.getFontMetrics();
        int width = fm.stringWidth(s);
        int height = fm.getHeight();
        g2d.dispose();

        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g2d = img.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.setFont(font);
        fm = g2d.getFontMetrics();
        g2d.setColor(Color.BLACK);
        g2d.drawString(s, 0, fm.getAscent());
        g2d.dispose();
		
		return img;
	}
	
	protected Image createImageFromString (String s, int w, int h) {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		Font font = new Font("Verdana", Font.PLAIN, 18);
		FontMetrics fontMetric = g.getFontMetrics();

		//set color and other parameters
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.WHITE);
		g.fillRect(2, 2, w-4, h-4);
		g.setColor(Color.BLACK);
		g.setFont(font);
		g.drawString(s, (float)((w-fontMetric.stringWidth(s))/2), (float) (h+fontMetric.getAscent())/2);

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
