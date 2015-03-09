package zlibrary;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import core.EventAdder;

/**
 * Abstract class for creating containers for Z-objects. Contains a component list and an entity list to self keep track of its sub-components.
 * 
 * @author      Mattias Benngard	
 * @version     1.0					
 * @since       2015-02-27
 */
public abstract class ZContainer extends ZImage implements ZDrawable
{
	protected List<ZComponent> components = new ArrayList<>();
	protected List<ZEntity> entities;
	
	/**
	 * Constructor for ZContainer
	 * @param image - which image to be drawn as the background of the container
	 * @param x - where it is placed in x
	 * @param y - where it is placed in y
	 * @param eventAdder - to what event queue it should be adding
	 * @param entities - a reference to all existing entities
	 */
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
	 * Calls render for the background ZImage of the container as well as calling render for all the Z-objects that the container holds.
	 * @param g - screen to render in
	 */
	@Override
	public void render(Graphics g) {
		super.render(g);
		for (ZComponent i : components) {
			i.render(g);
		}
	}
}
