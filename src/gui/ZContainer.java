package gui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import core.Drawable;
import core.EventAdder;
import core.MouseObject;

/**
 * @author	Anton Andr�n & Mattias Benng�rd
 * @version	0.4
 * @since	2015-02-17
 *
 * Class for creating containers for Z-objects.
 */
public abstract class ZContainer extends ZImage implements Drawable
{
	protected List<ZImage> components = new ArrayList<>();
	protected List<MouseObject> mouseObjects;
	
	public ZContainer (EventAdder eventAdder, Image image, int x, int y, List<MouseObject> mouseObjects) {
		super(image, x, y);
		this.mouseObjects = mouseObjects;
	}
	
	/**
	 * Called when removing this container from Game.removeContainer(ZContainer z)
	 */
	public void remove () {
		for (ZImage z : components) {
			mouseObjects.remove(z);
			z = null;
		}
	}

	/**
	 * Calls render for the background ZImage of the container 
	 * as well as calling render for all the Z-objects that the container holds.
	 * 
	 * @param Graphics g
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
		for (ZImage i : components) {
			i.render(g);
		}
	}
}
