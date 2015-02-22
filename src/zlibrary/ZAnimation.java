package zlibrary;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import core.Game;
import core.RealTime;

public class ZAnimation extends ZImage implements RealTime
{
	private List<Image> images;
	private int updatesLeftToIncrementIndex;
	private int index;
	
	public ZAnimation(List<Image> images, int x, int y, int index) {
		super(null, x, y);
		this.images = images;
		this.index = index;
	}

	@Override
	public void update () {
		updatesLeftToIncrementIndex--;
		
		if (updatesLeftToIncrementIndex <= 0) {
			index++;
			
			if (index > images.size()) {
				index = 0;
			}
			
			updatesLeftToIncrementIndex = (int) Game.GAME_UPDATE_RATE;
		}
	}
	
	@Override
	public void render (Graphics g) {
		g.drawImage(images.get(index), getX(), getY(), null);
	}
}