package core;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.4
 * @since	2015-02-16
 * 
 * Interface that keeps track of all objects with mouse interaction
 * 
 * All classes that implement Entity implement
 * public void onClick (int, int)
 * public void 
 */
public interface Entity
{
	public void onClick (int mouseX, int mouseY);
	public void onHover (int mouseX, int mouseY);
}
