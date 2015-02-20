package zlibrary;

import java.util.ArrayList;
import java.util.List;

public abstract class ZSelector extends ZEntity
{
	private List<?> elements = new ArrayList<>();

	private int currentElementToDisplay;
	private int maximumElementsToDisplay;
	
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
