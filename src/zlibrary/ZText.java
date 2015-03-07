package zlibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

/**
 * Class for creating images with text to be displayed.
 * 
 * @author	Anton Andren		<>
 * @version	1.0					<2015-03-07>
 * @since	2015-02-16
 */

public class ZText extends ZEntity
{
	/**
	 * Constructor for ZText
	 * @param string - what text to be converted to image
	 * @param x - x position of the text
	 * @param y - y position of the text
	 * @param fontSize - the size of the font
	 */
	public ZText (String string, int x, int y, int fontSize) {
		setImage(createImageFromString(string, fontSize));
		setX(x);
		setY(y);
	}
	
	/**
	 * Constructor for setting a cap on the text length, adding delimeter at the end
	 * @param string - what text to be converted to image
	 * @param x - x position of the text
	 * @param y - y position of the text
	 * @param w - maximum width of the text
	 * @param h - maximum height of the text
	 * @param fontSize - the size of the font
	 * @param delimteter - what to be displayed if maximum w or h was surpassed
	 */
	public ZText (String string, int x, int y, int w, int h, int fontSize, String delimeter) {
		setImage(createImageFromStringWrapped(string, w, h, fontSize, delimeter));
		setX(x);
		setY(y);
	}
	
	/**
	 * Creates the image displaying the text s
	 * @param s		- string to be displayed in the image
	 * @param fs	- fontSize to be used
	 * @return image to be displayed
	 */
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
	
	/**
	 * Creates an image containing the text s (wrapped with delimiter if needed).
	 * @param s		- string to be displayed in the image
	 * @param w		- max width of the image
	 * @param h		- max height of the image
	 * @param fs	- fontSize to be used
	 * @param d		- string to be used as delimiter
	 * @return image to be displayed
	 */
	private Image createImageFromStringWrapped (String s, int w, int h, int fs, String d) {
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
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
				
		// Wanted \n should be kept and used as intended.
		s = s.replace("/\n/g", " \n ");
		// Eliminate multiple spaces
		s = s.trim().replaceAll(" +", " ");
		// Split the strings into words for matching if space is available.
		String[] words = s.split(" ");
		
		// Sort how the string should be rendered out.
		// TO-DO check if a word is wider than the entire render space...
		// TO-DO Check if stringWidth returns what we want. we always have pixels over on each row.
		String stringToBeRendered = words[0] + " ";
		int spaceWidth = fm.stringWidth(" ");
		int currentLineWidth = fm.stringWidth(words[0]) + spaceWidth;
		int currentLineIndex = 1;
		int maxLineIndex = h / fs;
		
		for(int i = 1; i < words.length; ++i) {
			//if the word is an intended line break
			if (words[i].equals("\n")){
				if(currentLineIndex < maxLineIndex) {
					stringToBeRendered += "\n";
					currentLineIndex++;
					currentLineWidth = 0;
				} else {
					stringToBeRendered += d;
					break;
				}
			} else {
		//if the word is a normal word and it isn't the last line to be rendered.
		if(currentLineIndex < maxLineIndex){
			if((currentLineWidth + fm.stringWidth(words[i])) < w){
				stringToBeRendered += words[i] + " ";
				currentLineWidth += (spaceWidth + fm.stringWidth(words[i]));
			} else {
				stringToBeRendered += "\n" + words[i]+ " ";
				currentLineIndex++;
				currentLineWidth = fm.stringWidth(words[i]) + spaceWidth;
				}
			} else { 
				//if its the last line, make sure the delimiter has room.
				if((currentLineWidth + fm.stringWidth(words[i]) + fm.stringWidth(d)) < w){
					stringToBeRendered += words[i] + " ";
					currentLineWidth += (spaceWidth + fm.stringWidth(words[i]));
					} else {
						stringToBeRendered += d;
						break;
					}
				}
			}
		}
 
		g.dispose();
		 
		image = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
		g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setFont(g.getFont());
		fm = g.getFontMetrics();
		
		int currentLineHeight = 0;
		g.setColor(Color.black);
		for (String std : stringToBeRendered.split("\n")) {
			 g.drawString(std, 0, currentLineHeight += fs);
		}                      
	
		return image;
	}
}
