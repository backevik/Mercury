package zlibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import core.Entity;

public class ZTextArea extends ZImage implements Entity {

	private int w;
	private int h;
	private String text;
	private boolean textIsRendered;
	
	public ZTextArea(int x, int y, int w, int h) {
		super("", x, y, w, h);
		this.w = w;
		this.h = h;
		text = "";
	}
	
	public void addTextWithoutLineFeed (String s) {
		text += s;
		textIsRendered= false;
	}
	
	public void addText (String s) {
		if (!text.isEmpty()) {
			text += "\n";
		}
		text += s;
		textIsRendered = false;
	}
	@Override
	public void render (Graphics g) {
		if (textIsRendered == false) {
			image = createImageFromString ();
			textIsRendered = true;
		}
		super.render(g);
	}
	
	private Image createImageFromString () {
		Font font = new Font("Courier New", Font.PLAIN, 12);
		BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setFont(font);
		FontMetrics fontMetric = g.getFontMetrics();

		String[] words = text.split(" ");
		
		int currentWidth = fontMetric.stringWidth(words[0]);
		String stringToBeRendered = words[0];
		
		for (int i = 1; i < words.length; ++i) {
			words[i] = words[i].trim();
			
			/**
			 * TO-BE-DONE:: Too Long to be rendered? MegaLongWordThatNeedsToEndWithThreeDotsInOtherWordsBeClipped...
			 */
			int a = (int)fontMetric.stringWidth(" " + words[i]);
			if (currentWidth + a <= w) {
				stringToBeRendered += " " + words[i];
				currentWidth += a;
			} else {
				stringToBeRendered += "\n" + words[i];
				System.out.println(currentWidth);
				currentWidth = 0;				
			}
		}
		
		System.out.println(stringToBeRendered);
		g.setColor(Color.GRAY);
		g.fillRect(1, 1, w-2, h-2);		
		g.setColor(Color.BLACK);
		int y = 0;
		for (String s : stringToBeRendered.split("\n")) {
			g.drawString(s, 0, y += fontMetric.getHeight());
		}			
		g.dispose ();
		
		return image;
		
		//g.clip(new Rectangle(0, image.getHeight()-h, w, image.getHeight()));
	}

	@Override
	public void onClick(int mouseX, int mouseY) {}
}
