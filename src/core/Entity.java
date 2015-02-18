package core;

/**
 * @author	Anton Andrén & Mattias Benngård
 * @version	0.4
 * @since	2015-02-16
 * 
 * Interface that keeps track of all objects with mouse (click) interaction
 * 
 * All subclasses that implement MouseObject must implement public void onClick(int, int)
 */
public interface Entity
{
	public void onClick (int mouseX, int mouseY);
}
