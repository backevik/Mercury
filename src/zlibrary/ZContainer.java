package zlibrary;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import core.EventAdder;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.4
 * @since	2015-02-17
 *
 * Class for creating containers for Z-objects.
 */
public abstract class ZContainer extends ZImage implements ZDrawable
{
	protected List<ZComponent> components = new ArrayList<>();
	protected List<ZEntity> entities;
	
	public ZContainer (Image image, int x, int y, EventAdder eventAdder, List<ZEntity> entities) {
		super(image, x, y);
		this.entities = entities;
	}
	
	/**
	 * Called when removing this container from Game.removeContainer(ZContainer z)
	 */
	public void remove () {
		for (ZComponent z : components) {
			entities.remove(z);
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
		for (ZComponent i : components) {
			i.render(g);
		}
	}
}
