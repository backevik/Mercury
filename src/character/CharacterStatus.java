package character;

/**
 * @author      Mattias Benngård	<mbengan@gmail.com>
 * @version     0.1
 * @since       2015-02-12
 */

public class CharacterStatus {

	/*public static final int EFFECTS_MAX = 7;
	
	public enum StatusFlags {
		DEAD		(1<<1),
		PARALYZED	(1<<2),
		SLEEPING	(1<<3),
		CONFUSED	(1<<4),
		POISONED	(1<<5),
		BURNING		(1<<6),
		FROZEN		(1<<7);
		
		private final long status;
		
		StatusFlags (long status) {
			this.status = status;
		}
		
		public long getStatus () {
			return status;
		}
	}
	
	public CharacterStatus () {
		// declare a Status Flag with all bits set to 0
	}
	
	public setBit (String effect) {
		// It should set the correct bit Based on DEAD, PARALYZED etc
	}
	
	public clearBit (String effect) {
		// It should clear the correct bit Based on DEAD, PARALYZED etc
	}
	
	public setBit (int b) {
		// It should set the correct bit Based on DEAD, PARALYZED etc
	}
	
	public clearBit (int b) {
		// It should clear the correct bit Based on DEAD, PARALYZED etc
	}
	
	public long getValue () {
		return statusFlags.getStatus();
	}
	
	public String getValueAsString (String d) {
		String s = "";
		d = "|";
		
		int currentEffect = EFFECTS_MAX;
		
		for (int i = EFFECTS_MAX; statusFlags.getStatus() > 0; i--) {
			if (statusFlags.getStatus() > Math.pow(2, i)) {
				// load effect #i based on the StatusFlags
				s+= EFFECT#i + d;
			}
		}
		
		s.substring(0, s.length()-d.length());
				
		return s;
	}*/
}