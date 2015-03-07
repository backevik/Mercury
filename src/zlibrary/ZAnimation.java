package zlibrary;

import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import core.Game;
import core.RealTime;

/**
 * Stores an animation, a list of images, if added to real time list it will automatically switch between the images.
 * 
 * @author      Mattias Benngard	<mbengan@gmail.com>
 * @version     1.0					<2015-02-27>
 * @since       2015-03-07
 */

public class ZAnimation extends ZImage implements RealTime
{
	private List<Image> images;
	private int updatesLeftToIncrementIndex;
	private int index;
	
	/**
	 * 
	 * @param images - list of images to be rendered
	 * @param x - position of the ZImage in x
	 * @param y - position of the ZImage in y
	 * @param index - current index of the picture
	 */
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