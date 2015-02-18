package gui;

import java.awt.Image;

import core.Drawable;
import core.EventAdder;
import core.MouseObject;

public class ZButton extends ZImage implements MouseObject, Drawable
{
	private EventAdder eventAdder;
	private String eventOnClick;
	
	public ZButton (EventAdder eventAdder, String eventOnClick, Image image, int x, int y) {
		super(image, x, y);
		this.eventAdder = eventAdder;
		this.eventOnClick = eventOnClick;
	}
	
	public ZButton (EventAdder eventAdder, String eventOnClick, String string, int x, int y, int w, int h) {
		super(string, x, y, w, h);
		this.eventAdder = eventAdder;
		this.eventOnClick = eventOnClick;
	}
	
	@Override
	public void onClick(int mouseX, int mouseY) {
		if (isMouseInside (mouseX, mouseY)) {
			eventAdder.add(eventOnClick);
		}
	}
	
	private boolean isMouseInside (int mouseX, int mouseY) {
		return (mouseX >= x && mouseY >= y &&
				mouseX <= x+image.getWidth(null) &&
				mouseY <= y+image.getHeight(null));
	}
}
