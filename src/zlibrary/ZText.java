package zlibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

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

public class ZText extends ZEntity
{
	public ZText (String string, int x, int y, int fontSize) {
		setImage(createImageFromString(string, fontSize));
		setX(x);
		setY(y);
	}
	
	public ZText (String string, int x, int y, int w, int h, int fontSize, String delimeter) {
		setImage(createImageFromStringWrapped(string, w, h, fontSize, delimeter));
		setX(x);
		setY(y);
	}
	
	private Image createImageFromString (String s, int fs) {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setFont(new Font("Verdana", Font.PLAIN, fs));
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        FontMetrics fm = g.getFontMetrics();

        String[] lines = s.split("\n");
        int width = 0;        
        for (int i = 0; i < lines.length; i++) {
        	width = Math.max(width, fm.stringWidth(lines[i]));
        }
        int height = fs*lines.length+(fs/2);
        g.dispose();
        
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        g = img.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g.setFont(new Font("Verdana", Font.PLAIN, fs));
        fm = g.getFontMetrics();
        
		g.setColor(Color.BLACK);
		for (int i = 0; i < lines.length; i++) {
			g.drawString(lines[i], 0, fs*(i+1));
		}
        g.dispose();
		
        return img;
	}
	
	private Image createImageFromStringWrapped (String s, int w, int h, int fs, String d) {
		return createImageFromString(s, fs);
	}
}
