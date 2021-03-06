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
 * GUI component for Text Areas
 * 
 * @author	Anton Andren		
 * @version	1.0					
 * @since	2015-02-16
 */

public class ZTextArea extends ZComponent 
{
	private int w;
	private int h;
	private int fontSize;
	private String text;
	private boolean textIsRendered;
	
	/**
	 * Constructor for ZTextArea
	 * @param x - x position of the text
	 * @param y - y position of the text
	 * @param w - maximum width of the text
	 * @param h - maximum height of the text
	 * @param fontSize - the size of the font
	 */
	public ZTextArea(int x, int y, int w, int h, int fontSize) {
		// TO-DO ADD CONSTRUCTOR FOR PICTURE AND CHANGE DRAWIMAGEFROMSTRING ACCORDINGLY
		
		this.w = w;
		this.h = h;
		setX(x);
		setY(y);
		this.fontSize = fontSize;
		text = "";
	}
	
	/**
	 * Adds text to be displayed in the ZTextArea
	 * @param s	-text to be displayed
	 */
	public void addTextWithoutLineFeed (String s) {
		text += s;
		textIsRendered= false;
	}
	
	/**
	 * Adds text to be displayed in the ZTextArea after a line break
	 * @param s	-text to be displayed
	 */
	public void addText (String s) {
		if (!text.isEmpty()) {
			text += "\n";
		}
		text += s;
		textIsRendered = false;
	}
	
	/**
	 * Checks if the text displayed is updated and renders accordingly.
	 */
	@Override
	public void render (Graphics g) {
		if (textIsRendered == false) {
			setImage(createImageFromString ());
			textIsRendered = true;
		}
		super.render(g);
	}
	
	/**
	 * Creates an image based on the text in String text with the size of int w (width) and int h (height)
	 * @return image
	 */
	private Image createImageFromString () {
	     BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	     Graphics2D g = image.createGraphics();
	     g.setFont(new Font("Verdana", Font.PLAIN, fontSize));
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
		 text = text.replace("/\n/g", " \n ");
		 // Eliminate multiple spaces
		 text = text.trim().replaceAll(" +", " ");
		 //System.out.println(s);
		 // Split the strings into words for matching if space is available.
		 String[] words = text.split(" ");
		 
		 // Sort how the string should be rendered out.
		 // TO-DO check if a word is wider than the entire render space...
		 // TO-DO Check if stringWidth returns what we want. we always have pixels over on each row.
		 String stringToBeRendered = words[0] + " ";
		 int spaceWidth = fm.stringWidth(" ");
		 int currentLineWidth = fm.stringWidth(words[0]) + spaceWidth;
		 int lineIndex = 1;
		 
		 for(int i = 1; i < words.length; ++i){
			 //if the word is an intended line break
			 if (words[i].equals("\n")){
				 stringToBeRendered += "\n";
				 currentLineWidth = 0;
				 lineIndex++;
			 }else {
				 //if the word is a normal word
				 if((currentLineWidth + fm.stringWidth(words[i])) < w){
					 stringToBeRendered += words[i] + " ";
					 currentLineWidth += (spaceWidth + fm.stringWidth(words[i]));
				 } else {
					 stringToBeRendered += "\n" + words[i]+ " ";
					 currentLineWidth = fm.stringWidth(words[i]) + spaceWidth;
					 lineIndex++;
				 }
			 }
		 }
	 
		 g.dispose();
		 
	     image = new BufferedImage(w , lineIndex * fontSize + fm.getDescent() , BufferedImage.TYPE_INT_ARGB);
		 g = image.createGraphics();
	     g.setFont(new Font("Verdana", Font.PLAIN, fontSize));
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
			 g.drawString(std, 0, currentLineHeight += fontSize);
			 }
		 
		 //CLIP IMAGE ACCORDINGLY BY COPYING WHAT IS WANTED FROM THE PREVIOUS INTO A NEW ONE		 
	     BufferedImage renderImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
	     Graphics2D g2r = renderImage.createGraphics();
	     if(image.getHeight() > h){
	    	 g2r.drawImage(image, 0, 0, w, h, 0, (int)(image.getHeight() - h), w, (int)image.getHeight(), null, null);
	     } else {
	    	 g2r.drawImage(image, 0, 0, w, lineIndex * fontSize + fm.getDescent(), 0, 0, w, (int)image.getHeight(), null, null);
	     }

	     g2r.dispose();
	     g.dispose();
	
		 return renderImage;
	}
}