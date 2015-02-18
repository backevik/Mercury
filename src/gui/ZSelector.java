package gui;

import java.util.ArrayList;
import java.util.List;

public abstract class ZSelector extends ZImage
{
	private List<?> elements = new ArrayList<>();

	private int currentElementToDisplay;
	private int maximumElementsToDisplay;
	
	@SuppressWarnings("rawtypes")
	public ZSelector (Class container, int x, int y, int w, int h, int maximumElementsToDisplay) {
		super("", x, y, w, h);
		this.maximumElementsToDisplay = maximumElementsToDisplay;
	}
	
	public void scrollUp () {
		if (currentElementToDisplay > 0) {
			--currentElementToDisplay;
		}
	}
	
	public void scrollDown () {
		if (currentElementToDisplay + maximumElementsToDisplay < elements.size()) {
			++currentElementToDisplay;			
		}
	}
}
